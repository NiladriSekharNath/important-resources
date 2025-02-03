package com.adidas.dsa.striversde.dynamicprogramming.gridpattern;

import java.util.Arrays;

/**
 * Problem statement
 * Ninja has a 'GRID' of size 'R' X 'C'. Each cell of the grid contains some chocolates.
 * Ninja has two friends Alice and Bob, and he wants to collect as many chocolates as possible with the help of his friends.
 *
 * Initially, Alice is in the top-left position i.e. (0, 0), and Bob is in the top-right place i.e.
 * (0, ‘C’ - 1) in the grid. Each of them can move from their current cell to the cells just below them.
 * When anyone passes from any cell, he will pick all chocolates in it, and then the number of chocolates in that cell
 * will become zero. If both stay in the same cell, only one of them will pick the chocolates in it.
 *
 * If Alice or Bob is at (i, j) then they can move to (i + 1, j), (i + 1, j - 1) or (i + 1, j + 1).
 * They will always stay inside the ‘GRID’.
 *
 * Your task is to find the maximum number of chocolates Ninja can collect with the help of his friends by following the above rules.
 *
 * Example:
 * Input: ‘R’ = 3, ‘C’ = 4
 *        ‘GRID’ = [[2, 3, 1, 2], [3, 4, 2, 2], [5, 6, 3, 5]]
 * Output: 21
 *
 * Initially Alice is at the position (0,0) he can follow the path (0,0) -> (1,1) -> (2,1)
 * and will collect 2 + 4 + 6 = 12 chocolates.
 *
 * Initially Bob is at the position (0, 3) and he can follow the path (0, 3) -> (1,3) -> (2, 3)
 * and will colllect 2 + 2 + 5 = 9 chocolates.
 *
 * Hence the total number of chocolates collected will be 12 + 9 = 21. there is no other possible way
 * to collect a greater number of chocolates than 21.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints :
 * 1 <= ‘T’ <= 10
 * 2 <= 'R', 'C' <= 50
 * 0 <= 'GRID[i][j]'<= 10^2
 * Time Limit: 1sec
 * Sample Input 1 :
 * 2
 * 3 4
 * 2 3 1 2
 * 3 4 2 2
 * 5 6 3 5
 * 2 2
 * 1 1
 * 1 2
 * Sample Output 1 :
 * 21
 * 5
 * Explanation Of Sample Input 1 :
 * For the first test case, Initially Alice is at the position (0, 0) he can follow the path (0, 0) -> (1, 1) -> (2, 1)
 * and will collect 2 + 4 + 6 = 12 chocolates.
 *
 * Initially Bob is at the position (0, 3) and he can follow the path (0, 3) -> (1, 3) -> (2, 3)
 * and will collect 2 + 2 + 5 = 9 chocolates.
 *
 * Hence the total number of chocolates collected will be 12 + 9 = 21.
 *
 * For the second test case, Alice will follow the path (0, 0) -> (1, 0) and Bob will follow the path (0, 1) -> (1, 1).
 * total number of chocolates collected will be 1 + 1 + 1 + 2 = 5
 * Sample Input 2 :
 * 2
 * 2 2
 * 3 7
 * 7 6
 * 3 2
 * 4 5
 * 3 7
 * 4 2
 * Sample Output 2 :
 * 23
 * 25
 *
 *
 * Okay the entire idea of the question is we have two person basically Alice and Bob in a grid n x m
 * at positions (0, 0) and (0, m - 1) respectively, now we are said we have to travel together (Alice and Bob)
 * count the max chocolates that both than collect while ending at the last row (n - 1), only important point to this question is
 * if both Alice and Bob move to the same position only one of them can collect the chocolate not both
 *
 * so the concept if we try both separately it would not work for us, so we would have to as the question suggest
 * try together
 *
 * So trying together means both moves at the same time, if we see for one movement by Alice there is 3 movements that
 * can be done by Bob, if we see that
 *
 * total 9 movements (3 by Alice * 3 by Bob)
 *
 * let's call the movements (leftdiag, bottom, rightdiag) -> (col - 1, col, col + 1) also if we see no matter what the
 * the row always move for us by 1 (row + 1) so we need at max 3 variables, row(applicable for both), colA (position
 * of col for Alice), colB (position of col for Bob)
 *
 * so since we can pick one when Alice and Bob's position is same so we add one value from grid, otherwise we add both values
 *
 *
 *
 *
 */
public class CherryPickupII {
  public static int maximumChocolates(int r, int c, int[][] grid) {

    /**
     * since first one is row and c, c because first one is for Alice and next one is for Bob
     */
    int[][][] dp = new int[r][c][c];

    for(int[][] eachGrid: dp){
      for(int[] eachRow : eachGrid){
        Arrays.fill(eachRow, - 1);
      }
    }

    return helper(0, 0, c - 1, grid, r, c, dp);
  }

