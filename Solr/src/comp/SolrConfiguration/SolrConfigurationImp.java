package comp.SolrConfiguration;

import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.http.MimeTypes;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.restlet.ext.servlet.ServerServlet;

import edu.umkc.config.Bootstrapper;


public class SolrConfigurationImp implements ISolrConfigurationImp
{
  private SolrConfigurationArch _arch;
  
  final private Server server;
  
  final private String CONTEXT_PATH = "/solr";
  final private String WEBAPP_DIR   = "solr/webapp/web";
  final private String SOLR_HOME    = "solr/server/solr";
  
  public SolrConfigurationImp (){
    server = new Server();
  }

  public void setArch(SolrConfigurationArch arch){
    _arch = arch;
  }
  public SolrConfigurationArch getArch(){
    return _arch;
  }

  /*
      Myx Lifecycle Methods: these methods are called automatically by the framework
      as the bricks are created, attached, detached, and destroyed respectively.
  */  
  public void init()
  {
    Bootstrapper.incrInitCount();
    System.setProperty("solr.solr.home", SOLR_HOME);
    
    final ServerConnector connector = new ServerConnector(server, new HttpConnectionFactory());
    connector.setIdleTimeout(1000 * 60 * 60);
    connector.setSoLingerTime(-1);
    connector.setPort(8983);
    server.setConnectors(new Connector[] {connector});
  }
  
  public void begin()
  {
    Bootstrapper.incrBeginCount();
    
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          while (Bootstrapper.getInitCount() != Bootstrapper.getBeginCount()) {
            Thread.sleep(500);
          }
          System.out.println(Bootstrapper.getBeginCount() + " components have successfully started.");
          
          startSolr();
        } catch (InterruptedException e) {
          throw new RuntimeException();
        }
      }
    }).start();
  }
  
  public void startSolr()
  {
    final WebAppContext webapp = new WebAppContext();
    webapp.setResourceBase(WEBAPP_DIR);
    webapp.setContextPath(CONTEXT_PATH);
    webapp.setParentLoaderPriority(true);
    webapp.setWelcomeFiles(new String[] {"admin.html"});

    server.setHandler(webapp);
    
    // SolrDispatch filter
    final FilterHolder filterHolder = new FilterHolder(_arch.OUT_ISolrDispatchFilter);
    filterHolder.setInitParameter("excludePatterns", "/css/*,/js/*,/img/*,/tpl/*");
    webapp.addFilter(filterHolder, "/*", EnumSet.of(DispatcherType.REQUEST));

    // Zookeeper servlet
    final ServletHolder zookeeperServletHolder = new ServletHolder("Zookeeper", _arch.OUT_IZookeeperInfoServlet);
    webapp.addServlet(zookeeperServletHolder, "/zookeeper");

    // LoadAdminUI servlet
    final ServletHolder loadAdminServletHolder = new ServletHolder("LoadAdminUI", _arch.OUT_ILoadAdminUIServlet);
    webapp.addServlet(loadAdminServletHolder, "/admin.html");

    // RedirectOldAdminUI servlet
    final ServletHolder redirectOldAdminUIServletHolder = new ServletHolder("RedirectOldAdminUI", _arch.OUT_IRedirectOldAdminUIServlet);
    redirectOldAdminUIServletHolder.setInitParameter("destination", "${context}/#/");
    webapp.addServlet(redirectOldAdminUIServletHolder, "/admin/");

    // RedirectOldZookeeper servlet
    final ServletHolder redirectOldZookeeperServletHolder = new ServletHolder("RedirectOldZookeeper", _arch.OUT_IRedirectOldZookeeperServlet);
    redirectOldZookeeperServletHolder.setInitParameter("destination", "${context}/zookeeper");
    webapp.addServlet(redirectOldZookeeperServletHolder, "/zookeeper.jsp");

    // RedirectLogging servlet
    final ServletHolder redirectLoggingServletHolder = new ServletHolder("RedirectLogging", _arch.OUT_IRedirectLoggingServlet);
    redirectLoggingServletHolder.setInitParameter("destination", "${context}/#/~logging");
    webapp.addServlet(redirectLoggingServletHolder, "/logging");

    // SolrRestApi servlet
    final ServletHolder solrRestApiServletHolder = new ServletHolder("SolrRestApi", ServerServlet.class);
    solrRestApiServletHolder.setInitParameter("org.restlet.application", "edu.umkc.solr.rest.SolrSchemaRestApi");
    webapp.addServlet(solrRestApiServletHolder, "/schema/*");

    // Mime mappings
    final MimeTypes mimeTypes = new MimeTypes();
    mimeTypes.addMimeMapping(".xsl", "application/xslt+xml");
    webapp.setMimeTypes(mimeTypes);
    
    try {
      System.out.println(">>> STARTING EMBEDDED JETTY SERVER");
      server.start();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(100);
    }
  }
  
  public void end(){
    try {
      System.out.println(">>> TERMINATE EMBEDDED JETTY SERVER");
      server.stop();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(100);
    }
  }
  public void destroy(){
    //TODO Auto-generated method stub
  }

  /*
      Implementation primitives required by the architecture
  */
}