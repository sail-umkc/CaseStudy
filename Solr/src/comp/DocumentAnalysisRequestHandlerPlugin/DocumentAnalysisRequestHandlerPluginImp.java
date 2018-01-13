package comp.DocumentAnalysisRequestHandlerPlugin;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.PluginInfo;

import java.util.Map;

import annotation.Feature;
import annotation.Optional;

@Optional(Feature.DOCUMENT_ANALYSIS_REQUEST_HANDLER)
public class DocumentAnalysisRequestHandlerPluginImp implements IDocumentAnalysisRequestHandlerPluginImp
{
	private DocumentAnalysisRequestHandlerPluginArch _arch;

    public DocumentAnalysisRequestHandlerPluginImp (){
    }

	public void setArch(DocumentAnalysisRequestHandlerPluginArch arch){
		_arch = arch;
	}
	public DocumentAnalysisRequestHandlerPluginArch getArch(){
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

	/*
  	  Implementation primitives required by the architecture
	*/
	private final String pluginName = "DocumentAnalysisRequestHandler";
	
    //To be imported: Map,PluginInfo
    public boolean registerDocumentAnalysisRequestHandlerPlugin (final PluginInfo info,final Map<String, PluginInfo> infoMap)   {
      if (info.className.contains(pluginName)) {
        infoMap.put(info.name, info);
        return true;
      }
      
      return false;
    }
}