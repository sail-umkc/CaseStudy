package comp.StatsComponentRegister;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.handler.component.SearchComponent;
import edu.umkc.solr.handler.component.StatsComponent;

import java.util.Map;

public class StatsComponentRegisterImp implements IStatsComponentRegisterImp
{
	private StatsComponentRegisterArch _arch;

  public StatsComponentRegisterImp (){
  }

	public void setArch(StatsComponentRegisterArch arch){
		_arch = arch;
	}

	public StatsComponentRegisterArch getArch(){
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
    componentBag.put(StatsComponent.COMPONENT_NAME, StatsComponent.class);
  }
}