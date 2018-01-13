package comp.NotFoundRequestHandlerPlugin;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

import annotation.Feature;
import annotation.Optional;

@Optional(Feature.NOT_FOUND_REQUEST_HANDLER)
public class NotFoundRequestHandlerPluginImp implements INotFoundRequestHandlerPluginImp
{
	private NotFoundRequestHandlerPluginArch _arch;

    public NotFoundRequestHandlerPluginImp (){
    }

	public void setArch(NotFoundRequestHandlerPluginArch arch){
		_arch = arch;
	}
	public NotFoundRequestHandlerPluginArch getArch(){
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
	private final String pluginName = "NotFoundRequestHandler";
	
    //To be imported: Map,PluginInfo
    public boolean registerNotFoundRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
      if (info.className.contains(pluginName)) {
        infoMap.put(info.name, info);
        return true;
      }
      
      return false;
    }
}