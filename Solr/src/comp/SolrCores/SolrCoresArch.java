package comp.SolrCores;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.type.ICoreContainer;
import edu.umkc.type.ISolrCores;

import edu.umkc.type.tmpl.ISolrCoresBuilder;

public class SolrCoresArch extends AbstractMyxSimpleBrick implements ISolrCoresBuilder
{
    public static final IMyxName msg_ISolrCoresBuilder = MyxUtils.createName("edu.umkc.type.tmpl.ISolrCoresBuilder");


	private ISolrCoresImp _imp;

    public SolrCoresArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ISolrCoresImp getImplementation(){
        try{
			return new SolrCoresImp();    
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
		if (arg0.equals(msg_ISolrCoresBuilder)){
			return this;
		}        
		return null;
	}
  
    //To be imported: ICoreContainer,ISolrCores
    public ISolrCores createSolrCores (final ICoreContainer container)   {
		return _imp.createSolrCores(container);
    }    
}