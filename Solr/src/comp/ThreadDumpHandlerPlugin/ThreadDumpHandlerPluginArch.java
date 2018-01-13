package comp.ThreadDumpHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IThreadDumpHandlerPlugin;

import java.util.Map;

public class ThreadDumpHandlerPluginArch extends AbstractMyxSimpleBrick implements IThreadDumpHandlerPlugin
{
    public static final IMyxName msg_IThreadDumpHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IThreadDumpHandlerPlugin");


	private IThreadDumpHandlerPluginImp _imp;

    public ThreadDumpHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IThreadDumpHandlerPluginImp getImplementation(){
        try{
			return new ThreadDumpHandlerPluginImp();    
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
		if (arg0.equals(msg_IThreadDumpHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerThreadDumpHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerThreadDumpHandlerPlugin(info,infoMap);
    }    
}