package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Okay we are tasked to find the critical edge(bridge) of a graph, A bridge is a edge upon whose disconnection the
 * graph splits into 2 or more components
 *
 * Let's say we have this graph
 *
 * 1 --- 2
 * |     |
 * 3 --- 4
 * |
 * 5
 *
 * If we see in the above graph we can say edge(3,5) is a bridge because it splits the graph into 2 components
 *
 * 1-2-3-4 and 5
 *
 * Algorithm is
 *
 * We are using Tarjan's Algorithm
 *
 * initially we have a timer that increments by one at every node when we reach during DFS traversal,
 * this timer is called the startingTimer(currentTimer) now the time at which it reaches a node during dfs is called the
 * timeOfInsertion[node]
 * so we need an array of all nodes to keep track of the timeOfInsertion[] = [node1 -> 1, node2 -> 2 ...]
 *
 * and we have another array called the lowestTime[] -> [node1 -> lowestTime, node2 -> lowestTime ...], this stores the
 * lowest time of the node, which is the lowest of all it's neighbours except the parent node
 *
 * The algorithm steps are as follows:
 *
 * First, we need to create the adjacency list for the given graph from the edge information(If not already given).
 * And we will declare a variable timer(either globally or we can carry it while calling DFS),
 * that will keep track of the time of insertion for each node.
 * Then we will start DFS from node 0(assuming the graph contains a single component otherwise,
 * we will call DFS for every component) with parent -1.
 *
 * Inside DFS, we will first mark the node visited and then store the time of insertion and the lowest time of insertion
 * properly. The timer may be initialized to 0 or 1.
 * Now, it’s time to visit the adjacent nodes.
 *
 * If the adjacent node is the parent itself, we will just continue to the next node.
 *
 * If the adjacent node is not visited, we will call DFS for the adjacent node with the current node as the parent.
 *
 * After the DFS gets completed, we will compare the lowest time of insertion of the current node and the adjacent node
 * and take the minimum one.
 *
 * Now, we will check if the lowest time of insertion of the adjacent node is greater than the time of insertion of the
 * current node.
 *
 * If it is, then we will store the adjacent node and the current node in our answer array as they are representing the
 * bridge.
 *
 * If the adjacent node is already visited, we will just compare the lowest time of insertion of the current node
 * and the adjacent node and take the minimum one.
 *
 * Finally, our answer array will store all the bridges.
 *
 * Note: We are not considering the parent’s insertion time during calculating the lowest insertion time
 * as we want to check if any other path from the node to the parent exists excluding the edge we intend to remove.
 *
 * let's walkthrough for the graph we are marking (timeOfInsertion | lowestTime)
 *
 *       (0|0)  (1|1)
 *        1 --- 2
 *        |     |
 *  (3|3) 3 --- 4 (2|2)
 *        |
 *        5
 *      (4|4)
 *
 *   the dfs steps calls are as follows -> dfs(1) -> dfs(2) -> dfs(4) -> dfs(3) -> dfs(5)
 *
 *   at 3 during it's check for neighbours [1, 4, 5], we can see 1 is visited we don't care we simply take it's
 *   lowest and update the lowest of the currentNode so we make lowestTime[3] -> 0 (we get from node1)
 *   in the else block
 *
 *   else{
 *         lowestTime[currentNode] = Math.min(lowestTime[currentNode], lowestTime[neighbour]);
 *   }
 *
 *   for 4 it's the parent we skip
 *
 *   now for 5 we see that the node is unvisited we mark that and update the timeOfInsertion[5]|lowestTime[5]
 *
 *   at 5 we have only one neighbour 3 which is the parent we skip
 *
 *   now we don't check for all neighbours lowest except parent because we try to see if we can break an edge from parent
 *
 *   now dfs(5) is completed
 *
 *   now we move to dfs(3) : we move to the backtracking steps for current node3
 *
 *   we see node5 is one of the adjacent we see if we have a better time at node in the lowest compared to it's neighbour 5
 *
 *   we see lowest[currentNode -> 3] = 0 (from node1), lowestTime[neighbour -> 5] = 4, so no point in updating
 *
 *   now we check if the lowestTime[neighbour -> 5] = 4 is it >  timeOfInsertion[currentNode -> 3] = 3, we see it is
 *
 *   because otherwise from it's neighbours we could have got a better time now we understand for the neighbour 5
 *   we know the lowestTime to reach 5 is 4, and the time Of Insertion of the currentNode3 is 3 so this should be a bridge
 *
 *   lowestTime[currentNode] = Math.min(lowestTime[currentNode], lowestTime[neighbour]);
 *         if (lowestTime[neighbour] > timeOfInsertion[currentNode]) {
 *           bridges.add(Arrays.asList(currentNode, neighbour));
 *         }
 *
 *  To make this crystal clear let's do the same for when we go back to dfs(4) from dfs(3) during backtracking
 *
 *  For 4 -> we are seeing the lowestTime to reach 4 we update it from it's neighbour 3 which is 0
 *
 *  now we check if the edge 3 - 4 can be removed, hypothetically if we try to remove,
 *
 *  currently we see the updated low for 3 and 4 in the graph
 *
 *        (0|0)  (1|1)
 *          1 --- 2
 *          |     |
 *    (3|0) 3 --- 4 (2|0)
 *          |
 *          5
 *        (4|4)
 *
 *         |
 *        \/
 *
 *        (0|0)  (1|1)
 *          1 --- 2
 *          |     |
 *    (3|0) 3     4 (2|0)  ------------> let's say we removed edge 3 - 4
 *          |
 *          5
 *        (4|4)
 *
 *
 *
 *  we see lowest[neighbour -> 3] = 0 "is not greater than" timeOfInsertion[4 -> 2] which means we could have moved to node3
 *
 *  from 1 -> 3, instead of 1 -> 2 -> 3 -> 4
 *
 *  or other words we are connected to 4 and 3 are still belong to same component via 4 -> 2 -> 1 -> 3
 *
 *  which does not happen for the edge 3 - 5
 *
 *
 * Note the discovery timer we could start from 0 also instead of 1, it's not necessary that we would have to start the
 * discoveryTimer from 1 it's simply a representation
 *
 *
 *
 *
 */
