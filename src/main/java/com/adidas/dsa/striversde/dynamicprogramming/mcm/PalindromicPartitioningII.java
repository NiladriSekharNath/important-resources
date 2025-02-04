package com.adidas.dsa.striversde.dynamicprogramming.mcm;

import java.util.Arrays;

/**
 * Problem statement
 * You are given a string 'str' of length 'n'.
 *
 *
 *
 * Find the minimum number of partitions in the string so that no partition is empty and every partitioned substring is a palindrome.
 *
 *
 *
 * Example :
 * Input: 'str' = "aaccb"
 *
 * Output: 2
 *
 * Explanation: We can make a valid partition like aa | cc | b.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * aaccb
 *
 *
 * Sample Output 1 :
 * 2
 *
 *
 * Explanation of sample input 1 :
 * We can make a valid partition like aa | cc | b.
 *
 *
 * Sample Input 2 :
 * ababa
 *
 *
 * Sample Output 2 :
 * 0
 *
 *
 * Explanation of sample input 2 :
 * The string is already a palindrome, so we need not make any partition.
 *
 *
 * Sample Input 3:
 * aababa
 *
 *
 * Sample Output 3:
 * 1
 *
 *
 * Expected time complexity :
 * You can submit a solution of time complexity O(n ^ 3) but also try to write the solution having complexity O(n ^ 2).
 *
 *
 * Constraints :
 * 1 <= 'n' <= 100
 *
 * Time limit: 1 second
 *
 *
 * Approach for this problem is :
 *
 * given to us a String "aabbcc" find the min number of partitions required to make substrings are palindromic
 *
 * so for the above we get that 2 -> aa, bb, cc so 2 partitions -> aa|bb|cc 2 partitions
 *
 * if we see the above problem
 *
 * aabbcc always a answer is possible for us like
 *
 * a|a|b|b|c|c
 *
 * 5 partitions at max
 *
 * aabbcc total length - 1 -> 6 - 1 = 5
 *
 * we are required to find the min partitions
 *
 * Approach to this problem is
 *
 * we are trying a concept called front partitioning
 *
 * f(aabbcc)
 *
 * a| f(abbcc) = 1 + restPart cost
 * aa | f(bbcc) = 1 + restPart cost
 *
 * aab | f(bcc) => this call does not happen because 'aab' is not a palindrome
 *
 * so above if we see we are partitioning at every index and trying if there is a palindrome then we are adding and finding the
 * lowest cost
 *
 * same logic is Recursion/PalindromicPartitioning
 *
 * also finally in the answer
 * what happens is we are again partitioning at the last index
 * aa|bb|cc| <- here +1 is added at the end
 *
 * so finally we return by decrementing -1 from our answer
 *
 * if we see we need only one index:
 *
 * f(cI)
 * start -> cI to n
 * each function call -> checkPalindrome(cI, start)
 *    if palindrome -> cost = 1 + f(start + 1) (rest part)
 *
 */
public class PalindromicPartitioningII {
  public static int palindromePartitioning(String str) {
    int length = str.length();
    int[] dp = new int[length];
    Arrays.fill(dp, -1);
    return helper(0, str, dp) - 1 ;
  }
  private static int helper(int cI, String str, int[] dp){
    if(cI == str.length()) return 0;
    if(dp[cI] != -1) return dp[cI];
    int minPart = +(int)1e9;
    for(int start = cI ; start < str.length() ; start++){

      if(isPalindrome(cI, start, str)){
        minPart = Math.min(minPart, 1 + helper(start + 1, str, dp));
      }
    }

    return dp[cI] = minPart;
  }

  /**
   *
   * top down -> 0 -> n
   * bottoms up -> n - 0
   */
  private static int tabulation(String str){
    int n = str.length();

    int[] dp = new int[n];

    for(int cI = n - 1; cI >= 0; cI--){
      int minPart = +(int)1e9;
      for(int start = cI ; start < str.length() ; start++){

        if(isPalindrome(cI, start, str)){
          minPart = Math.min(minPart, 1 + dp[start + 1]);
        }
      }

      dp[cI] = minPart;
    }

    return dp[0] - 1;
  }

  private static boolean isPalindrome(int start, int end, String str){
    if(str.length() == 0) return true;


    int left = start, right = end;

    while(left <= right){
      if(str.charAt(left) != str.charAt(right)) return false;
      left++;
      right--;
    }
    return true;
  }
}
