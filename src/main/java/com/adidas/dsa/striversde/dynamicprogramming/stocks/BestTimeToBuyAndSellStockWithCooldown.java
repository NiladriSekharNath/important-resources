package com.adidas.dsa.striversde.dynamicprogramming.stocks;

import java.util.Arrays;

/**
 * You are given a list of stock prices of size 'n' called ‘prices’, where ‘prices[i]’ represents the price on ‘i’th day.
 *
 *
 *
 * Your task is to calculate the maximum profit you can earn by trading stocks if you can only hold one stock at a time.
 *
 *
 *
 * After you sell your stock on the ‘i’th day, you can only buy another stock on ‘i + 2’ th day or later.
 *
 *
 *
 * Example:
 * Input: 'prices' = [4, 9, 0, 4, 10]
 *
 * Output: 11
 *
 * Explanation:
 * You are given prices = [4, 9, 0, 4, 10]. To get maximum profits you will have to buy on day 0 and sell
 * on day 1 to make a profit of 5, and then you have to buy on day 3  and sell on day 4 to make the total profit of 11.
 * Hence the maximum profit is 11.
 *
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * 4
 * 1 2 3 4
 *
 *
 * Expected Answer:
 * 3
 *
 *
 * Output on console:
 * 3
 *
 *
 * Explanation:
 * For this test case, prices = [1, 2, 3, 4]. To get the maximum profit you have to buy the stock on day 0 and sell
 * on day 3 to get the maximum profit of 4. Hence the answer is 4.
 *
 *
 * Sample Input 2:
 * 3
 * 5 4 3
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
 * Expected Time Complexity:
 * Try to solve this in O(n).
 *
 *
 * Constraints:
 * 1 <= n <= 10^5
 * 1 <= prices[i] <= 10^6
 *
 * Time Limit: 1 sec
 *
 * so same condition as the BestTimeToBuyAndSellStockII question we can perform as many transactions we want however there
 * is a catch we can't buy the next day after we sold, we can buy again day after next
 *
 * so a condition helper(cI + 2, 1, n, prices, dp) is added(cI  + 2) in the sale condition
 * since we are not allowed to perform at that day
 *
 * again if we are at n - 1 and we do cI + 2 then we move outside the array so we added a >= n condition
 *
 *
 */
public class BestTimeToBuyAndSellStockWithCooldown {
  public static int stockProfit(int[] prices) {
    int n = prices.length ;
    int[][] dp = new int[n + 1][3];
    for(int[] row : dp)
      Arrays.fill(row, -1);
    return helper(0, 1, prices.length, prices, dp);
  }

  private static int helper(int cI, int canBuy, int n, int[] prices, int[][] dp){
    if(cI >= n) return 0;
    int maxProfit = 0;
    if(dp[cI][canBuy] != -1) return dp[cI][canBuy];
    if(canBuy == 1){
      maxProfit = Math.max(-prices[cI] + helper(cI + 1, 0, n, prices, dp),
          helper(cI + 1, 1, n, prices, dp));
    }
    else
      maxProfit = Math.max(prices[cI] + helper(cI + 2, 1, n, prices, dp),
          helper(cI + 1, 0, n, prices, dp));

    return dp[cI][canBuy] = maxProfit;
  }

  /**
   *
   * we are doing n + 2 same reason if we are at cI = n - 1 then we do cI + 2 -> n - 1 + 2 [n + 1]
   *
   * so we need array = n + 2, so we can hold n + 1 size value
   */

  private static int tabulation(int[] prices){
    int n = prices.length;
    int[][] dp = new int[n + 2][3];
    for(int cI = n - 1; cI >= 0 ; cI--){
      for(int canBuy = 0; canBuy <= 1; canBuy++){
        int maxProfit = 0;
        if(canBuy == 1){
          maxProfit = Math.max(-prices[cI] + dp[cI + 1][0],
              dp[cI + 1][1]);
        }
        else
          maxProfit = Math.max(prices[cI] + dp[cI + 2][1],
              dp[cI + 1][0]);

        dp[cI][canBuy] = maxProfit;
      }
    }

    return dp[0][1];
  }

  /**
   *
   * Here if we want to see howing swapping solution works we can follow this chat in ChatGPT
   *
   * https://chatgpt.com/c/679deaf0-80d4-8011-ae81-dcb737e0cdc1
   */

  private static int tabulationSpaceOptimized(int[] prices){
    int n = prices.length;
    int[] next2 = new int[3], next1 = new int[3];

    for(int cI = n - 1; cI >= 0 ; cI--){
      int[] curr = new int[3];
      for(int canBuy = 0; canBuy <= 1; canBuy++){
        int maxProfit = 0;
        if(canBuy == 1){
          maxProfit = Math.max(-prices[cI] + next1[0],
              next1[1]);
        }
        else
          maxProfit = Math.max(prices[cI] + next2[1],
              next1[0]);

        curr[canBuy] = maxProfit;
      }
      next2 = next1;
      next1 = curr;
    }

    return next1[1];
  }

  /**
   *
   * this solution is not recommended in interview however this one is shown as
   *
   * if(canBuy == 1){
   *           maxProfit = Math.max(-prices[cI] + next1[0],
   *               next1[1]);
   *         }
   *         else
   *           maxProfit = Math.max(prices[cI] + next2[1],
   *               next1[0]);
   * }
   *
   * here the canBuy = 1, then we are buying otherwise we are selling
   *
   * so we replace the inner loop by one condition
   *
   *
   *
   */
  private static int tabulationSpaceOptimizedOneLoop(int[] prices) {
    int n = prices.length;
    int[] next2 = new int[3], next1 = new int[3];

    for (int cI = n - 1; cI >= 0; cI--) {
      int[] curr = new int[3];


      curr[1] = Math.max(-prices[cI] + next1[0],
          next1[1]);

      curr[0] = Math.max(prices[cI] + next2[1],
          next1[0]);

      next2 = next1;
      next1 = curr;


    }

    return next1[1];
  }

}
