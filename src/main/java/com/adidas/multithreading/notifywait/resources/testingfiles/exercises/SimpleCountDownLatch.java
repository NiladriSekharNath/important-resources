package com.adidas.multithreading.notifywait.resources.testingfiles.exercises;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleCountDownLatch {
    private int count;

    public SimpleCountDownLatch(int count) {
        this.count = count;
        if (count < 0) {
            throw new IllegalArgumentException("count cannot be negative");
        }
    }

    /**
     * Causes the current thread to wait until the latch has counted down to zero.
     * If the current count is already zero then this method returns immediately.
     */

    /*public synchronized void await() throws InterruptedException {

        while (count > 0) {
            wait();
        }
        if (count == 0)

            return;

    }*/


    /**
     * Decrements the count of the latch, releasing all waiting threads when the count reaches zero.
     * If the current count already equals zero then nothing happens.
     */
    /*public synchronized void countDown() {

        if (count > 0){
            count--;
          if(count == 0)
            notifyAll();
        }
    }*/

    /**
     * Returns the current count.
     */
    /*public int getCount() {

        return this.count;
    }*/

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void await() throws InterruptedException {
        lock.lock();
        try{
            while(this.count > 0){
                condition.await();
            }

            if(count == 0)
                return;

        }finally{
            lock.unlock();
        }

    }
    public void countDown(){
        lock.lock();
        try{

            if(count > 0) {
                count--;
                if (count == 0)
                    condition.signalAll();
            }
        }finally{
            lock.unlock();
        }

    }
    public int getCount() {

        return this.count;
    }
}
