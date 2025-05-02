package com.adidas.dsa.striversde.dynamicprogramming.digitdp;

import com.adidas.dsa.striversde.array.CountPrimes;

import java.util.Arrays;

public class CountTheNumberOfPowerfulIntegers {
  public long numberOfPowerfulInt(long start, long finish, int limit, String suffix) {

    start -= 1 ;

    String finishString = String.valueOf(finish), startString = String.valueOf(start);

    int sizePrefix = finishString.length() - suffix.length(), sizePrefixMaxSize = limit * sizePrefix;

    long dp[][][] = new long[sizePrefix + 1][2][sizePrefixMaxSize + 1];

    for(var grid : dp){
      for(var row : grid){
        Arrays.fill(row, -1);
      }
    }

    return helper(finishString, "", 0, 0, limit, sizePrefix, dp) - helper(startString, "", 0, 0, limit, sizePrefix, dp);

  }

  private long helper(String range, String cS, int cI, int tight, int limit, int sizeP, long dp[][][]){

    if(cI == sizeP){
      return 1;
    }

    long res = 0;

    int currNumVal = cS.isEmpty() ? 0 : Integer.parseInt(cS);

    if(dp[cI][tight][currNumVal] != -1) return dp[cI][tight][currNumVal];

    if(tight == 0){
      for(int dig = 0 ; dig <= limit ; dig++){
        if(dig == range.charAt(dig) - '0')
          res += helper(range, cS + dig, cI + 1, 1, limit, sizeP, dp);
        else
          res += helper(range, cS + dig, cI + 1, 0, limit, sizeP, dp);
      }
    }

    else{
      for(int dig = 0; dig <= range.charAt(cI) - '0' ; dig++){
        res += helper(range, cS + dig, cI + 1, 1, limit, sizeP, dp);
      }
    }

    return dp[cI][tight][currNumVal] = res;


  }


  public static void main(String args[]){
    new CountTheNumberOfPowerfulIntegers().numberOfPowerfulInt(1, 20000, 4, "5");
  }
}
