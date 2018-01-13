package comp.HighlightComponentRegister;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.handler.component.SearchComponent;

import edu.umkc.type.tmpl.IHighlightComponentRegister;

import java.util.Map;

public class HighlightComponentRegisterArch extends AbstractMyxSimpleBrick implements IHighlightComponentRegister
{
    public static final IMyxName msg_IHighlightComponentRegister = MyxUtils.createName("edu.umkc.type.tmpl.IHighlightComponentRegister");


	private IHighlightComponentRegisterImp _imp;

    public HighlightComponentRegisterArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IHighlightComponentRegisterImp getImplementation(){
        try{
			return new HighlightComponentRegisterImp();    
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
		if (arg0.equals(msg_IHighlightComponentRegister)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,SearchComponent
    public void register (Map<String, Class<? extends SearchComponent>> componentBag)   {
		_imp.register(componentBag);
    }    
}