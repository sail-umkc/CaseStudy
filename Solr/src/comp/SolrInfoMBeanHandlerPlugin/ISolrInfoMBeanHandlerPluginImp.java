package comp.SolrInfoMBeanHandlerPlugin;


import comp.SolrInfoMBeanHandlerPlugin.SolrInfoMBeanHandlerPluginArch;

import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

public interface ISolrInfoMBeanHandlerPluginImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SolrInfoMBeanHandlerPluginArch arch);
	public SolrInfoMBeanHandlerPluginArch getArch();
	
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
    public boolean registerSolrInfoMBeanHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)  ;        
}