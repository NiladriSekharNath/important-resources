package com.adidas.dsa.striversde.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The main approach here we are doing is in order to generate all permutations of nums we would have to
 *
 * take each element and when we try and take each element we put the taken elements in a set and add them there
 *
 * and remove them once they are considered
 *
 * Time Complexity:  N! x N
 *
 * Space Complexity:  O(N)(recursion) + O(N) extra spce to store the elements in Map or Set or anything
 */
public class PrintAllPermutationsStringOrArray {
  public List<List<Integer>> permute(int[] nums) {
    return helper(nums, new ArrayList<>(), new HashSet<>());
  }

  private List<List<Integer>> helper(int[] nums, List<Integer> currentList, Set<Integer> currentSet) {
    List<List<Integer>> resultList = new ArrayList<>();
    if (currentSet.size() == nums.length) {
      resultList.add(new ArrayList<>(currentList));
      return resultList;
    }

    for (int start = 0; start < nums.length; start++) {
      if (!currentSet.contains(start)) {
        currentSet.add(start);
        currentList.add(nums[start]);
        resultList.addAll(helper(nums, currentList, currentSet));
        currentSet.remove(start);
        currentList.remove(currentList.size() - 1);
      }
    }

    return resultList;
  }

  /**
   * the more elegant solution to this we are doing is in-place swapping in order to permute this we are using swapping
   *
   * we have nums [1, 2, 3]
   *
   * first we do this :
   *
   * we take an entry :
   *
   * from the currentPosition we perform swap as shown in this Pic "com/adidas/dsa/striversde/recursion/permutation/recusivediagram/img.png"
   *
   * swap [1, 2, 3 ]
   *
   * swap 1 <-> 1 and then call next index f(1) perform (2 <-> 2) swap and then f(2) on next index (3 <-> 3) swap once this is done we understand this position
   * is the final position for 3 or the last value we get the value as "(1 -> 2 -> 3) "
   * and then recurse up in the now we perform, at the f(2) next iteration swap ( 2 -> 3) to call f(2) on next index 2 we understand for 2 that is the last position
   * so we get "(1 -> 3 -> 2)"
   *
   *
   * Time Complexity: O(N! X N)
   *
   * Space Complexity: O(1) (In place no extra space apart from recursion depth)
   */

  public List<List<Integer>> permuteWithOutExtraSpace(int[] nums) {
    return helperWithoutExtraSpace(nums, 0);
  }

  private List<List<Integer>> helperWithoutExtraSpace(int[] nums, int currentIndex){
    List<List<Integer>> resultSet = new ArrayList<>();

    if(currentIndex >= nums.length){
      List < Integer > ds = new ArrayList < > ();
      for (int i = 0; i < nums.length; i++) {
        ds.add(nums[i]);
      }
      resultSet.add(new ArrayList < > (ds));
      return resultSet;
    }

    for(int start = currentIndex; start < nums.length  ; start++){
      swap(nums, start, currentIndex );
      resultSet.addAll(helperWithoutExtraSpace(nums, currentIndex + 1));
      swap(nums, start, currentIndex);

    }

    return resultSet;

  }

  private void swap(int[] nums, int posA, int posB){
    int temp = nums[posA];
    nums[posA] = nums[posB];
    nums[posB] = temp;
  }

  public static void main(String args []) {
    new PrintAllPermutationsStringOrArray().permuteWithOutExtraSpace(new int[]{1, 2, 3});
  }
}
