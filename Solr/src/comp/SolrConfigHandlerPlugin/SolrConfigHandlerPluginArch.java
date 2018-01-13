package comp.SolrConfigHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.ISolrConfigHandlerPlugin;

import java.util.Map;

public class SolrConfigHandlerPluginArch extends AbstractMyxSimpleBrick implements ISolrConfigHandlerPlugin
{
    public static final IMyxName msg_ISolrConfigHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ISolrConfigHandlerPlugin");


	private ISolrConfigHandlerPluginImp _imp;

    public SolrConfigHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrConfigHandlerPluginImp getImplementation(){
        try{
			return new SolrConfigHandlerPluginImp();    
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
		if (arg0.equals(msg_ISolrConfigHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerSolrConfigHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerSolrConfigHandlerPlugin(info,infoMap);
    }    
}