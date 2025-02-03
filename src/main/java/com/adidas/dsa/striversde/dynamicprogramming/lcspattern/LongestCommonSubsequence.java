package com.adidas.dsa.striversde.dynamicprogramming.lcspattern;

import java.util.Arrays;

/**
 * Problem statement
 * Given two strings, 'S' and 'T' with lengths 'M' and 'N', find the length of the 'Longest Common Subsequence'.
 *
 * For a string 'str'(per se) of length K, the subsequences are the strings containing
 * characters in the same relative order as they are present in 'str,' but not necessarily contiguous.
 * Subsequences contain all the strings of length varying from 0 to K.
 *
 * Example :
 * Subsequences of string "abc" are:  ""(empty string), a, b, c, ab, bc, ac, abc.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 0 <= M <= 10 ^ 3
 * 0 <= N <= 10 ^ 3
 *
 * Time Limit: 1 sec
 * Sample Input 1 :
 * adebc
 * dcadb
 * Sample Output 1 :
 * 3
 * Explanation of the Sample Output 1 :
 * Both the strings contain a common subsequence 'adb', which is the longest common subsequence with length 3.
 * Sample Input 2 :
 * ab
 * defg
 * Sample Output 2 :
 * 0
 * Explanation of the Sample Output 2 :
 * The only subsequence that is common to both the given strings is an empty string("") of length 0.
 *
 *
 * Now we are tasked to find the longest common subsequence of two strings
 *
 * adebc, dcadb
 *
 * so the longest common subsequence is 3 "adb" now there is a point here
 *
 * Recursion Approach:
 *
 * If at a particular index we find matching characters then we increment the lcs by  1
 * and decrement the indices of the both the pointers by 1
 *
 * but if we don't find any matching characters
 *
 * Math.max(f(cIS - 1, cIT), f(cIS, cIT - 1))
 *
 * let's say we are at last index for these two strings,
 * s = abc, t = ab -> cIS = 2, cIT = 1
 * decrementing cIS by 1 -> ( cIS - 1) so after decrementing in the function call we get the
 * value now comparing (ab, ab)
 *
 *
 */
public class LongestCommonSubsequence {

  public static int lcs(String s, String t) {
    /*int sLength = s.length(), tLength = t.length();
    int[][] dp = new int[sLength + 1][tLength + 1];

    for(int[] row : dp){
      Arrays.fill(row, -1);
    }
    return helper(s, sLength - 1, t, tLength - 1, dp);*/

    //return tabulationApproach(s, t);


    //return tabulationSpaceOptimized(s, t);
    int sLength = s.length(), tLength = t.length();
    int[][] dp = new int[sLength + 1][tLength + 1];

    for(int[] row : dp){
      Arrays.fill(row, -1);
    }
    return helper2ShiftingIndexRight(s, sLength, t, tLength, dp);



  }

  /**
   *
   * Here if you see the indexes are less than the 0 then we return zero now obviously if
   * we need to adjust this we can't because in the dp because we cannot store -1
   * hence in the follow up tabulation Approach and the SpaceOptimization Approach
   * we are using ternary to prevent the out of bounds exception
   */

  private static int helper(String s, int cIS, String t, int cIT, int[][] dp){

    if(cIS < 0 || cIT < 0) return 0;

    if(dp[cIS][cIT] != -1) return dp[cIS][cIT];

    if(s.charAt(cIS) == t.charAt(cIT)){
      return dp[cIS][cIT] = 1 + helper(s, cIS - 1, t, cIT - 1, dp);
    }

    return dp[cIS][cIT] = Math.max(helper(s, cIS - 1, t, cIT, dp), helper(s, cIS, t, cIT - 1, dp));
  }