  /**
   *
   * @param row current Row
   * @param colA col for Alice
   * @param colB col for Bob
   * @param grid grid of the entries
   * @return
   */
  private static int helper(int row, int colA, int colB, int[][] grid, int rS, int cS, int[][][] dp){
    if(colA < 0 || colB < 0 || colA >= cS || colB >= cS ) return -(int) 1e9;
    if(row == rS - 1)
      return colA == colB ? grid[row][colA] : grid[row][colA] + grid[row][colB];

    if(dp[row][colA][colB] != -1) return dp[row][colA][colB];

    /**
     * for every movement of Alice, there would be 3 movements of Bob since we are doing the
     * recursion both at the same time
     *
     */
    int maxChoclates = -(int) 1e9;

    /**
     * for clarity we wrote here otherwise we could have written something like
     * in a single array dir: -1, 0, 1
     *
     * since we are allowed to move in col - 1, col, col + 1
     *
     */
    for(int dirA : new int[]{-1, 0, 1}){
      for(int dirB : new int[]{-1, 0, 1}){
        if(colA != colB){
          maxChoclates = Math.max(maxChoclates, grid[row][colA] + grid[row][colB] +
              helper(row + 1, colA + dirA, colB + dirB, grid, rS, cS, dp));

        }
        else
          maxChoclates = Math.max(maxChoclates, grid[row][colA] +
              helper(row + 1, colA + dirA, colB + dirB, grid, rS, cS, dp));
      }
    }

    return dp[row][colA][colB] = maxChoclates;
  }

  private static int tabulationApproach(int r, int c, int[][] grid){
    int[][][] dp = new int[r][c][c];

    /**
     * if we see for both Alice and Bob there is base case if both equal then once otherwise twice there are 2 * 2 cases
     *
     * let's say colA == colB so once addition in grid otherwise both additions so if you see there are 2 * 2 transitions
     * for the base case (rowSize - 1, denoted by r)
     *
     */
    for(int colAlice = 0 ; colAlice < c ; colAlice++){
      for(int colBob = 0 ; colBob  < c ; colBob++){
        /**
         * not equal adding both if equal we are good with one value;
         */
        if(colAlice != colBob) {
          dp[r - 1][colAlice][colBob] = grid[r - 1][colAlice] + grid[r - 1][colBob];
        }
        else
          dp[r - 1][colAlice][colBob] = grid[r - 1][colAlice];
      }
    }

    /**
     * since the row is moving from 0, 1, 2, .. n - 1 in TopDown Approach in Recursion + Memoization so in the tabulation
     * we are going in the reverse direction, n - 1, n - 2 ... 1, 0
     *
     * since the base case n - 1 is covered above we are iterating for the othercases
     */


    for(int row = r - 2; row >= 0 ; row--){
      for(int colAlice = 0 ; colAlice < c ; colAlice++){
        for(int colBob = 0 ; colBob < c; colBob++){
          /**
           * we kept the maxValue here because keeping it similar to the recursion
           */
          int maxValue = -(int) 1e9;
          for(int dirA : new int[]{-1, 0, 1}){
            for(int dirB : new int[]{-1, 0, 1}){
              int ans = 0 ;
              if(colAlice != colBob){
                ans = grid[row][colAlice] + grid[row][colBob] ;
              }
              else
                ans = grid[row][colAlice] ;

              if((colAlice + dirA < 0 || colAlice + dirA >= c) || (colBob + dirB < 0 || colBob + dirB >= c))
                ans += - (int) 1e9;
              else
                ans += dp[row + 1][colAlice + dirA][colBob + dirB];

              maxValue = Math.max(maxValue, ans);
            }
          }

          dp[row][colAlice][colBob] = maxValue;
        }
      }
    }

    /**
     *  the final answer is stored in the position as in recursion
     */
    return dp[0][0][c - 1];
  }


  private static int tabulationSpaceOptimization(int r, int c, int[][] grid){


    int next[][] = new int[c][c];
    /**
     * if we see for both Alice and Bob there is base case if both equal then once otherwise twice there are 2 * 2 cases
     *
     * let's say colA == colB so once addition in grid otherwise both additions so if you see there are 2 * 2 transitions
     * for the base case (rowSize - 1, denoted by r)
     *
     */
    for(int colAlice = 0 ; colAlice < c ; colAlice++){
      for(int colBob = 0 ; colBob  < c ; colBob++){
        /**
         * not equal adding both if equal we are good with one value;
         */
        if(colAlice != colBob) {
          next[colAlice][colBob] = grid[r - 1][colAlice] + grid[r - 1][colBob];
        }
        else
          next[colAlice][colBob] = grid[r - 1][colAlice];
      }
    }

    /**
     * since the row is moving from 0, 1, 2, .. n - 1 in TopDown Approach in Recursion + Memoization so in the tabulation
     * we are going in the reverse direction, n - 1, n - 2 ... 1, 0
     *
     * since the base case n - 1 is covered above we are iterating for the othercases
     */


    for(int row = r - 2; row >= 0 ; row--){

      int[][] curr = new int[c][c];

      for(int colAlice = 0 ; colAlice < c ; colAlice++){
        for(int colBob = 0 ; colBob < c; colBob++){
          /**
           * we kept the maxValue here because keeping it similar to the recursion
           */
          int maxValue = -(int) 1e9;
          for(int dirA : new int[]{-1, 0, 1}){
            for(int dirB : new int[]{-1, 0, 1}){
              int ans = 0 ;
              if(colAlice != colBob){
                ans = grid[row][colAlice] + grid[row][colBob] ;
              }
              else
                ans = grid[row][colAlice] ;

              if((colAlice + dirA < 0 || colAlice + dirA >= c) || (colBob + dirB < 0 || colBob + dirB >= c))
                ans += - (int) 1e9;
              else
                ans += next[colAlice + dirA][colBob + dirB];

              maxValue = Math.max(maxValue, ans);
            }
          }

          curr[colAlice][colBob] = maxValue;
        }
      }

      next = curr;

    }

    /**
     *  the final answer is stored in the position as in recursion
     */
    return next[0][c - 1];
  }

  public static void main(String[] args){
    CherryPickupII.tabulationApproach(3, 4, new int[][]{{2, 3, 1, 2}, {3, 4, 2, 2}, {5, 6, 3, 5}});
  }

}
