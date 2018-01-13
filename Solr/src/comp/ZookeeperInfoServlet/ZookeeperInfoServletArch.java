package comp.ZookeeperInfoServlet;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.type.tmpl.IZookeeperInfoServlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ZookeeperInfoServletArch extends AbstractMyxSimpleBrick implements IZookeeperInfoServlet
{
    public static final IMyxName msg_IZookeeperInfoServlet = MyxUtils.createName("edu.umkc.type.tmpl.IZookeeperInfoServlet");


	private IZookeeperInfoServletImp _imp;

    public ZookeeperInfoServletArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IZookeeperInfoServletImp getImplementation(){
        try{
			return new ZookeeperInfoServletImp();    
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
		if (arg0.equals(msg_IZookeeperInfoServlet)){
			return this;
		}        
		return null;
	}
  
    //To be imported: IOException,Servlet,ServletConfig,ServletException,ServletRequest,ServletResponse
    public void init (ServletConfig servletConfig) throws ServletException {
		_imp.init(servletConfig);
    }    
    public ServletConfig getServletConfig ()   {
		return _imp.getServletConfig();
    }    
    public void service (ServletRequest servletRequest,ServletResponse servletResponse) throws ServletException,IOException {
		_imp.service(servletRequest,servletResponse);
    }    
    public String getServletInfo ()   {
		return _imp.getServletInfo();
    }    
}