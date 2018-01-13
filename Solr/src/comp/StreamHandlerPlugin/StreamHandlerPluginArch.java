package comp.StreamHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IStreamHandlerPlugin;

import java.util.Map;

public class StreamHandlerPluginArch extends AbstractMyxSimpleBrick implements IStreamHandlerPlugin
{
    public static final IMyxName msg_IStreamHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IStreamHandlerPlugin");


	private IStreamHandlerPluginImp _imp;

    public StreamHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IStreamHandlerPluginImp getImplementation(){
        try{
			return new StreamHandlerPluginImp();    
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
		if (arg0.equals(msg_IStreamHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerStreamHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerStreamHandlerPlugin(info,infoMap);
    }    
}