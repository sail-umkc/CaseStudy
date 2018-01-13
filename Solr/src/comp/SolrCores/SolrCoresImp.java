package comp.SolrCores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.solr.common.SolrException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import comp.CoreContainer.CoreContainerImp;
import edu.umkc.config.Bootstrapper;
import edu.umkc.solr.core.CoreDescriptor;
import edu.umkc.solr.core.NodeConfig;
import edu.umkc.type.ICoreContainer;
import edu.umkc.type.ISolrCore;
import edu.umkc.type.ISolrCores;
import edu.umkc.type.ISolrResourceLoader;



public class SolrCoresImp implements ISolrCoresImp, ISolrCores
{
	private SolrCoresArch _arch;
	
  public SolrCoresImp () {
  }

	public void setArch(SolrCoresArch arch){
		_arch = arch;
	}
	
	public SolrCoresArch getArch(){
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
    
  public ISolrCores createSolrCores (final ICoreContainer container) {
    this.container = container;
    return this;
  }
  
  private static Object modifyLock = new Object(); // for locking around manipulating any of the core maps.
  private final Map<String, ISolrCore> cores = new LinkedHashMap<>(); // For "permanent" cores

  //WARNING! The _only_ place you put anything into the list of transient cores is with the putTransientCore method!
  private Map<String, ISolrCore> transientCores = new LinkedHashMap<>(); // For "lazily loaded" cores

  private final Map<String, CoreDescriptor> dynamicDescriptors = new LinkedHashMap<>();

  private final Map<String, ISolrCore> createdCores = new LinkedHashMap<>();

  private ICoreContainer container;

  private static final Logger logger = LoggerFactory.getLogger(SolrCoresImp.class);

  // This map will hold objects that are being currently operated on. The core (value) may be null in the case of
  // initial load. The rule is, never to any operation on a core that is currently being operated upon.
  private static final Set<String> pendingCoreOps = new HashSet<>();

  // Due to the fact that closes happen potentially whenever anything is _added_ to the transient core list, we need
  // to essentially queue them up to be handled via pendingCoreOps.
  private static final List<ISolrCore> pendingCloses = new ArrayList<>();

  // Trivial helper method for load, note it implements LRU on transient cores. Also note, if
  // there is no setting for max size, nothing is done and all cores go in the regular "cores" list
  public void allocateLazyCores(final int cacheSize, final ISolrResourceLoader loader) {
    if (cacheSize != Integer.MAX_VALUE) {
      CoreContainerImp.log.info("Allocating transient cache for {} transient cores", cacheSize);
      transientCores = new LinkedHashMap<String, ISolrCore>(cacheSize, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, ISolrCore> eldest) {
          if (size() > cacheSize) {
            synchronized (modifyLock) {
              ISolrCore coreToClose = eldest.getValue();
              logger.info("Closing transient core [{}]", coreToClose.getName());
              pendingCloses.add(coreToClose); // Essentially just queue this core up for closing.
              modifyLock.notifyAll(); // Wakes up closer thread too
            }
            return true;
          }
          return false;
        }
      };
    }
  }

  public void putDynamicDescriptor(String rawName, CoreDescriptor p) {
    synchronized (modifyLock) {
      dynamicDescriptors.put(rawName, p);
    }
  }

  // We are shutting down. You can't hold the lock on the various lists of cores while they shut down, so we need to
  // make a temporary copy of the names and shut them down outside the lock.
  public void close() {
    Collection<ISolrCore> coreList = new ArrayList<>();

    // It might be possible for one of the cores to move from one list to another while we're closing them. So
    // loop through the lists until they're all empty. In particular, the core could have moved from the transient
    // list to the pendingCloses list.

    do {
      coreList.clear();
      synchronized (modifyLock) {
        // make a copy of the cores then clear the map so the core isn't handed out to a request again
        coreList.addAll(cores.values());
        cores.clear();

        coreList.addAll(transientCores.values());
        transientCores.clear();

        coreList.addAll(pendingCloses);
        pendingCloses.clear();
      }

      for (ISolrCore core : coreList) {
        try {
          core.close();
        } catch (Throwable e) {
          SolrException.log(CoreContainerImp.log, "Error shutting down core", e);
          if (e instanceof Error) {
            throw (Error) e;
          }
        }
      }
    } while (coreList.size() > 0);
  }

