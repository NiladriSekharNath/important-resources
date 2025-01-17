package com.adidas.dsa.striversde.dynamicprogramming;

import java.util.Arrays;

/**
 * You have been given an N*M matrix filled with integer numbers, find the maximum sum that can be obtained from a path
 * starting from any cell in the first row to any cell in the last row.
 *
 * From a cell in a row, you can move to another cell directly below that row, or diagonally below left or right.
 * So from a particular cell (row, col), we can move in three directions i.e.
 *
 * Down: (row+1,col)
 * Down left diagonal: (row+1,col-1)
 * Down right diagonal: (row+1, col+1)
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 50
 * 1 <= N <= 100
 * 1 <= M <= 100
 * -10^4 <= matrix[i][j] <= 10^4
 *
 * Where 'T' is the number of test cases.
 * Where 'N' is the number of rows in the given matrix, and 'M' is the number of columns in the given matrix.
 * And, matrix[i][j] denotes the value at (i,j) cell in the matrix.
 *
 * Time Limit: 1sec
 * Input 1 :
 * 2
 * 4 4
 * 1 2 10 4
 * 100 3 2 1
 * 1 1 20 2
 * 1 2 2 1
 * 3 3
 * 10 2 3
 * 3 7 2
 * 8 1 5
 * Output 1 :
 * 105
 * 25
 * Explanation Of Input 1 :
 * In the first test case for the given matrix,
 *
 * The maximum path sum will be 2->100->1->2, So the sum is 105(2+100+1+2).
 *
 * In the second test case for the given matrix, the maximum path sum will be 10->7->8, So the sum is 25(10+7+8).
 * Input 2 :
 * 2
 * 3 3
 * 1 2 3
 * 9 8 7
 * 4 5 6
 * 4 6
 * 10 10 2 -13 20 4
 * 1 -9 -81 30 2 5
 * 0 10 4 -79 2 -10
 * 1 -5 2 20 -11 4
 * Output 2 :
 * 17
 * 74
 * Explanation Of Input 2 :
 * In the first test case for the given matrix, the maximum path sum will be 3->8->6, So the sum is 17(3+8+6).
 *
 * In the second test case for the given matrix, the maximum path sum will be 20->30->4->20, So the sum is 74(20+30+4+20).
 *
 *
 * Basically we can move from any column in the row 0 to any col in the last row (row - 1)
 *
 * by moving
 * Down: (row+1,col)
 * Down left diagonal: (row+1,col-1)
 * Down right diagonal: (row+1, col+1)
 *
 * so in this question, neither the start point nor the ending point is fixed
 *
 * so in recursion since we know there is no fixed starting point we would have to try for each path starting with
 * starting points
 * 1, 2, 10, 4 that is (0, 0), (0, 1), (0, 2), (0, 3)
 *
 * 1 2 10 4
 * 100 3 2 1
 * 1 1 20 2
 * 1 2 2 1
 *
 * That is exactly what is done in the recursion
 *
 *
 */

public class MaximumPathSumInGridVariableStartingAndEndpoints {
  public static int getMaxPathSum(int[][] matrix) {
    int rowSize = matrix.length, colSize = matrix[rowSize - 1].length;

    int[][] dp = new int[rowSize][colSize];

    for(int[] eachRow: dp){
      Arrays.fill(eachRow, -1);
    }

    int maxValue = - (int) 1e9 ;
    for(int col = 0 ; col < colSize; col++){
      maxValue = Math.max(maxValue, helper(0, col, matrix, rowSize, colSize, dp));
    }

    return maxValue;
  }

  private static int helper(int cR, int cC, int[][] grid, int rS, int cS, int[][] dp){
    if(cR < 0 || cC < 0 || cC >= cS || cR >= rS){
      return - (int)1e9;
    }

    if(dp[cR][cC] != -1) return dp[cR][cC];

    if(cR == rS - 1) return grid[cR][cC];

    int down = 0, downLeft = 0, downRight = 0;

    down = grid[cR][cC] + helper(cR + 1, cC, grid, rS, cS, dp);
    downLeft = grid[cR][cC] + helper(cR + 1, cC - 1, grid, rS, cS, dp);
    downRight = grid[cR][cC] + helper(cR + 1, cC + 1, grid, rS, cS, dp);

    return dp[cR][cC] = Math.max(down, Math.max(downLeft, downRight));

  }

  private static int tabulationApproach(int rS, int cS, int[][] grid){
    int[][] dp = new int[rS][cS];

    for (int col = 0; col < cS; col++) {
      dp[rS - 1][col] = grid[rS - 1][col];
    }


    for (int row = rS - 2; row >= 0; row--) {
      for (int col = cS - 1; col >= 0; col--) {

        /**
         * since here at say for 4 x 4 matrix if we are at col 3 -> if we try to find a value of dp[row + 1][col + 1]
         * we would be searching for dp[row][3 + 1 = 4] this is cause out of bounds exception hence adjusted to cover that
         * edge case with col < cS - 1, we can do for 4 size col, for 2 max we can search for 3
         *
         *
         * similarly for the case if we are searching for at col = 0 if we are searching for col - 1 we would be
         * going out of bounds again with -1 now the problem is we would have to keep col >= 1, atleast to search
         * for col - 1, position 0
         */
        int down = dp[row + 1][col],
            downLeft = col > 0 ? dp[row + 1][col - 1] : -(int) 1e9,
            downRight = col < cS - 1 ? dp[row + 1][col + 1] : -(int) 1e9;

        dp[row][col] = grid[row][col] + Math.max(down, Math.max(downLeft, downRight));
      }
    }

    int maxValue = -(int) 1e9;

    for(int col = 0 ; col < cS ; col++){
      maxValue = Math.max(maxValue, dp[0][col]);
    }

    return maxValue;
  }

  private static int tabulationSpaceOptimized(int rS, int cS, int[][] grid){

    int[] next = new int[cS];

    for (int col = 0; col < cS; col++) {
      next[col] = grid[rS - 1][col];
    }


    for (int row = rS - 2; row >= 0; row--) {
      int[] curr = new int[cS];
      for (int col = cS - 1; col >= 0; col--) {
        int down = next[col],
            downLeft = col > 0 ? next[col - 1] : -(int) 1e9,
            downRight = col < cS - 1 ? next[col + 1] : -(int) 1e9;
        curr[col] = grid[row][col] + Math.max(down, Math.max(downLeft, downRight));
      }

      next = curr;
    }

    int maxValue = -(int) 1e9;

    for(int col = 0 ; col < cS ; col++){
      maxValue = Math.max(maxValue, next[col]);
    }

    return maxValue;
  }

  public static void main(String[] args){
    MaximumPathSumInGridVariableStartingAndEndpoints.tabulationApproach(4, 4, new int[][]{{1, 2, 10, 4}, {100, 3, 2, 1}, {1, 1, 20, 2}, {1, 2, 2, 1}});
  }
}
