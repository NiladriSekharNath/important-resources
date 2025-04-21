package com.adidas.dsa;

import java.util.TreeMap;

public class Practice {
  public long countFairPairs(int[] nums, int lower, int upper) {
    long count = 0;
    TreeMap<Integer, Integer> map = new TreeMap<>();
    for(int num : nums){
      count += numbersInRange(map, lower - num, upper - num);

      map.put(num, map.getOrDefault(num, 0) + 1);
    }

    return count;
  }

  private long numbersInRange(TreeMap<Integer, Integer> map, int lowerRange, int upperRange){
    long count = 0;
    for(var entry : map.entrySet()){
      if(lowerRange <= entry.getKey() && entry.getKey() <= upperRange){
        count += entry.getValue();
      }
    }

    return count;
  }
}
