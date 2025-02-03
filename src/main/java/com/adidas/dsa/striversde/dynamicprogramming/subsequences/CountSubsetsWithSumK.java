package com.adidas.dsa.striversde.dynamicprogramming.subsequences;

/**
 * You are given an array 'arr' of size 'n' containing positive integers and a target sum 'k'.
 *
 *
 *
 * Find the number of ways of selecting the elements from the array such that the sum of chosen elements is equal to the target 'k'.
 *
 *
 *
 * Since the number of ways can be very large, print it modulo 10 ^ 9 + 7.
 *
 *
 *
 * Example:
 * Input: 'arr' = [1, 1, 4, 5]
 *
 * Output: 3
 *
 * Explanation: The possible ways are:
 * [1, 4]
 * [1, 4]
 * [5]
 * Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 4 5
 * 1 4 4 5
 *
 *
 * Sample Output 1 :
 *  3
 *
 *
 * Explanation For Sample Output 1:
 * The possible ways are:
 * [1, 4]
 * [1, 4]
 * [5]
 * Hence the output will be 3. Please note that both 1 present in 'arr' are treated differently.
 *
 *
 * Sample Input 2 :
 * 3 2
 * 1 1 1
 *
 *
 * Sample Output 2 :
 * 3
 *
 *
 * Explanation For Sample Output 1:
 * There are three 1 present in the array. Answer is the number of ways to choose any two of them.
 *
 *
 * Sample Input 3 :
 * 3 40
 * 2 34 5
 *
 *
 * Sample Output 3 :
 * 0
 *
 *
 * Expected time complexity :
 * The expected time complexity is O('n' * 'k').
 *
 *
 * Constraints:
 * 1 <= 'n' <= 100
 * 0 <= 'arr[i]' <= 1000
 * 1 <= 'k' <= 1000
 *
 * Time limit: 1 sec
 */
public class CountSubsetsWithSumK {

  /**
   * we cannot go in the previous approach with SubsumEqualToK
   *
   * because in the first step where we written if (target/k == 0)  return 1
   *
   * because array can contain 0
   *
   * so if we see [0, 0, 1] target = 1
   *
   * there can be 1 way by the previous approach because after selecting the last value we get target = 0
   *
   * and we find return 1 however for this nums and target = 1
   *
   * we should return 4
   *
   * [0 1] first zero, [0, 1] secondZero, [0 0 1] both zeroes [1] (taking the 1)
   *
   * here so we have to continue till the very first or last index which ever way we start the recursion
   *
   * here I have used from 0 to the last case
   *
   *
   */
  public static int findWays(int nums[], int tar) {

    int n = nums.length;
    return tabulationApproach(n, tar, nums);
  }


  private static int helper(int cI, int n, int k, int[] nums, int[][] dp){

    int mod = (int)(1e9 + 7);
    if(cI >= n) {
      if(k == 0) return 1 ;
      return 0;
    }

    if(dp[cI][k] != -1) return dp[cI][k];

    int pick = 0, nonPick = 0;

    if(nums[cI] <= k){
      pick = helper(cI + 1, n, k - nums[cI], nums, dp) % mod;
    }


    nonPick = helper(cI + 1, n, k, nums, dp) % mod;

    return dp[cI][k] = (pick + nonPick) % mod;
  }

  private static int tabulationApproach(int n, int target, int[] nums){
    int[][] dp = new int[n + 1][target + 1];
    int mod = (int)(1e9 + 7);
    /**
     * also if we see here the dp is initialized to [n+1][target + 1]
     * it's because we are considering till
     *
     * if(cI >= n) {
     *       if(k == 0) return 1 ;
     *       return 0;
     *     }
     *
     * Why n + 1 Rows Are Needed:
     * Base Case Requires an Extra Row:
     *
     * When implementing a bottom-up DP approach, the dp[row][col] value for a given row depends on the next row (dp[row + 1][col]), since we calculate solutions by "working backwards" from the last row (n-1) to the first row (0).
     * For the last row (row = n - 1), its computation will rely on dp[n][col] — which corresponds to having "no elements left" (the base case).
     * Therefore, we add an extra row (row = n) to handle the base case where no elements remain. This ensures that the algorithm doesn't run into an "out of bounds" error when accessing dp[row + 1][...].
     * Interpretation of dp[n][col]:
     *
     * dp[n][col] represents the scenario where no elements are left to pick from.
     * According to the problem:
     * If col == 0 (target is 0), there is exactly 1 way to achieve this (pick no elements).
     * If col > 0 (non-zero target), there are 0 ways to achieve this because there are no elements to pick.
     * This logic is initialized explicitly as:
     *
     * for (int col = 1; col <= target; col++) {
     *     dp[n][col] = 0; // No way to form a positive target with no elements
     * }
     *
     *
     */

    /**
     * we have initialized this as we can see the base case
     *
     * if(cI >= n) {
     *       if(k == 0) return 1 ;
     *       return 0;
     *     }
     *
     *     so at dp[n][0] = 1
     *
     * cI >= n, dp[n] and k == 0 we marked 1
     *
     * Also after that we start the target from 0 instead of 1 in the SubsetSumEqualToK question the reason being
     * here if we see this
     *
     * target in the top down appraoch moves from
     *
     * target - nums[k] ... 0
     *
     * so in the bottoms up approach the target moves from  0 - target (reverse manner),
     * ideally for the previous question this should have been the case we should have taken that order for that question
     * as well (SubsetSumEqualToK) as well now,
     *
     * we might have to adjust if we are taking the next col or something in future but this is crucial we build from the base
     * to answer;
     *
     *
     *
     */

    dp[n][0] = 1;

    for(int row = n - 1; row >= 0 ; row--){
      for(int col = 0 ; col <= target ; col++){
        int pick = 0, nonPick = dp[row + 1][col] % mod;

        if(nums[row] <= col){
          pick = dp[row + 1][col - nums[row]] % mod;
        }

        dp[row][col] = (pick + nonPick) % mod;



      }
    }

    return dp[0][target];


  }

  private static int tabulationSpaceOptimized(int n, int target, int[] nums){
    int[] next = new int[target + 1];
    int mod = (int)(1e9 + 7);

    next[0] = 1;

    for(int row = n - 1; row >= 0 ; row--){

      int[] curr = new int[target + 1];

      for(int col = 0 ; col <= target ; col++){
        int pick = 0, nonPick = next[col] % mod;

        if(nums[row] <= col){
          pick = next[col - nums[row]] % mod;
        }

        curr[col] = (pick + nonPick) % mod;



      }
      next = curr;
    }

    return next[target];


  }

  public static void main(String[] args){
    System.out.println(CountSubsetsWithSumK.tabulationSpaceOptimized(5, 13, new int[]{12, 14, 3, 18, 2}));
  }
}
