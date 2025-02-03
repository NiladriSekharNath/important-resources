package com.adidas.dsa.striversde.dynamicprogramming.strings;

/**
 * You are given two strings 'S' and 'T' of lengths 'N' and 'M' respectively. Find the "Edit Distance" between the strings.
 *
 * Edit Distance of two strings is the minimum number of steps required to make one string equal to the other.
 * In order to do so, you can perform the following three operations:
 *
 * 1. Delete a character
 * 2. Replace a character with another one
 * 3. Insert a character
 * Note:
 * Strings don't contain spaces in between.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 0 <= N <= 10 ^ 3
 * 0 <= M <= 10 ^ 3
 *
 * Time Limit : 1sec
 * Sample Input 1 :
 * abc
 * dc
 * Sample Output 1 :
 * 2
 *  Explanation For Sample Input 1 :
 * In 2 operations we can make the string T to look like string S. First, insert the character 'a' to string T, which makes it "adc".
 *
 * And secondly, replace the character 'd' of the string T with 'b' from the string S. This would make string T
 * to "abc" which is also the string S. Hence, the minimum distance.
 * Sample Input 2 :
 * whgtdwhgtdg
 * aswcfg
 * Sample Output 2 :
 * 9
 *
 *
 * so we are given to a task to convert str1 -> abc m =  3, str2 -> ab n = 2
 *
 * we can perform 3 operations
 *
 * insert/edit/delete
 *
 * now the idea for this question is if we would have to insert/edit/delete, each operation cost 1 whatever we do if there
 * is not a match
 *
 * f(3,abc, 2, ab) -> we are comparing c with b
 *
 * now for the 'insert' operation what we can say is since b doesn't match we would have to add 1
 *
 * now hypothetically we need to make b from 'ab', match but now the point for this is we need to add a 'b' in abc
 * making it abc -> 'abcb' (we literally don't add but hypothetically add that 'b' since we compared 'b' we
 * now decrement sub's pointer f(3, abc, 1, ab) == f(m, str1, n - 1, str2), we can't decrement str1's pointer 'm' because
 * we did not find a match we are simply adding an extra character so str1's pointer did not move
 *
 * insert : 1 + f(m, str1, n - 1, str2)
 *
 * delete :
 *
 *  f(3,abc, 2, ab) -> we are comparing c with b
 *  for the delete operation we are decrementing the pointer for abc by 1, meaning again hypothetically deleting c
 *  deleting 'c' means reducing abc from 3 -> 2 but 'ab' we can't move since there is no match
 *
 *  delete : 1 + f(m - 1, str1, n, str2)
 *
 * edit:
 *
 * f(3, abc, 2, ab) -> we are comparing c with b
 *
 *  for the edit operation we are matching the str1, abc's pointer currently at 'c' by pointer of str2 at 'b'
 *  since there is not a match we can do this, hypothetically edit the characters 'abc' -> 'c'  -> 'b' ( abb)
 *  since we found a match we are decrementing this time both str1, and str2 by 1
 *
 *  edit : 1 + f(m - 1, str1, n - 1, str2)
 *
 *  if there is match we would simply move to the next character without any extra cost
 *
 *  for the base case :
 *
 *  either str1 becomes < 0 or str2 becomes < 0 , either m or n becomes < 0
 *
 *  if(str1 < 0) {
 *    which means str1 = "", no characters left but str2 is not zero, let's say "abc" some character left this means we have to
 *    make abc -> 0 we can do that by inserting "a,b,c" in "abc" costing us (index position will n - 1), so
 *    for abc -> 2 (0, 1, 2), therefore the cost would be 2 + 1 (index + 1)
 *  }
 *
 *  if(str2 < 0) {
 *    which means str2 = "", no character left str1 is not zero, str1 = "abc" so we would have to remove "a, b, c"
 *    thereby adding 3 ( index + 1)
 *  }
 *
 *
 *
 *
 *
 *
 */
public class EditDistance {
  public static int editDistance(String str1, String str2) {
    return helper(str1.length() - 1, str1, str2.length() - 1, str2);
  }
  private static int helper(int m, String s1, int n, String s2){

    if(m < 0 ) return n + 1;
    if(n < 0) return m + 1;

    int minValue = (int)1e9;
    if(s1.charAt(m) == s2.charAt(n))
      minValue = Math.min(minValue, helper(m - 1, s1, n - 1, s2));

    else {


      minValue = Math.min(minValue, Math.min(1 + helper(m, s1, n - 1, s2),
          Math.min(1 + helper(m - 1, s1, n - 1, s2), 1 + helper(m - 1 , s1, n , s2))));


    }
    return minValue;
  }


  private static int helper2RightShiftedByOneIndex(int m, String s1, int n, String s2){

    /**
     * since it is 1 based indexing now since we moved all of them by 1 we would have to move the indexes by 1
     * so we can directly return the index
     *
     */
    if(m == 0) return n ;
    if(n == 0) return m;

    int minValue = +(int)1e9;
    if(s1.charAt(m - 1) == s2.charAt(n - 1))
      minValue = Math.min(minValue, helper2RightShiftedByOneIndex(m - 1, s1, n - 1, s2));

    else {


      minValue = Math.min(minValue, Math.min(1 + helper2RightShiftedByOneIndex(m, s1, n - 1, s2),
          Math.min(1 + helper2RightShiftedByOneIndex(m - 1, s1, n - 1, s2), 1 + helper2RightShiftedByOneIndex(m - 1 , s1, n , s2))));


    }
    return minValue;
  }

  private static int tabulation(String s1, String s2){
    int n = s1.length(), m = s2.length();

    int[][] dp = new int[n + 1][m + 1];

    /**
     m -> col
     n -> row

     m == 0, row 0, n
     */

    /**
     WE ARE SETTING AS ROW/COL BECAUSE IN ORDER TO CHANGE THE DISTANCE WE WOULD BE REQUIRING THE
     SAME AMOUNT OF CHANGE AND OBVOUSLY 1 BASED INDEXING
     */

    for(int row = 0; row <=  n ; row++){
      dp[row][0] = row;

    }
    /**
     we obviously could have set from 1 but since if both row/col m,n is equal to zero
     then we need 0 cost to make them equal as for empty string there is no cost
     */
    for(int col = 0; col <= m; col++){
      dp[0][col] = col;
    }

    for(int row = 1; row <= n; row++){
      for(int col = 1; col <= m; col++){
        int minValue = +(int)1e9;
        if(s1.charAt(row - 1) == s2.charAt(col - 1))
          minValue = Math.min(minValue, dp[row - 1][col - 1]);

        else {


          minValue = Math.min(minValue, Math.min(1 + dp[row][col - 1],
              Math.min(1 + dp[row - 1][col - 1], 1 + dp[row - 1][col])));


        }
        dp[row][col] = minValue;
      }
    }

    return dp[n][m];
  }
  public static void main(String[] args){
    EditDistance.editDistance("abc", "dc");
  }
}
