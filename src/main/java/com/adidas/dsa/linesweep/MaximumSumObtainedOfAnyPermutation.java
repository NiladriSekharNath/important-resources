package com.adidas.dsa.linesweep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MaximumSumObtainedOfAnyPermutation {
  public int maxSumRangeQuery(int[] nums, int[][] requests) {
    List<Integer> numsSorted = Arrays.stream(nums).boxed().collect(Collectors.toList());

    Collections.sort(numsSorted, Collections.reverseOrder());

    int n = nums.length;
    int diff[] = new int[n + 1];
    for (int[] request : requests) {
      int start = request[0], end = request[1];
      diff[start]++;
      diff[end + 1]--;

    }

    Map<Integer, Integer> count = new TreeMap<>((entry1, entry2) -> entry2 - entry1);
    for (int i = 1; i < n + 1; i++) {
      diff[i] = diff[i] + diff[i - 1];

    }

    for (int i = 0; i < n ; i++) {
      count.put(diff[i], count.getOrDefault(diff[i], 0) + 1);

    }

    long sum = 0, mod = (long) 1e9 + 7;
    int start = 0;

    for (var entry : count.entrySet()) {
      int i = start;
      for (; i < start + entry.getValue(); i++) {
        long currVal = entry.getKey() * (long) numsSorted.get(i) ;
        sum = (sum + currVal ) ;
      }
      start = i;
    }

    return (int)(sum % mod);
  }

  public static void main(String[] args){
    new MaximumSumObtainedOfAnyPermutation().maxSumRangeQuery(new int[]{1, 2, 3, 4, 5, 10}, new int[][]{{0, 2}, {1, 3}, {1,1}});
  }
}
