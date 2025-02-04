package com.adidas.dsa.striversde.dynamicprogramming.mcm;

import java.util.Arrays;

/**
 * Problem statement
 * Given a chain of matrices A1, A2, A3,.....An. Your task is to find out the minimum cost to multiply these matrices.
 * The cost of matrix multiplication is defined as the number of scalar multiplications.
 * A Chain of matrices A1, A2, A3,.....An is represented by a sequence of numbers
 * in an array ‘arr’ where the dimension of 1st matrix is equal to arr[0] * arr[1] , 2nd matrix is arr[1] * arr[2], and so on.
 * <p>
 * For example:
 * For arr[ ] = { 10, 20, 30, 40}, matrix A1 = [10 * 20], A2 = [20 * 30], A3 = [30 * 40]
 * <p>
 * Scalar multiplication of matrix with dimension 10 * 20 is equal to 200.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * 2
 * 4
 * 4 5 3 2
 * 4
 * 10 15 20 25
 * Sample Output 1:
 * 70
 * 8000
 * Sample Output Explanation 1:
 * In the first test case, there are three matrices of dimensions A = [4 5], B = [5 3] and C = [3 2].
 * The most efficient order of multiplication is A * ( B * C).
 * Cost of ( B * C ) = 5 * 3 * 2 = 30  and (B * C) = [5 2] and A * (B * C) = [ 4 5] * [5 2] = 4 * 5 * 2 = 40.
 * So the overall cost is equal to 30 + 40 =70.
 * <p>
 * In the second test case, there are two ways to multiply the chain - A1*(A2*A3) or (A1*A2)*A3.
 * <p>
 * If we multiply in order- A1*(A2*A3), then the number of multiplications required is 11250.
 * <p>
 * If we multiply in order- (A1*A2)*A3, then the number of multiplications required is 8000.
 * <p>
 * Thus a minimum number of multiplications required is 8000.
 * Sample Input 2:
 * 1
 * 4
 * 1 4 3 2
 * Sample Output 2:
 * 18
 * Explanation of Sample Output 2:
 * In the first test case, there are three matrices of dimensions A = [1 4], B = [4 3] and C = [3 2].
 * The most efficient order of multiplication is (A *  B) * C .
 *
 *
 * Given to us the matrix dimensions we are required to find the minimum multiplications to multiply
 *
 *  0   1    2   3   4
 * [10, 20, 30, 40, 50]
 *      A   B   C   D
 *
 * 1st Matrix A -> [0] * [1] -> 10 x 20
 * 2nd Matrix B -> [1] * [2] -> 20 x 30
 * 3rd Matrix C -> [2] * [3] -> 30 x 40
 * 4th Matrix D -> [3] * [4] -> 40 x 50
 *
 * so ith Matrix Ai -> [i - 1] * [i]
 *
 * to try out all Combinations :
 *
 * ABCD -> A(BCD)
 *      -> (AB)(CD)
 *      -> (ABC)D
 *
 *      these are the combinations/ways we are required to try out all the ways to compute the result
 *
 *
 * so if I am looking to solve the above in the matrix we would write the function like this
 *
 * [10, 20, 30, 40, 50]
 *      A   B   C   D
 *      i           j
 *
 *  i = start/low = 1
 *  j = end/high = n - 1
 *
 *  so we would call the function f(1, n - 1)
 *
 *  f(1, 4) -> returns the min multiplications to multiply matrix 1 -> 4
 *
 * now in the base case for a single matrix (i == j) return 0 as we need 0 multiplications
 *
 *   0   1   2   3  4
 * [10, 20, 30, 40, 50]
 *       A   B   C   D
 *       i           j                                                or other way ->
 *
 * k = (i -> j - 1) == 1 -> 3                                    |    k = (i + 1, j)
 * f(i, k), f(k + 1, j)                                          |
 *                                                               |   f(i, k - 1) f(k, j)
 * here we are going till j because k + 1, for k = 3             |
 * would be empty                                                |
 *                                                               |
 *                                                               |
 *                                                               |
 * k = 1, f(i, k) = f(1, 1), f(k + 1, j) = f(2, 4) -> A(BCD)     |  k = 2, f(1, 1), f(2, 4)
 * k = 2, f(i, k) = f(1, 2), f(k + 1, j) = f(3, 4) -> (AB)(CD)   |  k = 3  f(1, 2), f(3, 4)
 * k = 3, f(i, k) = f(1, 3), f(k + 1, j) = f(4, 4) -> (ABC)D     |  k = 4  f(1, 3), f(4, 4)
 *
 * however we want we can write this
 *
 * now we are required to find the min cost
 *
 * let's say we take the left one and let's take one of the partitions
 *
 * f(1, 4)
 *
 * f(1, 1), f(2, 4)
 *
 * let's write for us A = 10 x 20, B = 20 x 30, C = 30 x 40, D = 40 x 50
 *
 *
 * f(1, 1), f(2, 4)
 *      A
 * 10 x 20 (B C D) k = 1
 *
 *
 *       B         C        D                     we are trying BC first then D, we are simply writing the resultant matrix
 *   (20 x 30) (30 x 40) (40 x 50)
 *
 *    (20 x 40)    (40 x 50)
 *
 *          20 x 50
 *
 *
 * Now finally we are doing A x (BCD)
 *  (10 x 20)  x (20 x 50)
 *      10 x 20 x 50           i = 0,  k = 1,  j = 4
 *
 * so if we see here correctly we are writing
 *
 * nums[low - 1] * nums[k] * nums[high]
 *
 * now obviously we are adding
 *
 * f(1, k)
 *
 * k = (i, j -1)
 *
 * f(i, k)
 * f(k + 1, j)
 *
 * minCost = Math.min(minCost, f(i,k) + f(k + 1, j) + (nums[i - 1] * nums[k] * nums[j])
 *
 *
 *
 */

