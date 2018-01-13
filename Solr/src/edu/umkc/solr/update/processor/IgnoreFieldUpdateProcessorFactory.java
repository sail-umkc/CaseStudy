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

import org.apache.solr.common.SolrInputField;

import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.solr.schema.FieldType;
import edu.umkc.solr.schema.IndexSchema;
import edu.umkc.type.ISolrCore;

/**
 * Ignores &amp; removes fields matching the specified 
 * conditions from any document being added to the index.
 *
 * <p>
 * By default, this processor ignores any field name which does not 
 * exist according to the schema  
 * </p>
 * 
 * <p>
 * For example, in the configuration below, any field name which would cause 
 * an error because it does not exist, or match a dynamicField, in the 
 * schema.xml would be silently removed from any added documents...
 * </p>
 *
 * <pre class="prettyprint">
 * &lt;processor class="solr.IgnoreFieldUpdateProcessorFactory" /&gt;</pre>
 *
 * <p>
 * In this second example, any field name ending in "_raw" found in a 
 * document being added would be removed...
 * </p>
 * <pre class="prettyprint">
 * &lt;processor class="solr.IgnoreFieldUpdateProcessorFactory"&gt;
 *   &lt;str name="fieldRegex"&gt;.*_raw&lt;/str&gt;
 * &lt;/processor&gt;</pre>
 */
public final class IgnoreFieldUpdateProcessorFactory extends FieldMutatingUpdateProcessorFactory {

  @Override
  public UpdateRequestProcessor getInstance(SolrQueryRequest req,
                                            SolrQueryResponse rsp,
                                            UpdateRequestProcessor next) {
    return new FieldMutatingUpdateProcessor(getSelector(), next) {
      @Override
      protected SolrInputField mutate(final SolrInputField src) {
        return null;
      }
    };
  }

  @Override
  public FieldMutatingUpdateProcessor.FieldNameSelector 
    getDefaultSelector(final ISolrCore core) {

    return new FieldMutatingUpdateProcessor.FieldNameSelector() {
      @Override
      public boolean shouldMutate(final String fieldName) {
        final IndexSchema schema = core.getLatestSchema();
        FieldType type = schema.getFieldTypeNoEx(fieldName);
        return (null == type);

      }
    };
  }
  
}

