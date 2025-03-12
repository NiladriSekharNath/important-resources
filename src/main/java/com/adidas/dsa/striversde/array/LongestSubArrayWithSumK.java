package com.adidas.dsa.striversde.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem statement
 * You are given an array 'a' of size 'n' and an integer 'k'.
 *
 *
 *
 * Find the length of the longest subarray of 'a' whose sum is equal to 'k'.
 *
 *
 *
 * Example :
 * Input: ‘n’ = 7 ‘k’ = 3
 * ‘a’ = [1, 2, 3, 1, 1, 1, 1]
 *
 * Output: 3
 *
 * Explanation: Subarrays whose sum = ‘3’ are:
 *
 * [1, 2], [3], [1, 1, 1] and [1, 1, 1]
 *
 * Here, the length of the longest subarray is 3, which is our final answer.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 7 3
 * 1 2 3 1 1 1 1
 *
 *
 * Sample Output 1 :
 * 3
 *
 *
 * Explanation Of Sample Input 1 :
 * Subarrays whose sum = ‘3’ are:
 * [1, 2], [3], [1, 1, 1] and [1, 1, 1]
 * Here, the length of the longest subarray is 3, which is our final answer.
 *
 *
 * Sample Input 2 :
 * 4 2
 * 1 2 1 3
 *
 *
 * Sample Output 2 :
 * 1
 *
 *
 * Sample Input 3 :
 * 5 2
 * 2 2 4 1 2
 *
 *
 * Sample Output 3 :
 * 1
 *
 *
 * Expected time complexity :
 * The expected time complexity is O(n).
 *
 *
 * Constraints :
 * 1 <= 'n' <= 5 * 10 ^ 6
 * 1 <= 'k' <= 10^18
 * 0 <= 'a[i]' <= 10 ^ 9
 *
 * Time Limit: 1-second
 *
 * this question is very similar to the "LongestSubArrayWithSumKWithPositiveAndNegativeValues" but this contains only positive numbers
 * greater than 0
 *
 * this solution works with the above question approach (which is the best solution for this question ) however there is
 * still a better solution to this question
 *
 *
 */
public class LongestSubArrayWithSumK {
  public static int longestSubarrayWithSumK(int []nums, long k) {
    long prefixSum = 0;
    int maxLength = 0;
    Map<Long, Integer> prefixSumIndex = new HashMap<>();
    for(int cI = 0 ; cI < nums.length; cI++){
      prefixSum += nums[cI];
      if(prefixSum == k) maxLength = Math.max(maxLength, cI + 1);
      long remainingSum = prefixSum - k;
      if(prefixSumIndex.containsKey(remainingSum)){
        int remainingSumFoundAtIdx = prefixSumIndex.get(remainingSum);
        maxLength = Math.max(maxLength, cI - remainingSumFoundAtIdx);
      }

      /**
       * here while saving the sum we are checking if we have an index stored already then we are saving otherwise we are
       * not saving
       *
       * because this is an edge case like if we have negative numbers or zero like this example:
       *
       *  0  1  2  3
       * [2, 0, 0, 3], k = 3
       *
       * now we see we are at x = 3 where sum = 5
       *
       * now we need 5 - 3 = 2,
       *
       * now value of 2 could be at index 0, 1, 2 ->
       *
       * if we select 2, then our length would be at [3] giving us 1 however if we have sum = 2 at index = 0 only
       * we can find a sum range = [1 - 3]  [0, 0, 3] giving us a better length
       *
       * so we try to keep a smaller length(to the left) as much as it is possible to give us a better answer
       *
       *
       *
       */

      if(!prefixSumIndex.containsKey(prefixSum))
        prefixSumIndex.put(prefixSum, cI);
    }

    return maxLength;
  }

  /**
   *
   * sliding pointer approach with expanding of sliding pointer from the
   */
  public static int longestSubarrayWithSumKBetterApproach(int []nums, long k) {
    int left = 0, right = 0, maxLength = 0, n = nums.length;
    long sum = 0 ;

    while(right < n){
      sum += nums[right];
      while(sum > k){
        sum -= nums[left];
        left++;
      }
      if(sum == k){
        maxLength = Math.max(maxLength, right - left + 1);
      }
      right++;
    }

    return maxLength;
  }
}
