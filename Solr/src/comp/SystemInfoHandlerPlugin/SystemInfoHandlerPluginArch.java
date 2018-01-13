package comp.SystemInfoHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.ISystemInfoHandlerPlugin;

import java.util.Map;

public class SystemInfoHandlerPluginArch extends AbstractMyxSimpleBrick implements ISystemInfoHandlerPlugin
{
    public static final IMyxName msg_ISystemInfoHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ISystemInfoHandlerPlugin");


	private ISystemInfoHandlerPluginImp _imp;

    public SystemInfoHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISystemInfoHandlerPluginImp getImplementation(){
        try{
			return new SystemInfoHandlerPluginImp();    
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
		if (arg0.equals(msg_ISystemInfoHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerSystemInfoHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerSystemInfoHandlerPlugin(info,infoMap);
    }    
}