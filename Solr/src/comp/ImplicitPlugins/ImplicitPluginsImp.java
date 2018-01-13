package comp.ImplicitPlugins;


import static edu.umkc.solr.core.PluginInfo.DEFAULTS;
import static java.util.Collections.singletonMap;
import static org.apache.solr.common.cloud.ZkNodeProps.makeMap;
import static org.apache.solr.common.params.CommonParams.NAME;
import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;
import edu.umkc.solr.request.SolrRequestHandler;
import edu.umkc.type.ISolrCore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.util.NamedList;

public class ImplicitPluginsImp implements IImplicitPluginsImp
{
	private ImplicitPluginsArch _arch;

  public ImplicitPluginsImp (){
  }

	public void setArch(ImplicitPluginsArch arch){
		_arch = arch;
	}

	public ImplicitPluginsArch getArch(){
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
  
  //To be imported: List,PluginInfo
  public List<PluginInfo> getHandlers (final ISolrCore solrCore)   {
    List<PluginInfo> implicits = new ArrayList<>();

    //update handle implicits
    _arch.OUT_IImplicitUpdateRequestHandlerRegister.register(implicits);

    //solrconfighandler
    _arch.OUT_IImplicitSolrConfigHandlerRegister.register(implicits);
    //schemahandler
    _arch.OUT_IImplicitSchemaHandlerRegister.register(implicits);
    //register replicationhandler always for SolrCloud
    _arch.OUT_IImplicitReplicationHandlerRegister.register(implicits);

    _arch.OUT_IImplicitRealTimeGetHandlerRegister.register(implicits);
    //register adminHandlers
    _arch.OUT_IImplicitLukeRequestHandlerRegister.register(implicits);
    _arch.OUT_IImplicitSystemInfoHandlerRegister.register(implicits);
    _arch.OUT_IImplicitSolrInfoMBeanHandlerRegister.register(implicits);
    _arch.OUT_IImplicitPluginInfoHandlerRegister.register(implicits);
    _arch.OUT_IImplicitThreadDumpHandlerRegister.register(implicits);
    _arch.OUT_IImplicitPropertiesRequestHandlerRegister.register(implicits);
    _arch.OUT_IImplicitLoggingHandlerRegister.register(implicits);
    _arch.OUT_IImplicitShowFileRequestHandlerRegister.register(implicits);
    _arch.OUT_IImplicitPingRequestHandlerRegister.register(implicits);
    _arch.OUT_IImplicitSegmentsInfoRequestHandlerRegister.register(implicits);
    
    return Collections.unmodifiableList(implicits);
  }

  public static PluginInfo getReqHandlerInfo(String name, Class clz, Map defaults){
    if(defaults == null) defaults= Collections.emptyMap();
    Map m = makeMap(NAME, name, "class", clz.getName());
    return new PluginInfo(SolrRequestHandler.TYPE, m, new NamedList<>(singletonMap(DEFAULTS, new NamedList(defaults))),null);
  }
}