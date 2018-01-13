package comp.SolrInfoMBeanHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.ISolrInfoMBeanHandlerPlugin;

import java.util.Map;

public class SolrInfoMBeanHandlerPluginArch extends AbstractMyxSimpleBrick implements ISolrInfoMBeanHandlerPlugin
{
    public static final IMyxName msg_ISolrInfoMBeanHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ISolrInfoMBeanHandlerPlugin");


	private ISolrInfoMBeanHandlerPluginImp _imp;

    public SolrInfoMBeanHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrInfoMBeanHandlerPluginImp getImplementation(){
        try{
			return new SolrInfoMBeanHandlerPluginImp();    
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
		if (arg0.equals(msg_ISolrInfoMBeanHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerSolrInfoMBeanHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerSolrInfoMBeanHandlerPlugin(info,infoMap);
    }    
}