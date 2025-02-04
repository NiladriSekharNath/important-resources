package com.adidas.dsa.striversde.dynamicprogramming.stocks;

import java.util.Arrays;

/**
 * Problem statement
 * You have been given stock values/prices for N number of days. Every i-th day signifies the price of a stock on that day.
 * Your task is to find the maximum profit which you can make by buying and selling the stocks.
 *
 *  Note :
 * You may make as many transactions as you want but can not have more than one transaction at a time i.e,
 * if you have the stock, you need to sell it first, and then only you can buy it again.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= t <= 10^2
 * 0 <= N <= 10^5
 * Time Limit: 1 sec
 * Sample Input 1 :
 * 1
 * 7
 * 1 2 3 4 5 6 7
 * Sample Output 1 :
 * 6
 * Explanation :
 * We can make the maximum profit by buying the stock on the first day and selling it on the last day.
 * Sample Input 2 :
 * 1
 * 7
 * 7 6 5 4 3 2 1
 * Sample Output 2 :
 * 0
 * Explanation :
 * We can make the maximum profit by not buying the stock
 *
 * we can buy as many number of stocks however we have to buy a stock first inorder to sell the stock
 * and if we have a stock we have to sale the stock then only we can buy another stock
 */
public class BestTimeToBuyAndSellStockII {
  public static long getMaximumProfit(int n, long[] values) {
    return helper(0, -1, n, values);
  }

  /**
   *
   * solution self calling with indexes
   * helper(0, -1 ) holding the current and the previous index to where a stock is bought, prev stores thet last bought index
   *
   * if prev != -1 that means we can't buy we will have to sell
   *  sell has two choice either sell at that particular day then cI -> cI + 1, prev -> -1 (again)
   *  otherwise we can skip the sell for that day what we should do is cI -> cI + 1, prev -> prev, meaning we are storing
   *  the last previous
   *
   * if we are buying that when prev = -1
   * we can either buy a particular day or skip a particular day
   * buy in a particular day cI + 1, cI or skipping -> cI + 1, prev (not buying this day)
   *
   * and keeping the maxProfit
   *
   * ultimately at the base case which is the last day we would have no profit if there is even a stock holding by us
   * so we return 0
   *
   */
  private static long helper(int cI, int prev, int n, long[] values){
    if(cI >= n){
      return 0;
    }
    long maxProfit = 0;
    if(prev != -1){
      maxProfit =
          Math.max(values[cI] - values[prev] + helper(cI + 1, -1, n, values),
              helper(cI+1, prev, n, values));
    }
    else{
      maxProfit = Math.max(helper(cI + 1, prev, n, values), helper(cI + 1, cI, n, values));
    }

    return maxProfit;

  }

  /**
   *
   * Striver's solution is better here we are passing two parameters
   * dp[0][1] -> we are starting from 1, here 1 means we can buy a stock if a stock is bought then only we can sell and
   *             we are making it to 0, once we bought the stock
   *
   *
   */

  public static long getMaximumProfitStrivers(int n, long[] values) {
    long[][] dp = new long[n + 1][2];
    for(long[] row : dp)
      Arrays.fill(row, -1);
    return helperStrivers(0, 1, n, values, dp);
  }
  private static long helperStrivers(int cI, int canBuy, int n, long[] values, long[][] dp){
    if(cI >= n){
      return 0;
    }

    if(dp[cI][canBuy] != -1) return dp[cI][canBuy];
    /**
     1 means canBuy
     0 means can'tBuy
     */
    long maxProfit = 0;
    /**
     * buy options
     *
     *  we are at max(-negative (loss of money ) + helper(cI + 1, 0) or helper(cI + 1, 1))
     * buy means negative so add '-values[cI] and also once bought nextIndex = cI + 1, 0 ) 0 means we can't buy again
     * otherwise we choose to skip the index meaning 1 we are reserving the right to buy in the future
     */
    if(canBuy == 1){
      maxProfit = Math.max(- values[cI] + helperStrivers(cI + 1, 0, n, values, dp),
          helperStrivers(cI + 1, 1, n, values, dp));
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
      maxProfit = Math.max(+ values[cI] + helperStrivers(cI + 1, 1, n, values, dp),
          helperStrivers(cI + 1, 0, n, values, dp));

    return dp[cI][canBuy] = maxProfit;
  }

  private static long tabulationStriver(int n, long[] values){

    long[][] dp = new long[n + 1][2];
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
        long maxProfit = 0;
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
          maxProfit = Math.max( + values[row] +dp[row + 1][1], dp[row + 1][0]);

        dp[row][col] = maxProfit;
      }
    }
    return dp[0][1];
  }

}
