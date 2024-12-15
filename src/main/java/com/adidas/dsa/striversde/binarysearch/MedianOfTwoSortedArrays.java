package com.adidas.dsa.striversde.binarysearch;

/**
 * Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.
 *
 * The overall run time complexity should be O(log (m+n)).
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [1,3], nums2 = [2]
 * Output: 2.00000
 * Explanation: merged array = [1,2,3] and median is 2.
 *
 *
 * Example 2:
 *
 * Input: nums1 = [1,2], nums2 = [3,4]
 * Output: 2.50000
 * Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.
 *
 * Approach : The main approach for this problem is using binary search on numbers we are selecting
 *
 * Brute force is we can use the merge portion of the merge sort to merge both arrays and find the median (keep a count of the middle elements)
 *
 * The main approach for this problem is using binary search on numbers we are selecting
 *
 * let's say we have this example :
 *
 * 1, 4, 8, 10, 11, 12, 13, 15
 *
 * 2, 3, 6, 7, 9, 14
 *
 * if we need median, median is the equal number of elements both sides
 *
 *
 * n/2 + n/2 = n
 *
 * so the middle element
 *
 * let's say we choose both parts like this :
 * solution thinking is let's say when merging both arrays: 1, 2, 3, 4, 6, 7, [8, 9], 10, 11, 12, 13, 14, 15 since even, median is (8 + 9)/2.0,
 *
 * we can see that we are getting 1, 4,  from the first array and 2, 3, 6, 7, 8 from the second array which constitutes our one of the answers contributing
 * to median,
 *
 * or we can say equal division of parts in both sides we are getting 5 elements on the left side and 5 elements on the right side
 *
 * so if we try to select from array1, 3 elements and 2 elements from array2 we are getting the first part of the median
 *
 * below we are showing just an example,
 *
 * 1, 4, 8  10 | 11, 12, 13, 15 ---------- > array1
 *    2, 3  6 | 7, 9, 14  ---------- > array2,
 *
 * Now marking the above as shown below :
 *
 * let array1, size = n1 and array2, size = n2
 *
 * taking the above consideration we are selecting from [0 - (n1 + n2 + 1)/2] elements  [for odd it is adjusted as integer division]
 *
 *        1, 4, 8(l1)     | 10(r1 or mid), 11, 12, 13, 15 ---------- > array1  size = n1 = 8
 *  *    2, 3  6  7(l2)  | 9(r2 or mid), 14  ---------- > array2,     size = n2 = 6
 *
 *   left side | right side
 *
 *  -------------------------------------------------------------------------------------------------------------------
 * for the above example we are picking [0 - 7] elements on the left side either from array1 or array2 and 7 elements on
 * the right side to contribute to the median
 *
 * Dry run let's say in [0 - 7] we are picking up 3 elements from array1
 * mid1 = 3,
 * we therefore need
 * mid2 = (n1 + n2 + 1)/2 - mid1 = (8 + 6 + 1)/2 - 3 = 15/2 - 3 = 4
 *
 * to form 7 elements on the left side
 *
 * now (please note while counting actual element is count - 1) since we are counting and arrays are 0 indexed
 *
 * l1 = mid1 - 1 and l2 = mid2 - 1
 *
 * see nums[l1] <= nums[r1] always because sorted same array
 *
 * and nums[l2] <= nums[r2] always
 *
 * now we have to find the value for median to happen l1 < r2 and l2 < r1
 *
 * ---------------------------------------------------------------------------------------------------------------------
 * 1, 4, 8  10 | 11, 12, 13, 15 ---------- > array1
 *  *    2, 3  6 | 7, 9, 14  ---------- > array2,
 *
 *  mid1 = 3, mid2 = 4
 *
 *    1 4 8 | 10 11 12 13 15
 * 2 3 6 7 | 9 14
 *
 * this is the correct case but in order to find the eliminate other sides
 *
 * we are doing this let's say
 *
 * 1 4 8 10 | 11 12 13 15 ----> mid1 = 4
 * 2 3 6 | 7 9 14
 *
 * here (l2 < r1 | 6 < 11) which is okay but here 10(l1) > 7(r2) which is not correct so we move
 * high = mid1 - 1  = 4 - 1 which means we are selecting 3 not this next step but we are close effectively moving our search space
 *
 * 1 4 8  | 10 11 12 13 15
 * 2 3 6 7 | 9 14
 *
 *
 *
 * we try always with the lower size array for optimal time complexity
 *
 * for odd length
 *
 * like
 * 1 2 7     ---> array1
 * 3 4 5 6  ----> array2
 *
 * 1 2 3 4 | 5 6 7
 *
 * 1 2 | 7
 * 3 4 |
 *
 *
 * Time Complexity O(log(min(n, m))
 *
 *
 *
 */
public class MedianOfTwoSortedArrays{


  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if(nums1.length > nums2.length) return findMedianSortedArrays(nums2, nums1);
    int low = 0 , n1 = nums1.length, n2 = nums2.length, high = n1, required = (n1 + n2 + 1)/2;
    while(low <= high){
      int mid1 = low + (high - low)/2;
      int mid2 = required - mid1;
      int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE, r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

      if(mid1 - 1 >= 0) l1 = nums1[mid1 - 1];
      if(mid1 < n1) r1 = nums1[mid1];
      if(mid2 - 1 >= 0) l2 = nums2[mid2 - 1];
      if(mid2 < n2) r2 = nums2[mid2];

      if(l1 > r2) high = mid1 - 1;
      else if( l2 > r1) low = mid1 + 1;
      else {
        if((n1 + n2) % 2 == 0){
          return (Math.max(l1, l2) + Math.min(r1, r2)) /2.0;
        }
        return Math.max(l1, l2) ;
      }
    }

    return 0.0;
  }

  public static void main(String args[]){
    new MedianOfTwoSortedArrays().findMedianSortedArrays(new int[]{1,4, 8, 10, 11, 12, 13, 14, 15}, new int[]{2, 3, 6, 7, 9, 14});
  }
}
