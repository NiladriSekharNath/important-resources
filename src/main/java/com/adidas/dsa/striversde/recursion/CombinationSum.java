package com.adidas.dsa.striversde.recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Approach : we are doing the approach of picking up one number and not picking up a number
 * <p>
 * now if we have a number that is greater than the target sum we are not picking up the number,
 * <p>
 * and same number we can pick up any number of times.
 * <p>
 * <p>
 * <p>
 * Time Complexity: O(2^t * k) where t is the target, k is the average length
 * <p>
 * Reason: Assume if you were not allowed to pick a single element multiple times, every element will have a couple of options: pick or not pick which is 2^n different recursion calls, also assuming that the average length of every combination generated is k. (to put length k data structure into another data structure)
 * <p>
 * Why not (2^n) but (2^t) (where n is the size of an array)?
 * <p>
 * Assume that there is 1 and the target you want to reach is 10 so 10 times you can “pick or not pick” an element.
 * <p>
 * Space Complexity: O(k*x), k is the average length and x is the no. of combinations
 */
public class CombinationSum {


  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    return helper(candidates, target, 0, new ArrayList<>());
  }

  private List<List<Integer>> helper(int[] arr, int target, int curr, List<Integer> currentList) {
    List<List<Integer>> resultList = new ArrayList<>();

    if (curr >= arr.length) {

      if (target == 0) {
        resultList.add(new ArrayList<>(currentList));
      }

      return resultList;
    }

    if (arr[curr] <= target) {

      currentList.add(arr[curr]);

      resultList.addAll(helper(arr, target - arr[curr], curr, currentList));

      currentList.remove(currentList.size() - 1);

    }

    resultList.addAll(helper(arr, target, curr + 1, currentList));

    return resultList;
  }

  /**
   * the idea of this approach is finding other way to solve the problem not by using the taking/non-taking approach
   *
   * here the idea is we are trying out all combinations of numbers any number of times since same number can be used more than once
   *
   * and working our way till we have all the combinations
   *
   * Also the reason we took here "resultList.addAll(helper(arr, target - arr[start], start , currentList));" the current Iteration start and not the currentIndex because we don't want
   * to use the same index again and end up repeating our sequence everytime
   *
   *
   * Time complexity
   *
   *
   *
   */

  public List<List<Integer>> combinationSumWithLoop(int[] candidates, int target) {
    return helperWithLoop(candidates, target, 0, new ArrayList<>());
  }

  private List<List<Integer>> helperWithLoop(int[] arr, int target, int curr, List<Integer> currentList) {
    List<List<Integer>> resultList = new ArrayList<>();



    if (target == 0) {
      resultList.add(new ArrayList<>(currentList));
      return resultList;
    }


    for (int start = curr; start < arr.length; start++){



      if(arr[start] <= target){

        currentList.add(arr[start]);

        resultList.addAll(helperWithLoop(arr, target - arr[start], start , currentList));

        currentList.remove(currentList.size() - 1);

      }
    }


    return resultList;
  }

  public static void main(String[] args) {
    new CombinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7);
    new CombinationSum().combinationSum(new int[]{2, 7}, 5);
  }
}
