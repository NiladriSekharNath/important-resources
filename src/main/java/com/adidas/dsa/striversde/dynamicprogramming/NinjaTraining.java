package com.adidas.dsa.striversde.dynamicprogramming;

import java.util.Arrays;

/**
 * Problem statement
 * Ninja is planing this ‘N’ days-long training schedule. Each day, he can perform any one of these three activities.
 * (Running, Fighting Practice or Learning New Moves). Each activity has some merit points on each day.
 * As Ninja has to improve all his skills, he can’t do the same activity in two consecutive days.
 * Can you help Ninja find out the maximum merit points Ninja can earn?
 *
 * You are given a 2D array of size N*3 ‘POINTS’ with the points corresponding to each day and activity.
 * Your task is to calculate the maximum number of merit points that Ninja can earn.
 *
 * For Example
 * If the given ‘POINTS’ array is [[1,2,5], [3 ,1 ,1] ,[3,3,3] ],the answer will be 11 as 5 + 3 + 3.
 * Detailed explanation ( Input/output format, Notes, Images )
 * Constraints:
 * 1 <= T <= 10
 * 1 <= N <= 100000.
 * 1 <= values of POINTS arrays <= 100 .
 *
 * Time limit: 1 sec
 * Sample Input 1:
 * 2
 * 3
 * 1 2 5
 * 3 1 1
 * 3 3 3
 * 3
 * 10 40 70
 * 20 50 80
 * 30 60 90
 * Sample Output 1:
 * 11
 * 210
 * Explanation of sample input 1:
 * For the first test case,
 * One of the answers can be:
 * On the first day, Ninja will learn new moves and earn 5 merit points.
 * On the second day, Ninja will do running and earn 3 merit points.
 * On the third day, Ninja will do fighting and earn 3 merit points.
 * The total merit point is 11 which is the maximum.
 * Hence, the answer is 11.
 *
 * For the second test case:
 * One of the answers can be:
 * On the first day, Ninja will learn new moves and earn 70 merit points.
 * On the second day, Ninja will do fighting and earn 50 merit points.
 * On the third day, Ninja will learn new moves and earn 90 merit points.
 * The total merit point is 210 which is the maximum.
 * Hence, the answer is 210.
 * Sample Input 2:
 * 2
 * 3
 * 18 11 19
 * 4 13 7
 * 1 8 13
 * 2
 * 10 50 1
 * 5 100 11
 * Sample Output 2:
 * 45
 * 110
 *
 *
 * Basically the problem statement states that for a partcular activity at an index if we selected that index we cannot
 * use that index in the next activity day
 *
 * [1,2,3] -> day1 let's say at here we chose the task 3(column = 3)
 * [5,3,1] -> day2 Here our choices are limited to task0 -> 5, and task1 -> 3
 *
 * so the information that we require is the previous day value what we have chosen that is col = 3 (represented by last)
 *
 * Again for the day0 since we have not chosen anything we could say as -1 but since that representation is difficult in
 * an array we take this as last = 3
 *
 *
 *
 * so our recursion approach is take max of all options from the activities except the value of the previousColumn or the
 * last activity selected(denoted by last) and return that answer for that level ;
 *
 * for(int currentColumn = 0 ; currentColumn < 3 ; currentColumn++){
 *       if(currentColumn != last){
 *         maxPoints = Math.max(maxPoints,
 *             points[currentDay][currentColumn] + helper(points, currentDay - 1, currentColumn, dp));
 *       }
 *     }
 */
public class NinjaTraining {

  /**
   *
   * This one the recursion with memoization does not work for some reason but the logic is correct
   */
  public static int ninjaTraining(int n, int points[][]) {

    int[][] dp = new int[n][4];

    for(int[] eachRow : dp){
      Arrays.fill(eachRow, -1);
    }
    return helper(points, n - 1, 3, dp);
  }

  private static int helper(int[][] points, int currentDay, int last, int[][] dp){
    /* last represents the currentColumnumn and currentDay represents the row;

     */

    if(dp[currentDay][last] != -1) return dp[currentDay][last];

    int maxPoints = 0;
    if(currentDay == 0){

      for(int currentColumn = 0; currentColumn < 3; currentColumn++){
        if(currentColumn != last){
          maxPoints = Math.max(maxPoints, points[0][currentColumn]);
        }
      }

      return dp[currentDay][last] = maxPoints ;
    }



    maxPoints = 0;

    for(int currentColumn = 0 ; currentColumn < 3 ; currentColumn++){
      if(currentColumn != last){
        maxPoints = Math.max(maxPoints,
            points[currentDay][currentColumn] + helper(points, currentDay - 1, currentColumn, dp));
      }
    }

    return dp[currentDay][last] = maxPoints;


  }

