package com.adidas.multithreading.newconcepts.exercises;

import java.util.Arrays;
import java.util.List;

public class MultiExecutor {

    // Add any necessary member variables here

    /*
     * @param tasks to executed concurrently
     */

    private Thread[] threads ;

    public MultiExecutor(List<Runnable> tasks) {
        threads = new Thread[tasks.size()];
        for(int i = 0; i < tasks.size(); i++){
            threads[i] = new Thread(tasks.get(i));
        }
    }

    /**
     * Starts and executes all the tasks concurrently
     */
    public void executeAll() {
        for (Thread eachThread : this.threads) {
            eachThread.start();
        }
    }

    public static void main(String args[]) {

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                {
                    for (int i = 0; i < 5; i++)
                        System.out.println("print something from 1: " + i);
                }
            }
        };
        Runnable runnable2 = () -> {

            for (int i = 0; i < 5; i++)
                System.out.println("print something from 2: " + i);
        };
        Runnable runnable3 = () -> {
            for (int i = 0; i < 5; i++)
                System.out.println("print something from 3: " + i);
        };
        List<Runnable> tasks = Arrays.asList(runnable1, runnable2, runnable3);
        MultiExecutor multiExecutor = new MultiExecutor(tasks);
        multiExecutor.executeAll();

    }


}