package comp.PluginInfoHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IPluginInfoHandlerPlugin;

import java.util.Map;

public class PluginInfoHandlerPluginArch extends AbstractMyxSimpleBrick implements IPluginInfoHandlerPlugin
{
    public static final IMyxName msg_IPluginInfoHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IPluginInfoHandlerPlugin");


	private IPluginInfoHandlerPluginImp _imp;

    public PluginInfoHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IPluginInfoHandlerPluginImp getImplementation(){
        try{
			return new PluginInfoHandlerPluginImp();    
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
		if (arg0.equals(msg_IPluginInfoHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerPluginInfoHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerPluginInfoHandlerPlugin(info,infoMap);
    }    
}