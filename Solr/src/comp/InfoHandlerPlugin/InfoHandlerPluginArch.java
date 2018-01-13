package comp.InfoHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IInfoHandlerPlugin;

import java.util.Map;

public class InfoHandlerPluginArch extends AbstractMyxSimpleBrick implements IInfoHandlerPlugin
{
    public static final IMyxName msg_IInfoHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IInfoHandlerPlugin");


	private IInfoHandlerPluginImp _imp;

    public InfoHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IInfoHandlerPluginImp getImplementation(){
        try{
			return new InfoHandlerPluginImp();    
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
		if (arg0.equals(msg_IInfoHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerInfoHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerInfoHandlerPlugin(info,infoMap);
    }    
}