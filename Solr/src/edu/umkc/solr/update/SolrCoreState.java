package edu.umkc.solr.update;

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.IOException;
import java.util.concurrent.locks.Lock;

import org.apache.lucene.index.IndexWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.umkc.solr.cloud.ActionThrottle;
import edu.umkc.solr.core.CoreDescriptor;
import edu.umkc.solr.core.DirectoryFactory;
import edu.umkc.solr.util.RefCounted;
import edu.umkc.type.ICoreContainer;
import edu.umkc.type.ISolrCore;

/**
 * The state in this class can be easily shared between SolrCores across
 * SolrCore reloads.
 * 
 */
public abstract class SolrCoreState {
  public static Logger log = LoggerFactory.getLogger(SolrCoreState.class);
  
  protected boolean closed = false;
  private final Object deleteLock = new Object();
  
  public Object getUpdateLock() {
    return deleteLock;
  }
  
  private int solrCoreStateRefCnt = 1;

  public void increfSolrCoreState() {
    synchronized (this) {
      if (solrCoreStateRefCnt == 0) {
        throw new IllegalStateException("IndexWriter has been closed");
      }
      solrCoreStateRefCnt++;
    }
  }
  
  public boolean decrefSolrCoreState(IndexWriterCloser closer) {
    boolean close = false;
    synchronized (this) {
      solrCoreStateRefCnt--;
      assert solrCoreStateRefCnt >= 0;
      if (solrCoreStateRefCnt == 0) {
        closed = true;
        close = true;
      }
    }
    
    if (close) {
      try {
        log.info("Closing SolrCoreState");
        close(closer);
      } catch (Exception e) {
        log.error("Error closing SolrCoreState", e);
      }
    }
    return close;
  }
  
  public abstract Lock getCommitLock();
  
  /**
   * Force the creation of a new IndexWriter using the settings from the given
   * SolrCore.
   * 
   * @param rollback close IndexWriter if false, else rollback
   * @throws IOException If there is a low-level I/O error.
   */
  public abstract void newIndexWriter(ISolrCore core, boolean rollback) throws IOException;
  
  
  /**
   * Expert method that closes the IndexWriter - you must call {@link #openIndexWriter(ISolrCore)}
   * in a finally block after calling this method.
   * 
   * @param core that the IW belongs to
   * @param rollback true if IW should rollback rather than close
   * @throws IOException If there is a low-level I/O error.
   */
  public abstract void closeIndexWriter(ISolrCore core, boolean rollback) throws IOException;
  
  /**
   * Expert method that opens the IndexWriter - you must call {@link #closeIndexWriter(ISolrCore, boolean)}
   * first, and then call this method in a finally block.
   * 
   * @param core that the IW belongs to
   * @throws IOException If there is a low-level I/O error.
   */
  public abstract void openIndexWriter(ISolrCore core) throws IOException;
  
  /**
   * Get the current IndexWriter. If a new IndexWriter must be created, use the
   * settings from the given {@link ISolrCore}.
   * 
   * @throws IOException If there is a low-level I/O error.
   */
  public abstract RefCounted<IndexWriter> getIndexWriter(ISolrCore core) throws IOException;
  
  /**
   * Rollback the current IndexWriter. When creating the new IndexWriter use the
   * settings from the given {@link ISolrCore}.
   * 
   * @throws IOException If there is a low-level I/O error.
   */
  public abstract void rollbackIndexWriter(ISolrCore core) throws IOException;
  
  /**
   * @return the {@link DirectoryFactory} that should be used.
   */
  public abstract DirectoryFactory getDirectoryFactory();


  public interface IndexWriterCloser {
    public void closeWriter(IndexWriter writer) throws IOException;
  }

  public abstract void doRecovery(ICoreContainer cc, CoreDescriptor cd);
  
  public abstract void cancelRecovery();

  public abstract void close(IndexWriterCloser closer);

  /**
   * @return throttle to limit how fast a core attempts to become leader
   */
  public abstract ActionThrottle getLeaderThrottle();

  public abstract boolean getLastReplicateIndexSuccess();

  public abstract void setLastReplicateIndexSuccess(boolean success);
}
