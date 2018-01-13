package comp.RubyResponseWriter;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.type.tmpl.IRubyResponseWriter;

import java.io.IOException;
import java.io.Writer;

import org.apache.solr.common.util.NamedList;

import annotation.Feature;
import annotation.Optional;

@Optional(Feature.RUBY_RESPONSE)
public class RubyResponseWriterImp implements IRubyResponseWriterImp, IRubyResponseWriter
{
	private RubyResponseWriterArch _arch;
	
  public RubyResponseWriterImp (){
  }
  	
  public void setArch(RubyResponseWriterArch arch){
		_arch = arch;
	}
	
  public RubyResponseWriterArch getArch(){
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
  
  static String CONTENT_TYPE_RUBY_UTF8="text/x-ruby;charset=UTF-8";

  @Override
  public void init(NamedList n) {
    /* NOOP */
  }
  
  @Override
  public void write(Writer writer, SolrQueryRequest req, SolrQueryResponse rsp) throws IOException {
    RubyWriter w = new RubyWriter(writer, req, rsp);
    try {
      w.writeResponse();
    } finally {
      w.close();
    }
  }

  @Override
  public String getContentType(SolrQueryRequest request, SolrQueryResponse response) {
    return CONTENT_TYPE_TEXT_UTF8;
  }
}