  /**
   *
   * Here we performed a neat trick for these approach
   *
   * To convert the memoization approach to a tabulation one, create a dp array with the same size as done in memoization.
   *
   * Initialization: Shifting of indexes
   * In the recursive logic, we set the base case to if(ind1<0 || ind2<0) but we can’t set the dp array’s index to -1.
   * Therefore a hack for this issue is to shift every index by 1 towards the right.
   *
   *  -1, 0, 1,..., n
   *   0, 1, ...  , n + 1
   *
   *
   * Therefore, now the base case will be if(ind1==0 || ind2==0).
   * Similarly, we will implement the recursive code by keeping in mind the shifting of indexes,
   * therefore S1[ind1] will be converted to S1[ind1-1]. Same for others.
   * At last we will print dp[N][M] as our answer.
   *
   * the case we did is logically we are storing dp[1][1], result in the dp[2][2]
   * -1 -> 0 (-1 treated as 0)
   * 0 -> 1 (0 treated as 1)
   *
   * so for the cIS - 1 and cIT - 1 are storing in cIS and cIT respectively
   *
   * the results, this helps us and clean the code as well and in the tabulation since we started from 1 because
   * 0 represents 1 so we are trying that approach
   *
    *
   */

  private static int helper2ShiftingIndexRight(String s, int cIS, String t, int cIT, int[][] dp){

    if(cIS == 0 || cIT == 0) return 0;

    if(dp[cIS][cIT] != -1) return dp[cIS][cIT];

    if(s.charAt(cIS - 1) == t.charAt(cIT - 1)){
      return dp[cIS][cIT] = 1 + helper2ShiftingIndexRight(s, cIS - 1, t, cIT - 1, dp);
    }

    return dp[cIS][cIT] = Math.max(helper2ShiftingIndexRight(s, cIS - 1, t, cIT, dp), helper2ShiftingIndexRight(s, cIS, t, cIT - 1, dp));
  }

  private static int helper2ShiftingIndexRightTabulation(String s, String t){

    int sLength = s.length(), tLength = t.length() ;
    int[][] dp = new int[s.length() + 1][t.length() +1];

    for(int row = 1 ; row <= s.length() ; row++){
      for(int col = 1 ; col <= t.length(); col++){
        if(s.charAt(row - 1) == t.charAt(col - 1)){

          dp[row][col] = 1 + dp[row - 1][col - 1];


        }
        else {

          dp[row][col] = Math.max( dp[row - 1][col], dp[row][col - 1]);
        }
      }
    }

    return dp[sLength][tLength];
  }

  private static int helper2ShiftingIndexRightTabulationSpaceOptimized(String s, String t){

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


  private static int tabulationApproach(String s, String t){
    int sLength = s.length(), tLength = t.length() ;
    int[][] dp = new int[s.length() + 1][t.length() +1];

    for(int row = 0 ; row < s.length() ; row++){
      for(int col = 0 ; col < t.length(); col++){
        if(s.charAt(row) == t.charAt(col)){

          dp[row][col] = 1 ;

          if(row > 0 && col > 0)
            dp[row][col] += dp[row - 1][col - 1];
        }
        else {

          dp[row][col] = Math.max(row > 0 ? dp[row - 1][col] : 0,
              col > 0 ? dp[row][col - 1] : 0);
        }
      }
    }

    return dp[sLength - 1][tLength - 1];
  }


  private static int tabulationSpaceOptimized(String s, String t){
    int sLength = s.length(), tLength = t.length() ;
    int[] prev = new int[t.length() + 1];

    for(int row = 0 ; row < s.length() ; row++){

      int [] curr = new int[t.length() + 1];

      for(int col = 0 ; col < t.length(); col++){
        if(s.charAt(row) == t.charAt(col)){

          curr[col] = 1 ;

          if(row > 0 && col > 0)
            curr[col] += prev[col - 1];
        }
        else {

          curr[col] = Math.max(row > 0 ? prev[col] : 0,
              col > 0 ? curr[col - 1] : 0);
        }
      }
      prev = curr;
    }

    return prev[tLength - 1];
  }
}
