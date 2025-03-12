package com.adidas.dsa.striversde.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary array nums and an integer goal, return the number of non-empty subarrays with a sum goal.
 *
 * A subarray is a contiguous part of the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,0,1,0,1], goal = 2
 * Output: 4
 * Explanation: The 4 subarrays are bolded and underlined below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * Example 2:
 *
 * Input: nums = [0,0,0,0,0], goal = 0
 * Output: 15
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * nums[i] is either 0 or 1.
 * 0 <= goal <= nums.length
 *
 * the prefix sum way as explained properly in the "CountSubArrayWithGivenSum" Question is important because
 *
 *     [. . . . . . . . . .]
 *     [-----x-------]
 *     [-- x-k -][-k-]
 *
 *     that solution would work here for sure and is working as well
 *
 *     however this question is binarysubarrayWithSum we are working binary array so we need to use that as well
 *
 *
 */
public class BinarySubArrayWithSum {
  public int numSubarraysWithSum(int[] nums, int k) {

    Map<Integer, Integer> sumCount = new HashMap<>();
    int sum = 0, cnt = 0;
    for(int cI = 0; cI < nums.length; cI++){
      sum += nums[cI];
      if(sum == k) cnt += 1;
      int remainingSum = sum - k;
      if(sumCount.containsKey(remainingSum))
        cnt += sumCount.get(remainingSum);

      sumCount.put(sum, sumCount.getOrDefault(sum, 0) + 1);

    }

    return cnt;

  }

  /**
   * this is a different type of solution that we are trying here
   *
   * see the normal sliding window concept would fail here since there are zeroes
   *
   * where if we keep the condition as sum == k we would definitely fail
   *
   * let's visualize this one
   *  0 1 2 3 4 5
   * [1 0 0 1 1 0], k = 2
   *  l       r
   *
   *  now if we see the [l - r] k = 3 so we need to increment the left pointer
   *
   *  incrementing the left pointer by 1
   *   0 1 2 3 4 5
   *  [1 0 0 1 1 0]
   *     l     r     k = 2
   *
   *
   * if we see here [1 - 4] is a valid range and so is [2 - 4] again [3 - 4] and we cannot calculate properly this count
   * as we have zeroes in the range as well
   *
   * So the above approach fails so we are trying to calculate the number of subarrays which are lesser than equal to goal
   *
   *  k <= 2
   *
   *  we will search for all the subarrays
   *
   *        0 1 2 3 4 5
   *       [1 0 0 1 1 0]
   *        l     r     k = 2
   *
   *
   *         so going by this approach here we are at l = 0 and
   *         let's say r starts from 0 to n
   *         l = 0,
   *           r = 0 we, sum = 1 (<= 2) we add +1
   *           r = 1 we, sum = 1 (<= 2) we add (+2 ) idea being( we had previous answer count = 1(where sum <= 2) now if we add
   *                                  another number which again keeps the answer as <= 2 so that value would individually contribute
   *                                  to the answer so +2 subarrays [[0,1], [1, 1]]
   *
   *          r = 2 we, sum = 1 (<= 2) we +3 (subarrays -> [[0,2], [1,2], [2], basically all subarrays ending at index = 2
   *          r = 2 we, sum = 2 (<= 2) we +4
   *
   *          r = 4 we get sum = 3 before adding, the inner loop starts
   *
   *        l = 1,
   *          r = 4
   *
   *           0 1 2 3 4 5
   *          [1 0 0 1 1 0]
   *             l     r
   *
   *          sum  = 2 (<= 2) so we add all subarrays starting with index = 1 and ending with index = 4
   *                              [[1 - 4], [2 - 4], [3 - 4], [4]]
   *
   *
   *        Now the question for this we are getting
   *
   *        count of subarrays sum <= k
   *          but we only need count of subarrays sum == k
   *
   *        so what we did is
   *
   *        x = (count of subarrays sum <= k) - (count of subarrays <= k - 1)
   *
   *        x = (count of subarrays sum == k )
   *
   *        so this is the idea
   *
   *
   *        the above idea is working for the problems with count where we see we are missing out of counts
   *        0 1 2 3 4
   *        1 0 0 1 1 0
   *          l     r
   *
   *          here if we are not sure if we should move the 'l' to the right or move the 'r' to the right
   *          because [1 - 4] is valid but so it [2 - 4] also [3 - 4]..
   *
   *
   */

  public int numSubarraysWithSumDifferentApproach(int[] nums, int k){
    return helper(nums, k ) - helper(nums, k - 1);
  }
  private int helper(int[] nums, int k){

    /**
     * we added one extra case here bacause if k originally given as 0 then k - 1 = -1, is impossible to find from
     * arrays with only [0, 1]
     */
    if(k < 0) return 0 ;
    int left = 0, right = 0, n = nums.length, cnt = 0, sum = 0;
    while(right < n) {
      sum += nums[right];
      while(sum > k){
        sum -= nums[left];
        left++;
      }
      if(sum <= k) cnt += right - left + 1  ;
      right++;
    }

    return cnt;
  }
}
