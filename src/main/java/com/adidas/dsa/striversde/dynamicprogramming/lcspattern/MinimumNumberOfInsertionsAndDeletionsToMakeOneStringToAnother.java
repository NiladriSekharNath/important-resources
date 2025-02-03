package com.adidas.dsa.striversde.dynamicprogramming.lcspattern;

/**
 * You are given 2 non-empty strings 's1' and 's2' consisting of lowercase English alphabets only.
 *
 *
 *
 * In one operation you can do either of the following on 's1':
 *
 * (1) Remove a character from any position in 's1'.
 *
 * (2) Add a character to any position in 's1'.
 *
 *
 *
 * Find the minimum number of operations required to convert string 's1' into 's2'.
 *
 *
 *
 * Example:
 * Input: 's1' = "abcd", 's2' = "anc"
 *
 * Output: 3
 *
 * Explanation:
 * Here, 's1' = "abcd", 's2' = "anc".
 * In one operation remove 's1[3]', after this operation 's1' becomes "abc".
 * In the second operation remove 's1[1]', after this operation 's1' becomes "ac".
 * In the third operation add 'n' in 's1[1]', after this operation 's1' becomes "anc".
 *
 * Hence, the minimum operations required will be 3. It can be shown that there's no way to convert s1 into s2 in less than 3 moves.
 *
 *
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * aaa
 * aa
 *
 *
 * Expected Answer :
 * 1
 *
 *
 * Output on console :
 * 1
 *
 *
 * Explanation For Sample Output 1:
 * For this test case,
 * 's1' = "aaa", 's2' = "aa"
 *
 * In one operation remove 's1[2]', after this operation 's1' becomes "aa".
 *
 * Hence, the output will be 1.
 *
 *
 * Sample Input 2 :
 * edl
 * xcqja
 * Expected Answer :
 * 8
 *
 *
 * Output on console :
 * 8
 *
 *
 * Expected Time Complexity :
 * Try to do this in O(n*m), where n is the length of string 's1' and 'm' is the length of string 's2'.
 *
 *
 * Constraints:
 * 1 <= s1.length, s2.length <= 100
 *
 * Time limit: 1 sec
 *
 * Let's say we have two strings given to us s1 -> abcd, s2 -> anc, now we are asked to convert s1 -> s2
 *
 * what is the max way we can do is we remove(delete) all characters from s1 -> a,b,c,d and add back a,n,c
 *
 * on doing this we get 4 + 3 (7 insertions) which is the maximum value possible
 *
 * now if we do this let's think : abcd -> anc, the minimum way we can do is if we think about it to minimize this the
 * best way we can do is keep the common part intact for this is 'ac' which is nothing but the lcs(s1,s2)
 *
 * now what we can do is remove from, s1 - lcs(s1, s2) + s2 - lcs(s1, s2)
 *
 * so we got the value as
 *
 * min Insertions/deletions :
 *
 * s1 - lcs(s1, s2) + s2 - lcs(s1, s2)
 */
public class MinimumNumberOfInsertionsAndDeletionsToMakeOneStringToAnother {
  public static int canYouMake(String s1, String s2) {
    int lcsValue = lcs(s1, s2);
    return s1.length() - lcsValue + s2.length() - lcsValue;
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
