package comp.SolrXmlPlugins;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;
import edu.umkc.solr.core.SolrConfig;

import edu.umkc.type.tmpl.IAdminHandlersPlugin;
import edu.umkc.type.tmpl.IBlobHandlerPlugin;
import edu.umkc.type.tmpl.ICollectionsHandlerPlugin;
import edu.umkc.type.tmpl.ICoreAdminHandlerPlugin;
import edu.umkc.type.tmpl.IDocumentAnalysisRequestHandlerPlugin;
import edu.umkc.type.tmpl.IDumpRequestHandlerPlugin;
import edu.umkc.type.tmpl.IFieldAnalysisRequestHandlerPlugin;
import edu.umkc.type.tmpl.IInfoHandlerPlugin;
import edu.umkc.type.tmpl.ILoggingHandlerPlugin;
import edu.umkc.type.tmpl.ILukeRequestHandlerPlugin;
import edu.umkc.type.tmpl.IMoreLikeThisHandlerPlugin;
import edu.umkc.type.tmpl.INotFoundRequestHandlerPlugin;
import edu.umkc.type.tmpl.IPingRequestHandlerPlugin;
import edu.umkc.type.tmpl.IPluginInfoHandlerPlugin;
import edu.umkc.type.tmpl.IPropertiesRequestHandlerPlugin;
import edu.umkc.type.tmpl.IRealTimeGetHandlerPlugin;
import edu.umkc.type.tmpl.IReloadCacheRequestHandlerPlugin;
import edu.umkc.type.tmpl.IReplicationHandlerPlugin;
import edu.umkc.type.tmpl.ISchemaHandlerPlugin;
import edu.umkc.type.tmpl.ISearchHandlerPlugin;
import edu.umkc.type.tmpl.ISegmentsInfoRequestHandlerPlugin;
import edu.umkc.type.tmpl.IShowFileRequestHandlerPlugin;
import edu.umkc.type.tmpl.ISolrConfigHandlerPlugin;
import edu.umkc.type.tmpl.ISolrInfoMBeanHandlerPlugin;
import edu.umkc.type.tmpl.ISolrXmlRequestHandlerPluginRegister;
import edu.umkc.type.tmpl.IStandardRequestHandlerPlugin;
import edu.umkc.type.tmpl.IStreamHandlerPlugin;
import edu.umkc.type.tmpl.ISystemInfoHandlerPlugin;
import edu.umkc.type.tmpl.IThreadDumpHandlerPlugin;
import edu.umkc.type.tmpl.IUpdateRequestHandlerPlugin;

import java.util.Map;

