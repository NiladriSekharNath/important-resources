package com.adidas.dsa.striversde.slidingwindow;

/**
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * Example 2:
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 *
 *
 *
 * So the above question can be converted into
 *
 * Longest Subarray containing 1's with at most k zeroes
 *
 *
 * [1,1,1,0,0,0,1,1,1,1,0] k = 2
 *  |       |
 *
 *  see we are counting the zeroes -> with the rightPointer
 *
 *  till the max limit of zeroes is available we are calculating the maxLength
 *
 *  now if the maxlimit is exceeded we move the left pointer and if zeroes are found we decrement them
 *
 *
 */
public class MaximumConsequtiveOnesIII {

  /**
   *
   * TC -> O(n + n)
   *       because of the inner while loop, which runs not all times but some time
   * SC -> O(1)
   *
   *
   *
   */

  public int longestOnes(int[] nums, int k) {
    int left = 0, right = 0, n = nums.length, maxLength = 0, countZ = 0;
    while(right < n) {
      if(nums[right] == 0) countZ++;
      while(countZ > k){
        if(nums[left] == 0) countZ--;
        left++;

      }

      if(countZ <= k){
        maxLength = Math.max(maxLength, right - left + 1);

      }
      right++;
    }
    return maxLength;
  }

  /**
   * Okay this question there is a better solution in the sliding pointer above approach
   *
   * the idea is when we find a longer valid maxlength we don't update the maxlength
   * we simply move the left pointer by 1 if there is zero we decrement the number of available zeroes
   *
   *
   *
   * [1,1,1,0,0,0,1,1,1,1,0], k = 2
   *  |       |
   *
   *  after this we increment the right
   *
   *  [1,1,1,0,0,0,1,1,1,1,0]
   *     |       |                  see here the left increases by 1
   *
   *  since the count of Zeroes = 3 we don't move anywhere
   *
   */
  public int longestOnesBetter(int[] nums, int k) {
    int left = 0, right = 0, n = nums.length, maxLength = 0, countZ = 0;
    while(right < n) {
      if(nums[right] == 0) countZ++;
      if(countZ > k){
        if(nums[left] == 0) countZ--;
        left++;

      }

      if(countZ <= k){
        maxLength = Math.max(maxLength, right - left + 1);

      }
      right++;
    }
    return maxLength;
  }

  public static void main(String[] args) {
    //new MaximumConsequtiveOnesIII().longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3);
    new MaximumConsequtiveOnesIII().longestOnes(new int[]{0, 0, 1, 1, 1, 0, 0}, 0);
  }
}
