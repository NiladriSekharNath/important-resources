package com.adidas.multithreading.threadtermination;

import lombok.extern.slf4j.Slf4j;

/**
 * blocking a thread that is taking too long by an exception that is caught inside
 */
@Slf4j
public class ThreadInterruptionNewBlocking {
    public static void main(String args[]){
        Thread thread = new Thread(new BlockingThread());
        thread.start();
        /**
         * if we don't call this from outside the thread does not stop this exception is caught in the BlockingThread
         */
        thread.interrupt();
    }

    private static class BlockingThread implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(500000000);
            } catch (InterruptedException e) {
                log.info("Thread interrupted ");
            }
        }
    }
}
