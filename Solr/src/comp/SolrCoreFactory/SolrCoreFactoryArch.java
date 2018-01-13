package comp.SolrCoreFactory;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.ConfigSet;
import edu.umkc.solr.core.CoreDescriptor;
import edu.umkc.solr.core.IndexDeletionPolicyWrapper;
import edu.umkc.solr.core.SolrConfig;

import edu.umkc.solr.schema.IndexSchema;

import edu.umkc.solr.update.UpdateHandler;

import edu.umkc.type.ISolrCore;

import edu.umkc.type.tmpl.IDefaultResponseWriters;
import edu.umkc.type.tmpl.IDefaultSearchComponent;
import edu.umkc.type.tmpl.IRequestHandlersFactory;
import edu.umkc.type.tmpl.ISolrCoreFactory;
import edu.umkc.type.tmpl.ISolrCoreStateFactory;
import edu.umkc.type.tmpl.ISolrIndexSearcherFactory;
import edu.umkc.type.tmpl.ISolrIndexWriterFactory;

public class SolrCoreFactoryArch extends AbstractMyxSimpleBrick implements ISolrCoreFactory
{
    public static final IMyxName msg_ISolrCoreFactory = MyxUtils.createName("edu.umkc.type.tmpl.ISolrCoreFactory");
    public static final IMyxName msg_IRequestHandlersFactory = MyxUtils.createName("edu.umkc.type.tmpl.IRequestHandlersFactory");
    public static final IMyxName msg_IDefaultSearchComponent = MyxUtils.createName("edu.umkc.type.tmpl.IDefaultSearchComponent");
    public static final IMyxName msg_IDefaultResponseWriters = MyxUtils.createName("edu.umkc.type.tmpl.IDefaultResponseWriters");
    public static final IMyxName msg_ISolrIndexSearcherFactory = MyxUtils.createName("edu.umkc.type.tmpl.ISolrIndexSearcherFactory");
    public static final IMyxName msg_ISolrCoreStateFactory = MyxUtils.createName("edu.umkc.type.tmpl.ISolrCoreStateFactory");
    public static final IMyxName msg_ISolrIndexWriterFactory = MyxUtils.createName("edu.umkc.type.tmpl.ISolrIndexWriterFactory");

    public IRequestHandlersFactory OUT_IRequestHandlersFactory;
    public IDefaultSearchComponent OUT_IDefaultSearchComponent;
    public IDefaultResponseWriters OUT_IDefaultResponseWriters;
    public ISolrIndexSearcherFactory OUT_ISolrIndexSearcherFactory;
    public ISolrCoreStateFactory OUT_ISolrCoreStateFactory;
    public ISolrIndexWriterFactory OUT_ISolrIndexWriterFactory;

	private ISolrCoreFactoryImp _imp;

    public SolrCoreFactoryArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrCoreFactoryImp getImplementation(){
        try{
			return new SolrCoreFactoryImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_IRequestHandlersFactory = (IRequestHandlersFactory) MyxUtils.getFirstRequiredServiceObject(this,msg_IRequestHandlersFactory);
        if (OUT_IRequestHandlersFactory == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IRequestHandlersFactory returned null");
			return;       
        }
        OUT_IDefaultSearchComponent = (IDefaultSearchComponent) MyxUtils.getFirstRequiredServiceObject(this,msg_IDefaultSearchComponent);
        if (OUT_IDefaultSearchComponent == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IDefaultSearchComponent returned null");
			return;       
        }
        OUT_IDefaultResponseWriters = (IDefaultResponseWriters) MyxUtils.getFirstRequiredServiceObject(this,msg_IDefaultResponseWriters);
        if (OUT_IDefaultResponseWriters == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IDefaultResponseWriters returned null");
			return;       
        }
        OUT_ISolrIndexSearcherFactory = (ISolrIndexSearcherFactory) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrIndexSearcherFactory);
        if (OUT_ISolrIndexSearcherFactory == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrIndexSearcherFactory returned null");
			return;       
        }
        OUT_ISolrCoreStateFactory = (ISolrCoreStateFactory) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrCoreStateFactory);
        if (OUT_ISolrCoreStateFactory == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrCoreStateFactory returned null");
			return;       
        }
        OUT_ISolrIndexWriterFactory = (ISolrIndexWriterFactory) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrIndexWriterFactory);
        if (OUT_ISolrIndexWriterFactory == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrIndexWriterFactory returned null");
			return;       
        }
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		if (arg0.equals(msg_ISolrCoreFactory)){
			return this;
		}        
		return null;
	}
  
    //To be imported: ConfigSet,CoreDescriptor,IndexDeletionPolicyWrapper,SolrConfig,IndexSchema,UpdateHandler,ISolrCore
    public ISolrCore createSolrCore (final CoreDescriptor cd,final ConfigSet coreConfig)   {
		return _imp.createSolrCore(cd,coreConfig);
    }    
    public ISolrCore createSolrCore (final String name,final String dataDir,final SolrConfig config,final IndexSchema schema,final CoreDescriptor coreDescriptor,final UpdateHandler updateHandler,final IndexDeletionPolicyWrapper delPolicy,final ISolrCore prev)   {
		return _imp.createSolrCore(name,dataDir,config,schema,coreDescriptor,updateHandler,delPolicy,prev);
    }    
}