package com.adidas.dsa.striversde.slidingwindow;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 3. Longest Substring Without Repeating Characters
 * Solved
 * Medium
 * Topics
 * Companies
 * Hint
 * Given a string s, find the length of the longest
 * substring
 *  without duplicate characters.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 5 * 104
 * s consists of English letters, digits, symbols and spaces.
 *
 *
 * so the idea for this problem is pretty simple given this word we are trying to find the longest length of the substring
 * without repeating characters
 *
 * See the substring is "abcabcbb" we just wrote in that order to make this visually better for the indexing
 *
 * 0 1 2 3 4 5 6 7
 * a b c a b c b b
 *
 * so the idea is we are expanding a sliding window approach from 'a' both pointing left and right  = 0 and expanding
 * a
 * ab
 * abc
 * abca right -> 3
 *
 * when we find at right = 3 we find the count of the character 'a' also having a count > 1 now left is pointing from 'a' at index = 0
 *
 * so when we move from the left -> since now the substring "abca" so we would have to move from left and keep decrementing the count
 * of each characters we move till we find a unique window with unique characters see
 *
 * "abca" right -> 3 left -> 0
 * we keep counting for the letter at 'right' and keep incrementing left till we find length with no duplicates
 * moving one character at a time
 *
 *
 *
 */
public class LongestSubstringWithoutRepeatingCharacters {

  public int lengthOfLongestSubstring(String s) {
    int left = 0, right = 0, maxLength = 0, n = s.length();
    Map<Character, Integer> map = new HashMap<>();
    while(right < n){
      char currentLetter = s.charAt(right);
      map.put(currentLetter, map.getOrDefault(currentLetter, 0) + 1);
      while(map.get(currentLetter) > 1){
        char currentLeftLetter = s.charAt(left);
        map.put(currentLeftLetter, map.get(currentLeftLetter) - 1);
        left++;
      }

      if(map.get(currentLetter) == 1){
        maxLength = Math.max(maxLength, right - left + 1);
      }
      right++;
    }

    return maxLength;
  }

  /**
   * previous approach is find however it would take time for these kind of strings
   *
   * abcc
   *
   * see for this we are let's say we are
   *
   * instead of keeping the count -> what we could do is for each letter in each of the characters instead of the count
   *            it would be better to keep the indexing
   * 0 1 2 3
   * a b c c
   *
   * because when we are at right -> 3
   * from left we need to move from 0 -> 3 to find the better window
   *
   * left moves a lot from 0 -> 3
   * instead of that we could keep left = map[currentLetter] + 1 (so by this we can move directly to 3 from 0)
   *
   * also just a point
   *
   * 0 1 2 3 4 5 6 7 8
   * c a d b z a b c d
   *         |     |
   *       left   right
   *
   * right => 7 we see in the map already c exists at '0' also 'left' is at '4'
   * so we don't care what happens before left as there is no point to this one
   *
   * as we simply need the unique characters in a window
   *
   *
   *
   *
   */

  public int lengthOfLongestSubstringStrivers(String s) {
    int left = 0, right = 0, maxLength = 0, n = s.length();
    int[] map = new int[256];
    Arrays.fill(map, -1);
    while(right < n){
      char currentLetter = s.charAt(right);

      if(map[currentLetter] != -1){
        if(map[currentLetter] >= left){
          left = map[currentLetter] + 1;
        }
      }

      map[currentLetter] = right;
      maxLength = Math.max(maxLength, right - left + 1);
      right++;

    }

    return maxLength;
  }

  public static void main(String[] args){
    new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb");
    new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring(" ");
  }
}
