package comp.RawResponseWriter;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.response.QueryResponseWriter;
import edu.umkc.solr.response.QueryResponseWriterUtil;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.type.tmpl.IRawResponseWriter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

import org.apache.commons.io.IOUtils;
import org.apache.solr.common.util.ContentStream;
import org.apache.solr.common.util.NamedList;

import annotation.Feature;
import annotation.Optional;

@Optional(Feature.RAW_RESPONSE)
public class RawResponseWriterImp implements IRawResponseWriterImp, IRawResponseWriter
{
	private RawResponseWriterArch _arch;
	
  public RawResponseWriterImp (){
  }
  
	public void setArch(RawResponseWriterArch arch){
		_arch = arch;
	}
	
	public RawResponseWriterArch getArch(){
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
  
  /** 
   * The key that should be used to add a ContentStream to the 
   * SolrQueryResponse if you intend to use this Writer.
   */
  public static final String CONTENT = "content";
  private String _baseWriter = null;
  
  @Override
  public void init(NamedList n) {
    if( n != null ) {
      Object base = n.get( "base" );
      if( base != null ) {
        _baseWriter = base.toString();
      }
    }
  }

  // Even if this is null, it should be ok
  protected QueryResponseWriter getBaseWriter( SolrQueryRequest request ) {
    return request.getCore().getQueryResponseWriter( _baseWriter );
  }
  
  @Override
  public String getContentType(SolrQueryRequest request, SolrQueryResponse response) {
    Object obj = response.getValues().get( CONTENT );
    if( obj != null && (obj instanceof ContentStream ) ) {
      return ((ContentStream)obj).getContentType();
    }
    return getBaseWriter( request ).getContentType( request, response );
  }

  @Override
  public void write(Writer writer, SolrQueryRequest request, SolrQueryResponse response) throws IOException {
    Object obj = response.getValues().get( CONTENT );
    if( obj != null && (obj instanceof ContentStream ) ) {
      // copy the contents to the writer...
      ContentStream content = (ContentStream)obj;
      try(Reader reader = content.getReader()) {
        IOUtils.copy( reader, writer );
      }
    } else {
      getBaseWriter( request ).write( writer, request, response );
    }
  }

  @Override
  public void write(OutputStream out, SolrQueryRequest request, SolrQueryResponse response) throws IOException {
    Object obj = response.getValues().get( CONTENT );
    if( obj != null && (obj instanceof ContentStream ) ) {
      // copy the contents to the writer...
      ContentStream content = (ContentStream)obj;
      try(InputStream in = content.getStream()) {
        IOUtils.copy( in, out );
      }
    } else {
      QueryResponseWriterUtil.writeQueryResponse(out, 
          getBaseWriter(request), request, response, getContentType(request, response));
    }
  }
}