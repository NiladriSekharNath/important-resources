package com.adidas.dsa.striversde.dynamicprogramming.stocks;

import java.util.Arrays;

public class BestTimeToBuyAndSellStockWithTransactionFee {

  public static int maximumProfit(int[] prices, int n, int fee) {
    int[][] dp = new int[n][2];
    for(int[] row : dp)
      Arrays.fill(row, -1);
    return helper(0, 1, n, fee, prices, dp);
  }

  private static int helper(int cI, int canBuy, int n, int fee, int[] prices, int[][] dp){
    if(cI == n) return 0;
    int maxProfit = 0;
    if(dp[cI][canBuy] != -1) return dp[cI][canBuy];
    if(canBuy == 1)
      maxProfit = Math.max(-prices[cI]  + helper(cI + 1, 0, n, fee, prices, dp),
          helper(cI + 1, 1, n, fee, prices, dp));
    else
      maxProfit = Math.max((+prices[cI] - fee) + helper(cI + 1, 1, n, fee, prices, dp),
          helper(cI + 1, 0, n, fee, prices, dp));

    return dp[cI][canBuy] = maxProfit;

  }

  private static int tabulationStriver(int n, int[] values, int fee){

    int[][] dp = new int[n + 1][2];
    /**
     * 0 -> 1 -> ... -> n in topDown
     * bottom up : n -> n - 1 ..-> 0
     *
     * base case was not required to be initialized
     */
    for(int col = 0; col <= 1; col++){
      dp[n][col] = 0;
    }

    for(int row = n - 1; row >= 0 ; row--){
      for(int col = 0; col <= 1; col++){
        int maxProfit = 0;
        /**
         * buy options
         *
         *  we are at max(-negative (loss of money ) + helper(cI + 1, 0) or helper(cI + 1, 1))
         * buy means negative so add '-values[cI] and also once bought nextIndex = cI + 1, 0 ) 0 means we can't buy again
         * otherwise we choose to skip the index meaning 1 we are reserving the right to buy in the future
         */
        if (col == 1) {
          maxProfit = Math.max( - values[row] + dp[row + 1][0], dp[row + 1][1]);
        }
        /**
         * selling options
         *
         * option 1 selling at that cI -> selling means profit so adding the value -> +value[cI] + helper(cI + 1, 1)
         *          giving the avability to buy a stock in the next day
         *
         * option 2 skipping the sale of the stock for the particular day -> cI + 1, 0
         */
        else
          maxProfit = Math.max( + values[row] - fee +dp[row + 1][1], dp[row + 1][0]);

        dp[row][col] = maxProfit;
      }
    }
    return dp[0][1];
  }

}
