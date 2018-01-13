package comp.SolrXmlPlugins;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;
import edu.umkc.solr.core.SolrConfig;
import edu.umkc.solr.request.SolrRequestHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import annotation.Feature;
import annotation.Optional;

public class SolrXmlPluginsImp implements ISolrXmlPluginsImp
{
	private SolrXmlPluginsArch _arch;
	private static final List<String> solrRequestHandlers;
	  
  static {
    final List<String> internalHandlers = new ArrayList<String>(28);
    
    internalHandlers.add("AdminHandlers");
    internalHandlers.add("BlobHandler");
    internalHandlers.add("CollectionsHandler");
    internalHandlers.add("CoreAdminHandler");
    internalHandlers.add("DocumentAnalysisRequestHandler");
    internalHandlers.add("DumpRequestHandler");
    internalHandlers.add("FieldAnalysisRequestHandler");
    internalHandlers.add("FileFloatSource.ReloadCacheRequestHandler");
    internalHandlers.add("InfoHandler");
    internalHandlers.add("LoggingHandler");
    internalHandlers.add("LukeRequestHandler");
    internalHandlers.add("MoreLikeThisHandler");
    internalHandlers.add("NotFoundRequestHandler");
    internalHandlers.add("PingRequestHandler");
    internalHandlers.add("PluginInfoHandler");
    internalHandlers.add("PropertiesRequestHandler");
    internalHandlers.add("RealTimeGetHandler");
    internalHandlers.add("ReplicationHandler");
    internalHandlers.add("SchemaHandler");
    internalHandlers.add("SearchHandler");
    internalHandlers.add("SegmentsInfoRequestHandler");
    internalHandlers.add("ShowFileRequestHandler");
    internalHandlers.add("SolrConfigHandler");
    internalHandlers.add("SolrInfoMBeanHandler");
    internalHandlers.add("StandardRequestHandler");
    internalHandlers.add("StreamHandler");
    internalHandlers.add("SystemInfoHandler");
    internalHandlers.add("ThreadDumpHandler");
    internalHandlers.add("UpdateRequestHandler");
    
    solrRequestHandlers = Collections.unmodifiableList(internalHandlers);
  }
	  
  public SolrXmlPluginsImp (){
  }

	public void setArch(SolrXmlPluginsArch arch){
		_arch = arch;
	}
	public SolrXmlPluginsArch getArch(){
		return _arch;
	}

	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init(){
	  Bootstrapper.incrInitCount();
	}
	
	public void begin(){
	  Bootstrapper.incrBeginCount();
	}
	
	public void end(){
		//TODO Auto-generated method stub
	}
	
	public void destroy(){
		//TODO Auto-generated method stub
	}

	/*
  	  Implementation primitives required by the architecture
	*/
  
