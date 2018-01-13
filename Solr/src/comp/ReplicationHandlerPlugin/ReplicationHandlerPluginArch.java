package comp.ReplicationHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IReplicationHandlerPlugin;

import java.util.Map;

public class ReplicationHandlerPluginArch extends AbstractMyxSimpleBrick implements IReplicationHandlerPlugin
{
    public static final IMyxName msg_IReplicationHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IReplicationHandlerPlugin");


	private IReplicationHandlerPluginImp _imp;

    public ReplicationHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IReplicationHandlerPluginImp getImplementation(){
        try{
			return new ReplicationHandlerPluginImp();    
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
		if (arg0.equals(msg_IReplicationHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerReplicationHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerReplicationHandlerPlugin(info,infoMap);
    }    
}