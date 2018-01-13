package comp.JSONResponseWriter;

import java.io.IOException;
import java.io.Writer;

import edu.umkc.solr.request.SolrQueryRequest;
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

public abstract class NaNFloatWriter extends JSONWriter {
  
  abstract protected String getNaN();
  abstract protected String getInf();

  public NaNFloatWriter(Writer writer, SolrQueryRequest req, SolrQueryResponse rsp) {
    super(writer, req, rsp);
  }

  @Override
  public void writeFloat(String name, float val) throws IOException {
    if (Float.isNaN(val)) {
      writer.write(getNaN());
    } else if (Float.isInfinite(val)) {
      if (val < 0.0f)
        writer.write('-');
      writer.write(getInf());
    } else {
      writeFloat(name, Float.toString(val));
    }
  }

  @Override
  public void writeDouble(String name, double val) throws IOException {
    if (Double.isNaN(val)) {
      writer.write(getNaN());
    } else if (Double.isInfinite(val)) {
      if (val < 0.0)
        writer.write('-');
      writer.write(getInf());
    } else {
      writeDouble(name, Double.toString(val));
    }
  }
}