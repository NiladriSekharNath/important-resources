package com.adidas.dsa.striversde.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
 *
 * Notice that the solution set must not contain duplicate triplets.
 *
 * Example 1:
 *
 * Input: nums = [-1,0,1,2,-1,-4]
 * Output: [[-1,-1,2],[-1,0,1]]
 * Explanation:
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
 * The distinct triplets are [-1,0,1] and [-1,-1,2]. (We could also have [2, -1, -1] but that is duplicate so we don't need the duplicate]
 * Notice that the order of the output and the order of the triplets does not matter.
 *
 *
 *
 *
 */
public class ThreeSum {

  /**
   *
   * Approach 1 : We are taking a loop and starting from the ith pointer and all possible values after jth pointer ;
   *
   * and in order to avoid the same element from taken more than once we are doing this :
   *
   * indices = [0, 1,2,3, 4, 5]
   * nums =    [-1,0,1,2,-1,-4] at any particular i = 1 , j = 4 we are searching for the sum '-(nums[i] + nums[j])' from a set
   * that has values in the range greater than index 1 and less than index 4 meaning in the range [2, 3]
   * and refresh the set at every loop
   *
   * Also and we are sorting the triplets and storing in the Set to avoid duplicates
   *
   * Time Complexity: O(N2 * log(no. of unique triplets)), where N = size of the array.
   * Reason: Here, we are mainly using 3 nested loops. And inserting triplets into the set takes O(log(no. of unique triplets)) time complexity. But we are not considering the time complexity of sorting as we are just sorting 3 elements every time.
   *
   * Space Complexity: O(2 * no. of the unique triplets) + O(N) as we are using a set data structure and a list to store the triplets and extra O(N) for storing the array elements in another set.
   *
   *
   */
  public List<List<Integer>> threeSum(int[] nums) {
    Set<List<Integer>> resultList = new HashSet<>();
    for(int i = 0 ; i< nums.length ; i++){
      Set<Integer> canBeSelectedValues = new HashSet<>();
      for(int j = i + 1; j <nums.length; j++){
        if(canBeSelectedValues.contains(-(nums[i] + nums[j]))){
          List<Integer> tempList = new ArrayList<>(Arrays.asList(nums[i], nums[j], -(nums[i] + nums[j])));
          Collections.sort(tempList);
          resultList.add(tempList);
        }

        canBeSelectedValues.add(nums[j]);
      }
    }

    return new ArrayList<>(resultList);
  }

  /**
   *
   * Most Optimal Approach
   *
   * For this one we are sorting the input array and using two pointer approach:
   *
   * We use the two pointer approach : let's visualize using this example : (ignoring ith pointer)
   *
   * Example 1 : nums[-2, -2, -2, -1, -1, -1, 0, 0, 0, 2, 2, 2, 2]
   *
   * we take a pointer i = 0,
   *
   *    if(i > 0 && the previous [i] == current[i] (nums[i-1] == nums[i]) we continue (skip) the current iteration
   *
   *    then we take the left = i+1 and right = nums.length-1;
   *
   *    so in the inner loop we are taking the sum =  (nums[i], nums[left], nums[right])
   *    if sum < 0 we increment left pointer (left++)
   *    if sum > 0 we decrement right pointer (right--)
   *    if sum == 0
   *      if we find the sum we add the triplets in the resultant list,
   *
   *      now after this in order to not store duplicate values we are increment left pointer  and decrementing the right pointer,
   *      till the first non-duplicate value
   *
   *
   *  Time Complexity: O(NlogN)+O(N2), where N = size of the array.
   *  Reason: The pointer i, is running for approximately N times. And both the pointers j and k combined can run for approximately N times including the operation of skipping duplicates. So the total time complexity will be O(N2).
   *
   *  Space Complexity: O(no. of triplets), This space is only used to store the answer. We are not using any extra space to solve this problem. So, from that perspective, space complexity can be written as O(1).
   *
   */

  public List<List<Integer>> threeSumOptimised(int[] nums) {
    List<List<Integer>> resultList = new ArrayList<>();
    Arrays.sort(nums);
    for(int i = 0 ; i< nums.length ;i++){


      // while(i > 0 && i < nums.length && nums[i] == nums[i-1]) i++;

      if(i>0 && nums[i-1] == nums[i]) continue;

      int left = i + 1, right = nums.length - 1;
      while(left < right){
        int sum = nums[i] + nums[left] + nums[right];
        if(sum < 0) {
          left++;
        }
        else if(sum > 0){
          right--;
        }
        else {
          if(sum == 0){
            resultList.add(Arrays.asList(nums[i], nums[left], nums[right]));
            left++;
            right--;
            while(left < right && nums[left] == nums[left -1]) left++;
            while(left < right && nums[right] == nums[right + 1]) right--;
          }
        }
      }
    }

    return resultList;
  }

  public static void main(String[] args){
    new ThreeSum().threeSum(new int[]{-1, 0, 1, 2, -1, -4});

  }
}
