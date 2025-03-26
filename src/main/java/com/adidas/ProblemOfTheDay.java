package com.adidas;

import org.springframework.beans.factory.parsing.Problem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;

public class ProblemOfTheDay {

  private final int mod = (int)(1e9 + 7);

  public int countPaths(int n, int[][] roads) {
    List<List<int[]>> graph = makeGraph(n, roads);

    int[] dist = getShortestPath(n, graph);

    System.out.println(dist[n - 1]);

    return getShortestPathCount(n, graph, dist[n - 1]);

  }



  private List<List<int[]>> makeGraph(int n, int[][] roads){

    List<List<int[]>> graph = new ArrayList<>();

    for(int cN = 0; cN < n; cN++) graph.add(new ArrayList<>());

    for(int[] edge : roads){
      int u = edge[0], v = edge[1], w = edge[2];
      graph.get(u).add(new int[]{v, w});
      graph.get(v).add(new int[]{u, w});
    }

    return graph;

  }

  private int[] getShortestPath(int n, List<List<int[]>> graph){
    int[] dist = new int[n];
    Arrays.fill(dist, (int)1e9);

    dist[0] = 0;

    PriorityQueue<Integer> minHeap = new PriorityQueue<>((entry1, entry2) -> dist[entry1] - dist[entry2]);

    minHeap.add(0);

    while(!minHeap.isEmpty()){
      int cN = minHeap.poll();
      for(int[] edgeWPair : graph.get(cN)){
        int v = edgeWPair[0], w = edgeWPair[1];
        if(dist[cN] + w < dist[v]){
          dist[v] = dist[cN] + w;
          minHeap.add(v);
        }
      }
    }

    return dist;
  }

  private int getShortestPathCount(int n, List<List<int[]>> graph, int targetDist){
    int dist[] = new int[n];
    long count = 0;

    Arrays.fill(dist, (int)1e9);

    dist[0] = 0;

    PriorityQueue<int[]> minHeap = new PriorityQueue<>((entry1, entry2) -> entry1[0] != entry2[0] ? entry1[0] - entry2[0]
        :   entry1[1] - entry2[1]);

    minHeap.add(new int[]{dist[0], 0});

    while(!minHeap.isEmpty()){

      int cNPair[] = minHeap.poll(), distance = cNPair[0], cN = cNPair[1];

      if(distance > targetDist) continue;

      if(cN == n - 1) count = (count + 1) % mod;

      for(int[] edgeWPair : graph.get(cN)){
        int v = edgeWPair[0], w = edgeWPair[1];
        if(distance + w <= dist[v]){
          dist[v] = distance + w;
          minHeap.add(new int[]{dist[v], v});
        }

      }
    }

    return (int) count;
  }


  public int countDays(int days, int[][] meetings) {

    TreeMap<Integer, Integer> diff = new TreeMap<>();
    int previousDay = days;

    for (int[] meeting : meetings) {
      previousDay = Math.min(previousDay, meeting[0]);
      int start = meeting[0], end = meeting[1] + 1;
      diff.put(start, diff.getOrDefault(start, 0) + 1);
      diff.put(end, diff.getOrDefault(end, 0) - 1);
    }


    int prefixSum = 0, noMeetingDays = previousDay - 1, currentDay = 0;

    for (var entry : diff.entrySet()) {
      currentDay = entry.getKey();
      if (prefixSum == 0) {
        noMeetingDays += currentDay - previousDay;
      }

      previousDay = currentDay;
      prefixSum += entry.getValue();
    }

    noMeetingDays += days - previousDay + 1;

    return noMeetingDays;


  }




  public static void main(String[] args){
    //new ProblemOfTheDay().checkValidCuts(5, new int[][]{{1, 0, 5, 2}, {0, 2, 2, 4}, {3, 2, 5, 3}, {0, 4, 4, 5}});
  }
}
