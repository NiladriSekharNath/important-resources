package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Find the topological sort order of a Graph using dfs
 *
 *     5 --> 0 <-- 4
 *     |           |
 *    \/          \/
 *    2 --> 3 --> 1
 *
 *    For this graph one of the topological sort is 5 4 2 3 1 0
 *
 *    Few points topological sort for a graph can only be possible for a DAG
 *
 *    other than that it is not possible
 *
 *    it's a linear ordering of vertices such that if there is an edge between u & v, u appears
 *    before v in that ordering
 *
 *    for say there is an edge 5 --> 2, in the topo sort we have 5, ..., 2 what I mean is 5 comes before 2
 *
 *    also if you see here
 *
 *    Approach is pretty simple we are performing dfs once it is done we are storing the values in a stack
 *
 *    once all the nodes are complete we do this
 *
 *    pop elements from the stack and store it in a list
 *
 *
 */
public class TopologicalSortUsingDFS {
  static List<Integer> topologicalSort(List<List<Integer>> graph) {
    Stack<Integer> stack = new Stack<>();
    int vertices = graph.size() ;
    boolean[] visited = new boolean[vertices];

    for(int currentVertex = 0 ; currentVertex < vertices; currentVertex++){
      if(!visited[currentVertex])
        dfs(currentVertex, graph, visited, stack);
    }

    List<Integer> topologicalOrder = new ArrayList<>();
    while(!stack.isEmpty())
      topologicalOrder.add(stack.pop());

    return topologicalOrder;
  }

  private static void dfs(int currentNode, List<List<Integer>> graph, boolean[] visited, Stack<Integer> stack){
    visited[currentNode] = true;

    for(int neighbour : graph.get(currentNode)){
      if(!visited[neighbour])
        dfs(neighbour, graph, visited, stack);
    }

    stack.push(currentNode);
  }
}
