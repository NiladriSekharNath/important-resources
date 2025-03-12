package com.adidas.dsa.striversde.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Longest Substring with K Uniques
 * Difficulty: MediumAccuracy: 34.65%Submissions: 177K+Points: 4
 * Given a string s, you need to print the size of the longest possible substring with exactly k unique characters.
 * If no possible substring exists, print -1.
 *
 * Examples:
 *
 * Input: s = "aabacbebebe", k = 3
 * Output: 7
 * Explanation: "cbebebe" is the longest substring with 3 distinct characters.
 * Input: s = "aaaa", k = 2
 * Output: -1
 * Explanation: There's no substring with 2 distinct characters.
 * Input: s = "aabaaab", k = 2
 * Output: 7
 * Explanation: "aabaaab" is the longest substring with 2 distinct characters.
 * Constraints:
 * 1 ≤ s.size() ≤ 105
 * 1 ≤ k ≤ 26
 * All characters are lowercase letters
 *
 * Same logic as FruitsIntoBasket question only difference map.size <= 2 for the fruitIntoBaskets
 *
 * as atmost 2 and we are interested with map.size() == k (k unique elements)
 *
 *
 */
public class LongestSubstringsWithKUniques {
  public int longestkSubstr(String s, int k) {
    int left = 0, right = 0, maxLength = -1, n = s.length();
    Map<Character, Integer> map = new HashMap<>();
    while(right < n){
      while(map.size() > k){
        char leftVal = s.charAt(left);

        map.put(leftVal, map.get(leftVal) - 1);
        if(map.get(leftVal) == 0) map.remove(leftVal);

        left++;
      }

      map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0)  + 1);

      if(map.size() == k){
        maxLength = Math.max(maxLength, (right - left + 1));
      }

      right++;
    }

    return maxLength;
  }

  /**
   *
   * Only difference between the previous approach and the optimized approach is
   *
   * when we first find a length = 5 and then we see a non-unique element at the right position, then we increment from the left
   *
   *
   * let's say for this testcase
   *
   * aaabbccd k = 3
   * |     |
   * 0     6
   * we find the length = 7
   *
   * now if you see here right = 6, now when we increment right by 1 we get right = 7, which is fine with me
   *
   * but the point now there are 4 distinct elements so length is not updated so idea is we increment left
   *
   * but when we increment left we are also decrementing the maxLength so let's say we got 7
   *
   * there is no point in going from 7, 6, 5, ... since we just need a better answer than 7
   *
   * so we increment left by 1 only
   *
   * so the while -> changes to 'if'
   *
   *
   *
   *
   *
   */
  public int longestkSubstrOptimized(String s, int k) {
    int left = 0, right = 0, maxLength = -1, n = s.length();
    Map<Character, Integer> map = new HashMap<>();
    while(right < n){
      if(map.size() > k){
        char leftVal = s.charAt(left);

        map.put(leftVal, map.get(leftVal) - 1);
        if(map.get(leftVal) == 0) map.remove(leftVal);

        left++;
      }

      map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0)  + 1);

      if(map.size() == k){
        maxLength = Math.max(maxLength, (right - left + 1));
      }

      right++;
    }

    return maxLength;
  }
}
