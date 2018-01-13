package comp.PingRequestHandlerPlugin;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

import annotation.Feature;
import annotation.Optional;

@Optional(Feature.PING_REQUEST_HANDLER)
public class PingRequestHandlerPluginImp implements IPingRequestHandlerPluginImp
{
	private PingRequestHandlerPluginArch _arch;

    public PingRequestHandlerPluginImp (){
    }

	public void setArch(PingRequestHandlerPluginArch arch){
		_arch = arch;
	}
	public PingRequestHandlerPluginArch getArch(){
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
	private final String pluginName = "PingRequestHandler";
	
    //To be imported: Map,PluginInfo
    public boolean registerPingRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
      if (info.className.contains(pluginName)) {
        infoMap.put(info.name, info);
        return true;
      }
      
      return false;
    }
}