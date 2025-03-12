package com.adidas.dsa.striversde.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtmostKUniqueCharacters{
  public static int kDistinctChars(int k, String s) {
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

      /**
       * Only difference with LongestSubstringsWithKUniques is this is at most k uniques and
       * so instead of == we need <= k
       */

      if(map.size() <= k){
        maxLength = Math.max(maxLength, (right - left + 1));
      }

      right++;
    }

    return maxLength;
  }
}
