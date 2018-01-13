/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.umkc.solr.core;

import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.util.NamedList;

import edu.umkc.solr.request.LocalSolrQueryRequest;
import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.request.SolrRequestInfo;
import edu.umkc.solr.response.ResultContext;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.solr.search.DocIterator;
import edu.umkc.solr.search.DocList;
import comp.SolrIndexSearcherFactory.SolrIndexSearcherFactoryImp.SolrIndexSearcher;
import edu.umkc.type.ISolrCore;

import java.util.List;

/**
 *
 */
public class QuerySenderListener extends AbstractSolrEventListener {
  public QuerySenderListener(ISolrCore core) {
    super(core);
  }

  @Override
  public void newSearcher(SolrIndexSearcher newSearcher, SolrIndexSearcher currentSearcher) {
    final SolrIndexSearcher searcher = newSearcher;
    log.info("QuerySenderListener sending requests to " + newSearcher);
    List<NamedList> allLists = (List<NamedList>)getArgs().get("queries");
    if (allLists == null) return;
    for (NamedList nlst : allLists) {
      SolrQueryRequest req = null;

      try {
        // bind the request to a particular searcher (the newSearcher)
        NamedList params = addEventParms(currentSearcher, nlst);
        // for this, we default to distrib = false
        if (params.get("distrib") == null) {
          params.add("distrib", false);
        }
        req = new LocalSolrQueryRequest(getCore(),params) {
          @Override public SolrIndexSearcher getSearcher() { return searcher; }
          @Override public void close() { }
        };

        SolrQueryResponse rsp = new SolrQueryResponse();
        SolrRequestInfo.setRequestInfo(new SolrRequestInfo(req, rsp));
        getCore().execute(getCore().getRequestHandler(req.getParams().get(CommonParams.QT)), req, rsp);

        // Retrieve the Document instances (not just the ids) to warm
        // the OS disk cache, and any Solr document cache.  Only the top
        // level values in the NamedList are checked for DocLists.
        NamedList values = rsp.getValues();
        for (int i=0; i<values.size(); i++) {
          Object o = values.getVal(i);
          if (o instanceof ResultContext) {
            o = ((ResultContext)o).docs;
          }
          if (o instanceof DocList) {
            DocList docs = (DocList)o;
            for (DocIterator iter = docs.iterator(); iter.hasNext();) {
              newSearcher.doc(iter.nextDoc());
            }
          }
        }

      } catch (Exception e) {
        // do nothing... we want to continue with the other requests.
        // the failure should have already been logged.
      } finally {
        if (req != null) req.close();
        SolrRequestInfo.clearRequestInfo();
      }
    }
    log.info("QuerySenderListener done.");
  }


}
