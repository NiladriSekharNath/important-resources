package com.adidas.dsa.advanceddatastructures.fenwickTree;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given two 0-indexed arrays nums1 and nums2 of length n, both of which are permutations of [0, 1, ..., n - 1].
 *
 * A good triplet is a set of 3 distinct values which are present in increasing order by position both in nums1 and nums2. In other words, if we consider pos1v as the index of the value v in nums1 and pos2v as the index of the value v in nums2, then a good triplet will be a set (x, y, z) where 0 <= x, y, z <= n - 1, such that pos1x < pos1y < pos1z and pos2x < pos2y < pos2z.
 *
 * Return the total number of good triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: nums1 = [2,0,1,3], nums2 = [0,1,2,3]
 * Output: 1
 * Explanation:
 * There are 4 triplets (x,y,z) such that pos1x < pos1y < pos1z. They are (2,0,1), (2,0,3), (2,1,3), and (0,1,3).
 * Out of those triplets, only the triplet (0,1,3) satisfies pos2x < pos2y < pos2z. Hence, there is only 1 good triplet.
 * Example 2:
 *
 * Input: nums1 = [4,0,1,3,2], nums2 = [4,1,0,2,3]
 * Output: 4
 * Explanation: The 4 good triplets are (4,0,3), (4,0,2), (4,1,3), and (4,1,2).
 *
 *
 * Constraints:
 *
 * n == nums1.length == nums2.length
 * 3 <= n <= 105
 * 0 <= nums1[i], nums2[i] <= n - 1
 * nums1 and nums2 are permutations of [0, 1, ..., n - 1].
 *
 * so basically we are given two arrays we are supposed to find the number of good triplets in the array that occur in the strictly
 * increasing order
 *
 *  0  1  2  3  4    0  1  2  3  4
 * [4, 0, 1, 3, 2], [4, 1, 0, 2, 3]
 *
 * if you see here -> the pairs are
 *
 * (4,0,3), (4,0,2), (4,1,3), and (4,1,2).
 *
 * 4, 0, 3 occurs in the same increasing order of indices in both arrays
 *
 * and same goes other triplets as shown above
 *
 *
 * 1. in order to find the triplets where it is mentioned we need x < y < z
 *
 *    we always start fixing the middle element 'y'
 *
 *     example:
 *
 *           y
 *     0  1  2  3  4    0  1  2  3  4
 *    [4, 0, 1, 3, 2], [4, 1, 0, 2, 3]
 *
 *
 *    let's say we fix y = 0 in nums1
 *
 *    we find the equivalent position of y in nums2 we get index = 2 (though here just for understanding we don't need the index in nums2)
 *
 *    we see there are 1 common element on the left -> [4] and 2 common elements on the right [2,3]
 *
 *    so effectively we get
 *
 *    countTriplets += 1 * 2
 *
 *    [4] y [3, 2] , here y = 0
 *
 *    [4, 0, 3], [4, 0, 2]
 *
 *    -------------------------
 *
 *    again let's say y we fix as 1 in nums1,
 *
 *    we find the equivalent position of y in nums2 we get index = 1
 *
 *    we see there are 1 common element -> 4 in the left hand side and we see 3, 2 (2 elements on the right hand side)
 *    that are common, we do cross product fixing 'y'
 *
 *    countTriplets += 1 * 2
 *
 *    [4] y [3, 2] effectively giving us 2 triplets, [4, 1, 3], [4, 1, 2]
 *
 *
 *    so the idea is basically finding the fix a 'y' and finding the leftCommon * rightCommon index
 *
 *
 *
 *
 *
 *
 *
 *
 */
public class CountGoodTripletsInAnArray {
  public long goodTriplets(int[] nums1, int[] nums2) {
    Map<Integer, Integer> pos2 = new HashMap<>();
    long count = 0;
    int size = nums1.length;

    for(int cI = 0; cI < size; cI++){
      pos2.put(nums2[cI], cI);
    }

    FenwickTree fenwickTree = new FenwickTree(size);

    int cI = 0; // representing index in nums1;

    for(int num : nums1){
      int indexOfCurrentElementIn2 = pos2.get(num), leftCommonCount = fenwickTree.sum(indexOfCurrentElementIn2),
          leftUncommonCount = cI - leftCommonCount, totalRightElements = size - 1 - indexOfCurrentElementIn2,
          rightCommonCount = totalRightElements - leftUncommonCount ;

      count += (long) leftCommonCount * rightCommonCount ;

      fenwickTree.update(indexOfCurrentElementIn2, 1);

      cI++;

    }

    return count;

  }
  private class FenwickTree{
    int size, prefixSum [];

    public FenwickTree(int n){
      this.size = n + 1 ;
      this.prefixSum = new int[size];
    }

    public void update(int index, int value){
      index++;

      while(index < size){
        prefixSum[index] += value;
        index = index + (index & (-index));
      }
    }

    public int sum(int index){

      index++;

      int value = 0;
      while(index > 0){
        value += prefixSum[index];
        index = index - (index & (-index));
      }

      return value;
    }
  }
}
