package comp.SolrIndexWriterFactory;


import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.DirectoryFactory;
import edu.umkc.solr.core.DirectoryFactory.DirContext;
import edu.umkc.solr.schema.IndexSchema;
import edu.umkc.solr.update.SolrIndexConfig;
import edu.umkc.type.ISolrCore;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.lucene.codecs.Codec;
import org.apache.lucene.index.IndexDeletionPolicy;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.InfoStream;
import org.apache.solr.common.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SolrIndexWriterFactoryImp implements ISolrIndexWriterFactoryImp
{
	private SolrIndexWriterFactoryArch _arch;

  public SolrIndexWriterFactoryImp (){
  }

	public void setArch(SolrIndexWriterFactoryArch arch){
		_arch = arch;
	}

	public SolrIndexWriterFactoryArch getArch(){
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
  
  //To be imported: IOException,Codec,IndexDeletionPolicy,DirectoryFactory,IndexSchema,SolrIndexConfig,SolrIndexWriter,ISolrCore
  public SolrIndexWriter createSolrIndexWriter (ISolrCore core, String name, String path, DirectoryFactory directoryFactory, boolean create, IndexSchema schema, SolrIndexConfig config, IndexDeletionPolicy delPolicy, Codec codec) throws IOException {
    return SolrIndexWriter.create(core, name, path, directoryFactory, create, schema, config, delPolicy, codec);
  }
  
  /**
   * An IndexWriter that is configured via Solr config mechanisms.
   *
   * @since solr 0.9
   */

  public static class SolrIndexWriter extends IndexWriter {
    private static Logger log = LoggerFactory.getLogger(SolrIndexWriter.class);
    // These should *only* be used for debugging or monitoring purposes
    public static final AtomicLong numOpens = new AtomicLong();
    public static final AtomicLong numCloses = new AtomicLong();
    
    /** Stored into each Lucene commit to record the
     *  System.currentTimeMillis() when commit was called. */
    public static final String COMMIT_TIME_MSEC_KEY = "commitTimeMSec";

    private final Object CLOSE_LOCK = new Object();
    
    String name;
    private DirectoryFactory directoryFactory;
    private InfoStream infoStream;
    private Directory directory;

    public static SolrIndexWriter create(ISolrCore core, String name, String path, DirectoryFactory directoryFactory, boolean create, IndexSchema schema, SolrIndexConfig config, IndexDeletionPolicy delPolicy, Codec codec) throws IOException {

      SolrIndexWriter w = null;
      final Directory d = directoryFactory.get(path, DirContext.DEFAULT, config.lockType);
      try {
        w = new SolrIndexWriter(core, name, path, d, create, schema, 
                                config, delPolicy, codec);
        w.setDirectoryFactory(directoryFactory);
        return w;
      } finally {
        if (null == w && null != d) { 
          directoryFactory.doneWithDirectory(d);
          directoryFactory.release(d);
        }
      }
    }

    private SolrIndexWriter(ISolrCore core, String name, String path, Directory directory, boolean create, IndexSchema schema, SolrIndexConfig config, IndexDeletionPolicy delPolicy, Codec codec) throws IOException {
      super(directory,
            config.toIndexWriterConfig(core).
            setOpenMode(create ? IndexWriterConfig.OpenMode.CREATE : IndexWriterConfig.OpenMode.APPEND).
            setIndexDeletionPolicy(delPolicy).setCodec(codec)
            );
      log.debug("Opened Writer " + name);
      this.name = name;
      infoStream = getConfig().getInfoStream();
      this.directory = directory;
      numOpens.incrementAndGet();
    }
    
    private void setDirectoryFactory(DirectoryFactory factory) {
      this.directoryFactory = factory;
    }

    /**
     * use DocumentBuilder now...
     * private final void addField(Document doc, String name, String val) {
     * SchemaField ftype = schema.getField(name);
     * <p/>
     * // we don't check for a null val ourselves because a solr.FieldType
     * // might actually want to map it to something.  If createField()
     * // returns null, then we don't store the field.
     * <p/>
     * Field field = ftype.createField(val, boost);
     * if (field != null) doc.add(field);
     * }
     * <p/>
     * <p/>
     * public void addRecord(String[] fieldNames, String[] fieldValues) throws IOException {
     * Document doc = new Document();
     * for (int i=0; i<fieldNames.length; i++) {
     * String name = fieldNames[i];
     * String val = fieldNames[i];
     * <p/>
     * // first null is end of list.  client can reuse arrays if they want
     * // and just write a single null if there is unused space.
     * if (name==null) break;
     * <p/>
     * addField(doc,name,val);
     * }
     * addDocument(doc);
     * }
     * ****
     */
    private volatile boolean isClosed = false;

    @Override
    public void close() throws IOException {
      log.debug("Closing Writer " + name);
      try {
        super.close();
      } catch (Throwable t) {
        if (t instanceof OutOfMemoryError) {
          throw (OutOfMemoryError) t;
        }
        log.error("Error closing IndexWriter", t);
      } finally {
        cleanup();
      }
    }

    @Override
    public void rollback() throws IOException {
      log.debug("Rollback Writer " + name);
      try {
        super.rollback();
      } catch (Throwable t) {
        if (t instanceof OutOfMemoryError) {
          throw (OutOfMemoryError) t;
        }
        log.error("Exception rolling back IndexWriter", t);
      } finally {
        cleanup();
      }
    }

    private void cleanup() throws IOException {
      // It's kind of an implementation detail whether
      // or not IndexWriter#close calls rollback, so
      // we assume it may or may not
      boolean doClose = false;
      synchronized (CLOSE_LOCK) {
        if (!isClosed) {
          doClose = true;
          isClosed = true;
        }
      }
      if (doClose) {
        
        if (infoStream != null) {
          IOUtils.closeQuietly(infoStream);
        }
        numCloses.incrementAndGet();
        directoryFactory.release(directory);
      }
    }

    @Override
    protected void finalize() throws Throwable {
      try {
        if(!isClosed){
          assert false : "SolrIndexWriter was not closed prior to finalize()";
          log.error("SolrIndexWriter was not closed prior to finalize(), indicates a bug -- POSSIBLE RESOURCE LEAK!!!");
          close();
        }
      } finally { 
        super.finalize();
      }
      
    }
  }
}