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
 *                                                               |
 * here we are going till j because k + 1, for k = 3             |
 * would be empty                                                |
 *                                                               |
 *                                                               |
 *                                                               |
 * k = 1, f(i, k) = f(1, 1), f(k + 1, j) = f(2, 4) -> A(BCD)     |
 * k = 2, f(i, k) = f(1, 2), f(k + 1, j) = f(3, 4) -> (AB)(CD)   |
 * k = 3, f(i, k) = f(1, 3), f(k + 1, j) = f(4, 4) -> (ABC)D     |
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
    int minValue = (int) 1e9;
    if (dp[low][high] != -1) return dp[low][high];
    for (int start = low; start <= high - 1; start++) {
      int left = helper(low, start, nums, dp);
      int right = helper(start + 1, high, nums, dp);
      int totalCurr = nums[low - 1] * nums[start] * nums[high];
      minValue = Math.min(minValue, totalCurr + left + right);
    }

    return dp[low][high] = minValue;
  }


}
