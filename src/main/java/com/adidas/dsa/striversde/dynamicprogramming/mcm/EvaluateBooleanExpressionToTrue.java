package com.adidas.dsa.striversde.dynamicprogramming.mcm;

import java.util.Arrays;

/**
 * Problem statement:
 *
 * You are given an expression 'exp' in the form of a string where operands will be : (TRUE or FALSE),
 * and operators will be : (AND, OR or XOR).
 *
 *
 *
 * Now you have to find the number of ways we can parenthesize the expression such that it will evaluate to TRUE.
 *
 *
 *
 * As the answer can be very large, return the output modulo 1000000007.
 *
 *
 *
 * Note :
 *
 * ‘T’ will represent the operand TRUE.
 * ‘F’ will represent the operand FALSE.
 * ‘|’ will represent the operator OR.
 * ‘&’ will represent the operator AND.
 * ‘^’ will represent the operator XOR.
 * Example :
 *
 * Input: 'exp’ = "T|T & F".
 *
 * Output: 1
 *
 * Explanation:
 * There are total 2  ways to parenthesize this expression:
 *     (i) (T | T) & (F) = F
 *     (ii) (T) | (T & F) = T
 * Out of 2 ways, one will result in True, so we will return 1.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Sample Input 1 :
 * T^T^F
 * Sample Output 1 :
 * 0
 * Explanation For Sample Input 1:
 * There are total 2  ways to parenthesize this expression:
 * (i) (T^T)^(F) = F
 * (ii) (T)^(T^F) = F
 * Both ways will result in False, so we will return 0.
 * Sample Input 2 :
 * F|T^F
 * Sample Output 2 :
 * 2
 * Explanation For Sample Input 2:
 * For the first test case:
 * There are total 2  ways to parenthesize this expression:
 * (i) (F|T)^(F) = T
 * (ii) (F)|(T^F) = T
 * Both ways will result in True, so we will return 2.
 * Expected time complexity
 * The expected time complexity is O(n ^ 3), where 'n' denotes the length of 'exp'.
 * Constraints:
 * 3 <= |‘exp’| <= 200
 * Where |'exp'| denotes the length of 'exp'.
 *
 * Time Limit: 1 sec
 *
 *
 * Given an expression we need to count the ways the expression result to true
 *
 *      Striver's Approach
 *
 *      T ^ F | T & F ^ T | F
 *      0 1 2 3 4 5 6 7 8 9 10
 *
 *     to count the ways the expression result in True
 *
 *     we can try these ways that are shown below so this looks like MCM where we part the problem in solve for parts
 *
 *     (T) ^ (F | T & F ^ T | F)
 *
 *     (T ^ F ) | (T & F ^ T | F)
 *
 *     (T ^ F | T) & (F ^ T | F)
 *
 *     now if we see below we get any ways so let's start evaluating the expression in f(0, 10)
 *
 *     f(low, high) -> f(0, 10)
 *
 *     if we see above operands are present in 1, 3, 5, 7, 9
 *
 *     so start/k -> [low + 1, high - 1] -> 1 -> 9 incrementing by +2
 *
 *     we are writing using 'k'
 *
 *     So leftFunction -> f1(low, k - 1), rightFunction -> f2(k + 1, high)
 *
 *     f(0, 10)
 *
 *     for, k = 5
 *
 *     (T ^ F | T) & (F ^ T | F)
 *      0 1 2 3 4  5  6 7 8 9 10
 *
 *      f1(0, 4)      f2(6, 10)
 *
 *      Now here is the idea we need to take one extra variable for counting
 *
 *      isTrue -> 0, 1
 *
 *      0 -> false
 *      1 -> true
 *
 *
 *      Now the main point to this question is we have operands like &, |, ^
 *
 *      for the left function and right function we are counting the number of ways when '&'
 *
 *        for the '&' operand:
 *
 *          f1()                    f2()
 *          true: x1 ways           true: x3 ways
 *          false: x2 ways          false: x4 ways
 *
 *          now here we are counting the number of ways so
 *          to get
 *
 *          for true we need = (x1 * x3) ways -> (as both has to be true)
 *
 *          for false -> we also need to count ways = (x1 * x4) + (x2 * x3) + (x2 * x4)
 *                                                     T * F      F * T       F * F
 *
 *         for the '|' operand:
 *
 *            f1()                    f2()
 *            true: x1 ways           true: x3 ways
 *            false: x2 ways          false: x4 ways
 *
 *            now here we are counting the number of ways so
 *            to get
 *
 *            for true we need = (x1 * x3) + (x2 * x3) + (x1 * x4)
 *                                 T | T      F | T       T | F
 *
 *            for false -> we also need to count ways =  (x2 * x4)
 *                                                        F | F
 *
 *         for the '^' operand:
 *
 *            f1()                    f2()
 *            true: x1 ways           true: x3 ways
 *            false: x2 ways          false: x4 ways
 *
 *            now here we are counting the number of ways so
 *            to get
 *
 *            for true we need = (x1 * x4) + (x2 * x3)
 *                                T  ^  F     F  ^ T
 *
 *            for false -> we also need to count ways = (x1 * x3) + (x2 * x4)
 *                                                       T ^ T       F ^ F
 *
 *
 *   now we are calling the ways = f(0, n - 1, 1) -> as we need true
 *
 *   we are counting the ways if 1/True
 *
 *   since we need both 1/True and 0/False number of ways
 *
 *   so we write the by condition for every operand (&, |, ^)
 *
 *   for the base case:
 *
 *    if (low > high) return 0;
 *
 *   this one above we wrote because obviously if low > high we return 0 ways (though this is a simple safety check)
 *
 *
 *
 *   for this one when low == high
 *
 *   we check if we are at T or F
 *
 *    if we are counting 'T;  if the character at low == 'T' then we return 1 way
 *    or if the character 'F' if the at low == 'F' we return 1 way
 *
 *      if (low == high) {
 *           if (isTrue == 1) return exp.charAt(low) == 'T' ? 1 : 0;
 *          else return exp.charAt(low) == 'F' ? 1 : 0;
 *      }
 *
 *
 *
 * Also the mod we are following the steps
 *
 * for ways += way1 * way2 + way3 * way4
 *
 * we are making this
 *
 * ways = (ways + (way1 * way2) % mod + (way3 * way4) % mod) % mod
 *
 * also we are taking long to avoid overflowing moding and finally when returning
 *
 * we are doing this (int) longValue;
 *
 *
 * Note :
 *
 * We use multiplication (`count * count`)
 * because we are counting independent ways to evaluate the left and right subexpressions
 * that lead to a desired outcome.
 * Each valid way of evaluating the left subexpression
 * can be paired with each valid way of evaluating the right subexpression.
 * This follows the 'Rule of Product' in combinatorics,
 * which states that if one event can occur in  x  ways and another independent event can occur in  y  ways,
 * then both together can occur in x times y  ways.
 *
 * For example:
 * - If we are evaluating `(A & B)` to `True`, both `A` and `B` must be `True`.
 *   If `A` can be `True` in `x1` ways and `B` can be `True` in `x3` ways, then the total ways to make `(A & B)` `True` is `x1 * x3`.
 * - Similarly, for `(A | B)`, it is `True` if at least one operand is `True`, leading to multiple valid pairings.
 *
 * This multiplication ensures we count all possible parenthesizations correctly.
 *
 *
 *
 *
 */
