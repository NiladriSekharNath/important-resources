package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Find the topological sort order of a Graph using dfs
 *  *
 *  *     5 --> 0 <-- 4
 *  *     |           |
 *  *    \/          \/
 *  *    2 --> 3 --> 1
 *  *
 *  *    For this graph one of the topological sort is 5 4 2 3 1 0
 *
 *  We did the topo sort using dfs now we are doing the topo sort using bfs
 *
 *  the algorithm goes as follows
 *
 *  since we know toposort is a linear order of the graph edges
 *
 *  like we mean if we have en edge in A DAG 5 -> 2 which means first 5 comes and then 2 comes in the toposort
 *
 *  so using that idea we do this
 *
 *  Approach:
 *
 *  We take an indegree array = size of the nodes in the graph
 *
 *  for each neighbour in every node in the adjacency list add, we increment the value
 *
 *  For the above graph we have this adjacency list
 *
 *  0 : [] , 1 : [], 2 : [3], 3: [1], 4 : [0, 1],  5: [0, 2]
 *
 *  we take each neighbour and indegree[neighbour]++
 *
 *  indegree [0 -> 2, 1 -> 2, 2 -> 1, 3 -> 1, 4 -> 0, 5 -> 0] (here we are representing as [index -> indegree] just to make it easily readable)
 *
 *  now what we do initially to trigger the bfs is we take each node which has indegree = 0
 *
 *  and add it in the queue to trigger the process
 *
 *  toposort = []
 *
 *  while(queue is not empty()){
 *    element = queue.poll();
 *    toposort.add(element)
 *
 *    for(int neighbour : graph.get(element))
 *      // for each neighbour we reduce the indegree and check if the indegree is zero then we add the neighbour to the queue
 *      indegree[neighbour]--;
 *      if(indegree[neighbour] == 0)
 *        queue.add(neighbour)
 *  }
 *
 *  the above process means for this edge 5 -> 4
 *  where indegree [4 -> 1, 5 -> 0] when we start with 5 having zero degree then we take out 5 and then for its neighbour
 *  4 we reduce the indegree of 4 from  1 to 0, it in a way means we removed the edge from 5 to 4
 *
 *  5 -> 4
 *
 *  5    4 (no edge) this means we have considered the before node now we can add in the topo
 *
 *
 *
 *
 *
 *
 *
 */
public class TopologicalSortUsingBFS {
  static List<Integer> topologicalSort(List<List<Integer>> graph) {
    List<Integer> topoSort = new ArrayList<>();
    int size = graph.size();

    int[] indegree = new int[size];

    for(int currentNode = 0 ; currentNode < size; currentNode++){
      for(int neighbour : graph.get(currentNode))
        indegree[neighbour]++;
    }

    Queue<Integer> queue = new LinkedList<>();
    for(int currentNode = 0 ; currentNode < size ; currentNode++){
      if(indegree[currentNode] == 0)
        queue.add(currentNode);
    }

    while(!queue.isEmpty()){
      int currentNode = queue.poll();
      topoSort.add(currentNode);
      for(int neighbour : graph.get(currentNode)){
        indegree[neighbour]--;
        if(indegree[neighbour] == 0)
          queue.add(neighbour);
      }
    }

    return topoSort;
  }




}
