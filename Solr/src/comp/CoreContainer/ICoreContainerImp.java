package comp.CoreContainer;


import comp.CoreContainer.CoreContainerArch;

import edu.umkc.solr.core.CoresLocator;
import edu.umkc.solr.core.NodeConfig;

import edu.umkc.type.ICoreContainer;
import edu.umkc.type.ISolrResourceLoader;

import java.util.Properties;

public interface ICoreContainerImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (CoreContainerArch arch);
	public CoreContainerArch getArch();
	
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
  
    //To be imported: Properties,CoresLocator,NodeConfig,ICoreContainer,ISolrResourceLoader
    public ICoreContainer initCoreContainer (final ISolrResourceLoader loader)  ;        
    public ICoreContainer initCoreContainer (final NodeConfig config)  ;        
    public ICoreContainer initCoreContainer (final NodeConfig config,final Properties properties)  ;        
    public ICoreContainer initCoreContainer (final NodeConfig config,final Properties properties,final CoresLocator locator)  ;        
    public ICoreContainer initCoreContainer (final String solrHome)  ;        
}