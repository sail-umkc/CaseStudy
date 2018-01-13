package comp.DefaultResponseWriters;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.response.QueryResponseWriter;

import edu.umkc.type.tmpl.IBinaryResponseWriter;
import edu.umkc.type.tmpl.ICSVResponseWriter;
import edu.umkc.type.tmpl.IDefaultResponseWriters;
import edu.umkc.type.tmpl.IFileStreamResponseWriter;
import edu.umkc.type.tmpl.IJSONResponseWriter;
import edu.umkc.type.tmpl.IPHPResponseWriter;
import edu.umkc.type.tmpl.IPHPSerializedResponseWriter;
import edu.umkc.type.tmpl.IPythonResponseWriter;
import edu.umkc.type.tmpl.IRawResponseWriter;
import edu.umkc.type.tmpl.IRubyResponseWriter;
import edu.umkc.type.tmpl.ISchemaXMLResponseWriter;
import edu.umkc.type.tmpl.ISortingResponseWriter;
import edu.umkc.type.tmpl.IXMLResponseWriter;

import java.util.Map;

public class DefaultResponseWritersArch extends AbstractMyxSimpleBrick implements IDefaultResponseWriters
{
    public static final IMyxName msg_IXMLResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.IXMLResponseWriter");
    public static final IMyxName msg_IPHPResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.IPHPResponseWriter");
    public static final IMyxName msg_IJSONResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.IJSONResponseWriter");
    public static final IMyxName msg_IPythonResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.IPythonResponseWriter");
    public static final IMyxName msg_IPHPSerializedResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.IPHPSerializedResponseWriter");
    public static final IMyxName msg_IBinaryResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.IBinaryResponseWriter");
    public static final IMyxName msg_IRubyResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.IRubyResponseWriter");
    public static final IMyxName msg_IRawResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.IRawResponseWriter");
    public static final IMyxName msg_ICSVResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.ICSVResponseWriter");
    public static final IMyxName msg_IFileStreamResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.IFileStreamResponseWriter");
    public static final IMyxName msg_ISortingResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.ISortingResponseWriter");
    public static final IMyxName msg_ISchemaXMLResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.ISchemaXMLResponseWriter");
    public static final IMyxName msg_IDefaultResponseWriters = MyxUtils.createName("edu.umkc.type.tmpl.IDefaultResponseWriters");

    public IXMLResponseWriter OUT_IXMLResponseWriter;
    public IPHPResponseWriter OUT_IPHPResponseWriter;
    public IJSONResponseWriter OUT_IJSONResponseWriter;
    public IPythonResponseWriter OUT_IPythonResponseWriter;
    public IPHPSerializedResponseWriter OUT_IPHPSerializedResponseWriter;
    public IBinaryResponseWriter OUT_IBinaryResponseWriter;
    public IRubyResponseWriter OUT_IRubyResponseWriter;
    public IRawResponseWriter OUT_IRawResponseWriter;
    public ICSVResponseWriter OUT_ICSVResponseWriter;
    public IFileStreamResponseWriter OUT_IFileStreamResponseWriter;
    public ISortingResponseWriter OUT_ISortingResponseWriter;
    public ISchemaXMLResponseWriter OUT_ISchemaXMLResponseWriter;

	private IDefaultResponseWritersImp _imp;

    public DefaultResponseWritersArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IDefaultResponseWritersImp getImplementation(){
        try{
			return new DefaultResponseWritersImp();    
        } catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public void init(){
        _imp.init();
    }
    
    public void begin(){
        OUT_IXMLResponseWriter = (IXMLResponseWriter) MyxUtils.getFirstRequiredServiceObject(this,msg_IXMLResponseWriter);
        if (OUT_IXMLResponseWriter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IXMLResponseWriter returned null");
			return;       
        }
        OUT_IPHPResponseWriter = (IPHPResponseWriter) MyxUtils.getFirstRequiredServiceObject(this,msg_IPHPResponseWriter);
        if (OUT_IPHPResponseWriter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IPHPResponseWriter returned null");
			return;       
        }
        OUT_IJSONResponseWriter = (IJSONResponseWriter) MyxUtils.getFirstRequiredServiceObject(this,msg_IJSONResponseWriter);
        if (OUT_IJSONResponseWriter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IJSONResponseWriter returned null");
			return;       
        }
        OUT_IPythonResponseWriter = (IPythonResponseWriter) MyxUtils.getFirstRequiredServiceObject(this,msg_IPythonResponseWriter);
        if (OUT_IPythonResponseWriter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IPythonResponseWriter returned null");
			return;       
        }
        OUT_IPHPSerializedResponseWriter = (IPHPSerializedResponseWriter) MyxUtils.getFirstRequiredServiceObject(this,msg_IPHPSerializedResponseWriter);
        if (OUT_IPHPSerializedResponseWriter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IPHPSerializedResponseWriter returned null");
			return;       
        }
        OUT_IBinaryResponseWriter = (IBinaryResponseWriter) MyxUtils.getFirstRequiredServiceObject(this,msg_IBinaryResponseWriter);
        if (OUT_IBinaryResponseWriter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IBinaryResponseWriter returned null");
			return;       
        }
        OUT_IRubyResponseWriter = (IRubyResponseWriter) MyxUtils.getFirstRequiredServiceObject(this,msg_IRubyResponseWriter);
        if (OUT_IRubyResponseWriter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IRubyResponseWriter returned null");
			return;       
        }
        OUT_IRawResponseWriter = (IRawResponseWriter) MyxUtils.getFirstRequiredServiceObject(this,msg_IRawResponseWriter);
        if (OUT_IRawResponseWriter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IRawResponseWriter returned null");
			return;       
        }
        OUT_ICSVResponseWriter = (ICSVResponseWriter) MyxUtils.getFirstRequiredServiceObject(this,msg_ICSVResponseWriter);
        if (OUT_ICSVResponseWriter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ICSVResponseWriter returned null");
			return;       
        }
        OUT_IFileStreamResponseWriter = (IFileStreamResponseWriter) MyxUtils.getFirstRequiredServiceObject(this,msg_IFileStreamResponseWriter);
        if (OUT_IFileStreamResponseWriter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.IFileStreamResponseWriter returned null");
			return;       
        }
        OUT_ISortingResponseWriter = (ISortingResponseWriter) MyxUtils.getFirstRequiredServiceObject(this,msg_ISortingResponseWriter);
        if (OUT_ISortingResponseWriter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISortingResponseWriter returned null");
			return;       
        }
        OUT_ISchemaXMLResponseWriter = (ISchemaXMLResponseWriter) MyxUtils.getFirstRequiredServiceObject(this,msg_ISchemaXMLResponseWriter);
        if (OUT_ISchemaXMLResponseWriter == null){
 			System.err.println("Error: Interface edu.umkc.type.tmpl.ISchemaXMLResponseWriter returned null");
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
		if (arg0.equals(msg_IDefaultResponseWriters)){
			return this;
		}        
		return null;
	}
  
    //To be imported: Map,QueryResponseWriter
    public Map<String, QueryResponseWriter> getDefaultResponseWriters ()   {
		return _imp.getDefaultResponseWriters();
    }    
}