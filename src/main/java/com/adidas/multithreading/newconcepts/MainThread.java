package com.adidas.multithreading.newconcepts;

import lombok.extern.slf4j.Slf4j;

/**
 * Learning first way to schedule Thread in Java using the Runnable interface passing an object of a
 * class that implements the Runnable interface
 */
@Slf4j
public class MainThread {
    public static void main(String args[]) throws InterruptedException {
        Thread newThread = new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("Thread currently we are in :-> {}", Thread.currentThread().getName());
                log.info("Current Thread priority :-> {}", Thread.currentThread().getPriority());
                throw new RuntimeException("Some Intentional Exception Happened");
            }
        });

        newThread.setName("my-first-Thread");
        newThread.setPriority(Thread.MAX_PRIORITY);
        newThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.error("Some kind of exception happened in this thread: {}, exception: {}", t.getName(), e.getMessage());
            }
        });
        log.info("Thread currently we are in :-> {}", Thread.currentThread().getName());
        /***
         * After the Thread.start() method, the OS instantly did not schedule(start) the newThread it will be started in sometime
         */

        newThread.start();

        log.info("Thread currently we are in completed :-> {} ", Thread.currentThread().getName());
        //Thread.sleep(1000);
    }


}