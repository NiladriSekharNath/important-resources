package com.adidas.dsa;

public class ProblemOfTheDay {
  public long repairCars(int[] ranks, int cars) {

    /**
     the key insight to this problem is all the mechanics can work simulaneously
     */

    long low = 1, maxRank = -(int)1e9, ans = ranks.length, high = 0 ;
    for(int rank : ranks){
      maxRank = Math.max(rank, maxRank);

    }

    high = maxRank * cars * cars;

    while(low <= high){
      long mid = low + (high - low)/2;
      if(checkIfPossible(mid, ranks, cars)){
        ans = mid;
        high = mid - 1;
      }else{
        low = mid + 1;
      }
    }

    return ans;
  }

  private boolean checkIfPossible(long requiredTime, int[] ranks, int cars){
    long countOfCars = 0;
    for(int rank : ranks){
      countOfCars += Math.sqrt(requiredTime/rank);
    }

    return countOfCars >= cars;
  }

  public static void main(String[] args){
    new ProblemOfTheDay().repairCars(new int[]{4, 2, 3, 1}, 10);
  }
}
