package com.adidas.dsa.striversde.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem statement
 * You are given an integer array 'arr' of size 'N' and an integer 'K'.
 *
 * Your task is to find the total number of subarrays of the given array whose sum of elements is equal to k.
 *
 * A subarray is defined as a contiguous block of elements in the array.
 *
 * Example:
 * Input: ‘N’ = 4, ‘arr’ = [3, 1, 2, 4], 'K' = 6
 *
 * Output: 2
 *
 * Explanation: The subarrays that sum up to '6' are: [3, 1, 2], and [2, 4].
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * 2
 * 4 6
 * 3 1 2 4
 *
 * 3 3
 * 1 2 3
 * Sample output 1:
 * 2
 * 2
 * Explanation:
 * Test Case 1:
 *
 * Input: ‘N’ = 4, ‘arr’ = [3, 1, 2, 4], 'K' = 6
 *
 * Output: 2
 *
 * Explanation: The subarrays that sum up to '6' are: [3, 1, 2], and [2, 4].
 *
 * Test Case 2:
 *
 * Input: ‘N’ = 3, ‘arr’ = [1, 2, 3], 'K' = 3
 *
 * Output: 2
 *
 * Explanation: The subarrays that sum up to '7' are: [1, 2], and [3].
 * Sample Input 2:
 * 2
 * 3 7
 * 1 2 3
 *
 * 4 9
 * 6 3 5 2
 * Sample output 2:
 * 0
 * 1
 *
 *
 * Same approach as the previous question "LongestSubArrayWithSumKWithPositiveAndNegativeValues"
 *
 * Instead of keeping the Map<sum, index> as in the previous question we are storing Map<sum, count>
 *
 *   Map<sum, count> stores the number of subarrays that has the sum as "sum"
 *
 *   so basically we are doing this approach
 *
 *   [. . . . . . . . . .]
 *    [-----x-------]
 *    [-- x-k -][-k-]
 *
 *
 *    se we need the count of subarrays having sum == k now we already have 'x' since we are tracking we can't find out 'k'
 *    directly that portion subarray(from the middle) however we could find out the (x - k)
 *
 *    now let's say we have x = 10, k = 4, x - k = 6 -> if we find out the number of occurrences for x - k = 6 we also found the
 *    number of occurences for k = 4
 *
 *    which we are good idea is if we find the sum == k directly from the start (prefix sum we are carrying) we will directly
 *    add +1 to the answer
 *
 *
 *
 */
public class CountSubArrayWithGivenSum {

  public static int findAllSubarraysWithGivenSum(int nums[], int k) {
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

}
