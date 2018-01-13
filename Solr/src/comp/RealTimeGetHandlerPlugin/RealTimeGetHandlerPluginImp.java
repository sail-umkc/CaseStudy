package comp.RealTimeGetHandlerPlugin;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

import annotation.Feature;
import annotation.Optional;

@Optional(Feature.REAL_TIME_GET_HANDLER)
public class RealTimeGetHandlerPluginImp implements IRealTimeGetHandlerPluginImp
{
	private RealTimeGetHandlerPluginArch _arch;

    public RealTimeGetHandlerPluginImp (){
    }

	public void setArch(RealTimeGetHandlerPluginArch arch){
		_arch = arch;
	}
	public RealTimeGetHandlerPluginArch getArch(){
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
	private final String pluginName = "RealTimeGetHandler";
	
    //To be imported: Map,PluginInfo
    public boolean registerRealTimeGetHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
      if (info.className.contains(pluginName)) {
        infoMap.put(info.name, info);
        return true;
      }
      
      return false;
    }
}