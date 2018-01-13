package comp.ReloadCacheRequestHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IReloadCacheRequestHandlerPlugin;

import java.util.Map;

public class ReloadCacheRequestHandlerPluginArch extends AbstractMyxSimpleBrick implements IReloadCacheRequestHandlerPlugin
{
    public static final IMyxName msg_IReloadCacheRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IReloadCacheRequestHandlerPlugin");


	private IReloadCacheRequestHandlerPluginImp _imp;

    public ReloadCacheRequestHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IReloadCacheRequestHandlerPluginImp getImplementation(){
        try{
			return new ReloadCacheRequestHandlerPluginImp();    
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
		if (arg0.equals(msg_IReloadCacheRequestHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerReloadCacheRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerReloadCacheRequestHandlerPlugin(info,infoMap);
    }    
}