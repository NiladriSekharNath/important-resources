package com.adidas.dsa.striversde.dynamicprogramming.lispattern;

import java.util.Arrays;

/**
 * Problem statement
 * You are given an array 'arr' of strings, where each string consists of English lowercase letters.
 *
 *
 *
 * A string chain of 'arr' is defined as:
 *
 * (1) A sequence of string formed using elements of 'arr'.
 *
 * (2) Every string in the sequence can be formed by inserting a
 * lowercase English letter into the previous string (except the first string).
 *
 *
 *
 * Find the length of the longest possible string chain of 'arr'.
 *
 *
 *
 * Example :
 * Input: 'arr' = ["x", "xx", "y", "xyx"]
 *
 * Output: 3
 *
 * Explanation:
 * The longest possible string chain is “x” -> “xx” -> “xyx”.
 * The length of the given chain is 3, hence the answer is 3.
 *
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 3
 * m
 * nm
 * mmm
 *
 *
 * Expected Answer :
 * 2
 *
 *
 * Output on console :
 * 2
 *
 *
 * Explanation For Sample Input 1 :
 * In this testcase, the longest possible string chain is "m" -> "nm".
 * The length of the given chain is 2, hence the answer is 2.
 *
 *
 * Sample Input 2 :
 * 5
 * a
 * bc
 * ad
 * adc
 * bcd
 *
 *
 * Expected Answer :
 * 3
 *
 *
 * Output on console :
 * 3
 *
 *
 * Explanation For Sample Input 2 :
 * In this testcase, the longest possible string chain is “a” -> “ad” -> “adc”.
 * The length of the given chain is 3, hence the answer is 3.
 *
 *
 * Expected Time Complexity :
 * Try to solve this in O(n*n*l), where 'n' is the size of array 'arr' and 'l' is the maximum length of a string in 'arr'.
 *
 *
 * Constraints :
 * 1 ≤ n ≤ 300
 * 1 ≤ arr[i].length ≤ 16
 *
 * Time limit: 1 sec
 *
 *
 * Same logic as the longest Increasing subsequence pattern we are checking for
 *
 * "x", "xx", "y", "xyx"
 *
 * here we since the characters are increasing
 *
 * when cI -> xx , prev -> x
 *
 * now element at the cI > element in the prev
 *
 * now what we are doing is checking
 *
 * two pointers first and second, pointing to "xx", "x" respectively
 *
 * since the "curr" string holds the longer string the pointer pointing to curr (first) we are checking if it stays in the
 * till the end
 *
 * xx  x
 * |   |
 * f   s  (first second)
 *
 * now we increment both first and second when we get a match
 * if we don't get a match we increment only the first pointer thus we get
 *
 * now when we break we check if first reaches the end and second reaches the end at the same time
 * if yes then we return true otherwise we return false
 *
 *
 * now if we see we sorted the strings by their length
 *
 * ["xbc", "pcxbcf", "xb", "cxbc", "pcxbc"]
 *
 * our idea is if we don't sort we see we would match properly "xbc" -> "cxbc" -> "pcxbc" giving us 3
 *
 * however in this string
 *
 * our ideal answer should be after sorting by lengths
 *
 * ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"]
 *
 * "xb" -> "xbc" -> "cxbc" -> "pcxbc" -> "pcxbcf"
 *
 */
public class LongestStringChain {
  public static int longestStrChain(String[] arr) {
    int n = arr.length, maxValue = 1;
    Arrays.sort(arr, (s1, s2) -> s1.length() - s2.length());
    int[] dp = new int[n];
    Arrays.fill(dp, 1);
    for(int cI = 0; cI < n ; cI++){
      for(int prev = 0; prev < cI ; prev++){
        if(checkPossible(arr[cI], arr[prev]) && dp[prev] + 1 > dp[cI])
          dp[cI] = dp[prev] + 1;

      }

      maxValue = Math.max(maxValue, dp[cI]);
    }

    return maxValue;
  }

  private static boolean checkPossible(String curr, String prev){
    if(curr.length() != prev.length() + 1) return false;
    int first = 0, second = 0;
    while(first < curr.length()){
      if(second < prev.length() && curr.charAt(first) == prev.charAt(second)){
        first++;
        second++;
      }else {
        first++;
      }

    }
    if(first == curr.length() && second == prev.length()) return true;
    return false;
  }
}
