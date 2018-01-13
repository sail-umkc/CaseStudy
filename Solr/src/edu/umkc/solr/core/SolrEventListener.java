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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import comp.SolrCoreFactory.SolrCoreFactoryImp.SolrCore;
import comp.SolrIndexSearcherFactory.SolrIndexSearcherFactoryImp.SolrIndexSearcher;
import edu.umkc.solr.util.plugin.NamedListInitializedPlugin;

/**
 *
 */
public interface SolrEventListener extends NamedListInitializedPlugin{
  static final Logger log = LoggerFactory.getLogger(SolrCore.class);


  public void postCommit();
  
  public void postSoftCommit();

  /** The searchers passed here are only guaranteed to be valid for the duration
   * of this method call, so care should be taken not to spawn threads or asynchronous
   * tasks with references to these searchers.
   * <p>
   * Implementations should add the {@link org.apache.solr.common.params.EventParams#EVENT} parameter and set it to a value of either:
   * <ul>
   * <li>{@link org.apache.solr.common.params.EventParams#FIRST_SEARCHER} - First Searcher event</li>
   * <li>{@link org.apache.solr.common.params.EventParams#NEW_SEARCHER} - New Searcher event</li>
   * </ul>
   *
   * Sample:
   * <pre>
    if (currentSearcher != null) {
      nlst.add(CommonParams.EVENT, CommonParams.NEW_SEARCHER);
    } else {
      nlst.add(CommonParams.EVENT, CommonParams.FIRST_SEARCHER);
    }
   *
   * </pre>
   *
   * @see edu.umkc.solr.core.AbstractSolrEventListener#addEventParms(comp.SolrIndexSearcherFactory.SolrIndexSearcherFactoryImp.SolrIndexSearcher, org.apache.solr.common.util.NamedList) 
   *
   * @param newSearcher The new {@link comp.SolrIndexSearcherFactory.SolrIndexSearcherFactoryImp.SolrIndexSearcher} to use
   * @param currentSearcher The existing {@link comp.SolrIndexSearcherFactory.SolrIndexSearcherFactoryImp.SolrIndexSearcher}.  null if this is a firstSearcher event.
   *
   */
  public void newSearcher(SolrIndexSearcher newSearcher, SolrIndexSearcher currentSearcher);

}