  /***
   *
   * now after the recursion with memoization approach for the problem we are going to work with converting that to tabulation
   *
   * In tabulation,
   *
   * if we think about after initializating a same dp size which is same as the one with memoization, after that
   *
   * we try to initialize the base case like we do always
   *
   * for the base Case : ->
   *
   * Now we have the value as last -> which represents the activity performed in the previous day, this is crucial
   *
   * and since we can't take the similar day value
   *
   * so for the
   * [day0][0-Activity] = Max(points[0][1-activity], points[0][2-activity]) -----> since we cannot take the similar value, so max
   *                      remaining values except the 0th value
   *
   * [day0][1-Activity] = Max(points[0][0-activity], points[0][2-activity]) -----> since we cannot take the similar value, so max
   *                    remaining values except the 1th value
   *
   * [day0][2-Activity] = Max(points[0][0-activity], points[0][1-activity]) -----> since we cannot take the similar value, so max
   *                       remaining values except the 0th value
   *
   * [day0][3-Activity] = Max(points[0][0-activity], points[0][1-activity], points[0][2-activity]) -----> now for this
   *                      since none of the activities are selected we could take max of any day value
   *
   * If we see here similarly we have to keep track of the each "last" value so we could track and build for the currentDay
   * and we need to calculate the same for all the days starting from day1 to dayN
   *
   * Hence we need two loops for the, one for the day and one for the last and we copy the same recursion in the previous
   * approach
   *
   * and we finally return the value stored in dp[n-1][3]
   *
   *
   *
   *
   *
   */

  public static int tabulationApproach(int[][] points, int n){
    int[][] dp = new int[n][4];

    dp[0][0] = Math.max(points[0][1], points[0][2]);
    dp[0][1] = Math.max(points[0][0], points[0][2]);
    dp[0][2] = Math.max(points[0][0], points[0][1]);
    dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));

    for(int currentDay = 1; currentDay < n; currentDay++){
      for(int last = 0; last < 4; last++){
        int maxPoints = 0;
        for(int currentCol = 0 ; currentCol < 3; currentCol++){
          if(currentCol != last){
            maxPoints = Math.max(maxPoints, points[currentDay][currentCol] + dp[currentDay - 1][currentCol]);
            dp[currentDay][last] = maxPoints;
          }
        }
      }
    }

    return dp[n - 1][3];

  }

  /**
   *
   * Once the Tabulation approach is written by us, we need to check if we could write the SpaceOptimization Approach
   *
   * now if we see above we see we need -> dp[currentDay - 1][currentCol]);
   *
   * ...
   * secondPrevious -> [1, 2, 4, 3]
   * previousDay    -> [3, 2, 3, 5]
   * currentDay     -> [-, -, -, -]
   *
   * now if we see above we just need the previous Day information for us to use the values and work with them we don't
   * need all the values with us (like secondPrevious, and thirdPrevious) and all
   *
   * Now what we should do is instead of carrying over a large Dp of nx4 we simply need the previous columns so we are good
   * to carry one array of size 4
   *
   * so what we do is for calculating the currentDay value we create a temp array represented by currentDay value
   * at start of each day for tracking each activity
   *
   *
   * once done we set the previousDayDp = currentDayDp
   *
   * secondPrevious -> [1, 2, 4, 3]
   * previousDay   ->  [3, 2, 3, 5]
   * currentDay    ->  [-, -, -, -] -> previousDay for the nextIteration
   *
   * now once this is done:
   *
   * currentDay    ->  [-, -, -, -] -> previousDay (for the nextIteration)
   *
   * finally at the last step we have our answer stored in the previous and we return
   *
   * previousDayDp[3]
   *
   */

  public static int tabulationApproachSpaceOptimized(int[][] points, int n){
    int[] previousDayDp = new int[n];


    previousDayDp[0] = Math.max(points[0][1], points[0][2]);
    previousDayDp[1] = Math.max(points[0][0], points[0][2]);
    previousDayDp[2] = Math.max(points[0][0], points[0][1]);
    previousDayDp[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));


    for (int currentDay = 1; currentDay < n; currentDay++) {

      int currentDayDp[] = new int[n];

      for (int last = 0; last < 4; last++) {
        int maxPoints = 0;
        for (int currentCol = 0; currentCol < 3; currentCol++) {
          if (currentCol != last) {
            maxPoints = Math.max(maxPoints, points[currentDay][currentCol] + previousDayDp[currentCol]);
            currentDayDp[last] = maxPoints;
          }
        }
      }

      previousDayDp = currentDayDp;

    }

    return previousDayDp[3];

  }

  public static void main(String[] args){
    new NinjaTraining().tabulationApproach(new int[][]{{1, 2, 5}, {3, 1, 1}, {3, 3, 3}}, 3);
  }
}
