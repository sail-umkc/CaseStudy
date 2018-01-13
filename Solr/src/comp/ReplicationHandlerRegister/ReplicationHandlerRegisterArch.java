package comp.ReplicationHandlerRegister;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.core.PluginInfo;

import edu.umkc.type.tmpl.IImplicitReplicationHandlerRegister;

import java.util.List;

public class ReplicationHandlerRegisterArch extends AbstractMyxSimpleBrick implements IImplicitReplicationHandlerRegister
{
    public static final IMyxName msg_IImplicitReplicationHandlerRegister = MyxUtils.createName("edu.umkc.type.tmpl.IImplicitReplicationHandlerRegister");


	private IReplicationHandlerRegisterImp _imp;

    public ReplicationHandlerRegisterArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IReplicationHandlerRegisterImp getImplementation(){
        try{
			return new ReplicationHandlerRegisterImp();    
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
		if (arg0.equals(msg_IImplicitReplicationHandlerRegister)){
			return this;
		}        
		return null;
	}
  
    //To be imported: List,PluginInfo
    public void register (final List<PluginInfo> implicits)   {
		_imp.register(implicits);
    }    
}