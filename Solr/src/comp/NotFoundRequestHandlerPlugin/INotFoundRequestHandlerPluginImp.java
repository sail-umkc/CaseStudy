package comp.NotFoundRequestHandlerPlugin;


import comp.NotFoundRequestHandlerPlugin.NotFoundRequestHandlerPluginArch;

import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

public interface INotFoundRequestHandlerPluginImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (NotFoundRequestHandlerPluginArch arch);
	public NotFoundRequestHandlerPluginArch getArch();
	
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
  
    //To be imported: Map,PluginInfo
    public boolean registerNotFoundRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)  ;        
}