package com.adidas.dsa.striversde.dynamicprogramming;

import java.util.Arrays;

/**
 * Problem statement
 * You are given an array/list ‘ARR’ of ‘N’ positive integers and an integer ‘K’. Your task is to check if there
 * exists a subset in ‘ARR’ with a sum equal to ‘K’.
 *
 * Note: Return true if there exists a subset with sum equal to ‘K’. Otherwise, return false.
 *
 * For Example :
 * If ‘ARR’ is {1,2,3,4} and ‘K’ = 4, then there exists 2 subsets with sum = 4. These are {1,3} and {4}. Hence, return true.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 5
 * 1 <= N <= 10^3
 * 0 <= ARR[i] <= 10^9
 * 0 <= K <= 10^3
 *
 * Time Limit: 1 sec
 * Sample Input 1:
 * 2
 * 4 5
 * 4 3 2 1
 * 5 4
 * 2 5 1 6 7
 * Sample Output 1:
 * true
 * false
 * Explanation For Sample Input 1:
 * In example 1, ‘ARR’ is {4,3,2,1} and ‘K’ = 5. There exist 2 subsets with sum = 5. These are {4,1} and {3,2}. Hence, return true.
 * In example 2, ‘ARR’ is {2,5,1,6,7} and ‘K’ = 4. There are no subsets with sum = 4. Hence, return false.
 * Sample Input 2:
 * 2
 * 4 4
 * 6 1 2 1
 * 5 6
 * 1 7 2 9 10
 * Sample Output 2:
 * true
 * false
 * Explanation For Sample Input 2:
 * In example 1, ‘ARR’ is {6,1,2,1} and ‘K’ = 4. There exist 1 subset with sum = 4. That is {1,2,1}. Hence, return true.
 * In example 2, ‘ARR’ is {1,7,2,9,10} and ‘K’ = 6. There are no subsets with sum = 6. Hence, return false.
 *
 *
 * Hints:
 * 1. Can you find every possible subset of ‘ARR’ and check if its sum is equal to ‘K’?
 * 2. Can you use dynamic programming and use the previously calculated result to calculate the new result?
 * 3. Try to use a recursive approach followed by memoization by including both index and sum we can form.
 */
public class SubsetSumEqualToK {

  /**
   *
   * Here the subsetSumToK
   *
   * we are following the take and non take approach
   *
   * now this one is a recursive solution but not converted to dp, this is my solution however we are following one
   * standard solution that Striver is using just to complete all the solutions
   */
  public static boolean subsetSumToK(int n, int k, int arr[]){
    return helper(0, n, k, arr);
  }
  private static boolean helper(int cI, int n, int k, int[] nums){
    if(k == 0) return true;

    if(cI >= n) return false;
    boolean pick = false, nonPick = false;

    if(nums[cI] <= k){
      pick = helper(cI + 1, n, k - nums[cI], nums);
    }
    if(pick) return true;

    nonPick = helper(cI + 1, n, k, nums);

    if(nonPick)
      return true;

    return false;
  }

  public static boolean subsetSumToKStriver(int n, int k, int arr[]){
    int[][] dp = new int[n][k + 1];

    for(int[] eachRow : dp){
      Arrays.fill(eachRow, -1);
    }

    return helperStriver(n - 1, n, k, arr, dp) ;
  }
  private static boolean helperStriver(int cI, int n, int k, int[] nums, int[][] dp){
    if(k == 0) return true;


    /**
     at last step if the nums[cI] (element at cI == target) we return true;
     if the last number is equal to the reduced target at that step
     */
    if(cI == 0) return nums[cI] == k ;

    if(dp[cI][k] != -1) return dp[cI][k] == 1 ;

    boolean nonPick = helperStriver(cI - 1, n, k, nums, dp);
    boolean pick = false;

    if(nums[cI] <= k){
      pick = helperStriver(cI - 1, n, k - nums[cI], nums, dp);
    }

    dp[cI][k] = nonPick || pick ? 1 : 0;

    return nonPick || pick;


  }
  private static boolean tabulationApproach(int n, int k, int[] nums){
    boolean[][] dp = new boolean[n][k + 1];
    for(int row = 0 ; row < n; row++){
      dp[row][0] = true;
    }

    /**
     * [2, 3, 5] k = 5
     * Recursive Base Case (if (cI == 0)):
     *
     * At cI == 0, we check if nums[0] == k:
     * For k = 5, nums[0] == 2 doesn't match, so this base case returns false for k = 5.
     * Tabulation Initialization:
     *
     * For k = 5, we initialize the first row of the table:
     *
     * This means:
     * nums[0] = 2 contributes to subsets forming k = 2.
     * So, dp[0][2] = true.
     * Why is nums[0] <= k Important?
     *
     * When processing later rows, nums[0] = 2 could combine with nums[1] = 3 to form k = 5.
     * If you only allowed nums[0] == k in the initialization, you'd never set dp[0][2] = true,
     * and the algorithm wouldn't recognize that nums[0] can contribute to subsets that form k.
     *
     * If we think about at 0-index if the nums[0] == k that only can be true now the above case <= 0 we are writing because
     *
     *
     */

    if (nums[0] <= k) {
      dp[0][nums[0]] = true;
    }

    for(int row = 1; row < n ; row++){
      for(int col = 1 ; col <= k ; col++){
        boolean nonPick = dp[row - 1][col] ;
        boolean pick = false;
        if(nums[row] <= col){
          pick = dp[row - 1][col - nums[row]];

        }

        dp[row][col] = nonPick || pick;

      }
    }

    return dp[n - 1][k] ;
  }

  private static boolean tabulationApproachSpaceOptimized(int n, int k, int[] nums){
    boolean[] prev = new boolean[k + 1];

    prev[0] = true;



    if (nums[0] <= k) {
      prev[nums[0]] = true;
    }

    for(int row = 1; row < n ; row++){

      boolean[] curr = new boolean[k + 1];

      /**
       * since all 0 indexes where target = 0 we are setting as true
       */
      curr[0] = true;

      for(int col = 1 ; col <= k ; col++){
        boolean nonPick = prev[col] ;
        boolean pick = false;
        if(nums[row] <= col){
          pick = prev[col - nums[row]];

        }

        curr[col] = nonPick || pick;

      }
    }

    return prev[k] ;
  }
  public static void main(String[] args){
    SubsetSumEqualToK.subsetSumToKStriver(2, 2, new int[]{1, 2});
  }

}
