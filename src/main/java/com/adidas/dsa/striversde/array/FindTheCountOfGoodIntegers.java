package com.adidas.dsa.striversde.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FindTheCountOfGoodIntegers {
  public long countGoodIntegers(int n, int k) {
    Set<String> set = new HashSet<>();
    int d = (n + 1)/2;
    long start = pow(10, d - 1), end = pow(10, d) - 1;

    for(long cI = start ; cI <= end ; cI++){
      String firstHalf = String.valueOf(cI);
      int firstHalfLength = firstHalf.length();

      StringBuilder targetNum = new StringBuilder(firstHalf);

      if(n % 2 == 0){
        targetNum.append(new StringBuilder(firstHalf).reverse());
      }

      else {
        targetNum.append(new StringBuilder(firstHalf.substring(0, firstHalfLength - 1)).reverse());
      }


      String palindromicNumber = targetNum.toString();

      if(Long.parseLong(palindromicNumber) % k == 0){
        char[] chars = palindromicNumber.toCharArray();
        Arrays.sort(chars);
        set.add(new String(chars));
      }



    }

    int[] factorial = new int[11];
    factorial[0] = 1;
    for(int cI = 1; cI < 11; cI++){
      factorial[cI] = factorial[cI - 1] * cI;
    }

    long result = 0;

    for(String entry : set){
      int[] countDigs = new int[10];

      for(char dig : entry.toCharArray()){
        countDigs[dig - '0']++ ;
      }

      int nonZero = n - countDigs[0];

      long totalCounts = nonZero * factorial[n - 1];

      for(int freq : countDigs){
        totalCounts /= factorial[freq];
      }

      result += totalCounts;
    }

    return result;
  }

  public long pow(int n, int x){
    long result = 1;

    while(x > 0){
      if((x & 1) == 1){
        result = result * n ;
      }
      x /= 2;
      n = n * n ;
    }

    return result;
  }

  public static void main(String[] args){
    new FindTheCountOfGoodIntegers().countGoodIntegers(3, 5);
  }
}
