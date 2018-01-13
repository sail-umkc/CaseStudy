package edu.umkc.solr.spelling.suggest;

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

import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.suggest.DocumentDictionary;

import comp.SolrIndexSearcherFactory.SolrIndexSearcherFactoryImp.SolrIndexSearcher;
import edu.umkc.type.ISolrCore;

/**
 * Factory for {@link DocumentDictionary}
 */
public class DocumentDictionaryFactory extends DictionaryFactory {
  
  public static final String FIELD = "field";
  
  public static final String WEIGHT_FIELD = "weightField";
  
  public static final String PAYLOAD_FIELD = "payloadField";

  @Override
  public Dictionary create(ISolrCore core, SolrIndexSearcher searcher) {
    if(params == null) {
      // should not happen; implies setParams was not called
      throw new IllegalStateException("Value of params not set");
    }
    String field = (String) params.get(FIELD);
    String weightField = (String) params.get(WEIGHT_FIELD);
    String payloadField = (String) params.get(PAYLOAD_FIELD);
    
    if (field == null) {
      throw new IllegalArgumentException(FIELD + " is a mandatory parameter");
    }

    return new DocumentDictionary(searcher.getIndexReader(), field, weightField, payloadField);
  }
  
}
