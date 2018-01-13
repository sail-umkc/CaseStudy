package comp.SolrResourceLoader;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.SolrInfoMBean;

import edu.umkc.solr.handler.admin.CoreAdminHandler;

import edu.umkc.solr.rest.RestManager;

import edu.umkc.type.ICoreContainer;
import edu.umkc.type.ISolrCore;
import edu.umkc.type.ISolrResourceLoader;

import edu.umkc.type.tmpl.ISolrResourceLoaderInitiator;

import java.io.Closeable;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;

import java.nio.charset.Charset;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.lucene.analysis.util.ResourceLoader;

public class SolrResourceLoaderArch extends AbstractMyxSimpleBrick implements ISolrResourceLoaderInitiator,ISolrResourceLoader
{
    public static final IMyxName msg_ISolrResourceLoaderInitiator = MyxUtils.createName("edu.umkc.type.tmpl.ISolrResourceLoaderInitiator");
    public static final IMyxName msg_ISolrResourceLoader = MyxUtils.createName("edu.umkc.type.ISolrResourceLoader");


	private ISolrResourceLoaderImp _imp;

    public SolrResourceLoaderArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrResourceLoaderImp getImplementation(){
        try{
			return new SolrResourceLoaderImp();    
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
		if (arg0.equals(msg_ISolrResourceLoaderInitiator)){
			return this;
		}        
		if (arg0.equals(msg_ISolrResourceLoader)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Properties,ISolrResourceLoader
    public ISolrResourceLoader initSolrResourceLoader (final String instanceDir,final ClassLoader parent,final Properties coreProperties)   {
		return _imp.initSolrResourceLoader(instanceDir,parent,coreProperties);
    }    
  
    //To be imported: Closeable,FileFilter,IOException,InputStream,Charset,List,Map,Properties,ResourceLoader,ISolrCore,ICoreContainer,SolrInfoMBean,CoreAdminHandler,RestManager
    public RestManager.Registry getManagedResourceRegistry ()   {
		return _imp.getManagedResourceRegistry();
    }    
    public void addToClassLoader (final String baseDir,final FileFilter filter,boolean quiet)   {
		_imp.addToClassLoader(baseDir,filter,quiet);
    }    
    public void reloadLuceneSPI ()   {
		_imp.reloadLuceneSPI();
    }    
    public String[] listConfigDir ()   {
		return _imp.listConfigDir();
    }    
    public String getConfigDir ()   {
		return _imp.getConfigDir();
    }    
    public String getDataDir ()   {
		return _imp.getDataDir();
    }    
    public Properties getCoreProperties ()   {
		return _imp.getCoreProperties();
    }    
    public ClassLoader getClassLoader ()   {
		return _imp.getClassLoader();
    }    
    public InputStream openSchema (String name) throws IOException {
		return _imp.openSchema(name);
    }    
    public InputStream openConfig (String name) throws IOException {
		return _imp.openConfig(name);
    }    
    public InputStream openResource (String resource) throws IOException {
		return _imp.openResource(resource);
    }    
    public List<String> getLines (String resource) throws IOException {
		return _imp.getLines(resource);
    }    
    public List<String> getLines (String resource,String encoding) throws IOException {
		return _imp.getLines(resource,encoding);
    }    
    public List<String> getLines (String resource,Charset charset) throws IOException {
		return _imp.getLines(resource,charset);
    }    
    public <T> Class<? extends T> findClass (String cname,Class<T> expectedType)   {
		return _imp.findClass(cname,expectedType);
    }    
    public <T> Class<? extends T> findClass (String cname,Class<T> expectedType,String... subpackages)   {
		return _imp.findClass(cname,expectedType,subpackages);
    }    
    public <T> T newInstance (String name,Class<T> expectedType)   {
		return _imp.newInstance(name,expectedType);
    }    
    public <T> T newInstance (String cname,Class<T> expectedType,String ... subpackages)   {
		return _imp.newInstance(cname,expectedType,subpackages);
    }    
    public CoreAdminHandler newAdminHandlerInstance (final ICoreContainer coreContainer,String cname,String ... subpackages)   {
		return _imp.newAdminHandlerInstance(coreContainer,cname,subpackages);
    }    
    public <T> T newInstance (String cName,Class<T> expectedType,String [] subPackages,Class[] params,Object[] args)   {
		return _imp.newInstance(cName,expectedType,subPackages,params,args);
    }    
    public void inform (ISolrCore core)   {
		_imp.inform(core);
    }    
    public void inform (ResourceLoader loader) throws IOException {
		_imp.inform(loader);
    }    
    public void inform (Map<String, SolrInfoMBean> infoRegistry)   {
		_imp.inform(infoRegistry);
    }    
    public String getInstanceDir ()   {
		return _imp.getInstanceDir();
    }    
    public void close () throws IOException {
		_imp.close();
    }    
    public List<SolrInfoMBean> getInfoMBeans ()   {
		return _imp.getInfoMBeans();
    }    
    public String resolve (String pathToResolve)   {
		return _imp.resolve(pathToResolve);
    }    
}