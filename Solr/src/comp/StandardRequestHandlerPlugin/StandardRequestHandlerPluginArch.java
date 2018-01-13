package comp.StandardRequestHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IStandardRequestHandlerPlugin;

import java.util.Map;

public class StandardRequestHandlerPluginArch extends AbstractMyxSimpleBrick implements IStandardRequestHandlerPlugin
{
    public static final IMyxName msg_IStandardRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IStandardRequestHandlerPlugin");


	private IStandardRequestHandlerPluginImp _imp;

    public StandardRequestHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IStandardRequestHandlerPluginImp getImplementation(){
        try{
			return new StandardRequestHandlerPluginImp();    
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
		if (arg0.equals(msg_IStandardRequestHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerStandardRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerStandardRequestHandlerPlugin(info,infoMap);
    }    
}