package com.adidas.dsa.striversde.binarysearch;

/**
 * Problem Given an array in which every element is appearing twice except one, and we are tasked to find the element that is occuring once
 * using Time : O(log(n)) and Space O(1)
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,3,3,4,4,8,8]
 * Output: 2
 * Example 2:
 *
 * Input: nums = [3,3,7,7,10,11,11]
 * Output: 10
 *
 * Solution Approach :
 *
 * Let's take this example:
 *
 *             Left Half       |       Right Half
 * Index : [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
 * Array : [1, 1, 2, 2, 3, 3, 4, 5, 5, 7, 7,  8,  8]
 *
 * After careful observation above :
 *
 * we are dividing the entire array into 2 halves, the element that appears once, to the left of it we are saying as the left half and the other portion
 * right half
 *
 * For left Half ----------------------------------------------------------------------------------------------
 *
 * we can see that for a number, if the index = positive -> 1st Occurrence is even and the next occurrence is odd
 *
 * let's say for 1 -> first occurrence at 0(odd), second occurrence at 1(even)
 *
 * For Right half --------------------------------------------------------------------------------------------
 *
 * for the if we take any number, let's say 7, the first occurrence is odd index 9 and second occurrence even
 *
 * so we need to find the target element, hence we are trimming our search space, because ideally our number if it's in the right half
 * we need to move left and if we are in the left half we need to move right
 *
 */
public class SingleNonDuplicate {
  public int singleNonDuplicate(int[] nums) {

    /**
     *
     * Here we are handling the cases separately because if we incorporate it below in the binary search we would have to write a lot of
     * if statements to handle the edge cases to avoid ArrayIndexOutOfBounds exception on left or right
     *
     * 1. here we are handling the case like [1] we are returning the 0th element
     * 2. we are handling the case [1, 2, 2] for first element
     * 3. we are handling the case [2, 2, 3] for the last element
     *
     * We are doing this to make the code readable and cleaner
     *
     */

    int size = nums.length ;
    if(size == 1) return nums[0];
    if(nums[0] != nums[1]) return nums[0];
    if(nums[size - 2] != nums[size - 1]) return nums[size-1];

    /**
     * Having done that we are now interested in finding the low and high, low = 1 and high = n - 2 (second last Element)
     */

    int low = 1, high = size - 2 ;

    while(low <= high){

      int mid = low + (high - low) / 2;

      if(nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1])
        return nums[mid];

      else if((mid % 2 == 1 && nums[mid - 1] == nums[mid]) || (mid % 2 == 0 && nums[mid] == nums[mid + 1]))
        low = mid + 1;
      else
        high = mid - 1;

    }

    return -1;


  }
}
