package comp.ExpandComponentRegister;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.handler.component.ExpandComponent;
import edu.umkc.solr.handler.component.SearchComponent;

import java.util.Map;

public class ExpandComponentRegisterImp implements IExpandComponentRegisterImp
{
	private ExpandComponentRegisterArch _arch;

  public ExpandComponentRegisterImp (){
  }

	public void setArch(ExpandComponentRegisterArch arch){
		_arch = arch;
	}
	
	public ExpandComponentRegisterArch getArch(){
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
  		componentBag.put(ExpandComponent.COMPONENT_NAME, ExpandComponent.class);
    }
}