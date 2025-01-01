package com.adidas.dsa.striversde.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {

  /**
   * You are given an m x n grid where each cell can have one of three values:
   *
   * 0 representing an empty cell,
   * 1 representing a fresh orange, or
   * 2 representing a rotten orange.
   * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
   *
   * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
   *
   *
   *
   * Example 1:
   *
   *
   * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
   * Output: 4
   * Example 2:
   *
   * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
   * Output: -1
   * Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
   * Example 3:
   *
   * Input: grid = [[0,2]]
   * Output: 0
   * Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
   *
   *
   * Constraints:
   *
   * m == grid.length
   * n == grid[i].length
   * 1 <= m, n <= 10
   * grid[i][j] is 0, 1, or 2.
   *
   *
   * Approach is simple we need to do BFS traversal from the nodes where there are rotten oranges (matrix[row][column] = 2)
   *
   * we add all them and keep a "freshCount" for the fresh oranges matrix[row][column] = 1
   * we keep adding doing bfs and keep the counting of the bfs size bound since we need all oranges at a distance + 1 from source
   * be evaluated at the same time
   * and keep adding the result
   *
   *
   */
  public int orangesRotting(int[][] grid) {
    int freshCount = 0;
    int rows = grid.length;
    int columns = grid[0].length;
    Queue<int[]> queue = new LinkedList<>();
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        if (grid[i][j] == 2) queue.add(new int[]{i, j});
        if (grid[i][j] == 1) freshCount++;
      }
    }

    int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int minutes = 0;

    while (!queue.isEmpty() && freshCount > 0) {
      int size = queue.size();
      minutes++; //it may happen that we will be able to rotten the fresh oranges if they exists if they not that means not need for this loop;

      for (int i = 0; i < size; i++) {
        int[] current = queue.remove();
        int row = current[0];
        int column = current[1];

        for (int[] direction : directions) {
          int r = row + direction[0];
          int c = column + direction[1];

          if (r >= 0 && c >= 0 && r < rows && c < columns && grid[r][c] == 1) {
            //rot the fresh;
            grid[r][c] = 2;
            freshCount--;
            //add the rotten;
            queue.add(new int[]{r, c});
          }
        }
      }
    }
    //did we able to rotten the fresh oranges based on the assumption
    return freshCount == 0 ? minutes : -1;
  }
}
