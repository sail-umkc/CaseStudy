package comp.PropertiesRequestHandlerRegister;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IImplicitPropertiesRequestHandlerRegister;

import java.util.List;

public class PropertiesRequestHandlerRegisterArch extends AbstractMyxSimpleBrick implements IImplicitPropertiesRequestHandlerRegister
{
    public static final IMyxName msg_IImplicitPropertiesRequestHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitPropertiesRequestHandlerRegister");


	private IPropertiesRequestHandlerRegisterImp _imp;

    public PropertiesRequestHandlerRegisterArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IPropertiesRequestHandlerRegisterImp getImplementation(){
        try{
			return new PropertiesRequestHandlerRegisterImp();    
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
		if (arg0.equals(msg_IImplicitPropertiesRequestHandlerRegister)){
			return this;
		}        
		return null;
	}
  
    //To be imported: List,PluginInfo
    public void register (final List<PluginInfo> implicits)   {
		_imp.register(implicits);
    }    
}