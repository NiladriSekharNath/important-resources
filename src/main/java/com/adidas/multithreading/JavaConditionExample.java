package com.adidas.multithreading;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class JavaConditionExample {

    public static void main(String args[]) throws InterruptedException {
        JavaLocking javaLocking = new JavaLocking();
        Thread lockingThread = new Thread(() -> javaLocking.testCondition());
        lockingThread.setName("lockingThread");
        Thread unlockingThread = new Thread(() -> javaLocking.testUnlocking());
        unlockingThread.setName("unlockingThread");
        lockingThread.start();
        unlockingThread.sleep(10);
        unlockingThread.start();
        lockingThread.join();
        unlockingThread.join();

    }

    private static class JavaLocking {
        Lock lock = new ReentrantLock();

        Condition condition = lock.newCondition();

        boolean available = false;

        public void testCondition() {
            try {
                lock.lock();

                while (!available) {
                    log.info("Putting current thread to sleep {}", Thread.currentThread().getName());
                    long startTime = System.currentTimeMillis();
                    long durationRemaining = condition.awaitNanos(300_000_000);
                    long endTime = System.currentTimeMillis();
                    log.info("Stopped waiting here duration: {} millis, and awakened after durationRemaining: {}" +
                                    " nanos done, thread:  {}", (endTime - startTime), durationRemaining,
                            Thread.currentThread().getName());
                    return;
                }
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }
        }

        public void testUnlocking() {
            try {
                lock.lock();
                log.info("putting the signalling thread to sleep {}", Thread.currentThread().getName());
                Thread.sleep(60000);
                available = true;
                condition.signalAll();
                log.info("locked thread waked up by thread: {}", Thread.currentThread().getName());
            } catch (InterruptedException e) {

            } finally {
                lock.unlock();
            }
        }
    }


}
