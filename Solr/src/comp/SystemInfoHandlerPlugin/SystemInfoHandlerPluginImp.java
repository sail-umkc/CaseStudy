package comp.SystemInfoHandlerPlugin;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

import annotation.Feature;
import annotation.Optional;

@Optional(Feature.SYSTEM_INFO_HANDLER)
public class SystemInfoHandlerPluginImp implements ISystemInfoHandlerPluginImp
{
	private SystemInfoHandlerPluginArch _arch;

    public SystemInfoHandlerPluginImp (){
    }

	public void setArch(SystemInfoHandlerPluginArch arch){
		_arch = arch;
	}
	public SystemInfoHandlerPluginArch getArch(){
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
	
	private final String pluginName = "SystemInfoHandler";
  
    //To be imported: Map,PluginInfo
    public boolean registerSystemInfoHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
      if (info.className.contains(pluginName)) {
        infoMap.put(info.name, info);
        return true;
      }
      
      return false;
    }
}