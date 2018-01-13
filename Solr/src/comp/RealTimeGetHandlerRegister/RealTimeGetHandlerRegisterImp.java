package comp.RealTimeGetHandlerRegister;


import static org.apache.solr.common.cloud.ZkNodeProps.makeMap;
import static org.apache.solr.common.params.CommonParams.JSON;
import static org.apache.solr.common.params.CommonParams.WT;
import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;
import edu.umkc.solr.handler.RealTimeGetHandler;

import java.util.List;

import comp.ImplicitPlugins.ImplicitPluginsImp;

public class RealTimeGetHandlerRegisterImp implements IRealTimeGetHandlerRegisterImp
{
	private RealTimeGetHandlerRegisterArch _arch;

  public RealTimeGetHandlerRegisterImp (){
  }

	public void setArch(RealTimeGetHandlerRegisterArch arch){
		_arch = arch;
	}
	public RealTimeGetHandlerRegisterArch getArch(){
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
  public void register (final List<PluginInfo> implicits)
  {
    implicits.add(ImplicitPluginsImp.getReqHandlerInfo("/get", RealTimeGetHandler.class, makeMap("omitHeader", "true", WT, JSON, "indent", "true")));		
  }
}