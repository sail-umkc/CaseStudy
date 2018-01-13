package comp.SolrXmlConfig;


import comp.SolrXmlConfig.SolrXmlConfigArch;

import edu.umkc.solr.core.Config;
import edu.umkc.solr.core.NodeConfig;

import edu.umkc.type.ISolrResourceLoader;

import java.io.File;
import java.io.InputStream;

import java.nio.file.Path;

public interface ISolrXmlConfigImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SolrXmlConfigArch arch);
	public SolrXmlConfigArch getArch();
	
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
  
    //To be imported: File,InputStream,Path,Config,NodeConfig,ISolrResourceLoader
    public NodeConfig fromConfig (Config config)  ;        
    public NodeConfig fromFile (ISolrResourceLoader loader,File configFile)  ;        
    public NodeConfig fromString (ISolrResourceLoader loader,String xml)  ;        
    public NodeConfig fromInputStream (ISolrResourceLoader loader,InputStream is)  ;        
    public NodeConfig fromSolrHome (ISolrResourceLoader loader,String solrHome)  ;        
    public NodeConfig fromSolrHome (Path solrHome)  ;        
}