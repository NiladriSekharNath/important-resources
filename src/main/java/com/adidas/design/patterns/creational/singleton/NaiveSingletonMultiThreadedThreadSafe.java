package com.adidas.design.patterns.creational.singleton;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * this is thread safe, and we can see the double check locking one
 * locking with the synchronised function and the other one with the null check again surrounding that
 */
@Slf4j
public class NaiveSingletonMultiThreadedThreadSafe {
    /***
     * Here the keyword volatile is used because in line 35 where the new assignment operation is used there is one point
     * which is
     * 1. construct empty NaiveSingletonMultiThreadedThreadSafe() object with fields initialized as null
     * 2. call the constructor to assign or initialize the fields with the right value
     * 3. assign the value to the naiveSingletonMultiThreadedThreadSafe object
     * but the JVM can perform these instructions in any order which is not guaranteed at all times, so we should do something like
     * use the keyword "volatile" to make sure this happens which will tell JVM to not reorder the instructions
     *
     * For more information we can refer to the YOUTUBE video -> https://www.youtube.com/watch?v=Z5TRputhzHs&ab_channel=DefogTech
     *
     * https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
     *
     */
    private static volatile NaiveSingletonMultiThreadedThreadSafe naiveSingletonMultiThreadedThreadSafe;

    @Getter
    private String value;

    private NaiveSingletonMultiThreadedThreadSafe(String value) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.value = value;
    }

    public static NaiveSingletonMultiThreadedThreadSafe getInstance(String value) {

        if (naiveSingletonMultiThreadedThreadSafe == null) {
            synchronized (NaiveSingletonMultiThreadedThreadSafe.class) {
                if (naiveSingletonMultiThreadedThreadSafe == null)
                    naiveSingletonMultiThreadedThreadSafe = new NaiveSingletonMultiThreadedThreadSafe(value);
            }
        }
        return naiveSingletonMultiThreadedThreadSafe;
    }

    public static void main(String args[]) {
        /**
         * Same value is not guaranteed like here sometimes if we try to do this we would not get the similar value
         * like FOO FOO or BAR BAR
         * instead FOO BAR which means two instances are created which is not correct
         * */

        log.info("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");

        Thread threadFoo = new Thread(new NaiveSingletonMultiThreaded.ThreadFoo());
        Thread threadBar = new Thread(new NaiveSingletonMultiThreaded.ThreadBar());
        threadFoo.start();
        threadBar.start();
    }

    static class ThreadFoo implements Runnable {

        @Override
        public void run() {
            NaiveSingletonMultiThreaded naiveSingletonMultiThreaded = NaiveSingletonMultiThreaded.getInstance("FOO");
            log.info(naiveSingletonMultiThreaded.getValue());

        }
    }

    static class ThreadBar implements Runnable {

        @Override
        public void run() {

            NaiveSingletonMultiThreaded naiveSingletonMultiThreaded = NaiveSingletonMultiThreaded.getInstance("BAR");
            log.info(naiveSingletonMultiThreaded.getValue());
        }
    }
}
