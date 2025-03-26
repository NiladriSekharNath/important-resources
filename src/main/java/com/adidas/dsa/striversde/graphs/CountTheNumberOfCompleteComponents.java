package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given an integer n. There is an undirected graph with n vertices, numbered from 0 to n - 1. You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting vertices ai and bi.
 *
 * Return the number of complete connected components of the graph.
 *
 * A connected component is a subgraph of a graph in which there exists a path between any two vertices, and no vertex of the subgraph shares an edge with a vertex outside of the subgraph.
 *
 * A connected component is said to be complete if there exists an edge between every pair of its vertices.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4]]
 * Output: 3
 * Explanation: From the picture above, one can see that all of the components of this graph are complete.
 * Example 2:
 *
 *
 *
 * Input: n = 6, edges = [[0,1],[0,2],[1,2],[3,4],[3,5]]
 * Output: 1
 * Explanation: The component containing vertices 0, 1, and 2 is complete since there is an edge between every pair of two vertices. On the other hand, the component containing vertices 3, 4, and 5 is not complete since there is no edge between vertices 4 and 5. Thus, the number of complete components in this graph is 1.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 50
 * 0 <= edges.length <= n * (n - 1) / 2
 * edges[i].length == 2
 * 0 <= ai, bi <= n - 1
 * ai != bi
 * There are no repeated edges.
 *
 *
 * Let's say the graph has multiple components we have to count the complete components
 *
 * Example :
 *
 *      0                4
 *    /  \      3        \
 *   1 - 2           6 -  5
 *
 *   Total Components in the graph : 3
 *   Total Complete Connected Components = 2
 *
 *   Connected components mean :
 *
 *   every node in the component should connect to each other in the same component
 *
 *   and basically if n is the size of nodes = then edges for a complete connected component
 *
 *   n = 3,  (n * (n - 1))/2  = (3 * 2)/2 = 3
 *
 *
 *
 *
 *
 */
public class CountTheNumberOfCompleteComponents {

  /**
   * @param n
   * @param edges
   * @return DSU approach
   *
   * Main idea of the below DSU approach is
   *
   * We are using DSU Union by Size like
   *
   * let's say  u = 1, v = 2 :
   *
   *   1    2
   *   u    v
   *       /
   *      w
   *
   *
   *      Now let's say the above nodes we are joining :
   *      u = 1, v = 2
   *
   *         v
   *        / \
   *       u   w
   *
   *       v = 3 now
   *
   *       meaning while union by Size we are adding the size of the components the root node contains the count of
   *       all children
   *
   *
   *   1. Loop in the List of edges -> we loop the edges and union them by size, result for each pair of edge we get the root
   *                       where the size of the nodes(children) are counted
   *   2. Again loop in the list of edges, now getting the parent of the edge pair we find the edges:
   *      Example :
   *
   *          0
   *         / \
   *        1 - 2
   *
   *     edges : [[0, 1], [1, 2], [2, 0]]
   *
   *     we are assuming for the above the parent is 0 for all the nodes above:
   *
   *     now in the loop for 0 - 1 : parent[0] = 0, parent[1] = 0, parent[2] = 0
   *
   *     we are calculating the edges in this component denoted by parent -> 0 : 3 (this component represented by parent has 3 edges)
   *
   *   3. Once done we are simply doing this check,
   *
   *       for the nodes in cN - [0 -> n]
   *        finding the parent (parent[cN] = cN)
   *        we are fetching the node size from the DSU and calculating the expected size of edges
   *          using formula (n * (n - 1))/2
   *        and checking if for the same parent the no of edges in the graph are same (as stored in the matrix)
   *        then we increment the count of components
   *
   *
   *
   *
   *
   */
  public int countCompleteComponents(int n, int[][] edges) {
    int countComplComponents = 0;
    DSU dsu = new DSU(n);

    Map<Integer, Integer> countEdgesComponent = new HashMap<>();

    for (int[] edge : edges) {
      dsu.unionBySize(edge[0], edge[1]);
    }

    for (int[] edge : edges) {
      int parent = dsu.findParent(edge[0]);
      countEdgesComponent.put(parent, countEdgesComponent.getOrDefault(parent, 0) + 1);
    }

    for (int cN = 0; cN < n; cN++) {

      if (dsu.findParent(cN) == cN) {


        int nodeSize = dsu.getNodeSize(cN);
        int expectedSize = (nodeSize * (nodeSize - 1)) / 2;
        if (expectedSize == countEdgesComponent.getOrDefault(cN, 0)) {
          countComplComponents++;
        }
      }
    }
    return countComplComponents;

  }


