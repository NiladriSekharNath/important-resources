package com.adidas.dsa.striversde.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem statement
 * You are given an array/list of ‘N’ integers. You are supposed to return the maximum sum of the subsequence with the
 * constraint that no two elements are adjacent in the given array/list.
 * <p>
 * Note:
 * A subsequence of an array/list is obtained by deleting some number of elements (can be zero) from the array/list,
 * leaving the remaining elements in their original order.
 * <p>
 * Sample Input 1:
 * 2
 * 3
 * 1 2 4
 * 4
 * 2 1 4 9
 * Sample Output 1:
 * 5
 * 11
 * Explanation to Sample Output 1:
 * In test case 1, the sum of 'ARR[0]' & 'ARR[2]' is 5 which is greater than 'ARR[1]' which is 2 so the answer is 5.
 * <p>
 * In test case 2, the sum of 'ARR[0]' and 'ARR[2]' is 6, the sum of 'ARR[1]' and 'ARR[3]' is 10, and the sum of 'ARR[0]'
 * and 'ARR[3]' is 11. So if we take the sum of 'ARR[0]' and 'ARR[3]', it will give the maximum sum of sequence in which
 * no elements are adjacent in the given array/list.
 * Sample Input 2:
 * 2
 * 5
 * 1 2 3 5 4
 * 9
 * 1 2 3 1 3 5 8 1 9
 * Sample Output 2:
 * 8
 * 24
 * Explanation to Sample Output 2:
 * In test case 1, out of all the possibilities, if we take the sum of 'ARR[0]', 'ARR[2]' and 'ARR[4]', i.e. 8,
 * it will give the maximum sum of sequence in which no elements are adjacent in the given array/list.
 * <p>
 * In test case 2, out of all the possibilities, if we take the sum of 'ARR[0]', 'ARR[2]', 'ARR[4]', 'ARR[6]' and
 * 'ARR[8]', i.e. 24 so, it will give the maximum sum of sequence in which no elements are adjacent in the given array/list.
 */
public class HouseRobberI {


  public static int maximumNonAdjacentSum(List<Integer> nums) {
    int n = nums.size(), dp[] = new int[nums.size()];
    Arrays.fill(dp, -1);

    //return helper(nums, n - 1, dp);

    //return solutionWithTabulation(nums, nums.size());

    return solutionWithSpaceOptimization(nums, nums.size());
  }

  /**
   * recursion with memoization solution
   */
  private static int helper(List<Integer> nums, int cI, int[] dp) {
    if (cI == 0) return nums.get(0);
    if (cI < 0) return 0;
    if (dp[cI] != -1) return dp[cI];

    /**
     * Basically we are choosing values as in if we choose a current Value we are adding the current value at that index
     * and the next index would be cI - 2 since we are not allowed to pick up the next adjacent index, if we choose this
     * index so we choose the (cI - 2), otherwise,
     *
     * if we are not choosing the index then we would add 0 value (adding no value) and skip the index, since we are
     * not picking up the currentIndex we are free to pick up the next index (cI - 1)
     */

    int pickChoice = nums.get(cI) + helper(nums, cI - 2, dp);
    int notPickChoice = 0 + helper(nums, cI - 1, dp);

    return dp[cI] = Math.max(pickChoice, notPickChoice);
  }

  /**
   *
   * Tabulation is bottom's up solution we can think of this as bottom to up solution,
   *
   * bottom means from the base case and up means to the end so from baseCase to the end
   */
  private static int solutionWithTabulation(List<Integer> nums, int n) {

    int dp[] = new int[n];

    Arrays.fill(dp, -1);

    dp[0] = nums.get(0);

    for (int cI = 1; cI < n; cI++) {
      int pickChoice = 0, notPickChoice = 0;


      pickChoice = nums.get(cI) +  (cI - 2 >= 0 ? dp[cI - 2] : 0);

      notPickChoice = dp[cI - 1];
      dp[cI] = Math.max(pickChoice, notPickChoice);
    }

    return dp[n - 1];

  }

  /**
   * The same problem but now with SpaceOptimization
   *
   * in order to write this let's think here for a moment
   *
   * since we are concerned with dp[cI - 2] and dp[cI - 1], we don't need the entire array, to visualize this
   * we just need the last two which is enough for us
   *
   *
   *   ... dp[cI - 4]   dp[cI - 3]   dp[cI - 2]   dp[cI - 1]   dp[cI]
   *           val4        val3         val2          val1       val0
   *
   * Now in the above let's call dp[cI - 2] -> secondPrevious, dp[cI - 1] -> previous, dp[cI] -> current
   *
   * At first iteration: This should look like :
   *
   * and after calculating the current, we update current secondPrevious = previous, first and then previous = current
   *
   *  ... dp[cI - 4]   dp[cI - 3]   dp[cI - 2]   dp[cI - 1]   dp[cI]
   *         val4        val3         val2          val1       val0
   *                              secondPrevious  previous     current
   *                                          secondPrevious  previous   current
   *
   *   ... dp[cI - 4]   dp[cI - 3]   dp[cI - 2]   dp[cI - 1]   dp[cI]
   *         val4        val3         val2          val1       val0
   *                                         secondPrevious  previous   current
   *
   */
  private static int solutionWithSpaceOptimization(List<Integer> nums, int n){

    int secondPrevious = 0 , previous = nums.get(0), current = 0;

    for (int cI = 1; cI < n; cI++) {
      int pickChoice = 0, notPickChoice = 0;


      pickChoice = nums.get(cI) + (cI - 2 >= 0 ? secondPrevious : 0);

      notPickChoice = previous ;

      current = Math.max(pickChoice, notPickChoice);

      secondPrevious = previous;

      previous = current;

    }

    return previous;
  }

  public static void main(String[] args){
   // new HouseRobberI().maximumNonAdjacentSum(Arrays.asList(4, 8));

    new HouseRobberI().maximumNonAdjacentSum(Arrays.asList(5, 6, 6));
  }

}
