package comp.ExpandComponentRegister;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.handler.component.SearchComponent;

import edu.umkc.type.tmpl.IExpandComponentRegister;

import java.util.Map;

public class ExpandComponentRegisterArch extends AbstractMyxSimpleBrick implements IExpandComponentRegister
{
    public static final IMyxName msg_IExpandComponentRegister = MyxUtils.createName("edu.umkc.type.tmpl.IExpandComponentRegister");


	private IExpandComponentRegisterImp _imp;

    public ExpandComponentRegisterArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IExpandComponentRegisterImp getImplementation(){
        try{
			return new ExpandComponentRegisterImp();    
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
		if (arg0.equals(msg_IExpandComponentRegister)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,SearchComponent
    public void register (Map<String, Class<? extends SearchComponent>> componentBag)   {
		_imp.register(componentBag);
    }    
}