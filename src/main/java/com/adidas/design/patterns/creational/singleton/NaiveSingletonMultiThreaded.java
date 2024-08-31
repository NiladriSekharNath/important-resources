package com.adidas.design.patterns.creational.singleton;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NaiveSingletonMultiThreaded {
    /**
     * https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom
     *
     * also we can follow this implementation
     */

    private static NaiveSingletonMultiThreaded naiveSingletonMultiThreaded;

    @Getter
    private String value ;

    private NaiveSingletonMultiThreaded(String value){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.value = value;
    }

    public static NaiveSingletonMultiThreaded getInstance(String value){
        if(naiveSingletonMultiThreaded == null )
            naiveSingletonMultiThreaded = new NaiveSingletonMultiThreaded(value);
        return naiveSingletonMultiThreaded;
    }

    public static void main(String args[]){
        /**
         * Same value is not guaranteed like here sometimes if we try to do this we would not get the similar value
         * like FOO FOO or BAR BAR
         * instead FOO BAR which means two instances are created which is not correct
         * */

        log.info("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");

        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();
    }

    static class ThreadFoo implements Runnable{

        @Override
        public void run() {
            NaiveSingletonMultiThreaded naiveSingletonMultiThreaded = NaiveSingletonMultiThreaded.getInstance("FOO");
            log.info(naiveSingletonMultiThreaded.getValue());

        }
    }

    static class ThreadBar implements Runnable{

        @Override
        public void run() {
            NaiveSingletonMultiThreaded naiveSingletonMultiThreaded = NaiveSingletonMultiThreaded.getInstance("BAR");
            log.info(naiveSingletonMultiThreaded.getValue());
        }
    }

}
