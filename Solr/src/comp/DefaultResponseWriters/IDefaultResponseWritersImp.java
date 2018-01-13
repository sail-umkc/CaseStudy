package comp.DefaultResponseWriters;


import comp.DefaultResponseWriters.DefaultResponseWritersArch;

import edu.umkc.solr.response.QueryResponseWriter;

import java.util.Map;

public interface IDefaultResponseWritersImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (DefaultResponseWritersArch arch);
	public DefaultResponseWritersArch getArch();
	
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
  
    //To be imported: Map,QueryResponseWriter
    public Map<String, QueryResponseWriter> getDefaultResponseWriters ()  ;        
}