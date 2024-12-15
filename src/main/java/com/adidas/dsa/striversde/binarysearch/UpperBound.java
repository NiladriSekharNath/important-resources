package com.adidas.dsa.striversde.binarysearch;

/**
 * We are given an array we are tasked to find the smallest index greater than x
 *
 * in simple terms for an 'x', find 'ind' such that 'arr[ind] > x', absolutely same to lowerBound except in the "arr[mid] >= x"
 * in lowerBound changes to "arr[mid] > x" in upper bound
 *
 * example : [2, 3, 6, 7, 8, 8, 11, 11, 12 ], x = 8 we get upper bound, index = 6 and arr[index] = 11
 */
public class UpperBound {
  public int upperBound(int[] arr, int x){
    int low = 0, ans = arr.length, high = ans - 1;
    while(low <= high){
      int mid = low + (high - low)/2;

      // maybe an answer
      /**
       * absolutely same to lowerBound except in the "arr[mid] >= x" in lowerBound changes to "arr[mid] > x" in upper bound
       */
      if(arr[mid] > x){
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
