package comp.PHPResponseWriter;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.type.tmpl.IPHPResponseWriter;

import java.io.IOException;
import java.io.Writer;

import org.apache.solr.common.util.NamedList;

import comp.JSONResponseWriter.JSONWriter;

import annotation.Feature;
import annotation.Optional;


@Optional(Feature.PHP_RESPONSE)
public class PHPResponseWriterImp implements IPHPResponseWriterImp, IPHPResponseWriter
{
	private PHPResponseWriterArch _arch;
	
  public PHPResponseWriterImp (){
  }
  
	public void setArch(PHPResponseWriterArch arch){
		_arch = arch;
	}
	
	public PHPResponseWriterArch getArch(){
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
  
    //To be imported: IOException,Writer,NamedList,SolrQueryRequest,QueryResponseWriter,SolrQueryResponse
  static String CONTENT_TYPE_PHP_UTF8="text/x-php;charset=UTF-8";

  private String contentType = CONTENT_TYPE_PHP_UTF8;

  @Override
  public void init(NamedList namedList) {
    String contentType = (String) namedList.get("content-type");
    if (contentType != null) {
      this.contentType = contentType;
    }
  }

  @Override
  public void write(Writer writer, SolrQueryRequest req, SolrQueryResponse rsp) throws IOException {
    PHPWriter w = new PHPWriter(writer, req, rsp);
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
  
  class PHPWriter extends JSONWriter {
    public PHPWriter(Writer writer, SolrQueryRequest req, SolrQueryResponse rsp) {
      super(writer, req, rsp);
    }
    
    @Override
    public void writeNamedList(String name, NamedList val) throws IOException {
      writeNamedListAsMapMangled(name,val);
    }

    @Override
    public void writeMapOpener(int size) throws IOException {
      writer.write("array(");
    }

    @Override
    public void writeMapCloser() throws IOException {
      writer.write(')');
    }

    @Override
    public void writeArrayOpener(int size) throws IOException {
      writer.write("array(");
    }

    @Override
    public void writeArrayCloser() throws IOException {
      writer.write(')');
    }

    @Override
    public void writeNull(String name) throws IOException {
      writer.write("null");
    }

    @Override
    protected void writeKey(String fname, boolean needsEscaping) throws IOException {
      writeStr(null, fname, needsEscaping);
      writer.write('=');
      writer.write('>');
    }

    @Override
    public void writeStr(String name, String val, boolean needsEscaping) throws IOException {
      if (needsEscaping) {
        writer.write('\'');
        for (int i=0; i<val.length(); i++) {
          char ch = val.charAt(i);
          switch (ch) {
            case '\'':
            case '\\': writer.write('\\'); writer.write(ch); break;
            default:
              writer.write(ch);
          }
        }
        writer.write('\'');
      } else {
        writer.write('\'');
        writer.write(val);
        writer.write('\'');
      }
    }
  }
}
