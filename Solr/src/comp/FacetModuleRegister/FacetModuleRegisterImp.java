package comp.FacetModuleRegister;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.handler.component.SearchComponent;
import edu.umkc.solr.search.facet.FacetModule;

import java.util.Map;

public class FacetModuleRegisterImp implements IFacetModuleRegisterImp
{
	private FacetModuleRegisterArch _arch;

  public FacetModuleRegisterImp (){
  }

	public void setArch(FacetModuleRegisterArch arch){
		_arch = arch;
	}

	public FacetModuleRegisterArch getArch(){
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
    componentBag.put(FacetModule.COMPONENT_NAME, FacetModule.class);
  }
}