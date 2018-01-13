package edu.umkc.type;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;

import edu.umkc.solr.cloud.ZkController;
import edu.umkc.solr.core.CoreDescriptor;
import edu.umkc.solr.core.CoresLocator;
import edu.umkc.solr.core.JarRepository;
import edu.umkc.solr.core.NodeConfig;
import edu.umkc.solr.core.PluginBag;
import edu.umkc.solr.handler.admin.CollectionsHandler;
import edu.umkc.solr.handler.admin.CoreAdminHandler;
import edu.umkc.solr.handler.admin.InfoHandler;
import edu.umkc.solr.handler.component.ShardHandlerFactory;
import edu.umkc.solr.logging.LogWatcher;
import edu.umkc.solr.request.SolrRequestHandler;
import edu.umkc.solr.security.AuthenticationPlugin;
import edu.umkc.solr.security.AuthorizationPlugin;
import edu.umkc.solr.update.UpdateShardHandler;
import edu.umkc.type.tmpl.CoreLoadFailure;

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

public interface ICoreContainer
{
  public ExecutorService getCoreZkRegisterExecutorService();

  public SolrRequestHandler getRequestHandler(String path);

  public PluginBag<SolrRequestHandler> getRequestHandlers();

  public Properties getContainerProperties();

  //-------------------------------------------------------------------
  // Initialization / Cleanup
  //-------------------------------------------------------------------

  /**
   * Load the cores defined for this CoreContainer
   */
  public void load();
  
  public boolean isShutDown();

  /**
   * Stops all cores.
   */
  public void shutdown();
  
  public void cancelCoreRecoveries();
  
  public CoresLocator getCoresLocator();

  /**
   * Creates a new core based on a CoreDescriptor, publishing the core state to the cluster
   * @param cd the CoreDescriptor
   * @return the newly created core
   */
  public ISolrCore create(CoreDescriptor cd);

  /**
   * Creates a new core based on a CoreDescriptor.
   *
   * @param dcore        a core descriptor
   * @param publishState publish core state to the cluster if true
   *
   * @return the newly created core
   */
  public ISolrCore create(CoreDescriptor dcore, boolean publishState);

  /**
   * @return a Collection of registered SolrCores
   */
  public Collection<ISolrCore> getCores();

  /**
   * @return a Collection of the names that cores are mapped to
   */
  public Collection<String> getCoreNames();

  /** This method is currently experimental.
   * @return a Collection of the names that a specific core is mapped to.
   */
  public Collection<String> getCoreNames(ISolrCore core);

  /**
   * get a list of all the cores that are currently loaded
   * @return a list of al lthe available core names in either permanent or transient core lists.
   */
  public Collection<String> getAllCoreNames();

  /**
   * Returns an immutable Map of Exceptions that occured when initializing 
   * SolrCores (either at startup, or do to runtime requests to create cores) 
   * keyed off of the name (String) of the SolrCore that had the Exception 
   * during initialization.
   * <p>
   * While the Map returned by this method is immutable and will not change 
   * once returned to the client, the source data used to generate this Map 
   * can be changed as various SolrCore operations are performed:
   * </p>
   * <ul>
   *  <li>Failed attempts to create new SolrCores will add new Exceptions.</li>
   *  <li>Failed attempts to re-create a SolrCore using a name already contained in this Map will replace the Exception.</li>
   *  <li>Failed attempts to reload a SolrCore will cause an Exception to be added to this list -- even though the existing SolrCore with that name will continue to be available.</li>
   *  <li>Successful attempts to re-created a SolrCore using a name already contained in this Map will remove the Exception.</li>
   *  <li>Registering an existing SolrCore with a name already contained in this Map (ie: ALIAS or SWAP) will remove the Exception.</li>
   * </ul>
   */
  public Map<String, CoreLoadFailure> getCoreInitFailures();


  // ---------------- Core name related methods --------------- 
  /**
   * Recreates a SolrCore.
   * While the new core is loading, requests will continue to be dispatched to
   * and processed by the old core
   * 
   * @param name the name of the SolrCore to reload
   */
  public void reload(String name);

  /**
   * Swaps two SolrCore descriptors.
   */
  public void swap(String n0, String n1);

  /**
   * Unload a core from this container, leaving all files on disk
   * @param name the name of the core to unload
   */
  public void unload(String name);
  /**
   * Unload a core from this container, optionally removing the core's data and configuration
   *
   * @param name the name of the core to unload
   * @param deleteIndexDir if true, delete the core's index on close
   * @param deleteDataDir if true, delete the core's data directory on close
   * @param deleteInstanceDir if true, delete the core's instance directory on close
   */
  public void unload(String name, boolean deleteIndexDir, boolean deleteDataDir, boolean deleteInstanceDir);

  public void rename(String name, String toName);

  /**
   * Get the CoreDescriptors for all cores managed by this container
   * @return a List of CoreDescriptors
   */
  public List<CoreDescriptor> getCoreDescriptors();

  public CoreDescriptor getCoreDescriptor(String coreName);

  public String getCoreRootDirectory();

  public ISolrCore getCore(String name);

  public JarRepository getJarRepository();

  // ---------------- CoreContainer request handlers --------------

  public String getHostName();

  /**
   * Gets the alternate path for multicore handling:
   * This is used in case there is a registered unnamed core (aka name is "") to
   * declare an alternate way of accessing named cores.
   * This can also be used in a pseudo single-core environment so admins can prepare
   * a new version before swapping.
   */
  public String getManagementPath();

  public LogWatcher getLogging();

  /**
   * Determines whether the core is already loaded or not but does NOT load the core
   *
   */
  public boolean isLoaded(String name);

  public boolean isLoadedNotPendingClose(String name);

  /**
   * Gets a solr core descriptor for a core that is not loaded. Note that if the caller calls this on a
   * loaded core, the unloaded descriptor will be returned.
   *
   * @param cname - name of the unloaded core descriptor to load. NOTE:
   * @return a coreDescriptor. May return null
   */
  public CoreDescriptor getUnloadedCoreDescriptor(String cname);

  public String getSolrHome();

  public boolean isZooKeeperAware();
  
  public ZkController getZkController();
  
  public NodeConfig getConfig();

  /** The default ShardHandlerFactory used to communicate with other solr instances */
  public ShardHandlerFactory getShardHandlerFactory();
  
  public UpdateShardHandler getUpdateShardHandler();

  public ISolrResourceLoader getResourceLoader();

  public AuthorizationPlugin getAuthorizationPlugin();

  public AuthenticationPlugin getAuthenticationPlugin();
}
