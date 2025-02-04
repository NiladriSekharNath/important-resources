package com.adidas.dsa.striversde.dynamicprogramming.mcm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an
 * array nums. You are asked to burst all the balloons.
 *
 * If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
 * If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
 *
 * Return the maximum coins you can collect by bursting the balloons wisely.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,1,5,8]
 * Output: 167
 * Explanation:
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * Example 2:
 *
 * Input: nums = [1,5]
 * Output: 10
 *
 *
 * Solution:
 *
 * First, we will try to solve the problem using the technique we have learned in MCM.
 * In the MCM, we selected the matrices sequentially so that the number of scalar multiplication is minimized.
 * Similarly, here we can maintain an order where we will first try to choose the first element,
 * then we can try to find the second one, and so on.
 *
 * Now, let’s understand if we can really solve the problem using the above approach:
 *
 * Let’s consider the following example:
 *
 * We are given an array: {b1, b2, b3, b4, b5, b6}.
 * Each element of the given array is representing a balloon.
 *
 * Now, if we burst the b4, we will get a total of (b3*b4*b5) coins.
 *
 * After bursting b4, we are left with the left sub-problem {b1, b2, b3} and the right sub-problem {b5, b6} to solve.
 *
 *
 * Now, the question is, if we can say that the final answer will be the summation of the current number of coins
 * and the answers from the left and right subproblems. The answer is No.
 *
 * Let’s understand the reason behind this. Imagine, at first we burst the balloon b4.
 *
 * Then, we are left with the array:  {b1, b2, b3, b5, b6}.
 *
 * Now, if we try to burst b3, it will be dependent on b5.
 *
 * Similarly, if we try to burst b5, it will be dependent on b3.
 *
 * Similarly, we can observe the same dependency in the case of other elements as well.
 *
 * So, we cannot solve the subproblems {b1, b2, b3} and {b4, b5} independently as they are dependent on each other.
 *
 * Intuition:
 *
 * Until now, we have clearly understood that we cannot solve this problem using this approach.
 * So, we will just try to think in the opposite way.
 *
 * This is the key point: ----> each small subproblem has to individually give us the correct answer without being
 * dependent on other parts;
 *
 * First, we tried to find out a balloon that we will burst first.
 * But now, we will first try to find that balloon which we will burst last.
 *
 * Note: The intuition is to first find the last balloon, then the second last, and so on.
 * This is the sequence we need to follow to solve this problem.
 *
 * Now, let’s understand how the subproblems are independent in this approach:
 *
 * Let’s consider the following example:
 *
 * We are given an array: 1, {b1, b2, b3, b4, b5, b6}, 1.(Adding 1 before and at the end to make our life easy)
 *
 * Assume, b4 be the last balloon we will burst.
 *
 * Then we can surely say, the total no. of coins we can get by bursting the balloon b4 is (1*b4*
 *
 * Now, we get two subproblems as usual: {b1, b2, b3} and {b5, b6},
 *
 * and while choosing the second last balloon,
 *
 * we can ensure that b4 exists while bursting the second last balloon.
 *
 * If the second last balloon belongs to the 1st sub-problem i.e. {b1, b2, b3},
 *
 * it will be only dependent on the last balloon i.e. b4 as the rightmost element will be b4.
 *
 *
 *
 * Similarly,  if the second last balloon belongs to the 2nd sub-problem i.e. {b5, b6},
 * it will also be dependent only on the last balloon i.e. b4 as the leftmost element will be b4.
 *
 *
 * meaning in the second last step we would have (b1, b4) or (b2, b4) or (b3, b4), or (b4, b5) or (b5, b6)
 *
 * considering a smaller example with 3 balloons:
 *
 * 1, [b1, b2, b3], 1 ---> after bursting b1
 *
 * 1, [b2 b3], 1  ---> after bursting b3 [second last step]
 *
 * 1, [b2], 1  [last step]
 *
 *
 * so at the last step when we burst at idx -> we add (nums[low - 1] * nums[start], nums[high + 1]
 *
 * Now, we can clearly observe the subproblems are no anymore dependent on each other.
 *
 * We have found the right approach until now. Now, let us quickly revise the rules to solve a problem on partition dp.
 *
 *
 * Start with the entire block/array and mark it with i, j.
 * Try all partitions.
 * Return the best possible answer of the two partitions
 * (the answer that comes after dividing the problem into two subproblems and solving them recursively).
 * Now let us go through these rules and apply them to this problem.
 *
 * Marking the array with i, j:
 *
 * We are given an array of balloons of size N.
 * The entire array basically represents the range of the balloons. So, we will place i and j at both ends of the array.
 *
 *
 * Try all partitions:
 *
 * As we have figured out the logic for marking the i, and j pointers,
 * we will move to the partitioning loop. We can simply write a for loop(say ind) starting from i to j,
 * The problem is being broken in the following manner:
 *
 *
 * Note: Here f(i, ind-1) is the left sub-problem, and f(ind+1, j) is the right sub-problem.
 *
 * Base Case: We can say that when i > j this is not a valid partition and so we will return 0.
 *
 * Return the best possible answer:
 *
 * Here, in this problem, we are trying to achieve the maximum possible answer i.e.
 *
 * the maximum number of coins. So, among all the costs calculated, we will just store the maximum one.
 *
 * And finally, the maximum cost will be our answer.
 *
 *
 *
 */
public class BurstBalloons {
  public int maxCoins(int[] nums) {
    int n = nums.length;
    List<Integer> allBalloons = new ArrayList<>();
    allBalloons.add(1);
    for(int num : nums) allBalloons.add(num);
    allBalloons.add(1);
    int[][] dp = new int[n + 1][n + 1];
    for(int[] row : dp)
      Arrays.fill(row, -1);
    return helper(1, n, allBalloons, dp);
  }
  private int helper(int low, int high, List<Integer> allBalloons, int[][] dp){
    if(low > high) return 0;
    if(dp[low][high] != -1) return dp[low][high];
    int maxCost = 0;
    for(int start = low ; start <= high ; start++){
      int left = helper(low, start - 1, allBalloons, dp), right = helper(start + 1, high, allBalloons, dp);
      int currCost = allBalloons.get(low - 1) * allBalloons.get(start) * allBalloons.get(high + 1);
      maxCost = Math.max(left + right + currCost, maxCost);
    }

    return dp[low][high] = maxCost;
  }

  /**
   *
   * top down -> f(i, j)
   * i -> 1, n   bottoms up => n -> 1
   * j -> n -> 1              1 -> n
   *
   */

  public int tabulation(int n, List<Integer> allBalloons){

    /**
     * also just one point here we added n + 2 because
     * see at when col = n,  col + 1 we are okay for allBalloons since we added an extra 1 however
     * for the dp array when we try since "start" (moves from row to col) when col = n, and we try to access
     * dp[start + 1][col] for col = n, we are accessing dp[n + 1][n] which will give array index out of bounds so we do
     * n + 2
     */
    int[][] dp = new int[n + 2][n + 2];
    for(int row = n; row >= 1; row--){
      for(int col = 1 ; col <= n ; col++){
        if(row > col) continue;
        int maxCost = 0;
        for(int start = row ; start <= col ; start++){
          int left = dp[row][start - 1], right = dp[start + 1][col];
          int currCost = allBalloons.get(row - 1) * allBalloons.get(start) * allBalloons.get(col + 1);
          maxCost = Math.max(left + right + currCost, maxCost);
        }

        dp[row][col] = maxCost;
      }
    }

    return dp[1][n];
  }
}
