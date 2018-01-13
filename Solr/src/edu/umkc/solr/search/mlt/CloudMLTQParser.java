package edu.umkc.solr.search.mlt;
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

import org.apache.lucene.queries.mlt.MoreLikeThis;
import org.apache.lucene.search.Query;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrException;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.request.SolrQueryRequestBase;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.solr.schema.SchemaField;
import edu.umkc.solr.search.QParser;
import edu.umkc.solr.search.QueryParsing;
import edu.umkc.type.ISolrCore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CloudMLTQParser extends QParser {

  public CloudMLTQParser(String qstr, SolrParams localParams,
                         SolrParams params, SolrQueryRequest req) {
    super(qstr, localParams, params, req);
  }

  private static Logger log = LoggerFactory
      .getLogger(CloudMLTQParser.class);
  public Query parse() {
    String id = localParams.get(QueryParsing.V);
    // Do a Real Time Get for the document
    SolrDocument doc = getDocument(id);
    if(doc == null) {
      new SolrException(
          SolrException.ErrorCode.BAD_REQUEST, "Error completing MLT request. Could not fetch " +
          "document with id [" + id + "]");
    }
    
    MoreLikeThis mlt = new MoreLikeThis(req.getSearcher().getIndexReader());
    // TODO: Are the mintf and mindf defaults ok at 1/0 ?
    
    mlt.setMinTermFreq(localParams.getInt("mintf", 1));
    mlt.setMinDocFreq(localParams.getInt("mindf", 0));
    if(localParams.get("minwl") != null)
      mlt.setMinWordLen(localParams.getInt("minwl"));
    
    if(localParams.get("maxwl") != null)
      mlt.setMaxWordLen(localParams.getInt("maxwl"));

    mlt.setAnalyzer(req.getSchema().getIndexAnalyzer());

    String[] qf = localParams.getParams("qf");
    Map<String, Collection<Object>> filteredDocument = new HashMap();

    if (qf != null) {
      mlt.setFieldNames(qf);
      for (String field : qf) {
        filteredDocument.put(field, doc.getFieldValues(field));
      }
    } else {
      Map<String, SchemaField> fields = req.getSchema().getFields();
      ArrayList<String> fieldNames = new ArrayList();
      for (String field : doc.getFieldNames()) {
        // Only use fields that are stored and have an explicit analyzer.
        // This makes sense as the query uses tf/idf/.. for query construction.
        // We might want to relook and change this in the future though.
        if(fields.get(field).stored() 
            && fields.get(field).getType().isExplicitAnalyzer()) {
          fieldNames.add(field);
          filteredDocument.put(field, doc.getFieldValues(field));
        }
      }
      mlt.setFieldNames(fieldNames.toArray(new String[fieldNames.size()]));
    }

    try {
      return mlt.like(filteredDocument);
    } catch (IOException e) {
      e.printStackTrace();
      throw new SolrException(SolrException.ErrorCode.BAD_REQUEST, "Bad Request");
    }

  }

  private SolrDocument getDocument(String id) {
    ISolrCore core = req.getCore();
    SolrQueryResponse rsp = new SolrQueryResponse();
    ModifiableSolrParams params = new ModifiableSolrParams();
    params.add("id", id);

    SolrQueryRequestBase request = new SolrQueryRequestBase(core, params) {
    };

    core.getRequestHandler("/get").handleRequest(request, rsp);
    NamedList response = rsp.getValues();

    return (SolrDocument) response.get("doc");
  }

}
