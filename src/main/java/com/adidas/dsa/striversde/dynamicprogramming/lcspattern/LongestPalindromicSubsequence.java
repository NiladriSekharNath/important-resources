package com.adidas.dsa.striversde.dynamicprogramming.lcspattern;

/**
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters.
 *
 * same logic as the longest common subsequence
 *
 * what we are doing is taking a string s and reverse of the same string s, let's call this t
 *
 * then we are simply calling lcs(s, reverse(s))
 *
 *
 */
public class LongestPalindromicSubsequence {
  public int longestPalindromeSubseq(String s)  {

    return  helper2ShiftingIndexRightTabulationSpaceOptimized(s, reverse(s));

  }

  private String reverse(String s){
    StringBuilder sb = new StringBuilder();
    for(char ch : s.toCharArray())
      sb.insert(0, ch);
    return sb.toString();
  }

  private int helper2ShiftingIndexRightTabulationSpaceOptimized(String s, String t){

    int sLength = s.length(), tLength = t.length() ;
    int[] prev  = new int[t.length() +1];

    for(int row = 1 ; row <= s.length() ; row++){
      int[] curr = new int[t.length() + 1];
      for(int col = 1 ; col <= t.length(); col++){
        if(s.charAt(row - 1) == t.charAt(col - 1)){

          curr[col] = 1 + prev[col - 1];


        }
        else {

          curr[col] = Math.max(prev[col], curr[col - 1]);
        }
      }
      prev = curr;
    }

    return prev[tLength];
  }
}
