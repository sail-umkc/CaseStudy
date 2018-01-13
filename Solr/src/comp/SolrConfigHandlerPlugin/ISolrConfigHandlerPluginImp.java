package comp.SolrConfigHandlerPlugin;


import comp.SolrConfigHandlerPlugin.SolrConfigHandlerPluginArch;

import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

public interface ISolrConfigHandlerPluginImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SolrConfigHandlerPluginArch arch);
	public SolrConfigHandlerPluginArch getArch();
	
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
    public boolean registerSolrConfigHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)  ;        
}