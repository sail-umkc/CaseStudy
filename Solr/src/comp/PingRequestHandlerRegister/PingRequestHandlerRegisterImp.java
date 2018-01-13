package comp.PingRequestHandlerRegister;


import static edu.umkc.solr.core.PluginInfo.INVARIANTS;
import static org.apache.solr.common.cloud.ZkNodeProps.makeMap;
import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;
import edu.umkc.solr.handler.PingRequestHandler;

import java.util.List;

import org.apache.solr.common.util.NamedList;

import comp.ImplicitPlugins.ImplicitPluginsImp;

public class PingRequestHandlerRegisterImp implements IPingRequestHandlerRegisterImp
{
	private PingRequestHandlerRegisterArch _arch;

  public PingRequestHandlerRegisterImp (){
  }

	public void setArch(PingRequestHandlerRegisterArch arch){
		_arch = arch;
	}
	public PingRequestHandlerRegisterArch getArch(){
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
    final PluginInfo ping = ImplicitPluginsImp.getReqHandlerInfo("/admin/ping", PingRequestHandler.class, null);
    ping.initArgs.add(INVARIANTS, new NamedList<>(makeMap("echoParams", "all", "q", "solrpingquery")));
    implicits.add(ping);		
  }
}