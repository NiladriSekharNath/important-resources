package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * THOSE WHO ARE WORDERING WHY IT IS  O(N) + O(2E) NOT O(N*2E)
 *
 * For each node, the while loop runs multiple times based on the number of edges connected to that node. Here's how it works:
 *
 * In the first iteration, the loop runs for e1 edges, plus one extra operation for pushing and popping the node.
 * In the second iteration, it runs for e2 edges, plus one extra operation for pushing and popping, and so on.
 * Thus, the total time complexity is the sum of all iterations:
 *
 * (e1 + e2 + ... + en) + (1 + 1 + ... n times).
 * The sum of all the edges connected to each node is equal to the total number of edges, which is 2E (since each edge is counted twice in an undirected graph). Adding the n push/pop operations gives the final complexity:
 *
 * O(V + 2E) because e1 + e2 + ... + en = 2E.
 * So, the overall complexity is O(V + 2E), which simplifies to O(V + E).
 *
 * and the Space Complexity : O(3V) for the vertices
 */
public class BreadFirstSearch {
  public List<Integer> bfsOfGraph(int v, List<List<Integer>> graph) {


    /**
     *
     *
     *
     * since the question asked us to find the BFS from the starting node 0 only this is done otherwise our course of
     * action was
     *
     *
     *
     *  for(int i = 0; i < v; i++){
     *      if(!visited[i])
     *        performBFS(i, graph, visited);
     *  }
     *
     *
     *  Particularly considered for approaches for when the graph is disconnected in components  G => G1, G2, G3
     *
     *  1----2
     *  |    |     5----6    7
     *  3----4
     *
     *  Also just one point if the vertices are in the 1-based numbering then we could make the take the visited array
     *  to be [1 + v]  [0, 1, 2, 3, 4, 5, 6, 7] or take a visited set for that matter
     *
     *  Also here is one more point just for understanding
     *
     *  if the nodes are connected let's say originally we start for component which would trigger the process for
     *
     *  1, 2, 3, 4 [false, true, true, true, true, false, false, false], signifying that the bfs traversal is done
     *            for the G1 component, ignore the 0 as zero based indexing]
     *
     * then when in the visited node we find that 5, is not visited then again in the same array the BFS process is
     *            triggered for G2]
     *
     * and then finally for the 7 node
     *
     */

    boolean[] visited = new boolean[v];

    return performBFS(0, graph, visited);
  }

  private List<Integer> performBFS(int source, List<List<Integer>> graph, boolean[] visited){
    List<Integer> bfs = new ArrayList<>();

    Queue<Integer> queue = new LinkedList<>();

    /**
     * here a particular node is marked as visited when a node is added to the queue
     *
     * which means we can't add the nodes back again
     */
    visited[source] = true;
    queue.add(source);

    while(!queue.isEmpty()){
      /**
       * this could have been size bound if the bfs traversal was required in a level bound fashion
       */
      Integer currentNode = queue.poll();
      bfs.add(currentNode);
      for(Integer neighbour : graph.get(currentNode)){
        if(!visited[neighbour]){
          /**
           * Similarly here we are doing that only once unvisited node is found we add them to the queue
           *
           * meaning this can't be touced again for the bfs process by any other node whose neighbours' we are traversing
           *
           */
          visited[neighbour] = true;
          queue.add(neighbour);

        }
      }
    }

    return bfs;
  }
}
