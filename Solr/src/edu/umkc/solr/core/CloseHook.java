package edu.umkc.solr.core;

import comp.SolrCoreFactory.SolrCoreFactoryImp.SolrCore;

import edu.umkc.type.ISolrCore;


/**
 * Used to request notification when the core is closed.
 * <p>
 * Call {@link SolrCore#addCloseHook(edu.umkc.solr.core.CloseHook)} during the {@link edu.umkc.solr.util.plugin.SolrCoreAware#inform(ISolrCore)} method to
 * add a close hook to your object.
 * <p>
 * The close hook can be useful for releasing objects related to the request handler (for instance, if you have a JDBC DataSource or something like that)
 */

public abstract class CloseHook {

  /**
   * Method called when the given SolrCore object is closing / shutting down but before the update handler and
   * searcher(s) are actually closed
   * <br>
   * <b>Important:</b> Keep the method implementation as short as possible. If it were to use any heavy i/o , network connections -
   * it might be a better idea to launch in a separate Thread so as to not to block the process of
   * shutting down a given SolrCore instance.
   *
   * @param core SolrCore object that is shutting down / closing
   */
  public abstract void preClose(ISolrCore core);

  /**
   * Method called when the given SolrCore object has been shut down and update handlers and searchers are closed
   * <br>
   * Use this method for post-close clean up operations e.g. deleting the index from disk.
   * <br>
   * <b>The core's passed to the method is already closed and therefore, its update handler or searcher should *NOT* be used</b>
   *
   * <b>Important:</b> Keep the method implementation as short as possible. If it were to use any heavy i/o , network connections -
   * it might be a better idea to launch in a separate Thread so as to not to block the process of
   * shutting down a given SolrCore instance.
   */
  public abstract void postClose(ISolrCore core);
}
