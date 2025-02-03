package com.adidas.dsa.striversde.dynamicprogramming.lcspattern;

/**
 * Problem statement
 * You are given two strings, 'str1' and 'str2'. You have to find the length of the longest common substring.
 *
 *
 *
 * A substring is a continuous segment of a string. For example, "bcd" is a substring of "abcd", while "acd" or "cda" are not.
 *
 *
 *
 * Example:
 * Input: ‘str1’ = “abcjklp” , ‘str2’ = “acjkp”.
 *
 * Output: 3
 *
 * Explanation:  The longest common substring between ‘str1’ and ‘str2’ is “cjk”, of length 3.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * wasdijkl
 * wsdjkl
 * Sample Output 1:
 *  3
 * Explanation Of Sample Input 1 :
 *  The longest common substring is “jkl”, of length 3.
 * Sample Input 2:
 * tyfg
 * cvbnuty
 * Sample Output 2:
 * 2
 * Explanation Of Sample Input 2 :
 * The longest common substring is “ty”, of length 2.
 * Expected time complexity:
 * The expected time complexity is O(n*m),
 * Where ‘n’ and ‘m’ are the lengths of ‘st1’ and ‘str2’ respectively.
 * Constraints:
 * 1 <= str1.length <= 1000
 * 1 <= str2.length <= 1000
 *
 * same approach as the longestCommonSubsequence question
 *
 * let's try to see for String abcd, abzd
 *           a  b  z  d
 *        0, 1, 2, 3, 4
 *   0  [ 0  0  0  0  0]
 * a 1  [ 0  1  0  0  0]
 * b 2  [ 0  0  2  0  0]
 * c 3  [ 0  0  0  0  0]
 * d 4  [ 0  0  0  0  0]
 *
 * if we look at the approaches for this question while we filling up the matrices from row = 1, col = 1,
 * 1-based (shifting of indexing by 1) we see this if match we add 1 and see for the row - 1, col - 1, and add the value
 *
 * dp[row][col] = 1 + dp[row - 1][col - 1]
 * now we take the max(dp[row][col])
 *
 * if not match let's say when row = 2, col = 1, previously in the lcsubsequence approach we took the
 * max(lcs[row][col - 1], lcs[row - 1][col]) here since it is substring we make it zero
 *
 *
 *
 */
public class LongestCommonSubstring {


  public static int lcs(String str1, String str2){
    int n = str1.length(), m = str2.length(), longestCommonSubString = 0;
    int[][] lcs = new int[n + 1][m + 1];

    for(int row = 1; row <= n ; row++){
      for(int col = 1; col <= m ; col++){
        if(str1.charAt(row - 1) == str2.charAt(col - 1)){
          lcs[row][col] = 1 + lcs[row - 1][col - 1];
          longestCommonSubString = Math.max(longestCommonSubString, lcs[row][col]);
        }
        else lcs[row][col] = 0;
      }
    }

    return longestCommonSubString;

  }

  public static int lcsSpaceOptimized(String str1, String str2){
    int n = str1.length(), m = str2.length(), longestCommonSubString = 0;
    int[] prev  = new int[m + 1];


    for(int row = 1; row <= n ; row++){
      int[] curr = new int[m + 1];
      for(int col = 1; col <= m ; col++){
        if(str1.charAt(row - 1) == str2.charAt(col - 1)){
          curr[col] = 1 + prev[col - 1];
          longestCommonSubString = Math.max(longestCommonSubString, curr[col]);
        }
        else curr[col] = 0;
      }
      prev = curr;
    }

    return longestCommonSubString;
  }

  public static void main(String[] args){
    LongestCommonSubstring.lcs("cbdabedcb", "abbce");
  }
}
