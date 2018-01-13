package comp.AdminHandlersPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IAdminHandlersPlugin;

import java.util.Map;

public class AdminHandlersPluginArch extends AbstractMyxSimpleBrick implements IAdminHandlersPlugin
{
    public static final IMyxName msg_IAdminHandlersPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IAdminHandlersPlugin");


	private IAdminHandlersPluginImp _imp;

    public AdminHandlersPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IAdminHandlersPluginImp getImplementation(){
        try{
			return new AdminHandlersPluginImp();    
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
		if (arg0.equals(msg_IAdminHandlersPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerAdminHandlersPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerAdminHandlersPlugin(info,infoMap);
    }    
}