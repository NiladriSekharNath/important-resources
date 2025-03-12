package com.adidas.dsa.striversde.stack;

import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

/**
 * given to us [4, 5, 2, 10, 8]
 *
 *         pse [-1, 4, -1, 2, 2]
 */
@Slf4j
public class PreviousSmallerElement {
  public int[] prevSmaller(int[] nums) {
    return pseBuilder(nums);
  }

  private int[] pseBuilder(int[] nums){
    int n = nums.length;
    Stack<Integer> stack = new Stack<>();
    int[] pse = new int[n];
    for(int cI = 0; cI < n; cI++){
      /**
       * similar approach to the NextGreaterElement
       *
       * [4, 5, 2, 10, 8]
       *
       * we are filling from left
       * only catch for greater elements to the left like in case of 2
       * [4, 5] for element 2
       *
       * we are popping from stack till we get a smaller element
       *
       * [] no smaller element found so for 2, pse(2) = -1
       *
       * for 8
       *
       * [2, 10]
       * popping 10, we get 2
       *
       * pse(8) = 2
       *
       *
       */
      while(!stack.isEmpty() && stack.peek() >= nums[cI]){
        stack.pop();
      }
      pse[cI] = stack.isEmpty() ? -1 : stack.peek();

      stack.push(nums[cI]);
    }

    return pse;
  }

  public static void main(String[] args){
    log.info("Previous Smaller Element: {}", new PreviousSmallerElement().prevSmaller(new int[]{4, 5, 2, 10, 8}));
  }
}
