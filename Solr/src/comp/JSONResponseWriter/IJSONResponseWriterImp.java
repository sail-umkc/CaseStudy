package comp.JSONResponseWriter;


import comp.JSONResponseWriter.JSONResponseWriterArch;

import edu.umkc.solr.request.SolrQueryRequest;

import edu.umkc.solr.response.QueryResponseWriter;
import edu.umkc.solr.response.SolrQueryResponse;

import java.io.IOException;
import java.io.Writer;

import org.apache.solr.common.util.NamedList;

public interface IJSONResponseWriterImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (JSONResponseWriterArch arch);
	public JSONResponseWriterArch getArch();
	
	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init();	
	public void begin();
	public void end();
	public void destroy();

	/*
  	  Implementation primitives required by the architecture
	*/
  
    //To be imported: IOException,Writer,NamedList,SolrQueryRequest,QueryResponseWriter,SolrQueryResponse
    public void write (Writer writer,SolrQueryRequest request,SolrQueryResponse response) throws IOException;        
    public String getContentType (SolrQueryRequest request,SolrQueryResponse response)  ;        
    public void init (NamedList args)  ;        
}