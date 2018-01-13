package comp.RealTimeGetHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IRealTimeGetHandlerPlugin;

import java.util.Map;

public class RealTimeGetHandlerPluginArch extends AbstractMyxSimpleBrick implements IRealTimeGetHandlerPlugin
{
    public static final IMyxName msg_IRealTimeGetHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IRealTimeGetHandlerPlugin");


	private IRealTimeGetHandlerPluginImp _imp;

    public RealTimeGetHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IRealTimeGetHandlerPluginImp getImplementation(){
        try{
			return new RealTimeGetHandlerPluginImp();    
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
		if (arg0.equals(msg_IRealTimeGetHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerRealTimeGetHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerRealTimeGetHandlerPlugin(info,infoMap);
    }    
}