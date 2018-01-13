package comp.LoadAdminUIServlet;


import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.servlet.LoadAdminUiServlet;

public class LoadAdminUIServletImp implements ILoadAdminUIServletImp
{
	private LoadAdminUIServletArch _arch;
	
	final private LoadAdminUiServlet servlet;

    public LoadAdminUIServletImp (){
      servlet = new LoadAdminUiServlet();
    }

	public void setArch(LoadAdminUIServletArch arch){
		_arch = arch;
	}
	public LoadAdminUIServletArch getArch(){
		return _arch;
	}

	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init(){
	  Bootstrapper.incrInitCount();
	}
	public void begin(){
	  Bootstrapper.incrBeginCount();
	}
	public void end(){
		//TODO Auto-generated method stub
	}
	public void destroy(){
		//TODO Auto-generated method stub
	  servlet.destroy();
	}

	/*
  	  Implementation primitives required by the architecture
	*/
  
    //To be imported: IOException,Servlet,ServletConfig,ServletException,ServletRequest,ServletResponse
    public void init (ServletConfig servletConfig) throws ServletException {
		//TODO Auto-generated method stub
      servlet.init(servletConfig);
    }
    public ServletConfig getServletConfig ()   {
		//TODO Auto-generated method stub
		return servlet.getServletConfig();
    }
    public void service (ServletRequest servletRequest,ServletResponse servletResponse) throws ServletException,IOException {
		//TODO Auto-generated method stub
		servlet.service(servletRequest, servletResponse);
    }
    public String getServletInfo ()   {
		//TODO Auto-generated method stub
		return servlet.getServletInfo();
    }
}