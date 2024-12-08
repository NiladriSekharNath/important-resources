package com.adidas.dsa.striversde.binarysearch;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * given an array with non-decreasing elements(increasing or equal) and a given x, we need to find an element/index which is greater than or equal to the element
 *
 * in simple terms smallest index greater than or equal x, arr[index] >= x
 *
 *
 *
 * example : [3, 5, 8, 15, 19], x = 9  our index = 3, lowerBoundValue = 15
 *         : [3, 5, 8, 15, 19], x = 8 our index = 2, lowerBound Value answer = 8
 *         : [3, 5, 8, 15, 19] x = 20 lowerBound = 6 which is the last hypothetical index
 */
@Slf4j
public class LowerBound {
  public int lowerBound(int[] arr, int x){
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

  public static void main(String[] args){
    log.info("Lower Bound: {}", new LowerBound().lowerBound(new int[]{3, 5, 8, 15, 19}, 9));
    log.info("Lower Bound: {} using Java default Binary Search. This returns if not found the insertion point, using ((-insertionPoint) -1)", Arrays.binarySearch(new int[]{3, 5, 8, 15, 19}, 9));

    log.info("Lower Bound: {} using Java default Binary Search, after adjusting above value", Math.abs(Arrays.binarySearch(new int[]{3, 5, 8, 15, 19}, 9)) - 1);

    log.info("For index present Lower Bound: {}", new LowerBound().lowerBound(new int[]{3, 5, 8, 15, 19}, 8));
    log.info("For index present Lower Bound: {} using Java default Binary Search.", Arrays.binarySearch(new int[]{3, 5, 8, 15, 19}, 8));
  }
}
