package comp.RequestHandlersFactory;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.type.IRequestHandlers;
import edu.umkc.type.ISolrCore;

import edu.umkc.type.tmpl.IImplicitPlugins;
import edu.umkc.type.tmpl.IRequestHandlersFactory;
import edu.umkc.type.tmpl.ISolrXmlRequestHandlerPluginRegister;

public class RequestHandlersFactoryArch extends AbstractMyxSimpleBrick implements IRequestHandlersFactory
{
    public static final IMyxName msg_IRequestHandlersFactory = MyxUtils.createName("edu.umkc.type.tmpl.IRequestHandlersFactory");
    public static final IMyxName msg_IImplicitPlugins = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitPlugins");
    public static final IMyxName msg_ISolrXmlRequestHandlerPluginRegister = MyxUtils.createName("edu.umkc.type.tmpl.ISolrXmlRequestHandlerPluginRegister");

    public IImplicitPlugins OUT_IImplicitPlugins;
    public ISolrXmlRequestHandlerPluginRegister OUT_ISolrXmlRequestHandlerPluginRegister;

	private IRequestHandlersFactoryImp _imp;

    public RequestHandlersFactoryArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IRequestHandlersFactoryImp getImplementation(){
        try{
			return new RequestHandlersFactoryImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_IImplicitPlugins = (IImplicitPlugins) MyxUtils.getFirstRequiredServiceObject(this,msg_IImplicitPlugins);
        if (OUT_IImplicitPlugins == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IImplicitPlugins returned null");
			return;       
        }
        OUT_ISolrXmlRequestHandlerPluginRegister = (ISolrXmlRequestHandlerPluginRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrXmlRequestHandlerPluginRegister);
        if (OUT_ISolrXmlRequestHandlerPluginRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrXmlRequestHandlerPluginRegister returned null");
			return;       
        }
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		if (arg0.equals(msg_IRequestHandlersFactory)){
			return this;
		}        
		return null;
	}
  
    //To be imported: IRequestHandlers,ISolrCore
    public IRequestHandlers createRequestHandler (final ISolrCore solrCore)   {
		return _imp.createRequestHandler(solrCore);
    }    
}