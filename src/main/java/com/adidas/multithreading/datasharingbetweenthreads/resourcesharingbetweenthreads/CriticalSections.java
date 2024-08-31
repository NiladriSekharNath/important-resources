package com.adidas.multithreading.datasharingbetweenthreads.resourcesharingbetweenthreads;

import lombok.extern.slf4j.Slf4j;

/**
 * Why do we want threads to share resources in the first place
 * because the let's take the previous example of the Text Editor example where there are two threads
 * UI Thread and Document Saver Thread that is accessing the shared Data Structure (The Text Editor Document) if there is no Document Saver
 * thread that is active then we might lose our data if there is a sudden power cut
 * <p>
 * or in RestAPI where users send different HttpRequests though on separate threads sure but at the end each thread(request) has to interact with only one Database which is
 * the shared resource
 * <p>
 * When done incorrectly like in this example there would be inconsistent results
 * like shown below
 */
@Slf4j
public class CriticalSections {

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
     */
    private static class InventoryCounter {

        /**
         * now if we see here most important thing we can't have two different lockingObjects
         *
         * So basically if we have something like A itemCounter variable acting as a counter of items in a warehouse, we are trying to emulate a real scenario where we have two method increment and decrement we can’t have separate locking objects ? As if we have
         * there would be no consistency because one incrementThread when checks for the lockingObject1 will see that the lockingObject1 is open acquires it starts incrementing and then decrementThread will see the lockingObject2 in the decrementMethod and starts decrementing on the same variable
         * which would cause consistency issues,
         * and if we have the same locking object we are good because one thread can increment and other thread is waiting it can’t decrement at the same time right ?
         *
         * because IncrementingThread comes acquires the lockingObject1 starts incrementing, and at the sameTime decrementingThread, acquires a different lock (lockingObject2, which should not be done) and starts decrementing which should not be the case as decrementingThread when it tries to acquire
         * lock it should be blocked as it should be locked by incrementingThread
         *
         * You're absolutely correct! In the scenario you described with an itemCounter variable representing the number of items in a warehouse, you cannot use separate locking objects for incrementing and decrementing the counter. Separate locks would indeed lead to inconsistency.
         *
         * Here's why:
         *
         * Data Consistency:
         *
         * The itemCounter variable is a shared resource that needs to be accessed and updated consistently. Separate locks would allow one thread to increment the counter while another thread could potentially decrement it at the same time, resulting in an inaccurate count.
         * Race Condition:
         *
         * Without a single lock, a race condition could occur. Imagine two threads trying to access the itemCounter at the same time:
         * ThreadIncrement reads the current value (say, 10).
         * Before ThreadIncrement finishes updating the counter (incrementing by 1), ThreadDecrement reads the same value (10). (as lockingObjects are different )
         * ThreadIncrement updates to 11.
         * Thread B updates to 11 as well (because it read the same initial value). This results in a final count of 11, even though there should be 12 items.
         * Single Lock for Consistency:
         *
         * By using the same locking object for both incrementing and decrementing methods, you ensure that only one thread can access and modify the itemCounter at a time.
         * This guarantees data integrity and prevents race conditions.
         * Here's an example of how to achieve this:
         *
         * Java
         * public class Warehouse {
         *     int itemCounter = 10; // Shared resource
         *
         *     Object lock = new Object(); // Single lock object
         *
         *     public void incrementItem() {
         *         synchronized (lock) {
         *             itemCounter++;
         *         }
         *     }
         *
         *     public void decrementItem() {
         *         synchronized (lock) {
         *             if (itemCounter > 0) {
         *                 itemCounter--;
         *             }
         *         }
         *     }
         * }
         * Use code with caution.
         * In this example:
         *
         * Both incrementItem and decrementItem use the same lock object in their synchronized blocks.
         * This ensures that only one thread can access and update the itemCounter variable at a time, preventing race conditions and maintaining data consistency.
         * Additional Considerations:
         *
         * If you have other methods that read the itemCounter but don't modify it, you can potentially use a separate read-only lock mechanism (like ReentrantReadWriteLock) to improve performance by allowing concurrent reads while maintaining exclusive access for writes. However, this introduces more complexity and should only be considered if read operations are significantly more frequent than write operations.
         * By effectively using synchronization with a single lock object, you can ensure that your warehouse inventory is accurately represented in your multithreaded Java application.
         *
         */

        private Object lockingObject1 = new Object();
        /*private Object lockingObject2 = new Object();*/
        private int items;

        public InventoryCounter(int items) {
            this.items = items;
        }

        public void incrementCounter() {
            synchronized (lockingObject1) {
                this.items++;
            }
        }

        public void decrementCounter() {
            synchronized (lockingObject1) {
            /*synchronized (lockingObject2) {*/
                this.items--;
            }
        }

        public int getItems() {
            return this.items;
        }
    }
}

