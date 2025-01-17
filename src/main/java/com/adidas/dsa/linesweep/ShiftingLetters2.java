package com.adidas.dsa.linesweep;

/**
 * You are given a string s of lowercase English letters and a 2D integer array shifts where shifts[i] = [starti, endi, directioni]. For every i, shift the characters in s from the index starti to the index endi (inclusive) forward if directioni = 1, or shift the characters backward if directioni = 0.
 *
 * Shifting a character forward means replacing it with the next letter in the alphabet (wrapping around so that 'z' becomes 'a'). Similarly, shifting a character backward means replacing it with the previous letter in the alphabet (wrapping around so that 'a' becomes 'z').
 *
 * Return the final string after all such shifts to s are applied.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
 * Output: "ace"
 * Explanation: Firstly, shift the characters from index 0 to index 1 backward. Now s = "zac".
 * Secondly, shift the characters from index 1 to index 2 forward. Now s = "zbd".
 * Finally, shift the characters from index 0 to index 2 forward. Now s = "ace".
 * Example 2:
 *
 * Input: s = "dztz", shifts = [[0,0,0],[1,1,1]]
 * Output: "catz"
 * Explanation: Firstly, shift the characters from index 0 to index 0 backward. Now s = "cztz".
 * Finally, shift the characters from index 1 to index 1 forward. Now s = "catz".
 *
 * we are applying line sweep and adding the characters [start, end] -> start, end + 1
 */
public class ShiftingLetters2 {
  public String shiftingLetters(String s, int[][] shifts) {


    int n = s.length();
    int[] maxShifts = new int[n];

    for (int[] shift : shifts) {
      int start = shift[0], end = shift[1] + 1, dir = shift[2] == 1 ? +1 : -1;

      maxShifts[start] = maxShifts[start] + dir;
      if (end < n)
        maxShifts[end] = maxShifts[end] + ((-1) * dir);
    }

    for (int i = 1; i < s.length(); i++) {
      maxShifts[i] = maxShifts[i] + maxShifts[i - 1];
    }


    char[] result = new char[n];
    for (int i = 0; i < n; i++) {
      int shift = maxShifts[i];
      int o = s.charAt(i) - 'a';
      int sc = (o + shift) % 26;
      if (sc < 0) {
        sc += 26;
      }
      result[i] = (char) ('a' + sc);
    }


    return new String(result);


  }

  public static void main(String[] args) {
    new ShiftingLetters2().shiftingLetters("abc", new int[][]{{0, 1, 0}, {1, 2, 1}, {0, 2, 1}});
    new ShiftingLetters2().shiftingLetters("dztz", new int[][]{{0, 0, 0}, {1, 1, 1}});
  }
}
