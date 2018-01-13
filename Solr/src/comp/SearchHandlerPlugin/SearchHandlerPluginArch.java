package comp.SearchHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.ISearchHandlerPlugin;

import java.util.Map;

public class SearchHandlerPluginArch extends AbstractMyxSimpleBrick implements ISearchHandlerPlugin
{
    public static final IMyxName msg_ISearchHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ISearchHandlerPlugin");


	private ISearchHandlerPluginImp _imp;

    public SearchHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISearchHandlerPluginImp getImplementation(){
        try{
			return new SearchHandlerPluginImp();    
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
		if (arg0.equals(msg_ISearchHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerSearchHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerSearchHandlerPlugin(info,infoMap);
    }    
}