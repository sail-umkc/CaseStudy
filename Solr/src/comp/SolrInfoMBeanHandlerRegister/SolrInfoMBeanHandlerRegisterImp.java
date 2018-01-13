package comp.SolrInfoMBeanHandlerRegister;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;
import edu.umkc.solr.handler.admin.SolrInfoMBeanHandler;

import java.util.List;

import comp.ImplicitPlugins.ImplicitPluginsImp;

public class SolrInfoMBeanHandlerRegisterImp implements ISolrInfoMBeanHandlerRegisterImp
{
	private SolrInfoMBeanHandlerRegisterArch _arch;

  public SolrInfoMBeanHandlerRegisterImp (){
  }

	public void setArch(SolrInfoMBeanHandlerRegisterArch arch){
		_arch = arch;
	}

	public SolrInfoMBeanHandlerRegisterArch getArch(){
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
    implicits.add(ImplicitPluginsImp.getReqHandlerInfo("/admin/mbeans", SolrInfoMBeanHandler.class, null));
  }
}