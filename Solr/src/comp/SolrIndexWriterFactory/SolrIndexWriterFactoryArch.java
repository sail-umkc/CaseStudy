package comp.SolrIndexWriterFactory;


import comp.SolrIndexWriterFactory.SolrIndexWriterFactoryImp.SolrIndexWriter;

import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.DirectoryFactory;

import edu.umkc.solr.schema.IndexSchema;

import edu.umkc.solr.update.SolrIndexConfig;

import edu.umkc.type.ISolrCore;

import edu.umkc.type.tmpl.ISolrIndexWriterFactory;

import java.io.IOException;

import org.apache.lucene.codecs.Codec;

import org.apache.lucene.index.IndexDeletionPolicy;

public class SolrIndexWriterFactoryArch extends AbstractMyxSimpleBrick implements ISolrIndexWriterFactory
{
    public static final IMyxName msg_ISolrIndexWriterFactory = MyxUtils.createName("edu.umkc.type.tmpl.ISolrIndexWriterFactory");


	private ISolrIndexWriterFactoryImp _imp;

    public SolrIndexWriterFactoryArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrIndexWriterFactoryImp getImplementation(){
        try{
			return new SolrIndexWriterFactoryImp();    
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
		if (arg0.equals(msg_ISolrIndexWriterFactory)){
			return this;
		}        
		return null;
	}
  
    //To be imported: IOException,Codec,IndexDeletionPolicy,SolrIndexWriter,DirectoryFactory,IndexSchema,SolrIndexConfig,ISolrCore
    public SolrIndexWriter createSolrIndexWriter (ISolrCore core,String name,String path,DirectoryFactory directoryFactory,boolean create,IndexSchema schema,SolrIndexConfig config,IndexDeletionPolicy delPolicy,Codec codec) throws IOException {
		return _imp.createSolrIndexWriter(core,name,path,directoryFactory,create,schema,config,delPolicy,codec);
    }    
}