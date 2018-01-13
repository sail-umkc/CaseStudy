package comp.SolrConfigHandlerRegister;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IImplicitSolrConfigHandlerRegister;

import java.util.List;

public class SolrConfigHandlerRegisterArch extends AbstractMyxSimpleBrick implements IImplicitSolrConfigHandlerRegister
{
    public static final IMyxName msg_IImplicitSolrConfigHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitSolrConfigHandlerRegister");


	private ISolrConfigHandlerRegisterImp _imp;

    public SolrConfigHandlerRegisterArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrConfigHandlerRegisterImp getImplementation(){
        try{
			return new SolrConfigHandlerRegisterImp();    
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
		if (arg0.equals(msg_IImplicitSolrConfigHandlerRegister)){
			return this;
		}        
		return null;
	}
  
    //To be imported: List,PluginInfo
    public void register (final List<PluginInfo> implicits)   {
		_imp.register(implicits);
    }    
}