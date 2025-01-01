package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {

  /***
   *
   * Here the graph is already created in the adjacency list format which solves our problem, otherwise we would have to
   * create a graph out of that list please check the breadfirst solution for more details
   */
  public List<Integer> dfsOfGraph(List<List<Integer>> graph) {
    boolean[] visited = new boolean[graph.size()];

    List<Integer> dfsTraversal = new ArrayList<>();

    /**
     * Same as BFS way since we are just asked to find the dfs from 0 and the graph does not have multiple components
     *
     * we are good
     *
     * otherwise we should have done this
     *
     * we could go many ways the dfsTraversal result saving let's say separate for multiple components could also be done
     *
     * for(int currentVertex = 0 ; currentVertex < vertices ; currentVertex++){
     *    if(!visited[currentVertex]){
     *        dfs(currentVertex, visited, dfsTraversal, graph);
     *
     * }
     */
    dfs(0, visited, dfsTraversal, graph);

    return dfsTraversal;
  }

  private void dfs(Integer currentNode, boolean[] visited, List<Integer> dfsTraversal, List<List<Integer>> graph) {
    visited[currentNode] = true;
    dfsTraversal.add(currentNode);
    for (Integer neighbour : graph.get(currentNode)) {
      if (!visited[neighbour])
        dfs(neighbour, visited, dfsTraversal, graph);
    }
  }
}
