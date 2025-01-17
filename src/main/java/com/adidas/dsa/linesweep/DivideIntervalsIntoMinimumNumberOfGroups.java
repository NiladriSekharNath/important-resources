package com.adidas.dsa.linesweep;

import java.util.Map;
import java.util.TreeMap;

public class DivideIntervalsIntoMinimumNumberOfGroups {
  public int minGroups(int[][] intervals) {
    Map<Integer, Integer> overlaps = new TreeMap<>();
    for (int[] interval : intervals) {
      int start = interval[0], end = interval[1] + 1;
      overlaps.put(start, overlaps.getOrDefault(start, 0) + 1);
      overlaps.put(end, overlaps.getOrDefault(end, 0) - 1);
    }

    int maxOverlap = 0, currentOverlap = 0;
    for (Map.Entry<Integer, Integer> overlap : overlaps.entrySet()) {
      currentOverlap += overlap.getValue();
      maxOverlap = Math.max(maxOverlap, currentOverlap);
    }

    return maxOverlap;
  }


  public static void main(String[] args) {
    new DivideIntervalsIntoMinimumNumberOfGroups().minGroups(new int[][]{{5, 10}, {6, 8}, {1, 5}, {2, 3}, {1, 10}});
  }
}
