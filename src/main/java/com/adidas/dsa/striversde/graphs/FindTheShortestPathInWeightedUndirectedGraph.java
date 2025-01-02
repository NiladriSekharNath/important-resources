package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * The question is we are tasked to find the shortest path for source 1 (given) to the v(no of vertices) this question
 * can be easily adjusted to find the shortest path in from source to any destination
 *
 * We perform standard Dijkstra and then we follow along
 *
 *
 */
public class FindTheShortestPathInWeightedUndirectedGraph {
  public List<Integer> shortestPath(int v, int e, int edges[][]) {
    List<List<int[]>> graph = makeGraph(edges, v + 1);

    int[] dist = new int[v + 1], parent = new int[v + 1];

    Arrays.fill(dist, Integer.MAX_VALUE/2);


    /**
     * here we are creating a parent array and making it store elements like this
     *
     * parent[] = [0 -> 0, 1 -> 1, 2 -> 2, 3 -> 3, 4 -> 4, 5 -> 5] here the elements look like this [index -> element]
     *
     * so initially the parents are fixed as itself
     *
     * after performing Dijkstra's algorithm
     *
     *
     */

    for(int currentNode = 0 ; currentNode < v + 1 ; currentNode++){
      parent[currentNode] = currentNode;
    }

    dijkstra(graph, 1, dist, parent);

    /**
     * here we are returning [-1] in a list form because if the destination node is not reachable, then we should do this
     *
     */
    if(dist[v] == Integer.MAX_VALUE/2)
      return List.of(-1);



    /**
     * here we are searching a shortest path from the targetNode to the starting node
     * logically the node could have been named
     * like this
     *
     * currentNode = destinationNode, as we are trying to find a path from destination node
     *
     * to source
     *
     * the approach that we are doing is initially the parent array looks like this
     *
     * parent[] = [0 -> 0, 1 -> 1, 2 -> 2, 3 -> 3, 4 -> 4, 5 -> 5] here the elements look like this [index -> element]
     *
     * now the parent[] array after Dijkstra's we are getting this
     *
     * parent[] = [0 -> 0, 1 -> 1, 2 -> 1, 3 -> 4, 4 -> 1, 5 -> 3], for every node we are storing the parent meaning for
     *      index 5(node5) we are getting the parent = 3
     *
     * we are doing this loop checking
     * initially setting the currentNode as the destination node and then checking
     *
     * if (parent[currentNode] == currentNode) {      (which would be true for the starting node )
     *  we are storing currentNode -> shortestPath
     *
     *  currentNode = parent[curretNode] ;
     *
     *
     *  (basically at each currentNode we are moving to the parent till parent == currentNode (for the startingNode))
     *  }
     *
     *  so we found the path from the loop -> 5, 3, 4
     *
     *
     * Also since we find the starting node where the loop would stop what we did was add back the starting node after
     * exiting
     *
     * so after adding the starting node we get
     *
     * 5, 3, 4, 1
     *
     * and since the path is in reverse order we simply reverse the path thereby getting
     *
     * 1, 4, 3, 5
     *
     *
     *
     */

    int currentNode = v;

    List<Integer> shortestPath = new ArrayList<>();



    while(parent[currentNode] != currentNode){
      shortestPath.add(currentNode);
      currentNode = parent[currentNode];
    }

    shortestPath.add(1);

    /**
     *
     * we removed this line simply because we did not want the distance of the final path which was asked here
     *
     * shortestPath.add(dist[v]);
     */


    Collections.reverse(shortestPath);

    return shortestPath;

  }

  void dijkstra(List<List<int[]>> graph, int source, int[] dist, int[] parent) {


    dist[source] = 0;

    PriorityQueue<Integer> minHeap = new PriorityQueue<>((entry1, entry2) -> dist[entry1] != dist[entry2] ? dist[entry1] - dist[entry2] : entry1 - entry2);

    minHeap.add(source);

    while(!minHeap.isEmpty()){
      int currentNode = minHeap.poll();


      for(int[] neighEdge : graph.get(currentNode)){
        int targetNode = neighEdge[0], weight = neighEdge[1];
        /**
         * here is Dijkstra's we are doing an extra step to store the parent let's say for vertex destination 'v' we
         * find a smaller dist from vertex 'u' then we consider u as the parent of v
         */
        if(dist[currentNode] + weight < dist[targetNode]){
          dist[targetNode] = dist[currentNode] + weight;
          parent[targetNode] = currentNode;
          minHeap.add(targetNode);
        }
      }
    }

  }

  private List<List<int[]>> makeGraph(int edges[][], int vertices){
    List<List<int[]>> graph = new ArrayList<>();
    for(int i = 0 ; i < vertices ; i++)
      graph.add(new ArrayList<>());

    for(int[] edge: edges){
      int u = edge[0], v = edge[1], w = edge[2];
      graph.get(u).add(new int[]{v, w});
      graph.get(v).add(new int[]{u, w});
    }

    return graph;

  }


  public static void main(String args[]){
    new FindTheShortestPathInWeightedUndirectedGraph().shortestPath(5, 6, new int[][]{{1, 2, 2}, {2, 5, 5}, {2, 3, 4}, {1, 4, 1}, {4, 3, 3}, {3, 5, 1}});
  }
}
