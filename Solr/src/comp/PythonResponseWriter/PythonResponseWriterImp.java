package comp.PythonResponseWriter;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.type.tmpl.IPythonResponseWriter;

import java.io.IOException;
import java.io.Writer;

import org.apache.solr.common.util.NamedList;

import comp.JSONResponseWriter.NaNFloatWriter;

import annotation.Feature;
import annotation.Optional;

@Optional(Feature.PYTHON_RESPONSE)
public class PythonResponseWriterImp implements IPythonResponseWriterImp, IPythonResponseWriter
{
	private PythonResponseWriterArch _arch;
	
  public PythonResponseWriterImp (){
  }
  
	public void setArch(PythonResponseWriterArch arch){
		_arch = arch;
	}
	
	public PythonResponseWriterArch getArch(){
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
  
  static String CONTENT_TYPE_PYTHON_ASCII="text/x-python;charset=US-ASCII";

  @Override
  public void init(NamedList n) {
    /* NOOP */
  }
  
  @Override
  public void write(Writer writer, SolrQueryRequest req, SolrQueryResponse rsp) throws IOException {
    PythonWriter w = new PythonWriter(writer, req, rsp);
    try {
      w.writeResponse();
    } finally {
      w.close();
    }
  }

  @Override
  public String getContentType(SolrQueryRequest request, SolrQueryResponse response) {
    return CONTENT_TYPE_TEXT_ASCII;
  }
  
  class PythonWriter extends NaNFloatWriter {
    @Override
    protected String getNaN() { return "float('NaN')"; }
    @Override
    protected String getInf() { return "float('Inf')"; }

    public PythonWriter(Writer writer, SolrQueryRequest req, SolrQueryResponse rsp) {
      super(writer, req, rsp);
    }

    @Override
    public void writeNull(String name) throws IOException {
      writer.write("None");
    }

    @Override
    public void writeBool(String name, boolean val) throws IOException {
      writer.write(val ? "True" : "False");
    }

    @Override
    public void writeBool(String name, String val) throws IOException {
      writeBool(name,val.charAt(0)=='t');
    }

    /* optionally use a unicode python string if necessary */
    @Override
    public void writeStr(String name, String val, boolean needsEscaping) throws IOException {
      if (!needsEscaping) {
        writer.write('\'');
        writer.write(val);
        writer.write('\'');
        return;
      }

      // use python unicode strings...
      // python doesn't tolerate newlines in strings in its eval(), so we must escape them.

      StringBuilder sb = new StringBuilder(val.length());
      boolean needUnicode=false;

      for (int i=0; i<val.length(); i++) {
        char ch = val.charAt(i);
        switch(ch) {
          case '\'':
          case '\\': sb.append('\\'); sb.append(ch); break;
          case '\r': sb.append("\\r"); break;
          case '\n': sb.append("\\n"); break;
          case '\t': sb.append("\\t"); break;
          default:
            // we don't strictly have to escape these chars, but it will probably increase
            // portability to stick to visible ascii
            if (ch<' ' || ch>127) {
              unicodeEscape(sb, ch);
              needUnicode=true;
            } else {
              sb.append(ch);
            }
        }
      }

      if (needUnicode) {
        writer.write('u');
      }
      writer.write('\'');
      writer.append(sb);
      writer.write('\'');
    }

    /*
    old version that always used unicode
    public void writeStr(String name, String val, boolean needsEscaping) throws IOException {
      // use python unicode strings...
      // python doesn't tolerate newlines in strings in its eval(), so we must escape them.
      writer.write("u'");
      // it might be more efficient to use a stringbuilder or write substrings
      // if writing chars to the stream is slow.
      if (needsEscaping) {
        for (int i=0; i<val.length(); i++) {
          char ch = val.charAt(i);
          switch(ch) {
            case '\'':
            case '\\': writer.write('\\'); writer.write(ch); break;
            case '\r': writer.write("\\r"); break;
            case '\n': writer.write("\\n"); break;
            default:
              // we don't strictly have to escape these chars, but it will probably increase
              // portability to stick to visible ascii
              if (ch<' ' || ch>127) {
                unicodeChar(ch);
              } else {
                writer.write(ch);
              }
          }
        }
      } else {
        writer.write(val);
      }
      writer.write('\'');
    }
    */

  }
}