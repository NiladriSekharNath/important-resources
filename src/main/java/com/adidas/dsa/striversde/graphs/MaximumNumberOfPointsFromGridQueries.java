package com.adidas.dsa.striversde.graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * You are given an m x n integer matrix grid and an array queries of size k.
 *
 * Find an array answer of size k such that for each integer queries[i] you start in the top left cell of the matrix and repeat the following process:
 *
 * If queries[i] is strictly greater than the value of the current cell that you are in, then you get one point if it is your first time visiting this cell, and you can move to any adjacent cell in all 4 directions: up, down, left, and right.
 * Otherwise, you do not get any points, and you end this process.
 * After the process, answer[i] is the maximum number of points you can get. Note that for each query you are allowed to visit the same cell multiple times.
 *
 * Return the resulting array answer.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid = [[1,2,3],[2,5,7],[3,5,1]], queries = [5,6,2]
 * Output: [5,8,1]
 * Explanation: The diagrams above show which cells we visit to get points for each query.
 * Example 2:
 *
 *
 * Input: grid = [[5,2,1],[1,1,2]], queries = [3]
 * Output: [0]
 * Explanation: We can not get any points because the value of the top left cell is already greater than or equal to 3.
 *
 *
 * Constraints:
 *
 * m == grid.length
 * n == grid[i].length
 * 2 <= m, n <= 1000
 * 4 <= m * n <= 105
 * k == queries.length
 * 1 <= k <= 104
 * 1 <= grid[i][j], queries[i] <= 106
 *
 *
 * the core idea of this problem is
 *
 *
 * [[1, 2, 3]
 *  [2, 5, 7]
 *  [3, 5, 1]]
 *
 *  givens queries : [5, 6, 2]
 *
 *  each query we can travel as many points as long as grid[row][col] < eachQuery val, starting from (0, 0) going in 4 directions
 *
 *  up, down, left, right
 *
 *  example : 5 -> 1, 2 ,3, 2, 3 =  total five points [5], note these are grid values
 *            6 -> 1, 2, 3, 2, 3, 5, 1 = total 6 points [6]
 *
 *
 *  so idea for travelling each would cause us TLE if we start for each queries
 *
 *
 *  so the approach is if we sort the queries and track the indices,
 *
 *  we can fill them up
 *
 *  so the idea if we sort them then the natural order is preserved the lower query is visited first so
 *
 *  normal Dijkstra's works just fine with one pass inface
 *
 *  we keep a timer that keeps on incrementing the values and keep looping in the inner Dijkstra till we
 *
 *  gird[row][col] < queryVal
 *
 *  when it stops we add the current 'timer' value in the appropriate index position that will hold the answer
 *
 *
 */
public class MaximumNumberOfPointsFromGridQueries {

  private final int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
  public int[] maxPoints(int[][] grid, int[] queries) {

    int size = queries.length, result[] = new int[size], timer = 0, rows = grid.length, cols = grid[0].length;

    List<int[]> queriesIdx = new ArrayList<>();

    for(int cI = 0 ; cI < queries.length; cI++){
      queriesIdx.add(new int[]{queries[cI], cI});
    }

    Collections.sort(queriesIdx, (entry1, entry2) -> entry1[0] - entry2[0]);

    PriorityQueue<int[]> minHeap = new PriorityQueue<>((entry1, entry2) -> entry1[0] - entry2[0]);

    boolean[][] visited = new boolean[rows][cols];


    minHeap.add(new int[]{grid[0][0], 0, 0});

    visited[0][0] = true;

    for(int[] eachQuery : queriesIdx){

      int queryVal = eachQuery[0], queryIdx = eachQuery[1];

      while(!minHeap.isEmpty() && minHeap.peek()[0] < queryVal){
        int currentVal[] = minHeap.poll(), row = currentVal[1], col = currentVal[2];

        timer++;

        for(int[] dir : directions){
          int nextRow = row + dir[0], nextCol = col + dir[1];
          if(nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols
              && !visited[nextRow][nextCol]){

            visited[nextRow][nextCol] = true ;
            minHeap.add(new int[] {grid[nextRow][nextCol],  nextRow, nextCol});
          }
        }
      }

      result[queryIdx] = timer;
    }

    return result;

  }

  public static void main(String[] args){
    new MaximumNumberOfPointsFromGridQueries().maxPoints(new int[][]{{1, 2, 3}, {2, 5, 7}, {3, 5, 1}}, new int[]{5, 6, 2});
  }


}

