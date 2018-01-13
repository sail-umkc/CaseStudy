package comp.SolrIndexSearcherFactory;


import comp.SolrIndexSearcherFactory.SolrIndexSearcherFactoryImp.SolrIndexSearcher;

import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.DirectoryFactory;

import edu.umkc.solr.schema.IndexSchema;

import edu.umkc.type.ISolrCore;

import edu.umkc.type.tmpl.ISolrIndexSearcherFactory;

import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;

public class SolrIndexSearcherFactoryArch extends AbstractMyxSimpleBrick implements ISolrIndexSearcherFactory
{
    public static final IMyxName msg_ISolrIndexSearcherFactory = MyxUtils.createName("edu.umkc.type.tmpl.ISolrIndexSearcherFactory");


	private ISolrIndexSearcherFactoryImp _imp;

    public SolrIndexSearcherFactoryArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrIndexSearcherFactoryImp getImplementation(){
        try{
			return new SolrIndexSearcherFactoryImp();    
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
		if (arg0.equals(msg_ISolrIndexSearcherFactory)){
			return this;
		}        
		return null;
	}
  
    //To be imported: IOException,DirectoryReader,DirectoryFactory,IndexSchema,SolrIndexSearcher,ISolrCore
    public SolrIndexSearcher createSolrIndexSearcher (ISolrCore core,String path,IndexSchema schema,String name,DirectoryReader r,boolean closeReader,boolean enableCache,boolean reserveDirectory,DirectoryFactory directoryFactory) throws IOException {
		return _imp.createSolrIndexSearcher(core,path,schema,name,r,closeReader,enableCache,reserveDirectory,directoryFactory);
    }    
}