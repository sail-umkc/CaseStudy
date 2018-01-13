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

import org.apache.commons.lang.LocaleUtils;
import org.apache.solr.common.util.NamedList;

import edu.umkc.solr.schema.FieldType;
import edu.umkc.solr.schema.IndexSchema;
import edu.umkc.type.ISolrCore;

import java.util.Locale;

/**
 * Abstract base class for numeric parsing update processor factories.
 * Subclasses can optionally configure a locale.  If no locale is configured,
 * then {@link Locale#ROOT} will be used.  E.g. to configure the French/France
 * locale:
 * 
 * <pre class="prettyprint">
 * &lt;processor class="solr.Parse[Type]FieldUpdateProcessorFactory"&gt;
 *   &lt;str name="locale"&gt;fr_FR&lt;/str&gt;
 *   [...]
 * &lt;/processor&gt;</pre>
 *
 * <p>
 * See {@link Locale} for a description of acceptable language, country (optional)
 * and variant (optional) values, joined with underscore(s).
 * </p>
 */
public abstract class ParseNumericFieldUpdateProcessorFactory extends FieldMutatingUpdateProcessorFactory {

  private static final String LOCALE_PARAM = "locale";

  protected Locale locale = Locale.ROOT;

  @Override
  public void init(NamedList args) {
    String localeParam = (String)args.remove(LOCALE_PARAM);
    if (null != localeParam) {
      locale = LocaleUtils.toLocale(localeParam);
    }
    super.init(args);
  }

  /**
   * Returns true if the given FieldType is compatible with this parsing factory.
   */
  protected abstract boolean isSchemaFieldTypeCompatible(FieldType type);  

  /**
   * Returns true if the field doesn't match any schema field or dynamic field,
   *           or if the matched field's type is compatible
   * @param core Where to get the current schema from
   */
  @Override
  public FieldMutatingUpdateProcessor.FieldNameSelector
  getDefaultSelector(final ISolrCore core) {

    return new FieldMutatingUpdateProcessor.FieldNameSelector() {
      @Override
      public boolean shouldMutate(final String fieldName) {
        final IndexSchema schema = core.getLatestSchema();
        FieldType type = schema.getFieldTypeNoEx(fieldName);
        return (null == type) || isSchemaFieldTypeCompatible(type);
      }
    };
  }
}
