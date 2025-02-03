package com.adidas.dsa.striversde.dynamicprogramming.gridpattern;

import java.util.Arrays;

/**
 * Problem statement
 * You are present at point ‘A’ which is the top-left cell of an M X N matrix, your destination is point ‘B’, which is
 * the bottom-right cell of the same matrix. Your task is to find the total number of unique paths from point ‘A’ to point
 * ‘B’.In other words, you will be given the dimensions of the matrix as integers ‘M’ and ‘N’, your task is to find the
 * total number of unique paths from the cell MATRIX[0][0] to MATRIX['M' - 1]['N' - 1].
 *
 * To traverse in the matrix, you can either move Right or Down at each step. For example in a given point MATRIX[i] [j],
 * you can move to either MATRIX[i + 1][j] or MATRIX[i][j + 1].
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 ≤ T ≤ 100
 * 1 ≤ M ≤ 15
 * 1 ≤ N ≤ 15
 *
 * Where ‘M’ is the number of rows and ‘N’ is the number of columns in the matrix.
 *
 * Time limit: 1 sec
 * Sample Input 1:
 * 2
 * 2 2
 * 1 1
 * Sample Output 1:
 * 2
 * 1
 * Explanation of Sample Output 1:
 * In test case 1, we are given a 2 x 2 matrix, to move from matrix[0][0] to matrix[1][1] we have the following possible
 * paths.
 *
 * Path 1 = (0, 0) -> (0, 1) -> (1, 1)
 * Path 2 = (0, 0) -> (1, 0) -> (1, 1)
 *
 * Hence a total of 2 paths are available, so the output is 2.
 *
 * In test case 2, we are given a 1 x 1 matrix, hence we just have a single cell which is both the starting and ending
 * point. Hence the output is 1.
 * Sample Input 2:
 * 2
 * 3 2
 * 1 6
 * Sample Output 2:
 * 3
 * 1
 * Explanation of Sample Output 2:
 * In test case 1, we are given a 3 x 2 matrix, to move from matrix[0][0] to matrix[2][1] we have the following possible paths.
 *
 * Path 1 = (0, 0) -> (0, 1) -> (1, 1) -> (2, 1)
 * Path 2 = (0, 0) -> (1, 0) -> (2, 0) -> (2, 1)
 * Path 3 =  (0, 0) -> (1, 0) -> (1, 1) -> (2, 1)
 *
 * Hence a total of 3 paths are available, so the output is 3.
 *
 * In test case 2, we are given a 1 x 6 matrix, hence we just have a single row to traverse and thus total path will be 1.
 *
 * Basically we are asked to find the number of paths we can find if we are going from bottom right corner to top left corner
 * and our choices of paths are right or down
 *
 * So if we look at the problem we are moving from bottom right  corner to top left our choices are
 *
 * either go left or top (reverse order since we are going backwards that is instead of left -> we go right and instead of
 * bottom we go top)
 *
 * therefore in a m X n matrix we are going from (m - 1, n - 1) to (0, 0)
 *
 * once we reach top corner we understand that's a valid path so we return 1 as (1 path found) and we keeping adding up the paths
 *
 * TC: O(2^(m*n)) since for every square path we are trying 2 ways everytime to at max there are 2^(m*n) possibilities
 * SC: O((m - 1) + (n - 1)) path length because we are moving to at max the path length now the point
 *
 * We can see there are overlapping subproblems so this can be converted to DP now the point to note here is that
 *
 * since there is a dp way we see the changing variables since there are two changing variables we can say we need a 2D
 * matrix to represent the path
 *
 * if value is present for a current dp[cR][cC] != -1 then we return the answer dp[cR][cC],
 *
 * Time Complexity: O(M*N)
 *
 * Reason: At max, there will be M*N calls of recursion.
 *
 * Space Complexity: O((N-1)+(M-1)) + O(M*N)
 *
 * Reason: We are using a recursion stack space: O((N-1)+(M-1)), here (N-1)+(M-1) is the path length and an external DP Array of size ‘M*N’.
 *
 * Now we go for the tabulation approach
 */
public class UniquePaths {
  public static int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];
    for(int[] eachRow : dp)
      Arrays.fill(eachRow, -1);
    return helper(m - 1, n - 1, dp);
  }
  private static int helper(int cR, int cC, int[][] dp){
    if(cR == 0 && cC == 0) return 1;
    if(cR < 0 || cC < 0) return 0;

    if(dp[cR][cC] != -1) return dp[cR][cC];

    int leftCount = helper(cR, cC - 1, dp);
    int rightCount = helper(cR - 1, cC, dp);

    return dp[cR][cC] = leftCount + rightCount;
  }

  private static int tabulationApproach(int m, int n, int[][] dp){
    /**
     * Now tabulation approach is going from bottom to up so bottom means we are going from the base case to
     * to end
     *
     * Base Case means the 0, so we initialize the same
     */

    dp[0][0] = 0;

    for (int row = 0; row < m; row++) {
      for (int col = 0; col < n; col++) {
        if (row == 0 && col == 0) continue;
        else {
          int left = 0, top = 0;

          if(row > 0) top = dp[row - 1][col];
          if(col > 0) left = dp[row][col - 1];

          dp[row][col] = left + top ;
        }
      }
    }

    return dp[m - 1][n - 1];
  }

  /**
   *
   * now if we observe properly the tabulation Approach, for the space optimized solution we can see we only
   *
   * need the values dp[row - 1][col]  dp[row][col - 1]
   *
   * we just need to track the prev row and not the entire 2D array :
   *
   * For the value dp[row - 1][col], consider the below arbitrary example
   *
   *
   * [1, 2, 1, 3] -> prevRow
   * [-  -  -  -] -> currRow this the current Row which we need to compute, so simply we need to do this
   *
   * create a new prevRow with the size of the column, and create a temp currRow to compute the current Value
   *
   * [1, 2, 1, 3] -> prevRow
   * [-  -  -  -] -> currRow, after the currRow is computed we simply point prevRow = currentRow for the next iteration
   *
   * so we are covered with the case dp[row - 1][col]
   *
   * now for the case : dp[row][col - 1]
   *
   * if we see this we are requiring simply the prev Column Value for the currentRow we could carry a single variable
   * but we just need the currentRow[col - 1] for the value
   */
  private static int tabulationSpaceOptimized(int m, int n){
    /**
     * prevRow would have the size of the column as the number of elements
     */
    int[] prevRow = new int[n];


    for (int row = 0; row < m; row++) {

      int[] currRow = new int[n];

      for (int col = 0; col < n; col++) {
        if (row == 0 && col == 0){
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

          currRow[col] = left + top ;
        }
      }

      prevRow = currRow;
    }

    return prevRow[n - 1];

  }
}
