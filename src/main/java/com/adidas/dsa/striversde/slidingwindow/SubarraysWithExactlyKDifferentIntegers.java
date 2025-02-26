package com.adidas.dsa.striversde.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 *
 * A good array is an array where the number of different integers in that array is exactly k.
 *
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 * Example 2:
 *
 * Input: nums = [1,2,1,3,4], k = 3
 * Output: 3
 * Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * 1 <= nums[i], k <= nums.length
 *
 * Same logic as the "CountOfNiceSubArrays or BinarySubArrayWithSum" Question
 *
 */
public class SubarraysWithExactlyKDifferentIntegers {
  public int subarraysWithKDistinct(int[] nums, int k) {

    return helper(nums, k) - helper(nums, k - 1);

  }
  private int helper(int[] nums, int k){
    if(k < 0) return 0;

    int left = 0, right = 0, n = nums.length, cnt = 0;

    Map<Integer, Integer> map = new HashMap<>();

    while(right < n){
      map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
      while(map.size() > k){
        int leftValue = nums[left];
        map.put(leftValue, map.get(leftValue) - 1);
        if(map.get(leftValue) == 0){
          map.remove(leftValue);
        }
        left++;
      }
      if(map.size() <= k){
        cnt += right - left + 1;
      }
      right++;
    }

    return cnt;

  }
}
