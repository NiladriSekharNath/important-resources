package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a graph like this
 *
 * [[[1,2], [2,2]], [[0,2], [4,2]]]
 *
 * Given a graph with weights like this
 *
 * like we have [[1,2], [3,4] ...]
 *
 * like we say 0 -> [1,2], 1 -> [3,4] points above graph has weights too
 *
 * so for each edge for let's say 0 we taking each neighbour and add it's weight to the list<edge[]>
 *    represented by u, v, w -> edge[0], edge[1], edge[2]
 *
 *   WE find the mst using kruskal algorithm
 *
 *  Step 1 : sort the edges using by weight, (done one extra step if weight is same, we sort by the starting nodes u, )
 *  Step 2 : now check if the edge triplet(u, v, w) u,v is connected(if parent of u, v is different) if yest then
 *    add the edge in mst or weight to the mstValue depending on the use case
 *
 * though it's a standard algorithm, now we could have done one more instead of adding the edge and sorting we could
 * have added the edge in a priority queue this might give us better benefit
 *
 *
 */
public class MstUsingKruskal {
  public int spanningTree(int n, int e, List<List<int[]>> graph) {
    List<int[]> edges = new ArrayList<>();

    for(int currentNode = 0 ; currentNode < n; currentNode++){
      for(int[] neighEdgeWeight : graph.get(currentNode)){
        int targetNode = neighEdgeWeight[0], weight = neighEdgeWeight[1];
        edges.add(new int[]{currentNode, targetNode, weight});
      }
    }

    Collections.sort(edges, (entry1, entry2) -> entry1[2] != entry2[2] ?
        entry1[2] - entry2[2] : entry1[0] - entry2[0]);

    /**
     * List<int[]> mst = new ArrayList<>();
     *
     * we could have added the mst here if required
     */

    int mstValue = 0;

    DisjointSetUnion dsu = new DisjointSetUnion(n);

    for(int[] edge : edges){
      int u = edge[0], v = edge[1], w = edge[2];
      if(!dsu.isConnected(u, v)){
        dsu.unionByRank(u, v);
        mstValue += w;

        /**
         * Here we could have added the mst edge
         * mst.add(edge);
         */
      }
    }


    return mstValue;
  }

  private class DisjointSetUnion {

    /**
     *
     * vertices represent number of nodes in the graph if we have a zero based indexing then we pass n
     * otherwise if 1-based indexing we pass n + 1
     */
    private int vertices;

    private int[] parent;
    private int[] rank ;

    /**
     * now in the DisjointSetUnion we don't need both rank and size as both are necessary we can go with either one
     */
    private int[] size;
    public DisjointSetUnion(int vertices){
      this.vertices = vertices;
      this.parent = new int[vertices];
      this.rank = new int[vertices];
      this.size = new int[vertices];

      for(int i = 0 ; i < vertices; i++){
        parent[i] = i;
        /**
         * rank by default is initialized to zero always if we don't initialize, default with Java
         *
         * if we however use size we keep the size = 1 for the nodes
         */
        size[i] = 1;

      }

    }

    public int findParent(int node){
      if(parent[node] == node)
        return node;
      return parent[node] = findParent(parent[node]);
    }

    public void unionByRank(int u, int v){
      int parentU = findParent(u), parentV = findParent(v);

      if(parentU == parentV) return ;

      if(rank[parentU] < rank[parentV]){
        parent[parentU] = parentV;
      }
      else if(rank[parentV] < rank[parentU]){
        parent[parentV] = parentU;
      }
      else {
        parent[parentV] = parentU;
        rank[parentU]++;
      }
    }

    /**
     *
     * we don't need both unionByRank, unionBySize we need the either one
     */


    public boolean isConnected(int u , int v){
      return findParent(u) == findParent(v);
    }
  }
}
