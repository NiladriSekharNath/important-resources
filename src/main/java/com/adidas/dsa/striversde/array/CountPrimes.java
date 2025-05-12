package com.adidas.dsa.striversde.array;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountPrimes {

  public int[] countPrimes(int n){

    int countPrimes[] = new int[n + 1];
    for(int i = 2 ; i <= n; i++){
      if(countPrimes[i] == 0){
        for(int j = i ; j <= n ; j += i){
          //countPrimes[j] = 0;
          countPrimes[j]++;
        }
      }
    }

    return countPrimes;

  }

  public static void main(String[] args){
    new CountPrimes().countPrimes(30);

  }

}
