package comp.MoreLikeThisComponentRegister;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.handler.component.SearchComponent;

import edu.umkc.type.tmpl.IMoreLikeThisComponentRegister;

import java.util.Map;

public class MoreLikeThisComponentRegisterArch extends AbstractMyxSimpleBrick implements IMoreLikeThisComponentRegister
{
    public static final IMyxName msg_IMoreLikeThisComponentRegister = MyxUtils.createName("edu.umkc.type.tmpl.IMoreLikeThisComponentRegister");


	private IMoreLikeThisComponentRegisterImp _imp;

    public MoreLikeThisComponentRegisterArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IMoreLikeThisComponentRegisterImp getImplementation(){
        try{
			return new MoreLikeThisComponentRegisterImp();    
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
		if (arg0.equals(msg_IMoreLikeThisComponentRegister)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,SearchComponent
    public void register (Map<String, Class<? extends SearchComponent>> componentBag)   {
		_imp.register(componentBag);
    }    
}