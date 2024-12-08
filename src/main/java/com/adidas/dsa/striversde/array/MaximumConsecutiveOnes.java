package com.adidas.dsa.striversde.array;

/**
 * Approach: We take a count variable and increment it till we get a non-zero
 * and in every iteration we find the max value and return the max
 *
 * Time Complexity : O(n)
 * Space Complexity : O(1)
 */
public class MaximumConsecutiveOnes {
  public int findMaxConsecutiveOnes(int[] nums) {
    int count = 0, max = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 1) count++;
      else {
        count = 0;
      }

      max = Math.max(max, count);
    }

    return max;
  }
}
