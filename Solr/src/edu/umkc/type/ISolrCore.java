package edu.umkc.type;

import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.lucene.codecs.Codec;
import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;

import edu.umkc.solr.core.CloseHook;
import edu.umkc.solr.core.ConfigSet;
import edu.umkc.solr.core.CoreDescriptor;
import edu.umkc.solr.core.DirectoryFactory;
import edu.umkc.solr.core.IndexDeletionPolicyWrapper;
import edu.umkc.solr.core.IndexReaderFactory;
import edu.umkc.solr.core.MemClassLoader;
import edu.umkc.solr.core.PluginBag;
import edu.umkc.solr.core.PluginInfo;
import edu.umkc.solr.core.SolrConfig;
import edu.umkc.solr.core.SolrEventListener;
import edu.umkc.solr.core.SolrInfoMBean;
import edu.umkc.solr.handler.component.SearchComponent;
import edu.umkc.solr.request.SolrQueryRequest;
import edu.umkc.solr.request.SolrRequestHandler;
import edu.umkc.solr.response.QueryResponseWriter;
import edu.umkc.solr.response.SolrQueryResponse;
import edu.umkc.solr.response.transform.TransformerFactory;
import edu.umkc.solr.rest.RestManager;
import edu.umkc.solr.schema.IndexSchema;
import edu.umkc.solr.search.QParserPlugin;
import comp.SolrIndexSearcherFactory.SolrIndexSearcherFactoryImp.SolrIndexSearcher;
import edu.umkc.solr.search.ValueSourceParser;
import edu.umkc.solr.search.stats.StatsCache;
import edu.umkc.solr.update.SolrCoreState;
import edu.umkc.solr.update.UpdateHandler;
import edu.umkc.solr.update.processor.UpdateRequestProcessorChain;
import edu.umkc.solr.update.processor.UpdateRequestProcessorFactory;
import edu.umkc.solr.util.RefCounted;

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

public interface ISolrCore extends SolrInfoMBean, Closeable 
{

  /**
   * The SolrResourceLoader used to load all resources for this core.
   * @since solr 1.3
   */
  public ISolrResourceLoader getResourceLoader();

  public long getStartTime();
  
  public RestManager getRestManager();
  
  /**
   * Gets the configuration resource name used by this core instance.
   * @since solr 1.3
   */
  public String getConfigResource();

  /**
   * Gets the configuration object used by this core instance.
   */
  public SolrConfig getSolrConfig();

  /**
   * Gets the schema resource name used by this core instance.
   * @since solr 1.3
   */
  public String getSchemaResource();

  /** @return the latest snapshot of the schema used by this core instance. */
  public IndexSchema getLatestSchema();

  /** Sets the latest schema snapshot to be used by this core instance. */
  public void setLatestSchema(IndexSchema replacementSchema);

  public String getDataDir();

  public String getUlogDir();

  public String getIndexDir();


  /**
   * Returns the indexdir as given in index.properties. If index.properties exists in dataDir and
   * there is a property <i>index</i> available and it points to a valid directory
   * in dataDir that is returned Else dataDir/index is returned. Only called for creating new indexSearchers
   * and indexwriters. Use the getIndexDir() method to know the active index directory
   *
   * @return the indexdir as given in index.properties
   */
  public String getNewIndexDir();


  public DirectoryFactory getDirectoryFactory();

  public IndexReaderFactory getIndexReaderFactory();

  public String getName();

  public void setName(String v);

  public String getLogId();

  /**
   * Returns a Map of name vs SolrInfoMBean objects. The returned map is an instance of
   * a ConcurrentHashMap and therefore no synchronization is needed for putting, removing
   * or iterating over it.
   *
   * @return the Info Registry map which contains SolrInfoMBean objects keyed by name
   * @since solr 1.3
   */
  public Map<String, SolrInfoMBean> getInfoRegistry();

  public void registerFirstSearcherListener( SolrEventListener listener );

  public void registerNewSearcherListener( SolrEventListener listener );

  public QueryResponseWriter registerResponseWriter( String name, QueryResponseWriter responseWriter );

  public ISolrCore reload(ConfigSet coreConfig) throws IOException;

  public <T extends Object> T createInitInstance(PluginInfo info, Class<T> cast, String msg, String defClassName);


  public void seedVersionBuckets();

  /**
   * Get the StatsCache.
   */
  public StatsCache getStatsCache();

  public SolrCoreState getSolrCoreState();

  /**
   * @return an update processor registered to the given name.  Throw an exception if this chain is undefined
   */
  public UpdateRequestProcessorChain getUpdateProcessingChain( final String name );

  public UpdateRequestProcessorChain getUpdateProcessorChain(SolrParams params);

  public PluginBag<UpdateRequestProcessorFactory> getUpdateProcessors();

  /** expert: increments the core reference count */
  public void open();

