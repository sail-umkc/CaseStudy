package comp.UpdateRequestHandlerRegister;


import static java.util.Collections.singletonMap;
import static org.apache.solr.common.cloud.ZkNodeProps.makeMap;
import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;
import edu.umkc.solr.handler.UpdateRequestHandler;

import java.util.List;

import comp.ImplicitPlugins.ImplicitPluginsImp;

public class UpdateRequestHandlerRegisterImp implements IUpdateRequestHandlerRegisterImp
{
	private UpdateRequestHandlerRegisterArch _arch;

  public UpdateRequestHandlerRegisterImp (){
  }

	public void setArch(UpdateRequestHandlerRegisterArch arch){
		_arch = arch;
	}
	public UpdateRequestHandlerRegisterArch getArch(){
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
    implicits.add(ImplicitPluginsImp.getReqHandlerInfo("/update", UpdateRequestHandler.class, null));
    implicits.add(ImplicitPluginsImp.getReqHandlerInfo(UpdateRequestHandler.JSON_PATH, UpdateRequestHandler.class, singletonMap("update.contentType", "application/json")));
    implicits.add(ImplicitPluginsImp.getReqHandlerInfo(UpdateRequestHandler.CSV_PATH, UpdateRequestHandler.class, singletonMap("update.contentType", "application/csv")));
    implicits.add(ImplicitPluginsImp.getReqHandlerInfo(UpdateRequestHandler.DOC_PATH, UpdateRequestHandler.class, makeMap("update.contentType", "application/json", "json.command", "false")));
  }
}