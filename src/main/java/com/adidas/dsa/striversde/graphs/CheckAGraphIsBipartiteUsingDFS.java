package com.adidas.dsa.striversde.graphs;

import java.util.Arrays;
import java.util.List;
/**
 * To check if a graph is bipartite or not :
 *
 * Bipartite means a graph can be colored using 2 colors, if a graph can be colored using 2 colors, such that
 * no two adjacent vertices has the same color then the graph is bipartite, however there are few cases for a bipartite
 * graph
 *
 * Solution : "Initially the approach was thought that, if we could find that a graph has a cycle then the graph
 * cannot be a bipartite graph which is incorrect"
 *
 * consider the below example
 *  *
 *  *  0 --- 1
 *  *  |     |
 *  *  3 --- 2
 *  *
 *  *  Adjacency List : 0 -> [1, 3], 1 -> [0, 2], 2 -> [1, 3], 3 -> [0, 2], let's say we are coloring the above graph
 *  using 2 colours green and blue
 *
 *  node 0 -> green
 *  node 1 -> blue
 *  node 2 -> green
 *  node 3 -> blue
 *
 *  the graph is a bipartite graph, which means that if though it has a cycle it still can be coloured by 2 colours
 *  the main idea is graphs that have an even length cycle or straight graphs are a bipartite graph, but graphs that
 *  have odd length cycle are not bipartite graphs
 *
 *  Like this example :
 *
 *              1
 *            /  \
 *           2 -- 3
 *
 *  this above graph has odd length cycle :
 *
 *  let's say we colour using two colours red, blue
 *
 *  1 -> red
 *  2 -> blue
 *  3 -> cannot be red / blue, therefore it's not a bipartite graph
 *
 *  Process same as the bfs approach
 */
public class CheckAGraphIsBipartiteUsingDFS {
  public boolean isBipartite(List<List<Integer>> graph) {
    int vertices = graph.size();
    int[] color = new int[vertices];
    Arrays.fill(color, -1);
    for(int currentVertex = 0; currentVertex < vertices; currentVertex++){
      if(color[currentVertex] == -1){
        /**
         * only here what we are doing if the currentnode is not visited for a multi-component graph
         * we simply mark the color[node] which is the source of the dfs to be 0,
         */
        color[currentVertex] = 0;
        if(!checkBipartiteUsingDfs(currentVertex, graph, color))
          return false;

      }
    }

    return true;

  }

  private boolean checkBipartiteUsingDfs(int currentVertex, List<List<Integer>> graph, int[] color){

    /**
     * in my approach,
     * since the currentNode of the graph is already colored during the starting phase we don't need to color the graph
     *
     */

    for(int neighbour : graph.get(currentVertex)){
      if(color[neighbour] == -1){
        /**
         * here we change the color of the neighbour if the color of the neighbour is currently not set
         */
        color[neighbour] = 1 - color[currentVertex];
        if(!checkBipartiteUsingDfs(neighbour, graph, color))
          return false;
      }

      /**
       * if the color of the graph is not set here we check if the neighbour is not parent for whose case it is fine
       * otherwise the color[neighbour] == color[currentVertex] then we can say the graph is not bipartite
       */
      else if(color[neighbour] == color[currentVertex])
        return false;
    }
    return true;
  }


  public boolean isBipartite_StriversMethod(List<List<Integer>> graph) {
    int vertices = graph.size();
    int[] color = new int[vertices];
    Arrays.fill(color, -1);
    for(int currentVertex = 0; currentVertex < vertices; currentVertex++){
      if(color[currentVertex] == -1){
        if(!checkBipartiteUsingDfs_StriversMethod(currentVertex, 0, graph, color))
          return false;

      }
    }

    return true;

  }

  private boolean checkBipartiteUsingDfs_StriversMethod(int currentVertex, int currentVertexColor, List<List<Integer>> graph, int[] color){
    color[currentVertex] = currentVertexColor;
    for(int neighbour : graph.get(currentVertex)){
      if(color[neighbour] == -1){
        if(!checkBipartiteUsingDfs_StriversMethod(neighbour, 1 - color[currentVertex], graph, color))
          return false;
      }
      else if(color[neighbour] == color[currentVertex])
        return false;
    }
    return true;
  }
}