public class EvaluateBooleanExpressionToTrue {

  /**
   * My solution without seeing anything on first attempt
   *
   * Note this solution will not work unless used long or properly using mod
   *
   * for my solution we are doing in little different however most of the idea is same instead of the operator
   * I did partition on the operand
   *
   *            (T ^ F | T) & (F ^ T | F)
   *             0 1 2 3 4  5  6 7 8 9 10
   *
   *            f(0, n - 1) this is same
   *
   *            but k = 0, 2, 4, 6, 8, 10 -> incrementing by +2
   *
   *
   *            k = 0
   *
   *            (T) ^ (F | T & F ^ T | F)
   *             0  1  2 3 4  5 6 7 8 9 10
   *
   *            so leftFunction f1(low, start) -> f(0, 0) , rightFunction f2(start + 2, high) -> f(2, 10)
   *
   *            so operands are at (start + 1) position rest are same
   *
   *
   */
  public static int evaluateExp(String exp) {
    return helper(0, exp.length() - 1, exp).posCount;
  }

  private static Pair helper(int low, int high, String exp) {
    int mod = (int) 1e9 + 7;
    if (low > high) return new Pair();
    if (low == high) {

      if (exp.charAt(low) == 'T') return new Pair(1, 0);
      else if (exp.charAt(low) == 'F') return new Pair(0, 1);
      else return new Pair();

    }
    int countPos = 0, countNeg = 0;
    for (int start = low; start <= high - 1; start += 2) {

      Pair left = helper(low, start, exp), right = helper(start + 2, high, exp);

      if (exp.charAt(start + 1) == '&') {

        countPos += (left.posCount * right.posCount) % mod;
        countNeg += ((left.negCount * right.negCount) % mod + (left.posCount * right.negCount) % mod + (left.negCount * right.posCount) % mod) % mod;

      } else if (exp.charAt(start + 1) == '|') {

        countPos += ((left.posCount * right.posCount) % mod + (left.negCount * right.posCount) % mod +
            (left.posCount * right.negCount) % mod) % mod;
        countNeg += (left.negCount * right.negCount) % mod;

      } else if (exp.charAt(start + 1) == '^') {

        countPos += ((left.posCount * right.negCount) % mod + (left.negCount * right.posCount) % mod) % mod;
        countNeg += ((left.posCount * right.posCount) % mod + (left.negCount * right.negCount) % mod) % mod;

      }

    }

    return new Pair(countPos, countNeg);
  }

