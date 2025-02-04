package com.adidas.dsa.striversde.dynamicprogramming.subsequences;

/***
 * Problem statement
 * You are given an array ‘ARR’ of ‘N’ integers and a target number, ‘TARGET’.
 * Your task is to build an expression out of an array by adding one of the symbols '+' and '-' before each
 * integer in an array, and then by concatenating all the integers, you want to achieve a target.
 * You have to return the number of ways the target can be achieved.
 *
 * For Example :
 * You are given the array ‘ARR’ = [1, 1, 1, 1, 1], ‘TARGET’ = 3. The number of ways this target can be achieved is:
 * 1. -1 + 1 + 1 + 1 + 1 = 3
 * 2. +1 - 1 + 1 + 1 + 1 = 3
 * 3. +1 + 1 - 1 + 1 + 1 = 3
 * 4. +1 + 1 + 1 - 1 + 1 = 3
 * 5. +1 + 1 + 1 + 1 - 1 = 3
 * These are the 5 ways to make. Hence the answer is 5.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 10
 * 1 <= N <= 25
 * -1000 <= TARGET <= 1000
 * 0 <= ARR[i] <= 1000
 *
 * Time Limit: 1 sec
 * Note :
 * You do not need to print anything. It has already been taken care of. Just implement the given function.
 * Sample input 1 :
 * 2
 * 5 3
 * 1 1 1 1 1
 * 4 3
 * 1 2 3 1
 * Sample Output 2 :
 * 5
 * 2
 * Explanation For Sample Input 1 :
 * For the first test case, ‘ARR’ = [1, 1, 1, 1, 1], ‘TARGET’ = 3. The number of ways this target can be achieved is:
 * 1. -1 + 1 + 1 + 1 + 1 = 3
 * 2. +1 - 1 + 1 + 1 + 1 = 3
 * 3. +1 + 1 - 1 + 1 + 1 = 3
 * 4. +1 + 1 + 1 - 1 + 1 = 3
 * 5. +1 + 1 + 1 + 1 - 1 = 3
 * These are the 5 ways to get the target. Hence the answer is 5.
 *
 * For the second test case, ‘ARR’ = [1, 2, 3, 1]. ‘TARGET’ = 3, The number of ways this target can be achieved is:
 * 1. +1 - 2 + 1 + 3 = 3
 * 2. -1 + 2 - 1 + 3 = 3
 * These are the 3 ways to get the target. Hence the answer is 2.
 * Sample Input 2 :
 * 2
 * 3 2
 * 1 2 3
 * 2 0
 * 1 1
 * Sample Output 2 :
 * 1
 * 2
 *
 * So if we see this approach properly when we are adding the + or - between the values of the array
 *
 * if we gather all values + and all values - and call them s1 for all the sum of positive values and after taking common -
 * we get essentially a sum of s2
 *
 * we reduce the problem to basically s1 - s2 = diff
 *
 * so this problem gets reduced to countPartitionsWithGivenDiff
 *
 */
public class TargetSum {
  public static int targetSum(int n, int target, int[] nums) {
    int totalSum = 0;
    for(int num : nums){
      totalSum += num;
    }

    int totalSumDiff = totalSum - target;

    return totalSumDiff % 2 == 0 ? tabulationSpaceOptimized(n, totalSumDiff/2, nums) : 0 ;

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
}
