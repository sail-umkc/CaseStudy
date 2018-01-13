package edu.umkc.solr.search.grouping.distributed;

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

import edu.umkc.solr.handler.component.ResponseBuilder;
import edu.umkc.solr.handler.component.ShardRequest;

/**
 * Responsible for processing shard responses.
 *
 * @lucene.experimental
 */
public interface ShardResponseProcessor {

  /**
   * Processes the responses from the specified shardRequest. The result is put into specific
   * fields in the specified rb.
   *
   * @param rb The ResponseBuilder to put the merge result into
   * @param shardRequest The shard request containing the responses from all shards.
   */
  void process(ResponseBuilder rb, ShardRequest shardRequest);

}