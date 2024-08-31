package com.adidas.multithreading.newconcepts;

import lombok.extern.slf4j.Slf4j;

/***
 * Other way to create a new Thread is by making our class extend the Thread Class
 */
@Slf4j
public class  MainThread2{
    public static void main(String args[]){

        Thread newThreadObject = new WorkingThread();

        newThreadObject.setName("New-Worker-Thread");
        newThreadObject.setPriority(Thread.MAX_PRIORITY);

        log.info("Thread we are in before starting the new Thread {}", Thread.currentThread().getName());

        newThreadObject.start();

        log.info("Thread we are in after starting the new Thread {}", Thread.currentThread().getName());
    }

    private static class WorkingThread extends Thread{
        @Override
        public void run() {
            /*log.info("Thread currently we are in :-> {}", Thread.currentThread().getName());
            log.info("Current Thread priority :-> {}", Thread.currentThread().getPriority());*/
            /**
             * Instead of doing this we can also use the this.allMethods to get the currentThread methods
             */
            log.info("Thread currently we are in :-> {}", this.getName());
            log.info("Current Thread priority :-> {}", this.getPriority());
        }
    }

}
