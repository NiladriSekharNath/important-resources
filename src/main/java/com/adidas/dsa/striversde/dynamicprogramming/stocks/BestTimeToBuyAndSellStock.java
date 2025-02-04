package com.adidas.dsa.striversde.dynamicprogramming.stocks;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Problem statement
 * You are given an array/list 'prices' where the elements of the array represent the prices of the stock as they were
 * yesterday and indices of the array represent minutes. Your task is to find and return the maximum profit you can make
 * by buying and selling the stock. You can buy and sell the stock only once.
 *
 * Note:
 *
 * You can’t sell without buying first.
 * For Example:
 * For the given array [ 2, 100, 150, 120],
 * The maximum profit can be achieved by buying the stock at minute 0 when its price is Rs. 2 and selling it at minute 2
 * when its price is Rs. 150.
 * So, the output will be 148.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 2 <= N <= 10^4
 * 1 <= ARR[i] <= 10^9
 *
 * Time Limit: 1 sec.
 * Sample Input 1:
 * 2
 * 4
 * 1 2 3 4
 * 4
 * 2 2 2 2
 * Sample Output 1:
 * 3
 * 0
 * Explanation For Sample Output 1:
 * For the first test case, it’s optimal to buy the stock at minute 0 and sell it at minute 3 to get a maximum profit of 3.
 *
 * For the second test case, the maximum profit will be 0 for all possible ways of buying and selling stock.
 * Sample Input 2:
 * 2
 * 6
 * 17 20 11 9 12 6
 * 4
 * 98 101 66 72
 * Sample Output 2:
 * 3
 * 6
 */
public class BestTimeToBuyAndSellStock {
  public static int maximumProfit(ArrayList<Integer> prices){
    int n = prices.size();
    int[][] dp = new int[n + 1][n + 1];
    for(int[] row : dp)
      Arrays.fill(row, -1);
    return helper(0, -1, n, prices, dp);
  }

  /**
   *
   * this code cannot be memoized/tabulated as 'prev' we are trying to store with -1
   * what we are now doing is applying the index shifting by 1 strategy
   *
   * -1 -> 0
   *  0 -> 1
   *
   *
   *  here the way this is written in dp way is we have to buy first and then sell
   *
   *  prev -> represented to show where the stock is bought
   *  so if we buy
   *   we have a choice we can buy at currentIndex or not buy
   *
   *   if we buy we mark previous as the currentIndex for the sale
   *
   *   maxProfit = Math.max(helper(cI + 1, prev, n, prices, dp), helper(cI + 1, cI, n, prices,  dp));
   *
   *
   *  for sale it can be only possible only when stock is bought if stock is not bought we can't sale
   *  however in sale we also have a choice to sale at that particular index or sale at other index
   *
   *  Math.max(prices.get(cI) - prices.get(prev), helper(cI + 1, prev, n, prices,  dp));
   *
   *
   *
   */
  private static int helper(int cI, int prev, int n, ArrayList<Integer> prices, int[][] dp){
    if(cI >= n) return 0;

    int maxProfit = 0;
    if(prev != -1){
      maxProfit = Math.max(prices.get(cI) - prices.get(prev), helper(cI + 1, prev, n, prices,  dp));
    }
    else
      maxProfit = Math.max(helper(cI + 1, prev, n, prices, dp), helper(cI + 1, cI, n, prices,  dp));
    return maxProfit;
  }

  /**
   *
   * inorder to call this function we would have to make this adjustment
   *
   *     int n = prices.size();
   *     int[][] dp = new int[n + 2][n + 2];
   *     for(int[] row : dp)
   *       Arrays.fill(row, -1);
   *     return helper(1, 0, n, prices, dp);
   */

  private static int helper2RightShift(int cI, int prev, int n, ArrayList<Integer> prices, int[][] dp){
    if(cI >= n + 1) return 0;
    if(dp[cI][prev] != -1) return dp[cI][prev];
    int maxProfit = 0;
    if(prev != 0){
      maxProfit = Math.max(prices.get(cI - 1) - prices.get(prev - 1), helper2RightShift(cI + 1, prev, n, prices,  dp));
    }
    else
      maxProfit = Math.max(helper2RightShift(cI + 1, prev, n, prices, dp), helper2RightShift(cI + 1, cI, n, prices,  dp));
    return dp[cI][prev] = maxProfit;
  }

  /**
   *
   * though this is dp this is however not the optimal solution
   */
  private static int tabulation(ArrayList<Integer> prices){
    int n = prices.size();
    int[][] dp = new int[n + 2][n + 2];
    /**
     * ignoring the base case as cI >= n + 1 = 0 and our dp table is by default zero
     *
     * topdown
     * index, cI from 1, 2, ... n
     *        prev moving from 0,... n
     *
     *        n + 1 but it does not make sense for prev to be greater than cI so
     *              we can adjust to less than cI
     *        adjusted prev -> 0,..., cI - 1,
     *
     *        since row = n  --> 1
     *        col row - 1 -> 0     (since prev < cI so prev will end till cI - 1)
     *
     */

    for(int row = n  ; row >= 1 ; row--){
      for(int col = row - 1 ; col >= 0; col--){
        int maxProfit = -(int)1e9;
        if(col != 0){
          maxProfit = Math.max(prices.get(row - 1) - prices.get(col - 1), dp[row + 1][col]);
        }
        else
          maxProfit = Math.max(dp[row + 1][col], dp[row + 1][row]);
        dp[row][col] = maxProfit;
      }
    }

    return dp[1][0];

  }

  /**
   *
   * Note this is the actual solution this is however not dp
   *
   *
   *
   * what we are doing here we are tracking a minValue the minValue for this and keeping it as minimum at possible
   * and selling at all days and checking if the trade is better
   */
  public static int maximumProfitAcutualSolution(ArrayList<Integer> prices){
    int minPrice = prices.get(0), maxProfit = 0 ;

    for(int i = 1; i < prices.size() ; i++){
      int profit = prices.get(i) - minPrice;
      maxProfit = Math.max(maxProfit, profit);
      minPrice = Math.min(minPrice, prices.get(i));
    }

    return maxProfit;
  }
  public static void main(String[] args){
    BestTimeToBuyAndSellStock.tabulation(new ArrayList<>(Arrays.asList(1000, 500, 2, 100, 150)));
  }
}