  /**
   * Close all resources allocated by the core if it is no longer in use...
   * <ul>
   *   <li>searcher</li>
   *   <li>updateHandler</li>
   *   <li>all CloseHooks will be notified</li>
   *   <li>All MBeans will be unregistered from MBeanServer if JMX was enabled
   *       </li>
   * </ul>
   * <p>
   * The behavior of this method is determined by the result of decrementing
   * the core's reference count (A core is created with a reference count of 1)...
   * </p>
   * <ul>
   *   <li>If reference count is &gt; 0, the usage count is decreased by 1 and no
   *       resources are released.
   *   </li>
   *   <li>If reference count is == 0, the resources are released.
   *   <li>If reference count is &lt; 0, and error is logged and no further action
   *       is taken.
   *   </li>
   * </ul>
   * @see #isClosed()
   */
  public void close();

  /** Current core usage count. */
  public int getOpenCount();

  /** Whether this core is closed. */
  public boolean isClosed();

   /**
    * Add a close callback hook
    */
   public void addCloseHook( CloseHook hook );


  ////////////////////////////////////////////////////////////////////////////////
  // Request Handler
  ////////////////////////////////////////////////////////////////////////////////

  /**
   * Get the request handler registered to a given name.
   *
   * This function is thread safe.
   */
  public SolrRequestHandler getRequestHandler(String handlerName);

  /**
   * Returns an unmodifiable Map containing the registered handlers
   */
  public PluginBag<SolrRequestHandler> getRequestHandlers();


  /**
   * Registers a handler at the specified location.  If one exists there, it will be replaced.
   * To remove a handler, register <code>null</code> at its path
   *
   * Once registered the handler can be accessed through:
   * <pre>
   *   http://${host}:${port}/${context}/${handlerName}
   * or:
   *   http://${host}:${port}/${context}/select?qt=${handlerName}
   * </pre>
   *
   * Handlers <em>must</em> be initialized before getting registered.  Registered
   * handlers can immediately accept requests.
   *
   * This call is thread safe.
   *
   * @return the previous <code>SolrRequestHandler</code> registered to this name <code>null</code> if none.
   */
  public SolrRequestHandler registerRequestHandler(String handlerName, SolrRequestHandler handler);

  /**
   * @return a Search Component registered to a given name.  Throw an exception if the component is undefined
   */
  public SearchComponent getSearchComponent(String name);

  /**
   * Accessor for all the Search Components
   * @return An unmodifiable Map of Search Components
   */
  public PluginBag<SearchComponent> getSearchComponents();

  ////////////////////////////////////////////////////////////////////////////////
  // Update Handler
  ////////////////////////////////////////////////////////////////////////////////

  /**
   * RequestHandlers need access to the updateHandler so they can all talk to the
   * same RAM indexer.
   */
  public UpdateHandler getUpdateHandler();

  ////////////////////////////////////////////////////////////////////////////////
  // Searcher Control
  ////////////////////////////////////////////////////////////////////////////////


  /**
  * Return a registered {@link RefCounted}&lt;{@link SolrIndexSearcher}&gt; with
  * the reference count incremented.  It <b>must</b> be decremented when no longer needed.
  * This method should not be called from SolrCoreAware.inform() since it can result
  * in a deadlock if useColdSearcher==false.
  * If handling a normal request, the searcher should be obtained from
   * {@link edu.umkc.solr.request.SolrQueryRequest#getSearcher()} instead.
  */
  public RefCounted<SolrIndexSearcher> getSearcher();

  /**
  * Returns the current registered searcher with its reference count incremented, or null if none are registered.
  */
  public RefCounted<SolrIndexSearcher> getRegisteredSearcher();

  /**
   * Return the newest normal {@link RefCounted}&lt;{@link SolrIndexSearcher}&gt; with
   * the reference count incremented.  It <b>must</b> be decremented when no longer needed.
   * If no searcher is currently open, then if openNew==true a new searcher will be opened,
   * or null is returned if openNew==false.
   */
  public RefCounted<SolrIndexSearcher> getNewestSearcher(boolean openNew);

  /** Gets the latest real-time searcher w/o forcing open a new searcher if one already exists.
   * The reference count will be incremented.
   */
  public RefCounted<SolrIndexSearcher> getRealtimeSearcher();


  public RefCounted<SolrIndexSearcher> getSearcher(boolean forceNew, boolean returnSearcher, final Future[] waitSearcher);

  /** Opens a new searcher and returns a RefCounted&lt;SolrIndexSearcher&gt; with its reference incremented.
   *
   * "realtime" means that we need to open quickly for a realtime view of the index, hence don't do any
   * autowarming and add to the _realtimeSearchers queue rather than the _searchers queue (so it won't
   * be used for autowarming by a future normal searcher).  A "realtime" searcher will currently never
   * become "registered" (since it currently lacks caching).
   *
   * realtimeSearcher is updated to the latest opened searcher, regardless of the value of "realtime".
   *
   * This method acquires openSearcherLock - do not call with searckLock held!
   */
  public RefCounted<SolrIndexSearcher>  openNewSearcher(boolean updateHandlerReopens, boolean realtime);

