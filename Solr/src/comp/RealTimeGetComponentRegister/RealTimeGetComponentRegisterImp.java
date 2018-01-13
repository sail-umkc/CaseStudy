package comp.RealTimeGetComponentRegister;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.handler.component.RealTimeGetComponent;
import edu.umkc.solr.handler.component.SearchComponent;

import java.util.Map;

public class RealTimeGetComponentRegisterImp implements IRealTimeGetComponentRegisterImp
{
	private RealTimeGetComponentRegisterArch _arch;

  public RealTimeGetComponentRegisterImp (){
  }

	public void setArch(RealTimeGetComponentRegisterArch arch){
		_arch = arch;
	}
	public RealTimeGetComponentRegisterArch getArch(){
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
  
  //To be imported: Map,SearchComponent
  public void register (Map<String, Class<? extends SearchComponent>> componentBag)   {
    componentBag.put(RealTimeGetComponent.COMPONENT_NAME, RealTimeGetComponent.class);
  }
}