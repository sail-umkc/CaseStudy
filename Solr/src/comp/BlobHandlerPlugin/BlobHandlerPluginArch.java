package comp.BlobHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IBlobHandlerPlugin;

import java.util.Map;

public class BlobHandlerPluginArch extends AbstractMyxSimpleBrick implements IBlobHandlerPlugin
{
    public static final IMyxName msg_IBlobHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IBlobHandlerPlugin");


	private IBlobHandlerPluginImp _imp;

    public BlobHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IBlobHandlerPluginImp getImplementation(){
        try{
			return new BlobHandlerPluginImp();    
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
		if (arg0.equals(msg_IBlobHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerBlobHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerBlobHandlerPlugin(info,infoMap);
    }    
}