public class MatrixChainMultiplication {
  public static int matrixMultiplication(int[] arr, int n) {
    int[][] dp = new int[n][n];
    for (int[] row : dp)
      Arrays.fill(row, -1);
    return helper(1, n - 1, arr, dp);
  }

  private static int helper(int low, int high, int[] nums, int[][] dp) {
    if (low == high) return 0;

    if (dp[low][high] != -1) return dp[low][high];

    int minValue = (int) 1e9;
    for (int start = low; start <= high - 1; start++) {
      int left = helper(low, start, nums, dp);
      int right = helper(start + 1, high, nums, dp);
      int totalCurr = nums[low - 1] * nums[start] * nums[high];
      minValue = Math.min(minValue, totalCurr + left + right);
    }

    return dp[low][high] = minValue;
  }

  /**
   *
   * we are writing the tabulation code for this mcm now in order to do this let's think this way
   *
   * we are calculating
   *
   * f(i, j) -> f(1, n - 1)
   *
   * now in the top down approach i -> 1, n - 1,         bottoms up -> (n - 1, 1)
   *                              j -> n - 1, 1          bottoms up -> (1, n - 1)
   *
   *
   * now for the bottoms up approach f(i, j) when we are calculating there is no point to start j from 1 because
   *
   * when we need f(i, j) and for i == j we are returning 0 there no point in j < i,
   *
   * for valid, f(i, j) there to be valid j should be > i at least
   *
   * so instead of j being (1, n - 1) it should be (i + 1, n - 1)
   *
   *
   *
   *
   * also for the base case i = j return 0 ;
   *
   * also i is represented by row, j is represented by col
   *
   * so finally
   * i/row ->    n - 1, 1
   * j/col -> i/row + 1, n - 1
   *
   */
  private static int tabulation(int[] nums){
    int n = nums.length;
    int[][] dp = new int[n][n];
    for(int row = 0; row < n ; row++){
      dp[row][row] = 0;
    }

    for(int row = n - 1; row >= 1 ; row--){
      for(int col = row + 1; col <= n - 1; col++){
        int minValue = (int) 1e9;
        for (int start = row; start <= col - 1; start++) {
          int left = dp[row][start];
          int right = dp[start + 1][col];
          int totalCurr = nums[row - 1] * nums[start] * nums[col];
          minValue = Math.min(minValue, totalCurr + left + right);
        }

        dp[row][col] = minValue;
      }
    }

    return dp[1][n - 1];
  }
}
