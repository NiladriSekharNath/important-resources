package com.adidas.dsa.striversde.binarysearch;

public class NthRoot {
  public static int NthRoot(int n, int m) {

    int left = 1, right = m, mid = 0;

    while (left <= right) {
      mid = left + (right - left) / 2;
      int midValue = getMidValue(mid, n, m);
      if (midValue == 1) return mid;
      else if (midValue == 2) right = mid - 1;
      else left = mid + 1;
    }
    return -1;

  }

  /**
   * we are taking the mid and performing mid^n and checking if that is equal to m
   * now the point is we don't need to find the entire mid^n we are just interested if that value during computation exceeds m
   * <p>
   * we are simply returning this way if the value == n we are returning 1;
   * if the value exceeds n during computation, value > n return 2;
   * else if less than n we return 1;
   *
   * @param mid : the value we are calculating to find the mid
   * @param n : the to the power, mid ^ n
   * @param m : the target value mid ^ n  == m (ideally)
   * @return
   */
  private static int getMidValue(int mid, int n, int m) {

    long value = 1;
    for (int i = 1; i <= n; i++) {
      value = value * mid;
      if (value > m) return 2;
    }
    if (value == m) return 1;
    return 0;
  }

  public static void main(String[] args) {
    new NthRoot().NthRoot(3, 27);
  }
}
