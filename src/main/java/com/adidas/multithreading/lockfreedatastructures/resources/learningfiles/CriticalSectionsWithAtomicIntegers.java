package com.adidas.multithreading.lockfreedatastructures.resources.learningfiles;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class CriticalSectionsWithAtomicIntegers {
    public static void main(String args[]) throws InterruptedException {
        InventoryCounter inventoryCounter = new InventoryCounter(0);
        Thread incrementCounterThread = new Thread(new IncrementCounterThread(inventoryCounter));
        Thread decrementCounterThread = new Thread(new DecrementCounterThread(inventoryCounter));

        incrementCounterThread.start();
        decrementCounterThread.start();

        incrementCounterThread.join();
        decrementCounterThread.join();
        log.info("Current Counter Value : {}", inventoryCounter.getItems());


    }

    private static class IncrementCounterThread implements Runnable {

        private InventoryCounter inventoryCounter;

        public IncrementCounterThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++)
                inventoryCounter.incrementCounter();
        }
    }

    private static class DecrementCounterThread implements Runnable {

        private InventoryCounter inventoryCounter;

        public DecrementCounterThread(InventoryCounter inventoryCounter) {
            this.inventoryCounter = inventoryCounter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++)
                inventoryCounter.decrementCounter();
        }
    }

    /**
     * typically a type of Database to understand something here the item represent the number of items
     * that are there in an inventory, now increment means someone add new items and decrement means
     * some items are bought so decrease in the items
     * <p>
     * same example as the CriticalSections example but now we use lock free data-structure
     * AtomicInteger
     */
    private static class InventoryCounter {


        /*private Object lockingObject1 = new Object();*/


        /*private int items;*/

        private AtomicInteger items = null;

        public InventoryCounter(int items) {
            this.items = new AtomicInteger(items);
        }

        public void incrementCounter() {
           /* synchronized (lockingObject1) {
                this.items++;
            }*/
            this.items.incrementAndGet();
        }

        public void decrementCounter() {
            /* synchronized (lockingObject1) {
             *//*synchronized (lockingObject2) {*//*
                this.items--;
            }*/

            this.items.decrementAndGet();
        }

        public int getItems() {
            return this.items.get();
        }
    }
}
