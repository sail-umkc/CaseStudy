package comp.UpdateRequestHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IUpdateRequestHandlerPlugin;

import java.util.Map;

public class UpdateRequestHandlerPluginArch extends AbstractMyxSimpleBrick implements IUpdateRequestHandlerPlugin
{
    public static final IMyxName msg_IUpdateRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IUpdateRequestHandlerPlugin");


	private IUpdateRequestHandlerPluginImp _imp;

    public UpdateRequestHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IUpdateRequestHandlerPluginImp getImplementation(){
        try{
			return new UpdateRequestHandlerPluginImp();    
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
		if (arg0.equals(msg_IUpdateRequestHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerUpdateRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerUpdateRequestHandlerPlugin(info,infoMap);
    }    
}