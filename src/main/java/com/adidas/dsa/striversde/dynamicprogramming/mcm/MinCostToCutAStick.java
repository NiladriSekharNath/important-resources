package com.adidas.dsa.striversde.dynamicprogramming.mcm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/***
 * Problem statement
 * You are given chocolate of ‘N’ length. The chocolate is labeled from 0 to ‘N’. You are also given an array ‘CUTS’
 * of size ‘C’, denoting the positions at which you can do a cut. The order of cuts can be changed.
 * The cost of one cut is the length of the chocolate to be cut. Therefore, the total cost is the sum of all the cuts.
 * Print the minimum cost to cut the chocolate.
 *
 * Note:
 *
 * All the integers in the ‘CUTS’ array are distinct.
 * For example:
 * Let ‘N’ be: 4
 * Let the ‘CUTS’ array be: [1, 3].
 *
 * Let the order of doing the cut be [1, 3].
 * The first cut of 1 on length 4 results in a cost of 4, and chocolate is split into two parts of the length of 1 and 3.
 * The second cut of 3 on length 3 results in a cost of 3, and chocolate is split into two parts again of the length of 2
 * and 1. So the total cost is 7.
 *
 * The cost will remain the same if we change the order of cutting. So the result is 7.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 10
 * 2 <= N <= 10^5
 * 1 <= C <= 10^3
 * 1 <= CUTS[i] <= N - 1
 *
 * Time Limit: 1 sec
 * Sample Input 1 :
 * 2
 * 4 2
 * 1 3
 * 5 3
 * 1 3 4
 * Sample Output 1 :
 * 7
 * 10
 * Explanation For Sample Output 1 :
 * For test case 1:
 * Let the order of doing the cut be [1, 3].
 * The first cut of 1 on length 4 results in a cost of 4, and chocolate is split into two parts of the length of 1 and 3.
 * The second cut of 3 on length 3 results in a cost of 3, and chocolate is split into two parts again of the length of 2 and 1. So the total cost is 7.
 *
 * The cost will remain the same if we change the order of cutting. So the result is 7.
 *
 * For test case 2:
 * Let the order of doing the cut be [1, 3, 4].
 * The first cut of 1 on length 5 results in a cost of 5, and chocolate is split into two parts of the length of 1 and 4.
 * The second cut of 3 on length 4 results in a cost of 4, and chocolate is split into two parts again of the length of
 * 2 and 2. The total cost is 9.
 * The third cut of 4 on length 2 results in a cost of 2, and chocolate is split into two parts again of the length of
 * 1 and 1. The total cost is 11.
 *
 * The minimum cost will be 10 when the order of doing cuts will be: [3, 1, 4].
 * Sample Input 2 :
 * 2
 * 10 4
 * 1 3 4 7
 * 10 2
 * 1 3
 * Sample Output 2 :
 * 23
 * 13
 *
 *
 * so let's say we are given this cuts in the stick in this order
 *
 * [1, 3, 4] stick length = 5
 *
 * one starting point for this question is we can get the cuts in any order
 *
 * now
 *
 * let's imagine stick like this
 *   ___________________________
 *  |    |    |     |    |     |
 *  ----------------------------
 *  0    1    2     3    4     5
 *
 *  let's say we cut the stick at 1
 *
 *  we get
 *
 *
 *   _____     _______ ________________
 *  |    |    |     |    |     |      |      cost for cutting this stick =  5
 *  -----     ------------------------
 *  0   1     1     2     3    4     5
 *
 *  Here only catch is we need to sort the cuts because
 *
 *  let's say we get the cuts like 1, 4, 3
 *
 *  after cutting 4 there is no way we can cut at 3 so the idea for this question is we need to cut in a sorted way to avoid dependency
 *  so to do that also when we cut the stick the original cost for the stick is the current length of the stick
 *  in the above diagram it is 5
 *
 *  so inorder to start with the question we need to do this approach
 *
 *  given to us 1, 4, 3 and cost of the stick is 5
 *
 *  so we add 0, 5 making it 0, 1, 4, 3, 5
 *  then sorting it to make it 0, 1, 3, 4, 5
 *                             0, 1, 2, 3, 4  <- index
 *
 *  now the point is we start with f(0, 4)
 *  k -> 1 - 3 [low + 1, high - 1]
 *
 *  so our sub parts are f(low, k) f(k, high) let's say k = 1, f(0, 1) , f(1, 4) as we can see from the above diagram
 *
 *  and cost to cut the current stick is nums[high] - nums[low]
 *
 *
 *  also considering the base case for
 *  (high - low ) <= 1 -> let's say f(0, 1) 0---1  this stick we can't cut so the cost to cut is zero
 *
 *
 *
 */
