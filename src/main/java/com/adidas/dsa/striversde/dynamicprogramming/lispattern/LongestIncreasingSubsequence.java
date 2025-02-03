package com.adidas.dsa.striversde.dynamicprogramming.lispattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem statement
 * For a given array with N elements, you need to find the length of the longest subsequence from the array such that
 * all the elements of the subsequence are sorted in strictly increasing order.
 *
 * Strictly Increasing Sequence is when each term in the sequence is larger than the preceding term.
 *
 * For example:
 * [1, 2, 3, 4] is a strictly increasing array, while [2, 1, 4, 3] is not.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input :
 * 6
 * 5 4 11 1 16 8
 * Sample Output 1 :
 * 3
 * Explanation of Sample Input 1:
 * Length of longest subsequence is 3 i.e. [5, 11, 16] or [4, 11, 16].
 * Sample Input 2:
 * 3
 * 1 2 2
 * Sample Output 2 :
 * 2
 *
 *
 * We are tasked to find the length of the longest increasing subsequence
 *
 * let's say we are given an array
 *
 * [10, 9, 2, 5, 3, 7, 101, 18]
 *
 * one of the longest increasing subsequence is
 *
 * 2, 3, 7, 18
 *
 * so we should print len = 4
 *
 * so the way we are doing is we need two variables one to track the currentIndex (represented by cI) other one
 * tracked by prev = to keep track of the previous value because we are comparing with the previous value to understand
 * if the current value can be taken or not
 *
 * also here if(prev == -1 || nums[prev] < nums[cI]) we need to make prev == -1 or nums[prev] < nums[cI]
 *
 * the reason we took -1 because at the first step there is no previous we would have no nums[prev] so that reason
 *
 */
public class LongestIncreasingSubsequence {
  public static int longestIncreasingSubsequence(int arr[]) {
    int n = arr.length;
    int dp[][] = new int[n + 2][n + 2];
    for(int[] row : dp)
      Arrays.fill(row, -1);
    //helperRightShift(1, 0, arr.length, arr, dp);
    return tabulation(arr);
  }
  private static int helper(int cI, int prev, int n, int[] nums, int[][] dp){
    if(cI >= n) return 0;
    if(dp[cI][prev] != -1) return dp[cI][prev];
    int notPick = helper(cI + 1, prev, n, nums, dp), pick = 0;
    if(prev == -1 || nums[prev] < nums[cI])
      pick = 1 + helper(cI + 1, cI, n, nums, dp);
    return dp[cI][prev] = Math.max(notPick, pick);
  }

  /**
   *
   * since -1 cannot be stored in dp we have to perform the index shift
   *
   * -1 -> 0
   * 0 -> 1
   * .
   * .
   * .
   */

  private static int helperRightShift(int cI, int prev, int n, int[] nums, int[][] dp){
    if(cI >= n + 1) return 0;
    if(dp[cI][prev] != -1) return dp[cI][prev];
    int notPick = helperRightShift(cI + 1, prev, n, nums, dp), pick = 0;
    if(prev == 0 || nums[prev - 1] < nums[cI - 1])
      pick = 1 + helperRightShift(cI + 1, cI, n, nums, dp);
    return dp[cI][prev] = Math.max(notPick, pick);
  }

  /**
   *
   * this is not the most optimal solution as here we are given the length of the array 10^5 so here the loop runs till
   * cI * prev, so this is giving Runtime error
   *
   */
  private static int tabulation(int[] nums){
    int n = nums.length;
    int[][] dp = new int[n + 2][n + 2];
    for(int cI = n ; cI >= 1; cI--){
      for(int prev = cI - 1; prev >= 0; prev--){
        int notPick = dp[cI + 1][prev], pick = 0;
        if(prev == 0 || nums[prev - 1] < nums[cI - 1])
          pick = 1 + dp[cI + 1][cI];
        dp[cI][prev] = Math.max(notPick, pick);
      }
    }

    return dp[1][0];
  }

