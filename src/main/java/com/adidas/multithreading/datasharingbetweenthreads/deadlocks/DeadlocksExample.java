package com.adidas.multithreading.datasharingbetweenthreads.deadlocks;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadlocksExample {

    public static void main(String args[]) {
        Intersection intersection = new Intersection();
        Thread trainAThread = new Thread(new TrainAThread(intersection));
        Thread trainBThread = new Thread(new TrainBThread(intersection));
        trainAThread.setName("trainAThread");
        trainBThread.setName("trainBThread");
        trainAThread.start();
        trainBThread.start();
    }


    private static class TrainAThread implements Runnable {
        private Intersection intersection;

        public TrainAThread(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while (true) {
                intersection.takeRoadA();
            }
        }
    }

    private static class TrainBThread implements Runnable {
        private Intersection intersection;

        public TrainBThread(Intersection intersection) {
            this.intersection = intersection;
        }

        @Override
        public void run() {
            while (true) {
                intersection.takeRoadB();
            }
        }
    }

    private static class Intersection {
        /**
         * lock named "roadA" to lock roadA
         */
        private Object roadA = new Object();
        private Object roadB = new Object();

        /**
         * Imagine if both threads(trains) try to pass the intersection at the same time concurrently, as shown below
         *
         * deadlock happens here: at this time
         *      1. roadA is locked by trainAThread
         *      and at the same time
         *      2. roadB is locked by trainBThread
         *      3. trainAThread is trying to acquire lock "roadB" at line 71
         *          or other way trainBThread is trying to acquire lock "roadA" at line 93
         *          now the problem is : roadB is locked By trainBThread and roadA is locked by trainAThread as in point
         *          1 and 2 so there are stuck forever and none of the trains(threads) can pass
         *
         *          see the problem image
         *          in "deadlocks/resources/learningfiles/problem1_deadlock-happening-with-such-threads-execution-order.png"
         *
         *      so the problem we face here is called circular wait
         *
         *      now to solve the problem we try this solution here : as shown in "solution-to-problem-1-deadlock.png"
         *
         *          change the order of locks acquisition as shown in the above pic or in the solution of these comments below
         *
         *          keep the ordering of lock acquisition the same in both cases
         *
         *
         */
        public void takeRoadA() {
            synchronized (roadA) {
                log.info("Road A is locked by thread name :{}", Thread.currentThread().getName());
                synchronized (roadB) {
                    log.info("Train is passing through road A ");

                    /**
                     * Emulating the Train passing
                     */
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {

                    }

                }
            }

        }

        public void takeRoadB() {

            /**
             * deadlocks here to understand uncomment this code below and run this application
             */
           /* synchronized (roadB) {
                log.info("Road B is locked by Thread name :{}", Thread.currentThread().getName());
                synchronized (roadA) {
                    log.info("Train passing through road B: {}");

                    *//**
                     * Emulating the Train passing
                     *//*
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {

                    }

                }
            }*/
            synchronized (roadA) {
                log.info("Road A is locked by Thread name :{}", Thread.currentThread().getName());
                synchronized (roadB) {
                    log.info("Train passing through road B: {}");

                    /**
                     * emulating the train passing
                     */
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {

                    }

                }
            }



        }
    }
}
