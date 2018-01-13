package comp.SchemaHandlerRegister;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;
import edu.umkc.solr.handler.SchemaHandler;

import java.util.List;

import comp.ImplicitPlugins.ImplicitPluginsImp;

public class SchemaHandlerRegisterImp implements ISchemaHandlerRegisterImp
{
	private SchemaHandlerRegisterArch _arch;

  public SchemaHandlerRegisterImp (){
  }

	public void setArch(SchemaHandlerRegisterArch arch){
		_arch = arch;
	}

	public SchemaHandlerRegisterArch getArch(){
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
    implicits.add(ImplicitPluginsImp.getReqHandlerInfo("/schema", SchemaHandler.class, null));
  }
}