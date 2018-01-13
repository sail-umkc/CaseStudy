package comp.ImplicitPlugins;


import comp.ImplicitPlugins.ImplicitPluginsArch;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.ISolrCore;

import java.util.List;

public interface IImplicitPluginsImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (ImplicitPluginsArch arch);
	public ImplicitPluginsArch getArch();
	
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
  
    //To be imported: List,PluginInfo,ISolrCore
    public List<PluginInfo> getHandlers (final ISolrCore solrCore)  ;        
}