package comp.FacetComponentRegister;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.handler.component.FacetComponent;
import edu.umkc.solr.handler.component.SearchComponent;

import java.util.Map;

public class FacetComponentRegisterImp implements IFacetComponentRegisterImp
{
	private FacetComponentRegisterArch _arch;

  public FacetComponentRegisterImp (){
  }

	public void setArch(FacetComponentRegisterArch arch){
		_arch = arch;
	}
	public FacetComponentRegisterArch getArch(){
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
    componentBag.put(FacetComponent.COMPONENT_NAME, FacetComponent.class);
  }
}