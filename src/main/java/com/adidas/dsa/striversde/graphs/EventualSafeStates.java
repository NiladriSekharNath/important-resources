package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a directed graph find nodes that are safe Nodes.
 *
 * Any node that can reach a terminal node finally is a safe node
 *
 * A terminal node is A Node that has no outgoing edge
 *
 * let's say we have this edge
 *
 * 1 -> 2 -> 3
 *
 * all nodes in this graph is a safe node as it reaches to a terminal node  3
 *
 *
 * now for this graph
 *
 * 1 -> 2 -> 6
 * /\   |
 * |   \/
 * 4 <- 3 <- 5
 *
 * in this graph only the node 6 is a safe node as it has not outgoing edges
 *
 * now if you see this all other nodes eventually reach a cycle( node 5) the cycle
 * 1 -> 2 -> 3 -> 4 -> 1 and other nodes are part of the cycle
 *
 * the problem boils down to if a node is part of the cycle then or is somehow connected to the cycle then it is a terminal
 * node
 *
 * Here we use the cycle detection algorithm for a directed graph if we let's say we find a cycle when visited[node] = 1
 *
 * any point then we return for those nodes ultimately in the visited array nodes that are not part of the cycle
 * will be marked 2
 */
public class EventualSafeStates {
  public List<Integer> eventualSafeNodes(int n, List<List<Integer>> graph) {

    int[] visited = new int[n];



    for(int i = 0 ; i < n ; i++){
      if(visited[i] == 0)
        dfs(i, graph, visited);
    }

    List<Integer> safeNodes = new ArrayList<>();
    for(int i = 0 ; i < n; i++){
      if(visited[i] == 2)
        safeNodes.add(i);
    }
    return safeNodes;
  }

  private boolean dfs(int currentNode, List<List<Integer>> graph, int[] visited){
    visited[currentNode] = 1;

    for(int neighbour : graph.get(currentNode)){
      if(visited[neighbour] == 0){
        if(dfs(neighbour, graph, visited))
          return true;

      }

      else if(visited[neighbour] == 1) return true;
    }

    visited[currentNode] = 2;

    return false;
  }


  /**
   *
   * now this question could also be solve using the bfs topological sort approach
   *
   *    1 -> 2 -> 6
   *    /\   |
   *    |   \/
   *    4 <- 3 <- 5
   *
   *  let's say we have this graph which has only one safe node 6 now, (nodes having outdegree as 0)
   *
   *
   *  Let's reverse the graph
   *
   *  1 <- 2 <- 6
   *  |    /\
   * \/    |
   *  4 -> 3 -> 5
   *
   *  here the indegree of 6 is zero this is the safe node that has indegree = 0
   *
   *  Initially the terminal nodes are those who have outdegree 0 like 6 above
   * but after reversal the terminal nodes becomes those which have indegree 0
   * so we can apply Kahn's algo to find all the nodes connected to it  which have linear dependency on the terminal
   * node or is on the path which leads to terminal node
   * so if the nodes is a part of a cycle or points to a cycle , that path cannot lead to terminal node as each node
   * in that  path will have cyclic dependency
   *
   * Now for a cycle even if we remove all nodes which have indegree = 0, standard bfs appraoch we would still have nodes
   * that have indegree > 0
   *
   *       1 <- 2
   *       |    /\
   *      \/    |
   *      4 -> 3 -> 5
   *
   *      see here now what we did here in the standard bfs topological sort if we have something that has indegree = 0
   *
   *      that is part of the queue so if we get a node from the queue that node would definitely be a safe node
   *
   *
   */
  public List<Integer> eventualSafeNodesUsingBFSTopologicalSort(int n, List<List<Integer>> graph) {

    boolean[] visited = new boolean[n];

    List<List<Integer>> graphT = transposeGraph(n, graph);

    topologicalSort(graphT, visited);

    List<Integer> safeNodes = new ArrayList<>();
    for(int i = 0 ; i < n; i++){
      if(visited[i])
        safeNodes.add(i);
    }
    return safeNodes;
  }

  private void topologicalSort(List<List<Integer>> graph, boolean[] visited) {
    List<Integer> topoSort = new ArrayList<>();
    int size = graph.size();

    int[] indegree = new int[size];

    for(int currentNode = 0 ; currentNode < size; currentNode++){
      for(int neighbour : graph.get(currentNode))
        indegree[neighbour]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for(int currentNode = 0 ; currentNode < size ; currentNode++){
      if(indegree[currentNode] == 0){
        queue.add(currentNode);

      }
    }

    while(!queue.isEmpty()){
      int currentNode = queue.poll();
      topoSort.add(currentNode);
      visited[currentNode] = true;
      for(int neighbour : graph.get(currentNode)){
        indegree[neighbour]--;
        if(indegree[neighbour] == 0){
          queue.add(neighbour);

        }
      }
    }


  }

  private List<List<Integer>> transposeGraph(int n, List<List<Integer>> graph){
    List<List<Integer>> graphT = new ArrayList<>();
    for(int i = 0 ; i<n; i++){
      graphT.add(new ArrayList<>());
    }

    for(int i = 0 ; i < n; i++){
      for(int neighbour : graph.get(i)){
        graphT.get(neighbour).add(i);
      }
    }

    return graphT;
  }

}
