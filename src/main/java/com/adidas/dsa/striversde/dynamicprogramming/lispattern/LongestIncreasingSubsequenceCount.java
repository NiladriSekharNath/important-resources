package com.adidas.dsa.striversde.dynamicprogramming.lispattern;

/**
 *  Number of Longest Increasing Subsequence
 * Moderate
 * 80/80
 * Contributed by
 * 40 upvotes
 * Asked in companies
 * Problem statement
 * Given an integer array ‘arr’ of length ‘n’, return the number of longest increasing subsequences in it.
 *
 *
 *
 * The longest increasing subsequence(LIS) is the longest subsequence of the given sequence such that all subsequent
 * elements are in strictly increasing order.
 *
 *
 *
 * Example:
 * Input: ‘n’ = 5, ‘arr’ = [50, 3, 90, 60, 80].
 *
 * Output: 2
 *
 * Explanation:
 * In this array, the longest increasing subsequences are [50, 60, 80] and [3, 60, 80].
 *
 * There are other increasing subsequences as well, but we need the number of the longest ones. Hence the answer is 2.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 4
 * 3 7 4 6
 * Sample Output 1 :
 * 1
 * Explanation For Sample Input 1 :
 * The length of LIS is 3, and there is only one such LIS, which is [3, 4, 6].
 * Sample Input 2 :
 * 4
 * 5 7 2 3
 * Sample Output 2 :
 * 2
 * Explanation For Sample Input 2 :
 * The length of LIS is 2, and there are two such LIS, which are [5, 7] and [2, 3].
 * Expected Time Complexity:
 * The expected time complexity is O(n^2).
 * Constraints :
 * 1 ≤ ‘n’ ≤ 5000
 * 1 ≤ ‘arr’[i] ≤ 10 ^ 6
 *
 * Time limit: 1 sec
 *
 *
 * let's say we are given this array
 *
 *        1, 5, 4, 3, 2, 6, 7
 * lis    1  2  2  2  2  3, 4
 * count  1  1  1  1  1  4  4
 *
 * intially all the count lis and count are set as 1
 *
 * idea is we know the lis[cI] array stores the max lis at an index cI,
 * so now the count[cI] holds the number of increasing subsequence
 * till that index cI
 *
 * at 4 we get lis = 2
 * however count = 1 see here there is only one lis -> 1, 4
 *
 * at 6 we get lis = 3, when we try for 1, we get lis for [6] updates to 2
 *                      when we try for 5, we get lis for [6] updates to 3
 *                      when we try for 4, we get lis for [6] keep same as 3 (1, 5, 6, or 1, 4, 6)
 *                        so the count of lis of len 3 we get 2 count[7] = count[7] + count[4] = 1 + 1
 *                      when we try for 3, we get lis for [6] keep same as 3 (1, 5, 6, or 1, 4, 6 or 1, 3, 6)
 *  *                        so the count of lis of len 3 we get 2 count[7] = count[7] + count[3] = 1 + 1 + 1
 *
 *
 *  so similarly if we calculate we get this condition :
 *
 *   if(arr[prev] < arr[cI] && lis[prev] + 1 > lis[cI]){
 *           lis[cI] = lis[prev] + 1;
 *           count[cI] = count[prev];
 *   }else if(arr[prev] < arr[cI] && lis[prev] + 1 == lis[cI])
 *           count[cI] += count[prev];
 *
 *   if lis[prev] + 1 > lis[cI] we copy the previous value however if equal we add the count[cI] with the count[prev]
 *
 *
 *
 *   now there are places in the lis where we found the max lis and that point will have have to count all the places where
 *   are lis == maxLis and we keep adding the count
 *
 *
 *
 *
 *
 *
 */
public class LongestIncreasingSubsequenceCount {

  public static int findNumberOfLIS(int[] arr) {
    int n = arr.length, maxValue = 1;
    int[] lis = new int[n], count = new int[n];
    for(int cI = 0; cI < n; cI++){
      lis[cI] = 1;
      count[cI] = 1;
      for(int prev = 0; prev < cI; prev++){
        if(arr[prev] < arr[cI] && lis[prev] + 1 > lis[cI]){
          lis[cI] = lis[prev] + 1;
          count[cI] = count[prev];
        }else if(arr[prev] < arr[cI] && lis[prev] + 1 == lis[cI])
          count[cI] += count[prev];
      }

      maxValue = Math.max(maxValue, lis[cI]);

    }

    int ans = 0;
    for(int cI = 0; cI < n; cI++){
      if(lis[cI] == maxValue) ans += count[cI];
    }

    return ans;

  }

}
