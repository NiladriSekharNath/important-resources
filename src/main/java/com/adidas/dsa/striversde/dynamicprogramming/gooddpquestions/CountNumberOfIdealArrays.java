package com.adidas.dsa.striversde.dynamicprogramming.gooddpquestions;

import java.util.Arrays;

public class CountNumberOfIdealArrays {


  public int idealArrays(int n, int maxValue) {
    long count = 0;

    int[][] dp = new int[maxValue + 1][n + 1];

    for (int[] row : dp)
      Arrays.fill(row, -1);

    for (int startVal = 1; startVal <= maxValue; startVal++) {
      count = (count + helper(startVal, 1, n, maxValue, dp)) % MOD;
    }


    return (int) count;
  }

  private int helper(int startVal, int count, int n, int maxValue, int[][] dp) {
    if (count == n) {
      return 1;
    }
    long result = 0;

    if (dp[startVal][count] != -1) return dp[startVal][count];

    for (int multiplier = 1; startVal * multiplier <= maxValue; multiplier++) {
      result = (result + helper(startVal * multiplier, count + 1, n, maxValue, dp)) % MOD;
    }

    return dp[startVal][count] = (int) result;
  }

/**
   The constant MOD is used to take remainders to avoid overflow.
   We always compute answers modulo 1e9+7 (a large prime) as per many competitive programming problems.
 */
  private final long MOD = (long) 1e9 + 7;

  /**
   * Main function to compute the number of ideal arrays.
   *
   * An "ideal array" is constructed based on the following:
   * - It has length n, and every element is between 1 and maxValue (inclusive).
   * - Each array element divides the next one, forming a multiplicative sequence.
   *
   * The solution uses two ideas:
   *   1. Count how many valid multiplicative sequences (of various lengths) can be formed.
   *      - `dp[val][len]` stores the number of sequences ending with 'val' with length 'len'.
   *      - `count[len]` aggregates these counts across all 'val'.
   *   2. Compute the number of ways to "expand" such sequences into arrays of length n.
   *      - This uses combination (n-1 choose len-1) because we consider the positions where
   *        the sequence values appear in a non-decreasing array.
   *
   * @param n         The length of the ideal array.
   * @param maxValue  Maximum possible value in the array.
   * @return          The total number of ideal arrays modulo MOD.
   */
  public int idealArraysOptimized(int n, int maxValue) {
    // count[i] will hold the total count of valid sequences of length i (1-indexed).
    // We only need to track sequence lengths up to 14 because for numbers up to 10^4,
    // the maximum length of such a sequence is limited.
    long count[] = new long[15];

    // dp[val][len] is our DP table where for each value 'val' (from 1 to maxValue),
    // we store the count of valid multiplicative sequences ending with val with length 'len'.
    long dp[][] = new long[maxValue + 1][15];

    long result = 0;

    // For every possible value from 1 to maxValue, compute the valid sequences ending at that value.
    for (int val = 1; val <= maxValue; val++) {
      findSets(val, count, dp);
    }

    // Precompute factorials modulo MOD for the combination calculations,
    // which are used to determine the number of ways to place the valid sequence into an array of length n.
    long factorial[] = new long[n + 1];
    factorial[0] = 1;
    for (int cI = 1; cI < n + 1; cI++){
      factorial[cI] = (factorial[cI - 1] * cI) % MOD;
    }

    // Now, for each possible sequence length (from 1 to min(n, 14)),
    // compute the number of ways to embed that sequence in an array of length n.
    // "len" corresponds to the number of sequence elements.
    for (int len = 1; len <= Math.min(n, 14); len++) {
      // If no valid sequence with this length exists, skip.
      if (count[len] == 0) continue;

      // Calculate the number of ways to choose positions for the 'len' sequence elements in an array of length n.
      // This is given by combination: C(n-1, len-1).
      long possibilities = modularNCR(n - 1, len - 1, factorial);

      // Multiply the number of valid sequences (of this length) with the number of ways to arrange them,
      // and add it to the final result.
      result = (result + (possibilities * count[len]) % MOD) % MOD;
    }

    // Return the final result as an integer.
    return (int) result;
  }