  /**
   * Get a {@link SolrIndexSearcher} or start the process of creating a new one.
   * <p>
   * The registered searcher is the default searcher used to service queries.
   * A searcher will normally be registered after all of the warming
   * and event handlers (newSearcher or firstSearcher events) have run.
   * In the case where there is no registered searcher, the newly created searcher will
   * be registered before running the event handlers (a slow searcher is better than no searcher).
   *
   * <p>
   * These searchers contain read-only IndexReaders. To access a non read-only IndexReader,
   * see newSearcher(String name, boolean readOnly).
   *
   * <p>
   * If <tt>forceNew==true</tt> then
   *  A new searcher will be opened and registered regardless of whether there is already
   *    a registered searcher or other searchers in the process of being created.
   * <p>
   * If <tt>forceNew==false</tt> then:<ul>
   *   <li>If a searcher is already registered, that searcher will be returned</li>
   *   <li>If no searcher is currently registered, but at least one is in the process of being created, then
   * this call will block until the first searcher is registered</li>
   *   <li>If no searcher is currently registered, and no searchers in the process of being registered, a new
   * searcher will be created.</li>
   * </ul>
   * <p>
   * If <tt>returnSearcher==true</tt> then a {@link RefCounted}&lt;{@link SolrIndexSearcher}&gt; will be returned with
   * the reference count incremented.  It <b>must</b> be decremented when no longer needed.
   * <p>
   * If <tt>waitSearcher!=null</tt> and a new {@link SolrIndexSearcher} was created,
   * then it is filled in with a Future that will return after the searcher is registered.  The Future may be set to
   * <tt>null</tt> in which case the SolrIndexSearcher created has already been registered at the time
   * this method returned.
   * <p>
   * @param forceNew             if true, force the open of a new index searcher regardless if there is already one open.
   * @param returnSearcher       if true, returns a {@link SolrIndexSearcher} holder with the refcount already incremented.
   * @param waitSearcher         if non-null, will be filled in with a {@link Future} that will return after the new searcher is registered.
   * @param updateHandlerReopens if true, the UpdateHandler will be used when reopening a {@link SolrIndexSearcher}.
   */
  public RefCounted<SolrIndexSearcher> getSearcher(boolean forceNew, boolean returnSearcher, final Future[] waitSearcher, boolean updateHandlerReopens);

  public boolean isReloaded();




  public void closeSearcher();

  public void execute(SolrRequestHandler handler, SolrQueryRequest req, SolrQueryResponse rsp);

  public PluginBag<QueryResponseWriter> getResponseWriters();

  public MemClassLoader getMemClassLoader();

//  public interface RawWriter {
//    public void write(OutputStream os) throws IOException ;
//  }

  /** Finds a writer by name, or returns the default writer if not found. */
  public QueryResponseWriter getQueryResponseWriter(String writerName);

  /** Returns the appropriate writer for a request. If the request specifies a writer via the
   * 'wt' parameter, attempts to find that one; otherwise return the default writer.
   */
  public QueryResponseWriter getQueryResponseWriter(SolrQueryRequest request);



  public QParserPlugin getQueryPlugin(String parserName);

  public TransformerFactory getTransformerFactory(String name);

  public void addTransformerFactory(String name, TransformerFactory factory);

  public <T> T initPlugins(List<PluginInfo> pluginInfos, Map<String, T> registry, Class<T> type, String defClassName);

  /**For a given List of PluginInfo return the instances as a List
   * @param defClassName The default classname if PluginInfo#className == null
   * @return The instances initialized
   */
  public <T> List<T> initPlugins(List<PluginInfo> pluginInfos, Class<T> type, String defClassName);

  /**
   *
   * @param registry The map to which the instance should be added to. The key is the name attribute
   * @param type The type of the Plugin. These should be standard ones registered by type.getName() in SolrConfig
   * @return     The default if any
   */
  public <T> T initPlugins(Map<String, T> registry, Class<T> type);

  public ValueSourceParser getValueSourceParser(String parserName);

  public CoreDescriptor getCoreDescriptor();

  public IndexDeletionPolicyWrapper getDeletionPolicy();

  public ReentrantLock getRuleExpiryLock();

  /////////////////////////////////////////////////////////////////////
  // SolrInfoMBean stuff: Statistics and Module Info
  /////////////////////////////////////////////////////////////////////

  public String getVersion();

  public String getDescription();

  public Category getCategory();

  public String getSource();

  public URL[] getDocs();

  public NamedList getStatistics();

  public Codec getCodec();

  public void unloadOnClose(boolean deleteIndexDir, boolean deleteDataDir, boolean deleteInstanceDir);


  /**Register to notify for any file change in the conf directory.
   * If the file change results in a core reload , then the listener
   * is not fired
   */
  public void addConfListener(Runnable runnable);

  /**Remove a listener
   * */
  public boolean removeConfListener(Runnable runnable);

  public void registerInfoBean(String name, SolrInfoMBean solrInfoMBean);
}
