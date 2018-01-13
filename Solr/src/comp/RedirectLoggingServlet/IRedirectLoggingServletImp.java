package comp.RedirectLoggingServlet;


import comp.RedirectLoggingServlet.RedirectLoggingServletArch;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface IRedirectLoggingServletImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (RedirectLoggingServletArch arch);
	public RedirectLoggingServletArch getArch();
	
	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init();	
	public void begin();
	public void end();
	public void destroy();

	/*
  	  Implementation primitives required by the architecture
	*/
  
    //To be imported: IOException,Servlet,ServletConfig,ServletException,ServletRequest,ServletResponse
    public void init (ServletConfig servletConfig) throws ServletException;        
    public ServletConfig getServletConfig ()  ;        
    public void service (ServletRequest servletRequest,ServletResponse servletResponse) throws ServletException,IOException;        
    public String getServletInfo ()  ;        
}