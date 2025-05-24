package com.adidas.dsa.striversde.stack;

import java.util.Stack;

public class AsteroidCollisions {


    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for(int ast : asteroids){
            if(ast > 0) stack.push(ast);
            else{
                while(!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(ast)){
                    stack.pop();
                }
                if(!stack.isEmpty() && stack.peek() == Math.abs(ast)) stack.pop();

                else if(stack.isEmpty() || stack.peek() < 0) stack.push(ast);
            }
        }

        int index = stack.size() - 1, result[] = new int[stack.size()];

        while(!stack.isEmpty()){
            result[index--] = stack.pop();
        }

        return result;
    }
}