public class SolrXmlPluginsArch extends AbstractMyxSimpleBrick implements ISolrXmlRequestHandlerPluginRegister
{
    public static final IMyxName msg_ISolrXmlRequestHandlerPluginRegister = MyxUtils.createName("edu.umkc.type.tmpl.ISolrXmlRequestHandlerPluginRegister");
    public static final IMyxName msg_ISearchHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ISearchHandlerPlugin");
    public static final IMyxName msg_ILukeRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ILukeRequestHandlerPlugin");
    public static final IMyxName msg_ICoreAdminHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ICoreAdminHandlerPlugin");
    public static final IMyxName msg_IReloadCacheRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IReloadCacheRequestHandlerPlugin");
    public static final IMyxName msg_IDumpRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IDumpRequestHandlerPlugin");
    public static final IMyxName msg_ILoggingHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ILoggingHandlerPlugin");
    public static final IMyxName msg_IDocumentAnalysisRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IDocumentAnalysisRequestHandlerPlugin");
    public static final IMyxName msg_IAdminHandlersPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IAdminHandlersPlugin");
    public static final IMyxName msg_IBlobHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IBlobHandlerPlugin");
    public static final IMyxName msg_ICollectionsHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ICollectionsHandlerPlugin");
    public static final IMyxName msg_IFieldAnalysisRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IFieldAnalysisRequestHandlerPlugin");
    public static final IMyxName msg_IThreadDumpHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IThreadDumpHandlerPlugin");
    public static final IMyxName msg_ISystemInfoHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ISystemInfoHandlerPlugin");
    public static final IMyxName msg_IMoreLikeThisHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IMoreLikeThisHandlerPlugin");
    public static final IMyxName msg_INotFoundRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.INotFoundRequestHandlerPlugin");
    public static final IMyxName msg_IStreamHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IStreamHandlerPlugin");
    public static final IMyxName msg_ISolrConfigHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ISolrConfigHandlerPlugin");
    public static final IMyxName msg_ISolrInfoMBeanHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ISolrInfoMBeanHandlerPlugin");
    public static final IMyxName msg_IInfoHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IInfoHandlerPlugin");
    public static final IMyxName msg_IPingRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IPingRequestHandlerPlugin");
    public static final IMyxName msg_IPluginInfoHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IPluginInfoHandlerPlugin");
    public static final IMyxName msg_IPropertiesRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IPropertiesRequestHandlerPlugin");
    public static final IMyxName msg_IReplicationHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IReplicationHandlerPlugin");
    public static final IMyxName msg_IShowFileRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IShowFileRequestHandlerPlugin");
    public static final IMyxName msg_IRealTimeGetHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IRealTimeGetHandlerPlugin");
    public static final IMyxName msg_IStandardRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IStandardRequestHandlerPlugin");
    public static final IMyxName msg_ISchemaHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ISchemaHandlerPlugin");
    public static final IMyxName msg_ISegmentsInfoRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ISegmentsInfoRequestHandlerPlugin");
    public static final IMyxName msg_IUpdateRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IUpdateRequestHandlerPlugin");

    public ISearchHandlerPlugin OUT_ISearchHandlerPlugin;
    public ILukeRequestHandlerPlugin OUT_ILukeRequestHandlerPlugin;
    public ICoreAdminHandlerPlugin OUT_ICoreAdminHandlerPlugin;
    public IReloadCacheRequestHandlerPlugin OUT_IReloadCacheRequestHandlerPlugin;
    public IDumpRequestHandlerPlugin OUT_IDumpRequestHandlerPlugin;
    public ILoggingHandlerPlugin OUT_ILoggingHandlerPlugin;
    public IDocumentAnalysisRequestHandlerPlugin OUT_IDocumentAnalysisRequestHandlerPlugin;
    public IAdminHandlersPlugin OUT_IAdminHandlersPlugin;
    public IBlobHandlerPlugin OUT_IBlobHandlerPlugin;
    public ICollectionsHandlerPlugin OUT_ICollectionsHandlerPlugin;
    public IFieldAnalysisRequestHandlerPlugin OUT_IFieldAnalysisRequestHandlerPlugin;
    public IThreadDumpHandlerPlugin OUT_IThreadDumpHandlerPlugin;
    public ISystemInfoHandlerPlugin OUT_ISystemInfoHandlerPlugin;
    public IMoreLikeThisHandlerPlugin OUT_IMoreLikeThisHandlerPlugin;
    public INotFoundRequestHandlerPlugin OUT_INotFoundRequestHandlerPlugin;
    public IStreamHandlerPlugin OUT_IStreamHandlerPlugin;
    public ISolrConfigHandlerPlugin OUT_ISolrConfigHandlerPlugin;
    public ISolrInfoMBeanHandlerPlugin OUT_ISolrInfoMBeanHandlerPlugin;
    public IInfoHandlerPlugin OUT_IInfoHandlerPlugin;
    public IPingRequestHandlerPlugin OUT_IPingRequestHandlerPlugin;
    public IPluginInfoHandlerPlugin OUT_IPluginInfoHandlerPlugin;
    public IPropertiesRequestHandlerPlugin OUT_IPropertiesRequestHandlerPlugin;
    public IReplicationHandlerPlugin OUT_IReplicationHandlerPlugin;
    public IShowFileRequestHandlerPlugin OUT_IShowFileRequestHandlerPlugin;
    public IRealTimeGetHandlerPlugin OUT_IRealTimeGetHandlerPlugin;
    public IStandardRequestHandlerPlugin OUT_IStandardRequestHandlerPlugin;
    public ISchemaHandlerPlugin OUT_ISchemaHandlerPlugin;
    public ISegmentsInfoRequestHandlerPlugin OUT_ISegmentsInfoRequestHandlerPlugin;
    public IUpdateRequestHandlerPlugin OUT_IUpdateRequestHandlerPlugin;

