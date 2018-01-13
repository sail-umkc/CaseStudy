package comp.PropertiesRequestHandlerPlugin;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

import annotation.Feature;
import annotation.Optional;

@Optional(Feature.PROPERTIES_REQUEST_HANDLER)
public class PropertiesRequestHandlerPluginImp implements IPropertiesRequestHandlerPluginImp
{
	private PropertiesRequestHandlerPluginArch _arch;

    public PropertiesRequestHandlerPluginImp (){
    }

	public void setArch(PropertiesRequestHandlerPluginArch arch){
		_arch = arch;
	}
	public PropertiesRequestHandlerPluginArch getArch(){
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
	private final String pluginName = "PropertiesRequestHandler";
	
    //To be imported: Map,PluginInfo
    public boolean registerPropertiesRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
      if (info.className.contains(pluginName)) {
        infoMap.put(info.name, info);
        return true;
      }
      
      return false;
    }
}