package comp.SegmentsInfoRequestHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.ISegmentsInfoRequestHandlerPlugin;

import java.util.Map;

public class SegmentsInfoRequestHandlerPluginArch extends AbstractMyxSimpleBrick implements ISegmentsInfoRequestHandlerPlugin
{
    public static final IMyxName msg_ISegmentsInfoRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ISegmentsInfoRequestHandlerPlugin");


	private ISegmentsInfoRequestHandlerPluginImp _imp;

    public SegmentsInfoRequestHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISegmentsInfoRequestHandlerPluginImp getImplementation(){
        try{
			return new SegmentsInfoRequestHandlerPluginImp();    
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
		if (arg0.equals(msg_ISegmentsInfoRequestHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerSegmentsInfoRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerSegmentsInfoRequestHandlerPlugin(info,infoMap);
    }    
}