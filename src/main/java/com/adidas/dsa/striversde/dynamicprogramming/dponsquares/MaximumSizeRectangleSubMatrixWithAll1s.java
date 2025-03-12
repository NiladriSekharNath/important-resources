package com.adidas.dsa.striversde.dynamicprogramming.dponsquares;

import java.util.Stack;

public class MaximumSizeRectangleSubMatrixWithAll1s {

  /**
   *
   * You are given an 'N' * 'M' sized binary-valued matrix 'MAT, where 'N' is the number of rows and 'M' is the number of columns. You need to return the maximum size (area) of the submatrix which consists of all 1’s i.e. the maximum area of a submatrix in which each cell has only the value ‘1’.
   *
   *
   * In the above image, areas in green, red, and violet color are all submatrices of the original 4x4 matrix.
   * Note:
   *
   * 1. Binary valued matrix has only two values in each cell : 0 and 1.
   * 2. A submatrix is a matrix formed by selecting certain rows and columns from a larger matrix.
   * 3. The area of a matrix with 'h' rows and 'w' columns is equal to 'h' * 'w'.
   * Detailed explanation ( Input/output format, Notes, Images )
   * Constraints:
   * 1 <= 'T' <= 50
   * 1 <= 'N', 'M' <= 100
   *
   * Time Limit: 1 sec
   *
   *
   * 1 0 1 1   <-
   * 1 0 1 1   <-
   * 0 1 1 1   <-
   * 1 1 1 1   <-
   * 0 0 0 1
   *
   * so we are tasked to find the largest area of a rectangle with size = 1
   *
   * when we see the above problem -> the arrows pointed that is the largest rectangle we can form of size = 4
   *
   * the approach or the intuititon for this problem is the
   *
   * LargestAreaOfARectangleInAHistorgram problem
   *
   * going each row for each col -> row 0 -> 1, 0, 1, 1 [considering this like a height 0 or 1 building ]
   *     we can calculate for the above the largest rectangle in this one
   *
   *     we take it like a heights array => [1, 0, 1, 1]
   *
   *     again in the next row -> row1
   *
   *     heights array if we find a 1 we increment by index represented by [row1][col] by 1
   *
   *                   if we find a 0 we make the heights that position to be 0
   *
   *     to give us heights -> [1, 0, 2, 2] this is the way
   *
   *     we are calculating at each step are finding the maxAreaOfAHistogramInARectangle
   *
   *
   *
   */


  public static int maximalAreaOfSubMatrixOfAll1(int[][] mat, int n, int m) {
    int[] heights = new int[m];

    int maxArea = 0;
    for(int row = 0; row < n; row++){
      for(int col = 0; col < m; col++){
        if(mat[row][col] == 1) heights[col]++;
        else heights[col] = 0;
      }

      maxArea = Math.max(maxArea, largestRectangleArea(heights));
    }

    return maxArea;
  }
  public static int largestRectangleArea(int[] heights) {
    int n = heights.length, maxArea = 0;
    Stack<Integer> stack = new Stack<>();
    for(int cI = 0; cI < n; cI++){
      while(!stack.isEmpty() && heights[stack.peek()] >= heights[cI]){
        int currEleIdx = stack.pop(), currElementHeight = heights[currEleIdx], nextSmallerEleIdx = cI,
            previousSmallerEleIdx = stack.isEmpty() ? -1 : stack.peek(),
            width = nextSmallerEleIdx - previousSmallerEleIdx - 1;

        maxArea = Math.max(maxArea, width * currElementHeight);
      }
      stack.push(cI);
    }

    while(!stack.isEmpty()){
      int currEleIdx = stack.pop(), currElementHeight = heights[currEleIdx], nextSmallerEleIdx = n,
          previousSmallerEleIdx = stack.isEmpty() ? -1 : stack.peek(),
          width = nextSmallerEleIdx - previousSmallerEleIdx - 1;
      maxArea = Math.max(maxArea, width * currElementHeight);
    }

    return maxArea;
  }

}
