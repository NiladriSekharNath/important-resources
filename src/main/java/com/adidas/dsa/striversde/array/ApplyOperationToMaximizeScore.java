package com.adidas.dsa.striversde.array;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

@Slf4j
public class ApplyOperationToMaximizeScore {

  private final long mod = (long) 1e9 + 7;

  private int[] countPrimes ;

  public ApplyOperationToMaximizeScore(){

    this.countPrimes = new int[100001];
    countPrimes();
  }

  public int maximumScore(List<Integer> nums, int k) {
    int size = nums.size(), left[] = new int[size], right[] = new int[size];



    long maxScore = 1;

    Stack<Integer> stack = new Stack<>();

    for(int cI = 0 ; cI < size; cI++){
      while(!stack.isEmpty() && countPrimes[nums.get(cI)] > countPrimes[nums.get(stack.peek())]){
        stack.pop();

      }
      left[cI] = stack.isEmpty() ? -1 : stack.peek();
      stack.push(cI);
    }

    stack.clear();

    for(int cI = size - 1 ; cI >= 0; cI--){
      while(!stack.isEmpty() && countPrimes[nums.get(cI)] >= countPrimes[nums.get(stack.peek())]){
        stack.pop();

      }
      right[cI] = stack.isEmpty() ? size : stack.peek();
      stack.push(cI);
    }

    long[] countSubArrays = new long[size];

    for(int cI = 0; cI < size; cI++){
      countSubArrays[cI] = (long) (cI - left[cI]) * (right[cI] - cI);
    }



    List<int[]> values = new ArrayList<>();

    for(int cI = 0 ; cI < size; cI++){
      values.add(new int[]{nums.get(cI), cI});
    }

    Collections.sort(values, (entry1, entry2) -> Integer.compare(entry2[0], entry1[0]));

    int index = 0;

    while(k > 0){
      int currentPair[] = values.get(index++);
      long minValue = Math.min(k, countSubArrays[currentPair[1]]);

      k -= minValue ;



      maxScore = (maxScore * (pow(currentPair[0], minValue) % mod)) % mod;


    }

    return (int) maxScore;

  }

  private long pow(long base, long exponent) {
    long res = 1;
    while(exponent > 0){
      if (exponent % 2 == 1){
        res = (res * base) % mod;
      }

      base = (base * base) % mod;
      exponent /= 2;
    }

    return res;

  }

  private void countPrimes() {


    Arrays.fill(countPrimes, 0);

    for (int i = 2; i < 100001; i++) {
      if (countPrimes[i] == 0) {  // ✅ If `i` is prime
        for (int j = i; j < 100001; j += i) {
          countPrimes[j]++;  // ✅ Only count `i` ONCE for each `j`
        }
      }


    }
  }




  public static void main(String[] args){
    log.info("Score : {} ", new ApplyOperationToMaximizeScore().maximumScore(Arrays.asList(19, 12, 14, 6, 10, 18), 3));
  }
}
