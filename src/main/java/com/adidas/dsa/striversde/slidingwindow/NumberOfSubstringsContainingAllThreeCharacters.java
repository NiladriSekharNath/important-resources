package com.adidas.dsa.striversde.slidingwindow;

public class NumberOfSubstringsContainingAllThreeCharacters {

  /**
   *
   * Given a string s consisting only of characters a, b and c.
   *
   * Return the number of substrings containing at least one occurrence of all these characters a, b and c.
   *
   *
   *
   * Example 1:
   *
   * Input: s = "abcabc"
   * Output: 10
   * Explanation: The substrings containing at least one occurrence of the characters a, b and c are "abc", "abca", "abcab", "abcabc", "bca", "bcab", "bcabc", "cab", "cabc" and "abc" (again).
   * Example 2:
   *
   * Input: s = "aaacb"
   * Output: 3
   * Explanation: The substrings containing at least one occurrence of the characters a, b and c are "aaacb", "aacb" and "acb".
   * Example 3:
   *
   * Input: s = "abc"
   * Output: 1
   *
   *
   * Constraints:
   *
   * 3 <= s.length <= 5 x 10^4
   * s only consists of a, b or c characters.
   *
   *
   * idea for this problem is
   *
   * let's say we are given this string -> "abcabc" -> written spaced out for more clarity
   *
   * 0 1 2 3 4 5
   * a b c a b c
   *
   *
   * the brute force approach to this problem is generate all substrings
   *
   * in the above approach if we observe properly we are generating all the subsequences and keeping a
   * hash[0], hash[1], hash[2] for each entry 'a', 'b', 'c' if all is 1 we found then we simply cnt and add (j - i + 1)
   *
   *
   * f(String s) {
   *   n = s.length(), cnt = 0;
   *   for(i : 0 -> n) {
   *      int[] hash = new int[3];
   *     for(j : i -> n){
   *       hash[j - 'a'] = 1 ;
   *       if(hash[0] == 1 && hash[1] == 1 && hash[2] == 1)
   *        cnt += j - i + 1;
   *     }
   *   }
   *
   *   return cnt;
   *
   * }
   *
   * we could optimize the above approach if we see this one
   *
   * 0 1 2 3 4 5
   * a b c a b c
   * |   |
   * i   j
   *
   * see when we are at j = 2 we see that once we got "abc" we know now for sure that any substring after this 'j'
   * would always contribute to the
   * answer -> like these cases
   *
   * abca
   * abcab
   * abcabc
   *
   * so instead of calculating all we could simply
   *
   *
   * f(String s) {
   *    n = s.length(), cnt = 0;
   *    for(i : 0 -> n) {
   *      int[] hash = new int[3];
   *      for(j : i -> n){
   *      hash[j - 'a'] = 1 ;
   *      if(hash[0] == 1 && hash[1] == 1 && hash[2] == 1) {
   *        cnt += n - j + 1 ;
   *        break;
   *      }
   *    }
   *   }
   *
   *   return cnt;
   *
   *   }
   *
   *   we simply broke from the loop since we could calculate from the end directly + (n - j + 1)
   *
   *
   *  so keeping the above idea we are doing this
   *
   *  a b c a b c
   *        |
   *
   * at every index we are calculating the number of substrings that end at that position having all three characters
   *
   * and we keep a lastSeen of all the three elements the idea of this is we are firstly updating the lastSeen of every
   * element -> lastSeen[cI - 'a'] = cI;
   *
   * when
   *
   * 0 1 2 3 4 5
   * a b c a b c
   *       |
   *      right
   *
   * when we are at right = 3, we see the lastSeen of 'b' at -> 1, so once we have the window [1 - 3] -> [b, c, a]
   *
   * we see the lowest at b = 1, so window at index 3, 'bca', 'abca' is allowed so that's the important point
   *
   * so we add cnt = 2 ( 1 - 0 + 1)
   *
   *
   *
   *
   */
  public int numberOfSubstrings(String s) {
    char[] nums = s.toCharArray();
    int lastSeen[] = new int[]{-1, -1, -1}, cnt = 0, n = s.length();

    for (int cI = 0; cI < n; cI++) {
      lastSeen[nums[cI] - 'a'] = cI;
      if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
        int minValue = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
        cnt += minValue + 1 - 0;
      }
    }

    return cnt;
  }

  /**
   * this is a better written version
   * 
   */

  public int numberOfSubstringsBetter(String s) {
    char[] ch = s.toCharArray();
    int n = ch.length;
    int[] cnt = new int[3];

    for (int i = 0; i < 3; i++) {
      cnt[i] = -1;
    }

    int r = 0;
    int count = 0;

    while (r < n) {
      cnt[ch[r] - 'a'] = r;

      int min = Integer.MAX_VALUE;
      for (int i = 0; i < 3; i++) {
        min = Math.min(min, cnt[i]);
      }
      count = count + (min + 1);
      r++;
    }
    return count;
  }
}
