package comp.DumpRequestHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IDumpRequestHandlerPlugin;

import java.util.Map;

public class DumpRequestHandlerPluginArch extends AbstractMyxSimpleBrick implements IDumpRequestHandlerPlugin
{
    public static final IMyxName msg_IDumpRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IDumpRequestHandlerPlugin");


	private IDumpRequestHandlerPluginImp _imp;

    public DumpRequestHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IDumpRequestHandlerPluginImp getImplementation(){
        try{
			return new DumpRequestHandlerPluginImp();    
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
		if (arg0.equals(msg_IDumpRequestHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerDumpRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerDumpRequestHandlerPlugin(info,infoMap);
    }    
}