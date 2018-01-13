package comp.LukeRequestHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.ILukeRequestHandlerPlugin;

import java.util.Map;

public class LukeRequestHandlerPluginArch extends AbstractMyxSimpleBrick implements ILukeRequestHandlerPlugin
{
    public static final IMyxName msg_ILukeRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ILukeRequestHandlerPlugin");


	private ILukeRequestHandlerPluginImp _imp;

    public LukeRequestHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ILukeRequestHandlerPluginImp getImplementation(){
        try{
			return new LukeRequestHandlerPluginImp();    
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
		if (arg0.equals(msg_ILukeRequestHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerLukeRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerLukeRequestHandlerPlugin(info,infoMap);
    }    
}