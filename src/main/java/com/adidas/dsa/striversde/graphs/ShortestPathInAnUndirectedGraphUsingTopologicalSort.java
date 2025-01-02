package com.adidas.dsa.striversde.graphs;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;

/**
 * Given a Directed Acyclic Graph of V vertices from 0 to n-1 and a 2D Integer array(or vector) edges[ ][ ] of length E, where there is a directed edge from edge[i][0] to edge[i][1] with a distance of edge[i][2] for all i.
 *
 * Find the shortest path from src(0) vertex to all the vertices and if it is impossible to reach any vertex, then return -1 for that vertex.
 *
 * Examples :
 *
 * Input: V = 4, E = 2, edges = [[0,1,2], [0,2,1]]
 * Output: [0, 2, 1, -1]
 * Explanation: Shortest path from 0 to 1 is 0->1 with edge weight 2. Shortest path from 0 to 2 is 0->2 with edge weight 1. There is no way we can reach 3, so it's -1 for 3.
 *
 * Example 1 : not the example present
 *               3      4
 *            5 --> 0 <-- 4
 *  *  *  3  |           | 3
 *  *  *    \/          \/
 *  *  *    2 --> 3 --> 1
 *            3      1
 *
 *
 *
 *   Step1 : We find a valid topological sort
 *   Step2 : Once we get the topological sort we initialize a distance array representing the distance of nodes from the given
 *          source, we set the source distance as 0 as distance of source from source = 0
 *
 *          then for each nodes in the topological sort we perform the distance realization like if we have
 *
 *          if the original distance to a point v represented by distance[v] is greater than the distance[u] + weight(u to v) then
 *          distance[v] = distance[u] + weight(u to v)
 *          which means we do this because previously if we have a distance[v] which is greater then we find a path having
 *          less distance than current distance[v] which is via path u to path v
 *
 *          this is called edge relaxation, and we perform this for all nodes in the toposort order
 *
 *          and we return the final answer
 *
 *
 */
public class ShortestPathInAnUndirectedGraphUsingTopologicalSort {


  public int[] shortestPath(int v, int e, int[][] edges) {
    /***
     *
     * Also pair could have been taken here this is done to represent the edge weights
     *
     * u -> (v, w)
     *
     * where source = u, destination = v, weight = w
     *
     * which means u -> v has an edge weight of w
     *
     *
     */
    List<List<int[]>> graph = createGraph(edges, v);

    /**
     *  Step 1: Perform topo sort in the graph and get the resultStack
     */

    boolean[] visited = new boolean[v];

    Stack<Integer> stack = new Stack<>();

    for(int currentVertex = 0 ; currentVertex < v; currentVertex++){
      if(!visited[currentVertex])
        performTopoSort(currentVertex, graph, visited, stack);
    }

    /**
     * Step 2 : Perform the distance relaxation
     */

    int[] distance = new int[v];

    /**
     * initializing the distance array to a high value but the distance array we are not setting it to a
     *
     * Integer.MAX_VALUE because if we do this
     *
     * step, let's say,  k = [Integer.MAX_VALUE + some distance] and we check if is this is less than distance[v]
     *
     * this would cause problem because Integer.MAX_VALUE though greater it is cyclic and if we add some distance to this
     * it would be less than the currentValue
     *
     * +Integer_MAX_VALUE + some distance = - Integer.MIN_VALUE and it get rounded off, now this distance would always be
     * lower to the any distance hence to avoid overflow we are setting this to Integer.MAX_VALUE/2
     */
    Arrays.fill(distance, Integer.MAX_VALUE/2);

    /***
     *  if source is given the we would have done
     *
     * distance[source] = 0
     *
     * but since source is not given we are performing our relaxation assuming the
     * source = 0
     */

    distance[0] = 0;

    while(!stack.isEmpty()){
      int sourceNode = stack.pop();
      for(int[] edge : graph.get(sourceNode)){
        int destinationNode = edge[0], weight = edge[1];
        if(distance[sourceNode]  + weight < distance[destinationNode])
          distance[destinationNode] = distance[sourceNode] + weight;
      }
    }

    for(int currentNode = 0 ; currentNode < v ; currentNode++){
      if(distance[currentNode] == Integer.MAX_VALUE/2)
        distance[currentNode] = -1;

    }

    return distance;
  }


  private void performTopoSort(int currentNode, List<List<int[]>> graph, boolean[] visited, Stack<Integer> stack){
    visited[currentNode] = true;

    for(int[] neighbour : graph.get(currentNode)){
      /**
       * since the edges in the graph are represented by u -> (v, w), where v and w are the destination node and weight
       * respectively and since the edges are represented by 2 size distance array v = dist[0], w = dist[1]
       */
      if(!visited[neighbour[0]]){
        performTopoSort(neighbour[0], graph, visited, stack);
      }
    }

    stack.push(currentNode);
  }

  private List<List<int[]>> createGraph(int [][] edges, int vertices){
    List<List<int[]>> graph = new ArrayList<>();

    for(int i = 0 ; i < vertices; i++){
      graph.add(new ArrayList<>());
    }

    for(int[] neighbourEdgeWeightPair : edges){
      int source = neighbourEdgeWeightPair[0], destination = neighbourEdgeWeightPair[1], weight = neighbourEdgeWeightPair[2];

      /**
       * we could have taken a new Pair instead of 1D array however
       * instead of a pair class the array is more efficient
       */
      graph.get(source).add(new int[]{destination, weight});
    }

    return graph;
  }
}
