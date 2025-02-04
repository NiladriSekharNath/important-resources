package com.adidas.dsa.striversde.dynamicprogramming.subsequences;

import java.util.Arrays;

/**
 * You are given ‘n’ items with certain ‘profit’ and ‘weight’ and a knapsack with weight capacity ‘w’.
 *
 *
 *
 * You need to fill the knapsack with the items in such a way that you get the maximum profit.
 * You are allowed to take one item multiple times.
 *
 *
 *
 * Example:
 * Input:
 * 'n' = 3, 'w' = 10,
 * 'profit' = [5, 11, 13]
 * 'weight' = [2, 4, 6]
 *
 * Output: 27
 *
 * Explanation:
 * We can fill the knapsack as:
 *
 * 1 item of weight 6 and 1 item of weight 4.
 * 1 item of weight 6 and 2 items of weight 2.
 * 2 items of weight 4 and 1 item of weight 2.
 * 5 items of weight 2.
 *
 * The maximum profit will be from case 3 = 11 + 11 + 5 = 27. Therefore maximum profit = 27.
 *
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * 3 15
 * 7 2 4
 * 5 10 20
 *
 * Expected Answer:
 * 21
 *
 *
 * Output on console:
 * 21
 *
 *
 * Explanation of Sample Input 1
 * The given knapsack capacity is 15. We can fill the knapsack as [1, 1, 1] giving us profit 21 and as [1,2]
 * giving us profit 9. Thus maximum profit will be 21.
 *
 *
 * Sample Input 2
 * 2 3
 * 6 12
 * 4 17
 *
 *
 * Expected Answer:
 * 0
 *
 *
 * Output on console:
 * 0
 *
 *
 * Explanation of Sample Input 2:
 * We can clearly see that no item has weight less than knapsack capacity. Therefore we can not fill knapsack with any item.
 *
 *
 * Expected Time Complexity:
 * Try to solve this in O(n*w).
 *
 *
 * Constraints
 * 1 <= n <= 10^3
 * 1 <= w <= 10^3
 * 1 <= profit[i] , weight[i] <= 10^8
 *
 * Time Limit: 1 sec
 *
 * Full same logic and approach as the Knapsack01, however one item if we choose we can any number of times
 *
 * so we don't update the value of index when we choose a particular item having weight[idx] <= maxWeight for the pick case
 *
 * otherwise we don't choose we update the index
 */
public class KnapsackUnbounded {
  public static int unboundedKnapsack(int n, int w, int[] profits, int[] weights) {
    // int[][] dp = new int[n + 1][w + 1];
    // for(int[] row : dp){
    //   Arrays.fill(row, -1);
    // }
    // return helper(0, n, profit, weight, w, dp);

    return tabulationSpaceOptimized(n, w, weights, profits);
  }
  private static int helper(int cI, int n, int[] value, int[] weight, int maxWeight, int[][] dp){
    if(cI >= n) return 0;

    if(dp[cI][maxWeight] != -1) return dp[cI][maxWeight];

    int notPick = helper(cI + 1, n, value, weight, maxWeight, dp);


    int pick = 0;
    if(weight[cI] <= maxWeight){
      pick = value[cI] + helper(cI, n, value, weight, maxWeight - weight[cI], dp);
    }

    return dp[cI][maxWeight] = Math.max(pick, notPick);
  }

  private static int tabulationSpaceOptimized(int n, int maxWeight, int[] weights, int[] values){
    int[] next = new int[maxWeight + 1];
    for(int col = 0 ; col <= maxWeight ; col++){
      next[col] = 0;
    }

    for(int row = n - 1; row >= 0 ; row--){
      int[] curr = new int[maxWeight + 1];
      for(int col = 0 ; col <= maxWeight; col++){
        int notPick = next[col];


        int pick = 0;
        if(weights[row] <= col){
          pick = values[row] + curr[col - weights[row]];
        }

        curr[col] = Math.max(pick, notPick);
      }

      next = curr;
    }

    return next[maxWeight];
  }
}
