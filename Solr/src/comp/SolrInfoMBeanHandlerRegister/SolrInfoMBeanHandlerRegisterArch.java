package comp.SolrInfoMBeanHandlerRegister;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IImplicitSolrInfoMBeanHandlerRegister;

import java.util.List;

public class SolrInfoMBeanHandlerRegisterArch extends AbstractMyxSimpleBrick implements IImplicitSolrInfoMBeanHandlerRegister
{
    public static final IMyxName msg_IImplicitSolrInfoMBeanHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitSolrInfoMBeanHandlerRegister");


	private ISolrInfoMBeanHandlerRegisterImp _imp;

    public SolrInfoMBeanHandlerRegisterArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrInfoMBeanHandlerRegisterImp getImplementation(){
        try{
			return new SolrInfoMBeanHandlerRegisterImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		if (arg0.equals(msg_IImplicitSolrInfoMBeanHandlerRegister)){
			return this;
		}        
		return null;
	}
  
    //To be imported: List,PluginInfo
    public void register (final List<PluginInfo> implicits)   {
		_imp.register(implicits);
    }    
}