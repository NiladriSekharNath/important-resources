package com.adidas.dsa.striversde.binarysearch;

/**
 * Same logic as Median of two sorted arrays
 *
 * instead of selecting equal (n1 + n2)/2 elements both side we are doing
 *
 * total of k elements but only catch is we can't directly set as the low = 0 and high = lower(n1, n2) like previously
 *
 * assuming n1 < n2
 *
 * n1 = 4 and n2 = 6 and we are required to find k = 2 ;
 *
 * we cannot make high = 4 as we cannot select 4 numbers we can select atmost 2 numbers so high = min(n1, k)
 *
 * so also another edge case for the low part let's say
 *
 *
 * array1 [1, 3, 7, 9] array2 [2, 4, 5, 6, 10, 11] n1 = 4 and n2 = 6, k = 2
 *
 * we select let's say during an iteration we get mid1 = 4(selecting four elements) then we get mid2 = k - mid1 = 2 - 4 = -2
 * which means we need to unselect 2 elements so that's the problem
 *
 *
 *
 * array1 [1, 3, 7, 9] array2 [2, 4, 5, 6, 10, 11] n1 = 4 and n2 = 6, k = 9
 *
 * let's say we are selecting if we go with the median approach directly we can't because array1 is smaller and we say
 * are selecting from [0 - 4] elements, let's say we are selecting 0 elements from array1 hence we now need to select
 * all elements from array2 which we did n2 = 6 but we will never satisfy our condition as k = 9 and we selected a total
 * of 6 elements from array2 we need minimum of 3 (9 - 6) [k - n2]
 *
 * so low = Max(0, k - n2)
 * high = min(k, n1)
 *
 *
 */
public class KthElement {
  public int kthElement(int nums1[], int nums2[], int k) {
    if(nums1.length > nums2.length) return kthElement(nums2, nums1, k);
    int n1 = nums1.length, n2 = nums2.length, low = Math.max(0, k - n2), high = Math.min(n1, k);
    while(low <= high){
      int mid1 = low + (high - low)/2;
      int mid2 = k - mid1;
      int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE, r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

      if(mid1 - 1 >= 0) l1 = nums1[mid1 - 1];
      if(mid1 < n1) r1 = nums1[mid1];
      if(mid2 - 1 >= 0) l2 = nums2[mid2 - 1];
      if(mid2 < n2) r2 = nums2[mid2];

      if(l1 > r2) high = mid1 - 1;
      else if( l2 > r1) low = mid1 + 1;
      else {
        return Math.max(l1,l2);
      }
    }

    return 0;
  }

  public static void main(String args[]){
    new KthElement().kthElement(new int[]{2, 3, 6, 7, 9}, new int[]{1, 4, 8, 10}, 5);
  }
}
