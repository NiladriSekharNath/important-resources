package com.adidas.dsa.striversde.dynamicprogramming.gridpattern;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Problem statement
 * Given a ‘N’ * ’M’ maze with obstacles, count and return the number of unique paths to reach the right-bottom cell
 * from the top-left cell. A cell in the given maze has a value '-1' if it is a blockage or dead-end, else 0.
 * From a given cell, we are allowed to move to cells (i+1, j) and (i, j+1) only. Since the answer can be large,
 * print it modulo 10^9 + 7.
 *
 * For Example :
 * Consider the maze below :
 * 0 0 0
 * 0 -1 0
 * 0 0 0
 *
 * There are two ways to reach the bottom left corner -
 *
 * (1, 1) -> (1, 2) -> (1, 3) -> (2, 3) -> (3, 3)
 * (1, 1) -> (2, 1) -> (3, 1) -> (3, 2) -> (3, 3)
 *
 * Hence the answer for the above test case is 2.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 10
 * 1 <= N,M <= 200
 *
 * Note: It is guaranteed that the top-left cell does not have an obstacle.
 *
 * Time Limit: 1 sec
 * Sample Input 1 :
 * 2
 *
 * 2 2
 *
 * 0 0
 * 0 0
 *
 * 3 3
 *
 * 0 0 0
 * 0 -1 0
 * 0 0 0
 * Sample Output 1 :
 * 2
 * 2
 * Explanation For Sample Output 1 :
 * For the first test case, there are two possible paths to reach (2, 2) from (1, 1) :
 *     (1, 1) -> (1, 2) -> (2, 2)
 *     (1, 1) -> (2, 1) -> (2, 2)
 *
 * For the second test case, there are two ways to reach the bottom left corner -
 * (1, 1) -> (1, 2) -> (1, 3) -> (2, 3) -> (3, 3)
 * (1, 1) -> (2, 1) -> (3, 1) -> (3, 2) -> (3, 3)
 * Sample Input 2 :
 * 1
 * 2 2
 * 0 -1
 * -1  0
 * Sample Output 2 :
 * 0
 *
 *
 * Same solution as Unique Paths I, just if we get a dead cell where nums[i][j] == -1 we return 0 from there
 *
 */
public class UniquePathsII {
  static int mazeObstacles(int n, int m, ArrayList<ArrayList<Integer>> mat) {
    int[][] dp = new int[m][n];
    for(int[] eachRow : dp)
      Arrays.fill(eachRow, -1);
    return helper(n - 1, m - 1, mat, dp);
  }

  private static int helper(int cR, int cC, ArrayList<ArrayList<Integer>> nums, int[][] dp){
    if(cR == 0 && cC == 0) return 1;
    if(cR < 0 || cC < 0 || nums.get(cR).get(cC) == -1) return 0;

    if(dp[cR][cC] != -1) return dp[cR][cC];

    int leftCount = helper(cR, cC - 1, nums, dp);
    int rightCount = helper(cR - 1, cC, nums, dp);

    return dp[cR][cC] = leftCount + rightCount;
  }

  /**
   *
   * @param m -> total rows in the matrix
   * @param n -> total colums in the matrix
   * @param nums
   * @return
   *
   * if we see in the recursion with memoization solution we added just one extra case,
   * if
   *  nums.get(cR).get(cC) == -1 then we return 0; not a valid path found
   *
   *  so similarly we adjusted the same condition in the tabulation or the space optimized solution approach loop
   */

  private static int tabulationSpaceOptimized(int m, int n, ArrayList<ArrayList<Integer>> nums){
    /**
     * prevRow would have the size of the column as the number of elements
     */
    int[] prevRow = new int[n];


    for (int row = 0; row < m; row++) {

      int[] currRow = new int[n];
      int mod = (int)(1e9 + 7);

      for (int col = 0; col < n; col++) {
        /**
         * here is the adjustment
         */
        if(nums.get(row).get(col) == -1) currRow[col] = 0;
        else if (row == 0 && col == 0 ){
          /***
           * in the space optimized approach we initialized this one right here because we know the dp[0][0] = 0;
           *
           * which is represented by currRow[col = 0] = 1; so we had to write it here
           */
          currRow[col] = 1;
          continue;
        }
        else {
          int left = 0, top = 0;

          if(row > 0) top = prevRow[col];
          if(col > 0) left = currRow[col - 1];

          currRow[col] =( left + top) % mod ;
        }
      }

      prevRow = currRow;
    }

    return prevRow[n - 1];




  }
}
