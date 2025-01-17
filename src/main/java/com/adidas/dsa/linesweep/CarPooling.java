package com.adidas.dsa.linesweep;

import java.util.Map;
import java.util.TreeMap;

public class CarPooling {
  public boolean carPooling(int[][] trips, int capacity) {
    Map<Integer, Integer> capacityMap = new TreeMap<>();
    for(int[] trip : trips){
      int start = trip[1], end = trip[2];
      capacityMap.put(start, capacityMap.getOrDefault(trip[1], 0) + trip[0]);
      capacityMap.put(end + 1, capacityMap.getOrDefault(trip[2], 0) - trip[0]);
    }

    int count = 0;
    for(Map.Entry<Integer, Integer> eachEntry : capacityMap.entrySet()){
      count += eachEntry.getValue();
      if(count > capacity)
        return false;
    }
    return true;
  }
}
