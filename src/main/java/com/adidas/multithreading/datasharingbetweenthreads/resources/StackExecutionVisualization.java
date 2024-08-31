package com.adidas.multithreading.datasharingbetweenthreads.resources;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StackExecutionVisualization {

    public static void main(String args[]){
        int x = 5;
        int y = 10;
        int result = sum(x,y);
        log.info("result, {}", result);
    }

    private static int sum(int a, int b){
        int s = a + b;
        return s;
    }
}
