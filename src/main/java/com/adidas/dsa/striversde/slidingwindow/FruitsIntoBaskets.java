package com.adidas.dsa.striversde.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Find length of the longest subarray containing atmost two distinct integers
 *
 * Given an array arr[] containing positive elements, the task is to find the
 * length of the longest subarray of an input array containing at most two distinct integers.
 *
 * Examples:
 *
 * Input: arr[]= [2, 1, 2]
 * Output: 3
 * Explanation: The entire array [2, 1, 2] contains at most two distinct integers (2 and 1).
 * Hence, the length of the longest subarray is 3.
 * Input: arr[] = [3, 1, 2, 2, 2, 2]
 * Output: 5
 * Explanation: The longest subarray containing at most two distinct integers is [1, 2, 2, 2, 2], which has a length of 5.
 * The subarray starts at the second element 1 and ends at the last element. It contains at most two distinct integers (1 and 2).
 * Constraints:
 * 1 ≤ arr.size() ≤ 105
 * 1 ≤ arr[i] <=105
 *
 * this problem can be extended to 'k'
 */
public class FruitsIntoBaskets {

  public static int totalElements(Integer[] arr) {
    return helper(arr, 2);

  }

  /**
   *
   * Same sliding pointer approach with expanding the right pointer till and we are calculating with help of the map
   * storing the size of all untill k unique elements till the size is <= k we calculate the maxLength
   *
   * and keep going
   *
   * once we have larger length > k we simply move the left pointer front as there we need to remove the
   * extra elements
   *
   *
   */

  private static int helper(Integer[] nums, int k){
    Map<Integer, Integer> map = new HashMap<>();
    int left = 0, right = 0, maxLength = 0, n = nums.length;
    while(right < n){
      while(map.size() > k){
        int leftVal = nums[left];

        map.put(leftVal, map.get(leftVal) - 1);
        if(map.get(leftVal) == 0) map.remove(leftVal);

        left++;

      }
      map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

      if(map.size() <= k){
        maxLength = Math.max(maxLength, right - left + 1);
      }

      right++;
    }

    return maxLength;
  }

  /**
   *
   * same approach instead of the inner while loop we change that to 'if'
   * since the idea of this problem since we are controlling the maxLength function by the map size
   * we simply move the left pointer by 1
   */
  public static int totalElements2(Integer[] arr) {
    return helper2(arr, 2);

  }

  private static int helper2(Integer[] nums, int k){
    Map<Integer, Integer> map = new HashMap<>();
    int left = 0, right = 0, maxLength = 0, n = nums.length;
    while(right < n){
      if(map.size() > k){
        int leftVal = nums[left];

        map.put(leftVal, map.get(leftVal) - 1);
        if(map.get(leftVal) == 0) map.remove(leftVal);

        left++;

      }
      map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

      if(map.size() <= k){
        maxLength = Math.max(maxLength, right - left + 1);
      }

      right++;
    }

    return maxLength;
  }


}
