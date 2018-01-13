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

package edu.umkc.solr.update.processor;

import org.apache.solr.common.util.NamedList;

import comp.SolrCoreFactory.SolrCoreFactoryImp.SolrCore;

import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.solr.util.plugin.NamedListInitializedPlugin;
import edu.umkc.solr.util.plugin.SolrCoreAware;

/**
 * A factory to generate an UpdateRequestProcessor for each request.  
 * 
 * If the factory needs access to {@link SolrCore} in initialization, it could 
 * implement {@link SolrCoreAware}
 * 
 * @since solr 1.3
 */
public abstract class UpdateRequestProcessorFactory implements NamedListInitializedPlugin
{

  /** A marker interface for UpdateRequestProcessorFactory implementations indicating that
   * the factory should be used even if the update.distrib parameter would otherwise cause
   * it to not be run.
   */
  public interface RunAlways {}

  @Override
  public void init( NamedList args )
  {
    // could process the Node
  }
  
  abstract public UpdateRequestProcessor getInstance(SolrQueryRequest req, SolrQueryResponse rsp, UpdateRequestProcessor next );
}
