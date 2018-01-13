package comp.DefaultResponseWriters;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.handler.ReplicationHandler;
import edu.umkc.solr.response.QueryResponseWriter;

import java.util.HashMap;
import java.util.Map;

import org.apache.solr.common.params.CommonParams;

import annotation.Feature;

public class DefaultResponseWritersImp implements IDefaultResponseWritersImp
{
	private DefaultResponseWritersArch _arch;

  public DefaultResponseWritersImp (){
  }

	public void setArch(DefaultResponseWritersArch arch){
		_arch = arch;
	}

	public DefaultResponseWritersArch getArch(){
		return _arch;
	}

	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init(){
	  Bootstrapper.incrInitCount();
	}

	public void begin() {
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
  
  //To be imported: Map,QueryResponseWriter
  public Map<String, QueryResponseWriter> getDefaultResponseWriters () {
    HashMap<String, QueryResponseWriter> m = new HashMap<>();
    
    m.put("standard", _arch.OUT_IXMLResponseWriter);
    m.put(CommonParams.JSON, _arch.OUT_IJSONResponseWriter);

    /*@Optional(Feature.XML_RESPONSE)*/
    m.put("xml",  m.get("standard"));
    
    /*@Optional(Feature.PYTHON_RESPONSE)*/
    m.put("python", _arch.OUT_IPythonResponseWriter);
    
    /*@Optional(Feature.PHP_RESPONSE)*/
    m.put("php", _arch.OUT_IPHPResponseWriter);
    
    /*@Optional(Feature.PHPSERIALIZED_RESPONSE)*/
    m.put("phps", _arch.OUT_IPHPSerializedResponseWriter);
    
    /*@Optional(Feature.RUBY_RESPONSE)*/
    m.put("ruby", _arch.OUT_IRubyResponseWriter);
    
    /*@Optional(Feature.RAW_RESPONSE)*/
    m.put("raw", _arch.OUT_IRawResponseWriter);
    
    /*@Optional(Feature.BINARY_RESPONSE)*/
    m.put(CommonParams.JAVABIN, _arch.OUT_IBinaryResponseWriter);
    
    /*@Optional(Feature.CSV_RESPONSE)*/
    m.put("csv", _arch.OUT_ICSVResponseWriter);
    
    /*@Optional(Feature.SORTING_RESPONSE)*/
    m.put("xsort", _arch.OUT_ISortingResponseWriter);
    
    /*@Optional(Feature.SCHEMAXML_RESPONSE)*/
    m.put("schema.xml", _arch.OUT_ISchemaXMLResponseWriter);
    
    /*@Optional(Feature.FILESTREAM_RESPONSE)*/
    m.put(ReplicationHandler.FILE_STREAM, _arch.OUT_IFileStreamResponseWriter);
    
    return m;
  }
}