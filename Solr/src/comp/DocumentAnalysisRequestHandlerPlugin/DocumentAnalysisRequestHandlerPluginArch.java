package comp.DocumentAnalysisRequestHandlerPlugin;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IDocumentAnalysisRequestHandlerPlugin;

import java.util.Map;

public class DocumentAnalysisRequestHandlerPluginArch extends AbstractMyxSimpleBrick implements IDocumentAnalysisRequestHandlerPlugin
{
    public static final IMyxName msg_IDocumentAnalysisRequestHandlerPlugin = MyxUtils.createName("edu.umkc.type.tmpl.IDocumentAnalysisRequestHandlerPlugin");


	private IDocumentAnalysisRequestHandlerPluginImp _imp;

    public DocumentAnalysisRequestHandlerPluginArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IDocumentAnalysisRequestHandlerPluginImp getImplementation(){
        try{
			return new DocumentAnalysisRequestHandlerPluginImp();    
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
		if (arg0.equals(msg_IDocumentAnalysisRequestHandlerPlugin)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,PluginInfo
    public boolean registerDocumentAnalysisRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
		return _imp.registerDocumentAnalysisRequestHandlerPlugin(info,infoMap);
    }    
}