  /**
   *  tabulation second approach
   *
   *  okay the idea for this approach is little different
   *
   *  we are initially setting the dp array with size = 1
   *
   *  here dp[cI] means the longest increasing subsequence till currentIndex (cI)
   *
   *  so what we are doing is initially filling the dp tables with 1 for all entries since longest increasing subsequence
   *  is 1 when we take a currentElement
   *
   *  now what we are doing is for every element 'prev' till 'cI' we are checking if nums[prev] < nums[cI]
   *  if the currentElement(cI) greater than previousElement(prev) and if dp[prev] + 1 > dp[cI], that means if we consider
   *  element at 'prev' idx we are seeing if we add the dp[prev] + 1 to the dp[cI] if we see the
   *  dp[prev] + 1 > dp[cI] then we add dp[cI] = 1 + dp[prev]
   *
   *  [5, 1, 2, 3] nums
   *
   *  [0, 1, 2, 3] idx
   *  [1, 1, 1, 1] dp
   *
   *  at cI = 1, prev = 0
   *
   *  we are seeing nums[prev] > nums[cI]
   *  so we are not adding
   *
   *  again at cI = 2
   *  prev = 0, 1
   *
   *    at prev = 0,
   *      we are seeing nums[prev] > nums[cI]
   *      so we are not adding
   *
   *   at prev = 1,
   *      we are seeing nums[prev] < nums[cI] -> 1 < 2 and
   *      dp[prev -> 1] + 1 = (1 + 1) < dp[cI -> 2]
   *      so we are adding
   *
   *      dp = [1, 1, 2, 1] -> currently and maxValue = 2
   *
   */
  private static int tabulationDifferentApproach(int[] nums) {
    int n = nums.length;
    int[] dp = new int[n];

    Arrays.fill(dp, 1);

    int lis = 1;

    for (int cI = 0; cI < n; cI++) {
      for (int prev = 0; prev < cI; prev++) {
        if (nums[prev] < nums[cI] && dp[prev] + 1 > dp[cI]) {

          dp[cI] = 1 + dp[prev];

        }
      }

      lis = Math.max(lis, dp[cI]);
    }
    return lis;
  }

  /**
   * the binary search idea for this question is given we are having this list again
   *
   * 5, 7, 1, 2, 3, 4
   * we are iterating through the list and taking each element creating a lis
   * lis [5, 7]
   *
   * at element 1 what we are doing is we are not applying the lowerbound (SearchInsertPosition approach) to add the specific
   * element in the lis using binary search this helps because we are not interested in printing the lis instead we are interested
   * in finding the length of the lis
   *
   * so when we get 1
   *
   * ideally we should have created a new list
   *
   * lis1 -> 5, 7
   *
   * lis2 -> 1
   *
   * since we are simply interested in finding the length of the lis
   *
   * we are replacing the 5 with 1
   *
   * giving us :
   *
   * lis: 1, 7
   *
   * at 2, 3 ,4
   *
   * we get
   *
   * 1, 2, 3, 4
   *
   * length
   *
   * if(lis.size() > 0 && lis.get(lis.size() - 1) < num)
   *         lis.add(num);
   *       else if(index == lis.size())
   *         lis.add(num);
   *       else
   *         lis.set(index, num);
   *
   * so here are the conditions if we see we get a number during the lis
   * let's say we get 3, and our list contains 1, 2
   *
   * 1,2
   *
   * we see the last element is already less than the current number we simply append
   *
   * rest else cases are simply to check if the index == lis.size() let's say we have [] we get 0 (position to insert the first element
   *
   * we add the number
   *
   */

  private static int binarySearchWay(int[] nums) {
    List<Integer> lis = new ArrayList<>();
    int maxLength = 0;
    for(int num : nums){
      int index = findValUsingBs(lis, num);
      if(lis.size() > 0 && lis.get(lis.size() - 1) < num)
        lis.add(num);
      else if(index == lis.size())
        lis.add(num);
      else
        lis.set(index, num);
      maxLength = Math.max(maxLength, lis.size());

    }
    return maxLength;
  }
  private static int findValUsingBs(List<Integer> list, int val){
    int low = 0, ans = list.size(), high = ans - 1;

    while(low <= high){
      int mid = low + (high - low)/2;
      if(list.get(mid) >= val){
        ans = mid;
        high = mid - 1;
      }
      else
        low = mid + 1;
    }
    return ans ;
  }

  public static void main(String[] args){
    LongestIncreasingSubsequence.binarySearchWay(new int[]{1, 2, 3, 4});
  }
}
