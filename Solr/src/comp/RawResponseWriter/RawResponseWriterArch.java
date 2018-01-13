package comp.RawResponseWriter;


import edu.uci.isr.myx.fw.AbstractMyxSimpleBrick;
import edu.uci.isr.myx.fw.IMyxName;
import edu.uci.isr.myx.fw.MyxUtils;

import edu.umkc.solr.request.SolrQueryRequest;

import edu.umkc.solr.response.BinaryQueryResponseWriter;
import edu.umkc.solr.response.SolrQueryResponse;

import edu.umkc.type.tmpl.IRawResponseWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.apache.solr.common.util.NamedList;

public class RawResponseWriterArch extends AbstractMyxSimpleBrick implements IRawResponseWriter
{
    public static final IMyxName msg_IRawResponseWriter = MyxUtils.createName("edu.umkc.type.tmpl.IRawResponseWriter");


	private IRawResponseWriterImp _imp;

    public RawResponseWriterArch (){
		_imp = getImplementation();
		if (_imp != null){
			_imp.setArch(this);
		} else {
			System.exit(1);
		}
	}
    
    protected IRawResponseWriterImp getImplementation(){
        try{
			return new RawResponseWriterImp();    
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
		if (arg0.equals(msg_IRawResponseWriter)){
			return this;
		}        
		return null;
	}
  
    //To be imported: IOException,OutputStream,Writer,NamedList,SolrQueryRequest,BinaryQueryResponseWriter,SolrQueryResponse
    public void write (Writer writer,SolrQueryRequest request,SolrQueryResponse response) throws IOException {
		_imp.write(writer,request,response);
    }    
    public String getContentType (SolrQueryRequest request,SolrQueryResponse response)   {
		return _imp.getContentType(request,response);
    }    
    public void init (NamedList args)   {
		_imp.init(args);
    }    
    public void write (OutputStream out,SolrQueryRequest request,SolrQueryResponse response) throws IOException {
		_imp.write(out,request,response);
    }    
}