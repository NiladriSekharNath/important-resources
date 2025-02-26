package com.adidas.dsa.striversde.slidingwindow;

/**
 * You are given a string s and an integer k. You can choose any character of the string and
 * change it to any other uppercase English character. You can perform this operation at most k times.
 *
 * Return the length of the longest substring containing the same letter you can get after performing the above operations.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ABAB", k = 2
 * Output: 4
 * Explanation: Replace the two 'A's with two 'B's or vice versa.
 * Example 2:
 *
 * Input: s = "AABABBA", k = 1
 * Output: 4
 * Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
 * The substring "BBBB" has the longest repeating letters, which is 4.
 * There may exists other ways to achieve this answer too.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 105
 * s consists of only uppercase English letters.
 * 0 <= k <= s.length
 *
 *
 *
 * Let's understand using this example :  AABABBA k = 1
 * 0 1 2 3 4 5 6
 * A A B A B B A
 *
 *
 * Let's first consider the number of changes required for the index -> 0 - 4
 *
 * A = 3
 * B = 2
 *
 * if we think like this the total size of this index range = 4 - 0 + 1 = 5 (length substring)
 *
 * in order to change we need to find the letter with the maximum frequency -> and convert other letters to that giving us the
 * minimal cost
 *
 * A = 3, B = 2, cost to us is 5 - 3 = 2 (that means B needs to be changed)
 *
 * Here the catch is if we did with the B or any other lower frequency character (if present) we would have needed
 *
 * 5 - 2 = 3 ( Cost to move 'A' to 'B')
 *
 *
 * so this is the main idea to for this question
 *
 * Keeping track of the maximum frequency and subtracting that from the current length
 *
 *
 *
 */
public class LongestRepeatingCharacterReplacement {
  /**
   *
   * TC : O((n + n) * 26) -> the inside loop is moving 26 times because we are checking for the maxF everytime
   * SC : O(26)
   */

  public int characterReplacement(String s, int k) {
    int hash[] = new int[26], left = 0, right = 0, n = s.length(), maxF = 0, maxLength = 0;
    while(right < n){
      char currentLetter = s.charAt(right);

      hash[currentLetter - 'A']++;

      maxF = Math.max(hash[currentLetter - 'A'], maxF);

      /**
       * same logic as other sliding pointer questions here when our max length allowed for the cost greater than 'k'
       * we have to move the left pointer to the right and re-calculate the 'maxF' which we are doing here
       *
       */

      while((right - left + 1) - maxF > k){
        hash[s.charAt(left) - 'A']--;
        maxF = 0;
        for(int i = 0; i < 26; i++){
          maxF = Math.max(maxF, hash[i]);
        }
        left++;
      }

      if((right - left + 1) - maxF <= k){
        maxLength = Math.max(maxLength, right - left + 1);
      }

      right++;

    }

    return maxLength;
  }

  /**
   *
   * Key reduction :
   * When the window becomes invalid (i.e., the number of characters to change exceeds k), we shrink the window by moving left.
   *
   * Key insight: Shrinking the window by moving left never gives us a better maxF — it can only reduce the frequency of characters.
   * But reducing maxF isn’t helpful, because the largest possible valid window length for the current maxF has already been checked.
   * We only care about the maximum valid window length, so we don’t need to recalculate maxF after shrinking.
   *
   * The condition (right - left + 1) - maxF > k already decides when the window is invalid — recalculating maxF wouldn’t change that logic.
   *
   * Consider for this example
   *
   * AAABBAA , k = 2
   *
   * 0 1 2 3 4 5 6
   * A A A B B A A
   *
   * 0 - 4
   *
   * we get this value which is valid and we calculate maxF
   *
   * when we move left we are reducing the the value we would not get a better maxF for sure
   *
   * so this portion in the inner while loop controlled by "left" pointer is useless
   *
   * for(int i = 0; i < 26; i++){
   *           maxF = Math.max(maxF, hash[i]);
   * }
   *
   * removing that portion
   *
   *
   */

  public int characterReplacementBetter(String s, int k) {
    int hash[] = new int[26], left = 0, right = 0, n = s.length(), maxF = 0, maxLength = 0;
    while(right < n){
      char currentLetter = s.charAt(right);

      hash[currentLetter - 'A']++;

      maxF = Math.max(hash[currentLetter - 'A'], maxF);

      /**
       * same logic as other sliding pointer questions here when our max length allowed for the cost greater than 'k'
       * we have to move the left pointer to the right and re-calculate the 'maxF' which we are doing here
       *
       */

      if((right - left + 1) - maxF > k){
        hash[s.charAt(left) - 'A']--;
        maxF = 0;

        left++;
      }

      if((right - left + 1) - maxF <= k){
        maxLength = Math.max(maxLength, right - left + 1);
      }

      right++;

    }

    return maxLength;
  }

}
