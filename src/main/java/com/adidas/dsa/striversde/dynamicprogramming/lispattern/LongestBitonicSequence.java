package com.adidas.dsa.striversde.dynamicprogramming.lispattern;

import java.util.Arrays;

/**
 * A Bitonic Sequence is a sequence of numbers that is first strictly increasing and then strictly decreasing.
 *
 *
 * A strictly ascending order sequence is also considered bitonic, with the decreasing part as empty,
 * and same for a strictly descending order sequence.
 *
 *
 *
 * For example, the sequences [1, 3, 5, 3, 2], [1, 2, 3, 4] are bitonic, whereas the sequences [5, 4, 1, 4, 5] and [1, 2, 2, 3] are not.
 *
 *
 *
 * You are given an array 'arr' consisting of 'n' positive integers.
 *
 *
 *
 * Find the length of the longest bitonic subsequence of 'arr'.
 *
 *
 *
 * Example :
 * Input: 'arr' = [1, 2, 1, 2, 1]
 *
 * Output: 3
 *
 * Explanation: The longest bitonic subsequence for this array will be [1, 2, 1]. Please note that [1, 2, 2, 1]
 * is not a valid bitonic subsequence, because the consecutive 2's are neither strictly increasing, nor strictly decreasing.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 5
 * 1 2 1 2 1
 *
 *
 * Sample Output 1:
 * 3
 *
 *
 * Explanation For Sample Input 1:
 * The longest bitonic subsequence for this array will be [1, 2, 1]. Please note that [1, 2, 2, 1]
 * is not a valid bitonic subsequence, because the consecutive 2's are neither strictly increasing, nor strictly decreasing.
 *
 *
 * Sample Input 2 :
 * 5
 * 1 2 1 3 4
 *
 *
 * Sample Output 2 :
 * 4
 *
 *
 * Explanation For Sample Input 2:
 * The longest bitonic sequence for this array will be [1, 2, 3, 4].
 *
 *
 * Expected time complexity :
 * The expected time complexity is O(n ^ 2).
 *
 *
 * Constraints:
 * 1 <= 'n' <= 10^3
 * 1 <= 'arr[i]' <= 10^5
 *
 * Time Limit: 1sec
 *
 * let's say we are given this array : ->
 *
 * 1, 11, 2, 10, 4, 5, 2, 1
 *
 * we are trying to find the longest Bitonic subsequence
 *
 * here if we see
 *
 * we get 1, 11, 10, 5, 2, 1 -> 1, 11 strictly increasing 10, 5, 2, 1 strictly decreasing
 *
 * the trick to this problem is we are let's say we are trying to relate to lis
 *
 * in order to find the lis we are calculating this value
 *
 * let's say lis
 * [0, 1,  2, 3,  4, 5, 6, 7]  -> index
 * [1, 11, 2, 10, 4, 5, 2, 1]
 *
 * let's say we are at index = 3 in the lis we are getting till index = 3 the length of the lis
 *
 * which is 1, 2, 10 -> length = 3
 *
 * we are seeing from the left hand side, now if we are seeing from the right hand side
 *
 *
 * [0, 1,  2, 3,  4, 5, 6, 7]
 * [1, 11, 2, 10, 4, 5, 2, 1] <- looking from the right side we see we are seeing the length of increasing lis from the right side
 *
 *  that is at index = 4 we see the length of the longest lis from the right =
 *
 *  1 -> 2 -> 5 or 1 -> 2 -> 4
 *
 *
 * so below if we see this let's say at index = 3
 *
 * left side lis is       1, 2, 10    -> size = 3
 * right side lis(lds) is 1, 2, 4, 10 -> size = 4
 *
 * so we add them up giving us a total size = 7
 *
 * so the idea for this question lis + lds = 3 + 4 = 7 (since we are including 10 twice we would have to subtract once giving us
 * 6
 *
 *
 * for(int cI = 0 ; cI < n; cI++){
 *       longestBitTonicVal = Math.max(longestBitTonicVal, lis[cI] + lds[cI] - 1);
 *     }
 *
 * adding both and subtracting 1
 *
 * and keeping track of the longest length
 *
 */
public class LongestBitonicSequence {
  public static int longestBitonicSequence(int[] arr, int n) {
    int[] lis = new int[n], lds = new int[n];
    Arrays.fill(lis, 1);
    Arrays.fill(lds, 1);
    for(int cI = 0; cI < n; cI++){
      for(int prev = 0; prev < cI; prev++){
        if(arr[prev] < arr[cI] && lis[prev] + 1 > lis[cI])
          lis[cI] = lis[prev] + 1;
      }
    }

    for(int cI = n - 1; cI >= 0; cI--){
      for(int prev = n - 1; prev > cI; prev--){
        if(arr[prev] < arr[cI] && lds[prev] + 1 > lds[cI])
          lds[cI] = lds[prev] + 1;
      }
    }

    int longestBitTonicVal = 0;

    for(int cI = 0 ; cI < n; cI++){
      longestBitTonicVal = Math.max(longestBitTonicVal, lis[cI] + lds[cI] - 1);
    }

    return longestBitTonicVal;

  }
}
