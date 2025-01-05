package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.List;

/**
 * Number Of Islands
 * Difficulty: MediumAccuracy: 60.65%Submissions: 46K+Points: 4
 * You are given a n,m which means the row and column of the 2D matrix and an array of  size k denoting the number of operations. Matrix elements is 0 if there is water or 1 if there is land. Originally, the 2D matrix is all 0 which means there is no land in the matrix. The array has k operator(s) and each operator has two integer A[i][0], A[i][1] means that you can change the cell matrix[A[i][0]][A[i][1]] from sea to island. Return how many island are there in the matrix after each operation.You need to return an array of size k.
 * Note : An island means group of 1s such that they share a common side.
 *
 * [0, 0, 0, 0]
 * [0, 0, 0, 0]
 * [0, 0, 0, 0]
 *
 * let's say we have queries like [[0,0],[1,1],[0,0],[0,1]]
 *
 * After 1st query
 *
 * [1, 0, 0, 0]
 * [0, 0, 0, 0]   noOfIslands = 1
 * [0, 0, 0, 0]
 *
 * After second query
 *
 * [1, 0, 0, 0]
 * [0, 1, 0, 0]  noOfIslands = 2
 * [0, 0, 0, 0]
 *
 * After third query
 *
 * [1, 0, 0, 0]
 * [0, 1, 0, 0]   noOfIslands = 2
 * [0, 0, 0, 0]
 *
 * After 4th Query
 *
 * [1, 1, 0, 0]
 * [0, 1, 0, 0]  noOfIslands = 1
 * [0, 0, 0, 0]
 *
 * what we see initially the entire matrix is zero this indicates water when we execute a query we make that to 1 indicating
 * that it is a land now
 *
 * at the third step we don't count the 1 only we count all the nodes adjacent (1,1) left, right, top, bottom
 * as well if there is land we take that as one and count them as one whole component
 *
 * 1 - 1
 *     |
 *     1
 *
 * so each time we are checking if that can be connected to count as a single component
 *
 * Idea is we are using DisjointSetUnion
 *
 * we are treating the entire matrix[row][column] as a graph node, we are using the 2-D to 1D representation
 * row*colSize + column to get the 1D equivalent of the matrix
 *
 * [0, 1,  2,  3]
 * [4, 5,  6,  7]
 * [8, 9, 10, 11]
 *
 * now each vertex[0,1,..11] is a node
 *
 * Algorithm goes as follows once we get a query we see if the node is currently visited or not if not visited
 * we increase the count of the islands
 *
 * now we check to the right, left, top, down if they have an island(value = 1) if yes, we decrease the count of the island
 *
 * let's walkthrough example when we receive node[0,1] we increase the islandCount from 2 to 3
 *
 * we see on it's left -> 0, 0, there is an island matrix[0][0] is land what we do is we check if node0 is connected or not
 *
 * we see node0 is not connected to 1, so we connect (0, 1), since a connection is made we decrement the islandCount by 1
 *
 * islandCount = 2
 *
 * again for node1 we see [1,1] bottom node5 is not connected to the nodes (0 - 1) we connect and again decrement the
 * islandCount by 1
 *
 * so now islandCount = 1
 *
 * and now the component 0 - 1 - 5
 *
 * Now point is if we had any nodes that are already connected to the component then we don't decrement the "islandCount"
 * only after sucessful connection of a component we decrement by 1
 *
 */
public class NumberOfIslandsIIOnlineQueries {
  private final int[][] directions = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

  public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
    List<Integer> resultList = new ArrayList<>();

    int islandCount = 0;

    int[][] nums = new int[rows][cols];


    DisjointSetUnion dsu = new DisjointSetUnion(rows * cols );

    for (int[] currentNode : operators) {


      int currentRow = currentNode[0], currentColumn = currentNode[1], currentVertex = currentRow * cols + currentColumn;
      if (nums[currentRow][currentColumn] != 1) {
        islandCount++;
        nums[currentRow][currentColumn] = 1;
        for (int[] dir : directions) {
          int nextRow = currentRow + dir[0], nextColumn = currentColumn + dir[1];

          if (nextRow >= 0 && nextRow < rows && nextColumn >= 0 && nextColumn < cols
              && nums[nextRow][nextColumn] == 1) {

            int nextVertex = nextRow * cols + nextColumn;

            if (!dsu.isConnected(currentVertex, nextVertex)) {
              dsu.unionByRank(currentVertex, nextVertex);
              islandCount--;
            }
          }
        }

      }

      resultList.add(islandCount);
    }

    return resultList;
  }

  public class DisjointSetUnion {

    /**
     * vertices represent number of nodes in the graph if we have a zero based indexing then we pass n
     * otherwise if 1-based indexing we pass n + 1
     */
    private int vertices;

    private int[] parent;
    private int[] rank;

    /**
     * now in the DisjointSetUnion we don't need both rank and size as both are necessary we can go with either one
     */

    public DisjointSetUnion(int vertices) {
      this.vertices = vertices;
      this.parent = new int[vertices];
      this.rank = new int[vertices];


      for (int i = 0; i < vertices; i++) {
        parent[i] = i;
        /**
         * rank by default is initialized to zero always if we don't initialize, default with Java
         *
         * if we however use size we keep the size = 1 for the nodes
         */


      }

    }

    public int findParent(int node) {
      if (parent[node] == node)
        return node;
      return parent[node] = findParent(parent[node]);
    }

    public void unionByRank(int u, int v) {
      int parentU = findParent(u), parentV = findParent(v);

      if (parentU == parentV) return;

      if (rank[parentU] < rank[parentV]) {
        parent[parentU] = parentV;
      } else if (rank[parentV] < rank[parentU]) {
        parent[parentV] = parentU;
      } else {
        parent[parentV] = parentU;
        rank[parentU]++;
      }
    }

    /**
     * we don't need both unionByRank or unionBySize
     */


    public boolean isConnected(int u, int v) {
      return findParent(u) == findParent(v);
    }
  }

  public static void main(String args[]){
    new NumberOfIslandsIIOnlineQueries().numOfIslands(4, 5, new int[][]{{0, 1}, {1,1}, {3,3}, {3,4}});
  }
}
