package comp.ShowFileRequestHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IShowFileRequestHandlerPlugin;

import java.util.Map;

public class ShowFileRequestHandlerPluginArch extends AbstractMyxSimpleBrick implements IShowFileRequestHandlerPlugin
{
    public static final IMyxName msg_IShowFileRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IShowFileRequestHandlerPlugin");


	private IShowFileRequestHandlerPluginImp _imp;

    public ShowFileRequestHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IShowFileRequestHandlerPluginImp getImplementation(){
        try{
			return new ShowFileRequestHandlerPluginImp();    
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
		if (arg0.equals(msg_IShowFileRequestHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerShowFileRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerShowFileRequestHandlerPlugin(info,infoMap);
    }    
}