  public void registerRequestHandlerPluginsFromSolrXml (final SolrConfig config,final Map<String, PluginInfo> infoMap)   {
    for (PluginInfo info : config.getPluginInfos(SolrRequestHandler.class.getName())) {
      
      /*@Optional(Feature.ADMIN_HANDLERS)*/
      if (_arch.OUT_IAdminHandlersPlugin.registerAdminHandlersPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.BLOB_HANDLER)*/
      if (_arch.OUT_IBlobHandlerPlugin.registerBlobHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.COLLECTIONS_HANDLER)*/
      if (_arch.OUT_ICollectionsHandlerPlugin.registerCollectionsHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.CORE_ADMIN_HANDLER)*/
      if (_arch.OUT_ICoreAdminHandlerPlugin.registerCoreAdminHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.DOCUMENT_ANALYSIS_REQUEST_HANDLER)*/
      if (_arch.OUT_IDocumentAnalysisRequestHandlerPlugin.registerDocumentAnalysisRequestHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.DUMP_REQUEST_HANDLER)*/
      if (_arch.OUT_IDumpRequestHandlerPlugin.registerDumpRequestHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.FIELD_ANALYSIS_REQUEST_HANDLER)*/
      if (_arch.OUT_IFieldAnalysisRequestHandlerPlugin.registerFieldAnalysisRequestHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.INFO_HANDLER)*/
      if (_arch.OUT_IInfoHandlerPlugin.registerInfoHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.LOGGING_HANDLER)*/
      if (_arch.OUT_ILoggingHandlerPlugin.registerLoggingHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.LUKE_REQUEST_HANDLER)*/
      if (_arch.OUT_ILukeRequestHandlerPlugin.registerLukeRequestHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.MORE_LIKE_THIS_HANDLER)*/
      if (_arch.OUT_IMoreLikeThisHandlerPlugin.registerMoreLikeThisHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.NOT_FOUND_REQUEST_HANDLER)*/
      if (_arch.OUT_INotFoundRequestHandlerPlugin.registerNotFoundRequestHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.PING_REQUEST_HANDLER)*/
      if (_arch.OUT_IPingRequestHandlerPlugin.registerPingRequestHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.PLUGIN_INFO_HANDLER)*/
      if (_arch.OUT_IPluginInfoHandlerPlugin.registerPluginInfoHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.PROPERTIES_REQUEST_HANDLER)*/
      if (_arch.OUT_IPropertiesRequestHandlerPlugin.registerPropertiesRequestHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.REAL_TIME_GET_HANDLER)*/
      if (_arch.OUT_IRealTimeGetHandlerPlugin.registerRealTimeGetHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.RELOAD_CACHE_REQUEST_HANDLER)*/
      if (_arch.OUT_IReloadCacheRequestHandlerPlugin.registerReloadCacheRequestHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.REPLICATION_HANDLER)*/
      if (_arch.OUT_IReplicationHandlerPlugin.registerReplicationHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.SCHEMA_HANDLER)*/
      if (_arch.OUT_ISchemaHandlerPlugin.registerSchemaHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.SEARCH_HANDLER)*/
      if (_arch.OUT_ISearchHandlerPlugin.registerSearchHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.SEGMENTS_INFO_REQUEST_HANDLER)*/
      if (_arch.OUT_ISegmentsInfoRequestHandlerPlugin.registerSegmentsInfoRequestHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.SHOW_FILE_REQUEST_HANDLER)*/
      if (_arch.OUT_IShowFileRequestHandlerPlugin.registerShowFileRequestHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.SOLR_CONFIG_HANDLER)*/
      if (_arch.OUT_ISolrConfigHandlerPlugin.registerSolrConfigHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.SOLR_INFO_MBEAN_HANDLER)*/
      if (_arch.OUT_ISolrInfoMBeanHandlerPlugin.registerSolrInfoMBeanHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.STANDARD_REQUEST_HANDLER)*/
      if (_arch.OUT_IStandardRequestHandlerPlugin.registerStandardRequestHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.STREAM_HANDLER)*/
      if (_arch.OUT_IStreamHandlerPlugin.registerStreamHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.SYSTEM_INFO_HANDLER)*/
      if (_arch.OUT_ISystemInfoHandlerPlugin.registerSystemInfoHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.THREAD_DUMP_HANDLER)*/
      if (_arch.OUT_IThreadDumpHandlerPlugin.registerThreadDumpHandlerPlugin(info, infoMap)) continue;
      
      /*@Optional(Feature.UPDATE_REQUEST_HANDLER)*/
      if (_arch.OUT_IUpdateRequestHandlerPlugin.registerUpdateRequestHandlerPlugin(info, infoMap)) continue;
      
      if (isRegistrable(info.className)) infoMap.put(info.name, info);		
    }
  }
  
  private boolean isRegistrable(final String className) {
    for (final String solrRequestHandler : solrRequestHandlers) {
      if (className.contains(solrRequestHandler)) {
        return false;
      }
    }
    
    return true;
  }
}