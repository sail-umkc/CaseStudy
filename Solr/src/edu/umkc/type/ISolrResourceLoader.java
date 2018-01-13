package edu.umkc.type;

import java.io.Closeable;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.lucene.analysis.util.ResourceLoader;

import edu.umkc.type.ISolrCore;
import edu.umkc.type.ICoreContainer;
import edu.umkc.solr.core.SolrInfoMBean;
import edu.umkc.solr.handler.admin.CoreAdminHandler;
import edu.umkc.solr.rest.RestManager;

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

public interface ISolrResourceLoader extends ResourceLoader, Closeable
{
  public abstract RestManager.Registry getManagedResourceRegistry();
  
  public void addToClassLoader(final String baseDir, final FileFilter filter, boolean quiet);
  
  public void reloadLuceneSPI();
    
  public String[] listConfigDir();

  public String getConfigDir();
  
  public String getDataDir();

  public Properties getCoreProperties();

  public ClassLoader getClassLoader();

  public InputStream openSchema(String name) throws IOException;
  
  public InputStream openConfig(String name) throws IOException;
  
  @Override
  public InputStream openResource(String resource) throws IOException;

  public List<String> getLines(String resource) throws IOException;

  public List<String> getLines(String resource, String encoding) throws IOException;

  public List<String> getLines(String resource, Charset charset) throws IOException;

  @Override
  public <T> Class<? extends T> findClass(String cname, Class<T> expectedType);

  public <T> Class<? extends T> findClass(String cname, Class<T> expectedType, String... subpackages);
  
  @Override
  public <T> T newInstance(String name, Class<T> expectedType);

  public <T> T newInstance(String cname, Class<T> expectedType, String ... subpackages);

  public CoreAdminHandler newAdminHandlerInstance(final ICoreContainer coreContainer, String cname, String ... subpackages);

  public <T> T newInstance(String cName, Class<T> expectedType, String [] subPackages, Class[] params, Object[] args);

  public void inform(ISolrCore core);
  
  public void inform( ResourceLoader loader ) throws IOException;

  public void inform(Map<String, SolrInfoMBean> infoRegistry);

  public String getInstanceDir();
  
  @Override
  public void close() throws IOException;
  
  public List<SolrInfoMBean> getInfoMBeans();

  public String resolve(String pathToResolve);  
}
