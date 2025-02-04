package com.adidas.dsa.striversde.dynamicprogramming.subsequences;

/**
 * Problem statement
 * You are given an array 'arr' of size 'n' containing positive integers and a target sum 'k'.
 *
 *
 *
 * Find the number of ways of selecting the elements from the array such that the sum of chosen elements is equal to the target 'k'.
 *
 *
 *
 * Since the number of ways can be very large, print it modulo 10 ^ 9 + 7.
 *
 *
 *
 * Example:
 * Input: 'arr' = [1, 1, 4, 5]
 *
 * Output: 3
 *
 * Explanation: The possible ways are:
 * [1, 4]
 * [1, 4]
 * [5]
 * Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 4 5
 * 1 4 4 5
 *
 *
 * Sample Output 1 :
 *  3
 *
 *
 * Explanation For Sample Output 1:
 * The possible ways are:
 * [1, 4]
 * [1, 4]
 * [5]
 * Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.
 *
 *
 * Sample Input 2 :
 * 3 2
 * 1 1 1
 *
 *
 * Sample Output 2 :
 * 3
 *
 *
 * Explanation For Sample Output 1:
 * There are three 1 present in the array. Answer is the number of ways to choose any two of them.
 *
 *
 * Sample Input 3 :
 * 3 40
 * 2 34 5
 *
 *
 * Sample Output 3 :
 * 0
 *
 *
 * Expected time complexity :
 * The expected time complexity is O('n' * 'k').
 *
 *
 * Constraints:
 * 1 <= 'n' <= 100
 * 0 <= 'arr[i]' <= 1000
 * 1 <= 'k' <= 1000
 *
 * Time limit: 1 sec
 *
 * Standard 0-1 Knapsack pattern we are trying to pick and not pick,
 * pick only if the current weight is less the current Knapsack weight
 * and we are moving from left to right ;
 */
public class Knapsack01 {


  static int knapsack(int[] weight, int[] value, int n, int maxWeight) {

    /**

     int[][] dp = new int[n + 1][maxWeight + 1];
     for(int[] row : dp){
     Arrays.fill(row, -1);
     }
     return helper(0, weight, value, n, maxWeight, dp);

     return tabulationApproach(weight, value, n, maxWeight);*/

    return tabulationSpaceOptimized2(weight, value, n, maxWeight);

  }

  private static int helper(int cI, int[] weight, int[] value, int n, int maxWeight, int[][] dp) {
    if (cI >= n) return 0;
    if (dp[cI][maxWeight] != -1) return dp[cI][maxWeight];
    int maxValue = -(int) 1e9;
    int notPick = helper(cI + 1, weight, value, n, maxWeight, dp);

    int pick = 0;
    if (weight[cI] <= maxWeight) {
      pick = value[cI] + helper(cI + 1, weight, value, n, maxWeight - weight[cI], dp);
    }

    return dp[cI][maxWeight] = Math.max(pick, notPick);
  }

  private static int tabulationApproach(int[] weight, int[] value, int n, int maxWeight) {
    /**
     * since we moved till the last weight we initialized and extra value in the n
     */
    int[][] dp = new int[n + 1][maxWeight + 1];
    /**
     This could have not been done as for an array the default initialization is zero always

     */
    for (int col = 0; col <= maxWeight; col++) {
      dp[n][col] = 0;
    }

    /**
     * if you see in the row in Recursion sol moves from 0, 1, ... n therefore tabulation (bottoms up) move from n - 1, n - 2,...,0
     *
     * for the col target moves from tar, tar - someVal, ... 0, therefore tabulation(bottoms up) 0,1 ... target
     *
     *
     */

    for (int row = n - 1; row >= 0; row--) {
      for (int col = 0; col <= maxWeight; col++) {
        int maxValue = -(int) 1e9;
        int notPick = dp[row + 1][col];

        int pick = 0;
        if (weight[row] <= col) {
          pick = value[row] + dp[row + 1][col - weight[row]];
        }

        dp[row][col] = Math.max(pick, notPick);
      }
    }

    return dp[0][maxWeight];
  }

  private static int tabulationSpaceOptimized(int[] weight, int[] value, int n, int maxWeight) {
    int[] next = new int[maxWeight + 1];

    /**
     *  this is required to be done as the default array initialization is 0 in Java however
     *  just done here for practice
     */
    for(int col = 0; col <= maxWeight ; col++){
      next[col] = 0;
    }



    for (int row = n - 1; row >= 0; row--) {
      int[] curr = new int[maxWeight + 1];
      for (int col = 0; col <= maxWeight; col++) {
        int maxValue = -(int) 1e9;
        int notPick = next[col];

        int pick = 0;
        if (weight[row] <= col) {
          pick = value[row] + next[col - weight[row]];
        }

        curr[col] = Math.max(pick, notPick);
      }

      next = curr;
    }

    return next[maxWeight];
  }

  /**
   *
   * if we see this is more optimized solution if we see the spaceOptimized solution we are concerned with next[col - weight[row]]
   *
   * so we are moving from 0.. maxWeight in that solution we don't need some previous value (col - weight[row])
   *
   * [val1, val2,..valx...valn] if we stand at some valx we don't need the values to the right of x so if we do the movement
   * of val from right to left we don't even need a curr array we can keep updating in the next arr
   *
   * however we can't do that if we move from left because we need some value in the left so for us we can
   * move from maxWeight to 0 and fill in the next
   *
   * however this will not be told in the interview
   */

  private static int tabulationSpaceOptimized2(int[] weight, int[] value, int n, int maxWeight) {
    int[] next = new int[maxWeight + 1];

    next[0] = 0;

    for (int row = n - 1; row >= 0; row--) {

      for (int col = maxWeight; col >= 0; col--) {
        int maxValue = -(int) 1e9;
        int notPick = next[col];

        int pick = 0;
        if (weight[row] <= col) {
          pick = value[row] + next[col - weight[row]];
        }

        next[col] = Math.max(pick, notPick);
      }


    }

    return next[maxWeight];
  }
}
