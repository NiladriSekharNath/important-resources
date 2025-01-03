package com.adidas.dsa.striversde.graphs;

import java.util.Arrays;

/**
 * okay we are tasked to find the shortest node from a given source all nodes in graph
 *
 * Now we could have used Dijktra's Algorithm but Dijkstra's algorithm fails for negative weight and
 * negative cycles
 *
 * so we would have to use Bellman ford
 *
 * Also Bellman Ford works for only directed graphs 1 -> 2 but if we are given a undirected graph
 *
 *
 * we would have to add 1 - 2
 *
 * 1 -> 2
 * 2 -> 1
 *
 * same weight to make this graph as directed
 *
 * So algorithm goes as follows we relax an edge if we are given n nodes we relax n - 1 times. Also the edges of the graph
 * are not sorted or anything
 *
 * Additionally if we suspect graph contains negative cycles we would have relax one more time
 *
 * if we found a shorter distance then we can say there exist a negative cycle
 *
 *
 * let's say we have
 *     -3
 * 1 < - > 2
 *                               -3       -3
 *  this represents two edges 1 ---> 2 2 --->1
 *
 *  if we keep relaxing we would get smaller values so we can say there exist a negative edge
 *
 *  also in Bellman Ford why we perform n - 1 relaxations ?
 *
 *  consider this graph:
 *     1    1    1   1
 *  0 -> 1 -> 2 -> 3 -> 4
 *
 *
 *
 *  and edges we have like this order
 *
 *  [[3, 4, 1], [2, 3, 1], [1, 2, 1], [0,1,1]]
 *
 *  Since we have n = 5 nodes we need to relax 4(n - 1) times
 *
 *  If we observer properly
 *
 *  and we save our array dist[] = [0 -> 0, 1 -> inf, 2 -> inf, 3 -> inf, 4 -> inf] representedy by [idx -> dist[idx]]
 *
 *  during the iteration 1 last relaxation
 *
 *
 *
 *  relax [3, 4, 1]
 *  relax [2, 3, 1]
 *  relax [1, 2, 1]
 *  relax [0, 1, 1] we see here the distance[1] gets updated as d[0] + 1 < d[1] true as d[0] = 1
 *
 *  dist[] = [0 -> 0, 1 -> 1, 2 -> inf, 3 -> inf, 4 -> inf]
 *
 *  during the iteration 2 last relaxation
 *
 *  relax [3, 4, 1]
 *  relax [2, 3, 1]
 *  relax [1, 2, 1] we see here the distance[2] gets updated as d[1] + 1 < d[2] true as d[1] = 1
 *  relax [0, 1, 1]
 *
 *    dist[] = [0 -> 0, 1 -> 1, 2 -> 2, 3 -> inf, 4 -> inf] dist[2] changes from inf to 2, when src is [0]
 *
 * during the iteration 3 last relaxation
 *  *
 *  *  relax [3, 4, 1]
 *  *  relax [2, 3, 1] we see here the distance[3] gets updated as d[2] + 1 < d[3] true as d[2] = 2
 *  *  relax [1, 2, 1]
 *  *  relax [0, 1, 1]
 *  *
 *  *    dist[] = [0 -> 0, 1 -> 1, 2 -> 2, 3 -> 3, 4 -> inf] dist[3] changes from inf to 3, when src is [0]
 *
 *  during the iteration 4 last relaxation
 *  *  *
 *  *  *  relax [3, 4, 1] we see here the distance[3] gets updated as d[3] + 1 < d[4] true as d[3] = 3
 *  *  *  relax [2, 3, 1]
 *  *  *  relax [1, 2, 1]
 *  *  *  relax [0, 1, 1]
 *  *  *
 *  *  *    dist[] = [0 -> 0, 1 -> 1, 2 -> 2, 3 -> 3, 4 -> 4] dist[4] changes from inf to 4, when src is [0]
 *
 *  If we observe here all the nodes get relaxed but for this case it took n - 1 iterations to relax
 *
 *  so in BellmanFord's Algorithm we would have to relax n - 1 times
 *
 *
 *
 */
public class ShortestPathFromSourceToAllNodesUsingBellmanFord {


  public int[] bellmanFord(int v, int[][] edges, int src) {
    // Write your code here

    int[] dist = new int[v];

    Arrays.fill(dist, (int) 1e8);

    dist[src] = 0;

    for (int i = 0; i < v - 1; i++) {
      for (int[] edge : edges) {
        int sourceNode = edge[0], targetNode = edge[1], weight = edge[2];
        if (dist[sourceNode] != 1e8 && dist[sourceNode] + weight < dist[targetNode]) {
          dist[targetNode] = dist[sourceNode] + weight;
        }
      }
    }

    for (int[] edge : edges) {
      int sourceNode = edge[0], targetNode = edge[1], weight = edge[2];
      if (dist[sourceNode] != 1e8 && dist[sourceNode] + weight < dist[targetNode]) {
        return new int[]{-1};
      }
    }

    return dist;
  }

}
