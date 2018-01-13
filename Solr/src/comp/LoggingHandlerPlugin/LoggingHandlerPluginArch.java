package comp.LoggingHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.ILoggingHandlerPlugin;

import java.util.Map;

public class LoggingHandlerPluginArch extends AbstractMyxSimpleBrick implements ILoggingHandlerPlugin
{
    public static final IMyxName msg_ILoggingHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ILoggingHandlerPlugin");


	private ILoggingHandlerPluginImp _imp;

    public LoggingHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ILoggingHandlerPluginImp getImplementation(){
        try{
			return new LoggingHandlerPluginImp();    
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
		if (arg0.equals(msg_ILoggingHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerLoggingHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerLoggingHandlerPlugin(info,infoMap);
    }    
}