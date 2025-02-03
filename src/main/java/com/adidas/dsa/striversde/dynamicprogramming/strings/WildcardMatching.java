package com.adidas.dsa.striversde.dynamicprogramming.strings;

import java.util.Arrays;

/**
 * Problem statement
 * Given a text and a wildcard pattern of size N and M respectively, implement a wildcard pattern matching algorithm
 * that finds if the wildcard pattern is matched with the text. The matching should cover the entire text not partial text.
 *
 * The wildcard pattern can include the characters ‘?’ and ‘*’
 *
 *  ‘?’ – matches any single character
 *  ‘*’ – Matches any sequence of characters(sequence can be of length 0 or more)
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 100
 * 1 <= N <= 200
 * 1 <= M <= 200
 *
 * Where 'N' denotes the length of 'TEXT' and 'M' denotes the length of 'PATTERN'.
 *
 * 'TEXT' and 'PATTERN' contain only lowercase letters and patterns may contain special characters ‘*’ and ‘?’
 *
 * Time Limit: 1sec
 * Sample Input 1:
 * 3
 * ?ay
 * ray
 * ab*cd
 * abdefcd
 * ab?d
 * abcc
 * Sample Output 1:
 * True
 * True
 * False
 * Explanation of the Sample Input1:
 * Test Case 1:
 * The pattern is “?ay” and the text is ray.
 * ‘?’ can match a character so it matches with a character ‘r’ of the text and rest of the text matches with the
 * pattern so we print True.
 *
 * Test Case 2:
 * “ab” of text matches with “ab” of pattern and then ‘*’ of pattern matches with “def” of the text and “cd” of text
 * matches with “cd” of the pattern. Whole text matches with the pattern so we print True.
 *
 * Test Case 3:
 * “ab” of pattern matches with “ab” of text. ‘?’ of pattern matches with ‘c’ of the text but ‘d’ of the pattern
 * do not match with ‘c’ of the text so we print False.
 * Sample Input 1:
 * 1
 * ba*a?
 * baaabab
 * Sample Output 1:
 * True
 *
 *
 * wildCard matching case by case
 * when, matching pattern, length m with text, length n
 *
 * if the character at pattern and character at text matches || character at pattern = '?' then
 * we can move ahead and check for the next string (m - 1, n - 1)
 *
 * if the character at pattern == '*'
 *
 *
 * let's say we are checking pattern = ab*, m = 2, text = abcd, n = 3
 *
 * now we know the * meaning 0 or anything
 *
 * we are recursing like this
 *
 * case:
 * f(patt, m, txt, n) -> f(patt, m - 1, txt, n)
 *
 *  f(ab*, 2, abcd, 3) -> f(ab*, 1, abcd, 3) --> here we are saying the * means 0
 *    it's like comparing (ab*, ab) -> f(2, 1) -> f(1, 1)
 *
 * case 'can be anything' :
 * f(patt, m, txt, n) -> f(patt, m, txt, n - 1)
 *
 *  f(ab*, 2, abcd, 3) -> f(ab*, 2, abcd, 2) -> here the star means we are comparing * = d
 *
 *
 *  basecase : - >
 *
 *  basecase 1 : if(m < 0 && n < 0) return 1;
 *  basecase 2 : if(m < 0 && n >= 0) return 0;
 *  basecase 3 : if(m >= 0 && n < 0){
 *    for(int idx = 0 ; idx <= m; idx++){
 *     if(pattern.charAt(idx) != '*') return 0;
 *
 *    }
 *    return 1;
 *  }
 *
 *  basecases can be anything either pattern < 0 || text < 0
 *
 *  basecase 1: if both pattern and text < 0 we return true because both match we return zero
 *
 *  basecase 2: if pattern < 0 but text >= 0, therefore there is some text to match
 *
 *  basecase 3: if  pattern >= 0 but text < 0, it will only match empty text, if all the characters are '*'
 *     we are matching text = "", pattern = "****"
 *
 *
 */
