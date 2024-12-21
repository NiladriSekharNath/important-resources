package com.adidas.dsa.striversde.binarysearch;

import java.util.Arrays;

/**
 * Problem statement
 * You are given an array 'arr' consisting of 'n' integers which denote the position of a stall.
 *
 *
 *
 * You are also given an integer 'k' which denotes the number of aggressive cows.
 *
 *
 *
 * You are given the task of assigning stalls to 'k' cows such that the minimum distance between any two of them is the maximum possible.
 *
 *
 *
 * Print the maximum possible minimum distance.
 *
 *
 *
 * Example:
 * Input: 'n' = 3, 'k' = 2 and 'arr' = {1, 2, 3}
 *
 * Output: 2
 *
 * Explanation: The maximum possible minimum distance will be 2 when 2 cows are placed at positions {1, 3}. Here distance between cows is 2.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * 6 4
 * 0 3 4 7 10 9
 *
 *
 * Sample Output 1 :
 * 3
 *
 *
 * Explanation to Sample Input 1 :
 * The maximum possible minimum distance between any two cows will be 3 when 4 cows are placed at positions {0, 3, 7, 10}. Here distance between cows are 3, 4 and 3 respectively.
 *
 *
 * Sample Input 2 :
 * 5 2
 * 4 2 1 3 6
 *
 *
 * Sample Output 2 :
 * 5
 *
 *
 * Expected time complexity:
 * Can you solve this in O(n * log(n)) time complexity?
 *
 *
 * Constraints :
 * 2 <= 'n' <= 10 ^ 5
 * 2 <= 'k' <= n
 * 0 <= 'arr[i]' <= 10 ^ 9
 * Time Limit: 1 sec.
 *
 *
 * Approach : This is very similar approach to Allocate Books previous was finding the min(max) this is finding the "max(min)"
 *
 * Example : 0, 2, 3, 4, 7, 10, 9,  cows 4
 *
 * just few point the stalls are not sorted and we have to sort this one :
 *
 * so after sorting we get
 *
 * [0, 2, 3, 4, 7, 9, 10] cows = 4
 * Here we are finding the low = minimum distance between stalls that can be possible 2
 * high is max distance between stalls (10 - 0 (value not index)) 10
 *
 * let's say low, high = [2, 10] we try with 10 we are trying to place cows now at a minimum distance limit(same as max pages allocation limit ) as 10
 *
 * we place 0 -> C1, 10 -> C2 but now we can't place anymore cows we have 4 cows
 *
 * so we try to decrease the limit of 10 -> 9 and then try,
 *
 * again say if we placed at minimum of 2 distance we
 *
 * see that we can place 0 -> C1, 2 -> C2, 4 -> C3, 7 -> C4, 9 -> C5 we see we can place 5 cows, 5 is a possible answer
 * and we store the variable in answer = 5,
 * so we get one possible answer, now we need to try for a higher value since we are interested in maximum possible value
 *
 */

public class AggressiveCows {
  public static int aggressiveCows(int []stalls, int cows) {
    Arrays.sort(stalls);
    int lowest = stalls[0], secondLowest = stalls[1], highest = stalls[stalls.length - 1],
        low = secondLowest - lowest, high = highest - lowest, ans = 0;
    while(low <= high){
      int mid = low + (high - low)/2;
      int cowsPlaced = getCowsPlacedXDistanceApart(stalls, mid);
      if(cowsPlaced < cows){
        high = mid - 1;

      }
      else {
        ans = mid;
        low = mid + 1;
      }
    }
    return ans;
  }

  private static int getCowsPlacedXDistanceApart(int[] stalls, int minDistance){
    int countCows = 1, lastCowPlacedAt = stalls[0];
    for(int i = 0 ; i < stalls.length; i++){
      if(stalls[i] - lastCowPlacedAt >= minDistance){
        lastCowPlacedAt = stalls[i];
        countCows++;
      }
    }
    return countCows;
  }



  public static void main(String[] args){
    AggressiveCows.aggressiveCows(new int[]{0, 3, 4, 7, 9, 10}, 4);
  }
}
