package comp.SolrCores;


import comp.SolrCores.SolrCoresArch;

import edu.umkc.type.ICoreContainer;
import edu.umkc.type.ISolrCores;

public interface ISolrCoresImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SolrCoresArch arch);
	public SolrCoresArch getArch();
	
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
  
    //To be imported: ICoreContainer,ISolrCores
    public ISolrCores createSolrCores (final ICoreContainer container)  ;        
}