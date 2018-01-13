package comp.SolrCoreStateFactory;


import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.lucene.index.IndexWriter;
import org.apache.solr.common.SolrException;
import org.apache.solr.common.SolrException.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.cloud.ActionThrottle;
import edu.umkc.solr.cloud.RecoveryStrategy;
import edu.umkc.solr.core.CoreDescriptor;
import edu.umkc.solr.core.DirectoryFactory;
import edu.umkc.solr.update.SolrCoreState;
import comp.SolrIndexWriterFactory.SolrIndexWriterFactoryImp.SolrIndexWriter;
import edu.umkc.solr.util.RefCounted;
import edu.umkc.type.ICoreContainer;
import edu.umkc.type.ISolrCore;

public class SolrCoreStateFactoryImp implements ISolrCoreStateFactoryImp
{
	private static SolrCoreStateFactoryArch _arch;

  public SolrCoreStateFactoryImp (){
  }

	public void setArch(SolrCoreStateFactoryArch arch){
		_arch = arch;
	}

	public SolrCoreStateFactoryArch getArch(){
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
  
  //To be imported: DirectoryFactory,SolrCoreState
  public SolrCoreState createDefaultSolrCoreState (final DirectoryFactory directoryFactory)   {
    return new DefaultSolrCoreState(directoryFactory);
  }
  
  public static final class DefaultSolrCoreState extends SolrCoreState implements RecoveryStrategy.RecoveryListener {
    public static Logger log = LoggerFactory.getLogger(DefaultSolrCoreState.class);
    
    private final boolean SKIP_AUTO_RECOVERY = Boolean.getBoolean("solrcloud.skip.autorecovery");
    
    private final Object recoveryLock = new Object();
    
    private final ActionThrottle recoveryThrottle = new ActionThrottle("recovery", 10000);
    
    private final ActionThrottle leaderThrottle = new ActionThrottle("leader", 5000);
    
    // protects pauseWriter and writerFree
    private final Object writerPauseLock = new Object();
    
    private SolrIndexWriter indexWriter = null;
    private DirectoryFactory directoryFactory;

    private volatile boolean recoveryRunning;
    private RecoveryStrategy recoveryStrat;
    private volatile boolean lastReplicationSuccess = true;

    private RefCounted<IndexWriter> refCntWriter;

    private boolean pauseWriter;
    private boolean writerFree = true;
    
    protected final ReentrantLock commitLock = new ReentrantLock();

    public DefaultSolrCoreState(DirectoryFactory directoryFactory) {
      this.directoryFactory = directoryFactory;
    }
    
    private void closeIndexWriter(IndexWriterCloser closer) {
      try {
        log.info("SolrCoreState ref count has reached 0 - closing IndexWriter");
        if (closer != null) {
          log.info("closing IndexWriter with IndexWriterCloser");
          closer.closeWriter(indexWriter);
        } else if (indexWriter != null) {
          log.info("closing IndexWriter...");
          indexWriter.close();
        }
        indexWriter = null;
      } catch (Exception e) {
        log.error("Error during close of writer.", e);
      } 
    }
    
    @Override
    public RefCounted<IndexWriter> getIndexWriter(ISolrCore core)
        throws IOException {
      synchronized (writerPauseLock) {
        if (closed) {
          throw new SolrException(ErrorCode.SERVICE_UNAVAILABLE, "SolrCoreState already closed");
        }
        
        while (pauseWriter) {
          try {
            writerPauseLock.wait(100);
          } catch (InterruptedException e) {}
          
          if (closed) {
            throw new SolrException(ErrorCode.SERVICE_UNAVAILABLE, "Already closed");
          }
        }
        
        if (core == null) {
          // core == null is a signal to just return the current writer, or null
          // if none.
          initRefCntWriter();
          if (refCntWriter == null) return null;
          writerFree = false;
          writerPauseLock.notifyAll();
          if (refCntWriter != null) refCntWriter.incref();
          
          return refCntWriter;
        }
        
        if (indexWriter == null) {
          indexWriter = createMainIndexWriter(core, "DirectUpdateHandler2");
        }
        initRefCntWriter();
        writerFree = false;
        writerPauseLock.notifyAll();
        refCntWriter.incref();
        return refCntWriter;
      }
    }

    private void initRefCntWriter() {
      if (refCntWriter == null && indexWriter != null) {
        refCntWriter = new RefCounted<IndexWriter>(indexWriter) {
          @Override
          public void close() {
            synchronized (writerPauseLock) {
              writerFree = true;
              writerPauseLock.notifyAll();
            }
          }
        };
      }
    }

    @Override
    public synchronized void newIndexWriter(ISolrCore core, boolean rollback) throws IOException {
      log.info("Creating new IndexWriter...");
      String coreName = core.getName();
      synchronized (writerPauseLock) {
        if (closed) {
          throw new SolrException(ErrorCode.SERVICE_UNAVAILABLE, "Already closed");
        }
        
        // we need to wait for the Writer to fall out of use
        // first lets stop it from being lent out
        pauseWriter = true;
        // then lets wait until it's out of use
        log.info("Waiting until IndexWriter is unused... core=" + coreName);
        
        while (!writerFree) {
          try {
            writerPauseLock.wait(100);
          } catch (InterruptedException e) {}
          
          if (closed) {
            throw new SolrException(ErrorCode.SERVICE_UNAVAILABLE, "SolrCoreState already closed");
          }
        }

        try {
          if (indexWriter != null) {
            if (!rollback) {
              try {
                log.info("Closing old IndexWriter... core=" + coreName);
                indexWriter.close();
              } catch (Exception e) {
                SolrException.log(log, "Error closing old IndexWriter. core="
                    + coreName, e);
              }
            } else {
              try {
                log.info("Rollback old IndexWriter... core=" + coreName);
                indexWriter.rollback();
              } catch (Exception e) {
                SolrException.log(log, "Error rolling back old IndexWriter. core="
                    + coreName, e);
              }
            }
          }
          indexWriter = createMainIndexWriter(core, "DirectUpdateHandler2");
          log.info("New IndexWriter is ready to be used.");
          // we need to null this so it picks up the new writer next get call
          refCntWriter = null;
        } finally {
          
          pauseWriter = false;
          writerPauseLock.notifyAll();
        }
      }
    }
    
    @Override
    public synchronized void closeIndexWriter(ISolrCore core, boolean rollback)
        throws IOException {
      log.info("Closing IndexWriter...");
      String coreName = core.getName();
      synchronized (writerPauseLock) {
        if (closed) {
          throw new SolrException(ErrorCode.SERVICE_UNAVAILABLE, "Already closed");
        }
        
        // we need to wait for the Writer to fall out of use
        // first lets stop it from being lent out
        pauseWriter = true;
        // then lets wait until it's out of use
        log.info("Waiting until IndexWriter is unused... core=" + coreName);
        
        while (!writerFree) {
          try {
            writerPauseLock.wait(100);
          } catch (InterruptedException e) {}
          
          if (closed) {
            throw new SolrException(ErrorCode.SERVICE_UNAVAILABLE,
                "SolrCoreState already closed");
          }
        }
        
        if (indexWriter != null) {
          if (!rollback) {
            try {
              log.info("Closing old IndexWriter... core=" + coreName);
              indexWriter.close();
            } catch (Exception e) {
              SolrException.log(log, "Error closing old IndexWriter. core="
                  + coreName, e);
            }
          } else {
            try {
              log.info("Rollback old IndexWriter... core=" + coreName);
              indexWriter.rollback();
            } catch (Exception e) {
              SolrException.log(log, "Error rolling back old IndexWriter. core="
                  + coreName, e);
            }
          }
        }
        
      }
    }
    
    @Override
    public synchronized void openIndexWriter(ISolrCore core) throws IOException {
      log.info("Creating new IndexWriter...");
      synchronized (writerPauseLock) {
        if (closed) {
          throw new SolrException(ErrorCode.SERVICE_UNAVAILABLE, "Already closed");
        }
        
        try {
          indexWriter = createMainIndexWriter(core, "DirectUpdateHandler2");
          log.info("New IndexWriter is ready to be used.");
          // we need to null this so it picks up the new writer next get call
          refCntWriter = null;
        } finally {
          pauseWriter = false;
          writerPauseLock.notifyAll();
        }
      }
    }

    @Override
    public synchronized void rollbackIndexWriter(ISolrCore core) throws IOException {
      newIndexWriter(core, true);
    }
    
    protected SolrIndexWriter createMainIndexWriter(ISolrCore core, String name) throws IOException {
      return _arch.OUT_ISolrIndexWriterFactory.createSolrIndexWriter(core, name, core.getNewIndexDir(),
          core.getDirectoryFactory(), false, core.getLatestSchema(),
          core.getSolrConfig().indexConfig, core.getDeletionPolicy(), core.getCodec());
    }

    @Override
    public DirectoryFactory getDirectoryFactory() {
      return directoryFactory;
    }

    @Override
    public void doRecovery(ICoreContainer cc, CoreDescriptor cd) {
      if (SKIP_AUTO_RECOVERY) {
        log.warn("Skipping recovery according to sys prop solrcloud.skip.autorecovery");
        return;
      }
      
      // check before we grab the lock
      if (cc.isShutDown()) {
        log.warn("Skipping recovery because Solr is close");
        return;
      }
      
      synchronized (recoveryLock) {
        // to be air tight we must also check after lock
        if (cc.isShutDown()) {
          log.warn("Skipping recovery because Solr is close");
          return;
        }
        log.info("Running recovery - first canceling any ongoing recovery");
        cancelRecovery();
        
        while (recoveryRunning) {
          try {
            recoveryLock.wait(1000);
          } catch (InterruptedException e) {

          }
          // check again for those that were waiting
          if (cc.isShutDown()) {
            log.warn("Skipping recovery because Solr is close");
            return;
          }
          if (closed) return;
        }

        // if true, we are recovering after startup and shouldn't have (or be receiving) additional updates (except for local tlog recovery)
        boolean recoveringAfterStartup = recoveryStrat == null;

        recoveryThrottle.minimumWaitBetweenActions();
        recoveryThrottle.markAttemptingAction();
        
        recoveryStrat = new RecoveryStrategy(cc, cd, this);
        recoveryStrat.setRecoveringAfterStartup(recoveringAfterStartup);
        recoveryStrat.start();
        recoveryRunning = true;
      }
      
    }
    
    @Override
    public void cancelRecovery() {
      synchronized (recoveryLock) {
        if (recoveryStrat != null && recoveryRunning) {
          recoveryStrat.close();
          while (true) {
            try {
              recoveryStrat.join();
            } catch (InterruptedException e) {
              // not interruptible - keep waiting
              continue;
            }
            break;
          }
          
          recoveryRunning = false;
          recoveryLock.notifyAll();
        }
      }
    }

    @Override
    public void recovered() {
      recoveryRunning = false;
    }

    @Override
    public void failed() {
      recoveryRunning = false;
    }

    @Override
    public synchronized void close(IndexWriterCloser closer) {
      closed = true;
      cancelRecovery();
      closeIndexWriter(closer);
    }
    
    @Override
    public Lock getCommitLock() {
      return commitLock;
    }
    
    @Override
    public ActionThrottle getLeaderThrottle() {
      return leaderThrottle;
    }
    
    @Override
    public boolean getLastReplicateIndexSuccess() {
      return lastReplicationSuccess;
    }

    @Override
    public void setLastReplicateIndexSuccess(boolean success) {
      this.lastReplicationSuccess = success;
    }
    
  }
}