package edu.umkc.type.tmpl;

import java.io.IOException;
import java.io.Writer;

import org.apache.solr.common.util.NamedList;

import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.response.QueryResponseWriter;
import edu.umkc.solr.response.SolrQueryResponse;

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

public interface IPHPResponseWriter extends QueryResponseWriter
{
  /**
   * Write a SolrQueryResponse, this method must be thread save.
   *
   * <p>
   * Information about the request (in particular: formating options) may be 
   * obtained from <code>req</code> but the dominant source of information 
   * should be <code>rsp</code>.
   * <p>
   * There are no mandatory actions that write must perform.
   * An empty write implementation would fulfill
   * all interface obligations.
   * </p> 
   */
  public void write(Writer writer, SolrQueryRequest request, SolrQueryResponse response) throws IOException;

  /** 
   * Return the applicable Content Type for a request, this method 
   * must be thread safe.
   *
   * <p>
   * QueryResponseWriter's must implement this method to return a valid 
   * HTTP Content-Type header for the request, that will logically 
   * correspond with the output produced by the write method.
   * </p>
   * @return a Content-Type string, which may not be null.
   */
  public String getContentType(SolrQueryRequest request, SolrQueryResponse response);
  
  /** <code>init</code> will be called just once, immediately after creation.
   * <p>The args are user-level initialization parameters that
   * may be specified when declaring a response writer in
   * solrconfig.xml
   */
  @Override
  public void init(NamedList args);  
}