  //WARNING! This should be the _only_ place you put anything into the list of transient cores!
  public ISolrCore putTransientCore(NodeConfig cfg, String name, ISolrCore core, ISolrResourceLoader loader) {
    ISolrCore retCore;
    CoreContainerImp.log.info("Opening transient core {}", name);
    synchronized (modifyLock) {
      retCore = transientCores.put(name, core);
    }
    return retCore;
  }

  public ISolrCore putCore(String name, ISolrCore core) {
    synchronized (modifyLock) {
      return cores.put(name, core);
    }
  }

  public List<ISolrCore> getCores() {
    List<ISolrCore> lst = new ArrayList<>();

    synchronized (modifyLock) {
      lst.addAll(cores.values());
      return lst;
    }
  }

  public Set<String> getCoreNames() {
    Set<String> set = new TreeSet<>();

    synchronized (modifyLock) {
      set.addAll(cores.keySet());
      set.addAll(transientCores.keySet());
    }
    return set;
  }

  public List<String> getCoreNames(ISolrCore core) {
    List<String> lst = new ArrayList<>();

    synchronized (modifyLock) {
      for (Map.Entry<String, ISolrCore> entry : cores.entrySet()) {
        if (core == entry.getValue()) {
          lst.add(entry.getKey());
        }
      }
      for (Map.Entry<String, ISolrCore> entry : transientCores.entrySet()) {
        if (core == entry.getValue()) {
          lst.add(entry.getKey());
        }
      }
    }
    return lst;
  }

  /**
   * Gets a list of all cores, loaded and unloaded (dynamic)
   *
   * @return all cores names, whether loaded or unloaded.
   */
  public Collection<String> getAllCoreNames() {
    Set<String> set = new TreeSet<>();
    synchronized (modifyLock) {
      set.addAll(cores.keySet());
      set.addAll(transientCores.keySet());
      set.addAll(dynamicDescriptors.keySet());
      set.addAll(createdCores.keySet());
    }
    return set;
  }

  ISolrCore getCore(String name) {

    synchronized (modifyLock) {
      return cores.get(name);
    }
  }

  public void swap(String n0, String n1) {

    synchronized (modifyLock) {
      ISolrCore c0 = cores.get(n0);
      ISolrCore c1 = cores.get(n1);
      if (c0 == null) { // Might be an unloaded transient core
        c0 = container.getCore(n0);
        if (c0 == null) {
          throw new SolrException(SolrException.ErrorCode.BAD_REQUEST, "No such core: " + n0);
        }
      }
      if (c1 == null) { // Might be an unloaded transient core
        c1 = container.getCore(n1);
        if (c1 == null) {
          throw new SolrException(SolrException.ErrorCode.BAD_REQUEST, "No such core: " + n1);
        }
      }
      cores.put(n0, c1);
      cores.put(n1, c0);

      c0.setName(n1);
      c1.setName(n0);
    }

  }

  public ISolrCore remove(String name) {

    synchronized (modifyLock) {
      ISolrCore tmp = cores.remove(name);
      ISolrCore ret = null;
      ret = (ret == null) ? tmp : ret;
      // It could have been a newly-created core. It could have been a transient core. The newly-created cores
      // in particular should be checked. It could have been a dynamic core.
      tmp = transientCores.remove(name);
      ret = (ret == null) ? tmp : ret;
      tmp = createdCores.remove(name);
      ret = (ret == null) ? tmp : ret;
      dynamicDescriptors.remove(name);
      return ret;
    }
  }

  /* If you don't increment the reference count, someone could close the core before you use it. */
  public ISolrCore  getCoreFromAnyList(String name, boolean incRefCount) {
    synchronized (modifyLock) {
      ISolrCore core = cores.get(name);

      if (core == null) {
        core = transientCores.get(name);
      }

      if (core != null && incRefCount) {
        core.open();
      }

      return core;
    }
  }

  public CoreDescriptor getDynamicDescriptor(String name) {
    synchronized (modifyLock) {
      return dynamicDescriptors.get(name);
    }
  }

  // See SOLR-5366 for why the UNLOAD command needs to know whether a core is actually loaded or not, it might have
  // to close the core. However, there's a race condition. If the core happens to be in the pending "to close" queue,
  // we should NOT close it in unload core.
  public boolean isLoadedNotPendingClose(String name) {
    // Just all be synchronized
    synchronized (modifyLock) {
      if (cores.containsKey(name)) {
        return true;
      }
      if (transientCores.containsKey(name)) {
        // Check pending
        for (ISolrCore core : pendingCloses) {
          if (core.getName().equals(name)) {
            return false;
          }
        }

        return true;
      }
    }
    return false;
  }

