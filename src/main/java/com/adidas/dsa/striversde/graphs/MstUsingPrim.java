package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a graph we are finding the mst in the graph using Prim's Algorithm
 *
 * so the approach is very much similar to the Dijkstra' Algorithm since here we are not interested in finding the distance
 *
 * from node u -> v just the edge we are concerned hence we don't create a dist array instead we take visited array
 *
 * firstly we take a priority queue which stores elements in this way ->
 *
 * in here in the priorityQueue we are storing the new int[]{dist, currentNode, parentOfCurrentNode}, with the lower distance first if
 *      * distances are not equal otherwise the lower value nodes comes first, here the dist is the node weight
 *
 * Also we store the mst in a List<List<Integer>> [source, destination, weight] -> (we keep track using the source, parent, dist)
 *
 * initially we add new int[]{0, 0, -1} which is the starting node any node can be the starting node ->
 *
 * if for our case first 0 is the dist, second 0 is the node0 and the third one is the previous
 *
 * which means weight(0, 0 ) = 0 and parent of 0 = -1
 *
 * so initially in the minheap we add the above
 *
 *
 * ------------------------------------------------------
 * now till the minHeap is not Empty(){
 *
 *   we do this first poll the current triplet
 *
 *   if we see this triplet the node is already visited we skip the current operation
 *
 *   if not then we go to the next step we check if the parent != -1 (for the starting node) if yes then only
 *   we add the [node, parent, dist] and mstVal += dist ( we add the dist to mstValue)
 *
 *   now for each currentNode -> {
 *     we check if the neighbour 'v' for the current node is not visited then we add the value to the minHeap
 *
 *     --- example : minHeap.add(new int[]{weight, targetNode, currentNode});
 *
 *     note : here we don't mark visited, only when we pop from the minHeap then only we mark it as visited
 *      little unconventional from the standard bfs traversal we were doing where here only while checking the neighbour
 *      we mark it as visited, reason being in bfs we are concerned with the edges only and that no other edge comes after
 *      here from some other node that is visited previously
 *
 *      But here the point is we need the lowest possible distance at the top so we are doing this
 *
 *   }
 * }
 *
 * ----------------------------------------
 *
 *
 *
 */
public class MstUsingPrim {
  /**
   * here in the graph is given to us otherwise we would have to create the graph
   */
  public int spanningTree(int v, int e, List<List<int[]>> graph) {

    /**
     * in here in the priorityQueue we are storing the new int[]{dist, currentNode, parentOfCurrentNode}, with the lower distance first if
     * distances are not equal otherwise the lower value nodes comes first
     */

    PriorityQueue<int[]> minHeap = new PriorityQueue<>((entry1, entry2) ->
        entry1[0] != entry2[0] ? entry1[0] - entry2[0] : entry1[1] - entry2[1]);

    int mstVal = 0 ;

    boolean[] visited = new boolean[v];
    /**
     * this stores the actual mst
     */
    List<List<Integer>> mst = new ArrayList<>();

    minHeap.add(new int[]{0, 0, -1});

    while(!minHeap.isEmpty()){
      int[] currentTriplet = minHeap.poll();
      int dist = currentTriplet[0], currentNode = currentTriplet[1], parent = currentTriplet[2];

      if(visited[currentNode]) continue;

      visited[currentNode] = true;

      if(parent != -1){

        /**
         * initially the node when we add the parent = -1 for the 0th node if we starting from 0
         * if we have 0 based indexing graph
         *
         * and in the mst we are adding currentNode, parent, dist {which is the weight between currentNode and parent)
         */
        mstVal += dist;
        mst.add(Arrays.asList(currentNode, parent, dist));
      }

      for(int[] edgeWeightPair : graph.get(currentNode)){
        /**
         * in general convention targetNode == 'v' generally represented
         * currentNode = 'u' and weight = 'w'
         *
         * representing there is an edge between (u, v , w)
         *
         */
        int targetNode = edgeWeightPair[0], weight = edgeWeightPair[1];
        if(!visited[targetNode]){
          minHeap.add(new int[]{weight, targetNode, currentNode});
        }
      }
    }

    return mstVal;

  }
}
