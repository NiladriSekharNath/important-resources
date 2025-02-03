package com.adidas.dsa.striversde.dynamicprogramming.stocks;

import java.util.Arrays;

/**
 * Problem statement
 You have been given an array 'PRICES' consisting of 'N' integers where PRICES[i] denotes the price of a given stock on
 the i-th day. You are also given an integer 'K' denoting the number of possible transactions you can make.

 Your task is to find the maximum profit in at most K transactions. A valid transaction involves buying a stock and then selling it.

 Note
 You can’t engage in multiple transactions simultaneously, i.e. you must sell the stock before rebuying it.
 For Example
 Input: N = 6 , PRICES = [3, 2, 6, 5, 0, 3] and K = 2.
 Output: 7

 Explanation : The optimal way to get maximum profit is to buy the stock on day 2(price = 2)
 and sell it on day 3(price = 6) and rebuy it on day 5(price = 0) and sell it on day 6(price = 3).
 The maximum profit will be (6 - 2) + (3 - 0) = 7.
 Detailed explanation ( Input/output format, Notes, Images )
 Constraints
 1 <= T <= 100
 1 <= N <= 5000
 0 <= K <= N/2
 0 <= ARR[i] <=10^5

 Time Limit : 1 sec
 Sample Input 1
 2
 5 2
 8 5 1 3 10
 4 2
 10 8 6 2
 Sample Output 1
 9
 0
 Explanation for Sample 1
 Test Case 1: In this case, we can make a maximum of 2 transactions. The optimal way to get maximum profit
 is to make only 1 transaction, i.e., buy the stock on day 3 (price = 1) and sell it on day 5(price = 10).
 The profit will be 10 - 1 = 9.

 Test Case 2: In the second test case, we can make a maximum of 2 transactions.
 The optimal way to get maximum profit is to make 0 transactions because the price of stock
 is continuously decreasing and we will have a loss if we buy and sell the stock.
 Sample Input 2
 2
 4 0
 2 6 5 2
 4 2
 1 2 3 5
 Sample Output 2
 0
 4
 *
 * Same logic as all the question we are allowed to buy only when we are not holding a stock otherwise we can only sell
 * if we are holding a stock
 *
 * everything same as per the question BestTimeToBuyAndSellStockIII however the only difference to this is we are allowed
 * upto k transactions
 *
 * so instead of k = 0 return 0, initially calling from 2, we change that to k = 0 return 0 calling the function k
 *
 *
 */
public class BestTimeToBuyAndSellStockIV {
  public static int maximumProfit(int[] prices, int n, int k)

  {

    int[][][] dp = new int[n + 1][2][k + 1];
    for(int[][] grid: dp){
      for(int[] row : grid){
        Arrays.fill(row, -1);
      }
    }

    return helper(0, 1, k, n, prices, dp);
  }

  private static int helper(int cI, int canBuy, int k, int n, int[] prices, int[][][] dp){
    if(cI >= n || k == 0) return 0;

    if(dp[cI][canBuy][k] != -1) return dp[cI][canBuy][k];
    int maxProfit = -(int)1e9;
    if(canBuy == 1){
      maxProfit = Math.max( -prices[cI] + helper(cI + 1, 0, k, n, prices, dp),
          helper(cI + 1, 1, k, n, prices, dp));
    }
    else
      maxProfit = Math.max(prices[cI] + helper(cI + 1, 1, k - 1, n, prices, dp),
          helper(cI + 1, 0, k, n, prices, dp));
    return dp[cI][canBuy][k] = maxProfit;
  }

  private static int tabulation(int[] prices, int k){
    int n = prices.length;

    int[][][] dp = new int[n + 1][2][k + 1];
    for(int buy = 0; buy <= 1; buy++){
      for(int trans = 0; trans <= k; trans++){
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
        for(int trans = 1; trans <= k; trans++){
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

    return dp[0][1][k];
  }



  public static void main(String[] args){
    BestTimeToBuyAndSellStockIV.tabulation(new int[]{3, 2, 6, 5, 0, 3}, 2);
  }
}
