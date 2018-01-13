package comp.SolrIndexWriterFactory;


import comp.SolrIndexWriterFactory.SolrIndexWriterFactoryArch;

import comp.SolrIndexWriterFactory.SolrIndexWriterFactoryImp.SolrIndexWriter;

import edu.umkc.solr.core.DirectoryFactory;

import edu.umkc.solr.schema.IndexSchema;

import edu.umkc.solr.update.SolrIndexConfig;

import edu.umkc.type.ISolrCore;

import java.io.IOException;

import org.apache.lucene.codecs.Codec;

import org.apache.lucene.index.IndexDeletionPolicy;

public interface ISolrIndexWriterFactoryImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SolrIndexWriterFactoryArch arch);
	public SolrIndexWriterFactoryArch getArch();
	
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
  
    //To be imported: IOException,Codec,IndexDeletionPolicy,SolrIndexWriter,DirectoryFactory,IndexSchema,SolrIndexConfig,ISolrCore
    public SolrIndexWriter createSolrIndexWriter (ISolrCore core,String name,String path,DirectoryFactory directoryFactory,boolean create,IndexSchema schema,SolrIndexConfig config,IndexDeletionPolicy delPolicy,Codec codec) throws IOException;        
}