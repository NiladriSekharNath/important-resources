package com.adidas.dsa.striversde.dynamicprogramming.lcspattern;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LongestPalindromicSubsequenceUsingVirtualThreadingInLeetCode implements AutoCloseable {

    private final ExecutorService executorService;
    public LongestPalindromicSubsequenceUsingVirtualThreadingInLeetCode(){
      this.executorService = Executors.newVirtualThreadPerTaskExecutor();
    }
    public int longestPalindromeSubseq(String s)  {

      Future<Integer> result = executorService.submit(() -> helper2ShiftingIndexRightTabulationSpaceOptimized(s, reverse(s)));
      int resultVal = 0 ;
      try {
        resultVal = result.get();
      } catch (Exception e) {

      }
      return resultVal;
    }

    private String reverse(String s){
      StringBuilder sb = new StringBuilder();
      for(char ch : s.toCharArray())
        sb.insert(0, ch);
      return sb.toString();
    }

    private int helper2ShiftingIndexRightTabulationSpaceOptimized(String s, String t){

      int sLength = s.length(), tLength = t.length() ;
      int[] prev  = new int[t.length() +1];

      for(int row = 1 ; row <= s.length() ; row++){
        int[] curr = new int[t.length() + 1];
        for(int col = 1 ; col <= t.length(); col++){
          if(s.charAt(row - 1) == t.charAt(col - 1)){

            curr[col] = 1 + prev[col - 1];


          }
          else {

            curr[col] = Math.max(prev[col], curr[col - 1]);
          }
        }
        prev = curr;
      }

      return prev[tLength];
    }

  @Override
  public void close() throws Exception {
    this.executorService.shutdown();
  }
}

