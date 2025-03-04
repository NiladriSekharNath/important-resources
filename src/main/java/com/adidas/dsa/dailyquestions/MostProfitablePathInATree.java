package com.adidas.dsa.dailyquestions;

import java.util.ArrayList;
import java.util.List;

/**
 * There is an undirected tree with n nodes labeled from 0 to n - 1, rooted at node 0.
 * You are given a 2D integer array edges of length n - 1
 * where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
 *
 * At every node i, there is a gate. You are also given an array of even integers amount, where amount[i] represents:
 *
 * the price needed to open the gate at node i, if amount[i] is negative, or,
 * the cash reward obtained on opening the gate at node i, otherwise.
 * The game goes on as follows:
 *
 * Initially, Alice is at node 0 and Bob is at node bob.
 * At every second, Alice and Bob each move to an adjacent node. Alice moves towards some leaf node, while Bob moves towards node 0.
 * For every node along their path, Alice and Bob either spend money to open the gate at that node, or accept the reward.
 *
 * Note that:
 * If the gate is already open, no price will be required, nor will there be any cash reward.
 * If Alice and Bob reach the node simultaneously, they share the price/reward for opening the gate there.
 * In other words, if the price to open the gate is c, then both Alice and Bob pay c / 2 each.
 * Similarly, if the reward at the gate is c, both of them receive c / 2 each.
 * If Alice reaches a leaf node, she stops moving. Similarly, if Bob reaches node 0, he stops moving.
 * Note that these events are independent of each other.
 * Return the maximum net income Alice can have if she travels towards the optimal leaf node.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: edges = [[0,1],[1,2],[1,3],[3,4]], bob = 3, amount = [-2,4,2,-4,6]
 * Output: 6
 * Explanation:
 * The above diagram represents the given tree. The game goes as follows:
 * - Alice is initially on node 0, Bob on node 3. They open the gates of their respective nodes.
 *   Alice's net income is now -2.
 * - Both Alice and Bob move to node 1.
 *   Since they reach here simultaneously, they open the gate together and share the reward.
 *   Alice's net income becomes -2 + (4 / 2) = 0.
 * - Alice moves on to node 3. Since Bob already opened its gate, Alice's income remains unchanged.
 *   Bob moves on to node 0, and stops moving.
 * - Alice moves on to node 4 and opens the gate there. Her net income becomes 0 + 6 = 6.
 * Now, neither Alice nor Bob can make any further moves, and the game ends.
 * It is not possible for Alice to get a higher net income.
 * Example 2:
 *
 *
 * Input: edges = [[0,1]], bob = 1, amount = [-7280,2350]
 * Output: -7280
 * Explanation:
 * Alice follows the path 0->1 whereas Bob follows the path 1->0.
 * Thus, Alice opens the gate at node 0 only. Hence, her net income is -7280.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 105
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges represents a valid tree.
 * 1 <= bob < n
 * amount.length == n
 * amount[i] is an even integer in the range [-104, 104].
 *
 * Given to us a Tree represented by Graph like this( this gives us the idea that there are no cycles )
 *
 *                  0 A
 *                  |
 *                  1
 *                 / \
 *                2  3 - 6
 *                   B
 *
 *  A -> represents the starting position of Alice
 *  B -> represents the starting position of Bob
 *
 *  very good problem:
 *
 *  idea:
 *
 *  Concept to this problem is very simple,
 *  First the important points
 *
 *  Bob starts from some node(given to us) and then moves to 0
 *  Alice moves from 0 to any leaf node and we need to find the maximum value
 *
 *
 *  Steps:
 *
 *  idea is since we need to start both at the same time Alice and Bob
 *
 *
 *
 *  1. Doing this in programming can be done using DFS on starting times(time at which we reach a node)
 *
 *     where we keep a timer at every node (explained later)
 *
 *     for Bob we start from
 *
 *     node 3 (timer = 0) -> node1 (timer = 1) -> node0 (timer = 2)
 *
 *     (idea is we have to start the timer from bob)
 *
 *
 *
 *  2. similarly using the timer approach we start a dfs for Alice
 *
 *    node0  (timer = 0) -> node1(timer = 1) -> node3(timer = 2) -> node4(timer = 3)
 *
 *
 *  3. See the idea is :
 *
 *    Find Bob's path and mark the time using dfs and backtracking
 *    Explore Alice's all leaf paths and compute using the below rules
 *
 *      now if we find Both BobTime == Alice Time for a particular node -> we add (amount[node]/2)
 *      otherwise if bobTime[node] < aliceTime[node] -> we add 0
 *
 *  Time Complexity :
 *
 *  O(n)
 *
 *  SC: O(n)
 *
 *
 *
 *
 *
 *
 *
 */
