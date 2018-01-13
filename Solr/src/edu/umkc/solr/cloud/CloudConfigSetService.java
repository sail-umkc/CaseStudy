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

package edu.umkc.solr.cloud;

import comp.SolrResourceLoader.SolrResourceLoaderImp;

import edu.umkc.solr.core.ConfigSetService;
import edu.umkc.solr.core.CoreDescriptor;
import edu.umkc.type.ISolrResourceLoader;

public class CloudConfigSetService extends ConfigSetService {

  private final ZkController zkController;

  public CloudConfigSetService(ISolrResourceLoader loader, ZkController zkController) {
    super(loader);
    this.zkController = zkController;
  }

  @Override
  public SolrResourceLoaderImp createCoreResourceLoader(CoreDescriptor cd) {
    // TODO: Shouldn't the collection node be created by the Collections API?
    zkController.createCollectionZkNode(cd.getCloudDescriptor());
    String configName = zkController.getZkStateReader().readConfigName(cd.getCollectionName());
    return new ZkSolrResourceLoader(cd.getInstanceDir(), configName, parentLoader.getClassLoader(),
        cd.getSubstitutableProperties(), zkController);
  }

  @Override
  public String configName(CoreDescriptor cd) {
    return "collection " + cd.getCloudDescriptor().getCollectionName();
  }
}
