package com.adidas.multithreading.threadtermination;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;

/**
 * in this there is no place where an exception is caught using try-catch from inside the Runnable Run method
 * so we would have to send interrupt signal from outside and in the main computations place
 * (where our code is working with heavy calculations) we would have to keep a check for interruptions if any, made from outside
 *
 * https://gemini.google.com/app/085b0444b384acd6
 */
@Slf4j
public class LongComputationExample {
    public static void main(String args[]) {
        /*Thread smallComputation = new LongComputationTask(new BigInteger("2"), new BigInteger("10"));
        smallComputation.start();
        smallComputation.setName("smallComputation");*/

        Thread longComputation = new LongComputationTask(new BigInteger("200000"), new BigInteger("1000000000"));
        /**
         * this daemon property makes the program close after the main thread is completed
         * and we would have to set the Daemon true before we start the thread and if we do after the thread is started
         * we will get an IllegalStateException
         *
         */
        longComputation.setDaemon(true);
        longComputation.start();
        longComputation.setName("longComputation");
        longComputation.interrupt();


    }

    private static class LongComputationTask extends Thread {
        private BigInteger base;
        private BigInteger pow;

        private LongComputationTask(BigInteger base, BigInteger pow) {
            this.base = base;
            this.pow = pow;
        }

        @Override
        public void run() {
            log.info("base: {}, pow: {}, result: {}", base, pow, calculatePow(base, pow));
        }

        private BigInteger calculatePow(BigInteger base, BigInteger power) {
            BigInteger result = BigInteger.ONE;
            for (BigInteger iterator = BigInteger.ZERO; iterator.compareTo(power) != 0; iterator = iterator.add(BigInteger.ONE)) {
/*
                if (Thread.currentThread().isInterrupted()) {
                    log.info("Thread prematurely interuppted, name : {}", Thread.currentThread().getName());
                    return BigInteger.ZERO;
                }
*/
                result = result.multiply(base);

            }
            return result;
        }
    }
}
