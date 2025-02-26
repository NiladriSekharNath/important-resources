package com.adidas.dsa.striversde.slidingwindow;

/**
 * Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
 *
 * Return the number of nice sub-arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * Example 2:
 *
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There are no odd numbers in the array.
 * Example 3:
 *
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 *
 * Prerequisite to this problem is "BinarySubArrayWithSum"
 *
 *  0 1 2 3 4 5 6 7 8 9
 * [2,2,2,1,2,2,1,2,2,2], k = 2
 *
 * if we see this problem all subarrays (since we need k = 2 ) have the subarray [3 - 6] namely [2 - 6], [1 - 6]
 *
 * so again directly counting will not work here
 * again if we see this we can directly relate to the BinarySubArrayWithSum Problem
 *
 * if we see that all odd numbers are 1 and all even numbers are 0, k = 2
 *
 *
 * [0,0,0,1,0,0,1,0,0,0], k = 2
 *
 * now if we consider this problem as counting the number of subarray sum with sum == k
 *
 * exactly same problem as the BinarySubArrayWithSum
 *
 * same approach we are counting odd numbers to the right and deducting from the left once the limit has exceeded
 *
 */
public class CountOfNiceSubArrays {
  public int numberOfSubarrays(int[] nums, int k) {
    return helper(nums, k) - helper(nums, k - 1);
  }

  private int helper(int[] nums, int k){
    if(k < 0) return 0;
    int left = 0, right = 0, n = nums.length, cnt = 0, oddCounter = 0;
    while(right < n){
      if(nums[right] % 2 != 0) oddCounter++;
      while(oddCounter > k){
        if(nums[left] % 2 != 0) oddCounter--;
        left++;
      }
      if(oddCounter <= k) cnt += right - left + 1;
      right++;
    }
    return cnt;
  }
}