public class WildcardMatching {
  public static boolean wildcardMatching(String pattern, String text) {
    int m = pattern.length(), n = text.length();
    int[][] dp = new int[m + 1][n + 1];
    for(int[] row : dp)
      Arrays.fill(row, -1);
    return helper(m - 1, pattern, n - 1, text, dp) == 1;
  }

  /**
   *
   *
   */
  private static int helper(int m, String pattern, int n, String text, int[][] dp){
    if(m < 0 && n < 0) return 1;
    if(m < 0 && n >= 0) return 0;
    if(m >= 0 && n < 0){
      for(int idx = 0 ; idx <= m; idx++){
        if(pattern.charAt(idx) != '*') return 0;

      }
      return 1;
    }
    if(dp[m][n] != -1) return dp[m][n];

    if(pattern.charAt(m) == text.charAt(n) || pattern.charAt(m) == '?')
      return dp[m][n] = helper(m - 1, pattern, n - 1, text, dp);
    if(pattern.charAt(m) == '*')
      return dp[m][n] = (helper(m - 1, pattern, n, text, dp) == 1 || helper(m, pattern, n - 1, text, dp) == 1)? 1 : 0;
    return dp[m][n] = 0;
  }

  /**
   *
   * since here
   * -1 -> 0
   * 0 -> 1
   *
   * so where there is >= 0 we change it to >= 1
   *
   */

  private static int helperShiftingIndexBy1(int m, String pattern, int n, String text, int[][] dp){
    if(m == 0 && n == 0) return 1;
    if(m == 0 && n >= 1) return 0;
    if(m >= 1 && n == 0){
      for(int idx = 1 ; idx <= m; idx++){
        if(pattern.charAt(idx - 1) != '*') return 0;

      }
      return 1;
    }
    if(dp[m][n] != -1) return dp[m][n];

    if(pattern.charAt(m - 1) == text.charAt(n - 1) || pattern.charAt(m - 1) == '?')
      return dp[m][n] = helperShiftingIndexBy1(m - 1, pattern, n - 1, text, dp);
    if(pattern.charAt(m - 1) == '*')
      return dp[m][n] = (helperShiftingIndexBy1(m - 1, pattern, n, text, dp) == 1 || helperShiftingIndexBy1(m, pattern, n - 1, text, dp) == 1)? 1 : 0;
    return dp[m][n] = 0;
  }

  /**
   *
   *
   *
   */
  private static boolean tabulationApproach(String pattern, String text){
    int m = pattern.length(), n = text.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    /**
     *
     * if(m == 0 && n >= 1) return 0;
     *
     */
    dp[0][0] = true;

    for(int col = 1; col <= m; col++){
      dp[0][col] = false;
    }
    /**
     * if(m >= 1 && n == 0){
     *       for(int idx = 1 ; idx <= m; idx++){
     *         if(pattern.charAt(idx - 1) != '*') return 0;
     *
     *       }
     *       return 1;
     * }
     */
    for(int row = 1; row <= m; row++){
      boolean flag = true;
      for(int idx = 1 ; idx <= row; idx++){
        if(pattern.charAt(idx - 1) != '*') {
          flag = false;
          break;
        }

      }
      dp[row][0] = flag;


    }

    for(int row = 1; row <= m; row++){
      for(int col = 1; col <= n; col++){
        if(pattern.charAt(row - 1) == text.charAt(col - 1) || pattern.charAt(row - 1) == '?')
          dp[row][col] = dp[row - 1][col - 1];
        else if(pattern.charAt(row - 1) == '*')
          dp[row][col] = dp[row - 1][col] || dp[row][col - 1];
        else
          dp[row][col] = false;
      }
    }

    return dp[m][n];
  }


  public static void main(String[] args) {
    WildcardMatching.wildcardMatching("*bc*da?*", "abccdazzz");
  }
}
