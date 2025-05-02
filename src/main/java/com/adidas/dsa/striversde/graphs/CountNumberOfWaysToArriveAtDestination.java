package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 *  You are in a city that consists of n intersections numbered from 0 to n - 1 with bi-directional roads between some intersections.
 *  The inputs are generated such that you can reach any intersection from any other intersection
 *  and that there is at most one road between any two intersections.
 *
 * You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei]
 * means that there is a road between intersections ui and vi that takes timei minutes to travel.
 * You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the shortest amount of time.
 *
 * Return the number of ways you can arrive at your destination in the shortest amount of time. Since the answer may be
 * large, return it modulo 109 + 7.
 *
 * basically we are given few graphs edges and a graph is to be made
 *
 * we are required to count the paths of shortest lengths
 *
 *
 */

public class CountNumberOfWaysToArriveAtDestination {
  private final int mod = (int) 1e9;

  public int countPaths(int n, int[][] roads) {
    List<List<int[]>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }

    for (int[] road : roads) {
      int u = road[0], v = road[1], time = road[2];
      graph.get(u).add(new int[]{v, time});
      graph.get(v).add(new int[]{u, time});
    }

    PriorityQueue<long[]> minHeap = new PriorityQueue<>(
        (entry1, entry2) -> (int) (entry1[0] != entry2[0] ? entry1[0] - entry2[0] : entry1[1] - entry2[1]));

    long[] count = new long[n];

    long[] distance = new long[n];

    Arrays.fill(distance, (int) 1e9);

    distance[0] = 0;

    count[0] = 1;

    minHeap.add(new long[]{distance[0], 0});

    while (!minHeap.isEmpty()) {
      long currentNodePair[] = minHeap.poll(),  dist = currentNodePair[0];

      int cN = (int) currentNodePair[1];


      /**
       * we are using standard Dijkstras to find the solution and also the valid path counting logic is
       * same as the LongestIncreasingSubsequenceCount logic
       *
       * We use the standard Dijkstra's algorithm because it guarantees the shortest path from a single source to any other node.
       * If we obtain a shortest distance of 'x' to a node, any subsequent path to that node (processed later in the min-heap)
       * will have an equal or greater distance."
       *
       *  u
       *   \ w
       *    v
       *
       *    think about it if ->  dist[u] + w < dist[v]                 same logic as if count[v] (meaning there are let's say 5 paths
       *                            then count[v] = count[u]            to 'u' and we add some weight 'w' and that distance is
       *                                                                less than the distance[v] then we have the same count
       *                                                                for count[v]
       *    if dist[u] + w = dist[v]
       *
       *    u
       *     \ w
       *      v
       *     / x
       *   p
       *
       *    here above we see there were already few paths let's say from 'x' which contributed to the distance
       *    now again some dist node 'u' + w = dist 'v' gave the same path
       *
       *    so same length paths from diff 'u' we are adding the paths again
       *
       *    count[v] += count[u]
       *
       *
       *
       *
       */
      for (int[] neighbour : graph.get(cN)) {
        int v = neighbour[0], w = neighbour[1];

        if (dist + w < distance[v]) {
          distance[v] = dist + w;
          count[v] = count[cN];
          minHeap.add(new long[]{distance[v], v});
        }
        else if (dist + w == distance[v]) {
          count[v] = (count[v] + count[cN]) % mod;
        }
      }


    }

    return (int) count[n - 1];

  }
}
