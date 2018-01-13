package comp.SolrXmlPlugins;


import comp.SolrXmlPlugins.SolrXmlPluginsArch;

import edu.umkc.solr.core.PluginInfo;
import edu.umkc.solr.core.SolrConfig;

import java.util.Map;

public interface ISolrXmlPluginsImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SolrXmlPluginsArch arch);
	public SolrXmlPluginsArch getArch();
	
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
  
    //To be imported: Map,PluginInfo,SolrConfig
    public void registerRequestHandlerPluginsFromSolrXml (final SolrConfig config,final Map<String, PluginInfo> infoMap)  ;        
}