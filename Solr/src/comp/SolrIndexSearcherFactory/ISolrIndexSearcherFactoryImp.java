package comp.SolrIndexSearcherFactory;


import comp.SolrIndexSearcherFactory.SolrIndexSearcherFactoryArch;

import comp.SolrIndexSearcherFactory.SolrIndexSearcherFactoryImp.SolrIndexSearcher;

import edu.umkc.solr.core.DirectoryFactory;

import edu.umkc.solr.schema.IndexSchema;

import edu.umkc.type.ISolrCore;

import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;

public interface ISolrIndexSearcherFactoryImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SolrIndexSearcherFactoryArch arch);
	public SolrIndexSearcherFactoryArch getArch();
	
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
  
    //To be imported: IOException,DirectoryReader,DirectoryFactory,IndexSchema,SolrIndexSearcher,ISolrCore
    public SolrIndexSearcher createSolrIndexSearcher (ISolrCore core,String path,IndexSchema schema,String name,DirectoryReader r,boolean closeReader,boolean enableCache,boolean reserveDirectory,DirectoryFactory directoryFactory) throws IOException;        
}