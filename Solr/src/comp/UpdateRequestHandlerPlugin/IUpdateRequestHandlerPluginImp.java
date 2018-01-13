package comp.UpdateRequestHandlerPlugin;


import comp.UpdateRequestHandlerPlugin.UpdateRequestHandlerPluginArch;

import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

public interface IUpdateRequestHandlerPluginImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (UpdateRequestHandlerPluginArch arch);
	public UpdateRequestHandlerPluginArch getArch();
	
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
    public boolean registerUpdateRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)  ;        
}