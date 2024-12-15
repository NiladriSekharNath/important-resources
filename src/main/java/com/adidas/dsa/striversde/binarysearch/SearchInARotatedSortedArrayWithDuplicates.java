package com.adidas.dsa.striversde.binarysearch;

/**
 * Approach Similar problem as the previous SearchInARotatedSortedArray but now it has duplicates
 *
 * [4,5,6,7,0,1,2]
 *
 * previously we were searching this if the left half [low, mid] both inclusive is sorted or the right half is sorted [mid, high]
 *
 * now the only problem here is it would fail at this point
 *
 * index : [0, 1, 2, 3, 4, 5, 6]
 * array : [1, 0, 1, 1, 1, 1, 1], target = 0
 *
 * where both left half we are not sure if either of them are sorted here when low = 0, high = 6, mid = 3,
 * we see left half [0, 3], right half[3, 6] both are not sorted programatically we see here so that's the problem stopping us
 *
 * if after checking if mid is the target what we do is when not the target we check if nums[low] == nums[mid] == nums[high] then
 * we would simply increase low = low + 1 and high = high - 1 which means we now point to low = 1, high = 5 and we keep on checking
 *
 * in our binary search
 */
public class SearchInARotatedSortedArrayWithDuplicates {
  public boolean search(int[] nums, int target) {
    int low = 0, high = nums.length - 1;
    while (low <= high) {
      int mid = low + (high - low) / 2;

      if (nums[mid] == target) return true;
      if(nums[low] == nums[mid] && nums[mid] == nums[high]){
        low = low + 1;
        high = high - 1;
        //continue;
      }


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
    return false;
  }
}
