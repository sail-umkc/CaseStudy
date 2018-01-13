package comp.CoreContainer;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.CoresLocator;
import edu.umkc.solr.core.NodeConfig;

import edu.umkc.type.ICoreContainer;
import edu.umkc.type.ISolrResourceLoader;

import edu.umkc.type.tmpl.ICoreContainerInitiator;
import edu.umkc.type.tmpl.ISolrCoreFactory;
import edu.umkc.type.tmpl.ISolrCoresBuilder;
import edu.umkc.type.tmpl.ISolrXmlConfig;

import java.util.Properties;

public class CoreContainerArch extends AbstractMyxSimpleBrick implements ICoreContainerInitiator
{
    public static final IMyxName msg_ICoreContainerInitiator = MyxUtils.createName("edu.umkc.type.tmpl.ICoreContainerInitiator");
    public static final IMyxName msg_ISolrCoresBuilder = MyxUtils.createName("edu.umkc.type.tmpl.ISolrCoresBuilder");
    public static final IMyxName msg_ISolrCoreFactory = MyxUtils.createName("edu.umkc.type.tmpl.ISolrCoreFactory");
    public static final IMyxName msg_ISolrXmlConfig = MyxUtils.createName("edu.umkc.type.tmpl.ISolrXmlConfig");
    public static final IMyxName msg_ISolrResourceLoader = MyxUtils.createName("edu.umkc.type.ISolrResourceLoader");

    public ISolrCoresBuilder OUT_ISolrCoresBuilder;
    public ISolrCoreFactory OUT_ISolrCoreFactory;
    public ISolrXmlConfig OUT_ISolrXmlConfig;
    public ISolrResourceLoader OUT_ISolrResourceLoader;

	private ICoreContainerImp _imp;

    public CoreContainerArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ICoreContainerImp getImplementation(){
        try{
			return new CoreContainerImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_ISolrCoresBuilder = (ISolrCoresBuilder) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrCoresBuilder);
        if (OUT_ISolrCoresBuilder == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrCoresBuilder returned null");
			return;       
        }
        OUT_ISolrCoreFactory = (ISolrCoreFactory) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrCoreFactory);
        if (OUT_ISolrCoreFactory == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrCoreFactory returned null");
			return;       
        }
        OUT_ISolrXmlConfig = (ISolrXmlConfig) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrXmlConfig);
        if (OUT_ISolrXmlConfig == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrXmlConfig returned null");
			return;       
        }
        OUT_ISolrResourceLoader = (ISolrResourceLoader) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrResourceLoader);
        if (OUT_ISolrResourceLoader == null){
 			System.err.println("Error: Interface edu.umkc.type.ISolrResourceLoader returned null");
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
		if (arg0.equals(msg_ICoreContainerInitiator)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Properties,CoresLocator,NodeConfig,ICoreContainer,ISolrResourceLoader
    public ICoreContainer initCoreContainer (final ISolrResourceLoader loader)   {
		return _imp.initCoreContainer(loader);
    }    
    public ICoreContainer initCoreContainer (final NodeConfig config)   {
		return _imp.initCoreContainer(config);
    }    
    public ICoreContainer initCoreContainer (final NodeConfig config,final Properties properties)   {
		return _imp.initCoreContainer(config,properties);
    }    
    public ICoreContainer initCoreContainer (final NodeConfig config,final Properties properties,final CoresLocator locator)   {
		return _imp.initCoreContainer(config,properties,locator);
    }    
    public ICoreContainer initCoreContainer (final String solrHome)   {
		return _imp.initCoreContainer(solrHome);
    }    
}