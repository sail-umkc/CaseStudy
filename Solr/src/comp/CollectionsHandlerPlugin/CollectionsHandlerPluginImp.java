package comp.CollectionsHandlerPlugin;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

import annotation.Feature;
import annotation.Optional;

@Optional(Feature.COLLECTIONS_HANDLER)
public class CollectionsHandlerPluginImp implements ICollectionsHandlerPluginImp
{
	private CollectionsHandlerPluginArch _arch;

    public CollectionsHandlerPluginImp (){
    }

	public void setArch(CollectionsHandlerPluginArch arch){
		_arch = arch;
	}
	public CollectionsHandlerPluginArch getArch(){
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
	private final String pluginName = "CollectionsHandler";
	
    //To be imported: Map,PluginInfo
    public boolean registerCollectionsHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
      if (info.className.contains(pluginName)) {
        infoMap.put(info.name, info);
        return true;
      }
      
      return false;
    }
}