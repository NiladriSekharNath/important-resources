package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CheapestFlightsKStops {

  public int CheapestFLight(int n, int flights[][], int src, int dst, int k) {
    List<List<int[]>> graph = makeGraph(flights, n);

    int[] dist = new int[n];
    Arrays.fill(dist, (int) 1e9);

    dist[src] = 0;


    /***
     * in the queue we are storing the stops, currentNode, dist[currentNode]
     *
     * we could have taken a priority queue and kept track of the stops however only point is stops will increase one at a time
     *
     * let's we have a path 0 -> 1 -> 2
     *
     * 0 stops -> 1 stop -> 2 stop
     *
     * so queue also works we don't add the higher stops value
     *
     * Also, a point to note here is that do we really need a priority queue for carrying out the algorithm? The answer for that is No because when we are storing everything in terms of a number of stops, the stops are increasing monotonically
     * which means that the number of sops is increasing by 1 and when we pop an element out of the queue, we are always popping the element with a lesser number of stops first. Replacing the priority queue with a simple queue will let us eliminate an extra log(N) of the complexity of insertion-deletion in a priority queue which would in turn make our algorithm a lot faster.
     *
     * Here we specifically need to keep the dist[currentNode] in minHeap reason is below
     *
     * 1. Two different paths to the same node can have different numbers of stops. In some cases, a path with more stops may offer a cheaper price. The distance[] array may not properly capture these relationships between cost and stops, leading to incorrect results.

     2. Let’s say you have two paths to a destination:

     Path A reaches the destination with 2 stops and a cost of 100.
     Path B reaches the same destination with 3 stops and a cost of 90.
     If you only use distance[], after processing Path B, the distance[] array will record 90 as the minimum cost to reach the destination, overwriting Path A's information. This could lead to an invalid solution if your algorithm later tries to use Path B (which exceeds the stop limit k).


     */
    Queue<int[]> queue = new LinkedList<>();
    queue.add(new int[]{0, src, 0});

    while (!queue.isEmpty()) {
      int[] currentTriplet = queue.poll();
      int stops = currentTriplet[0], currNode = currentTriplet[1], currNodeDist = currentTriplet[2];
      /**
       * we are not considering anything greater than k stops as at most k would already have our answer possibly
       * stored in dist[dst] i
       */
      if (stops > k) continue;

      for (int[] neighEdge : graph.get(currNode)) {
        int targetNode = neighEdge[0], weight = neighEdge[1];
        /**
         * here we added a stops <= k, because excluding the destination we are allowed k stops
         * meaning we can k + 1 count to destination at max
         */
        if (currNodeDist + weight < dist[targetNode] && stops <= k) {
          dist[targetNode] = currNodeDist + weight;
          queue.add(new int[]{stops + 1, targetNode, dist[targetNode]});

        }
      }
    }
    if (dist[dst] == 1e9) return -1;

    return dist[dst];

  }


  private List<List<int[]>> makeGraph(int[][] flights, int n) {
    List<List<int[]>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] edgeWeight : flights) {
      int u = edgeWeight[0], v = edgeWeight[1], w = edgeWeight[2];
      graph.get(u).add(new int[]{v, w});
    }

    return graph;
  }
}
