package comp.DefaultSearchComponent;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.handler.component.SearchComponent;

import edu.umkc.type.tmpl.IDebugComponentRegister;
import edu.umkc.type.tmpl.IDefaultSearchComponent;
import edu.umkc.type.tmpl.IExpandComponentRegister;
import edu.umkc.type.tmpl.IFacetComponentRegister;
import edu.umkc.type.tmpl.IFacetModuleRegister;
import edu.umkc.type.tmpl.IHighlightComponentRegister;
import edu.umkc.type.tmpl.IMoreLikeThisComponentRegister;
import edu.umkc.type.tmpl.IQueryComponentRegister;
import edu.umkc.type.tmpl.IRealTimeGetComponentRegister;
import edu.umkc.type.tmpl.IStatsComponentRegister;

import java.util.Map;

public class DefaultSearchComponentArch extends AbstractMyxSimpleBrick implements IDefaultSearchComponent
{
    public static final IMyxName msg_IDefaultSearchComponent = MyxUtils.createName("edu.umkc.type.tmpl.IDefaultSearchComponent");
    public static final IMyxName msg_IQueryComponentRegister = MyxUtils.createName("edu.umkc.type.tmpl.IQueryComponentRegister");
    public static final IMyxName msg_IFacetComponentRegister = MyxUtils.createName("edu.umkc.type.tmpl.IFacetComponentRegister");
    public static final IMyxName msg_IFacetModuleRegister = MyxUtils.createName("edu.umkc.type.tmpl.IFacetModuleRegister");
    public static final IMyxName msg_IHighlightComponentRegister = MyxUtils.createName("edu.umkc.type.tmpl.IHighlightComponentRegister");
    public static final IMyxName msg_IExpandComponentRegister = MyxUtils.createName("edu.umkc.type.tmpl.IExpandComponentRegister");
    public static final IMyxName msg_IRealTimeGetComponentRegister = MyxUtils.createName("edu.umkc.type.tmpl.IRealTimeGetComponentRegister");
    public static final IMyxName msg_IDebugComponentRegister = MyxUtils.createName("edu.umkc.type.tmpl.IDebugComponentRegister");
    public static final IMyxName msg_IStatsComponentRegister = MyxUtils.createName("edu.umkc.type.tmpl.IStatsComponentRegister");
    public static final IMyxName msg_IMoreLikeThisComponentRegister = MyxUtils.createName("edu.umkc.type.tmpl.IMoreLikeThisComponentRegister");

    public IQueryComponentRegister OUT_IQueryComponentRegister;
    public IFacetComponentRegister OUT_IFacetComponentRegister;
    public IFacetModuleRegister OUT_IFacetModuleRegister;
    public IHighlightComponentRegister OUT_IHighlightComponentRegister;
    public IExpandComponentRegister OUT_IExpandComponentRegister;
    public IRealTimeGetComponentRegister OUT_IRealTimeGetComponentRegister;
    public IDebugComponentRegister OUT_IDebugComponentRegister;
    public IStatsComponentRegister OUT_IStatsComponentRegister;
    public IMoreLikeThisComponentRegister OUT_IMoreLikeThisComponentRegister;

	private IDefaultSearchComponentImp _imp;

    public DefaultSearchComponentArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IDefaultSearchComponentImp getImplementation(){
        try{
			return new DefaultSearchComponentImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_IQueryComponentRegister = (IQueryComponentRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IQueryComponentRegister);
        if (OUT_IQueryComponentRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IQueryComponentRegister returned null");
			return;       
        }
        OUT_IFacetComponentRegister = (IFacetComponentRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IFacetComponentRegister);
        if (OUT_IFacetComponentRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IFacetComponentRegister returned null");
			return;       
        }
        OUT_IFacetModuleRegister = (IFacetModuleRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IFacetModuleRegister);
        if (OUT_IFacetModuleRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IFacetModuleRegister returned null");
			return;       
        }
        OUT_IHighlightComponentRegister = (IHighlightComponentRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IHighlightComponentRegister);
        if (OUT_IHighlightComponentRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IHighlightComponentRegister returned null");
			return;       
        }
        OUT_IExpandComponentRegister = (IExpandComponentRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IExpandComponentRegister);
        if (OUT_IExpandComponentRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IExpandComponentRegister returned null");
			return;       
        }
        OUT_IRealTimeGetComponentRegister = (IRealTimeGetComponentRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IRealTimeGetComponentRegister);
        if (OUT_IRealTimeGetComponentRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IRealTimeGetComponentRegister returned null");
			return;       
        }
        OUT_IDebugComponentRegister = (IDebugComponentRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IDebugComponentRegister);
        if (OUT_IDebugComponentRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IDebugComponentRegister returned null");
			return;       
        }
        OUT_IStatsComponentRegister = (IStatsComponentRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IStatsComponentRegister);
        if (OUT_IStatsComponentRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IStatsComponentRegister returned null");
			return;       
        }
        OUT_IMoreLikeThisComponentRegister = (IMoreLikeThisComponentRegister) MyxUtils.getFirstRequiredServiceObject(this,msg_IMoreLikeThisComponentRegister);
        if (OUT_IMoreLikeThisComponentRegister == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IMoreLikeThisComponentRegister returned null");
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
		if (arg0.equals(msg_IDefaultSearchComponent)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,SearchComponent
    public Map<String, Class<? extends SearchComponent>> getDefaultSearchComponents ()   {
		return _imp.getDefaultSearchComponents();
    }    
}