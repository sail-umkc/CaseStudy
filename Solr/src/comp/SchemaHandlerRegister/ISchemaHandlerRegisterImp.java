package comp.SchemaHandlerRegister;


import comp.SchemaHandlerRegister.SchemaHandlerRegisterArch;

import edu.umkc.solr.core.PluginInfo;

import java.util.List;

public interface ISchemaHandlerRegisterImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SchemaHandlerRegisterArch arch);
	public SchemaHandlerRegisterArch getArch();
	
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
  
    //To be imported: List,PluginInfo
    public void register (final List<PluginInfo> implicits)  ;        
}