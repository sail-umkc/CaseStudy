package comp.BinaryResponseWriter;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.solr.schema.BinaryField;
import edu.umkc.solr.schema.BoolField;
import edu.umkc.solr.schema.StrField;
import edu.umkc.solr.schema.TextField;
import edu.umkc.solr.schema.TrieDateField;
import edu.umkc.solr.schema.TrieDoubleField;
import edu.umkc.solr.schema.TrieField;
import edu.umkc.solr.schema.TrieFloatField;
import edu.umkc.solr.schema.TrieIntField;
import edu.umkc.solr.schema.TrieLongField;
import edu.umkc.type.tmpl.IBinaryResponseWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import org.apache.solr.client.solrj.impl.BinaryResponseParser;
import org.apache.solr.common.params.CommonParams;
import org.apache.solr.common.util.JavaBinCodec;
import org.apache.solr.common.util.NamedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import annotation.Feature;
import annotation.Optional;

@Optional(Feature.BINARY_RESPONSE)
public class BinaryResponseWriterImp implements IBinaryResponseWriterImp, IBinaryResponseWriter
{
	private BinaryResponseWriterArch _arch;

  public BinaryResponseWriterImp() {
  }
  
	public void setArch(BinaryResponseWriterArch arch){
		_arch = arch;
	}
	
	public BinaryResponseWriterArch getArch(){
		return _arch;
	}

	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init() {    
    Bootstrapper.incrInitCount();
	}
	
	public void begin(){
    Bootstrapper.incrBeginCount();
	}
	
	public void end(){
		//TODO Auto-generated method stub
	}
	
	public void destroy(){
		//TODO Auto-generated method stub
	}

	/*
  	  Implementation primitives required by the architecture
	*/
  
  static final Logger LOG = LoggerFactory.getLogger(BinaryResponseWriterImp.class);
  public static final Set<Class> KNOWN_TYPES = new HashSet<>();

  @Override
  public void write(OutputStream out, SolrQueryRequest req, SolrQueryResponse response) throws IOException {
    Resolver resolver = new Resolver(req, response.getReturnFields());
    Boolean omitHeader = req.getParams().getBool(CommonParams.OMIT_HEADER);
    if (omitHeader != null && omitHeader) response.getValues().remove("responseHeader");
    JavaBinCodec codec = new JavaBinCodec(resolver);
    codec.marshal(response.getValues(), out);
  }

  @Override
  public void write(Writer writer, SolrQueryRequest request, SolrQueryResponse response) throws IOException {
    throw new RuntimeException("This is a binary writer , Cannot write to a characterstream");
  }

  @Override
  public String getContentType(SolrQueryRequest request, SolrQueryResponse response) {
    return BinaryResponseParser.BINARY_CONTENT_TYPE;
  }

  @Override
  public void init(NamedList args) {
    /* NOOP */
  }


  /**
   * TODO -- there may be a way to do this without marshal at all...
   *
   * @return a response object equivalent to what you get from the XML/JSON/javabin parser. Documents become
   *         SolrDocuments, DocList becomes SolrDocumentList etc.
   *
   * @since solr 1.4
   */
  @SuppressWarnings("unchecked")
  public static NamedList<Object> getParsedResponse(SolrQueryRequest req, SolrQueryResponse rsp) {
    try {
      Resolver resolver = new Resolver(req, rsp.getReturnFields());

      ByteArrayOutputStream out = new ByteArrayOutputStream();
      new JavaBinCodec(resolver).marshal(rsp.getValues(), out);

      InputStream in = new ByteArrayInputStream(out.toByteArray());
      return (NamedList<Object>) new JavaBinCodec(resolver).unmarshal(in);
    }
    catch (Exception ex) {
      throw new RuntimeException(ex);
    }
  }

  static {
    KNOWN_TYPES.add(BoolField.class);
    KNOWN_TYPES.add(StrField.class);
    KNOWN_TYPES.add(TextField.class);
    KNOWN_TYPES.add(TrieField.class);
    KNOWN_TYPES.add(TrieIntField.class);
    KNOWN_TYPES.add(TrieLongField.class);
    KNOWN_TYPES.add(TrieFloatField.class);
    KNOWN_TYPES.add(TrieDoubleField.class);
    KNOWN_TYPES.add(TrieDateField.class);
    KNOWN_TYPES.add(BinaryField.class);
    // We do not add UUIDField because UUID object is not a supported type in JavaBinCodec
    // and if we write UUIDField.toObject, we wouldn't know how to handle it in the client side
  }
}