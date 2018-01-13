package comp.RequestHandlersFactory;


import comp.RequestHandlersFactory.RequestHandlersFactoryArch;

import edu.umkc.type.IRequestHandlers;
import edu.umkc.type.ISolrCore;

public interface IRequestHandlersFactoryImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (RequestHandlersFactoryArch arch);
	public RequestHandlersFactoryArch getArch();
	
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
  
    //To be imported: IRequestHandlers,ISolrCore
    public IRequestHandlers createRequestHandler (final ISolrCore solrCore)  ;        
}