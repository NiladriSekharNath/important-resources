package com.adidas.dsa.striversde.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given an undirected graph to us with Unit weights and a source we are required to find the minimum distance from the
 * source to all nodes
 *
 * now for this we do a simple bfs
 *
 * we create a distance array which represents the shortest distance and initialize that to "Integer.MAX_VALUE/2" to avoid overflow
 *
 * once that is done we set distance[source] = 0 ;
 *
 * and add the source to the queue
 *
 * for each entry in the queue() {
 *   we perform these steps
 *   1. take the front element from the queue (as in bfs) let's say that is 'currentnode'
 *   2. for each neighbour in the adjacency list we perform edge relaxation, check if dist[currentNode] + 1 < dist[neighbour]
 *        dist[neighbour] = 1 + dist[currentNode]
 *
 *        and adding the neighbour to the queue
 *
 *        (meaning we are checking if there is a better path from currentNode to neighbour(destination node) via the currentNode
 *
 *        if yes we are setting that value add the neighbour back to the queue to continue the same steps)
 * }
 */
public class ShortestPathFromSourceToDestInUndirectedGraphHavingUnitWeight {


  public int[] shortestPath(List<List<Integer>> graph, int source) {
    int vertices = graph.size();

    int[] dist = new int[vertices];

    Arrays.fill(dist, Integer.MAX_VALUE / 2);


    Queue<Integer> queue = new LinkedList<>();

    queue.add(source);

    dist[source] = 0;

    while (!queue.isEmpty()) {

      Integer currentSource = queue.poll();

      for (Integer neighbour : graph.get(currentSource)) {
        if (dist[currentSource] + 1 < dist[neighbour]) {

          dist[neighbour] = dist[currentSource] + 1;
          queue.add(neighbour);

        }
      }
    }

    for (int currentVertex = 0; currentVertex < vertices; currentVertex++) {
      if (dist[currentVertex] == Integer.MAX_VALUE / 2)
        dist[currentVertex] = -1;
    }

    return dist;

  }
}
