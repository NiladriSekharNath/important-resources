package com.adidas.dsa.striversde.stack;

import java.util.Stack;


/**
 * Computes the largest rectangular area in a histogram given an array of bar heights.
 *
 * <p>This method implements the Monotonic Stack approach to efficiently determine the maximum
 * rectangular area possible by identifying the previous and next smaller elements for each bar.
 * The stack helps track indices of bars in a non-decreasing order to maintain efficient processing.</p>
 *
 * <h2>Approach:</h2>
 * <ul>
 *   <li>Iterate through the heights array and use a stack to track previous smaller elements.</li>
 *   <li>Whenever a bar of smaller height is encountered, it triggers processing of bars in the stack.</li>
 *   <li>For each processed bar, the width of the rectangle is determined using the previous and next smaller elements.</li>
 *   <li>The maximum area encountered during the process is stored and returned.</li>
 * </ul>
 *
 * <h2>Key Observations:</h2>
 * <ul>
 *   <li>The stack maintains indices of bars in an increasing order of height.</li>
 *   <li>The next smaller element is identified during traversal when a shorter bar is encountered.</li>
 *   <li>The previous smaller element is stored implicitly in the stack.</li>
 * </ul>
 *
 * <h2>Time Complexity:</h2>
 * <p>O(N), where N is the number of bars. Each bar is pushed and popped from the stack at most once.</p>
 *
 * <h2>Dry Run:</h2>
 * Consider the input: {@code heights = [2, 1, 5, 6, 2, 3]}
 *
 * <pre>
 * Index   0   1   2   3   4   5
 * Height  2   1   5   6   2   3
 *
 * Stack Processing:
 * - Push index 0 (height=2)
 * - Pop index 0 when processing index 1 (height=1) → Calculate area
 * - Push index 1
 * - Push index 2 (height=5), push index 3 (height=6)
 * - Pop index 3 at index 4 → Calculate area
 * - Pop index 2 at index 4 → Calculate area
 * - Push index 4, push index 5
 * - Pop remaining indices in stack → Compute max areas
 * </pre>
 *
 * <h2>Visual Representation:</h2>
 * <pre>
 * Heights array:
 * 6  ┌───┐
 * 5  ├───┤
 * 4  │   │
 * 3  │   ├───┐
 * 2  ├───┤   ├───┐
 * 1  ├───┴───┴───┴───┴───┤
 *    0   1   2   3   4   5
 * </pre>
 *
 * @param heights An array where each element represents the height of a histogram bar.
 * @return The maximum rectangular area that can be formed in the histogram.
 *
 * <h2>Idea of the problem</h2>
 *
 * <p>we are finding the next smaller element index(nseI) and previous smaller element index(pseIndex)
 *
 *  <p>
 *           0  1   2  3   4  5   6  7
 *          [3, 2, 10, 11, 5, 10, 6, 3]
 *
 *          for the 10 nseIndex -> 4, pseIndex = 1
 *
 *          so we are getting the length of the largest rectangle is [10, 11]
 *
 *          (nseIndex - 1 ) - (pseIndex + 1) + 1
 *
 *          nseIndex - pseIndex - 2 + 1
 *
 *          nseIndex - pseIndex - 1 [
 *
 *          pseIndex we will get from the next value in the stack that  contain the previous element
 *          (monotonous increasing stack) (if we don't have previous elements we would consider -1 denoted by the first element
 *          index before 0
 *
 *          and the next smaller element would be the currentIndex we are standing at( cI)
 *
 *          now once completed we would have element that don't have right smaller
 *          that we would consider as (length of the heights array)
 *
 *          nseIndex = length of the array
 *
 *
 *  </p>
 *
 * <p/>
 */
public class LargestAreaOfAHistogram {
  public int largestRectangleArea(int[] heights) {
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


  public static void main(String[] args){
    new LargestAreaOfAHistogram().largestRectangleArea(new int[]{2, 3});
  }
}
