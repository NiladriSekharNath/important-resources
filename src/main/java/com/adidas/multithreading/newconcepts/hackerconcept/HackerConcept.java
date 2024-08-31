package com.adidas.multithreading.newconcepts.hackerconcept;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Purpose of this class is to put in the idea of Threading with a simple game like feature
 * <p>
 * Feature for this, we have a vault let's say that Hackers(some threads) are trying to brute-force entry and loot the contents
 * of the vault now and at the same time there is a Police Thread that is counting down from 10 -> 0 at the same time,
 * if the hackers guesses the password first then the hackers won and the vault is looted and then if the police counts down from
 * 10 -> 0 before the hacker, the hackers get caught. Let's try this example to demonstrate this feature.
 *
 */
@Slf4j
public class HackerConcept {
    private static final Integer MAX_PASSWORD = 999;

    private static class Vault {
        private int vaultPassword;

        public Vault(int vaultPassword) {
            this.vaultPassword = vaultPassword;
        }

        public boolean isPasswordCorrect(int password) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return this.vaultPassword == password;
        }

    }

    private static abstract class HackerThread extends Thread {

        protected Vault vault;

        public HackerThread(Vault vault) {
            this.vault = vault;
            this.setName(this.getClass().getSimpleName());
            this.setPriority(Thread.MAX_PRIORITY);
        }

        @Override
        public void start() {
            super.start();
            log.info("Current Thread started: {}", this.getName());
        }
    }

    /**
     * This class gets all the features of the HackerThread and
     * all the features of the Thread class as it is inherited
     */
    private static class AscendingHacker extends HackerThread {

        /**
         * https://gemini.google.com/app/3422725c612efef6
         * follow this thread to understand why this constructor in the parent class is initialised
         */


        public AscendingHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = 1; guess < MAX_PASSWORD; guess++) {
                //log.info("Vault password guess threadName: {}, guessValue: {} ", this.getName(), guess);
                if (vault.isPasswordCorrect(guess)) {
                    log.info("Vault unlocked by: {}, Password is: {}", this.getName(), guess);
                    System.exit(0);
                }

            }
        }
    }

    private static class DescendingHacker extends HackerThread {
        public DescendingHacker(Vault vault) {
            super(vault);
        }

        @Override
        public void run() {
            for (int guess = MAX_PASSWORD; guess > 1; guess--) {
                //log.info("Vault password guess threadName: {}, guessValue: {} ", this.getName(), guess);
                if (vault.isPasswordCorrect(guess)) {

                    log.info("Vault unlocked by: {}, Password is: {}", this.getName(), guess);
                    System.exit(0);
                }

            }
        }
    }

    private static class Police extends Thread {
        public Police(String name) {
            this.setName(name);
        }

        @Override
        public void run() {
            for (int count = 10; count >= 1; count--) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.info("Police arriving in : {}", count);
            }
            log.info("Game over for you All Hackers are caught");
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        Random random = new Random();
        Integer randInt = random.nextInt(MAX_PASSWORD);
        //log.info("Random integer password: {}", randInt);
        Vault vault = new Vault(randInt);

        Thread newAscendingHacker = new AscendingHacker(vault);
        Thread newDescendingHacker = new DescendingHacker(vault);
        Thread policeHacker = new Police("new-Police-hacker");
        List<Thread> threads = Arrays.asList(newAscendingHacker, newDescendingHacker, policeHacker);

        for (Thread thread : threads) {
            thread.start();
        }

    }
}
