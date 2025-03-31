package com.adidas.dsa.mergeintervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears
 * in at most one part. For example, the string "ababcc" can be partitioned into ["abab", "cc"],
 * but partitions such as ["aba", "bcc"] or ["ab", "ab", "cc"] are invalid.
 *
 * Note that the partition is done so that after concatenating all the parts in order, the resultant string should be s.
 *
 * Return a list of integers representing the size of these parts.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 * Example 2:
 *
 * Input: s = "eccbbbbdec"
 * Output: [10]
 *
 *
 * Given -> "ababcc"
 *  ans => [4, 2]
 */

public class PartitionLabels {

  /**
   *
   * @param s
   * @return
   *
   * Approach to this problem is :
   *
   * 0 1 2 3 4 5 (spaced out to write the indexes not spaces)
   * a b a b c c ->
   *
   * we are finding out the lastIndexes of each letter to find the optimal partition size
   *
   * a = 2
   * b = 3
   * c = 5
   *
   * we initialize partitionStart = 0, partitionEnd = 0
   *
   * now again we loop through each letter and
   * keep each letter updating the  'partitionEnd' = max(partitionEnd, currentLetter)
   *
   * now we check if check if currentPos 'i' == partitionEnd
   *
   * which will be for the case 'b' at i = 3
   *
   * therfore when that's the case that position is a valid partition we add (partitionEnd - partitionStart + 1) in the
   * resultList
   *
   * and we update :
   *     partitionStart = partitionEnd + 1
   *
   *
   *
   */
  public List<Integer> partitionLabels(String s) {
    int lastPosition[] = new int[26], partitionStart = 0, partitionEnd = 0;;

    for(int cI = 0 ; cI < s.length(); cI++){
      lastPosition[s.charAt(cI) - 'a'] = cI;
    }


    List<Integer> result = new ArrayList<>();

    for(int i = 0; i < s.length(); i++){
      partitionEnd = Math.max(partitionEnd, lastPosition[s.charAt(i) - 'a']);

      if(i == partitionEnd){
        result.add(i - partitionStart + 1);
        partitionStart = partitionEnd + 1;
      }
    }

    return result;

  }

  /**
   *
   * @param s
   * @return
   *
   *
   * keeping the above concept in mind we apply MergeIntervals pattern to this problem, core idea behind
   *
   * We finding the first occurrence and last occurrence of each letter
   *
   *  0 1 2 3 4 5
   * "a b a b c c"
   *
   * a -> [0, 3]
   * b -> [1, 3]
   * c -> [4, 5]
   *
   * now the idea behind is
   * if we see 0 -> 3
   *           1 -> 3
   *
   *           then the entire range is 0 -> 3
   *
   *
   * rest same logic
   *
   *
   *
   */

  public List<Integer> partitionLabelsUsingMergeIntervals(String s) {
    int start[] = new int[26], end[] = new int[26];

    Arrays.fill(start, -1);
    Arrays.fill(end, -1);

    for(int cI = 0; cI < s.length() ; cI++){
      char cL = s.charAt(cI);

      if(start[cL - 'a'] == -1) start[cL - 'a'] = cI ;
      end[cL - 'a'] =  cI;
    }


    List<int[]> values = new ArrayList<>();

    for(int cI = 0 ; cI < 26; cI++){
      if(start[cI] != -1)
        values.add(new int[]{start[cI], end[cI]});
    }

    Collections.sort(values, (entry1, entry2) -> entry1[0] - entry2[0]);

    int[] previous = values.get(0);

    List<Integer> result = new ArrayList<>();

    for(int cI = 1; cI < values.size(); cI++){
      int currentPair[] = values.get(cI);
      /**
       * here in MergeIntervals we added '>=' we are adding > here because letters cannot overlap
       *
       * 0 -> 3   now letter can start at 3, it will be either before 3 or after 3 any other letter
       *
       *
       */
      if(previous[1] > currentPair[0]){
        previous[1] = Math.max(previous[1], currentPair[1]);
      }
      else{
        result.add(previous[1] - previous[0] + 1);
        previous = currentPair;
      }
    }

    result.add(previous[1] - previous[0] + 1);

    return result;
  }

}
