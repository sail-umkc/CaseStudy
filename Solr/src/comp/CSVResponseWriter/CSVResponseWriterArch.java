package comp.CSVResponseWriter;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.request.SolrQueryRequest;

import edu.umkc.solr.response.QueryResponseWriter;
import edu.umkc.solr.response.SolrQueryResponse;

import edu.umkc.type.tmpl.ICSVResponseWriter;

import java.io.IOException;
import java.io.Writer;

import org.apache.solr.common.util.NamedList;

public class CSVResponseWriterArch extends AbstractMyxSimpleBrick implements ICSVResponseWriter
{
    public static final IMyxName msg_ICSVResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.ICSVResponseWriter");


	private ICSVResponseWriterImp _imp;

    public CSVResponseWriterArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected ICSVResponseWriterImp getImplementation(){
        try{
			return new CSVResponseWriterImp();    
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
		if (arg0.equals(msg_ICSVResponseWriter)){
			return this;
		}        
		return null;
	}
  
    //To be imported: IOException,Writer,NamedList,SolrQueryRequest,QueryResponseWriter,SolrQueryResponse
    public void write (Writer writer,SolrQueryRequest request,SolrQueryResponse response) throws IOException {
		_imp.write(writer,request,response);
    }    
    public String getContentType (SolrQueryRequest request,SolrQueryResponse response)   {
		return _imp.getContentType(request,response);
    }    
    public void init (NamedList args)   {
		_imp.init(args);
    }    
}