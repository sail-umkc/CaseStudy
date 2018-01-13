package comp.ImplicitPlugins;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.ISolrCore;

import edu.umkc.type.tmpl.IImplicitLoggingHandlerRegister;
import edu.umkc.type.tmpl.IImplicitLukeRequestHandlerRegister;
import edu.umkc.type.tmpl.IImplicitPingRequestHandlerRegister;
import edu.umkc.type.tmpl.IImplicitPluginInfoHandlerRegister;
import edu.umkc.type.tmpl.IImplicitPlugins;
import edu.umkc.type.tmpl.IImplicitPropertiesRequestHandlerRegister;
import edu.umkc.type.tmpl.IImplicitRealTimeGetHandlerRegister;
import edu.umkc.type.tmpl.IImplicitReplicationHandlerRegister;
import edu.umkc.type.tmpl.IImplicitSchemaHandlerRegister;
import edu.umkc.type.tmpl.IImplicitSegmentsInfoRequestHandlerRegister;
import edu.umkc.type.tmpl.IImplicitShowFileRequestHandlerRegister;
import edu.umkc.type.tmpl.IImplicitSolrConfigHandlerRegister;
import edu.umkc.type.tmpl.IImplicitSolrInfoMBeanHandlerRegister;
import edu.umkc.type.tmpl.IImplicitSystemInfoHandlerRegister;
import edu.umkc.type.tmpl.IImplicitThreadDumpHandlerRegister;
import edu.umkc.type.tmpl.IImplicitUpdateRequestHandlerRegister;

import java.util.List;

public class ImplicitPluginsArch extends AbstractMyxSimpleBrick implements IImplicitPlugins
{
    public static final IMyxName msg_IImplicitPlugins = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitPlugins");
    public static final IMyxName msg_IImplicitUpdateRequestHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitUpdateRequestHandlerRegister");
    public static final IMyxName msg_IImplicitLukeRequestHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitLukeRequestHandlerRegister");
    public static final IMyxName msg_IImplicitPropertiesRequestHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitPropertiesRequestHandlerRegister");
    public static final IMyxName msg_IImplicitSolrConfigHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitSolrConfigHandlerRegister");
    public static final IMyxName msg_IImplicitRealTimeGetHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitRealTimeGetHandlerRegister");
    public static final IMyxName msg_IImplicitSystemInfoHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitSystemInfoHandlerRegister");
    public static final IMyxName msg_IImplicitThreadDumpHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitThreadDumpHandlerRegister");
    public static final IMyxName msg_IImplicitSegmentsInfoRequestHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitSegmentsInfoRequestHandlerRegister");
    public static final IMyxName msg_IImplicitReplicationHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitReplicationHandlerRegister");
    public static final IMyxName msg_IImplicitPluginInfoHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitPluginInfoHandlerRegister");
    public static final IMyxName msg_IImplicitLoggingHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitLoggingHandlerRegister");
    public static final IMyxName msg_IImplicitPingRequestHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitPingRequestHandlerRegister");
    public static final IMyxName msg_IImplicitShowFileRequestHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitShowFileRequestHandlerRegister");
    public static final IMyxName msg_IImplicitSchemaHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitSchemaHandlerRegister");
    public static final IMyxName msg_IImplicitSolrInfoMBeanHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitSolrInfoMBeanHandlerRegister");

    public IImplicitUpdateRequestHandlerRegister OUT_IImplicitUpdateRequestHandlerRegister;
    public IImplicitLukeRequestHandlerRegister OUT_IImplicitLukeRequestHandlerRegister;
    public IImplicitPropertiesRequestHandlerRegister OUT_IImplicitPropertiesRequestHandlerRegister;
    public IImplicitSolrConfigHandlerRegister OUT_IImplicitSolrConfigHandlerRegister;
    public IImplicitRealTimeGetHandlerRegister OUT_IImplicitRealTimeGetHandlerRegister;
    public IImplicitSystemInfoHandlerRegister OUT_IImplicitSystemInfoHandlerRegister;
    public IImplicitThreadDumpHandlerRegister OUT_IImplicitThreadDumpHandlerRegister;
    public IImplicitSegmentsInfoRequestHandlerRegister OUT_IImplicitSegmentsInfoRequestHandlerRegister;
    public IImplicitReplicationHandlerRegister OUT_IImplicitReplicationHandlerRegister;
    public IImplicitPluginInfoHandlerRegister OUT_IImplicitPluginInfoHandlerRegister;
    public IImplicitLoggingHandlerRegister OUT_IImplicitLoggingHandlerRegister;
    public IImplicitPingRequestHandlerRegister OUT_IImplicitPingRequestHandlerRegister;
    public IImplicitShowFileRequestHandlerRegister OUT_IImplicitShowFileRequestHandlerRegister;
    public IImplicitSchemaHandlerRegister OUT_IImplicitSchemaHandlerRegister;
    public IImplicitSolrInfoMBeanHandlerRegister OUT_IImplicitSolrInfoMBeanHandlerRegister;

