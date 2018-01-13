package comp.MoreLikeThisHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IMoreLikeThisHandlerPlugin;

import java.util.Map;

public class MoreLikeThisHandlerPluginArch extends AbstractMyxSimpleBrick implements IMoreLikeThisHandlerPlugin
{
    public static final IMyxName msg_IMoreLikeThisHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IMoreLikeThisHandlerPlugin");


	private IMoreLikeThisHandlerPluginImp _imp;

    public MoreLikeThisHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IMoreLikeThisHandlerPluginImp getImplementation(){
        try{
			return new MoreLikeThisHandlerPluginImp();    
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
		if (arg0.equals(msg_IMoreLikeThisHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerMoreLikeThisHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerMoreLikeThisHandlerPlugin(info,infoMap);
    }    
}