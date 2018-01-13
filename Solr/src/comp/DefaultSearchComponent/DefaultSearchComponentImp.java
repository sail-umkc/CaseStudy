package comp.DefaultSearchComponent;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.handler.component.SearchComponent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DefaultSearchComponentImp implements IDefaultSearchComponentImp
{
	private DefaultSearchComponentArch _arch;

  public DefaultSearchComponentImp (){
  }

	public void setArch(DefaultSearchComponentArch arch){
		_arch = arch;
	}
	
	public DefaultSearchComponentArch getArch(){
		return _arch;
	}

	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init(){
	  Bootstrapper.incrInitCount();
	}
	
	public void begin() {
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
  public Map<String, Class<? extends SearchComponent>> getDefaultSearchComponents ()
  {
    HashMap<String, Class<? extends SearchComponent>> componentBag = new HashMap<>();
    
    _arch.OUT_IHighlightComponentRegister.register(componentBag);
    _arch.OUT_IQueryComponentRegister.register(componentBag);
    _arch.OUT_IFacetComponentRegister.register(componentBag);
    _arch.OUT_IFacetModuleRegister.register(componentBag);
    _arch.OUT_IMoreLikeThisComponentRegister.register(componentBag);
    _arch.OUT_IStatsComponentRegister.register(componentBag);
    _arch.OUT_IDebugComponentRegister.register(componentBag);
    _arch.OUT_IRealTimeGetComponentRegister.register(componentBag);
    _arch.OUT_IExpandComponentRegister.register(componentBag);
    
    return Collections.unmodifiableMap(componentBag);
  }
}