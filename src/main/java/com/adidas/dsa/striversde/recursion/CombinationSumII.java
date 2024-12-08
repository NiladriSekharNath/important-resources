package com.adidas.dsa.striversde.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * the main highlight of this approach :
 * Problem statement: we are tasked to find the combination that adds up to the target the only catch being we are not allowed to
 * consider duplicate entries like for this example [1, 2, 2, 2, 3, 3, 3, 4] for target 2,(index = 1) 2(index = 2) , 3 for 7 and we are not allowed to take some other 2(index = 2), 2(index = 3), 3
 *
 *
 * so the best approach(or pattern) is we need to sort the array and skip the taking the duplicate entries (take only the first one)
 *
 * Time Complexity:O(2^n*k)
 *
 * Reason: Assume if all the elements in the array are unique then the no. of subsequence you will get will be O(2^n). we also add the ds to our ans when we reach the base case that will take “k”//average space for the ds.
 *
 * Space Complexity:O(k*x)
 *
 * Reason: if we have x combinations then space will be x*k where k is the average length of the combination.
 *
 *
 *
 */
public class CombinationSumII {

  public List<List<Integer>> combinationSum2(int[] candidates, int target) {
    Arrays.sort(candidates);
    return helper(candidates, target, new ArrayList<>(), 0);
  }

  private List<List<Integer>> helper(int[] nums, int targetSum, List<Integer> currentList, int currentIndex) {
    List<List<Integer>> resultList = new ArrayList<>();

    if(targetSum == 0 )
    {resultList.add(new ArrayList<>(currentList));

      return resultList; }

    for(int start = currentIndex; start< nums.length ; start++){

      if(nums[start] <= targetSum){
        if(start > currentIndex && nums[start - 1] == nums[start]) continue;

        currentList.add(nums[start]);
        resultList.addAll(helper(nums, targetSum - nums[start], currentList, start + 1));
        currentList.remove(currentList.size() - 1);
      }
    }

    return resultList;


  }
}
