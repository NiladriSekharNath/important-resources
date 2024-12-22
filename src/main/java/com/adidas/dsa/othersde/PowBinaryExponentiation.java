package com.adidas.dsa.othersde;

/**
 * for more details on the exponentiation
 *
 * https://www.geeksforgeeks.org/binary-exponentiation-for-competitive-programming/
 *
 */
public class PowBinaryExponentiation {
  public double myPow(double x, int n) {

    if (n == Integer.MIN_VALUE) {
      // Special handling for Integer.MIN_VALUE
      return 1.0 / (myPow(x, Integer.MAX_VALUE) * x);
    }
    /**
     * or we could take long and help with the overflow case since Integer.MIN_VALUE cannot be made positive without overflowing hence we took
     * long
     *
     * long nn = n ;
     *     if (nn < 0) {
     *       nn = -nn;
     *       x = 1.0/x;
     *     }
     *     return calPow(x, nn);
     */
    if (n < 0) {
      n = -n;
      x = 1.0/x;
    }
    return calPow(x, n);


  }

  /**
   *
   * this works let's say we need to find x, n, -> 2^n -> 2^10
   *
   * 10 -> 8 -> 4 -> 2 -> 1
   */

  private double calPow(double x, long n) {
    double result = 1.0;
    while (n > 0) {
      if ((n & 1) == 1) {
        result = result * x;
        n -= 1;
      }

      x = x * x;
      n = n >>> 1;
    }

    return result;
  }

  public static void main(String[] args){
    new PowBinaryExponentiation().myPow(2.00000, -2147483648);
  }
}
