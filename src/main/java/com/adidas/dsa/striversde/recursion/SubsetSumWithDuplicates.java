package com.adidas.dsa.striversde.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Approach : we are sorting the nums array we are taking the indices that are greater than currentIndex
 * but the previous index value == currentIndex value we are simply skipping that iteration since we don't want duplicates as the previous one is already considered
 *
 * like in 1,2,2 in  f(index, currentList) f(0 []),  for i = 1 we have already considered nums[1] = 2 and added to the list and call the next function f(1, [2])
 * and we don't need i = 2 in f(0 []) as we have selected already in the first iteration
 *
 * so we need to call let's say in this array:  1, 2, 2, 3, 3, 3, if we have f(0 []) if we try for index  = 3, nums[3] = 3 so the next function
 * call would f(4 []) and not f(1 []).
 *
 *
 * Time Complexity: O(2^n) for generating every subset and O(k)  to insert every subset in another data structure if the average length of every subset is k. Overall O(k * 2^n).
 *
 * Space Complexity: O(2^n * k) to store every subset of average length k. Auxiliary space is O(n)  if n is the depth of the recursion tree.
 *
 */
public class SubsetSumWithDuplicates {
  public List<List<Integer>> subsetsWithDup(int[] nums) {
    Arrays.sort(nums);
    return helper(nums, 0, new ArrayList<>());

  }

  private List<List<Integer>> helper(int[] nums, int currentIndex, List<Integer> currentList){
    List<List<Integer>> resultList = new ArrayList<>();

    resultList.add(new ArrayList<>(currentList));

    for(int i = currentIndex; i < nums.length; i++){
      if(i > currentIndex && nums[i-1] == nums[i]) continue;
      currentList.add(nums[i]);
      resultList.addAll(helper(nums, currentIndex + 1, currentList));
      currentList.remove(currentList.size() -1) ;

    }
    return resultList;
  }

  public static void main(String args[]){
    new SubsetSumWithDuplicates().subsetsWithDup(new int[]{1,2,2});
  }
}
