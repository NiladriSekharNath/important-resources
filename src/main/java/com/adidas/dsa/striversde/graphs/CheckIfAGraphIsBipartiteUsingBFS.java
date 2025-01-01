package com.adidas.dsa.striversde.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
 */
public class CheckIfAGraphIsBipartiteUsingBFS {

  public boolean isBipartite(List<List<Integer>> graph) {
    int vertices = graph.size();
    int[] color = new int[vertices];
    Arrays.fill(color, -1);
    for(int currentVertex = 0; currentVertex < vertices; currentVertex++){
      if(color[currentVertex] == -1)
        if(!checkBipartite(currentVertex, graph, color))
          return false;
    }

    return true;

  }

  private boolean checkBipartite(int source, List<List<Integer>> graph, int[] color){

    Queue<Integer> queue = new LinkedList<>();
    queue.add(source);
    color[source] = 0;

    while(!queue.isEmpty()){
      int currentVertex = queue.poll();
      for(Integer neighbour : graph.get(currentVertex)){
        /**
         * IF a neighbour IS NOT coloured then we can color the neighbour using a opposite color as the currentSource
         * Node
         */
        if(color[neighbour] == -1){
          color[neighbour] = 1 - color[currentVertex];
          queue.add(neighbour);
        }
        /**
         * If a neighbour is coloured here in this case, then we check if the neighbour's colour == the colour of the
         * current vertex, if it's yes then it is not a bipartite
         *
         * here the option let's the say parent node will still come here but the parent node will not have the same
         * color as the currentNode
         */
        else if(color[neighbour] == color[currentVertex])
          return false;
      }
    }

    return true;
  }
}
