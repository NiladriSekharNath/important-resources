package com.adidas.dsa.striversde.stack;

import java.util.Stack;

/**
 * finding the previous greater element and return the span of the current element.
 * The span of an element is the number of elements that are greater than the current element
 * and are to the left of the current element.
 *
 * [7, 2, 1, 3, 3, 4, 8]
 *
 *
 */
public class StockSpanner {
    Stack<int[]> stack ;
    int index = 0;
    public StockSpanner() {
        stack = new Stack<>();
    }

    public int next(int price) {
        while(!stack.isEmpty() && stack.peek()[0] <= price){
            stack.pop();
        }

        int pgeIndex = stack.isEmpty() ? -1 : stack.peek()[1],
                val = index - pgeIndex;

        stack.push(new int[]{price, index});
        index++;
        return val;

    }
}