	private ISolrXmlPluginsImp _imp;

    public SolrXmlPluginsArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrXmlPluginsImp getImplementation(){
        try{
			return new SolrXmlPluginsImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_ISearchHandlerPlugin = (ISearchHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_ISearchHandlerPlugin);
        if (OUT_ISearchHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISearchHandlerPlugin returned null");
			return;       
        }
        OUT_ILukeRequestHandlerPlugin = (ILukeRequestHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_ILukeRequestHandlerPlugin);
        if (OUT_ILukeRequestHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ILukeRequestHandlerPlugin returned null");
			return;       
        }
        OUT_ICoreAdminHandlerPlugin = (ICoreAdminHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_ICoreAdminHandlerPlugin);
        if (OUT_ICoreAdminHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ICoreAdminHandlerPlugin returned null");
			return;       
        }
        OUT_IReloadCacheRequestHandlerPlugin = (IReloadCacheRequestHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IReloadCacheRequestHandlerPlugin);
        if (OUT_IReloadCacheRequestHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IReloadCacheRequestHandlerPlugin returned null");
			return;       
        }
        OUT_IDumpRequestHandlerPlugin = (IDumpRequestHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IDumpRequestHandlerPlugin);
        if (OUT_IDumpRequestHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IDumpRequestHandlerPlugin returned null");
			return;       
        }
        OUT_ILoggingHandlerPlugin = (ILoggingHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_ILoggingHandlerPlugin);
        if (OUT_ILoggingHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ILoggingHandlerPlugin returned null");
			return;       
        }
        OUT_IDocumentAnalysisRequestHandlerPlugin = (IDocumentAnalysisRequestHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IDocumentAnalysisRequestHandlerPlugin);
        if (OUT_IDocumentAnalysisRequestHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IDocumentAnalysisRequestHandlerPlugin returned null");
			return;       
        }
        OUT_IAdminHandlersPlugin = (IAdminHandlersPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IAdminHandlersPlugin);
        if (OUT_IAdminHandlersPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IAdminHandlersPlugin returned null");
			return;       
        }
        OUT_IBlobHandlerPlugin = (IBlobHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IBlobHandlerPlugin);
        if (OUT_IBlobHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IBlobHandlerPlugin returned null");
			return;       
        }
        OUT_ICollectionsHandlerPlugin = (ICollectionsHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_ICollectionsHandlerPlugin);
        if (OUT_ICollectionsHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ICollectionsHandlerPlugin returned null");
			return;       
        }
        OUT_IFieldAnalysisRequestHandlerPlugin = (IFieldAnalysisRequestHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IFieldAnalysisRequestHandlerPlugin);
        if (OUT_IFieldAnalysisRequestHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IFieldAnalysisRequestHandlerPlugin returned null");
			return;       
        }
        OUT_IThreadDumpHandlerPlugin = (IThreadDumpHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IThreadDumpHandlerPlugin);
        if (OUT_IThreadDumpHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IThreadDumpHandlerPlugin returned null");
			return;       
        }
        OUT_ISystemInfoHandlerPlugin = (ISystemInfoHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_ISystemInfoHandlerPlugin);
        if (OUT_ISystemInfoHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISystemInfoHandlerPlugin returned null");
			return;       
        }
        OUT_IMoreLikeThisHandlerPlugin = (IMoreLikeThisHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IMoreLikeThisHandlerPlugin);
        if (OUT_IMoreLikeThisHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IMoreLikeThisHandlerPlugin returned null");
			return;       
        }
        OUT_INotFoundRequestHandlerPlugin = (INotFoundRequestHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_INotFoundRequestHandlerPlugin);
        if (OUT_INotFoundRequestHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.INotFoundRequestHandlerPlugin returned null");
			return;       
        }
        OUT_IStreamHandlerPlugin = (IStreamHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IStreamHandlerPlugin);
        if (OUT_IStreamHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IStreamHandlerPlugin returned null");
			return;       
        }
        OUT_ISolrConfigHandlerPlugin = (ISolrConfigHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrConfigHandlerPlugin);
        if (OUT_ISolrConfigHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrConfigHandlerPlugin returned null");
			return;       
        }
        OUT_ISolrInfoMBeanHandlerPlugin = (ISolrInfoMBeanHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrInfoMBeanHandlerPlugin);
        if (OUT_ISolrInfoMBeanHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrInfoMBeanHandlerPlugin returned null");
			return;       
        }
        OUT_IInfoHandlerPlugin = (IInfoHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IInfoHandlerPlugin);
        if (OUT_IInfoHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IInfoHandlerPlugin returned null");
			return;       
        }
        OUT_IPingRequestHandlerPlugin = (IPingRequestHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IPingRequestHandlerPlugin);
        if (OUT_IPingRequestHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IPingRequestHandlerPlugin returned null");
			return;       
        }
        OUT_IPluginInfoHandlerPlugin = (IPluginInfoHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IPluginInfoHandlerPlugin);
        if (OUT_IPluginInfoHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IPluginInfoHandlerPlugin returned null");
			return;       
        }
        OUT_IPropertiesRequestHandlerPlugin = (IPropertiesRequestHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IPropertiesRequestHandlerPlugin);
        if (OUT_IPropertiesRequestHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IPropertiesRequestHandlerPlugin returned null");
			return;       
        }
        OUT_IReplicationHandlerPlugin = (IReplicationHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IReplicationHandlerPlugin);
        if (OUT_IReplicationHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IReplicationHandlerPlugin returned null");
			return;       
        }
        OUT_IShowFileRequestHandlerPlugin = (IShowFileRequestHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IShowFileRequestHandlerPlugin);
        if (OUT_IShowFileRequestHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IShowFileRequestHandlerPlugin returned null");
			return;       
        }
        OUT_IRealTimeGetHandlerPlugin = (IRealTimeGetHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IRealTimeGetHandlerPlugin);
        if (OUT_IRealTimeGetHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IRealTimeGetHandlerPlugin returned null");
			return;       
        }
        OUT_IStandardRequestHandlerPlugin = (IStandardRequestHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IStandardRequestHandlerPlugin);
        if (OUT_IStandardRequestHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IStandardRequestHandlerPlugin returned null");
			return;       
        }
        OUT_ISchemaHandlerPlugin = (ISchemaHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_ISchemaHandlerPlugin);
        if (OUT_ISchemaHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISchemaHandlerPlugin returned null");
			return;       
        }
        OUT_ISegmentsInfoRequestHandlerPlugin = (ISegmentsInfoRequestHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_ISegmentsInfoRequestHandlerPlugin);
        if (OUT_ISegmentsInfoRequestHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISegmentsInfoRequestHandlerPlugin returned null");
			return;       
        }
        OUT_IUpdateRequestHandlerPlugin = (IUpdateRequestHandlerPlugin) MyxUtils.getFirstRequiredServiceObject(this,msg_IUpdateRequestHandlerPlugin);
        if (OUT_IUpdateRequestHandlerPlugin == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IUpdateRequestHandlerPlugin returned null");
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
		if (arg0.equals(msg_ISolrXmlRequestHandlerPluginRegister)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo,SolrConfig
    public void registerRequestHandlerPluginsFromSolrXml (final SolrConfig config,final Map<String, PluginInfo> infoMap)   {
		_imp.registerRequestHandlerPluginsFromSolrXml(config,infoMap);
    }    
}