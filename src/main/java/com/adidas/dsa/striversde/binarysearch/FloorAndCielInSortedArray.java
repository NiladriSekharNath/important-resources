package com.adidas.dsa.striversde.binarysearch;

/**
 * Given an array and an 'x' we are tasked to find the floor and ciel,
 * floor is the largest number in array <= x and
 * ciel is the smallest no in array >= x
 *
 * arr [10, 20, 30, 40, 50] x = 25
 *
 * floor = 20, ciel = 30 ,
 *
 * arr[10, 20, 25, 30, 40, 50] x = 25, and if x is present
 *
 * floor = 25, ciel = 25
 *
 * ciel is simply the lower bound if we call we are good
 */
public class FloorAndCielInSortedArray {
  public int floor(int[] arr, int x){
    int low = 0, high = arr.length - 1, ans = -1;
    while(low <= high){
      int mid = low + (high - low)/2;
      if(arr[mid] <= x){
        ans = arr[mid];
        low = mid + 1;
      }
      else
        high = mid - 1;
    }
    return ans ;
  }

  /**
   *
   * since it's the lower bound we can simply call the method directly
   * but only small changes in the lowerbound code is in lower bound we are interested in finding the index and
   * with ciel we are interested in finding the actual value and conventionally
   * lower bound of a number if not found we send the last index,( size of the array) in ciel however we
   * send -1 if not found
   */
  public int ciel(int[] arr, int x){
    /*return new LowerBound().lowerBound(arr, x);*/
    int low = 0, high = arr.length - 1;
    int ans = -1;

    while (low <= high) {
      int mid = (low + high) / 2;
      // maybe an answer
      if (arr[mid] >= x) {
        ans = arr[mid];
        //look for smaller index on the left
        high = mid - 1;
      }else {
        low = mid + 1; // look on the right
      }
    }
    return ans;
  }
}
