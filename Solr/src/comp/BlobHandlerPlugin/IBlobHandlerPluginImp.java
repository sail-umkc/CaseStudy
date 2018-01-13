package comp.BlobHandlerPlugin;


import comp.BlobHandlerPlugin.BlobHandlerPluginArch;

import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

public interface IBlobHandlerPluginImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (BlobHandlerPluginArch arch);
	public BlobHandlerPluginArch getArch();
	
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
    public boolean registerBlobHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)  ;        
}