	private IImplicitPluginsImp _imp;

    public ImplicitPluginsArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IImplicitPluginsImp getImplementation(){
        try{
			return new ImplicitPluginsImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_IImplicitUpdateRequestHandlerRegister = (IImplicitUpdateRequestHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitUpdateRequestHandlerRegister);
        if (OUT_IImplicitUpdateRequestHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitUpdateRequestHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitLukeRequestHandlerRegister = (IImplicitLukeRequestHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitLukeRequestHandlerRegister);
        if (OUT_IImplicitLukeRequestHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitLukeRequestHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitPropertiesRequestHandlerRegister = (IImplicitPropertiesRequestHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitPropertiesRequestHandlerRegister);
        if (OUT_IImplicitPropertiesRequestHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitPropertiesRequestHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitSolrConfigHandlerRegister = (IImplicitSolrConfigHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitSolrConfigHandlerRegister);
        if (OUT_IImplicitSolrConfigHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitSolrConfigHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitRealTimeGetHandlerRegister = (IImplicitRealTimeGetHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitRealTimeGetHandlerRegister);
        if (OUT_IImplicitRealTimeGetHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitRealTimeGetHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitSystemInfoHandlerRegister = (IImplicitSystemInfoHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitSystemInfoHandlerRegister);
        if (OUT_IImplicitSystemInfoHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitSystemInfoHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitThreadDumpHandlerRegister = (IImplicitThreadDumpHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitThreadDumpHandlerRegister);
        if (OUT_IImplicitThreadDumpHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitThreadDumpHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitSegmentsInfoRequestHandlerRegister = (IImplicitSegmentsInfoRequestHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitSegmentsInfoRequestHandlerRegister);
        if (OUT_IImplicitSegmentsInfoRequestHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitSegmentsInfoRequestHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitReplicationHandlerRegister = (IImplicitReplicationHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitReplicationHandlerRegister);
        if (OUT_IImplicitReplicationHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitReplicationHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitPluginInfoHandlerRegister = (IImplicitPluginInfoHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitPluginInfoHandlerRegister);
        if (OUT_IImplicitPluginInfoHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitPluginInfoHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitLoggingHandlerRegister = (IImplicitLoggingHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitLoggingHandlerRegister);
        if (OUT_IImplicitLoggingHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitLoggingHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitPingRequestHandlerRegister = (IImplicitPingRequestHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitPingRequestHandlerRegister);
        if (OUT_IImplicitPingRequestHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitPingRequestHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitShowFileRequestHandlerRegister = (IImplicitShowFileRequestHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitShowFileRequestHandlerRegister);
        if (OUT_IImplicitShowFileRequestHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitShowFileRequestHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitSchemaHandlerRegister = (IImplicitSchemaHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitSchemaHandlerRegister);
        if (OUT_IImplicitSchemaHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitSchemaHandlerRegister returned null");
			return;       
        }
        OUT_IImplicitSolrInfoMBeanHandlerRegister = (IImplicitSolrInfoMBeanHandlerRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitSolrInfoMBeanHandlerRegister);
        if (OUT_IImplicitSolrInfoMBeanHandlerRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitSolrInfoMBeanHandlerRegister returned null");
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
		if (arg0.equals(msg_IImplicitPlugins)){
			return this;
		}        
		return null;
	}
  
    //To be imported: List,PluginInfo,ISolrCore
    public List<PluginInfo> getHandlers (final ISolrCore solrCore)   {
		return _imp.getHandlers(solrCore);
    }    
}