  public boolean isLoaded(String name) {
    synchronized (modifyLock) {
      if (cores.containsKey(name)) {
        return true;
      }
      if (transientCores.containsKey(name)) {
        return true;
      }
    }
    return false;

  }

  public CoreDescriptor getUnloadedCoreDescriptor(String cname) {
    synchronized (modifyLock) {
      CoreDescriptor desc = dynamicDescriptors.get(cname);
      if (desc == null) {
        return null;
      }
      return new CoreDescriptor(cname, desc);
    }

  }

  // Wait here until any pending operations (load, unload or reload) are completed on this core.
  public ISolrCore waitAddPendingCoreOps(String name) {

    // Keep multiple threads from operating on a core at one time.
    synchronized (modifyLock) {
      boolean pending;
      do { // Are we currently doing anything to this core? Loading, unloading, reloading?
        pending = pendingCoreOps.contains(name); // wait for the core to be done being operated upon
        if (! pending) { // Linear list, but shouldn't be too long
          for (ISolrCore core : pendingCloses) {
            if (core.getName().equals(name)) {
              pending = true;
              break;
            }
          }
        }
        if (container.isShutDown()) return null; // Just stop already.

        if (pending) {
          try {
            modifyLock.wait();
          } catch (InterruptedException e) {
            return null; // Seems best not to do anything at all if the thread is interrupted
          }
        }
      } while (pending);
      // We _really_ need to do this within the synchronized block!
      if (! container.isShutDown()) {
        if (! pendingCoreOps.add(name)) {
          CoreContainerImp.log.warn("Replaced an entry in pendingCoreOps {}, we should not be doing this", name);
        }
        return getCoreFromAnyList(name, false); // we might have been _unloading_ the core, so return the core if it was loaded.
      }
    }
    return null;
  }

  // We should always be removing the first thing in the list with our name! The idea here is to NOT do anything n
  // any core while some other operation is working on that core.
  public void removeFromPendingOps(String name) {
    synchronized (modifyLock) {
      if (! pendingCoreOps.remove(name)) {
        CoreContainerImp.log.warn("Tried to remove core {} from pendingCoreOps and it wasn't there. ", name);
      }
      modifyLock.notifyAll();
    }
  }

  public Object getModifyLock() {
    return modifyLock;
  }

  // Be a little careful. We don't want to either open or close a core unless it's _not_ being opened or closed by
  // another thread. So within this lock we'll walk along the list of pending closes until we find something NOT in
  // the list of threads currently being loaded or reloaded. The "usual" case will probably return the very first
  // one anyway..
  public ISolrCore getCoreToClose() {
    synchronized (modifyLock) {
      for (ISolrCore core : pendingCloses) {
        if (! pendingCoreOps.contains(core.getName())) {
          pendingCoreOps.add(core.getName());
          pendingCloses.remove(core);
          return core;
        }
      }
    }
    return null;
  }

  public void addCreated(ISolrCore core) {
    synchronized (modifyLock) {
      createdCores.put(core.getName(), core);
    }
  }

  /**
   * Return the CoreDescriptor corresponding to a given core name.
   * @param coreName the name of the core
   * @return the CoreDescriptor
   */
  public CoreDescriptor getCoreDescriptor(String coreName) {
    synchronized (modifyLock) {
      if (cores.containsKey(coreName))
        return cores.get(coreName).getCoreDescriptor();
      if (dynamicDescriptors.containsKey(coreName))
        return dynamicDescriptors.get(coreName);
      return null;
    }
  }

  /**
   * Get the CoreDescriptors for every SolrCore managed here
   * @return a List of CoreDescriptors
   */
  public List<CoreDescriptor> getCoreDescriptors() {
    List<CoreDescriptor> cds = Lists.newArrayList();
    synchronized (modifyLock) {
      for (String coreName : getAllCoreNames()) {
        // TODO: This null check is a bit suspicious - it seems that
        // getAllCoreNames might return deleted cores as well?
        CoreDescriptor cd = getCoreDescriptor(coreName);
        if (cd != null)
          cds.add(cd);
      }
    }
    return cds;
  }
}