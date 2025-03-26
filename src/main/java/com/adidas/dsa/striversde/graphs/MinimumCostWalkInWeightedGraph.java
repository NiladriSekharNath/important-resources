package com.adidas.dsa.striversde.graphs;

import java.util.HashMap;
import java.util.Map;

/**
 * There is an undirected weighted graph with n vertices labeled from 0 to n - 1.
 *
 * You are given the integer n and an array edges, where edges[i] = [ui, vi, wi] indicates that there is an edge between vertices ui and vi with a weight of wi.
 *
 * A walk on a graph is a sequence of vertices and edges. The walk starts and ends with a vertex, and each edge connects the vertex that comes before it and the vertex that comes after it. It's important to note that a walk may visit the same edge or vertex more than once.
 *
 * The cost of a walk starting at node u and ending at node v is defined as the bitwise AND of the weights of the edges traversed during the walk. In other words, if the sequence of edge weights encountered during the walk is w0, w1, w2, ..., wk, then the cost is calculated as w0 & w1 & w2 & ... & wk, where & denotes the bitwise AND operator.
 *
 * You are also given a 2D array query, where query[i] = [si, ti]. For each query, you need to find the minimum cost of the walk starting at vertex si and ending at vertex ti. If there exists no such walk, the answer is -1.
 *
 * Return the array answer, where answer[i] denotes the minimum cost of a walk for query i.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 5, edges = [[0,1,7],[1,3,7],[1,2,1]], query = [[0,3],[3,4]]
 *
 * Output: [1,-1]
 *
 * Explanation:
 *
 *
 * To achieve the cost of 1 in the first query, we need to move on the following edges: 0->1 (weight 7), 1->2 (weight 1), 2->1 (weight 1), 1->3 (weight 7).
 *
 * In the second query, there is no walk between nodes 3 and 4, so the answer is -1.
 *
 * Example 2:
 *
 * Input: n = 3, edges = [[0,2,7],[0,1,15],[1,2,6],[1,2,1]], query = [[1,2]]
 *
 * Output: [0]
 *
 * Explanation:
 *
 *
 * To achieve the cost of 0 in the first query, we need to move on the following edges: 1->2 (weight 1), 2->1 (weight 6), 1->2 (weight 1).
 *
 *
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * 0 <= edges.length <= 105
 * edges[i].length == 3
 * 0 <= ui, vi <= n - 1
 * ui != vi
 * 0 <= wi <= 105
 * 1 <= query.length <= 105
 * query[i].length == 2
 * 0 <= si, ti <= n - 1
 * si != ti
 *
 *
 *    Given a graph like this that has edges weights
 *
 *      7   7
 *    0 - 1 - 3       4
 *        | 1
 *        2
 *
 *  n = 5, edges = [[0, 1, 7], [1, 3, 7], [1, 2, 1]], query = [[0, 3], [3, 4]]
 *
 *  and queries given to us for each query
 *
 *  [0 - 3] we have to find the minimum cost walk that connects [0 - 3]
 *
 *  Minimum cost walk is given between an edge as let's say we have nodes and edges:
 *
 *  n0 - w0 - n1 - w1 - n2...
 *
 *  where w0 and w1 are edges weights
 *
 *  so for the query :
 *
 *
 *  [0 - 3]
 *
 *  we are moving on the following edges :
 *
 *  0 -> 1 (weight 7), 1 -> 2 (weight 1), 2 -> 1 (weight 1), 1 -> 3(weight 7)
 *
 *   cost = 7 & 1 & 1 & 3 = 1
 *
 *  [3 - 4] since this edge is not connected = -1
 *
 * Approach:
 *
 *  1. We see first we given an edge like [3 - 4] this we are seeing that this node is not connected, so intuition that we
 *     get from the problem is we need to somehow try connecting the edges, (Approach DSU is coming to us)
 *
 *  2. Again we are told we can take repeat the same paths and keep performing 'Bitwise AND', this essentially tells us we
 *     if we take all the paths the chances of bitwise AND value would be minimized
 *
 *       7 & 7 & 1 & 3 = 111 & 111 & 001 & 011 (written in 3 bits)
 *                       if we see this while we are doing this since 1 has the only one bit set
 *                       and rest are all zeroes we infact end up minimizing the result
 *
 *  3. So we do this merge all the edges and for each edge/component we have a parent where we setting the value
 *     in a map and then loop through the queries and for each edge pair find out it's parent and get the value
 *
 *      a. we loop through the edges and keep connecting them,
 *      b. Loop through the edges again,
 *
 *         for component:
 *
 *            0 - 1 - 3
 *                |
 *                2
 *
 *         we assume that 0 is the parent node of all the nodes and this node represents the component
 *         and we store the component value while iterating in the same edges loop second time
 *
 *  Note: initially the we cannot set the initial value of the node to be
 *  0 or infact 1 (because 0 - 1 would have given us incorrect results)
 *  so we set the initial value -1
 *
 *  as -1 -> represented in binary (As negative numbers are represented in 2's complement)
 *
 *  1 -> flip nodes from 1 to 0 and 0 - 1 and add +1 bit
 *
 *  1 -> 0 + 1 -> 1
 *
 *  */

/*
*

 Java Notes: Representing -10 in Binary (Two's Complement)

 It includes steps for conversion, verification, and Java code implementation.

*

 Two's Complement Representation of -10

 Convert +10 to Binary (8-bit)

 10₁₀ = 00001010₂





 Find One’s Complement (Invert the Bits)



 00001010 → 11110101





 Add 1 to Get Two’s Complement



 11110101 + 1 = 11110110



 Result: -10₁₀ = 11110110₂ (8-bit representation)

*

 Verification: Convert 11110110 Back to Decimal

 Identify the Number Type:

 MSB (Most Significant Bit) = 1 → Negative Number

 Take Two’s Complement:

 Invert: 11110110 → 00001001

 Add 1: 00001001 + 1 = 00001010

 Decimal Equivalent: 10₁₀

 Final Value: -10₁₀

*

 Representation in Other Bit-Lengths

 4-bit Two’s Complement: Not possible (range is -8 to +7).

 8-bit Two’s Complement: 11110110

 16-bit Two’s Complement: 11111111 11110110

 32-bit Two’s Complement: 11111111 11111111 11111111 11110110

*

 Java Code to Compute Two’s Complement


*

 Expected Output:

 Binary Representation (8-bit): 11110110

 Binary Representation (32-bit): 11111111111111111111111111110110

*

 Key Takeaways:

 Two’s Complement is the standard way to represent negative numbers in binary.

 Bitwise AND (& 0xFF) ensures an 8-bit representation.

 Integer.toBinaryString(int num) converts an integer to its binary representation (default 32-bit in Java).

 Java uses signed 32-bit integers by default.

 Tip: For different bit sizes, use masking (& 0xFF, & 0xFFFF, etc.) to extract the required bits.
 This document helps in understanding binary representation and how Java handles negative numbers using Two’s Complement.
*/
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
