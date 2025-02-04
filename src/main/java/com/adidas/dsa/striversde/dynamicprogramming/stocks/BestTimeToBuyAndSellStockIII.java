package com.adidas.dsa.striversde.dynamicprogramming.stocks;

import java.util.Arrays;

/**
 * You are Harshad Mehta’s friend. He told you the price of a particular stock for the next ‘n’ days.
 *
 *
 *
 * You are given an array ‘prices’ which such that ‘prices[i]’ denotes the price of the stock on the ith day.
 *
 *
 *
 * You don't want to do more than 2 transactions. Find the maximum profit that you can earn from these transactions.
 *
 *
 *
 * Note
 *
 * 1. Buying a stock and then selling it is called one transaction.
 *
 * 2. You are not allowed to do multiple transactions at the same time. This means you have to sell the stock before buying it again.
 * Example:
 * Input: ‘n’ = 7, ‘prices’ = [3, 3, 5, 0, 3, 1, 4].
 *
 * Output: 6
 *
 * Explanation:
 * The maximum profit can be earned by:
 * Transaction 1: Buying the stock on day 4 (price 0) and then selling it on day 5 (price 3).
 * Transaction 2: Buying the stock on day 6 (price 1) and then selling it on day 6 (price 4).
 * Total profit earned will be (3 - 0) + ( 4 - 1) = 6.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * 6
 * 1 3 1 2 4 8
 * Sample Output 1:
 * 9
 * Explanation Of Sample Input 1 :
 * The maximum profit can be earned by:
 * Transaction 1: Buying the stock on day 1 (price 1) and then selling it on day 2 (price 3).
 * Transaction 2: Buying the stock on day 3 (price 1) and then selling it on day 6 (price 8).
 * Total profit earned will be (3 - 1) + ( 8 - 1) = 9.
 * Sample Input 2:
 * 5
 * 5 4 3 2 1
 * Sample Output 2:
 * 0
 * Explanation of Sample Output 2:
 * It's better not to do any transactions as the stock prices are decreasing.
 * Expected time complexity:
 * The expected time complexity is O(n).
 * Constraints:
 * 1 <= ‘n’ <= 10^6
 * 0 <= ‘prices[i]’ <= 10^3
 *
 * Where ‘prices[i]’ is the stock price on ith day.
 *
 * Time Limit: 1 sec.
 *
 * BestTime to Buy and Sell Stock 3 we are given and told similar we can make max of 2 transactions to find the maxProfit
 * so approach is we kept a canBuy option since first we have to buy then we can sell otherwise we can't sell without buying and
 * while holding a stock we cannot buy another stock
 *
 * Once here we are doing transaction count will be incremented when there is sell transaction,
 * if(trans == 2) we return 0 which is the base case
 * rest all is same as per the BuySellStockII
 *
 *
 */
public class BestTimeToBuyAndSellStockIII {
  public static int maxProfit(int[] prices) {
    int n = prices.length;
    int[][][] dp = new int[n + 1][2][3];
    for(int[][] grid : dp){
      for(int[] row : grid)
        Arrays.fill(row, -1);
    }
    return helper(0, 1, 2, prices.length, prices, dp);
  }
  private static int helper(int cI, int canBuy, int trans, int n, int[] prices, int[][][] dp){
    if(cI >= n || trans == 0) return 0;
    int maxProfit = 0;

    if(dp[cI][canBuy][trans] != -1) return dp[cI][canBuy][trans];

    if(canBuy == 1){
      maxProfit = Math.max(-prices[cI] + helper(cI + 1, 0, trans, n, prices, dp),
          helper(cI + 1, 1, trans, n, prices, dp));
    }
    else
      maxProfit = Math.max(prices[cI] + helper(cI + 1, 1, trans - 1, n, prices, dp),
          helper(cI + 1, 0, trans, n, prices, dp));

    return dp[cI][canBuy][trans] = maxProfit;
  }

  private static int tabulation(int[] prices){
    int n = prices.length;

    int[][][] dp = new int[n + 1][2][3];
    for(int buy = 0; buy <= 1; buy++){
      for(int trans = 0; trans <= 2; trans++){
        dp[n][buy][trans] = 0;

      }
    }

    for(int price = 0 ; price <= n; price++){
      for(int buy = 0; buy <= 1; buy++){
        dp[price][buy][0] = 0;
      }
    }

    for(int price = n - 1; price >= 0; price--){
      for(int buy = 0; buy <= 1; buy++){
        for(int trans = 1; trans <= 2; trans++){
          int maxProfit = 0;
          if(buy == 1){
            maxProfit = Math.max(-prices[price] + dp[price + 1][0][trans],
                dp[price + 1][1][trans]);
          }
          else
            maxProfit = Math.max(prices[price] + dp[price + 1][1][trans - 1],
                dp[price + 1][0][trans]);

          dp[price][buy][trans] = maxProfit;
        }
      }
    }

    return dp[0][1][2];
  }

