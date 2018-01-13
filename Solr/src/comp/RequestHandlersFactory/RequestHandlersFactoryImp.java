package comp.RequestHandlersFactory;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.util.StrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.InitParams;
import edu.umkc.solr.core.PluginBag;
import edu.umkc.solr.core.PluginInfo;
import edu.umkc.solr.core.SolrConfig;
import edu.umkc.solr.request.SolrRequestHandler;
import edu.umkc.type.IRequestHandlers;
import edu.umkc.type.ISolrCore;

public class RequestHandlersFactoryImp implements IRequestHandlersFactoryImp
{
	private RequestHandlersFactoryArch _arch;

  public RequestHandlersFactoryImp (){
  }

	public void setArch(RequestHandlersFactoryArch arch){
		_arch = arch;
	}
	
	public RequestHandlersFactoryArch getArch(){
		return _arch;
	}

	/*
  	  Myx Lifecycle Methods: these methods are called automatically by the framework
  	  as the bricks are created, attached, detached, and destroyed respectively.
	*/	
	public void init(){
	  Bootstrapper.incrInitCount();
	}
	
	public void begin(){
	  Bootstrapper.incrBeginCount();
	}
	
	public void end(){
		//TODO Auto-generated method stub
	}
	
	public void destroy(){
		//TODO Auto-generated method stub
	}
	
  /**
   * Trim the trailing '/' if it's there, and convert null to empty string.
   * 
   * we want:
   *  /update/csv   and
   *  /update/csv/
   * to map to the same handler 
   * 
   */
  public static String normalize( String p )
  {
    if(p == null) return "";
    if( p.endsWith( "/" ) && p.length() > 1 )
      return p.substring( 0, p.length()-1 );
    
    return p;
  }
  
	/*
  	  Implementation primitives required by the architecture
	*/

  public IRequestHandlers createRequestHandler (ISolrCore solrCore) {
    final RequestHandlers requestHandlers = new RequestHandlers();
    
    requestHandlers.core = solrCore;
    // we need a thread safe registry since methods like register are currently documented to be thread safe.
    requestHandlers.handlers =  new PluginBag<>(SolrRequestHandler.class, solrCore, true);
    
    return requestHandlers;
  }  

  public static Logger log = LoggerFactory.getLogger(RequestHandlers.class);

  private final class RequestHandlers implements IRequestHandlers {

    private ISolrCore core;

    private PluginBag<SolrRequestHandler> handlers;

    /**
     * @return the RequestHandler registered at the given name 
     */
    public SolrRequestHandler get(String handlerName) {
      return handlers.get(normalize(handlerName));
    }

    /**
     * Handlers must be initialized before calling this function.  As soon as this is
     * called, the handler can immediately accept requests.
     * 
     * This call is thread safe.
     * 
     * @return the previous handler at the given path or null
     */
    public SolrRequestHandler register( String handlerName, SolrRequestHandler handler ) {
      String norm = normalize(handlerName);
      if (handler == null) {
        return handlers.remove(norm);
      }
      return handlers.put(norm, handler);
//        return register(handlerName, new PluginRegistry.PluginHolder<>(null, handler));
    }


    /**
     * Returns an unmodifiable Map containing the registered handlers
     */
    public PluginBag<SolrRequestHandler> getRequestHandlers() {
      return handlers;
    }


    /**
     * Read solrconfig.xml and register the appropriate handlers
     * 
     * This function should <b>only</b> be called from the SolrCore constructor.  It is
     * not intended as a public API.
     * 
     * While the normal runtime registration contract is that handlers MUST be initialized
     * before they are registered, this function does not do that exactly.
     *
     * This function registers all handlers first and then calls init() for each one.
     *
     * This is OK because this function is only called at startup and there is no chance that
     * a handler could be asked to handle a request before it is initialized.
     * 
     * The advantage to this approach is that handlers can know what path they are registered
     * to and what other handlers are available at startup.
     * 
     * Handlers will be registered and initialized in the order they appear in solrconfig.xml
     */

    public void initHandlersFromConfig(SolrConfig config) {
      List<PluginInfo> implicits = _arch.OUT_IImplicitPlugins.getHandlers(core);
      // use link map so we iterate in the same order
      Map<String, PluginInfo> infoMap= new LinkedHashMap<>();
      //deduping implicit and explicit requesthandlers
      for (PluginInfo info : implicits) infoMap.put(info.name,info);
      
      _arch.OUT_ISolrXmlRequestHandlerPluginRegister.registerRequestHandlerPluginsFromSolrXml(config, infoMap);
      ArrayList<PluginInfo> infos = new ArrayList<>(infoMap.values());

      List<PluginInfo> modifiedInfos = new ArrayList<>();
      for (PluginInfo info : infos) {
        modifiedInfos.add(applyInitParams(config, info));
      }
      handlers.init(Collections.<String,SolrRequestHandler>emptyMap(),core, modifiedInfos);
      handlers.alias(handlers.getDefault(), "");
      log.info("Registered paths: {}" , StrUtils.join(new ArrayList<>(handlers.keySet()) , ',' ));
      if(!handlers.alias( "/select","")){
        if(!handlers.alias( "standard","")){
          log.warn("no default request handler is registered (either '/select' or 'standard')");
        }
      }
    }

    private PluginInfo applyInitParams(SolrConfig config, PluginInfo info) {
      List<InitParams> ags = new ArrayList<>();
      String p = info.attributes.get(InitParams.TYPE);
      if(p!=null) {
        for (String arg : StrUtils.splitSmart(p, ',')) {
          if(config.getInitParams().containsKey(arg)) ags.add(config.getInitParams().get(arg));
          else log.warn("INVALID paramSet {} in requestHandler {}", arg, info.toString());
        }
      }
      for (InitParams args : config.getInitParams().values())
        if(args.matchPath(info.name)) ags.add(args);
      if(!ags.isEmpty()){
        info = info.copy();
        for (InitParams initParam : ags) {
          initParam.apply(info);
        }
      }
      return info;
    }

    public void close() {
      handlers.close();
    }
  }
}