  private class DSU {
    int[] parent;
    int[] size;
    int nodes = 0;

    public DSU(int n) {
      this.nodes = n;
      this.parent = new int[n];
      this.size = new int[n];
      for (int cN = 0; cN < n; cN++) {
        parent[cN] = cN;
        size[cN] = 1;
      }
    }

    public int findParent(int node) {
      if (parent[node] == node) return node;
      return parent[node] = findParent(parent[node]);
    }

    public void unionBySize(int u, int v) {
      int parentU = findParent(u), parentV = findParent(v);
      if (parentU == parentV) return;
      if (size[parentU] <= size[parentV]) {
        parent[parentU] = parentV;
        size[parentV] += size[parentU];
      } else {
        parent[parentV] = parentU;
        size[parentU] += size[parentV];
      }
    }

    public int getNodeSize(int node) {
      return this.size[node];
    }
  }

  /**
   *
   * @param n
   * @param edges
   * @return
   *
   * Almost similar logic however using DFS we are trying this,
   *
   * Instead, we can take advantage of a key property of complete graphs: in a complete graph with n vertices, there must be exactly
   *
   * n⋅(n−1))/2  unique edges = equal to the number of pairs of nodes in the graph.
   *
   * Since our graph is undirected but our adjacency list counts each edge twice (once from each endpoint),
   * the total edge count from the adjacency lists should be n⋅(n−1)
   *
   *
   * example :
   *
   *         0
   *        / \
   *       1 - 2
   *
   *  edges : [[0, 1], [1, 2], [2, 0]]
   *
   *
   *  graph :
   *      0: [1, 2]
   *      1: [0, 2]
   *      2: [0, 1]
   *
   * for (int[] edge : edges) {
   *       int u = edge[0], v = edge[1];
   *       graph.get(u).add(v);
   *       graph.get(v).add(u);
   *     }
   *
   * when we compute the adjacency list for a directed graph we add twice for a same edge:
   *
   *  [0 - 1], [1 - 0]
   *
   *  that's a reason we are calculating twice also:
   *
   *  countComponents[1] += graph.get(cN).size(); ----------->  [at every cN (currentIndex)], we [2 * edges]
   *
   *  so instead of (n * (n - 1))/2 = edges
   *
   *  we get 2*edges
   *
   *  so now we compare it with -> 2 * edges = (n * (n - 1))/2
   *
   *  we increment the components
   *
   *
   *
   *
   */

  public int countCompleteComponentsDFS(int n, int[][] edges) {
    boolean[] visited = new boolean[n];

    int countCompleteComponents = 0;

    List<List<Integer>> graph = makeGraph(n, edges);

    for (int cN = 0; cN < n; cN++) {
      int[] countComponents = new int[]{0, 0};
      if (!visited[cN]) {
        dfs(cN, graph, countComponents, visited);
        int nodes = countComponents[0], edgeCount = countComponents[1];
        if (nodes * (nodes - 1) == edgeCount) {
          countCompleteComponents++;
        }
      }
    }

    return countCompleteComponents;
  }

  private void dfs(int cN, List<List<Integer>> graph, int[] countComponents, boolean[] visited) {
    visited[cN] = true;

    countComponents[0] += 1;
    countComponents[1] += graph.get(cN).size();

    for (int neighbour : graph.get(cN)) {
      if (!visited[neighbour]) {
        dfs(neighbour, graph, countComponents, visited);
      }
    }
  }

  private List<List<Integer>> makeGraph(int n, int[][] edges) {
    List<List<Integer>> graph = new ArrayList<>();

    for (int cN = 0; cN < n; cN++) {
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
