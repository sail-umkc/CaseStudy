package comp.SolrDispatchFilter;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.type.tmpl.ICoreContainerInitiator;
import edu.umkc.type.tmpl.ISolrDispatchFilter;
import edu.umkc.type.tmpl.ISolrResourceLoaderInitiator;
import edu.umkc.type.tmpl.ISolrXmlConfig;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SolrDispatchFilterArch extends AbstractMyxSimpleBrick implements ISolrDispatchFilter
{
    public static final IMyxName msg_ISolrDispatchFilter = MyxUtils.createName("edu.umkc.type.tmpl.ISolrDispatchFilter");
    public static final IMyxName msg_ICoreContainerInitiator = MyxUtils.createName("edu.umkc.type.tmpl.ICoreContainerInitiator");
    public static final IMyxName msg_ISolrXmlConfig = MyxUtils.createName("edu.umkc.type.tmpl.ISolrXmlConfig");
    public static final IMyxName msg_ISolrResourceLoaderInitiator = MyxUtils.createName("edu.umkc.type.tmpl.ISolrResourceLoaderInitiator");

    public ICoreContainerInitiator OUT_ICoreContainerInitiator;
    public ISolrXmlConfig OUT_ISolrXmlConfig;
    public ISolrResourceLoaderInitiator OUT_ISolrResourceLoaderInitiator;

	private ISolrDispatchFilterImp _imp;

    public SolrDispatchFilterArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrDispatchFilterImp getImplementation(){
        try{
			return new SolrDispatchFilterImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_ICoreContainerInitiator = (ICoreContainerInitiator) MyxUtils.getFirstRequiredServiceObject(this,msg_ICoreContainerInitiator);
        if (OUT_ICoreContainerInitiator == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ICoreContainerInitiator returned null");
			return;       
        }
        OUT_ISolrXmlConfig = (ISolrXmlConfig) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrXmlConfig);
        if (OUT_ISolrXmlConfig == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrXmlConfig returned null");
			return;       
        }
        OUT_ISolrResourceLoaderInitiator = (ISolrResourceLoaderInitiator) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrResourceLoaderInitiator);
        if (OUT_ISolrResourceLoaderInitiator == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrResourceLoaderInitiator returned null");
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
		if (arg0.equals(msg_ISolrDispatchFilter)){
			return this;
		}        
		return null;
	}
  
    //To be imported: IOException,Filter,FilterChain,FilterConfig,ServletException,ServletRequest,ServletResponse
    public void init (final FilterConfig filterConfig) throws ServletException {
		_imp.init(filterConfig);
    }    
    public void doFilter (final ServletRequest servletRequest,final ServletResponse ServletResponse,final FilterChain filterChain) throws IOException,ServletException {
		_imp.doFilter(servletRequest,ServletResponse,filterChain);
    }    
}