package comp.JSONResponseWriter;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.type.tmpl.IJSONResponseWriter;

import java.io.IOException;
import java.io.Writer;

import org.apache.solr.common.util.NamedList;

import annotation.Feature;
import annotation.Optional;

public class JSONResponseWriterImp implements IJSONResponseWriterImp, IJSONResponseWriter
{
	private JSONResponseWriterArch _arch;
	
  public JSONResponseWriterImp (){
  }
  
	public void setArch(JSONResponseWriterArch arch){
		_arch = arch;
	}
	
	public JSONResponseWriterArch getArch(){
		return _arch;
	}
	
	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init(){
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
  
  static String CONTENT_TYPE_JSON_UTF8 = "application/json; charset=UTF-8";

  private String contentType = CONTENT_TYPE_JSON_UTF8;

  @Override
  public void init(NamedList namedList) {
    String contentType = (String) namedList.get("content-type");
    if (contentType != null) {
      this.contentType = contentType;
    }
  }

  @Override
  public void write(Writer writer, SolrQueryRequest req, SolrQueryResponse rsp) throws IOException {
    JSONWriter w = new JSONWriter(writer, req, rsp);
    try {
      w.writeResponse();
    } finally {
      w.close();
    }
  }

  @Override
  public String getContentType(SolrQueryRequest request, SolrQueryResponse response) {
    return contentType;
  }
}