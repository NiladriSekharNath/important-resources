package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StronglyConnectedComponentsByKosarajuAlgorithm {
  /***
   * SCC using KosaRaju's Alrgorithm
   *
   * Steps
   *  1: Sort the graph by their finishing time
   *      this is simply done by Performing DFS for all nodes, for a node once it's neighbours are visited
   *      we save the currentNode in a stack. Kind of like a Topological Sort on a graph( but not reversing the
   *      graph)
   *  2: Transpose the edges of the graph (node -> neighbour, after transpose: neighbour -> node)
   *  3: Once we get the order in the stack pop each node from the stack if node is not visited
   *     perform the dfs again and store the result of the graph
   *     (this works because for a SCC component in a graph even after it is reversed, the SCC would be visited
   *     in a single shot non SCC would not be visited in that traversal)
   *
   *
   * the inituition is very simple let's say originally we have a graph like this
   *
   *  1 -> 2
   * /\    |
   * |    \/
   * 3 <- 4 -> 5
   *
   * this graph contains two SCC 1 -> 2 -> 3 -> 4 & single SCC 5
   *
   * now in the above graph component 1, 2, 3, 4 every pair of node in this component is reachable to each other
   *
   * and
   *
   * but if consider the compoent 5 as well any pair 3,5 is not reachable (we can reach 5 from 3 but we can't reach 3 from 5)
   *
   * Now in this graph : If we simply did the dfs we would not be able to perform this
   *
   * because
   *
   * dfs(1) -> dfs(2) -> dfs(4) -> dfs(3)
   *                            -> dfs(5)
   *
   * we can't know for sure what is the order so, but if we observer carefully we can find out this
   *
   * if we call 1 -> 2 -> 3 -> 4 as SCC1 and 5 as SCC2
   *
   * we see
   *
   * if we move SCC1 -> SCC2 we can find the components
   *
   * as SCC1 and SCC2,
   *
   * So the steps
   *
   * 1: traverse the graph perform dfs and store all the entries in the stack
   * as follows [1, 2, 4, 5, 3] -> now the stack is containing the node after finishing in the reverseOrder(similarly like topological sort dfs)
   *        TOP = 1 AND LOWEST ELEMENT = 3
   *
   * 2: now transpose the graph
   *
   *    we did this because if we originally had
   *
   *       SCC1 -> SCC2
   *
   *       after transpose, we got:
   *
   *       SCC1 <- SCC2
   *
   *       and we start from the stack top if not visited and we do dfs now we would be able to get each individual component
   *
   *       Original graph :
   *
   *        1 -> 2
   *       /\    |
   *       |    \/
   *       3 <- 4 -> 5
   *
   *      After transpose of our graph we got
   *
   *       1 <- 2
   *       |   /\
   *      \/   |
   *      3 -> 4 <- 5
   *
   *  3: Now take each top element from stack and perform dfs now
   *    stack top 1 and end 3
   *    [1, 2, 4, 5, 3]
   *
   *    we take 1: perform dfs(1)
   *
   *    we get component 1 -> 2 -> 3 -> 4 and add this to our scc list
   *
   *    and now after first scc we see 1, 2, 4 is visited
   *
   *    and we find 5 not visited then we perform dfs on 5
   *
   *    we add 5 to our stack
   *
   *    and see 3 is also visited
   *
   *    Important Point : This works because when we tranpose the graph The SCC is remains unchanged
   *
   *    SCC1 ->  SCC2 after tranpose SCC1 <- SCC2
   *
   *    but the component outside SCC1 changed and we can treat it as a separate component
   *
   *
   *
   *
   */
  public List<List<Integer>> stronglyConnectedComponents(int n, List<List<Integer>> graph) {
    Stack<Integer> stack = new Stack<>();

    boolean[] visited = new boolean[n];

    /**
     *
     * Step 1: perform DFS and store the nodes in the stack
     */

    for (int currentNode = 0; currentNode < n; currentNode++) {
      if (!visited[currentNode]) {
        performDfsSaveNodesToStack(currentNode, graph, visited, stack);
      }
    }


    /**
     * Step 2: Tranpose the graph
     *
     * transpose the graph and since we need to use the visited array again in the third step
     * we can mark that as unvisited again
     */

    List<List<Integer>> transposedGraph = new ArrayList<>();

    for (int currentNode = 0; currentNode < n; currentNode++) {
      transposedGraph.add(new ArrayList<>());
    }

    for (int currentNode = 0; currentNode < n; currentNode++) {

      visited[currentNode] = false;

      for (int neighbour : graph.get(currentNode)) {
        transposedGraph.get(neighbour).add(currentNode);
      }
    }


    /**
     *
     * Step 3: Using the nodes from the previous stack and perform a dfs again however this time we are storing the
     * components also
     *
     * we can have a variable 'scc' to keep track of all components
     *
     *
     */

    List<List<Integer>> scc = new ArrayList<>();

    while (!stack.isEmpty()) {
      int currentNode = stack.pop();

      if (!visited[currentNode]) {
        /**
         * scc++
         */
        scc.add(dfsOnTGraph(currentNode, transposedGraph, visited));
      }
    }

    return scc;

  }

  private void performDfsSaveNodesToStack(int currentNode, List<List<Integer>> graph, boolean[] visited, Stack<Integer> stack) {
    visited[currentNode] = true;

    for (int neighbour : graph.get(currentNode)) {
      if (!visited[neighbour]) {
        performDfsSaveNodesToStack(neighbour, graph, visited, stack);
      }
    }

    stack.add(currentNode);
  }

  private List<Integer> dfsOnTGraph(int currentNode, List<List<Integer>> graph, boolean[] visited) {
    List<Integer> currentSCComponent = new ArrayList<>();

    visited[currentNode] = true;
    currentSCComponent.add(currentNode);

    for (int neighbour : graph.get(currentNode)) {
      if (!visited[neighbour]) {
        currentSCComponent.addAll(dfsOnTGraph(neighbour, graph, visited));
      }
    }

    return currentSCComponent;
  }
}
