package com.adidas.dsa.linesweep;

import java.util.Map;
import java.util.TreeMap;

public class NumberOfFlowersInFullBloom {
  public int[] fullBloomFlowers(int[][] flowers, int[] people) {
    int n = people.length, result[] = new int[n];

    Map<Integer, Integer> diff = new TreeMap<>();
    for(int[] flower : flowers){
      int start = flower[0], end = flower[1] + 1;
      diff.put(start, diff.getOrDefault(start, 0) + 1);
      diff.put(end , diff.getOrDefault(end, 0) - 1);
    }
    TreeMap<Integer, Integer> count = new TreeMap<>();
    int currentSum = 0;
    for(Map.Entry<Integer, Integer> entry : diff.entrySet()){
      currentSum += entry.getValue();
      count.put(entry.getKey(), currentSum);
    }

    for(int i = 0 ; i<n; i++){
      Map.Entry<Integer, Integer> entry = count.floorEntry(people[i]);
      if(entry != null) result[i] = entry.getValue();
    }

    return result;

  }

  public static void main(String[] args){
    //new NumberOfFlowersInFullBloom().fullBloomFlowers(new int[][]{{1,6}, {3, 7}, {9, 12}, {4, 13}}, new int[]{2, 3, 7, 11});

    new NumberOfFlowersInFullBloom().fullBloomFlowers(new int[][]{{2, 8}, {31, 37}}, new int[]{33, 47, 45, 12});
  }
}
