package comp.SchemaHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.ISchemaHandlerPlugin;

import java.util.Map;

public class SchemaHandlerPluginArch extends AbstractMyxSimpleBrick implements ISchemaHandlerPlugin
{
    public static final IMyxName msg_ISchemaHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.ISchemaHandlerPlugin");


	private ISchemaHandlerPluginImp _imp;

    public SchemaHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISchemaHandlerPluginImp getImplementation(){
        try{
			return new SchemaHandlerPluginImp();    
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
		if (arg0.equals(msg_ISchemaHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerSchemaHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerSchemaHandlerPlugin(info,infoMap);
    }    
}