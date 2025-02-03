package com.adidas.dsa.striversde.dynamicprogramming.strings;

import java.util.Arrays;

/**
 * Problem statement
 * A Subsequence of a string is the string that is obtained by deleting 0 or more letters from the
 * string and keeping the rest of the letters in the same order.
 *
 *
 *
 * We are given two strings, 'str' and 'sub'.
 *
 *
 *
 * Find the number of subsequences of 'str' which are equal to 'sub'.
 *
 *
 *
 * Since the answer can be very large, print it modulo 10 ^ 9 + 7.
 *
 *
 *
 * Example :
 * Input: 'str' = “brootgroot” and 'sub' = “brt”
 *
 * Output: 3
 *
 * Explanation: The following subsequences formed by characters at given indices (0-based) of 'str' are equal to 'sub' :
 *
 * str[0] = ‘b’, str[1] = ‘r’, str[4] = ‘t’
 *
 * str[0] = ‘b’, str[1] = ‘r’, str[9] = ‘t’
 *
 * str[0] = ‘b’, str[6] = ‘r’, str[9] = ‘t’
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * brootgroot
 * brt
 *
 *
 * Sample Output 1 :
 * 3
 *
 *
 * Explanation For Sample Input 1 :
 * The following subsequences formed by characters at given indices (0-based) of 'str' are equal to 'sub' :
 *
 * str[0] = ‘b’, str[1] = ‘r’, str[4] = ‘t’
 *
 * str[0] = ‘b’, str[1] = ‘r’, str[9] = ‘t’
 *
 * str[0] = ‘b’, str[6] = ‘r’, str[9] = ‘t’
 *
 *
 * Sample Input 2 :
 * dingdingdingding
 * ing
 *
 *
 * Sample Output 2 :
 * 20
 *
 *
 * Sample Input 3:
 * aaaaa
 * a
 *
 *
 * Sample Output 3:
 * 5
 *
 *
 * Expected time complexity :
 * The expected time complexity is O(|str| * |sub|).
 *
 *
 * Constraints:
 * 1 <= |str| <= 1000
 * 1 <= |sub| <= 1000
 *
 * Where |str| represents the length of the string 'str', and |sub| represents the length of the string 'sub'.
 *
 * Time Limit: 1 sec.
 *
 *
 * Approach to the question : str -> brootgroot, m = 10,  sub = brt, n = 3
 *
 * we are given two strings we are trying to find the number of distinct occurrences of the sub in str
 *
 * [distinct occurrences mean any brt [0, 1, 4], [0, 1, 9], any one character can be distinct here in the str
 * there are 3 occurrences of sub.
 *
 * The idea of the problem is very straightforward, if we again go back to the pick vs notpick way
 *
 * starting from the end from both (m, n) -> (9, 2)
 * what we see is if we can either take first 1 or not take the first t  giving us
 *
 * (9,2) -> (8, 1) taking is only possible when both the values match otherwise it is not possible we have to go with the not pick case
 *          (8, 2)
 *
 *
 * giving us from above:
 *
 * (m, n) -> (m - 1, n - 1)
 *        -> (m - 1 , n)
 *
 * base case :
 *
 * if either way we can see the m < 0 or n < 0
 *
 * we have to write it in particular order:
 * firstly if (n < 0) because if 'sub' becomes zero, then there is 1 match as all the character in 'sub' gets matched,
 * again if (m < 0) ( and now the n > 0 because some characters needs to be matched but there is no string left
 *                          'str' where can we can match so we can't do anything other than to return 0
 *
 *
 */

public class DistinctSubsequences {
  public static int distinctSubsequences(String str, String sub) {
    int m = str.length() - 1, n = sub.length() - 1;
    int[][] dp = new int[m + 1][n + 1];
    for(int[] row : dp)
      Arrays.fill(row, -1);
    return helper(m, str, n, sub, dp);
  }

  private static int helper(int m, String str, int n, String sub, int[][] dp){
    if(n < 0) return 1;
    if(m < 0) return 0;
    if(dp[m][n] != -1) return dp[m][n];
    int notPick = helper(m - 1, str, n, sub, dp), pick = 0;
    if(str.charAt(m) == sub.charAt(n)) {
      pick = helper(m - 1, str, n - 1, sub, dp);
    }
    return pick + notPick;
  }

  private static int helper2RightShiftOneIndex(int m, String str, int n, String sub, int[][] dp){
    if(n == 0) return 1;
    if(m == 0) return 0;

    if(dp[m][n] != -1) return dp[m][n];
    int notPick = helper2RightShiftOneIndex(m - 1, str, n, sub, dp), pick = 0;
    if(str.charAt(m - 1) == sub.charAt(n - 1)) {
      pick = helper2RightShiftOneIndex(m - 1, str, n - 1, sub, dp);
    }
    return pick + notPick;
  }

  private static int tabulationOptimized(String str, String sub){
    int m = str.length(), n = sub.length();
    int[][] dp = new int[m + 1][n + 1];
    int mod = (int) 1e9 + 7;
    /**
     row -> m
     col -> n

     */
    for(int row = 0 ; row <= m; row++)
      dp[row][0] = 1 ;

    /**
     * // Initialize the first row (except dp[0][0]) with 0 because there's no way to form s2 from an empty string.
     *
     * because the row -> 0 col -> 0, we can form a answer = 1 match
     */
    for(int col = 1; col <= n ; col++){
      dp[0][col] = 0;
    }

    /**
     top Down
     m -> m - 1 ... 0
     n -> n - 1 ... 0

     Bottoms Up is reverse

     -1 -> 0
     0 -> 1


     */
    for(int row = 1 ; row <= m ; row++){
      for(int col = 1; col <= n ; col++){

        int notPick = dp[row - 1][col] , pick = 0;
        if(str.charAt(row - 1) == sub.charAt(col - 1)) {
          pick = dp[row - 1][col - 1];
        }
        dp[row][col] =( pick + notPick) % mod;
      }
    }

    return dp[m][n];
  }
  public static void main(String[] args){

    DistinctSubsequences.distinctSubsequences("aaaa","a");

    DistinctSubsequences.tabulationOptimized("brutegroot", "brt");
  }
}