public class MostProfitablePathInATree {
  public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
    int size = amount.length, tIAlice[] = new int[size], tIBob[] = new int[size], currentSum[] = new int[]{0},
        maxSum[] = new int[]{-(int)1e9};

    for(int i = 0 ; i < size; i++){
      tIAlice[i] = -1 ;
      tIBob[i] = -1;
    }

    List<List<Integer>> graph = makeGraph(edges, size);

    List<Integer> bobPath = new ArrayList<>();
    findBobPath(bob, 0, graph, new boolean[size], 0, tIBob, new ArrayList<>(), bobPath);

    amount[bob] = 0;

    finalHelper(0, graph, new boolean[size], 0, tIAlice, tIBob, currentSum, amount, maxSum);

    return maxSum[0];
  }

  private boolean findBobPath(int cN, int targetNode, List<List<Integer>> graph, boolean[] visited, int timer, int[] tIBob, List<Integer> currentPath, List<Integer> bobPath){
    visited[cN] = true;
    bobPath.add(cN);

    tIBob[cN] = timer;

    if(cN == targetNode){
      bobPath.addAll(currentPath);
      return true;
    }

    /***
     * timer logic we started for timer = timer + 1 for each recursion call is because we need to find nodes that
     * can be travelled at the same level in the same timer
     *
     * because let's say :
     *
     * for Bob we are at:
     *                      0
     *                      |
     *                      1  (1)
     *                      |
     *                  2 - 3 - 4
     *                 (1)  B  (1)
     *                      0
     *
     *              because logically from node3 we can move in any direction -> (1, 2, 4)
     */

    for(int neighbour : graph.get(cN)){
      if(!visited[neighbour]){
        if(findBobPath(neighbour, targetNode, graph, visited, timer + 1, tIBob, currentPath, bobPath)) return true;
      }
    }

    tIBob[cN] = -1;

    bobPath.remove(bobPath.size() - 1);
    return false;

  }

  private void finalHelper(int cN, List<List<Integer>> graph, boolean[] visited, int timer, int[] tIAlice, int[] tIBob, int[] currentSum, int[] amount, int[]maxSum){
    visited[cN] = true;

    int valueToAdd = 0;

    tIAlice[cN] = timer;

    if(tIBob[cN] != -1 && tIBob[cN] < tIAlice[cN]) valueToAdd = 0;
    else valueToAdd = tIAlice[cN] == tIBob[cN] ? amount[cN]/2 : amount[cN];

    currentSum[0] += valueToAdd;

    if(cN != 0 && graph.get(cN).size() == 1) maxSum[0] = Math.max(maxSum[0], currentSum[0]);

    /**
     * Same logic as done above for Bob
     *
     * because
     *                            A (0)
     *                            0
     *                            |
     *                            1  (1)
     *                            |
     *                        2 - 3 - 4
     *                       (3) (2)  3
     *
     *      because in order to find the path accurately and compute with Alice we need to start from node0 and
     *      every node would move their particular timer for same level nodes at the same time
     *
     *      node3 (2) -> node2 (3)
     *                   node4 (3)
     *
     *      as here we don't know which path Alice might take first which would affect our result hence we would have to take
     *      both path first so instead of incrementing and decrementing we take same level path at the same time
     *
     *      because when at node3 we can either take [2 or 4] in any order
     *
     */
    for(int neighbour : graph.get(cN)){
      if(!visited[neighbour]){
        finalHelper(neighbour, graph, visited, timer + 1, tIAlice, tIBob, currentSum, amount, maxSum);
      }
    }

    currentSum[0] -= valueToAdd;
  }

  private List<List<Integer>> makeGraph(int[][] edges, int size){
    List<List<Integer>> graph = new ArrayList<>();
    for(int cN = 0; cN < size; cN++){
      graph.add(new ArrayList<>());
    }

    for(int[] edge : edges){
      int u = edge[0], v = edge[1];
      graph.get(u).add(v);
      graph.get(v).add(u);
    }

    return graph;
  }

  public static void main(String[] args){
    new MostProfitablePathInATree().mostProfitablePath(new int[][]{{0, 1}, {1, 2}, {1, 3}, {3, 4}}, 3, new int[]{-2, 4, 2, -4, 6});
  }
}
