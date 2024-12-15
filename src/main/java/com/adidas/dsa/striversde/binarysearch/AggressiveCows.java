package com.adidas.dsa.striversde.binarysearch;

import java.util.Arrays;

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
