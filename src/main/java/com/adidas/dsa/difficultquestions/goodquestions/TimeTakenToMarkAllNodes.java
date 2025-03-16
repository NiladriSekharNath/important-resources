package com.adidas.dsa.difficultquestions.goodquestions;

import java.util.ArrayList;
import java.util.List;

/**
 * There exists an undirected tree with n nodes numbered 0 to n - 1. You are given a 2D integer array edges of length n - 1, where edges[i] = [ui, vi] indicates that there is an edge between nodes ui and vi in the tree.
 *
 * Initially, all nodes are unmarked. For each node i:
 *
 * If i is odd, the node will get marked at time x if there is at least one node adjacent to it which was marked at time x - 1.
 * If i is even, the node will get marked at time x if there is at least one node adjacent to it which was marked at time x - 2.
 * Return an array times where times[i] is the time when all nodes get marked in the tree, if you mark node i at time t = 0.
 *
 * Note that the answer for each times[i] is independent, i.e. when you mark node i all other nodes are unmarked.
 *
 *
 *
 * Example 1:
 *
 * Input: edges = [[0,1],[0,2]]
 *
 * Output: [2,4,3]
 *
 * Explanation:
 *
 *
 *
 * For i = 0:
 * Node 1 is marked at t = 1, and Node 2 at t = 2.
 * For i = 1:
 * Node 0 is marked at t = 2, and Node 2 at t = 4.
 * For i = 2:
 * Node 0 is marked at t = 2, and Node 1 at t = 3.
 * Example 2:
 *
 * Input: edges = [[0,1]]
 *
 * Output: [1,2]
 *
 * Explanation:
 *
 *
 *
 * For i = 0:
 * Node 1 is marked at t = 1.
 * For i = 1:
 * Node 0 is marked at t = 2.
 * Example 3:
 *
 * Input: edges = [[2,4],[0,1],[2,3],[0,2]]
 *
 * Output: [4,6,3,5,5]
 *
 * Explanation:
 *
 *
 *
 *
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= edges[i][0], edges[i][1] <= n - 1
 * The input is generated such that edges represents a valid tree.
 *
 * Basically questions like where we are required to find all nodes(try all nodes ) we are required to do rerooting
 *
 * We always have to do bottoms up dp
 *
 * go explore neighbours ->
 *
 * dfs(cN){
 * for(int neigh : graph.get(cN)){
 *  dfs(neighbour, cN);
 *  ...
 *  backtracking step logic written here
 *  }
 * }
 *
 * also in rerooting ->
 *
 *                          0
 *                         / \
 *                        1   2
 *
 * Here above 0 is the root, if we have to reroot from 0 to new root 1
 *
 * which looks like ->
 *
 *                         1
 *                        |
 *                       0
 *
 *
 */
public class TimeTakenToMarkAllNodes {
  public int[] timeTaken(int[][] edges) {
    List<List<Integer>> graph = makeGraph(edges);
    int n = edges.length + 1;
    int[] result = new int[n];
    // dp[node][0] = best contribution from children (i.e. maximum delay from a subtree)
    // dp[node][1] = second best contribution from children
    int[][] dp = new int[n][2];

    // First, compute dp values for a fixed root (0) in a bottom–up fashion.
    dfs0(0, -1, graph, dp);

    // Now, use rerooting (top–down DFS) to compute the answer if each node were the starting point.
    findAll(0, -1, graph, dp, result);

    return result;
  }

  // Bottom-up DFS: compute the best and second-best contributions from children.
  // For a leaf, dp[node] remains {0,0}.
  // For a non-leaf, for each child "nei", we compute:
  //    candidate = getNodeValue(nei) + dp[nei][0]
  // and update dp[cur] accordingly.

  // for finding the second best we apply the simple 1 pass finding the second best approach
  // if we find a 'value' that is greater than the highest we update -> secondhighest = highest
  // highest = value
  private void dfs0(int cur, int parent, List<List<Integer>> graph, int[][] dp) {
    dp[cur][0] = 0;  // best contribution from children (initially none)
    dp[cur][1] = 0;  // second-best contribution
    for (int nei : graph.get(cur)) {
      if (nei == parent) continue;
      dfs0(nei, cur, graph, dp);
      int candidate = getNodeValue(nei) + dp[nei][0];
      if (candidate > dp[cur][0]) {
        dp[cur][1] = dp[cur][0];
        dp[cur][0] = candidate;
      } else if (candidate > dp[cur][1]) {
        dp[cur][1] = candidate;
      }
    }
  }

  // Top-down DFS: try "rerooting" the tree at every neighbor.
  // Before recursing, we adjust the dp values using reroot.
  // After the recursive call returns, we restore the original dp values.
  private void findAll(int cur, int parent, List<List<Integer>> graph, int[][] dp, int[] result) {
    // The answer for a node is defined as the best contribution from its children.
    // (For example, when node 0 is the starting point, its marking time is dp[0][0].)
    result[cur] = dp[cur][0];

    for (int nei : graph.get(cur)) {
      if (nei == parent) continue;

      // Save the current dp values for restoration.
      int originalCurBest = dp[cur][0];
      int originalCurSecond = dp[cur][1];
      int originalNeiBest = dp[nei][0];
      int originalNeiSecond = dp[nei][1];

      // Reroot: make "nei" the parent and "cur" its child.
      reroot(cur, nei, dp);

      // Recurse with nei as the new root.
      findAll(nei, cur, graph, dp, result);

      // Restore the original dp values.
      dp[cur][0] = originalCurBest;
      dp[cur][1] = originalCurSecond;
      dp[nei][0] = originalNeiBest;
      dp[nei][1] = originalNeiSecond;
    }
  }

  // Reroot from "cur" (current parent) to "nei" (child becoming new parent).
  // We must remove nei's contribution from cur and then add cur's contribution to nei.
  private void reroot(int cur, int nei, int[][] dp) {
    // --- Remove nei's contribution from cur ---
    // The candidate contributed by nei is:
    int candidate = getNodeValue(nei) + dp[nei][0];
    // If nei provided the best contribution to cur, then after removing it,
    // cur’s new best from children becomes its previous second-best.
    int newParentContribution = (candidate == dp[cur][0]) ? dp[cur][1] : dp[cur][0];
    // Update cur's dp: note that dp[cur] stores only the contribution from its children.
    dp[cur][0] = newParentContribution ;

    // --- Add cur's contribution (now adjusted) to nei ---
    // When cur becomes a child of nei, its contribution to nei is:
    int addVal = getNodeValue(cur) + dp[cur][0];
    // We update nei's dp by inserting this candidate.
    if (addVal > dp[nei][0]) {
      dp[nei][1] = dp[nei][0];
      dp[nei][0] = addVal;
    } else if (addVal > dp[nei][1]) {
      dp[nei][1] = addVal;
    }
  }

  // getNodeValue returns the marking delay for a node:
  // Even-indexed nodes contribute 2, odd-indexed nodes contribute 1.
  private int getNodeValue(int node) {
    return (node % 2 == 0) ? 2 : 1;
  }

  // Build the undirected graph from the edge list.
  private List<List<Integer>> makeGraph(int[][] edges) {
    int n = edges.length + 1;
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] edge : edges) {
      int u = edge[0], v = edge[1];
      graph.get(u).add(v);
      graph.get(v).add(u);
    }
    return graph;
  }
}