public class BridgesInGraphsCriticalConnections {
  public List<List<Integer>> criticalConnections(int n, List<List<Integer>> graph) {
    boolean[] visited = new boolean[n];
    int lowestTime[] = new int[n], timeOfInsertion[] = new int[n], currentTimer[] = new int[]{1};

    List<List<Integer>> bridges = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      if (!visited[i]) {
        bridges.addAll(dfs(0, -1, graph, visited, lowestTime, timeOfInsertion, currentTimer));
      }
    }

    return bridges;
  }

  private List<List<Integer>> dfs(int currentNode, int parent, List<List<Integer>> graph,
                                  boolean[] visited, int[] lowestTime, int[] timeOfInsertion, int[] currentTimer) {
    List<List<Integer>> bridges = new ArrayList<>();

    visited[currentNode] = true;
    lowestTime[currentNode] = timeOfInsertion[currentNode] = currentTimer[0];
    currentTimer[0]++;

    for (int neighbour : graph.get(currentNode)) {
      if (neighbour == parent) continue;
      if (!visited[neighbour]) {
        bridges.addAll(dfs(neighbour, currentNode, graph, visited, lowestTime, timeOfInsertion, currentTimer));
        lowestTime[currentNode] = Math.min(lowestTime[currentNode], lowestTime[neighbour]);
        if (lowestTime[neighbour] > timeOfInsertion[currentNode]) {
          bridges.add(Arrays.asList(currentNode, neighbour));
        }
      }
      else{
        lowestTime[currentNode] = Math.min(lowestTime[currentNode], lowestTime[neighbour]);
      }

    }

    return bridges;
  }
}
