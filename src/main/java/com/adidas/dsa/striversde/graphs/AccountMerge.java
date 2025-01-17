package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AccountMerge {
  static List<List<String>> accountsMerge(List<List<String>> accounts) {
    List<List<String>> mergedAccounts = new ArrayList<>();

    DisjointSetUnion dsu = new DisjointSetUnion(accounts.size());

    Map<String, Integer> emailMap = new LinkedHashMap<>();

    int currentIndex = 0;
    for (int i = 1; i < accounts.get(currentIndex).size(); i++) {
      String currentEmail = accounts.get(currentIndex).get(i);
      if (!emailMap.containsKey(currentEmail)) {
        emailMap.put(currentEmail, currentIndex);
      } else if (!dsu.isConnected(emailMap.get(currentEmail), currentIndex)) {
        dsu.unionByRank(emailMap.get(currentEmail), currentIndex);

      }

      currentIndex++;
    }

    for (int i = 0; i < accounts.size(); i++) {
      mergedAccounts.add(new LinkedList<>());
    }

    for (Map.Entry<String, Integer> eachEntry : emailMap.entrySet()) {
      int findUltimateParent = dsu.findParent(eachEntry.getValue());
      mergedAccounts.get(findUltimateParent).add(eachEntry.getKey());

    }

    currentIndex = 0;
    for (List<String> eachMergedAccount : mergedAccounts) {
      Collections.sort(eachMergedAccount);
      eachMergedAccount.addFirst(accounts.get(currentIndex).get(0));
    }

    return mergedAccounts;
  }

  private static class DisjointSetUnion {

    /**
     * vertices represent number of nodes in the graph if we have a zero based indexing then we pass n
     * otherwise if 1-based indexing we pass n + 1
     */
    private int vertices;

    private int[] parent;
    private int[] rank;

    /**
     * now in the DisjointSetUnion we don't need both rank and size as both are necessary we can go with either one
     */

    public DisjointSetUnion(int vertices) {
      this.vertices = vertices;
      this.parent = new int[vertices];
      this.rank = new int[vertices];


      for (int i = 0; i < vertices; i++) {
        parent[i] = i;
        /**
         * rank by default is initialized to zero always if we don't initialize, default with Java
         *
         * if we however use size we keep the size = 1 for the nodes
         */

      }

    }

    public int findParent(int node) {
      if (parent[node] == node)
        return node;
      return parent[node] = findParent(parent[node]);
    }

    public void unionByRank(int u, int v) {
      int parentU = findParent(u), parentV = findParent(v);

      if (parentU == parentV) return;

      if (rank[parentU] < rank[parentV]) {
        parent[parentU] = parentV;
      } else if (rank[parentV] < rank[parentU]) {
        parent[parentV] = parentU;
      } else {
        parent[parentV] = parentU;
        rank[parentU]++;
      }
    }

    public boolean isConnected(int u, int v) {
      return findParent(u) == findParent(v);
    }

  }
}