package comp.SolrConfiguration;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.type.tmpl.ILoadAdminUIServlet;
import edu.umkc.type.tmpl.IRedirectLoggingServlet;
import edu.umkc.type.tmpl.IRedirectOldAdminUIServlet;
import edu.umkc.type.tmpl.IRedirectOldZookeeperServlet;
import edu.umkc.type.tmpl.ISolrDispatchFilter;
import edu.umkc.type.tmpl.IZookeeperInfoServlet;

public class SolrConfigurationArch extends AbstractMyxSimpleBrick
{
    public static final IMyxName msg_ILoadAdminUIServlet = MyxUtils.createName("edu.umkc.type.tmpl.ILoadAdminUIServlet");
    public static final IMyxName msg_IRedirectLoggingServlet = MyxUtils.createName("edu.umkc.type.tmpl.IRedirectLoggingServlet");
    public static final IMyxName msg_IRedirectOldAdminUIServlet = MyxUtils.createName("edu.umkc.type.tmpl.IRedirectOldAdminUIServlet");
    public static final IMyxName msg_IZookeeperInfoServlet = MyxUtils.createName("edu.umkc.type.tmpl.IZookeeperInfoServlet");
    public static final IMyxName msg_ISolrDispatchFilter = MyxUtils.createName("edu.umkc.type.tmpl.ISolrDispatchFilter");
    public static final IMyxName msg_IRedirectOldZookeeperServlet = MyxUtils.createName("edu.umkc.type.tmpl.IRedirectOldZookeeperServlet");

    public ILoadAdminUIServlet OUT_ILoadAdminUIServlet;
    public IRedirectLoggingServlet OUT_IRedirectLoggingServlet;
    public IRedirectOldAdminUIServlet OUT_IRedirectOldAdminUIServlet;
    public IZookeeperInfoServlet OUT_IZookeeperInfoServlet;
    public ISolrDispatchFilter OUT_ISolrDispatchFilter;
    public IRedirectOldZookeeperServlet OUT_IRedirectOldZookeeperServlet;

	private ISolrConfigurationImp _imp;

    public SolrConfigurationArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrConfigurationImp getImplementation(){
        try{
			return new SolrConfigurationImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_ILoadAdminUIServlet = (ILoadAdminUIServlet) MyxUtils.getFirstRequiredServiceObject(this,msg_ILoadAdminUIServlet);
        if (OUT_ILoadAdminUIServlet == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ILoadAdminUIServlet returned null");
			return;       
        }
        OUT_IRedirectLoggingServlet = (IRedirectLoggingServlet) MyxUtils.getFirstRequiredServiceObject(this,msg_IRedirectLoggingServlet);
        if (OUT_IRedirectLoggingServlet == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IRedirectLoggingServlet returned null");
			return;       
        }
        OUT_IRedirectOldAdminUIServlet = (IRedirectOldAdminUIServlet) MyxUtils.getFirstRequiredServiceObject(this,msg_IRedirectOldAdminUIServlet);
        if (OUT_IRedirectOldAdminUIServlet == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IRedirectOldAdminUIServlet returned null");
			return;       
        }
        OUT_IZookeeperInfoServlet = (IZookeeperInfoServlet) MyxUtils.getFirstRequiredServiceObject(this,msg_IZookeeperInfoServlet);
        if (OUT_IZookeeperInfoServlet == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IZookeeperInfoServlet returned null");
			return;       
        }
        OUT_ISolrDispatchFilter = (ISolrDispatchFilter) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrDispatchFilter);
        if (OUT_ISolrDispatchFilter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrDispatchFilter returned null");
			return;       
        }
        OUT_IRedirectOldZookeeperServlet = (IRedirectOldZookeeperServlet) MyxUtils.getFirstRequiredServiceObject(this,msg_IRedirectOldZookeeperServlet);
        if (OUT_IRedirectOldZookeeperServlet == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IRedirectOldZookeeperServlet returned null");
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
		return null;
	}
}