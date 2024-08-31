package com.adidas.multithreading.datasharingbetweenthreads.advancedlocking.reentrant.readwritelock;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReadWriteLockExample {
    private static final int HIGHEST_PRICE = 1000;

    public static void main(String[] args) throws InterruptedException {
        InventoryDatabase inventoryDatabase = new InventoryDatabase();
        Random random = new Random();

        for (int i = 0; i < 100000; i++) {
            inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
        }


        Thread writerThread = new Thread(() -> {
            /**
             * the writerThread adds an item removes an item wakes up does this process
             */
            while (true) {
                inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
                inventoryDatabase.removeItem(random.nextInt(HIGHEST_PRICE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {

                }
            }

        });
        writerThread.setDaemon(true);

        writerThread.start();

        List<Thread> readerThreads = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Thread eachReaderThread = new Thread(() -> {
                for (int count = 0; count < 100000; count++) {

                    int upperBoundPrice = random.nextInt(HIGHEST_PRICE);
                    int lowerBoundPrice = upperBoundPrice > 0 ? random.nextInt(upperBoundPrice) : 0;

                    inventoryDatabase.getNumbersOfItemsInPriceRange(lowerBoundPrice, upperBoundPrice);
                }
            });

            eachReaderThread.setDaemon(true);
            readerThreads.add(eachReaderThread);
        }

        long startTime = System.currentTimeMillis();

        for (Thread eachThread : readerThreads)
            eachThread.start();

        for (Thread eachThread : readerThreads)
            eachThread.join();

        long currentEndTime = System.currentTimeMillis();

        long duration = currentEndTime - startTime;

        /**
         * duration for the reentrant lock is 1849 milliseconds
         * but duration for the readwrite reentrant lock is 345 milliseconds
         *
         */
        log.info("Total duration of the operation : {} ms", duration);
    }

    private static class InventoryDatabase {

        private Lock lockingObject = new ReentrantLock();

        private ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

        Lock readLock = reentrantReadWriteLock.readLock();

        Lock writeLock = reentrantReadWriteLock.writeLock();

        private TreeMap<Integer, Integer> inventoryPricesWithCount = new TreeMap<>();

        public int getNumbersOfItemsInPriceRange(int lowerPrice, int upperPrice) {

            //lockingObject.lock();
            readLock.lock();

            try {
                /**
                 * 1 ,2, 4,5,6,7,8,10  lowerPrice = 3 and upperPrice = 9
                 * fromKey = lowerPrice(3) = 4 and upperPrice = 8
                 */
                Integer fromKey = inventoryPricesWithCount.ceilingKey(lowerPrice);
                Integer toKey = inventoryPricesWithCount.floorKey(upperPrice);

                if (fromKey == null || toKey == null) return 0;

                NavigableMap<Integer, Integer> rangeOfPrices =
                        inventoryPricesWithCount.subMap(fromKey, true, toKey, true);

                int sum = 0;

                for (int numberOfItemsForPrice : rangeOfPrices.values()) {
                    sum += numberOfItemsForPrice;
                }

                return sum;
            } finally {
                //lockingObject.unlock();
                readLock.unlock();
            }

        }

        public void addItem(int price) {
            //lockingObject.lock();
            writeLock.lock();
            try {


                Integer numberOfItemsPerPrice = inventoryPricesWithCount.get(price);
                if (numberOfItemsPerPrice != null) {
                    inventoryPricesWithCount.put(price, numberOfItemsPerPrice + 1);
                } else inventoryPricesWithCount.put(price, 1);
            } finally {
                //lockingObject.unlock();
                writeLock.unlock();
            }
        }

        public void removeItem(int price) {

            //lockingObject.lock();

            List<Integer> list = new ArrayList<>();
            list.forEach(element -> System.out.println(element));
            writeLock.lock();
            try {
                Integer numberOfItemsPerPrice = inventoryPricesWithCount.get(price);
                if (numberOfItemsPerPrice == null || numberOfItemsPerPrice == 1) inventoryPricesWithCount.remove(price);
                else inventoryPricesWithCount.put(price, numberOfItemsPerPrice - 1);
            } finally {

                //lockingObject.unlock();

                writeLock.unlock();
            }
        }
    }

}
