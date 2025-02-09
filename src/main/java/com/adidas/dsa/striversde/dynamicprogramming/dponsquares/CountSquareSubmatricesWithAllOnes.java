package com.adidas.dsa.striversde.dynamicprogramming.dponsquares;

/**
 * Problem statement
 * A matrix 'arr' with 'n' rows and 'm' columns is given.
 *
 *
 *
 * Count the number of square submatrices in matrix ‘arr’ with all elements equal to 1.
 *
 *
 *
 * A square matrix is a matrix with square dimensions.
 *
 *
 *
 * Example :
 * Input: If 'n' = 2, 'm' = 2, and 'arr' = [ [1, 1], [1, 1] ].
 *
 * Output: 5
 *
 * Explanation: We have 4 square submatrices of size 1*1 and 1 square submatrix of size 2*2.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 2 2
 * 1 0
 * 0 1
 * Sample Output 1 :
 * 2
 * Explanation of sample input 1:
 * There are two square submatrices of size 1*1, so the answer is 2.
 * Sample Input 2 :
 * 3 4
 * 0 1 1 0
 * 1 1 1 0
 * 0 0 1 0
 * Sample Output 2 :
 * 7
 * Explanation of sample input 2:
 * There are six square submatrices of size 1*1, and there is one square submatrix of size 2*2. So, the answer is 6 + 1 = 7.
 * Expected time complexity:
 * The expected time complexity is O(n*m), where 'n' and 'm' are the dimensions of the matrix.
 * Constraints :
 * 1 ≤ 'n', 'm' ≤ 1000
 * 0 ≤ 'arr'[i][j] ≤ 1
 *
 * Time limit: 1 sec
 *
 *
 * THE IDEA OF THE PROBLEM IS QUITE SIMPLE :
 *   0 1 2 3
 * 0 0 1 1 0
 * 1 1 1 1 1
 * 2 0 0 1 0
 *
 * we have this above array with us -> we are counting the number of 1 size squares => nums[0][1], nums[0][2]
 *                                                                   2 size squares => nums[0][1], nums[0][2], nums[1][1],
 *                                                                                     nums[1][2]
 * now the point to this question all the questions we have been doing using recursion top down
 *
 * but usually for questions on squares -> we go with bottoms up
 *
 * the most important idea here is
 *
 *      0 1 2 3
 *  * 0 0 1 1 0
 *  * 1 1 1 1 1
 *  * 2 0 0 1 0
 *
 *  we create a similar size dp array
 *         j
 *     0 0 0 0
 * i   0 0 0 0
 *     0 0 0 0
 *
 * at every dp[i][j] we say we are trying to find a square ending with bottom right corner (i, j)
 *
 *
 *    since at every 1 we count as 1 and every 0 we count 0 or no squares
 *
 *         0 1 2 3
 *  *  * 0 0 1 1 1
 *  *  * 1 1 1 1 1
 *  *  * 2 0 0 1 0
 *
 *  we copy the zero row and zero col
 *          0  1  2  3
 *       0  0, 1, 1, 1
 *       1  1  1  2  2
 *       2  0
 *
 *          at element -> 1, 2 we see there are square at the index itself and 3 ones adding up from the
 *                        leftdiag,(left - 1, right - 1)
 *                        top and left col (row, col - 1)
 *
 *                        so taking the squares we see we adding another 1
 *
 *          however 1,3 ->
 *                       we see a diff story we cannot consider 1-3 and 0 - 1 size as that would form a rectangle
 *                       so we take min( leftdiag,(left - 1, right - 1)
 *  *                        top and left col (row, col - 1)) + 1 -> add 1 to make a square ->
 *                           IF THE CURRENT ELEMENT IS NOT A SQUARE
 *
 *          LATER WE ADD ALL THE VALUES IN THE DP TABLE TO GET THE TOTAL NUMBER OF SQUARES
 */
public class CountSquareSubmatricesWithAllOnes {
  public static int countSquares(int n, int m, int[][] nums) {
    int[][] dp = new int[n][m];

    for(int row = 0; row < n; row++){
      dp[row][0] = nums[row][0];
    }

    for(int col = 0; col < m; col++){
      dp[0][col] = nums[0][col];
    }
    for(int row = 1; row < n; row++){
      for(int col = 1; col < m; col++){
        if(nums[row][col] == 0) dp[row][col] = 0;
        else dp[row][col] = 1 + Math.min(dp[row - 1][col], Math.min(dp[row][col - 1], dp[row - 1][col - 1]));
      }
    }
    int totalSquares = 0;
    for(int[] row : dp){
      for(int val : row){
        totalSquares += val ;
      }

    }

    return totalSquares ;
  }
}
