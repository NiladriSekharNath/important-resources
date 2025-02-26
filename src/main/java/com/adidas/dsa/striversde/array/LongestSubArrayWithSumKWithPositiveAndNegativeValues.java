package com.adidas.dsa.striversde.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Ninja and his friend are playing a game of subarrays. They have an array ‘NUMS’ of length ‘N’.
 *
 * Ninja’s friend gives him an arbitrary integer ‘K’ and asks him to find the length of the longest subarray in which the sum of elements is equal to ‘K’.
 *
 * Ninjas asks for your help to win this game. Find the length of the longest subarray in which the sum of elements is equal to ‘K’.
 *
 * If there is no subarray whose sum is ‘K’ then you should return 0.
 *
 * Example:
 * Input: ‘N’ = 5,  ‘K’ = 4, ‘NUMS’ = [ 1, 2, 1, 0, 1 ]
 *
 * Output: 4
 *
 * There are two subarrays with sum = 4, [1, 2, 1] and [2, 1, 0, 1]. Hence the length of the longest subarray with sum = 4 is 4.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= T <= 10
 * 1 <= N <= 10^5
 * -10^6 <= NUMS[ i ] <= 10^6
 * -10^6 <= K <= 10^6
 *
 * Sum of N Over all the Test cases <= 10^5
 *
 * Time Limit: 1 sec
 * Sample Input 1 :
 * 2
 * 3 5
 * 2 3 5
 * 3 1
 * -1 1 1
 * Sample Output 1 :
 * 2
 * 3
 * Explanation Of Sample Input 1 :
 * For the first case:
 * There are two subarrays with sum = 5, [2, 3] and [5]. Hence the length of the longest subarray is 2.
 *
 * For the second case:
 * Longest subarray with sum = 1 is [1, -1, 1].
 * Sample Input 2 :
 * 2
 * 3 4
 * 1 1 1
 * 3 2
 * -50 0 52
 * Sample Output 2 :
 * 0
 * 3
 *
 *
 * Let's say we are given this array, k = 3
 *
 *  0  1  2  3  4  5  6  7  8  9
 * [1, 2, 3, 1, 1, 1, 1, 4, 2, 3]
 *              |
 *             cI
 *
 * idea is we are storing the subarray till the end 4 (meaning in the range) -> 0 - 4
 *
 * meaning if we have a running sum(prefix sum) and use this as shown below
 *
 *  0  1  2  3  4  5  6  7  8  9
 * [1, 2, 3, 1, 1, 1, 1, 4, 2, 3]
 * |-------x ------|
 * |------| |------|
 *  x - k      k
 *
 *  we store in the map<sum, indexFoundAt>
 *
 *  so the idea of this problem is if we are seeing the prefixSum(sum till index 5) where we keep track of the sum
 *
 *  x = 9, we know 'k' we'll check if we have (x - k = 9 - 3 = 6), '6' in our map, where k = 3(given),
 *
 *  we found sum 6 present in the subarray (0 - 2) and we are currently at index = 5, where sum = 9 (denoted by x)
 *
 *  since we are at sum = 9 and we have 6 in the map, we can find a length = 5 - 2( actually we are concerned with the length
 *
 *  'k' so 5 - (2 + 1 ) + 1 = 5 - 2 - 1 + 1 = 5 - 2 = 3
 *
 *
 *  we are simply finding 'x' we know 'x - k' we just need to find 'k' since we cannot directly get 'k'
 *
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

public class LongestSubArrayWithSumKWithPositiveAndNegativeValues {
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
}