  private static class Pair {
    int posCount = 0;
    int negCount = 0;

    public Pair() {

    }

    public Pair(int posCount, int negCount) {
      this.posCount = posCount;
      this.negCount = negCount;
    }
  }

  /**
   *
   * Strivers expression we wrote the explanation
   */


  public static int evaluateExpStrivers(String exp) {
    int n = exp.length();

    long[][][] dp = new long[n][n][2];

    for (long[][] grid : dp) {
      for (long[] row : grid) {
        Arrays.fill(row, -1);
      }
    }

    return (int) helperStrivers(0, n - 1, 1, exp, dp);
  }

  private static long helperStrivers(int low, int high, int isTrue, String exp, long[][][] dp) {

    if (low > high) return 0;

    if (low == high) {
      if (isTrue == 1) return exp.charAt(low) == 'T' ? 1 : 0;
      else return exp.charAt(low) == 'F' ? 1 : 0;
    }

    if (dp[low][high][isTrue] != -1) return dp[low][high][isTrue];

    long ways = 0, mod = (int) 1e9 + 7;

    for (int start = low + 1; start <= high - 1; start += 2) {

      long leftTrue = helperStrivers(low, start - 1, 1, exp, dp),
          leftFalse = helperStrivers(low, start - 1, 0, exp, dp),
          rightTrue = helperStrivers(start + 1, high, 1, exp, dp),
          rightFalse = helperStrivers(start + 1, high, 0, exp, dp);

      if (exp.charAt(start) == '&') {
        if (isTrue == 1) {
          ways = (ways + leftTrue * rightTrue) % mod;
        } else
          ways = (ways + (leftTrue * rightFalse) % mod +
              (leftFalse * rightTrue) % mod +
              (leftFalse * rightFalse) % mod) % mod;
      } else if (exp.charAt(start) == '|') {
        if (isTrue == 1) {
          ways = (ways + (leftTrue * rightTrue) % mod + (leftTrue * rightFalse) % mod + (leftFalse * rightTrue) % mod) % mod;
        } else
          ways = (ways + leftFalse * rightFalse) % mod;
      } else if (exp.charAt(start) == '^') {
        if (isTrue == 1) {
          ways = (ways + (leftTrue * rightFalse) % mod + (leftFalse * rightTrue) % mod) % mod;
        } else
          ways = (ways + (leftTrue * rightTrue) % mod + (leftFalse * rightFalse) % mod) % mod;
      }


    }

    return dp[low][high][isTrue] = ways;
  }

  /**
   * top down ->
   * low -> 0 -> n -1
   * high -> n - 1 -> 0
   * <p>
   * bottoms up
   * row/low -> n - 1 -> 0
   * col/high -> 0 -> n - 1
   */
  public static int tabulation(String exp) {
    int n = exp.length(), MOD = (int) 1e9 + 7;
    long[][][] dp = new long[n][n][2];


    for (int low = n - 1; low >= 0; low--) {
      for (int high = 0; high <= n - 1; high++) {

        if (low > high) continue;

        for (int isTrue = 0; isTrue <= 1; isTrue++) {
          // Base case 1:
          if (low == high) {
            if (isTrue == 1) dp[low][high][isTrue] = exp.charAt(low) == 'T' ? 1 : 0;
            else dp[low][high][isTrue] = exp.charAt(low) == 'F' ? 1 : 0;
            continue;
          }

          // Recurrence logic:
          long ways = 0;
          for (int ind = low + 1; ind <= high - 1; ind += 2) {
            long leftTrue = dp[low][ind - 1][1];
            long leftFalse = dp[low][ind - 1][0];
            long rightTrue = dp[ind + 1][high][1];
            long rightFalse = dp[ind + 1][high][0];

            char operator = exp.charAt(ind);
            if (operator == '&') {
              if (isTrue == 1) ways = (ways + (leftTrue * rightTrue) % MOD) % MOD;
              else
                ways = (ways + (leftFalse * rightTrue) % MOD + (leftTrue * rightFalse) % MOD + (leftFalse * rightFalse) % MOD) % MOD;
            } else if (operator == '|') {
              if (isTrue == 1)
                ways = (ways + (leftFalse * rightTrue) % MOD + (leftTrue * rightFalse) % MOD + (leftTrue * rightTrue) % MOD) % MOD;
              else ways = (ways + (leftFalse * rightFalse) % MOD) % MOD;
            } else {
              if (isTrue == 1) ways = (ways + (leftFalse * rightTrue) % MOD + (leftTrue * rightFalse) % MOD) % MOD;
              else ways = (ways + (leftFalse * rightFalse) % MOD + (leftTrue * rightTrue) % MOD) % MOD;
            }
          }
          dp[low][high][isTrue] = ways;
        }
      }
    }
    return (int) dp[0][n - 1][1];
  }


}
