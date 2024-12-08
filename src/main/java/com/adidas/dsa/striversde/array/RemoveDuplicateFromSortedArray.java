package com.adidas.dsa.striversde.array;

public class RemoveDuplicateFromSortedArray {
  /**
   * * Approach : We take 2 pointer, leftPointer and the rightPointer both pointing to zero and
   * we are doing incrementing the rightPointer always but if there are non-duplicates we increment the leftPointer
   * and at the leftPointer we get the next duplicate pointed by the right Pointer
   *
   * Time Complexity: O(N)
   *
   * Space Complexity: O(1)
   */
  public int removeDuplicates(int[] nums) {
    int leftPointer = 0, rightPointer = 0;
    while(rightPointer < nums.length){
      if(nums[leftPointer] != nums[rightPointer]){
        leftPointer++;
        nums[leftPointer] = nums[rightPointer];
      }
      rightPointer++;
    }
    return leftPointer + 1;
  }
}
