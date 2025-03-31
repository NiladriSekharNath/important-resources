package com.adidas.dsa.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 *
 *
 * Example 1:
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 *
 * Constraints:
 *
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 *
 *
 */
public class MergeIntervals {

  /**
   *
   * @param intervals
   * @return
   *
   *
   *  Given : -> [[1,3],[2,6],[8,10],[15,18]]
   *
   *
   *            [[1,6],[8,10],[15,18]]
   *
   * now the idea to the problem is very simple sort the intervals by their startTime
   *
   * prevInterval[] = intervals[0] = [1,3] -> and we are pointing the currently starting from 1
   *
   *
   * [[1,3],[2,6],[8,10],[15,18]]
   *          1
   *
   *  for every interval if the ending of the previous >= starting of the current(1) we
   *    keep starting as the previous and ending we take max(prevEnd, currentEnd)
   *
   *    otherwise else ->
   *      if it's less we add to the resultList
   *
   *   at last we are at the previous we add the previous value
   *
   */
  public int[][] merge(int[][] intervals) {


    List<int[]> result = new ArrayList<>();

    Arrays.sort(intervals, (entry1, entry2) -> entry1[0] - entry2[0]);

    int prev[] = intervals[0], index = 0 ;

    for(int i = 1 ; i < intervals.length; i++){
      int[] currentInterval = intervals[i];
      if(prev[1] >= currentInterval[0]){
        prev[1] = Math.max(prev[1], currentInterval[1]);
      }else{
        result.add(new int[]{prev[0], prev[1]});
        prev = currentInterval;
      }



    }

    result.add(new int[]{prev[0], prev[1]});

    return result.toArray(new int[result.size()][]);

  }
}
