package com.adidas.dsa.striversde.binarysearch;

/**
 * You are given a sorted array 'arr' of distinct values and a target value 'm', You need to find the index of the target value x in the array
 *
 * If the value is present in the array, then return its index
 *
 * If the value is not present, determine the index where it would be inserted in the array while maintaining the sorting order
 *
 * arr = [1, 2, 4, 7], x = 6
 *
 * our position = 3, which means if we rebuild the array it should look like [1, 2, 4, 6, 7] position at 3
 *
 * for arr = [1, 2, 4, 7], x = 4 we get the value 2, which is the value 4
 *
 * This is simply the lowerBound of the binary Search
 */
public class SearchInsertPosition {
  /**
   * lower bound code
   */
  public int searchInsert(int[] arr, int x){
    int low = 0, ans = arr.length, high = ans - 1;
    while(low <= high){
      int mid = low + (high - low)/2;

      // maybe an answer

      if(arr[mid] >= x){
        ans = mid;

        /**
         * above mid is a possible candidate still we keep searching on the left
         */
        high = mid - 1;
      }
      else
        low = mid + 1;
    }
    return ans;
  }
}
