package comp.SolrXmlConfig;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.Config;
import edu.umkc.solr.core.NodeConfig;

import edu.umkc.type.ISolrResourceLoader;

import edu.umkc.type.tmpl.ISolrXmlConfig;

import java.io.File;
import java.io.InputStream;

import java.nio.file.Path;

public class SolrXmlConfigArch extends AbstractMyxSimpleBrick implements ISolrXmlConfig
{
    public static final IMyxName msg_ISolrXmlConfig = MyxUtils.createName("edu.umkc.type.tmpl.ISolrXmlConfig");


	private ISolrXmlConfigImp _imp;

    public SolrXmlConfigArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrXmlConfigImp getImplementation(){
        try{
			return new SolrXmlConfigImp();    
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
		if (arg0.equals(msg_ISolrXmlConfig)){
			return this;
		}        
		return null;
	}
  
    //To be imported: File,InputStream,Path,Config,NodeConfig,ISolrResourceLoader
    public NodeConfig fromConfig (Config config)   {
		return _imp.fromConfig(config);
    }    
    public NodeConfig fromFile (ISolrResourceLoader loader,File configFile)   {
		return _imp.fromFile(loader,configFile);
    }    
    public NodeConfig fromString (ISolrResourceLoader loader,String xml)   {
		return _imp.fromString(loader,xml);
    }    
    public NodeConfig fromInputStream (ISolrResourceLoader loader,InputStream is)   {
		return _imp.fromInputStream(loader,is);
    }    
    public NodeConfig fromSolrHome (ISolrResourceLoader loader,String solrHome)   {
		return _imp.fromSolrHome(loader,solrHome);
    }    
    public NodeConfig fromSolrHome (Path solrHome)   {
		return _imp.fromSolrHome(solrHome);
    }    
}