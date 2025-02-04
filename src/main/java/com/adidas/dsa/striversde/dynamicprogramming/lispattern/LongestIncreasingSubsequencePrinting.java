package com.adidas.dsa.striversde.dynamicprogramming.lispattern;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
public class LongestIncreasingSubsequencePrinting {
  public static List< Integer > printingLongestIncreasingSubsequence(int []arr, int x) {
    List<Integer> lis = new ArrayList<>();
    helper(0, -1, x, new ArrayList<>(), lis, arr);
    return lis;

  }

  private static void helper(int cI, int prev, int n, List<Integer> currentList, List<Integer> resultList, int[] nums){

    if(cI == n) {
      if(resultList.size() < currentList.size()){
        resultList.clear();
        resultList.addAll(currentList);
      }
      return;

    }

    helper(cI + 1, prev, n, currentList, resultList, nums);


    if(prev == -1 || nums[prev] < nums[cI]){
      currentList.add(nums[cI]);
      helper(cI + 1, cI, n, currentList, resultList, nums);
      currentList.remove(currentList.size() - 1);

    }

  }

  public static List<Integer> printingLongestIncreasingSubsequenceII(int[] arr, int x) {
    List<Integer> lis = new ArrayList<>();
    Map<String, List<Integer>> dp = new HashMap<>();
    helperII(0, -1, x, new ArrayList<>(), lis, arr, dp);

    return lis;
  }

  private static List<Integer> helperII(int cI, int prev, int n, List<Integer> currentList, List<Integer> resultList, int[] nums,
                                        Map<String, List<Integer>> dp) {
    String key = String.format("%s_%s", cI, prev);

    // ? Use memoized result if it exists


    if (cI == n) {
      if (resultList.size() < currentList.size()) {
        resultList.clear();
        resultList.addAll(currentList);
        dp.put(key, new ArrayList<>(resultList));
      }
      //  Store a copy instead of modifying `resultList`

      return currentList;
    }

    if (dp.containsKey(key)) {
      return dp.get(key);
    }

    // Case 1: Skip current element
    helperII(cI + 1, prev, n, currentList, resultList, nums, dp);

    // Case 2: Take current element if it's valid


    if (prev == -1 || nums[prev] < nums[cI]) {
      currentList.add(nums[cI]);
      helperII(cI + 1, cI, n, currentList, resultList, nums, dp);
      currentList.remove(currentList.size() - 1);
    }

    return dp.get(key);
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
   *  [15, 4, 12, 13, 3] nums
   *
   *  [0, 1, 2,  3,  4] idx
   *  [1, 1, 1,  1,  1] dp
   *  [0, 1, 2,  3,  4] prevValue (initially we are setting as same value as the index, prevValue[cI] = cI)
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
   *      so we are not adding and not setting the prevIndex
   *
   *   at prev = 1,
   *      we are seeing nums[prev] < nums[cI] -> 1 < 2 and
   *      dp[prev -> 1] + 1 = (1 + 1) < dp[cI -> 2]
   *      and also setting the prevIndex [cI = 2] = 1
   *
   *
   *      so we are adding
   *
   *      dp = [1, 1, 2, 1] -> currently and maxValue = 2
   *      prevInd -> [0, 1, 1,  2,  4]
   *
   *      here this approach will help us find the "lisList"
   *      we are doing keeping a prevValue array to store the prevIndex in the list
   *
   *      so we keep on doing this also while storing the lisValue we are keeping track of the last index where we
   *      find the longest increasing subsequence
   *
   *      if(dp[cI] > lis) {
   *         lis = dp[cI];
   *         lastIndex = cI;
   *       }
   *
   *
   * then from here we build the longest increasing subsequence
   *      we finally get for this array
   *
   *      [15, 4, 12, 13, 3]
   *
   *      [0, 1,  1,  2, 1]
   *
   *      from here we are doing the same logic going in reverse
   *
   *      while(prevVal[lastIndex] != lastIndex){
   *       lisList.add(nums[lastIndex]);
   *       lastIndex = prevVal[lastIndex];
   *      }
   *
   *      lisList.add(nums[lastIndex]);
   *
   *      Collections.reverse(lisList);
   *
   *      adding to the list, till the prevValue[lastIndex] != lastIndex ( for lastIndex = 1)
   *      this is when the loop breaks and we add the final value 4
   *
   *      and we finally reverse the value to get the longestIncreasingSubsequence
   *
   *      [4, 12, 13]
   *
   */
  private static List<Integer> tabulationDifferentApproach(int[] nums) {
    List<Integer> lisList = new ArrayList<>();

    int n = nums.length, lastIndex = 0;
    int[] dp = new int[n], prevVal = new int[n];

    for(int cI = 0; cI < n ; cI++){
      prevVal[cI] = cI;
    }

    Arrays.fill(dp, 1);

    int lis = 1;

    for (int cI = 0; cI < n; cI++) {

      for (int prev = 0; prev < cI; prev++) {
        if (nums[prev] < nums[cI] && dp[prev] + 1 > dp[cI]) {

          dp[cI] = 1 + dp[prev];
          prevVal[cI] = prev;
        }
      }
      if(dp[cI] > lis) {
        lis = dp[cI];
        lastIndex = cI;
      }
    }

    while(prevVal[lastIndex] != lastIndex){
      lisList.add(nums[lastIndex]);
      lastIndex = prevVal[lastIndex];
    }

    lisList.add(nums[lastIndex]);

    Collections.reverse(lisList);



    return lisList;
  }

  public static void main(String[] args){
    log.info("ResutList: {} ", LongestIncreasingSubsequencePrinting.printingLongestIncreasingSubsequenceII(new int[]{5, 1, 2, 3}, 4));
  }
}
