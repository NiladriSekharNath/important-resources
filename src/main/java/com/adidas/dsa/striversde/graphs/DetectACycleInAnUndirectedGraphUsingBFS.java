package com.adidas.dsa.striversde.graphs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The general idea of finding a graph has a cycle or not is we are doing this. This approach is done using BFS
 *
 *  0 --- 1
 *  |     |
 *  3 --- 2
 *
 *  Adjacency List : 0 -> [1, 3], 1 -> [0, 2], 2 -> [1, 3], 3 -> [0, 2],
 *
 *  If you see in the above graph let's say we start the bfs from 0, then we go to either vertex 1 or 3 (usually we
 *  go to 1 since the adjacency list is usually ordered so we move there let's say
 *
 *  when we moving from 1 (when 1 is the source) we see 0 is already visited but we can't call this a cycle because we
 *  moved from 0, (parent = 0 so only one visited[array] won't help we need to know the parents as well)
 *
 *  for 0 -> we add [1, 0], [3, 0]
 *
 *  for 1 -> we add [2, 1] since 0 is already visited as parent
 *
 *  for 3 -> when we try to add 0, since 0 is already visited as the parent but when we try for 2, we get that
 *           already visited, now if already visited if it's not the parent since from 3 when we see 2 is not visited and
 *           not the parent that means it is already visited previously this can happen only when there is a cycle
 *
 *
 *  now when we move from 1, we mark 2 as visited and add in the queue like this [2,1] but for the bfs when we are going
 *  level by level we are moving we already have for 2 as visited from 1 and then if we try again to mark from 3 for 2
 *  we get this problem like 2 is already visited in case of 3, so a cycle
 *
 *  Also for all multi component graph we try the same for all the components
 *
 *
 *
 *
 */
public class DetectACycleInAnUndirectedGraphUsingBFS {

  public boolean hasCycle(List<List<Integer>> graph) {

    int vertices = graph.size();
    boolean[] visited = new boolean[vertices];
    for(int currentVertex = 0; currentVertex < vertices; currentVertex++){
      if(!visited[currentVertex])
        if(detectCycle(currentVertex, graph, visited))
          return true;
    }
    return false;
  }

  private boolean detectCycle(int source, List<List<Integer>> graph, boolean[] visited){
    /**
     * we are taking the int[][] to determine the source and the parent where the first value [0] = sourceNode,
     * [1] = parent
     *
     * For the first node since there are no parents we are marking that as -1;
     */
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{source, -1});
    visited[source] = true;

    while(!queue.isEmpty()){
      int[] currentNode = queue.poll();
      int currentVertex = currentNode[0], parent =  currentNode[1];

      for(Integer neighbour : graph.get(currentVertex)){
        if(!visited[neighbour]){
          visited[neighbour] = true;
          queue.add(new int[]{neighbour, currentVertex});
        }

        else if(neighbour != parent)
          return true;
      }
    }

    return false;
  }
}
