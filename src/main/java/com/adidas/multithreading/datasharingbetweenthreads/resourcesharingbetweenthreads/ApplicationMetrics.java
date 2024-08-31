package com.adidas.multithreading.datasharingbetweenthreads.resourcesharingbetweenthreads;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class ApplicationMetrics {


    public static void main(String args[]) {
        Metrics metrics = new Metrics();
        Thread businessLogicThread1 = new Thread(new BusinessLogicThread(metrics));
        Thread businessLogicThread2 = new Thread(new BusinessLogicThread(metrics));

        Thread metricsPrinterThread = new Thread(new MetricsPrinter(metrics));

        businessLogicThread1.start();
        businessLogicThread2.start();
        metricsPrinterThread.start();

    }

    private static class MetricsPrinter implements Runnable {
        private Metrics metrics;

        public MetricsPrinter(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);

                } catch (InterruptedException exception) {

                }
                log.info("Current average time is {}", metrics.getAverage());
            }

        }
    }

    private static class BusinessLogicThread implements Runnable {

        private Metrics metrics;

        private Random random = new Random();

        public BusinessLogicThread(Metrics metrics) {
            this.metrics = metrics;
        }

        @Override
        public void run() {
            while (true) {
                long start = System.currentTimeMillis();
                try {
                    Thread.sleep(random.nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                long end = System.currentTimeMillis();
                metrics.addSample(end - start);
            }
        }
    }

    private static class Metrics {
        private long count = 0;

        /***
         *
         Why haven't we used volatile keyword for count in Metrics class?
         At 6:44, you explained that it is not safe to R/W double, thus added a volatile keyword there...the same story goes for count variable which is long, why haven't we added volatile in front of count...
         is it because we are not accessing it in our console output and the only method it is used in is marked as synchronized?

         Hi Developer,
         "is it because we are not accessing it in our console output or accessing it outside like any getterMethods like in double
         we are accessing by this method getAverage()
         and the only method it is used in is marked as synchronized?"

         Yes that is right. We are not reading it anywhere that is not part of the synchronized method already.

         Volatile keyword needs to be used for long and double

         */

        private volatile double average = 0.0;

        private synchronized void addSample(long sample) {
            double currentSum = count * average;
            count++;
            average = (currentSum + sample) / count;

        }


        public double getAverage() {
            return this.average;
        }
    }
}
