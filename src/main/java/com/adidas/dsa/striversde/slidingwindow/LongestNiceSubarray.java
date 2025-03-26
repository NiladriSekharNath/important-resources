package com.adidas.dsa.striversde.slidingwindow;

/**
 * You are given an array nums consisting of positive integers.
 *
 * We call a subarray of nums nice if the bitwise AND of every pair of elements that are in different positions in the subarray is equal to 0.
 *
 * Return the length of the longest nice subarray.
 *
 * A subarray is a contiguous part of an array.
 *
 * Note that subarrays of length 1 are always considered nice.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,3,8,48,10]
 * Output: 3
 * Explanation: The longest nice subarray is [3,8,48]. This subarray satisfies the conditions:
 * - 3 AND 8 = 0.
 * - 3 AND 48 = 0.
 * - 8 AND 48 = 0.
 * It can be proven that no longer nice subarray can be obtained, so we return 3.
 * Example 2:
 *
 * Input: nums = [3,1,5,11,13]
 * Output: 1
 * Explanation: The length of the longest nice subarray is 1. Any subarray of length 1 can be chosen.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 109
 *
 * Approach
 * I'll use a sliding window technique with these steps:
 *
 * Maintain a variable used_bits that tracks which bits are currently "used" by elements in our window
 * For each element:
 * If it doesn't share bits with used_bits, add it to our window
 * If it shares bits, shrink the window from the left until there's no conflict
 * Keep track of the maximum window size seen
 * The key operations are:
 *
 * used_bits & nums[right] != 0: Checks if the current number shares any bits with our window
 * used_bits ^= nums[left]: Removes the leftmost element's bits from our tracking
 * used_bits |= nums[right]: Adds the current element's bits to our tracking
 *
 * Sliding Window with Bit Masking Concepts
 *
 *
 * given the array
 *
 * l
 * |
 * 1, 3, 8, 48, 10
 * |
 * r
 *
 * we have a setBits = 0
 *
 * initially let's dry run this :
 *
 * setBits = 0
 *
 * idea is in order for the 'AND' of two numbers are 0 we don't ->
 *
 * we can have atmost one set bit for the same bit
 *
 *                           8 4 2 1
 * consider 8 and 10 -> 8 -> 1 0 0 0
 *                     10 -> 1 0 1 0
 *
 *
 *    so if we do (8 & 10) -> we don't get zero we get 8 & 10 = 8
 *
 *    also will be zero for 3 & 8 = 0011 & 1000 = 0
 *
 *    that's the idea:
 *
 *    iterate using right to the end ->
 *
 *    1, 3, 8, 48, 10
 *
 *
 *    IN ORDER TO SET THE BITS WE apply
 *
 *    setBits = setBits | nums[right] = right = 1(number not index) so setBits = 1
 *
 *    now increase the window,
 *
 *    we encounter setBits |= nums[right], right = 3
 *
 *    now we see 'setBits & nums[left] != 0  -> 1 & 3 != 0( ==> 01 & 11 = 01) '
 *
 *    if you see we have more bits set what we should do it
 *
 *    increment from the left side -> and remove the left values
 *
 *    ->
 *       we use the xor(^) operator to undo the results:
 *
 *       left = 1
 *
 *       setBits = 01
 *
 *       nums[left] |= 1 ^ 1 = 0
 *
 *        (01 ^ 01 == 0)
 *
 *       another example :
 *
 *       when we are at this window :
 *
 *       (1, 3, 8, 48, 10)
 *           |      |
 *
 *       and setBits = (3 & 8 & 48) written like shown for easy visualization
 *
 *           32 16 8 4 2 1
 *           0  0  0 0 1 1
 *           0  0  1 0 0 0
 *           1  1  0 0 0 0
 *
 *           1  1  1 0 1 1 -> setBits
 *
 *        setBits = 1 1 1 0 1 1
 *
 *        now when we encountered -> 10(since 10 doesn't satisfy, we have to remove 3, from the left)
 *
 *        11 ^ 111011 = 111000
 *
 *        That's the whole idea
 *        honestly
 *
 *        & -> is used to check if bits are set
 *        | -> to set the bits
 *        ^ -> to unset the bits
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
 *
 *
 */
public class LongestNiceSubarray {
  public int longestNiceSubarray(int[] nums) {
    int left = 0, right = 0, setBits = 0, maxLength = 0;
    while(right < nums.length){
      while((setBits & nums[right]) != 0){
        setBits ^= nums[left];
        left++;
      }

      if((setBits & nums[right]) == 0)
        maxLength = Math.max(maxLength, right - left + 1);

      setBits |= nums[right];
      right++;
    }

    return maxLength;
  }
}
