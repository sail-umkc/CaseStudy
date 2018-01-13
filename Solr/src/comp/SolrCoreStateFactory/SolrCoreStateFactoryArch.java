package comp.SolrCoreStateFactory;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.DirectoryFactory;

import edu.umkc.solr.update.SolrCoreState;

import edu.umkc.type.tmpl.ISolrCoreStateFactory;
import edu.umkc.type.tmpl.ISolrIndexWriterFactory;

public class SolrCoreStateFactoryArch extends AbstractMyxSimpleBrick implements ISolrCoreStateFactory
{
    public static final IMyxName msg_ISolrCoreStateFactory = MyxUtils.createName("edu.umkc.type.tmpl.ISolrCoreStateFactory");
    public static final IMyxName msg_ISolrIndexWriterFactory = MyxUtils.createName("edu.umkc.type.tmpl.ISolrIndexWriterFactory");

    public ISolrIndexWriterFactory OUT_ISolrIndexWriterFactory;

	private ISolrCoreStateFactoryImp _imp;

    public SolrCoreStateFactoryArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrCoreStateFactoryImp getImplementation(){
        try{
			return new SolrCoreStateFactoryImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_ISolrIndexWriterFactory = (ISolrIndexWriterFactory) MyxUtils.getFirstRequiredServiceObject(this,msg_ISolrIndexWriterFactory);
        if (OUT_ISolrIndexWriterFactory == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISolrIndexWriterFactory returned null");
			return;       
        }
        _imp.begin();
    }
    
    public void end(){
        _imp.end();
    }
    
    public void destroy(){
        _imp.destroy();
    }
    
	public Object getServiceObject(IMyxName arg0) {
		if (arg0.equals(msg_ISolrCoreStateFactory)){
			return this;
		}        
		return null;
	}
  
    //To be imported: DirectoryFactory,SolrCoreState
    public SolrCoreState createDefaultSolrCoreState (final DirectoryFactory directoryFactory)   {
		return _imp.createDefaultSolrCoreState(directoryFactory);
    }    
}