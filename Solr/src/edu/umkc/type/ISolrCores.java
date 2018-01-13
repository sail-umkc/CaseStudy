package edu.umkc.type;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import edu.umkc.solr.core.CoreDescriptor;
import edu.umkc.solr.core.NodeConfig;

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

public interface ISolrCores
{

  // Trivial helper method for load, note it implements LRU on transient cores. Also note, if
  // there is no setting for max size, nothing is done and all cores go in the regular "cores" list
  public void allocateLazyCores(final int cacheSize, final ISolrResourceLoader loader);

  public void putDynamicDescriptor(String rawName, CoreDescriptor p);

  // We are shutting down. You can't hold the lock on the various lists of cores while they shut down, so we need to
  // make a temporary copy of the names and shut them down outside the lock.
  public void close();

  //WARNING! This should be the _only_ place you put anything into the list of transient cores!
  public ISolrCore putTransientCore(NodeConfig cfg, String name, ISolrCore core, ISolrResourceLoader loader);

  public ISolrCore putCore(String name, ISolrCore core);

  public List<ISolrCore> getCores();

  public Set<String> getCoreNames();

  public List<String> getCoreNames(ISolrCore core);

  /**
   * Gets a list of all cores, loaded and unloaded (dynamic)
   *
   * @return all cores names, whether loaded or unloaded.
   */
  public Collection<String> getAllCoreNames();

  public void swap(String n0, String n1);

  public ISolrCore remove(String name);

  /* If you don't increment the reference count, someone could close the core before you use it. */
  public ISolrCore  getCoreFromAnyList(String name, boolean incRefCount);

  public CoreDescriptor getDynamicDescriptor(String name);

  // See SOLR-5366 for why the UNLOAD command needs to know whether a core is actually loaded or not, it might have
  // to close the core. However, there's a race condition. If the core happens to be in the pending "to close" queue,
  // we should NOT close it in unload core.
  public boolean isLoadedNotPendingClose(String name);

  public boolean isLoaded(String name);

  public CoreDescriptor getUnloadedCoreDescriptor(String cname);

  // Wait here until any pending operations (load, unload or reload) are completed on this core.
  public ISolrCore waitAddPendingCoreOps(String name);

  // We should always be removing the first thing in the list with our name! The idea here is to NOT do anything n
  // any core while some other operation is working on that core.
  public void removeFromPendingOps(String name);

  public Object getModifyLock();

  // Be a little careful. We don't want to either open or close a core unless it's _not_ being opened or closed by
  // another thread. So within this lock we'll walk along the list of pending closes until we find something NOT in
  // the list of threads currently being loaded or reloaded. The "usual" case will probably return the very first
  // one anyway..
  public ISolrCore getCoreToClose();

  public void addCreated(ISolrCore core);

  /**
   * Return the CoreDescriptor corresponding to a given core name.
   * @param coreName the name of the core
   * @return the CoreDescriptor
   */
  public CoreDescriptor getCoreDescriptor(String coreName);

  /**
   * Get the CoreDescriptors for every SolrCore managed here
   * @return a List of CoreDescriptors
   */
  public List<CoreDescriptor> getCoreDescriptors();
}
