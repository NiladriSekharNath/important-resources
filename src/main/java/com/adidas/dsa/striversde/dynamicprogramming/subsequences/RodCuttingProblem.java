package com.adidas.dsa.striversde.dynamicprogramming.subsequences;

import java.util.Arrays;

/**
 * Problem statement
 * Given a rod of length ‘N’ units. The rod can be cut into different sizes and each size has a cost
 * associated with it. Determine the maximum cost obtained by cutting the rod and selling its pieces.
 *
 * Note:
 * 1. The sizes will range from 1 to ‘N’ and will be integers.
 *
 * 2. The sum of the pieces cut should be equal to ‘N’.
 *
 * 3. Consider 1-based indexing.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 50
 * 1 <= N <= 100
 * 1 <= A[i] <= 100
 *
 * Where ‘T’ is the total number of test cases, ‘N’ denotes the length of the rod, and A[i] is the cost of sub-length.
 *
 * Time limit: 1 sec.
 * Sample Input 1:
 * 2
 * 5
 * 2 5 7 8 10
 * 8
 * 3 5 8 9 10 17 17 20
 * Sample Output 1:
 * 12
 * 24
 * Explanation of sample input 1:
 * Test case 1:
 *
 * All possible partitions are:
 * 1,1,1,1,1           max_cost=(2+2+2+2+2)=10
 * 1,1,1,2             max_cost=(2+2+2+5)=11
 * 1,1,3               max_cost=(2+2+7)=11
 * 1,4                 max_cost=(2+8)=10
 * 5                   max_cost=(10)=10
 * 2,3                 max_cost=(5+7)=12
 * 1,2,2               max _cost=(1+5+5)=12
 *
 * Clearly, if we cut the rod into lengths 1,2,2, or 2,3, we get the maximum cost which is 12.
 *
 *
 * Test case 2:
 *
 * Possible partitions are:
 * 1,1,1,1,1,1,1,1         max_cost=(3+3+3+3+3+3+3+3)=24
 * 1,1,1,1,1,1,2           max_cost=(3+3+3+3+3+3+5)=23
 * 1,1,1,1,2,2             max_cost=(3+3+3+3+5+5)=22
 * and so on….
 *
 * If we cut the rod into 8 pieces of length 1, for each piece 3 adds up to the cost. Hence for 8 pieces, we get 8*3 = 24.
 * Sample Input 2:
 * 1
 * 6
 * 3 5 6 7 10 12
 * Sample Output 2:
 * 18
 *
 * Now if we see this question we are asked to cut rod lengths if we cut a length each value of length we cut we get a
 * profit for that length so initially what we did is
 *
 * given n = 5 (rod of length 5 ), price = [2, 5, 7, 8, 10]
 *
 * now what did here is made cuts like 1, 2, 3, 4, 5 [ if cut size = 5 then rod remaining = 5 - 5 = 0]
 *
 * so if we add that
 *
 * profit[i] = [0, 2, 5, 7, 8, 10]
 * weights[i] = [0, 1, 2, 3, 4, 5]
 *
 * we added one extra zero for 1-based indexing as we cannot cut 0 length rod
 *
 * Now seeing the problem here we can find that this is like a Unbounded Knapsack ->
 *
 * where weights are the weights(cuts) and profit = value
 *
 * why unbounded knapsack it is because we can cut same length unlimited number of times till the weight
 *
 *
 */
public class RodCuttingProblem {
  public static int cutRod(int price[], int n) {
    int[] profit = new int[n + 1], weights = new int[n + 1];
    for(int i = 1 ; i < n + 1; i++){
      profit[i] = price[i - 1];
      weights[i] = i;
    }


    int length = profit.length;

    int[][] dp = new int[length + 1][length + 1];

    for(int[] row : dp)
      Arrays.fill(row, -1);

    return helper(1, length, n, profit, weights, dp);
  }

  private static int helper(int cI, int n, int maxLength, int[] profit, int[] weights, int[][] dp){
    if(cI >= n) return 0;

    if(dp[cI][maxLength] != -1) return dp[cI][maxLength];

    int notPick = helper(cI + 1, n, maxLength, profit, weights, dp);

    int pick = -(int) 1e9;

    if(weights[cI] <= maxLength){
      pick = profit[cI] + helper(cI, n, maxLength - weights[cI], profit, weights, dp);
    }

    return dp[cI][maxLength] = Math.max(pick, notPick);
  }

  /**
   *
   * the above way works perfectly however we can make that better instead of making new arrays we can do this
   * if we see let's start from 0
   *
   * and then the lengthOfCut = 0 + 1;
   *
   * so recursion wise we can write this as for any index = cI,  cI + 1
   *
   * so from the totalLength n = we reduce LengthOfCut and follow the same unbounded knapsack pattern
   *
   *
   *
   */

  public static int cutRodApproachII(int price[], int n) {


    int[][] dp = new int[n + 1][n + 1];

    for(int[] row : dp)
      Arrays.fill(row, -1);

    return helperII(0, n, n, price, dp);
  }

  private static int helperII(int cI, int n, int maxLength, int[] profit, int[][] dp){
    if(cI >= n) return 0;

    if(dp[cI][maxLength] != -1) return dp[cI][maxLength];

    int currentLengthToCut = cI + 1;

    int notPick = helperII(cI + 1, n, maxLength, profit, dp);

    int pick = -(int) 1e9;

    if(currentLengthToCut <= maxLength){
      pick = profit[cI] + helperII(cI, n, maxLength - currentLengthToCut, profit, dp);
    }

    return dp[cI][maxLength] = Math.max(pick, notPick);
  }

  private static int tabulationSpaceOptimized(int[] price, int maxLength){
    int[] next = new int[maxLength + 1];

    for(int col = 0 ; col <= maxLength ; col++){
      next[col] = 0;
    }

    for(int row = maxLength - 1; row >= 0 ; row--){
      int[] curr = new int[maxLength + 1];
      for(int col = 0 ; col <= maxLength ; col++){

        int currentLengthToCut = row + 1;

        int notPick = next[col];

        int pick = -(int) 1e9;

        if(currentLengthToCut <= col){
          pick = price[row] + curr[col - currentLengthToCut];
        }

        curr[col] = Math.max(pick, notPick);
      }

      next = curr;
    }

    return next[maxLength];


  }


}
