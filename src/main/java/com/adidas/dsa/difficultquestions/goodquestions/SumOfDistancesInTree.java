package com.adidas.dsa.difficultquestions.goodquestions;

import java.util.ArrayList;
import java.util.List;

public class SumOfDistancesInTree {

  /**
   *
   * @param n
   * @param edges
   * @return
   *
   * Partially solved solution that works but fails with TLE and
   * MLE
   *
   * O(n2) solution that needs to be optimized
   */
  public int[] sumOfDistancesInTree(int n, int[][] edges) {
    List<List<Integer>> graph = makeGraph(edges, n);

    int result[] = new int[n];




    for(int cN = 0; cN < n ; cN++){
      result[cN] = performDfs(cN, -1, 0, graph);
    }

    return result;
  }

  private int performDfs(int cN, int parent, int timer, List<List<Integer>> graph){
    int valueToAdd = timer;

    for(int neighbour : graph.get(cN)){
      if(neighbour != parent){
        valueToAdd += performDfs(neighbour, cN, timer + 1, graph);
      }
    }
    return valueToAdd;
  }

  private List<List<Integer>> makeGraph(int[][] edges, int n){
    List<List<Integer>> graph = new ArrayList<>();

    for(int cN = 0; cN < n; cN++)
      graph.add(new ArrayList<>());

    for(int[] edge : edges){
      int u = edge[0], v = edge[1];
      graph.get(u).add(v);
      graph.get(v).add(u);
    }

    return graph;
  }
}
