package comp.FileStreamResponseWriter;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.handler.ReplicationHandler;
import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.type.tmpl.IFileStreamResponseWriter;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.apache.solr.client.solrj.impl.BinaryResponseParser;
import org.apache.solr.common.util.NamedList;

import comp.SolrCoreFactory.SolrCoreFactoryImp.SolrCore.RawWriter;

import annotation.Feature;
import annotation.Optional;


@Optional(Feature.FILESTREAM_RESPONSE)
public class FileStreamResponseWriterImp implements IFileStreamResponseWriterImp, IFileStreamResponseWriter
{
	private FileStreamResponseWriterArch _arch;

	public FileStreamResponseWriterImp (){
  }
	
  public void setArch(FileStreamResponseWriterArch arch){
		_arch = arch;
	}
	
  public FileStreamResponseWriterArch getArch(){
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
  
  @Override
  public void write(Writer writer, SolrQueryRequest request, SolrQueryResponse response) throws IOException {
    throw new RuntimeException("This is a binary writer , Cannot write to a characterstream");
  }

  @Override
  public void write(OutputStream out, SolrQueryRequest req, SolrQueryResponse response) throws IOException {
    RawWriter rawWriter = (RawWriter) response.getValues().get(ReplicationHandler.FILE_STREAM);
    if(rawWriter!=null) rawWriter.write(out);
  }

  @Override
  public String getContentType(SolrQueryRequest request, SolrQueryResponse response) {
    return BinaryResponseParser.BINARY_CONTENT_TYPE;
  }
  @Override
  public void init(NamedList args) {
    /* NOOP */
  }
}