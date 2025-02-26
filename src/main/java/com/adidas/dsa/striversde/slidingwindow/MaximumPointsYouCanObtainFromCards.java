package com.adidas.dsa.striversde.slidingwindow;

/**
 * There are several cards arranged in a row, and each card has an associated number of points. The points are given in the integer array cardPoints.
 *
 * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
 *
 * Your score is the sum of the points of the cards you have taken.
 *
 * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
 *
 *
 *
 * Example 1:
 *
 * Input: cardPoints = [1,2,3,4,5,6,1], k = 3
 * Output: 12
 * Explanation: After the first step, your score will always be 1. However, choosing the rightmost card first will maximize your total score. The optimal strategy is to take the three cards on the right, giving a final score of 1 + 6 + 5 = 12.
 * Example 2:
 *
 * Input: cardPoints = [2,2,2], k = 2
 * Output: 4
 * Explanation: Regardless of which two cards you take, your score will always be 4.
 * Example 3:
 *
 * Input: cardPoints = [9,7,7,9,7,7,9], k = 7
 * Output: 55
 * Explanation: You have to take all the cards. Your score is the sum of points of all cards.
 *
 *
 * Constraints:
 *
 * 1 <= cardPoints.length <= 105
 * 1 <= cardPoints[i] <= 104
 * 1 <= k <= cardPoints.length
 *
 *
 * given an array ->
 *
 * [6, 2, 3, 4, 7, 2, 1, 7, 1], k = 4
 *  _  _                 _  _
 *
 * the maximum score out of this is 6 + 2 + 7 + 1
 *
 *
 * we can pick from either from the beginning or from the end
 *
 * picking -> [6, 2, 3, 4] from the beginning, end [2, 1, 7, 1]
 *
 * picking 3 from beginning and 1 from end
 *
 * [6, 2, 3] [1] total = leftSum + rightSum
 *
 * picking 2 from beginning and 2 from end
 *
 * [6, 2], [7, 1]
 *
 * so we create a size 'k' window and from there
 *
 * from k - 1 to 0 we move
 * and have one right pointer removing one element from the cI and adding one element from the right side
 *
 */
public class MaximumPointsYouCanObtainFromCards {
  public int maxScore(int[] nums, int k) {
    int leftSum = 0, rightSum = 0, maxSum = 0;

    for(int cI = 0;  cI < k; cI++){
      leftSum += nums[cI];
    }

    maxSum = leftSum;
    int rightIndex = nums.length - 1;

    for(int cI = k - 1; cI >= 0; cI--){
      leftSum -= nums[cI];
      rightSum += nums[rightIndex];
      maxSum = Math.max(maxSum, leftSum + rightSum);
      rightIndex--;
    }

    return maxSum ;
  }

  /**
   *
   * Different approach to this problem is
   *
   * let's say we are given
   *
   * [2, 1, 1, 3, 4, 5, 6], k = 3
   *     -  -  -  -
   *
   * let's say our answer window is [[2], [5, 6]] from left and right respectively
   *
   * this can be stated as (n - k) size window (7 - 3) = 4 size window
   *
   *
   * so if we have a totalSum of all elements = tS - (sum[n - k] window )
   *
   * we will get the max sum of [2, 5, 6] window
   *
   *
   */

  public int maxScoreApproachII(int[] nums, int k) {
    int totalSum = 0, maxSum = 0, sum = 0;
    k = nums.length - k ;
    for(int num : nums){
      totalSum += num;
    }
    for(int cI = 0; cI < k ; cI++){
      sum += nums[cI];

    }

    maxSum = Math.max(maxSum, totalSum - sum);

    int leftIndex = 0, rightIndex = k - 1 ;

    while(rightIndex < nums.length - 1){
      sum -= nums[leftIndex];
      leftIndex++;
      rightIndex++;
      sum += nums[rightIndex];
      maxSum = Math.max(maxSum, totalSum - sum);
    }
    return maxSum;
  }
}
