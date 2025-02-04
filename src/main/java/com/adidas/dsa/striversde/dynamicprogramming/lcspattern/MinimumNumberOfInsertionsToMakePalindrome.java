package com.adidas.dsa.striversde.dynamicprogramming.lcspattern;

/**
 * Problem statement
 * A palindrome string is one that reads the same backward as well as forward.
 *
 *
 *
 * You are given a string 'str'.
 *
 *
 *
 * Find the minimum number of characters needed to insert in 'str' to make it a palindromic string.
 *
 *
 *
 * Example :
 * Input: 'str' = "abca"
 *
 * Output: 1
 *
 * Explanation:
 * If we insert the character ‘b’ after ‘c’, we get the string "abcba", which is a palindromic string.
 * Please note that there are also other ways possible.
 *
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * abca
 *
 *
 * Sample Output 1:
 * 1
 *
 *
 * Explanation for input 1:
 * If we insert the character ‘b’ after ‘c’, we get the string "abcba", which is a palindromic string.
 * Please note that there are also other ways possible.
 *
 *
 * Sample Input 2:
 * abcdefg
 *
 *
 * Sample Output 2:
 * 6
 *
 *
 * Sample Input 3:
 * aaaaa
 *
 *
 * Sample Output 3:
 * 0
 *
 *
 * Expected time complexity :
 * The expected time complexity is O(|str| ^ 2).
 *
 *
 * Constraints:
 * 1 <= |str| <= 1000
 *
 * Where |str| represents the length of the string 'str'.
 *
 * Time Limit: 1 sec.
 *
 * MinimumNumberOfInsertionsToMakePalindrome
 *
 * Given a string we need to find the minimum number of insertions to make a String 'str', palindrome is simply
 *
 * str.length() - longestPalindromicSubsequence(str)
 *
 * which is,
 * str.length() - lcs(str, reverse(str))
 */
public class MinimumNumberOfInsertionsToMakePalindrome {
  public static int minInsertion(String str) {
    return str.length() - lcs(str, new StringBuilder(str).reverse().toString());
  }

  private static int lcs(String s, String t){

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
