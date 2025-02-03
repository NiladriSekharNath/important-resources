package com.adidas.dsa.striversde.dynamicprogramming.lispattern;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem statement
 * You are given an array of distinct numbers ‘arr’ of size 'n'.
 *
 *
 *
 * Your task is to return the largest subset of numbers from ‘arr’, such that any pair of numbers ‘a’ and ‘b’
 * from the subset satisfies the following: a % b == 0 or b % a == 0.
 *
 *
 *
 * A subset is nothing but any possible combination of the original array
 *
 *
 *
 * Example:
 * Input: ‘arr’ = [1, 16, 7, 8, 4]
 *
 * Output: [1, 4, 8, 16].
 *
 * Explanation: In the set {1, 4, 8, 16}, you can take any pair from the set, and either one of the elements
 * from the pair will divide the other.
 *
 * There are other possible sets, but this is the largest one.
 * Detailed explanation  Input/output format, Notes, Images 
 * Sample Input 1:
 * 3
 * 1 2 5
 * Sample Output 1 :
 *  Correct
 * Explanation of sample input 1:
 * The sets {1, 2} or {1, 5} are the two largest sets that satisfy the given condition.
 *
 * Hence either of them could be the answer.
 * Sample Input 2:
 *  3
 *  3 3 3
 * Sample Output 2:
 * Correct
 * Explanation of sample input 2:
 * The set {3, 3, 3} is the largest set that satisfies the given condition.
 * Expected Time Complexity:
 * The expected time complexity is On^2.
 * Constraints:
 * 1 <= N <= 5000
 * 0 <= arr[i] <= 10^8
 *
 * Time Limit: 1 sec
 *
 * Approach to the question it is said that any answer is fine so we sort the answer we are trying to sort because the order
 * does not matter since the order does not matter we just need to satisfy divisibility
 *
 * so let's take the example [1, 16, 7, 8, 4]
 *
 * we sort this we get       [1, 4, 7, 8, 16]
 *
 * after getting this we are doing this part
 *
 * let's say we have few numbers 1, 4, 8 we say 4 is divisible by 1 and we say 8 is divisible by 4
 * can we say that 8 is also divisible by 1? I know it is but how can we prove that ?
 *
 * Yes, 8 is divisible by 1, and we can prove this using the **transitivity property of divisibility**.
 *
 * ### Proof:
 * Divisibility means that for two integers  a  and  b , we say b  is divisible by  a denoted as  a | b
 * if there exists an integer  k  such that:
 *
 * [
 * b = a times k
 * ]
 *
 * Now, given:
 *
 * 1.  1 | 4 , meaning there exists some integer  k_1  such that:
 *    [
 *    4 = 1 times k_1
 *    ]
 *    Here,  k_1 = 4 
 *
 * 2.  4 | 8 , meaning there exists some integer  k_2  such that:
 *    [
 *    8 = 4 times k_2
 *    ]
 *    Here,  k_2 = 2 
 *
 * Now, substituting  4 = 1 times k_1  into  8 = 4 times k_2 :
 *
 * [
 * 8 = 1 times k_1 times k_2
 * ]
 *
 * [
 * 8 = 1 times k_1 times k_2
 * ]
 *
 * Since  k_1 times k_2  is an integer, we conclude that:
 *
 * [
 * 1 | 8
 * ]
 *
 * Thus, **if  a mid b  and  b mid c , then  a mid c **, proving that divisibility is transitive.
 *
 * So, since  1 | 4  and  4 | 8 , we conclude that  1 | 8 .
 *
 * so taking the above idea into consideration we are doing this
 *
 *  1, 4, 7, 8, 16]
 *
 *  for each element we are checking if all the numbers from previous are divides the current number
 *
 * for 4 -> 4 / 1 so adding 4,
 *
 * [1, 4,
 *
 * for 7 -> 7/ 1 but 7 / 4 does not divide so we are not adding 7
 *
 * so we effectively convert the question to a variation of lis printing
 *
 * and now the same approach to add the numbers
 *
 *
 */
@Slf4j
public class LargestDivisibleSubset {
  public static List<Integer> divisibleSet(int[] arr) {

    Arrays.sort(arr) ;
    int n = arr.length, maxValue = 1, lastIndex = 0;


    /**
     * we are creating the dp array to track the lastIndex value which will be found once we get the maxsize
     *
     */

    int[] prevVal = new int[n], dp = new int[n];

    for(int cI = 0 ; cI < n ; cI++) {
      prevVal[cI] = cI;
      dp[cI] = 1;
    }

    for(int cI = 0; cI < n; cI++){
      for(int prev = 0; prev < cI; prev++){
        if(arr[cI] % arr[prev] == 0 && dp[prev] + 1 > dp[cI]) {
          dp[cI] = 1 + dp[prev];
          prevVal[cI] = prev;
        }
      }
      if(dp[cI] > maxValue){
        maxValue = dp[cI];
        lastIndex = cI;
      }
    }

    /**
     * building longest divisible subset
     *
     */

    List<Integer> longestDivSubset = new ArrayList<>();

    while(prevVal[lastIndex] != lastIndex){
      longestDivSubset.add(arr[lastIndex]);
      lastIndex = prevVal[lastIndex];
    }

    longestDivSubset.add(arr[lastIndex]);

    return longestDivSubset;

  }
}
