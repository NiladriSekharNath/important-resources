package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class DijkstrasAlgorithm {
  ArrayList<Integer> dijkstra(List<List<iPair>> graph, int source) {
    /**
     * in the priority queue we are storing Pair(distance, node) we are storing lower distance first otherwise if distance is same we are storing the shorter node first
     *
     */
    PriorityQueue<int[]> minHeap = new PriorityQueue<>((entry1, entry2) -> entry1[0] != entry2[0] ?
        entry1[0] - entry2[0] : entry1[1] - entry2[1]);

    int vertices = graph.size();

    int[] dist = new int[vertices];


    Arrays.fill(dist, Integer.MAX_VALUE/2);

    dist[source] = 0;

    minHeap.add(new int[]{dist[source], source});



    while(!minHeap.isEmpty()){
      int[] currentNodePair = minHeap.poll();

      int distOfCurrNode = currentNodePair[0], currentNode = currentNodePair[1];

      for(iPair neighEdge : graph.get(currentNode)){
        int v = neighEdge.v, weight = neighEdge.w;
        if(distOfCurrNode + weight < dist[v]){
          dist[v] = distOfCurrNode + weight;
          minHeap.add(new int[]{dist[v], v});
        }
      }
    }

    ArrayList<Integer> relaxedDistances = new ArrayList<>();

    for(int dis : dist){
      relaxedDistances.add(dis);
    }

    return relaxedDistances;

  }

  /**
   * Approach looks very similar to the bfs approach where we
   *
   * first fill the dist array with the Integer.MAX_VALUE/2 (for taking care of the overflow)
   *
   * we mark the distance[source] = 0
   *
   * and add the values in the minHeap = > the node (the minHeap, we are tracking with the help of the dist array)
   *
   *
   *
   *
   *
   */
  int[] dijkstra_BetterCodeUsingPriorityQueue(List<List<iPair>> graph, int source) {
    /**
     * in the priority queue we are storing Pair(distance, node) we are storing lower distance first otherwise
     * if distance is same we are storing the shorter node first
     *
     * now instead of storing the distance and taking it along in the Priority Queue we are keeping the idea similar here
     * but storing only the nodes however the comparision is still kept on the distance
     *
     * which we are tracking with the help of the dist[array]
     *
     * now in the comparator we are simply specifying that if distance are not equal then we take the lower distance on top
     *
     * however if the distances are equal then we take the node with the higher value ;
     *
     * we are adding the source to the minHeap and
     *
     * and keep continuing the minHeap till minHeap is empty
     *
     * Till minHeap has elements{
     *
     *   we take the top element( element having the lowest distance) let's say that is 'u'
     *
     *   and for it's neighbours let's say 'v', having weight 'w',  we check if dist[u] + w < dist[v]
     *
     *   then we update the dist[v] = dist[u] + w
     *
     *   and add the 'v' back to the minHeap
     *
     *   What we did here is we are checking if we can find a dist to 'v' from 'u' which is actually shorted than the previous
     *   distance at 'v' recorded in dist[v]
     * }
     *
     *
     */


    int vertices = graph.size();

    int[] dist = new int[vertices];


    Arrays.fill(dist, Integer.MAX_VALUE/2);

    dist[source] = 0;

    PriorityQueue<Integer> minHeap = new PriorityQueue<>((entry1, entry2) -> dist[entry1] != dist[entry2] ? dist[entry1] - dist[entry2] : entry1 - entry2);

    minHeap.add(source);

    while(!minHeap.isEmpty()){
      int currentNode = minHeap.poll();

      /**
       * int distOfCurrNode = dist[currentNode];
       *
       * also conventionally, we say currentNode as 'u'
       */
      for(iPair neighEdge : graph.get(currentNode)){
        int targetNode = neighEdge.v, weight = neighEdge.w;
        if(dist[currentNode] + weight < dist[targetNode]){
          dist[targetNode] = dist[currentNode] + weight;
          minHeap.add(targetNode);
        }
      }
    }

    return dist;

  }

  /**
   * gfg official solution but would have personally take a size 2 array where the [0] -> node v, [1] -> weight
   *
   *
   */

  private class iPair{
    public int v;
    public int w;

    public iPair(){

    }

    public iPair(int v, int w){
      this.v = v;
      this.w = w;
    }
  }
}
