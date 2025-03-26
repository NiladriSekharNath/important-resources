package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

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
