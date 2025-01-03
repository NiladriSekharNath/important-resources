package com.adidas.dsa.striversde.graphs;

/**
 * The key idea behind this algorithm is we are using All Nodes Shortest Path Algorithm
 *
 * This is used to find the multi-source shortest path
 *
 * This algorithm is good for all nodes like if we would want to find any node we can find all nodes
 *
 * this mainly works for Directed Graphs however we are given a directed graph we can convert an Undirected Graph to a DirectedGraph
 *
 * now the idea for this is
 *
 * we are initially given now not a adjacency lis,t but we are given an adjacency matrix
 *
 * now if we are given a matrix we would have something like this
 *
 *    0 1 2
 * 0 [0 2 1 ]
 * 1 [1 0 1 ]
 * 2 [2 1 0 ]
 *
 * if there is no edge between souce to vertex, we represent that as inf and dist[u][u] = 0, meaning node 0 to node 0 min dist = 0
 *
 * source node0 to targetnode 0, dist = 0
 *
 * now we have this matrix with the distances/cost represented by row -> column  or u -> v
 * example there is node from 2 -> 0, weight 2
 *
 * now we what we would be trying is to check throught all paths if we find a better distance
 *
 * the like above matrix via 0, 1, 2 (meaning the number of nodes)
 *
 * what we are saying is
 *
 * if we say for a particular node (2, 1) we are trying via node 1 (i.e. trying to find a path via node 1 )
 *
 * represented by dist[u][v] = Math.min(dist[u][via] + [via][v], dist[u][v])
 *
 * which is if we find a node if (dist[u][via] + dis[via][v] < dist[u][v])
 * then we set the distance[u][v]  = dist[u][via] + dist[via][v]
 *
 * now we do for all nodes dist(u,v) in the matrix (row, column) and for each entry we are checking with
 * all the via nodes
 *
 * which translates to this
 *
 * for( node via in [0 - n] nodes) {
 *   for(int u  in [0 - n]){
 *     for(int v in [0 - n]){
 *       if(dist[u][via] + dist[via][v] < dist[u][v])
 *             dist[u][v] = dist[u][via] + dist[via][v];
 *     }
 *   }
 * }
 *
 *
 * we are trying this operation everytime for each node
 *
 * so this is the idea
 *
 * Another point to this is we can find out if the graph contains a negative cycle
 *
 * let's consider this graph
 *   -1
 * 0 -> 1
 *
 * 1 <- 0
 *    -1
 *
 *
 * if we compute
 *
 *    0   1
 * 0 [0, -1]
 * 1 [-1  0]
 *
 * see we know dist[0][0] any node to same node will always be zero
 *
 * now if we do calculate for a particular iteration dis[0][0] (via 1) = min(dist[0][0], dist[0][1] + dist[1][0])
 *
 * now we get dist[0][0] = min(0, -1 + -1)
 *
 * dist[0][0] = -2
 *
 * which indicates there is negative cycle in this graph
 *
 *
 *
 *
 *
 *
 */
public class AllNodesShortestPathAlgorithmUsingFloydWarshall {
  public void shortestDistance(int[][] dist) {
    int nodes = dist.length;

    for(int u = 0 ; u < nodes ; u++){
      for(int v = 0 ; v < nodes ; v++){
        if(dist[u][v] == -1)
          dist[u][v] = (int)1e8;
        if(u == v)
          dist[u][v] = 0;
      }
    }

    for(int via = 0 ; via < nodes; via++){
      for(int u = 0 ; u < nodes ; u++){
        for(int v = 0 ; v < nodes ; v++){
          if(dist[u][via] + dist[via][v] < dist[u][v])
            dist[u][v] = dist[u][via] + dist[via][v];
        }
      }
    }

    /**
     * for(int i = 0 ; i < nodes; i++){
     *    if(dis[i][i] = 0;
     * }
     */


    for(int u = 0 ; u < nodes ; u++){
      for(int v = 0 ; v < nodes ; v++){
        if(dist[u][v] == (int)1e8)
          dist[u][v] = -1;

      }
    }
  }
}
