package com.adidas.dsa.striversde.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * for this question we are maintaining a monotonic increasing stack
 */
public class NextGreaterElement {
  public int[] nextGreaterElement(int[] nums1, int[] nums2) {
    var nge = ngeBuilderReturningMap(nums2);
    // for(int cI = 0; cI <= nums2.length; cI++){
    //     System.out.println(String.format("NGE Current Index for %s -> %s", nums2[cI], nge[cI]));
    // }

    int[] result = new int[nums1.length];
    for(int cI = 0; cI < nums1.length; cI++){
      result[cI] = nge.get(nums1[cI]);
    }
    return result;
  }

  /**
   *
   * for every element in the array we are required to find the next greater element
   *
   * given an array to us we are required to find the next greater element in the array
   *
   * so we are here [1, 3,  4,  2]
   *          nge : [3, 4, -1, -1]
   *
   *          for every element we are finding the next greater element let's say for 4 -> -1 since there is no element
   *          greater than 4 to the right of 4
   *
   * Approach :
   *
   * Concept we are using is Monotonic Stack (Increasing Stack, stack will only hold strictly increasing elements 1, 2 ,3
   *
   * we will compute from the right side of the array
   *
   * [1, 3,  4,  2]    nums
   *             /\
   *             |
   *            cI
   *
   * Obviously for 2 we don't have any right element
   *
   * the idea is Stack []
   *
   * from the cI = n - 1 -> 0
   *
   *      At every index we will see if the top of the "stack is not empty" and
   *      the currentElement pointed by nums[cI] >=  top stack element
   *      we will keep popping elements from the stack
   *
   *      once the loop breaks the top of the stack contains the next greater element
   *
   *      adding that to our result list
   *
   *      and adding nums[cI] again on the stack
   *
   *      Dry run :
   *
   *      stack [] => stack is empty adding -1
   *
   *        nge[0, 0, 0, -1]
   *
   *      stack [2]
   *
   *      at nums 4 ->
   *          we see stack is not empty 4 >= stack.peek() -> 4 >= 2
   *
   *          popping element 2
   *
   *          stack [] -> empty  adding -1
   *
   *          nge[0, 0, -1, -1]
   *
   *          [4]
   *
   *     at nums 3
   *
   *        inner loop condition does not run
   *            3 is not >= 4 ( 3 < 4) stack [4]
   *
   *            nge[0, 4, -1, -1]
   *
   *            pushing stack => 3
   *
   *            current stack: [3, 4]
   *
   *    at nums 1
   *
   *      same as 3
   *        1 is not >= 3 (1 < 3 ) stack[3, 4]
   *
   *        nge[3, 4, -1, -1]
   *
   *        pushing into stack 1
   *
   *        current stack : [1, 3, 4]
   *
   *
   *
   * finally we return -> nge[3, 4, -1, -1]
   *
   */
  private int[] ngeBuilder(int[] nums){
    int n = nums.length;
    Stack<Integer> stack = new Stack<>();
    int[] nge = new int[n];
    for(int cI = n - 1; cI >= 0; cI--){
      /**
       * strivers approach is stacks.peek() <= nums[cI]
       */
      while(!stack.isEmpty() && nums[cI] >= stack.peek()){
        stack.pop();
      }
      nge[cI] = stack.isEmpty() ? -1 : stack.peek();

      stack.push(nums[cI]);
    }

    return nge;
  }

  /**
   this is adjusted for this question only

   */
  private Map<Integer, Integer> ngeBuilderReturningMap(int[] nums){
    int n = nums.length;
    Stack<Integer> stack = new Stack<>();
    Map<Integer, Integer> nge = new HashMap<>();
    for(int cI = n - 1; cI >= 0; cI--){
      while(!stack.isEmpty() && nums[cI] >= stack.peek()){
        stack.pop();
      }
      //nge[cI] = stack.isEmpty() ? -1 : stack.peek();

      int nextGreaterElementForCI = stack.isEmpty() ? -1 : stack.peek();

      nge.put(nums[cI], nextGreaterElementForCI);

      stack.push(nums[cI]);
    }

    return nge;
  }
}
