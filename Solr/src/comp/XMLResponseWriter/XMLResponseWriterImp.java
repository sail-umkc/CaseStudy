package comp.XMLResponseWriter;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.solr.response.XMLWriter;
import edu.umkc.type.tmpl.IXMLResponseWriter;

import java.io.IOException;
import java.io.Writer;

import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.common.util.NamedList;


public class XMLResponseWriterImp implements IXMLResponseWriterImp, IXMLResponseWriter
{
	private XMLResponseWriterArch _arch;

  public XMLResponseWriterImp (){
  }
  
	public void setArch(XMLResponseWriterArch arch){
		_arch = arch;
	}
	public XMLResponseWriterArch getArch(){
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
  
  @Override
  public void init(NamedList n) {
    /* NOOP */
  }

  @Override
  public void write(Writer writer, SolrQueryRequest req, SolrQueryResponse rsp) throws IOException {
    XMLWriter w = new XMLWriter(writer, req, rsp);
    try {
      w.writeResponse();
    } finally {
      w.close();
    }
  }

  @Override
  public String getContentType(SolrQueryRequest request, SolrQueryResponse response) {
    return XMLResponseParser.XML_CONTENT_TYPE;
  }
}