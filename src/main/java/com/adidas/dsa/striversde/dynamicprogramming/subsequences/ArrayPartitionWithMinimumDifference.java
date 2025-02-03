package com.adidas.dsa.striversde.dynamicprogramming.subsequences;

/**
 * Problem statement
 * You are given an array 'arr' containing 'n' non-negative integers.
 *
 *
 *
 * Your task is to partition this array into two subsets such that the absolute difference between subset sums is minimum.
 *
 *
 *
 * You just need to find the minimum absolute difference considering any valid division of the array elements.
 *
 *
 *
 * Note:
 *
 * 1. Each array element should belong to exactly one of the subsets.
 *
 * 2. Subsets need not always be contiguous.
 * For example, for the array : [1, 2, 3], some of the possible divisions are
 *    a) {1,2} and {3}
 *    b) {1,3} and {2}.
 *
 * 3. Subset-sum is the sum of all the elements in that subset.
 * Example:
 * Input: 'n' = 5, 'arr' = [3, 1, 5, 2, 8].
 *
 * Ouput: 1
 *
 * Explanation: We can partition the given array into {3, 1, 5} and {2, 8}.
 * This will give us the minimum possible absolute difference i.e. (10 - 9 = 1).
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1:
 * 4
 * 1 2 3 4
 * Sample Output 1:
 * 0
 * Explanation for sample input 1:
 * We can partition the given array into {2,3} and {1,4}.
 * This will give us the minimum possible absolute difference i.e. (5 - 5 = 0) in this case.
 * Sample Input 2:
 * 3
 * 8 6 5
 * Sample Output 2:
 * 3
 * Explanation for sample input 2:
 * We can partition the given array into {8} and {6,5}.
 * This will give us the minimum possible absolute difference i.e. (11 - 8 = 3).
 * Expected time complexity:
 * The expected time complexity is O(n * 𝚺 'arr'[i]), where 𝚺 'arr'[i] denotes the sum of all elements in 'arr'.
 * Constraints:
 * 1 <= 'n' <= 10^3
 * 0 <= 'arr'[i] <= 10^3
 * 0 <= 𝚺 'arr'[i] <= 10^4,
 *
 * where 𝚺 'arr'[i] denotes the sum of all elements in 'arr'.
 *
 * Time Limit: 1sec
 *
 * So if we go with the SubSetSumEqualtoK we get from the last row in the dp
 * all the sums possible upto target Sum
 *
 * so for this we are required to partition the array let's say s1 + s2 = totalSum, we need to find the
 * Min(abs(s1 - s2)) the lowest possible value for the partitions if we see the that question
 *
 * the last row returns all sums possible till targetSum so if we calculate the totalSum and send to the minSubsetSumDifference
 * to subsetSumEqualToK question we would be able to get the value
 *
 * we go through each col in the last row till totalSum/2
 *
 * and see for each sum s1 till totalSum/2 we try to find if we get a value if possible
 *
 * and if the value is possible we find out the remaining sum (totalSum - s1)
 *
 * now we store the minValue = Math.min(minValue, Math.abs(s1 - s2));
 */
public class ArrayPartitionWithMinimumDifference {
  public static int minSubsetSumDifference(int []arr, int n) {

    int totalSum = 0;
    for(int num : arr)
      totalSum += num;

    boolean dp[] = tabulationApproachSpaceOptimized(n, totalSum, arr);

    int minValue = (int)1e9;

    for(int val = 0 ; val <= totalSum/2; val++){
      if(dp[val]){
        int sum1 = val, sum2 = totalSum - sum1;
        minValue = Math.min(minValue, Math.abs(sum1 - sum2));
      }

    }

    return minValue;
  }
  private static boolean[] tabulationApproachSpaceOptimized(int n, int k, int[] nums){
    boolean[] prev = new boolean[k + 1];

    prev[0] = true;



    if (nums[0] <= k) {
      prev[nums[0]] = true;
    }

    for(int row = 1; row < n ; row++){

      boolean[] curr = new boolean[k + 1];

      /**
       * since all 0 indexes where target = 0 we are setting as true
       */
      curr[0] = true;

      for(int col = 1 ; col <= k ; col++){
        boolean nonPick = prev[col] ;
        boolean pick = false;
        if(nums[row] <= col){
          pick = prev[col - nums[row]];

        }

        curr[col] = nonPick || pick;

      }
      prev = curr;
    }

    return prev ;
  }
}
