package comp.SolrDispatchFilter;


import comp.SolrDispatchFilter.SolrDispatchFilterArch;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public interface ISolrDispatchFilterImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SolrDispatchFilterArch arch);
	public SolrDispatchFilterArch getArch();
	
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
  
    //To be imported: IOException,Filter,FilterChain,FilterConfig,ServletException,ServletRequest,ServletResponse
    public void init (final FilterConfig filterConfig) throws ServletException;        
    public void doFilter (final ServletRequest servletRequest,final ServletResponse ServletResponse,final FilterChain filterChain) throws IOException,ServletException;        
}