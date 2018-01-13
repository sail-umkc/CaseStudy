package comp.BinaryResponseWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.util.BytesRef;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.util.JavaBinCodec;

import comp.SolrIndexSearcherFactory.SolrIndexSearcherFactoryImp.SolrIndexSearcher;

import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.response.ResultContext;
import edu.umkc.solr.response.transform.DocTransformer;
import edu.umkc.solr.response.transform.TransformContext;
import edu.umkc.solr.schema.FieldType;
import edu.umkc.solr.schema.IndexSchema;
import edu.umkc.solr.schema.SchemaField;
import edu.umkc.solr.search.DocList;
import edu.umkc.solr.search.ReturnFields;
import edu.umkc.solr.search.SolrReturnFields;

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

public class Resolver implements JavaBinCodec.ObjectResolver {
  protected final SolrQueryRequest solrQueryRequest;
  protected IndexSchema schema;
  protected SolrIndexSearcher searcher;
  protected final ReturnFields returnFields;

  // transmit field values using FieldType.toObject()
  // rather than the String from FieldType.toExternal()
  boolean useFieldObjects = true;

  public Resolver(SolrQueryRequest req, ReturnFields returnFields) {
    solrQueryRequest = req;
    this.returnFields = returnFields;
  }

  @Override
  public Object resolve(Object o, JavaBinCodec codec) throws IOException {
    if (o instanceof ResultContext) {
      writeResults((ResultContext) o, codec);
      return null; // null means we completely handled it
    }
    if (o instanceof DocList) {
      ResultContext ctx = new ResultContext();
      ctx.docs = (DocList) o;
      writeResults(ctx, codec);
      return null; // null means we completely handled it
    }
    if( o instanceof IndexableField ) {
      if(schema == null) schema = solrQueryRequest.getSchema(); 
      
      IndexableField f = (IndexableField)o;
      SchemaField sf = schema.getFieldOrNull(f.name());
      try {
        o = getValue(sf, f);
      } 
      catch (Exception e) {
        BinaryResponseWriterImp.LOG.warn("Error reading a field : " + o, e);
      }
    }
    if (o instanceof SolrDocument) {
      // Remove any fields that were not requested.
      // This typically happens when distributed search adds 
      // extra fields to an internal request
      SolrDocument doc = (SolrDocument)o;
      Iterator<Map.Entry<String, Object>> i = doc.iterator();
      while ( i.hasNext() ) {
        String fname = i.next().getKey();
        if ( !returnFields.wantsField( fname ) ) {
          i.remove();
        }
      }
      return doc;
    }
    return o;
  }

  protected void writeResultsBody( ResultContext res, JavaBinCodec codec ) throws IOException 
  {
    DocList ids = res.docs;
    int sz = ids.size();
    codec.writeTag(JavaBinCodec.ARR, sz);
    if(searcher == null) searcher = solrQueryRequest.getSearcher();
    if(schema == null) schema = solrQueryRequest.getSchema();

    DocTransformer transformer = returnFields.getTransformer();
    TransformContext context = new TransformContext();
    context.query = res.query;
    context.wantsScores = returnFields.wantsScore() && ids.hasScores();
    context.req = solrQueryRequest;
    context.searcher = searcher;
    if( transformer != null ) {
      transformer.setContext( context );
    }
    
    Set<String> fnames = returnFields.getLuceneFieldNames();
    boolean onlyPseudoFields = (fnames == null && !returnFields.wantsAllFields() && !returnFields.hasPatternMatching())
        || (fnames != null && fnames.size() == 1 && SolrReturnFields.SCORE.equals(fnames.iterator().next()));
    context.iterator = ids.iterator();
    for (int i = 0; i < sz; i++) {
      int id = context.iterator.nextDoc();
      SolrDocument sdoc;
      if (onlyPseudoFields) {
        // no need to get stored fields of the document, see SOLR-5968
        sdoc = new SolrDocument();
      } else {
        Document doc = searcher.doc(id, fnames);
        sdoc = getDoc(doc);
      }
      if( transformer != null ) {
        transformer.transform(sdoc, id);
      }
      codec.writeSolrDocument(sdoc);
    }
    if( transformer != null ) {
      transformer.setContext( null );
    }
  }
  
  public void writeResults(ResultContext ctx, JavaBinCodec codec) throws IOException {
    codec.writeTag(JavaBinCodec.SOLRDOCLST);
    boolean wantsScores = returnFields.wantsScore() && ctx.docs.hasScores();
    List l = new ArrayList(3);
    l.add((long) ctx.docs.matches());
    l.add((long) ctx.docs.offset());
    
    Float maxScore = null;
    if (wantsScores) {
      maxScore = ctx.docs.maxScore();
    }
    l.add(maxScore);
    codec.writeArray(l);
    
    // this is a seprate function so that streaming responses can use just that part
    writeResultsBody( ctx, codec );
  }

  public SolrDocument getDoc(Document doc) {
    SolrDocument solrDoc = new SolrDocument();
    for (IndexableField f : doc) {
      String fieldName = f.name();
      if( !returnFields.wantsField(fieldName) )
        continue;

      SchemaField sf = schema.getFieldOrNull(fieldName);
      Object val = null;
      try {
        val = getValue(sf,f);
      } catch (Exception e) {
        // There is a chance of the underlying field not really matching the
        // actual field type . So ,it can throw exception
        BinaryResponseWriterImp.LOG.warn("Error reading a field from document : " + solrDoc, e);
        //if it happens log it and continue
        continue;
      }
        
      if(sf != null && sf.multiValued() && !solrDoc.containsKey(fieldName)){
        ArrayList l = new ArrayList();
        l.add(val);
        solrDoc.addField(fieldName, l);
      } else {
        solrDoc.addField(fieldName, val);
      }
    }
    return solrDoc;
  }
  
  public Object getValue(SchemaField sf, IndexableField f) throws Exception {
    FieldType ft = null;
    if(sf != null) ft =sf.getType();
    
    if (ft == null) {  // handle fields not in the schema
      BytesRef bytesRef = f.binaryValue();
      if (bytesRef != null) {
        if (bytesRef.offset == 0 && bytesRef.length == bytesRef.bytes.length) {
          return bytesRef.bytes;
        } else {
          final byte[] bytes = new byte[bytesRef.length];
          System.arraycopy(bytesRef.bytes, bytesRef.offset, bytes, 0, bytesRef.length);
          return bytes;
        }
      } else return f.stringValue();
    } else {
      if (useFieldObjects && BinaryResponseWriterImp.KNOWN_TYPES.contains(ft.getClass())) {
        return ft.toObject(f);
      } else {
        return ft.toExternal(f);
      }
    }
  }
}
