package com.adidas.dsa.striversde.dynamicprogramming;

import java.util.Arrays;

/**
 * Problem statement
 * You are given a triangular array/list 'TRIANGLE'. Your task is to return the minimum path sum to reach from the top
 * to the bottom row.
 *
 * The triangle array will have N rows and the i-th row, where 0 <= i < N will have i + 1 elements.
 *
 * You can move only to the adjacent number of row below each step. For example, if you are at index j in row i,
 * then you can move to i or i + 1 index in row j + 1 in each step.
 *
 * For Example :
 * If the array given is 'TRIANGLE' = [[1], [2,3], [3,6,7], [8,9,6,1]] the triangle array will look like:
 *
 * 1
 * 2,3
 * 3,6,7
 * 8,9,6,10
 *
 * For the given triangle array the minimum sum path would be 1->2->3->8. Hence the answer would be 14.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 5
 * 1 <= N <= 10^3
 * -10^6 <= TRIANGLE[i][pos] <= 10^6 ,
 *
 * Where 'TRIANGLE[i][pos]' is the element at row = 'i' & position = 'pos' in triangle array.
 *
 * Time limit: 1 sec
 * Sample Input 1 :
 * 2
 * 4
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 * 1
 * -10
 * Sample output 1 :
 * 11
 * -10
 * Sample Input explanation:
 * Test case 1:
 * Here our triangle array is:
 *
 * 2
 * 3 4
 * 6 5 7
 * 4 1 8 3
 *
 * In this array, the minimum path will be 2->3->5->1, so the minimum sum path would be 2+3+5+1=11
 *
 * Test case 2:
 * In this case, there is one row. Thus, the minimum path will be -10, and the minimum sum path=-10.
 * Sample input 2 :
 * 2
 * 4
 * 1
 * 2 3
 * 4 5 6
 * 7 8 9 10
 * 3
 * 5
 * -1 3
 * 22 1 -9
 * Sample Output 2 :
 * 14
 * -1
 *
 * Given a triangle like this structure :
 *
 * 1
 * 2 3
 * 3 6 7
 * 8 9 6 10
 *
 *
 * Basically the problem is saying we are at the first index (0, 0) in the matrix is in a right triangular fashion
 * we are asked to move from the top to the bottom (any column value [8, 9, 6, 10], in the last row) but we could move
 * in these directions only bottom and diagonal
 *
 * Now generally in the problems like Unique Paths 1, 2 we are moving from the right corner to bottom left
 */
public class MinimumPathSumInTriangularGrid {
  public static int minimumPathSum(int[][] triangle, int n) {
    int rowSize = triangle.length;
    int[][] dp = new int[rowSize][triangle[rowSize - 1].length];

    for(int[] eachRow : dp){
      Arrays.fill(eachRow, -1);
    }
    return helper(0, 0, rowSize, triangle, dp);
  }
  private static int helper(int cR, int cC, int rS, int[][] triangle, int[][] dp){
    if(cR > rS) return (int) 1e9;

    if(cR == rS - 1) return triangle[cR][cC];

    if(dp[cR][cC] != -1) return dp[cR][cC];

    return dp[cR][cC] = triangle[cR][cC] +
        Math.min(helper(cR + 1, cC, rS, triangle, dp),
            helper(cR + 1, cC + 1, rS, triangle, dp));
  }

  /**
   *
   * @param rS rowSize
   * @param cS columnSize
   * @param triangle the original grid
   * @return
   *
   * so we are going from the bottom to the top
   *
   * 1
   * 2 3
   * 3 6 7
   * 8 9 6 10
   *
   * now since the array is like this above
   *
   * for each row when we are moving in the column we are going from row to 0
   *
   * if we see this
   *
   * 1           -> row 0 cols 0
   * 2 3         -> row 1 cols 0, 1
   * 3 6 7       -> row 2 cols 0, 1, 2
   * 8 9 6 10    -> row 3 cols 0, 1, 2, 3
   *
   * so if we see the above when we iterate from row2 (size - 2) to 0 we go like this as shown here
   *
   * Current iteration, row = 2
   * from, for(col = row ; col >= 0 col --)
   *
   */

  private static int tabulationApproach(int rS, int cS,  int[][] triangle){

    int[][] dp = new int[rS][cS];

    for(int col = 0; col < cS ; col++){
      dp[rS - 1][col] = triangle[rS - 1][col];
    }

    for(int row = rS - 2 ; row >= 0 ; row --){
      /**
       * for(int col = 0 ; col <= row ; col++){
       *
       * For this question the above for loop still works for us
       *
       *
       *
       * as a general rule of thumb if we are moving f(row - 1, col)
       *
       * we write the tabulation in the reverse order like this
       *
       * for(int row = 0 ; row < rowSize; row++) ...
       *
       * Now since we have here Math.min(helper(cR + 1, cC, rS, triangle, dp), helper(cR + 1, cC + 1, rS, triangle, dp)
       *
       *  +1 or some + sign
       *
       *  we move in the reverse fashion
       *
       *  for( col = colSize ; col >= 0 ; col --)
       *
       *  for this particular question we only need from the row
       *
       *  hence we move from
       *
       *  for(int col = row ; col >= 0 ; col--){
       *
       *
       */

        for(int col = row ; col >= 0 ; col--){
          dp[row][col] = triangle[row][col] + Math.min(dp[row + 1][col], dp[row + 1][col + 1]);
      }
    }

    return dp[0][0];
  }

  /**
   *
   * now since for every col we are moving from ->  row to 0
   *
   * so for let's say row = 2, we need to move from 2, 1, 0
   *
   * so a size of current Row, curr = row + 1 size
   *
   * curr = 3
   */

  private static int tabulationSpaceOptimized(int rS, int cS, int[][] triangle){
    int[] next = new int[cS];

    for(int col = 0; col < cS ; col++){
      next[col] = triangle[rS - 1][col];
    }

    for(int row = rS - 2 ; row >= 0 ; row --){

      int[] curr = new int[row + 1];

      for(int col = row ; col >= 0 ; col--){
        curr[col] = triangle[row][col] + Math.min(next[col], next[col + 1]);
      }

      next = curr;
    }

    return next[0];
  }

  public static void main(String[] args){
    MinimumPathSumInTriangularGrid.tabulationApproach(4, 4, new int[][]{{2}, {3, 4}, {6, 5, 7}, {4, 1, 8, 3}});
  }
}