public class MinCostToCutAStick {
  public static int cost(int n, int c, int cuts[]) {

    List<Integer> allCuts = new ArrayList<>();
    int[][] dp = new int[n + 1][n + 1];
    allCuts.add(0);
    for(int cut: cuts)
      allCuts.add(cut);
    allCuts.add(n);
    Collections.sort(allCuts);

    for(int[] row : dp)
      Arrays.fill(row, -1);

    //return helper(0, allCuts.size() - 1, allCuts, dp);

    return tabulation(allCuts);
  }
  private static int helper(int low, int high, List<Integer> allCuts, int[][] dp){
    if(high - low <= 1) return 0;
    if(dp[low][high] != -1) return dp[low][high];
    int minCuts = + (int) 1e9;
    for(int start = low + 1; start <= high - 1; start++){
      int left = helper(low, start, allCuts, dp);
      int right = helper(start, high, allCuts, dp);
      int currCost = allCuts.get(high) - allCuts.get(low);
      minCuts = Math.min(minCuts, left + right + currCost);
    }
    return dp[low][high] = minCuts;
  }

  /**
   * top down f(i, j)
   * i -> 0, n - 1 so bottoms up n - 1 -> 0
   * j -> n - 1, 0 so bottoms up 0 -> n - 1
   *
   *
   */

  private static int tabulation(List<Integer> allCuts){
    int n = allCuts.size();
    int[][] dp = new int[n + 1][n + 1];

    /**
     * ignoring the base case as it is zero
     *
     */

    for(int row = n - 1; row >= 0 ; row--){
      for(int col = 0; col <= n - 1; col++){
        /**
         * we wrote the condition of the base case here also apart from initializating though we did not initialize
         * but we wrote this here because ( high - low <= 1 we return 0) ;
         * col can be anything x - (x - 1) for any x it is zero
         */
        if(col - row <= 1) continue;
        int minCuts = + (int) 1e9;
        for(int start = row + 1; start <= col - 1; start++){
          int left = dp[row][start];
          int right = dp[start][col];
          int currCost = allCuts.get(col) - allCuts.get(row);
          minCuts = Math.min(minCuts, left + right + currCost);
        }
        dp[row][col] = minCuts;
      }
    }

    return dp[0][n - 1];
  }

  /**
   *
   * strivers approach is similar we are cutting
   * 0, 1, 2, 3, 4
   * 0, 1, 3, 4, 5
   *
   * in my case I started with 0, 4 striver started with 1, 3 index (actually from 1, 3, 4 values )
   *
   * so for that case
   *
   * in the cost f(1, 3)
   *
   * since we started from (1, 3) k also ranges from (low, high)
   * now the idea for the cost here is nums[high + 1] - nums[low - 1]
   * obviously because nums[3 + 1] - nums[1 - 1] = 5 - 0
   *
   * now let's we cut k = 2
   *
   * we need to cut again or solve subproblems like f(low, start - 1) -> f(1, 1)
   *                                                f(start + 1, high) -> f(3, 3)
   *
   *
   * so to visualize this let's say after we cut k = 2 + at this level  cost = 5
   *
   * f(1, 1) + f(3, 3) + 5
   *
   * 0--1--2--3--4--5 we try for (1, 4)
   *
   * 0--1--2--3   3--4--5
   *   part1        part2
   *
   * so here for the part1, we have length 3 - 0
   * part2 we have length 5 - 3
   *
   *  nums[high + 1] - nums[low - 1]
   *
   *
   *
   */

  public static int costStrivers(int n, int c, int cuts[]) {

    List<Integer> allCuts = new ArrayList<>();
    allCuts.add(0);
    for(int cut: cuts)
      allCuts.add(cut);
    allCuts.add(n);
    Collections.sort(allCuts);
    return helperStrivers(1, c, allCuts);
  }
  private static int helperStrivers(int low, int high, List<Integer> allCuts){
    if(low > high ) return 0;
    int minCuts = + (int) 1e9;
    for(int start = low ; start <= high ; start++){
      int left = helperStrivers(low, start - 1, allCuts);
      int right = helperStrivers(start + 1, high, allCuts);
      int currCost = allCuts.get(high + 1) - allCuts.get(low - 1);
      minCuts = Math.min(minCuts, left + right + currCost);
    }
    return minCuts;
  }

  public static void main(String[] args){
    MinCostToCutAStick.cost(5, 3, new int[]{1, 3, 4});
  }

}
