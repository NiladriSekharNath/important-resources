package com.adidas.dsa.striversde.dynamicprogramming.lcspattern;

/**
 * You are given two strings ‘s1’ and ‘s2’.
 *
 *
 *
 * Return the longest common subsequence of these strings.
 *
 *
 *
 * If there’s no such string, return an empty string. If there are multiple possible answers, return any such string.
 *
 *
 *
 * Note:
 * Longest common subsequence of string ‘s1’ and ‘s2’ is the longest subsequence of ‘s1’ that is also a subsequence of ‘s2’. A ‘subsequence’ of ‘s1’ is a string that can be formed by deleting one or more (possibly zero) characters from ‘s1’.
 *
 *
 * Example:
 * Input: ‘s1’  = “abcab”, ‘s2’ = “cbab”
 *
 * Output: “bab”
 *
 * Explanation:
 * “bab” is one valid longest subsequence present in both strings ‘s1’ , ‘s2’.
 *
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * 5 6
 * ababa
 * cbbcad
 *
 *
 * Expected Answer:
 * "bba"
 *
 *
 * Output on console:
 * 1
 *
 *
 * Explanation of sample output 1:
 * “bba” is only possible longest subsequence present in both s1 = “ababa” and s2 = “cbbcad”. '1' is printed if the returned string is equal to "bba".
 *
 * Sample Input 2:
 * 3 3
 * xyz
 * abc
 *
 *
 * Expected Answer:
 * ""
 *
 *
 * Output on console:
 * 1
 *
 *
 * Explanation of sample output 2:
 * There’s no subsequence of ‘s1’ that is also present in ‘s2’. Thus an empty string is returned and '1' is printed.
 *
 * Expected Time Complexity:
 * Try to solve this in O(n*m). Where ‘n’ is the length of ‘s1’ and ‘m’ is the length of ‘s2’.
 *
 *
 * Constraints:
 * 1 <= n, m <= 10^3
 *
 * Time Limit: 1 sec
 *
 * we are trying to find print one of the longest common subsequence between two words
 *
 * idea is after generating the longest common subsequence we are moving to bottom right corner
 *
 * once there what we do is we see if there is a common character for that position (actually position - 1, as 1 based indexing)
 * and see when there is a common we add the character and move diagonally top (from row, col to row - 1, col - 1)
 * otherwise we check if there is no match we see either left side (row, col - 1) is greater or top side (row - 1, col)
 * whichever side is greater we move there (either col-- or row-- respectively). we continue till either row or col is
 * equal to zero which represents -1 position since we are placing
 * dp[1][1] value at dp[2][2]
 */
public class LongestCommonSubsequencePrinting {
  public static String findLCS(int n, int m, String s1, String s2) {
    int[][] lcsTable = getLcs(s1, s2);
    int row = n, col = m;
    StringBuilder lcs = new StringBuilder();
    while (row > 0 && col > 0) {
      if (s1.charAt(row - 1) == s2.charAt(col - 1)) {
        lcs.insert(0, s1.charAt(row - 1));
        row--;
        col--;
      } else if (lcsTable[row][col - 1] > lcsTable[row - 1][col]) {
        col--;
      } else row--;
    }

    return lcs.toString();
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

  public static void main(String[] args){
    System.out.println(LongestCommonSubsequencePrinting.findLCS(5, 5, "brute", "groot"));
  }
}
