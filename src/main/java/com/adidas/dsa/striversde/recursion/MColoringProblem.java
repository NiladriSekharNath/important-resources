package com.adidas.dsa.striversde.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Solution: This is kind of like a graph problem but using backtracking here similar to the Bipartite Graph color a graph using 2 colors we are coloring the graph with m colors
 * <p>
 * We are trying all possible colors for each node of graph. Let's say at 0th node we try to color using the first color [ ( 1 to m ) 1 based indexing is used
 * because if we use 0 based indexing without initializing the color array as -1 we would have incorrect results] then
 * next node, we are not travelling by dfs (we are travelling by the next index in a graph)
 * we don't care about the children or the depth of the graph(because order does not matter here, first parent then children, no we are not interested)
 * we are interested in coloring all the vertices of a graph with at max 'm' colors.
 *
 * we are having a graph structure though(List<List<Integer>> graph, to find out the neighbours if they have the same color or not to the current color for the current node,
 * if same color then the current node cannot have the same color,
 *
 * then we move on till we find that our function (currentNode == graph.size()) which means that we found less than m or at most m colors
 * for all the nodes of the graph, then we return true;
 *
 * if we find true in one particular iteration we don't require to check for all possible function branch as we are required to check
 * (Can we use at most m colors to color a graph ? and not all ways to color )
 *
 * If no color works for a node after trying all M colors, return false.( in simpler words for a particular current node we try all M colors and
 * still we are unable to color the graph we return false)
 *
 * please try this example graph for better understanding:
 *
 * Input: v = 3, edges[] = [(0,1),(1,2),(0,2)], m = 2
 * Output: false
 * Explanation: It is not possible to color the given graph using only 2 colors because vertices 0, 1, and 2 form a triangle.
 *
 *
 * TC: V^M (all vertex we are trying to use M colors in the worst case we would have M ways to V^M)
 * SC: O(V) vertex for recursion depths for V vertex
 *
 *
 *
 */
public class MColoringProblem {


  public boolean graphColoring(int v, List<int[]> edges, int m) {
    List<List<Integer>> graph = prepareGraph(edges, v);
    return checkHelper(graph, m, 0, new int[v]);


  }

  private List<List<Integer>> prepareGraph(List<int[]> edges, int v) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < v; i++) {
      graph.add(new ArrayList<>());
    }

    for (int[] edge : edges) {
      graph.get(edge[0]).add(edge[1]);
      graph.get(edge[1]).add(edge[0]);
    }

    return graph;
  }

  private boolean checkHelper(List<List<Integer>> graph, int m, int currentNode, int[] color) {
    if (currentNode == graph.size()) return true;
    for (int toColor = 1; toColor <= m; toColor++) {
      if (isSafeToColor(color, toColor, currentNode, graph)) {
        color[currentNode] = toColor;
        if (checkHelper(graph, m, currentNode + 1, color)) return true;
        else
          color[currentNode] = 0;
      }
    }

    return false;
  }


  private boolean isSafeToColor(int[] color, int toColor, int currentNode, List<List<Integer>> graph) {
    for (Integer neighbour : graph.get(currentNode)) {
      if (color[neighbour] == toColor) return false;
    }

    return true;
  }

  public static void main(String[] args) {
    new MColoringProblem().graphColoring(4, Arrays.asList(new int[]{0, 1}, new int[]{1, 2}, new int[]{2, 3}, new int[]{3, 0}, new int[]{0, 2}), 3);
  }
}
