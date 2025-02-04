package com.adidas.dsa.striversde.dynamicprogramming.mcm;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 1043. Partition Array for Maximum Sum
 *
 * Given an integer array arr, partition the array into (contiguous) subarrays of length at most k.
 * After partitioning, each subarray has their values changed to become the maximum value of that subarray.
 *
 * Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,15,7,9,2,5,10], k = 3
 * Output: 84
 * Explanation: arr becomes [15,15,15,9,10,10,10]
 * Example 2:
 *
 * Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * Output: 83
 * Example 3:
 *
 * Input: arr = [1], k = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 109
 * 1 <= k <= arr.length
 *
 * Solution : Same approach as the Previous PalindromicPartitioningII question
 *
 * given to us an array -> arr = [1,15,7,9,2,5,10], k = 3
 *
 * let's say we try out 2 of this partitions
 *
 *  [1, 15 | 7, 9, 2 | 5, 10],     [1, 15, 7 | 9 | 2, 5, 10]
 *
 *  [15, 15 | 9, 9, 9 | 10, 10],  [15, 15, 15 | 9 | 10, 10, 10]
 *
 *  30 + 27 + 20 ,                 45 + 9 + 30
 *
 *  77                                84
 *
 *  so our better answer = 84
 *
 *  again following the same logic
 *
 *  we are trying this way
 *
 *  f(1,15,7,9,2,5,10){
 *      1 | f(15,7,9,2,5,10)       -> calculate rest
 *      1, 15 | f(7,9,2,5,10)      -> calculate rest
 *      1, 15, 7 | f(9,2,5,10)     -> calculate rest
 *      1, 15, 7, 9, | f(2, 5, 10) -> this one is not allowed as we are allowed to take at most k elements
 *      ...
 *
 *  }
 *
 *  so basically we run a loop "current sum in the range" = (maxValue in the range * length of the range)
 *
 *  start -> [cI -> cI + k - 1], now obviously for the last range (n - 1) this can go out of bounds so we do
 *
 *  start -> [cI -> min(cI + k - 1, n - 1)]
 *
 *    0  1 2 3 4,5, 6
 *  f(1,15,7,9,2,5,10){ here k = 3, (till max index 2)
 *        1 | f(15,7,9,2,5,10)       -> calculate rest
 *        1, 15 | f(7,9,2,5,10)      -> calculate rest
 *        1, 15, 7 | f(9,2,5,10)     -> calculate rest
 *        1, 15, 7, 9, | f(2, 5, 10) -> this one is not allowed as we are allowed to take at most k elements
 *        ...
 *
 *    }
 *
 *    now for this question I wrote a
 *    helper function -> int maxSumInRange = getMaxSumInRange(cI, start, nums);
 *    this is again not required as this will add another '* k' TC
 *
 *    instead we keep a length which we increment -> and also we keep incrementing the length of the array
 *
 *     int currentLength = 0, maxValueInRange = 0, maxValue = 0;
 *
 *     for(int start = cI; start <= Math.min(cI + k - 1, nums.length - 1); start++){
 *
 *       currentLength++;
 *
 *       maxValueInRange = Math.max(maxValueInRange, nums[start]);
 *
 *       int maxSumInRange = currentLength * maxValueInRange;
 *
 *       maxValue = Math.max(maxValue,  maxSumInRange + helperStriver(start + 1, k,  nums, dp));
 *     }
 *
 *     see we are adding the sum of (length * maxSumInRange)
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
@Slf4j
public class PartitioningArrayForMaximumSum {
  public int maxSumAfterPartitioning(int[] arr, int k) {
    int[] dp = new int[arr.length];
    Arrays.fill(dp, -1);
    return helper(0, k, arr, dp);
  }
  private int helper(int cI, int k, int[] nums, int[] dp){
    if(cI == nums.length) return 0;

    if(dp[cI] != -1) return dp[cI];

    int maxValue = 0;

    for(int start = cI; start <= Math.min(cI + k - 1, nums.length - 1); start++){
      int maxSumInRange = getMaxSumInRange(cI, start, nums);
      maxValue = Math.max(maxValue, maxSumInRange + helper(start + 1, k,  nums, dp));
    }

    return dp[cI] = maxValue;

  }

  private int helperStriver(int cI, int k, int[] nums, int[] dp){
    if(cI == nums.length) return 0;

    if(dp[cI] != -1) return dp[cI];



    int currentLength = 0, maxValueInRange = 0, maxValue = 0;

    for(int start = cI; start <= Math.min(cI + k - 1, nums.length - 1); start++){

      currentLength++;

      maxValueInRange = Math.max(maxValueInRange, nums[start]);

      int maxSumInRange = currentLength * maxValueInRange;

      maxValue = Math.max(maxValue,  maxSumInRange + helperStriver(start + 1, k,  nums, dp));
    }

    return dp[cI] = maxValue;

  }

  /**
   * Top down
   * f(i)
   *
   * i -> 0, n
   *
   * bottoms up
   *
   * n, 0
   *
   */

  private int tabulationStriver(int[] nums, int k){
    int n = nums.length;
    int dp[] = new int[n + 1];

    for(int cI = n - 1; cI >= 0 ; cI--){

      int currentLength = 0, maxValueInRange = 0, maxValue = 0;

      for(int start = cI; start <= Math.min(cI + k - 1, nums.length - 1); start++){

        currentLength++;

        maxValueInRange = Math.max(maxValueInRange, nums[start]);

        int maxSumInRange = currentLength * maxValueInRange;

        maxValue = Math.max(maxValue,  maxSumInRange + dp[start + 1]);
      }

      dp[cI] = maxValue;
    }

    return dp[0];

  }

  private int getMaxSumInRange(int start, int end, int[] nums){
    int maxValue = 0;
    for(int cI = start ; cI <= end; cI++){
      maxValue = Math.max(maxValue, nums[cI]);
    }

    return (end - start + 1) * maxValue;
  }

  public static void main(String[] args){
    log.info("Max Sum in Range : {} ", new PartitioningArrayForMaximumSum().getMaxSumInRange(3, 3, new int[]{1, 15, 7, 9, 2, 5, 10}));
  }

}
