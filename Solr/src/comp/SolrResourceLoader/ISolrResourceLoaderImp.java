package comp.SolrResourceLoader;


import comp.SolrResourceLoader.SolrResourceLoaderArch;

import edu.umkc.solr.core.SolrInfoMBean;

import edu.umkc.solr.handler.admin.CoreAdminHandler;

import edu.umkc.solr.rest.RestManager;

import edu.umkc.type.ICoreContainer;
import edu.umkc.type.ISolrCore;
import edu.umkc.type.ISolrResourceLoader;

import java.io.Closeable;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.Charset;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.lucene.analysis.util.ResourceLoader;

public interface ISolrResourceLoaderImp 
{

	/*
	  Getter and Setter of architecture reference
	*/
    public void setArch (SolrResourceLoaderArch arch);
	public SolrResourceLoaderArch getArch();
	
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
  
    //To be imported: Properties,ISolrResourceLoader
    public ISolrResourceLoader initSolrResourceLoader (final String instanceDir,final ClassLoader parent,final Properties coreProperties)  ;        
  
    //To be imported: Closeable,FileFilter,IOException,InputStream,Charset,List,Map,Properties,ResourceLoader,ISolrCore,ICoreContainer,SolrInfoMBean,CoreAdminHandler,RestManager
    public RestManager.Registry getManagedResourceRegistry ()  ;        
    public void addToClassLoader (final String baseDir,final FileFilter filter,boolean quiet)  ;        
    public void reloadLuceneSPI ()  ;        
    public String[] listConfigDir ()  ;        
    public String getConfigDir ()  ;        
    public String getDataDir ()  ;        
    public Properties getCoreProperties ()  ;        
    public ClassLoader getClassLoader ()  ;        
    public InputStream openSchema (String name) throws IOException;        
    public InputStream openConfig (String name) throws IOException;        
    public InputStream openResource (String resource) throws IOException;        
    public List<String> getLines (String resource) throws IOException;        
    public List<String> getLines (String resource,String encoding) throws IOException;        
    public List<String> getLines (String resource,Charset charset) throws IOException;        
    public <T> Class<? extends T> findClass (String cname,Class<T> expectedType)  ;        
    public <T> Class<? extends T> findClass (String cname,Class<T> expectedType,String... subpackages)  ;        
    public <T> T newInstance (String name,Class<T> expectedType)  ;        
    public <T> T newInstance (String cname,Class<T> expectedType,String ... subpackages)  ;        
    public CoreAdminHandler newAdminHandlerInstance (final ICoreContainer coreContainer,String cname,String ... subpackages)  ;        
    public <T> T newInstance (String cName,Class<T> expectedType,String [] subPackages,Class[] params,Object[] args)  ;        
    public void inform (ISolrCore core)  ;        
    public void inform (ResourceLoader loader) throws IOException;        
    public void inform (Map<String, SolrInfoMBean> infoRegistry)  ;        
    public String getInstanceDir ()  ;        
    public void close () throws IOException;        
    public List<SolrInfoMBean> getInfoMBeans ()  ;        
    public String resolve (String pathToResolve)  ;        
}