package com.adidas.dsa.striversde.graphs;

import java.util.HashMap;
import java.util.Map;

public class MinimumCostWalkInWeightedGraph {
  public int[] minimumCost(int n, int[][] edges, int[][] querys) {
    DSU dsu = new DSU(n);
    Map<Integer, Integer> parentVal = new HashMap<>();
    for(int[] edge: edges){
      dsu.unionByRank(edge[0], edge[1]);
    }
    for(int[] edge : edges){
      int u = edge[0], v = edge[1], w = edge[2], parentU = dsu.getParent(u), parentV = dsu.getParent(v);
      if(parentU == parentV){
        parentVal.put(parentU, (parentVal.getOrDefault(parentU, -1) & w));
      }
    }

    int result[] = new int[querys.length], index = 0;

    for(int[] query : querys){
      int u = query[0], v = query[1];
      if(dsu.isConnected(u, v)){
        result[index] = parentVal.get(dsu.getParent(u));
      }
      else
        result[index] = -1;
      index++;
    }

    return result;
  }


  private class DSU{
    private int[] rank;
    private int[] parent;
    private int nodes ;

    public DSU(int n){
      this.nodes = n;
      this.rank = new int[n];
      this.parent = new int[n];
      for(int i = 0; i < n; i++){
        parent[i] = i;
      }
    }

    public int getParent(int node){
      if(parent[node] == node) return node;
      return parent[node] = getParent(parent[node]);
    }

    public void unionByRank(int u, int v){
      int parentU = getParent(u), parentV = getParent(v);
      if(parentU == parentV) return;

      if(rank[parentU] < rank[parentV]){
        parent[parentU] = parentV;
      }else if(rank[parentV] < rank[parentU]){
        parent[parentV] = parentU;
      }else{
        parent[parentV] = parentU;
        rank[parentU]++;
      }
    }

    public boolean isConnected(int u, int v){
      return getParent(u) == getParent(v);
    }

  }
}