  /**
   * Recursive function to count valid multiplicative sequences.
   *
   * This function uses recursion (with memoization) to build up the count of sequences.
   * Explanation in simple terms:
   *
   * - If dp[m][1] is not zero, then we have already processed the number 'm';
   *   so simply return to avoid recomputation.
   *
   * - For the base case, every number 'm' itself is a valid sequence of length 1.
   *
   * - Then, for every divisor 'div' (starting from 2 up to m),
   *   if 'div' divides 'm', we compute sequences for m/div first.
   *   Then, for each sequence ending at (m/div) of a given length 'len', we can form
   *   a new sequence ending at 'm' of length 'len+1' (because 'm' is divisible by (m/div)).
   *
   *
   *
   *
   *   dp[m][len] -> holds the dp[values ending at m][length] holds the state 'ending at m all lengths]
   *
   *   dp[8][1] -> holds the count of sets which are ending at 8, and of length = 1, example : {8}
   *   dp[8][2] -> holds the count of sets which are ending at 8, and of length = 2, example : {1, 8}, {2, 8}, {4, 8}..
   *
   *    meaning let's say sequence ending at ...8 [(1, 8), (8) ] ...16, n = 2(let's say)
   *    *
   *    *   so every set of dp[8][2] => would add to result dp[16][3]
   *    *
   *    *   => dp[16][3] += dp[8][2] (because all paths ending at 8 length = 2, we get {{1, 8}, {2, 8}, {4, 8}}
   *
   * - We propagate the counts upward, updating both dp and the global count array.
   *
   * @param m      The current number whose sequences we want to count.
   * @param count  Global array that aggregates sequence counts by length.
   * @param dp     The DP table storing counts for specific values and sequence lengths.
   */
  private void findSets(int m, long[] count, long[][] dp) {
    // If dp[m][1] != 0, it means we already processed m (its base sequence exists).
    if (dp[m][1] != 0) return;

    // Base case: Every number m is a sequence of length 1 by itself.
    dp[m][1] = 1;
    count[1]++; // Increment global count for sequences of length 1.

    // Try every possible divisor from 2 up to m.
    for (int div = 2; div <= m; div++) {
      // If 'div' is a divisor of m, then m % div == 0.
      if (m % div == 0) {
        // Recursively process the smaller number (m/div) to ensure its sequences are counted.
        findSets(m / div, count, dp);

        // For each valid sequence length at (m/div), we can extend it to a sequence ending at m.
        for (int len = 1; len < 15; len++) {
          if (dp[m / div][len] != 0) {
            // Add the count from (m/div) to m for sequences of length (len+1).
            dp[m][len + 1] = (dp[m][len + 1] + dp[m / div][len]) % MOD;

            // Also, update the global count for sequences of length (len+1).
            count[len + 1] = (count[len + 1] + dp[m / div][len]) % MOD;
          }
        }
      }
    }
  }

  /**
   * Compute the modular combination nCr modulo MOD.
   *
   * This function calculates "n choose r" (number of ways to choose r elements from n)
   * under modulo arithmetic using precomputed factorials.
   *
   * To compute nCr % MOD, we use the formula:
   *    nCr = n! / (r! * (n - r)!)
   * And then we use Fermat's Little Theorem to compute the modular inverse:
   *    a^(MOD-2) % MOD gives the modular inverse of a under modulus MOD.
   *
   * @param n          Total number of items.
   * @param r          Number of items to choose.
   * @param factorial  Precomputed factorials (mod MOD) from 0! to n!.
   * @return           The combination nCr modulo MOD.
   */
  private long modularNCR(int n, int r, long[] factorial) {
    // If r is less than 0 or more than n, it's an invalid scenario. Return 0.
    if (r < 0 || r > n) {
      return 0;
    }

    // numerator = n! (factored value)
    long numerator = factorial[n];

    // denominator = r! * (n - r)! (mod MOD)
    long denominator = (factorial[r] * factorial[n - r]) % MOD;

    // Using Fermat's Little Theorem, we compute the modular inverse of the denominator:
    // The modular inverse is pow(denominator, MOD - 2) modulo MOD.
    //(1/2) % mod -> (2^-1) % mod
    // 2^-1 % mod => 2^(mod - 2) % mod
    return (numerator * pow(denominator, MOD - 2)) % MOD;
  }

  /**
   * Compute the power (base^exp) modulo MOD using fast binary exponentiation.
   *
   * Explanation in layman terms:
   * - Binary exponentiation is like splitting the exponent into powers of 2.
   * - We multiply the base when the current bit of the exponent is 1.
   * - This method is much faster than simply multiplying in a loop, especially for large exponents.
   *
   * @param base  The base number.
   * @param exp   The exponent.
   * @return      The value of (base^exp) % MOD.
   */
  private long pow(long base, long exp) {
    long result = 1;
    // Reduce base modulo MOD first to keep numbers smaller.
    base %= MOD;

    // While we still have bits to process in the exponent:
    while (exp > 0) {
      // If the current (lowest) bit of exp is 1, multiply result by base.
      if (exp % 2 != 0) {
        result = (result * base) % MOD;
      }

      // Square the base for the next bit.
      base = (base * base) % MOD;

      // Divide exp by 2 (shift right by 1 bit).
      exp /= 2;
    }

    return result;
  }

}
