package com.adidas.dsa.striversde.dynamicprogramming.subsequences;

public class PartitionEqualSubsetSum {
  public static boolean canPartition(int[] arr, int n) {
    int totalSum = 0 ;
    for(int num : arr)
      totalSum += num;
    return totalSum % 2 == 0 ? tabulationApproachSpaceOptimized(n, totalSum / 2, arr) : false;
  }

  private static boolean tabulationApproachSpaceOptimized(int n, int k, int[] nums){
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

    return prev[k] ;
  }
}
