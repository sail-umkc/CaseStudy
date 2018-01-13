package edu.umkc.solr.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.LeafReaderContext;
import org.apache.lucene.search.DocIdSet;
import org.apache.lucene.search.DocIdSetIterator;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Scorer;
import org.apache.lucene.search.Weight;
import org.apache.lucene.util.Bits;

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

public class FilterImpl extends Filter {
  final DocSet filter;
  final Filter topFilter;
  final List<Weight> weights;

  public FilterImpl(DocSet filter, List<Weight> weights) {
    this.filter = filter;
    this.weights = weights;
    this.topFilter = filter == null ? null : filter.getTopFilter();
  }

  @Override
  public DocIdSet getDocIdSet(LeafReaderContext context, Bits acceptDocs) throws IOException {
    DocIdSet sub = topFilter == null ? null : topFilter.getDocIdSet(context, acceptDocs);
    if (weights.size() == 0) return sub;
    return new FilterSet(sub, context);
  }

  @Override
  public String toString(String field) {
    return "SolrFilter";
  }

  private class FilterSet extends DocIdSet {
    DocIdSet docIdSet;
    LeafReaderContext context;

    public FilterSet(DocIdSet docIdSet, LeafReaderContext context) {
      this.docIdSet = docIdSet;
      this.context = context;
    }

    @Override
    public DocIdSetIterator iterator() throws IOException {
      List<DocIdSetIterator> iterators = new ArrayList<>(weights.size()+1);
      if (docIdSet != null) {
        DocIdSetIterator iter = docIdSet.iterator();
        if (iter == null) return null;
        iterators.add(iter);
      }
      for (Weight w : weights) {
        Scorer scorer = w.scorer(context, context.reader().getLiveDocs());
        if (scorer == null) return null;
        iterators.add(scorer);
      }
      if (iterators.size()==0) return null;
      if (iterators.size()==1) return iterators.get(0);
      if (iterators.size()==2) return new DualFilterIterator(iterators.get(0), iterators.get(1));
      return new FilterIterator(iterators.toArray(new DocIdSetIterator[iterators.size()]));
    }

    @Override
    public Bits bits() throws IOException {
      return null;  // don't use random access
    }

    @Override
    public long ramBytesUsed() {
      return docIdSet != null ? docIdSet.ramBytesUsed() : 0L;
    }
  }

  private static class FilterIterator extends DocIdSetIterator {
    final DocIdSetIterator[] iterators;
    final DocIdSetIterator first;

    public FilterIterator(DocIdSetIterator[] iterators) {
      this.iterators = iterators;
      this.first = iterators[0];
    }

    @Override
    public int docID() {
      return first.docID();
    }

    private int doNext(int doc) throws IOException {
      int which=0;  // index of the iterator with the highest id
      int i=1;
      outer: for(;;) {
        for (; i<iterators.length; i++) {
          if (i == which) continue;
          DocIdSetIterator iter = iterators[i];
          int next = iter.advance(doc);
          if (next != doc) {
            doc = next;
            which = i;
            i = 0;
            continue outer;
          }
        }
        return doc;
      }
    }


    @Override
    public int nextDoc() throws IOException {
      return doNext(first.nextDoc());
    }

    @Override
    public int advance(int target) throws IOException {
      return doNext(first.advance(target));
    }

    @Override
    public long cost() {
      return first.cost();
    }
  }

  private static class DualFilterIterator extends DocIdSetIterator {
    final DocIdSetIterator a;
    final DocIdSetIterator b;

    public DualFilterIterator(DocIdSetIterator a, DocIdSetIterator b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public int docID() {
      return a.docID();
    }

    @Override
    public int nextDoc() throws IOException {
      int doc = a.nextDoc();
      for(;;) {
        int other = b.advance(doc);
        if (other == doc) return doc;
        doc = a.advance(other);
        if (other == doc) return doc;
      }
    }

    @Override
    public int advance(int target) throws IOException {
      int doc = a.advance(target);
      for(;;) {
        int other = b.advance(doc);
        if (other == doc) return doc;
        doc = a.advance(other);
        if (other == doc) return doc;
      }
    }

    @Override
    public long cost() {
      return Math.min(a.cost(), b.cost());
    }
  }

}