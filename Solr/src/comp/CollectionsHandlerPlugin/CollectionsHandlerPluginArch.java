package comp.CollectionsHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.ICollectionsHandlerPlugin;

import java.util.Map;

public class CollectionsHandlerPluginArch extends AbstractMyxSimpleBrick implements ICollectionsHandlerPlugin
{
    public static final IMyxName msg_ICollectionsHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ICollectionsHandlerPlugin");


	private ICollectionsHandlerPluginImp _imp;

    public CollectionsHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ICollectionsHandlerPluginImp getImplementation(){
        try{
			return new CollectionsHandlerPluginImp();    
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
		if (arg0.equals(msg_ICollectionsHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerCollectionsHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerCollectionsHandlerPlugin(info,infoMap);
    }    
}