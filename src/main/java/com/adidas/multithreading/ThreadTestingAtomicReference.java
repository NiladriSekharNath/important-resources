package com.adidas.multithreading;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class ThreadTestingAtomicReference {
    public static void main(String args[]) {
        Random random = new Random();
        Thread checkingThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    checkAndSetValue(String.valueOf(random.nextInt(10)));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread changingValueThread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                changeValue(String.valueOf(random.nextInt(100)));
            }
        });
        checkingThread.setName("checkingThread");
        changingValueThread.setName("changingValueThread");


        checkingThread.start();
        changingValueThread.start();
    }

    private static AtomicReference<String> value = new AtomicReference<>("initialValue");

    public static void checkAndSetValue(String newValue) throws InterruptedException {

        String currentValue = value.get();
        log.info("Current Value : {}", currentValue);

        if (value.compareAndSet(currentValue, newValue)) {
            log.info("new Value successfully set : {}", value.get());

        } else {
            log.info("Old value is preserved : {}", currentValue);
        }

    }

    public static void changeValue(String newValue) {
        log.info("setting new Value : {}", newValue);
        value.set(newValue);
    }
}
