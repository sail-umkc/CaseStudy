package comp.PluginInfoHandlerRegister;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IImplicitPluginInfoHandlerRegister;

import java.util.List;

public class PluginInfoHandlerRegisterArch extends AbstractMyxSimpleBrick implements IImplicitPluginInfoHandlerRegister
{
    public static final IMyxName msg_IImplicitPluginInfoHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitPluginInfoHandlerRegister");


	private IPluginInfoHandlerRegisterImp _imp;

    public PluginInfoHandlerRegisterArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IPluginInfoHandlerRegisterImp getImplementation(){
        try{
			return new PluginInfoHandlerRegisterImp();    
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
		if (arg0.equals(msg_IImplicitPluginInfoHandlerRegister)){
			return this;
		}        
		return null;
	}
  
    //To be imported: List,PluginInfo
    public void register (final List<PluginInfo> implicits)   {
		_imp.register(implicits);
    }    
}