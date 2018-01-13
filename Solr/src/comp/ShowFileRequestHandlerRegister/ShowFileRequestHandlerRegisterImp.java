package comp.ShowFileRequestHandlerRegister;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;
import edu.umkc.solr.handler.admin.ShowFileRequestHandler;

import java.util.List;


import comp.ImplicitPlugins.ImplicitPluginsImp;

public class ShowFileRequestHandlerRegisterImp implements IShowFileRequestHandlerRegisterImp
{
	private ShowFileRequestHandlerRegisterArch _arch;

  public ShowFileRequestHandlerRegisterImp (){
  }

	public void setArch(ShowFileRequestHandlerRegisterArch arch){
		_arch = arch;
	}
	
	public ShowFileRequestHandlerRegisterArch getArch(){
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
    implicits.add(ImplicitPluginsImp.getReqHandlerInfo("/admin/file", ShowFileRequestHandler.class, null));		
  }
}