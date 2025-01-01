package com.adidas.dsa.striversde.graphs;

import java.util.List;

/**
 * detecting the cycle in a directed graph we are using this approach
 *
 * let's say we have this directed graph
 *
 *        0 ---> 1
 *        |     |
 *       \/    \/
 *       3 ---> 2
 *
 *
 * graph adjacency list : 0 -> [1,3], 1 -> [2], 3 -> [2], 2 :[]
 *
 * Now if we tried the previous approach of the directed graph
 * when we move first in the path 0 -> 1 -> 2 we would make the node2 as visited in the visited array
 *
 * but again when we come to node2 in the path 0 -> 3 -> 2 after backtracking till 0 and we explore path 3 then the problem
 * that would rise here is we would find the path 2 is already visited we would declare that it is a cycle
 *
 * but it is not true there is no cycle here in the above graph which
 *
 * so what we do instead is
 *
 * take two arrays visited and pathvisited where we mark like this
 *
 * 0 -> 1 -> 2
 * visited [0 : 1, 1 : 1, 2 : 1, 3 : 0] here 1 means visited
 * pathVisited [0 : 1, 1 : 1, 2 : 1, 3 : 0] here 1 means visited
 *
 * but the only point during the backtracking step in node2 we again mark the
 * nodes in pathVisited as 0 again
 *
 * pathVisited [0 : 1, 1 : 0, 2 : 0, 3 : 0], here the 0 is not marked as 0, as all paths in 0 are not explored
 * visited [0 : 1, 1 : 1, 2 : 1, 3 : 0], however the visited remains the same
 *
 * now when we explore :
 *
 * 0 -> 3 -> 2
 *
 * pathVisited [0 : 1, 1 : 0, 2 : 1, 3 : 1], now here we mark them as pathVisited, this is particularly helpful in this
 * graph
 *
 *           0 ---> 1
 *  *       /\      |
 *  *       |      \/
 *  *       3 <--- 2
 *
 *  this above graph has a cycle
 *
 *  in the visited and the pathVisited array
 *
 *  let's say when we explore
 *
 *  0 -> 1 -> 2 -> 3 -> 0
 *
 *  visited [0 : 1, 1 : 1, 2 : 1, 3 : 1]
 *  pathVisited [0 : 1, 1 : 1, 2 : 1, 3 : 1]
 *
 *  when we move from 3 to 0
 *
 *  we see 0 is already visited then we check in the pathVisited that node1 in pathVisited is marked as 1
 *  which means there is a valid cycle in the graph
 *
 *
 *
 * "Approach 2": Instead of taking two arrays visited and pathVisited
 *
 * we take single array
 *
 * visited
 * wherein for the pathVisited value we simply mark the value as 2
 *
 * this is still helpful in the cycle case
 *
 *              0 ---> 1
 *  *  *       /\      |
 *  *  *       |      \/
 *  *  *       3 <--- 2
 *  *
 *  *  this above graph has a cycle
 *
 * visited [0 : 1, 1 : 1, 2 : 1, 3 : 1]
 *
 * 0 -> 1 -> 2 -> 3 -> 0
 *
 * here if the visit is completed we could have came out and marked the visited as 2 but if the visit is 1
 * that means we found a cycle which is good for us as we did this using one array
 *
 *
 *
 *
 *
 */
public class DetectACycleInADirectedGraphUsingDFS {
  public boolean isCyclic(int v, List<List<Integer>> graph) {
    int[] visited = new int[v];
    int[] pathVisited = new int[v];


    for(int currentVertex = 0; currentVertex < v; currentVertex++){
      if(visited[currentVertex] == 0){
        if(hasCycle(currentVertex, graph, visited, pathVisited))
          return true;
      }
    }

    return false;
  }

  private boolean hasCycle(int currentNode, List<List<Integer>> graph, int[] visited, int[] pathVisited){
    visited[currentNode] = 1;
    pathVisited[currentNode] = 1;

    for(int neighbour : graph.get(currentNode)){
      if(visited[neighbour] == 0){
        if(hasCycle(neighbour, graph, visited, pathVisited))
          return true;
      }

      else if(pathVisited[neighbour] == 1)
        return true;
    }

    pathVisited[currentNode] = 0;

    return false;
  }

  public boolean isCyclicApproach2(int v, List<List<Integer>> graph) {
    int[] visited = new int[v];

    for(int currentVertex = 0; currentVertex < v; currentVertex++){
      if(visited[currentVertex] == 0){
        if(hasCycleApproach2(currentVertex, graph, visited))
          return true;
      }
    }

    return false;
  }

  private boolean hasCycleApproach2(int currentNode, List<List<Integer>> graph, int[] visited){
    visited[currentNode] = 1;

    for(int neighbour : graph.get(currentNode)){
      if(visited[neighbour] == 0){
        if(hasCycleApproach2(neighbour, graph, visited))
          return true;
      }

      else if(visited[neighbour] == 1)
        return true;
    }

    visited[currentNode] = 2;

    return false;
  }
}
