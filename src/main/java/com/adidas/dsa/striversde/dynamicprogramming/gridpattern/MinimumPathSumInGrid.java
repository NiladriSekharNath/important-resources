package com.adidas.dsa.striversde.dynamicprogramming.gridpattern;

import java.util.Arrays;

/**
 * Problem statement
 * Ninjaland is a country in the shape of a 2-Dimensional grid 'GRID', with 'N' rows and 'M' columns.
 * Each point in the grid has some cost associated with it.
 *
 *
 *
 * Find a path from top left i.e. (0, 0) to the bottom right i.e. ('N' - 1, 'M' - 1) which minimizes
 * the sum of the cost of all the numbers along the path. You need to tell the minimum sum of that path.
 *
 *
 *
 * Note:
 * You can only move down or right at any point in time.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 100
 * 1 <= N, M <= 10^2
 * 1 <= GRID[i][j] <= 10^5
 *
 * Where 'GRID[i][j]' denotes the value of the cell in the matrix.
 *
 * Time limit: 1 sec
 * Sample Input 1:
 * 2
 * 2 3
 * 5 9 6
 * 11 5 2
 * 1 1
 * 5
 * Sample Output 1:
 * 21
 * 5
 * Explanation For Sample Output 1:
 * In test case 1, Consider a grid of 2*3:
 *
 * For this the grid the path with minimum value is (0,0) -> (0,1) -> (1,1) -> (1,2).
 * And the sum along this path is 5 + 9 +5 + 2 = 21. So the ans is 21.
 *
 * In test case 2, The given grid is:
 *
 * For this the grid the path with minimum value is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2).
 * The sum along this path is 1 + 2 + 3 + 4 + 9 = 19.
 * Sample Input 2:
 * 2
 * 2 2
 * 5 6
 * 1 2
 * 3 3
 * 1 2 3
 * 4 5 4
 * 7 5 9
 * Sample Output 2:
 * 8
 * 19
 * Explanation For Sample Output 2:
 * In test case 1, For this the grid the path with minimum value is (0,0) -> (1,0) -> (1,1).
 * The sum along this path is 5 + 1 + 2 = 8.
 *
 * In test case 2, The given grid is:
 *
 * For this the grid the path with minimum value is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2).
 * The sum along this path is 1 + 2 + 3 + 4 + 9 = 19.
 *
 * Well the approach for this problem is similar to the UniquePathsI and UniquePathII
 * once we encounter an out of bounds case we return infinity signifying there are no paths in this direction
 *
 * if(cR < 0 || cC < 0) return (int) 1e9;
 *
 * Note we can use either 1e9 or Integer.MAX_VALUE/2 instead of Integer.MAX_VALUE becuase if we use Integer.MAX_VALUE
 * we will get overflow if we add something to this, so this is a problem
 *
 */
public class MinimumPathSumInGrid {
  public static int minSumPath(int[][] grid) {
    int rowSize = grid.length, colSize = grid[rowSize - 1].length,
        dp[][] = new int[rowSize][colSize];

    for(int[] eachRow : dp){
      Arrays.fill(eachRow, -1);
    }
    return helper(rowSize - 1, colSize - 1, grid, dp);
  }

  private static int helper(int cR, int cC, int[][] nums, int[][] dp){
    if(cR == 0 && cC == 0) return nums[cR][cC];

    if(cR < 0 || cC < 0) return (int) 1e9;

    if(dp[cR][cC] != -1) return dp[cR][cC];

    int left = 0, top = 0 ;
    left = nums[cR][cC] + helper(cR - 1, cC, nums, dp);
    top = nums[cR][cC] + helper(cR, cC - 1, nums, dp);
    return dp[cR][cC] = Math.min(left, top);
  }

  private static int tabulationSpaceOptimized(int m, int n, int[][] grid){
    /**
     * prevRow would have the size of the column as the number of elements
     */
    int[] prevRow = new int[n];



    for (int row = 0; row < m; row++) {

      int[] currRow = new int[n];

      for (int col = 0; col < n; col++) {
        if (row == 0 && col == 0){

          currRow[col] = grid[row][col];
          continue;
        }
        else {

          /**
           * now here we initialized the base case because initially the left and top will be the max value
           */
          int left = (int) 1e9, top = (int) 1e9;

          if(row > 0) top = prevRow[col];
          if(col > 0) left = currRow[col - 1];

          currRow[col] = grid[row][col] + Math.min(top, left) ;
        }
      }

      prevRow = currRow;
    }

    return prevRow[n - 1];

  }
}
