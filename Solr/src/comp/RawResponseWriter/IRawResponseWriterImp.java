package comp.RawResponseWriter;


import comp.RawResponseWriter.RawResponseWriterArch;

import edu.umkc.solr.request.SolrQueryRequest;

import edu.umkc.solr.response.BinaryQueryResponseWriter;
import edu.umkc.solr.response.SolrQueryResponse;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.apache.solr.common.util.NamedList;

public interface IRawResponseWriterImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (RawResponseWriterArch arch);
	public RawResponseWriterArch getArch();
	
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
  
    //To be imported: IOException,OutputStream,Writer,NamedList,SolrQueryRequest,BinaryQueryResponseWriter,SolrQueryResponse
    public void write (Writer writer,SolrQueryRequest request,SolrQueryResponse response) throws IOException;        
    public String getContentType (SolrQueryRequest request,SolrQueryResponse response)  ;        
    public void init (NamedList args)  ;        
    public void write (OutputStream out,SolrQueryRequest request,SolrQueryResponse response) throws IOException;        
}