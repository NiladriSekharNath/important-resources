package com.adidas.dsa.striversde.array;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class FindPrimesInTillNUsingSieve {

  /**
   * @param n
   * @return idea of this approach is we are trying to find the primes in the range uptill n
   * <p>
   * Idea :
   * we start from 2 (as it's the first prime number)
   * <p>
   * we take a number and do this if a number is marked as 1 (which means it is prime)
   * and start marking all its multiple as 0 (as all it's multiples are not prime till n)
   * <p>
   * ex : starting at i = 2 and n = 30
   * <p>
   * we mark
   * <p>
   * 4 (2 * 2) = 0
   * 6 (2 * 3)
   * 8
   * 10
   * 12
   * ...
   * 30 (2 * 15)  = 0
   * <p>
   * the inner 'j' loop goes like ->
   * <p>
   * starts from,
   * 4
   * 6 (j += i, i = 2)
   * 8
   * 10...
   * <p>
   * similarly for 3
   * <p>
   * 6 (j += i, i = 3)
   * 9
   */
  public List<Integer> findPrimes(int n) {
    int nums[] = new int[n + 1];

    Arrays.fill(nums, 1);

    for (int i = 2; i <= n; i++) {
      if (nums[i] == 1) {
        for (int j = 2 * i; j <= n; j += i) {
          nums[j] = 0;
        }
      }
    }

    List<Integer> result = new ArrayList<>();

    for (int i = 2; i <= n; i++) {
      if (nums[i] == 1) {
        result.add(i);
      }
    }

    return result;
  }

  /**
   *
   * @param n
   * @return
   *
   * the above approach is fine however we can do better,
   *
   * Key Observation 1 :
   *
   * 2 * 2        3 * 2      5 * 2      7 * 2
   * 2 * 3        3 * 3      5 * 3      7 * 3
   * 2 * 4        3 * 4      5 * 4      7 * 4
   * ...          ...        5 * 5      7 * 5
   *                         ...        7 * 6
   *                                    7 * 7
   *
   *
   * if you see above for 5 or 7 for case of larger prime numbers there are also few duplicates iterations that we are still
   * doing that can be ignored, like in case of 7 * 2, 7 * 3, 7 * 4, 7 * 5, 7 * 6 is already marked previously therefore
   * there is no point to that
   *
   * so ideally we can straightaway start from 2 * 2 or 3 * 3 or 5 * 5 or 7 * 7,
   * that means j = i * i
   *
   *
   * Key Observation 2 :
   *
   * let's say we try for ->  7 * 7 = 49 which is > 30 so no point till running even higher like 8 * 8, ... 10 * 10 we
   * can stop the outer loop till
   *
   * square root (n)
   *
   * for(int i = 2 ; i <= n ; i++)
   *
   * for(int i = 2; i * i <= n; i++)
   *
   *
   * TC : 0(n) + 0(nlog(log(n))) + 0(n)
   * SC : 0(n) for filling the primes array
   * *
   * first 0(n) -> for filling the Arrays.fill
   * second 0(n) -> for seeing which are prime and adding to the resultList
   *
   *
   */

  public List<Integer> findPrimesEfficient(int n) {

    int nums[] = new int[n + 1];

    Arrays.fill(nums, 1);

    for (int i = 2; i * i <= n; i++) {
      if (nums[i] == 1) {
        for (int j = i * i; j <= n; j += i) {
          nums[j] = 0;
        }
      }
    }

    List<Integer> result = new ArrayList<>();

    for (int i = 2; i <= n; i++) {
      if (nums[i] == 1) {
        result.add(i);
      }
    }

    return result;
  }



  public static void main(String[] args) {
    long startTime = System.currentTimeMillis();
    log.info("All Primes till n : 30 -> {}", new FindPrimesInTillNUsingSieve().findPrimes(10000));

    log.info("Duration : {} millis", (System.currentTimeMillis() - startTime));

    startTime = System.currentTimeMillis();

    log.info("All Primes till n : 30 -> {}", new FindPrimesInTillNUsingSieve().findPrimesEfficient(10000));

    log.info("Duration : {} millis", (System.currentTimeMillis() - startTime));
  }
}
