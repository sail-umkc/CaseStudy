package comp.SegmentsInfoRequestHandlerPlugin;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

import annotation.Feature;
import annotation.Optional;

@Optional(Feature.SEGMENTS_INFO_REQUEST_HANDLER)
public class SegmentsInfoRequestHandlerPluginImp implements ISegmentsInfoRequestHandlerPluginImp
{
	private SegmentsInfoRequestHandlerPluginArch _arch;

    public SegmentsInfoRequestHandlerPluginImp (){
    }

	public void setArch(SegmentsInfoRequestHandlerPluginArch arch){
		_arch = arch;
	}
	public SegmentsInfoRequestHandlerPluginArch getArch(){
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
	private final String pluginName = "SegmentsInfoRequestHandler";
	
    //To be imported: Map,PluginInfo
    public boolean registerSegmentsInfoRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
      if (info.className.contains(pluginName)) {
        infoMap.put(info.name, info);
        return true;
      }
      
      return false;
    }
}