  /**
   *
   * @param prices
   * @return we are returning the maxProfit approach is little different here
   *
   * what we are doing here is instead of a canBuy option with changing 1 for canBuy and 0 for cannotBuy (sell )
   *
   * we are keeping a transaction count of the numbers
   *
   *  0, 1, 2, 3, 4, 5, 6 --> note these are not indexes
   * [3, 3, 5, 0, 3, 1, 4]
   *
   * here the idea is we are keeping a number on the transactions
   * if transactionNo = even 0, 2, 4, we can only buy
   * if transactionNo = odd 1, 3, 5 we can only sell
   *
   * so similarly the base condition we are keeping as cI == n if we want to buy or skip or sell or skip till we reach the
   * last transaction
   *
   * or if we decide to buy/sell we increment the transactionNumber by 1
   *
   * so if we reach at a position = 4 we return 0 (as transactions are completed)
   *
   * 0 -> buy -> 1 -> sell -> 2 -> buy -> 3 -> sell -> 4( 2 transactions (maxlimit = 2) reached so we return from here
   *
   * that's the idea
   *
   *
   *
   */
  public static int maxProfitApproachII(int[] prices) {
    int n = prices.length;
    int [][]dp = new int[n + 1][n + 1];
    for(int[] row : dp)
      Arrays.fill(row, -1);
    //return helper(0, 0, prices.length, prices, dp);
    //return tabulation(prices);

    return tabulationSpaceOptimizedII(prices);
  }
  private static int helperII(int cI, int transNo, int n, int[] prices, int[][] dp){
    if(cI == n || transNo == 4) return 0;
    int maxProfit = 0;
    if(dp[cI][transNo] != -1) return dp[cI][transNo];
    /**
     For even condition we are buying and odd condition we are selling
     */
    if(transNo % 2 == 0){
      maxProfit = Math.max( - prices[cI] + helperII(cI + 1, transNo + 1, n, prices, dp),
          helperII(cI + 1, transNo, n, prices, dp));
    }
    else
      maxProfit = Math.max( + prices[cI] + helperII(cI + 1, transNo + 1, n, prices, dp),
          helperII(cI + 1, transNo, n, prices, dp));

    return dp[cI][transNo] = maxProfit;
  }

  private static int tabulationII(int[] prices){
    int n = prices.length;
    int[][] dp = new int[n + 1][5];

    for(int cI = n - 1; cI >= 0; cI--){
      for(int transNo = 3; transNo >= 0; transNo--){
        int maxProfit = 0;

        /**
         For even condition we are buying and odd condition we are selling
         */
        if(transNo % 2 == 0){
          maxProfit = Math.max( - prices[cI] + dp[cI + 1][transNo + 1],
              dp[cI + 1][transNo]);
        }
        else
          maxProfit = Math.max( + prices[cI] + dp[cI + 1][transNo + 1],
              dp[cI + 1][transNo]);

        dp[cI][transNo] = maxProfit;
      }
    }

    return dp[0][0];
  }

  private static int tabulationSpaceOptimizedII(int[] prices){
    int n = prices.length;
    int[] next = new int[5];

    for(int cI = n - 1; cI >= 0; cI--){
      int[] curr = new int[5];
      for(int transNo = 3; transNo >= 0; transNo--){
        int maxProfit = 0;

        /**
         For even condition we are buying and odd condition we are selling
         */
        if(transNo % 2 == 0){
          maxProfit = Math.max( - prices[cI] + next[transNo + 1],
              next[transNo]);
        }
        else
          maxProfit = Math.max( + prices[cI] + next[transNo + 1],
              next[transNo]);

        curr[transNo] = maxProfit;
      }

      next = curr;
    }

    return next[0];
  }

  /**
   *
   * @param prices
   * @param k
   * @return
   *
   * approach 2 since we are given k and we are giving a transaction number
   * for when k = 2
   *
   * we said for 0, 1, 2, 3, 4 ( we stop at transaction number = 4)
   *
   * here we need to stop at transaction number = 2k
   *
   * now in the dp array while storing we need to store till 0, 1, ... 2k [so we need an array size [2*k + 1]
   *
   * since at since when if(transNo = 2k) we return 0
   *
   * so we need to start from 2k - 1, and since we need transNo + 1, so at 2k - 1 we need till 2k so we need to create a
   * dp array of 2k + 1
   */
  private static int tabulationSpaceOptimizedII(int[] prices, int k){
    int n = prices.length;
    int[] next = new int[2*k + 1];

    for(int cI = n - 1; cI >= 0; cI--){
      int[] curr = new int[2*k + 1];
      for(int transNo = 2*k - 1 ; transNo >= 0; transNo--){
        int maxProfit = 0;

        /**
         For even condition we are buying and odd condition we are selling
         */
        if(transNo % 2 == 0){
          maxProfit = Math.max( - prices[cI] + next[transNo + 1],
              next[transNo]);
        }
        else
          maxProfit = Math.max( + prices[cI] + next[transNo + 1],
              next[transNo]);

        curr[transNo] = maxProfit;
      }

      next = curr;
    }

    return next[0];
  }
  public static void main(String[] args){
    BestTimeToBuyAndSellStockIII.tabulation(new int[]{1, 3, 1, 2, 4, 8});
  }
}
