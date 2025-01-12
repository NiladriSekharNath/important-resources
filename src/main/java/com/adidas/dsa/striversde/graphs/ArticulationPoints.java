package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/***
 * Find articulation Points in a graph,
 *
 * An Articulation Point of a graph is a point removal of which breaks the graph into 2 or more components
 *
 *
 *
 *   1 --- 2
 *   |     |
 *   3 --- 4
 *   |
 *   5
 *
 *   we have this graph if we remove the node 3 we would get 2 components  1 - 2 - 4 and 5
 *
 *   1 --- 2
 *         |
 *         4
 *
 *   5
 *
 *   if you see this graph we got 2 components
 *
 *   the logic is almost similar to the find the bridges in a graph concept except for a few places
 *
 *   in bridges we would have used something like
 *
 *   if(lowestTime[neighbour] > timeOfInsertion[currentNode] )
 *
 *   but for articulation points we took this
 *
 *   if(lowestTime[neighbour] >= timeOfInsertion[currentNode] && parent != -1 )
 *
 *         0
 *       / | \
 *      1  2--3
 *        /\
 *       4 5
 *       \/
 *       6
 *
 *  now considering for this graph if let's say hypothetically we were simply removing the bridge 2 - 5 reaching 2 was still okay
 *  but for articulation point we are not removing the edge just we are removing the entire node 2 and along with it's
 *  connecting edges
 *
 *  something like this
 *
 *              0
 *             / \
 *            1   2
 *
 *            4  5
 *            \  /
 *              6
 *
 *  we would have to check if we reaching somewhere in 0 - 1 - 2 from 5 or something which is not hence 2 is an articulation
 *  point also if we see in the else section
 *
 *  in bridges we took this one
 *
 *  if (lowestTime[neighbour] > timeOfInsertion[currentNode]) {
 *           bridges.add(Arrays.asList(currentNode, neighbour));
 *         }
 *
 *  here in articulation points, we are taking the min of low([currentNode], timeOfInsertion[neighbour])
 *
 *  else{
 *         lowestTime[currentNode] = Math.min(lowestTime[currentNode], timeOfInsertion[neighbour]);
 *       }
 *
 *
 *                 0  (1|1)
 *               / | \
 *        (2|2) 1 2--3   FOR 2 (3|3) FOR 3 (4|4), afer dfs completion 3 becomes(4 | 1) and in dfs backtracking 2 becomes(3 | 1) from 3
 *               /\
 *        (5|5) 4 5 (7|7) at 5 after dfs of 5 is completed the value becomes(7|min(low[low[5], timeIns[2]
 *              \/
 *              6 (6 | 6)
 *
 *  let's imagine dfs takes these routes
 *
 *  dfs(0) -> dfs(1) completed then backtrack
 *         -> dfs(2) -> dfs(3) completed then backtrack
 *                   -> dfs(4) -> dfs(6) -> dfs(5) then backtrack
 *
 *  Currently at dfs of node5 when it check for its neighbour's [2, 6] for 2 it finds the low[2] = 1 and timeOfInsertion[2] = 3
 *
 *  so it does low[currentNode -> 5] = Math.min(low[currentNode -> 5] = 7, timeIns[neighbour -> 2) = 3)
 *
 *  lows[currentNode -> 5] = 3 and not 1 as in bridges (it did not take low[neighbour])
 *
 *  same reason if we are to remove currentNode 2, let's say, then we cannot take anyone before that node2 (0, 1, 3 )
 *
 *  for the above graph 0 is also an articulation point
 *
 *  let's observe if we have a graph like this
 *
 *  we are doing the above when parent != -1 for parent or root node we are not taking the calculation, calculation is differnt
 *
 *    0
 *   /
 *  2 - 3
 *
 *  if we remove 0 we don't get anything useful as 2 - 3 still gets attached, so if it has single children there is no
 *  articulation point
 *
 *  now for the node if we see
 *
 *      0
 *    / | \
 *   1  2  3
 *
 *   here if we remove 0 we would get 3 components 1, 2, 3
 *
 *   here again there is slight twist
 *
 *   if we have something like this we cannot simply count children
 *
 *   if we have something like this
 *
 *   0
 *  / \
 * 1--2
 *
 * in this graph we cannot say 0 is an articulationPoint
 *
 * if we remove 0 we still have one component 1--2
 *
 * so we keep the count in the !visited section
 *
 * because if we count
 *
 * for graph
 *
 *      0
 *     / \
 *    1--2
 *
 *    we do dfs(0) -> dfs(1) -> dfs(2)
 *
 *    dfs(0) when it tried for neighbours [1, 2] it found 1 incremented children but when it saw for 2 it found 2 is already
 *    visited, so did not increment children by 1 again
 *
 *    final answer for dfs(0) children = 1
 *
 *    however, if we try for this graph
 *
 *      0
 *     / \
 *    1   2
 *
 *
 *
 *  dfs(0) -> dfs(1) +1 children
 *         -> dfs(2) +1 children
 *
 * children = 2
 *
 * now in the articulation points finally we took one boolean marker let's say we found a node 2 as an articulation point
 * what we did was marked in the articulationPointMarker[2] = true
 *
 * this we did for this let's say in this graph we got this graph instead
 *
 *
 *              0
 *  *        /  | \
 *  *      1   2 --3
 *  *       / | \ \
 *  *       4 5 7 8
 *  *       \/  \ /
 *  *       6    9
 *
 *  meaning at the node 2 we have neighbour of 2 [4,5,7,8] this we after completion for cycle 7 - 8 - 9 we would be
 *
 *  doing the same again and adding 2 again so there is a chance for duplicate's so instead of adding to a list we mark
 *  this node2 in the marker array
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class ArticulationPoints {
  public List<Integer> articulationPoints(int v, List<List<Integer>> graph)
  {
    List<Integer> articulationPoints = new ArrayList<>();

    int[] lowestTime = new int[v], timeOfInsertion = new int[v], currentTimer = new int[]{1};
    boolean[] visited = new boolean[v], articulationPointMarker = new boolean[v];

    for(int i = 0 ; i < v ; i++){
      if(!visited[i]){
        dfs(i, -1, graph, lowestTime, currentTimer, timeOfInsertion, visited, articulationPointMarker);
      }
    }

    for(int i = 0; i < v; i++){
      if(articulationPointMarker[i])
        articulationPoints.add(i);
    }

    if(articulationPoints.size() == 0) return new ArrayList<>(Arrays.asList(-1));

    return articulationPoints;


  }

  private void dfs(int currentNode, int parent, List<List<Integer>> graph, int[] lowestTime, int[] currentTimer, int[] timeOfInsertion,
                   boolean[] visited, boolean[] articulationPointMarker){

    visited[currentNode] = true;
    timeOfInsertion[currentNode] = lowestTime[currentNode] = currentTimer[0];
    currentTimer[0]++;

    int children = 0;

    for(int neighbour : graph.get(currentNode)){
      if(neighbour == parent) continue;
      if(!visited[neighbour]){
        dfs(neighbour, currentNode, graph, lowestTime, currentTimer, timeOfInsertion, visited, articulationPointMarker);

        lowestTime[currentNode] = Math.min(lowestTime[currentNode], lowestTime[neighbour]);
        if(lowestTime[neighbour] >= timeOfInsertion[currentNode] && parent != -1)
          articulationPointMarker[currentNode] = true;

        children++;
      }

      else{
        lowestTime[currentNode] = Math.min(lowestTime[currentNode], timeOfInsertion[neighbour]);
      }
    }

    if(children > 1 && parent == -1){
      articulationPointMarker[currentNode] = true;
    }
  }
}
