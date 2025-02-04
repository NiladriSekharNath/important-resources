package com.adidas.dsa.striversde.dynamicprogramming.subsequences;

/**
 * Problem statement
 * You are given an array of ‘N’ distinct integers and an integer ‘X’ representing the target sum.
 * You have to tell the minimum number of elements you have to take to reach the target sum ‘X’.
 *
 * Note:
 * You have an infinite number of elements of each type.
 * For example
 * If N=3 and X=7 and array elements are [1,2,3].
 * Way 1 - You can take 4 elements  [2, 2, 2, 1] as 2 + 2 + 2 + 1 = 7.
 * Way 2 - You can take 3 elements  [3, 3, 1] as 3 + 3 + 1 = 7.
 * Here, you can see in Way 2 we have used 3 coins to reach the target sum of 7.
 * Hence the output is 3.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 1 <= N <= 15
 * 1 <= nums[i] <= (2^31) - 1
 * 1 <= X <= 10000
 *
 * All the elements of the “nums” array will be unique.
 * Time limit: 1 sec
 * Sample Input 1 :
 * 2
 * 3 7
 * 1 2 3
 * 1 0
 * 1
 * Sample output 1 :
 *  3
 *  0
 * Explanation For Sample Output 1:
 * For the first test case,
 * Way 1 - You can take 4 elements  [2, 2, 2, 1] as 2 + 2 + 2 + 1 = 7.
 * Way 2 - You can take 3 elements  [3, 3, 1] as 3 + 3 + 1 = 7.
 * Here, you can see in Way 2 we have used 3 coins to reach the target sum of 7.
 * Hence the output is 3.
 * For the second test case
 * To reach X = 0, you don’t need to take any elements from the array.
 * The sum is already 0, so the total number of elements used is 0.
 * Sample Input 2 :
 * 2
 * 3 4
 * 12 1 3
 * 2 11
 * 2 1
 * Sample output 2 :
 * 2
 * 6
 *
 * this problem is almost similar to the knap sack however here we have infinite supply of coins here the point is we can
 * select the same coin as many number of times for our use
 *
 *
 */

public class MinimumCoins {
  public static int minimumElements(int nums[], int target) {
    int n = nums.length;

    // int[][] dp = new int[n][target + 1];
    // for(int[] row : dp){
    //   Arrays.fill(row, -1);
    // }
    // int answer = helper(n - 1,nums , target, dp);
    //int answer = tabulationApproach(nums, n, target);
    int answer = tabulationSpaceOptimized(nums, n, target);
    if(answer >= (int)1e9)
      return -1;
    return answer;
  }

  private static int helper(int cI, int[] nums, int target, int[][] dp){

    /**
     * understanding the base we moving from n - 1, n - 2, ..., 0
     *
     * while standing at the cI = 0, target can be anything that will be used in tabulation
     *
     * however the point when we are at currentIndex = 0 ;
     *
     * let's say
     * case1: nums: [6], target = 7
     * we can see something here no matter what we do we can't get the target so we return some inf
     *
     * case2: nums: [6], target = 6
     *
     * we can see something here we can take 1 value
     *
     * case3: nums: [6], target = 18
     *
     * we can see that we could take 18/6 = 3 times
     *
     * so we stand to check at zero if we are able to take that if yes the target/nums[0] otherwise return Inf
     *
     */
    if(cI == 0){
      if(target % nums[cI] == 0) return target/nums[cI];
      else return (int)1e9;
    }

    if(dp[cI][target] != -1) return dp[cI][target];

    int notPick = helper(cI - 1, nums, target, dp);
    int pick = (int)1e9;
    if(nums[cI] <= target){
      pick = 1 + helper(cI, nums, target - nums[cI], dp);
    }

    return dp[cI][target] = Math.min(notPick, pick);
  }

  private static int tabulationApproach(int[]nums, int n, int target){
    int[][] dp = new int[n][target + 1];

    /**
     * since at zero target can be anything we did this
     */

    for(int col = 0; col <= target ;col++){
      if(col % nums[0] == 0) dp[0][col] = col/nums[0];
      else dp[0][col] = (int)1e9;
    }

    for(int row = 1 ; row < n; row++){
      for(int col = 0; col <= target; col++){
        int notPick = dp[row - 1][col];
        int pick = (int)1e9;
        if(nums[row] <= col){
          pick = 1 + dp[row][col - nums[row]];
        }

        dp[row][col] = Math.min(notPick, pick);
      }
    }

    return dp[n-1][target];
  }

  private static int tabulationSpaceOptimized(int[] nums, int n, int target){
    int[] prev = new int[target + 1];

    for(int col = 0; col <= target ;col++){
      if(col % nums[0] == 0) prev[col] = col/nums[0];
      else prev[col] = (int)1e9;
    }

    for(int row = 1 ; row < n; row++){
      int curr[] = new int[target + 1];
      for(int col = 0; col <= target; col++){
        int notPick = prev[col];
        int pick = (int)1e9;
        if(nums[row] <= col){
          pick = 1 + curr[col - nums[row]];
        }

        curr[col] = Math.min(notPick, pick);
      }
      prev = curr;
    }

    return prev[target];
  }


  public static void main(String[] args){
    new MinimumCoins().minimumElements(new int[]{1, 2, 3}, 7);
  }
}
