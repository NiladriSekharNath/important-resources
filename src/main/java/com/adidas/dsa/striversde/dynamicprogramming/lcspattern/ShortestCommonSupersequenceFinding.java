package com.adidas.dsa.striversde.dynamicprogramming.lcspattern;

/**
 * Given two strings, ‘A’ and ‘B’. Return the shortest supersequence string ‘S’, containing both ‘A’ and ‘B’ as its subsequences.
 * If there are multiple answers, return any of them.
 *
 * Note: A string 's' is a subsequence of string 't' if deleting some number of characters from 't'
 * (possibly 0) results in the string 's'.
 *
 * For example:
 * Suppose ‘A’ = “brute”, and ‘B’ = “groot”
 *
 * The shortest supersequence will be “bgruoote”. As shown below, it contains both ‘A’ and ‘B’ as subsequences.
 *
 * A   A A     A A
 * b g r u o o t e
 *   B B   B B B
 *
 * It can be proved that the length of supersequence for this input cannot be less than 8. So the output will be bgruoote.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 2
 * brute
 * groot
 * bleed
 * blue
 * Sample Output 1 :
 * bgruoote
 * bleued
 * Explanation For Sample Input 1 :
 * For First Case - Same as explained in above example.
 *
 * For the second case -
 *
 * ‘A’ = “bleed”, and ‘B’ = “blue”
 *
 * The shortest supersequence will be “bleued”. As shown below, it contains both ‘A’ and ‘B’ as subsequences.
 *
 * A A A   A A
 * b l e u e d
 * B B   B B
 *
 * It can be proved that the length of supersequence for this input cannot be less than 6. So the output will be bleued.
 * Sample Input 2 :
 * 2
 * coding
 * ninjas
 * blinding
 * lights
 * Sample Output 2 :
 * codningjas
 * blindinghts
 *
 *
 * This question is asked to print the shortest common supersequence
 *
 * s1 -> brute, s2 -> groot
 *
 * scs -> bgruoote
 *
 * the point for this question is we cannot print it in any order
 *
 * both should use this, if we see bleued contains bleed and blue in the same order
 *
 * idea is if we need to find the length of the common supersequence we can do this
 *
 * s1 + s2 -> brutegroot this is a supersequence however this is not the shortest
 *
 * in order to find the length of the shortest common supersequence we can do this
 *
 * s1 len(s1) = n
 * s2 len(s2) = m
 *
 * we can make -> n + m - lcs(s1, s2),
 * the point is we are not taking both the lcs of s1 and s2 twice we are taking it once
 *
 * so we are modifying the lcs_printing code
 *
 * *           g  r  o  o  t
 *          0, 1, 2, 3, 4  5
 *     0  [ 0  0  0  0  0  0]
 *   b 1  [ 0  0  0  0  0  0]
 *   r 2  [ 0  0  1  1  1  1]
 *   u 3  [ 0  0  1  1  1  1]
 *   t 4  [ 0  0  1  1  1  2]
 *   e 5  [ 0  0  1  1  1  2]
 *
 *
 *   so again modifying the code in longestCommonSubsequence printing starting from the bottom right corner
 *   row = s1.length() and col = s2.length();
 *
 *   scs = ""
 *
 *   what we are doing is if we find the s1.charAt(row - 1) == s2.charAt(col - 1)
 *   we take either of row, col and add to scs = ""
 *
 *   at row = 5 col = 5
 *
 *   we see e != t
 *   dp[row - 1][col] > dp[row][col - 1]
 *
 *   so naturally we move up
 *   row--
 *
 *   but before that we do this since we are moving up to row = 4, col = 5
 *   we are forming -> brut and groot, so since we moved up we are adding 'e' to our scs
 *
 *   scs = e (scs.append(s1.charAt( row - 1)) row--
 *
 *   similarly we do the same for when dp[row][col - 1] > dp[row][col]
 *
 *   we remove from 'col' so before doing col-- we add the -> scs + = s2.charAt(col - 1)
 *
 *
 *   ONCE AT THE COMPLETION
 *   either row  == 0 or col == 0 (represented by -1, rightShifted 1 - based indexing)
 *   so we iterate the unfinished one
 *
 *   while(row > 0){
 *       revScs.append(s1.charAt(row - 1));
 *       row--;
 *     }
 *     while(col > 0){
 *       revScs.append(s2.charAt(col - 1));
 *       col--;
 *     }
 *
 *     and lastly reverse the word to get the -> shortestCommonSupersequence
 *
 *
 *
 *
 *
 */

public class ShortestCommonSupersequenceFinding {
  public static String shortestSupersequence(String s1, String s2) {

    int[][] lcsTable = getLcs(s1, s2);
    int row = s1.length(), col = s2.length();
    StringBuilder revScs = new StringBuilder();

    while (row > 0 && col > 0) {
      if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
        revScs.append(s1.charAt(row - 1));
        row--;
        col--;
      } else if (lcsTable[row][col - 1] > lcsTable[row - 1][col]) {
        revScs.append(s2.charAt(col - 1));
        col--;
      } else {
        revScs.append(s1.charAt(row - 1));
        row--;
      }

    }

    while(row > 0){
      revScs.append(s1.charAt(row - 1));
      row--;
    }
    while(col > 0){
      revScs.append(s2.charAt(col - 1));
      col--;
    }

    return revScs.reverse().toString();


  }


  private static int[][] getLcs(String s, String t) {

    int sLength = s.length(), tLength = t.length();
    int[][] dp = new int[s.length() + 1][t.length() + 1];

    for (int row = 1; row <= s.length(); row++) {
      for (int col = 1; col <= t.length(); col++) {
        if (s.charAt(row - 1) == t.charAt(col - 1)) {

          dp[row][col] = 1;

          if (row > 0 && col > 0)
            dp[row][col] += dp[row - 1][col - 1];
        } else {

          dp[row][col] = Math.max(row > 0 ? dp[row - 1][col] : 0,
              col > 0 ? dp[row][col - 1] : 0);
        }
      }
    }

    return dp;
  }

  public static void main(String[] args) {
    ShortestCommonSupersequenceFinding.shortestSupersequence("brute", "groot");
  }
}
