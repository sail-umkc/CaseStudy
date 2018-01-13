package comp.MoreLikeThisComponentRegister;


import comp.MoreLikeThisComponentRegister.MoreLikeThisComponentRegisterArch;

import edu.umkc.solr.handler.component.SearchComponent;

import java.util.Map;

public interface IMoreLikeThisComponentRegisterImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (MoreLikeThisComponentRegisterArch arch);
	public MoreLikeThisComponentRegisterArch getArch();
	
	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init();	
	public void begin();
	public void end();
	public void destroy();

	/*
  	  Implementation primitives required by the architecture
	*/
  
    //To be imported: Map,SearchComponent
    public void register (Map<String, Class<? extends SearchComponent>> componentBag)  ;        
}