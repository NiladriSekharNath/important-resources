package com.adidas.dsa.striversde.binarysearch;

/**
 * Question : Given the array nums after the possible rotation and an integer target, return the index of target if it is in nums, or -1 if it is not in nums.
 *
 * You must write an algorithm with O(log n) runtime complexity.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 0
 *
 * Output: 4
 *
 * Explanation : Originally the array was [0, 1, 2, 4, 5, 6, 7] after rotation at 4 we get the array is [4, 5, 6, 7, 0, 1, 2]
 *
 * Example 2:
 *
 * Input: nums = [4,5,6,7,0,1,2], target = 3
 * Output: -1
 * Example 3:
 *
 * Input: nums = [1], target = 0
 * Output: -1
 */
public class SearchInARotatedSortedArray {
  /**
   * works but not readable code
   */
  public int search(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;
      if (nums[mid] == target) return mid;
      else if (nums[low] <= nums[mid]) {
        if (target < nums[low]) low = mid + 1;
        else if (target < nums[mid]) high = mid - 1;
        else low = mid + 1;
      } else {
        if (target > nums[mid] && target < nums[high]) low = mid + 1;
        else high = mid - 1;
      }
    }
    return -1;
  }

  /**
   * In normal binary search we know entire array is sorted for this one [0, 1, 2, 4, 5, 6, 7] and we need to find a target let's say 6
   * we find mid if nums[mid] < 6 we eliminate the left half and search in the right half
   * but here is a catch :
   *
   * This elimination of one half would not work in the rotated array case
   *
   * now back to our example :
   *
   * index =       [0,1,2,3,4,5,6]
   * given array = [4,5,6,7,0,1,2]
   *
   * if we see here mid = 3, the left half [low,mid = (0,3)] is sorted
   * but right half 7, [mid,high (3,6)] is not sorted
   *
   * So here we need to identify the sorted halves
   *
   * because of rotation, if left half is sorted then right half is not sorted and if right half is sorted then left half is not sorted
   *
   * Consider the below example, mid = 3, right half sorted, so left half is not sorted
   *
   * index =       [0,1,2,3,4,5,6]
   * given array = [5,6,7,1,2,3,4]
   *
   * now in the sorted halves,
   * case 1: for left half:  if the nums[low] <= target <= nums[mid] we search in the left half, otherwise we search in the right half
   * case 2: for right half: if the nums[mid] <= target <= nums[high] we search in the right half, otherwise we search in the left half
   *
   * for this case 1:
   * index =       [0,1,2,3,4,5,6]
   * given array = [4,5,6,7,0,1,2], target 5, 0 respectively, for the first time
   *
   * for this case 2:
   *
   * index =       [0,1,2,3,4,5,6]
   * given array = [5,6,7,1,2,3,4] , target 3, 5 respectively, for the first time
   *
   *
   */
  public int search_BetterReadable(int[] nums, int target) {

    int low = 0, high = nums.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (nums[mid] == target) return mid;

      /**
       * left half sorted
       */

      else if (nums[low] <= nums[mid]) {
        if (nums[low] <= target && target <= nums[mid])
          high = mid - 1;
        else
          low = mid + 1;

      }

      /**
       *  else right half sorted
       */

      else {
        if (nums[mid] <= target && target <= nums[high])
          low = mid + 1;

        else
          high = mid - 1;

      }
    }
    return -1;
  }
}
