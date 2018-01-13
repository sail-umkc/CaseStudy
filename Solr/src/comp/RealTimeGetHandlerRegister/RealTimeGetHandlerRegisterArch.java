package comp.RealTimeGetHandlerRegister;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IImplicitRealTimeGetHandlerRegister;

import java.util.List;

public class RealTimeGetHandlerRegisterArch extends AbstractMyxSimpleBrick implements IImplicitRealTimeGetHandlerRegister
{
    public static final IMyxName msg_IImplicitRealTimeGetHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitRealTimeGetHandlerRegister");


	private IRealTimeGetHandlerRegisterImp _imp;

    public RealTimeGetHandlerRegisterArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IRealTimeGetHandlerRegisterImp getImplementation(){
        try{
			return new RealTimeGetHandlerRegisterImp();    
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
		if (arg0.equals(msg_IImplicitRealTimeGetHandlerRegister)){
			return this;
		}        
		return null;
	}
  
    //To be imported: List,PluginInfo
    public void register (final List<PluginInfo> implicits)   {
		_imp.register(implicits);
    }    
}