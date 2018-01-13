package edu.umkc.type;

import edu.umkc.solr.core.PluginBag;
import edu.umkc.solr.core.SolrConfig;
import edu.umkc.solr.request.SolrRequestHandler;

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

public interface IRequestHandlers
{
  /**
   * @return the RequestHandler registered at the given name 
   */
  public SolrRequestHandler get(String handlerName);

  /**
   * Handlers must be initialized before calling this function.  As soon as this is
   * called, the handler can immediately accept requests.
   * 
   * This call is thread safe.
   * 
   * @return the previous handler at the given path or null
   */
  public SolrRequestHandler register( String handlerName, SolrRequestHandler handler );


  /**
   * Returns an unmodifiable Map containing the registered handlers
   */
  public PluginBag<SolrRequestHandler> getRequestHandlers();


  /**
   * Read solrconfig.xml and register the appropriate handlers
   * 
   * This function should <b>only</b> be called from the SolrCore constructor.  It is
   * not intended as a public API.
   * 
   * While the normal runtime registration contract is that handlers MUST be initialized
   * before they are registered, this function does not do that exactly.
   *
   * This function registers all handlers first and then calls init() for each one.
   *
   * This is OK because this function is only called at startup and there is no chance that
   * a handler could be asked to handle a request before it is initialized.
   * 
   * The advantage to this approach is that handlers can know what path they are registered
   * to and what other handlers are available at startup.
   * 
   * Handlers will be registered and initialized in the order they appear in solrconfig.xml
   */

  public void initHandlersFromConfig(SolrConfig config);

  public void close();
}
