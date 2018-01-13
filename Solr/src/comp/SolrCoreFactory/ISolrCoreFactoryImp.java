package comp.SolrCoreFactory;


import comp.SolrCoreFactory.SolrCoreFactoryArch;

import edu.umkc.solr.core.ConfigSet;
import edu.umkc.solr.core.CoreDescriptor;
import edu.umkc.solr.core.IndexDeletionPolicyWrapper;
import edu.umkc.solr.core.SolrConfig;

import edu.umkc.solr.schema.IndexSchema;

import edu.umkc.solr.update.UpdateHandler;

import edu.umkc.type.ISolrCore;

public interface ISolrCoreFactoryImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SolrCoreFactoryArch arch);
	public SolrCoreFactoryArch getArch();
	
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
  
    //To be imported: ConfigSet,CoreDescriptor,IndexDeletionPolicyWrapper,SolrConfig,IndexSchema,UpdateHandler,ISolrCore
    public ISolrCore createSolrCore (final CoreDescriptor cd,final ConfigSet coreConfig)  ;        
    public ISolrCore createSolrCore (final String name,final String dataDir,final SolrConfig config,final IndexSchema schema,final CoreDescriptor coreDescriptor,final UpdateHandler updateHandler,final IndexDeletionPolicyWrapper delPolicy,final ISolrCore prev)  ;        
}