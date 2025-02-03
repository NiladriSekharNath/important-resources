package com.adidas.dsa.striversde.dynamicprogramming.subsequences;

/**
 * Problem statement
 * You are given an infinite supply of coins of each of denominations D = {D0, D1, D2, D3, ...... Dn-1}.
 * You need to figure out the total number of ways W, in which you can make a change for value V
 * using coins of denominations from D. Print 0, if a change isn't possible.
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 3
 * 1 2 3
 * 4
 * Sample Output 1:
 * 4
 * Explanation for Sample Output 1:
 * We can make a change for the value V = 4 in four ways.
 * 1. (1,1,1,1),
 * 2. (1,1, 2), [One thing to note here is, (1, 1, 2) is same as that of (2, 1, 1) and (1, 2, 1)]
 * 3. (1, 3), and
 * 4. (2, 2)
 * Sample Input 2 :
 * 3
 * 5 3 2
 * 1
 * Sample Output 2:
 * 0
 *
 * same approach and logic as MinimumCoins question here just modification of base case where we see
 * that the denominations if we are at index = 0 when we find a value same logic if the value % nums[0]
 * is divisible then we return count = 1;
 *
 * also we return count = 1, when value == 0
 *
 * rest all same
 * and obviously we take the conditions for pick = 0
 */
public class CountWaysToMakeChange {

  public static long countWaysToMakeChange(int denominations[], int value){
    int n = denominations.length;
    // long[][] dp = new long[n][value + 1];

    // for(long[] row : dp)
    // 	Arrays.fill(row, -1);
    //return helper(n - 1, denominations, value, dp);

    return tabulationSpaceOptimized(denominations, n, value);
  }

  private static long helper(int cI, int[] nums, int value, long[][] dp){
    if(value == 0) return 1;

    if(cI == 0)
      return value % nums[cI] == 0 ?  1 : 0 ;

    if(dp[cI][value] != -1) return dp[cI][value] ;

    long notPick = helper(cI - 1, nums, value,  dp);
    long pick = 0;
    if(nums[cI] <= value){
      pick = helper(cI, nums, value - nums[cI], dp);

    }

    return dp[cI][value] = (pick + notPick);
  }


  private static long tabulationSpaceOptimized(int[] nums, int n, int target){
    long[] prev = new long[target + 1];

    prev[0] = 1;

    for(int col = 0; col <= target ;col++){
      if(col % nums[0] == 0) prev[col] = 1;
      else prev[col] = 0;
    }

    for(int row = 1 ; row < n; row++){
      long curr[] = new long[target + 1];
      curr[0] = 1;
      for(int col = 0; col <= target; col++){
        long notPick = prev[col];
        long pick = 0;
        if(nums[row] <= col){
          pick = curr[col - nums[row]];
        }

        curr[col] = pick + notPick;
      }
      prev = curr;
    }

    return prev[target];

  }


}
