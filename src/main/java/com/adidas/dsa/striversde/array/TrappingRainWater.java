package com.adidas.dsa.striversde.array;

import java.util.Stack;

public class TrappingRainWater {
  /*public int trap(int[] height) {
    int[] ngeRight = nge(height);
    int[] ngeLeft = reverse(nge(reverse(height)));

    int maxWater = 0;

    for (int i = 0; i < height.length; i++) {
      if(ngeRight[i] != -1 && ngeLeft[i] != -1)
        maxWater += Math.min(ngeRight[i], ngeLeft[i]) - height[i];
    }

    return maxWater;
  }

  private int[] nge(int[] nums) {
     Stack<Integer> stack = new Stack<>();
    int nge[] = new int[nums.length];
    int right = nums.length - 1;
    while (right >= 0) {
      int currentElement = nums[right];
      while (!stack.isEmpty() && nums[right] > stack.peek()) stack.pop();

      nge[right--] = stack.isEmpty() ? -1 : stack.peek();

      stack.push(currentElement);

    }
    return nge;
  }

  private int[] reverse(int[] nums) {
    int left = 0, right = nums.length - 1;
    while (left <= right) {
      int temp = nums[left];
      nums[left] = nums[right];
      nums[right] = temp;
      left++;
      right--;
    }

    return nums;
  }*/

  public int trap(int[] height) {
    int size = height.length;
    int[] prefixMax = new int[size];
    int[] suffixMax = new int[size];
    prefixMax[0] = height[0];
    suffixMax[size - 1] = height[size - 1];

    for (int left = 1; left < size; left++) {
      prefixMax[left] = Math.max(prefixMax[left - 1], height[left]);
    }

    for (int right = size - 2; right >= 0; right--) {
      suffixMax[right] = Math.max(suffixMax[right + 1], height[right]);

    }

    int maxWater = 0;
    for (int i = 0; i < size; i++) {
      maxWater += Math.min(prefixMax[i], suffixMax[i]) - height[i];
    }

    return maxWater;
  }

  /**
   * Approach : The main approach for this is understanding is we are interested in only the lower of LeftMax or the rightMax
   * <p>
   * we use two pointer from left or right and we see if the left height is lower to the right height and check if there if the current left
   * height is higher than leftMax then we update the leftMax otherwise we find a case that on the right there is higher buildings but current building
   * lower than the leftMax so water can be stored based on the height of the leftMax - current height
   * <p>
   * <p>
   * <p>
   * Time Complexity : O(n)
   * Space Complexity : O(1)
   */
  public int trapOptimized(int[] height) {
    int left = 0, right = height.length - 1, leftMax = 0, rightMax = 0, waterTrapped = 0;

    while (left <= right) {
      if (height[left] <= height[right]) {
        if (height[left] >= leftMax) leftMax = height[left];
        else waterTrapped += leftMax - height[left];

        left++;
      } else {
        if (height[right] >= rightMax) rightMax = height[right];
        else waterTrapped += rightMax - height[right];
        right--;
      }
    }

    return waterTrapped;
  }

  public static void main(String[] args) {
    /*new TrappingRainWater().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});

    new TrappingRainWater().nge(new int[]{1, 2, 7, 4, 3, 9});*/

    new TrappingRainWater().trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
  }
}
