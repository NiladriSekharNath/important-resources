package com.adidas.multithreading.virtualthreads.importance;

import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*import java.util.concurrent.TimeUnit;*/
@Slf4j
public class IoBoundApplicationUsingVirtualThreads {


    /**
     * if here we see something when the number of tasks are currently 1000
     * we see our application keeps on creating each new tasks and submitting to the thread pool
     * and each Task take approximately 1 second, so we see in the output, for the duration that all the tasks since executing on different
     * threads, takes an approximate time of 1 second
     * <p>
     * now if we keep a very big number here our application might run into outofmemory exceptions for sure
     * <p>
     * <p>
     * So following the thread per task model, we expect that every task is going to be executed by a new
     * thread in the pool and as a result we should expect that this thread pool will grow to about 1000 threads.
     * So let's run this application.
     * And as we can see, our thread pool indeed grew to a thousand threads and overall our tasks took about
     * 1000 milliseconds to complete.
     * This means that our current throughput is 1000 operations per second.
     * Now let's close this window and increase the number of tasks we need to execute concurrently to 10,000.
     * This, in theory, should result in our thread pool growing to 10,000 threads.
     * So let's run the application and see what happens.
     * As we can see, our thread pool keeps growing until our application crashes.
     * This error indicates that the OS refused to allocate a new thread for our application.
     * This can be because our JVM ran out of memory or the OS reached the cap on the number of threads it
     * can allow.
     * This cap is different on different operating systems and configurations, but the main takeaway is allocating
     * too many threads is extremely expensive and dangerous.
     * So to protect our application from crashing, we are forced to limit the number of threads in our pool
     * to a safe number, which in our case is going to be 1000.
     * So now let's rerun the application and as we would expect, the execution of 10,000 operations by a
     * pool of only 1000 threads where each operation takes one second took us 10s.
     * This means that our throughput is now capped at 1000 operations per second.
     * Even though our CPU is idle and is not running anything.
     */
    //private static final int NUMBER_OF_TASKS = 1000;

    private static final int NUMBER_OF_TASKS = 100_000;

    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    public static void main(String[] args) throws InterruptedException {
        Scanner s = new Scanner(System.in);
        log.info("Press enter to Start");
        s.nextLine();



        long start = System.currentTimeMillis();
        performTasks();

        log.info("Tasks took to complete in {}  ", System.currentTimeMillis() - start);
    }

    private static void performTasks() {

        /** please see the guidelines for using the try-with resources parameter which enables auto-closeable
         *
         *
         * https://www.baeldung.com/java-try-with-resources
         *
         * this is done to make the main thread(calling thread here below ) not finish first
         * just in case and wait for this operation inside the executor to complete
         *
         */

        /**
         * instead of creating a fix threadPool we are now creating a
         * cachedThreadPool that would keep creating threads as needed and cache them for resusing in the future
         *
         * now if we use for these platform threads like this here, as shown we would end up crashing our application with the out of memory error
         * as the NUMBER_OF_TASKS increases
         *
         * but if we use the VirtualThreadPerTaskExecutor we don't crash our application
         *
         * It finished executing all the 100_000 tasks within 8.5 seconds.
         *
         * Also, by inspecting our print statements, we see that while we created 100,000 virtual threads, the
         *
         * JVM internally still used no more than 12 platform threads, which matches the number of cores(virtual) on
         *
         * my computer.
         */

        try (/*ExecutorService executorService = Executors.newCachedThreadPool()*/
            ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor())
         {
            for (int i = 0; i < NUMBER_OF_TASKS; i++) {
                /**
                 * here we are doing this, each task for a thousand time, we are submitting one by one
                 * to the executor threadpool
                 *
                 */
                executorService.submit(() -> blockingIoOperation());
            }
        }



        /**
         * another way of doing other than try with resources block
         */

        /*ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        for (int i = 0; i < NUMBER_OF_TASKS; i++) {

            executorService.submit(() -> blockingIoOperation());
        }
        executorService.shutdown();
        try{
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        }catch (InterruptedException e){

        }
        finally {

            executorService.shutdownNow();
        }*/



        /***
         * or could be done in this way too
         *
         */

        /**
         * Java
         * import java.util.concurrent.*;
         *
         * public class CountDownLatchExample {
         *
         *     public static void main(String[] args) throws InterruptedException  
         *  {
         *         int numberOfTasks = 5;
         *         CountDownLatch latch = new CountDownLatch(numberOfTasks);
         *
         *         ExecutorService executorService = Executors.newFixedThreadPool(3);  
         *  // Limit to 3 concurrent tasks
         *
         *         for (int i = 0; i < numberOfTasks; i++) {
         *             executorService.submit(() -> {
         *                 try {
         *                     // Simulate a task
         *                     Thread.sleep(1000);
         *                     System.out.println("Task " + Thread.currentThread().getName() + " completed.");
         *                 } catch (InterruptedException e) {
         *
         *                 } finally {
         *                     latch.countDown(); // Signal that a task has finished
         *                 }
         *             });
         *         }
         *
         *         latch.await(); // Wait for all tasks to complete
         *         System.out.println("All tasks have finished.");
         *
         *         executorService.shutdown();
         *     }
         * }
         * Use code with caution.
         *
         * In this example:
         *
         * A CountDownLatch is created with a count equal to the number of tasks.
         * Tasks are submitted to the executor service.
         * Each task, upon completion, calls latch.countDown().
         * The main thread calls latch.await(), which blocks until the latch count reaches zero (all tasks have finished).
         * Once all tasks are complete, the main thread continues execution.
         * This approach ensures that the main thread waits for all tasks to finish before proceeding, while still limiting the number of concurrent tasks using the fixed thread pool.
         *
         * ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
         * final CountDownLatch latch = new CountDownLatch(NUMBER_OF_TASKS); // Initialize with number of tasks
         *
         * try {
         *     for (int i = 0; i < NUMBER_OF_TASKS; i++) {
         *         executorService.submit(() -> {
         *             blockingIoOperation();
         *             latch.countDown(); // Signal completion after each task
         *         });
         *     }
         *
         *     executorService.shutdown();
         *     latch.await(); // Wait for all tasks to complete using the latch
         * } finally {
         *     // No lock or condition.signal() needed
         * }
         */
    }
    /**
     * behaves similarly to a IOOperation as it is blocking operation
     */
    private static void blockingIoOperation() {
        log.info("Executing a blocking task from thread: {}", Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        log.info("Completed a blocking task from thread: {}", Thread.currentThread());
    }
}

