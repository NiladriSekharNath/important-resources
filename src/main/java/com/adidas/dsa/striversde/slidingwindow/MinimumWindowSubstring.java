package com.adidas.dsa.striversde.slidingwindow;

/**
 * Given two strings s and t of lengths m and n respectively, return the minimum window substring of s
 * such that every character in t (including duplicates) is included in the window.
 * If there is no such substring, return the empty string "".
 *
 * The testcases will be generated such that the answer is unique.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 * Example 2:
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 * Example 3:
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 *
 *
 * Constraints:
 *
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 105
 * s and t consist of uppercase and lowercase English letters.
 *
 *
 * s = ddaaabbca t = abc
 *
 * so we are required to find the minimum string containing the string 't' in string 's'
 * 0 1 2 3 4 5 6 7 8
 * d d a a a b b c a
 * l
 * r
 *
 * ideally we would have to count all the occurences of each letter of 't' in 's'
 *
 * so the best way to do this is doing this way
 *
 * first store in a hash all elements frequencies of 't' with
 *
 * a = 1, b = 1, c = 1
 *
 *    0 1 2 3 4 5 6 7 8
 *    d d a a a b b c a
 *    l
 *    r
 *
 *    now when we are incrementing the r we are simply doing this decrementing each letter occurence by 1
 *    now if a letter is already pre-existing like 'a' in our case when a = 1 (already existing )
 *    we keep a counter 'cnt' which we increment when pre-existing letters like 'a' is present and greater than zero
 *
 *    d = - 2 a = 1 -> 0
 *    cnt = 1
 *
 *    now while increasing the right pointer we see the right when the at index = 7 we found a, b, c we have all the elements
 *    now what we do since cnt = 3 (== size(t) meaning we got a valid value) now we try to decrease the size of the window
 *    we keep doing till the cnt = 3
 *    we check if we get a better length
 *    and then
 *    we increase the values of each letter from the left again if the letters value while increasing we get a letter value
 *    (greater than ) > 0 we decrement the count
 *    and we move the left pointer
 *
 *    idea is let's say we have this string
 *
 *   s = aaabbcc  t = bc
 *
 *   now when we decrement 'a' first using right pointer we get
 *
 *   a = - 3
 *
 *   again when we increment 'a' first using left pointer we get
 *
 *   a = 0
 *
 *   this happens because there is no letter 'a' in the string 't'
 *
 *   ideally for 'b' we would have got the case but since we included from 't'
 *
 *   b = 1 (using the first loop in 't')
 *       -> 0 -> -1 (using right)
 *
 *  then using the left pointer
 *      -1 -> 0 -> 1
 *
 *   see what we observed here is if we have only characters in 's' and not in 't' like 'a' in string s
 *   we simply get 0 and nothing more
 *
 *   however if we include the 't' also we get positive values atleast depending on the occurences
 *
 *
 *
 *
 *
 *
 */
public class MinimumWindowSubstring {
  public String minWindow(String s, String t) {
    int left = 0, right = 0, n = s.length(), cnt = 0, minLength = (int) 1e9, startIndex = 0, hash[] = new int[256];

    for(char letter : t.toCharArray())
      hash[letter]++;

    while(right < n){
      char currentLetter = s.charAt(right);
      if(hash[currentLetter] > 0) cnt++;
      hash[currentLetter]--;

      while(cnt == t.length()){
        char currentLeftLetter = s.charAt(left);
        if(right - left + 1 < minLength){
          minLength = right - left + 1;
          startIndex = left;
        }
        hash[currentLeftLetter]++;
        if(hash[currentLeftLetter] > 0) cnt--;
        left++;
      }

      right++;
    }

    return minLength == (int)1e9 ? "" : s.substring(startIndex, startIndex + minLength);

  }
}
