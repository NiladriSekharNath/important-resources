package com.adidas.multithreading.async;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * theory guiding over here for better understanding
 *
 * https://4comprehension.com/completablefuture-the-difference-between-thenapply-thenapplyasync/
 *
 *
 */
@Slf4j
public class TestThenAcceptVsThenAcceptAsync {

    public static void main(String args[]) throws Exception {
        var testingObject = new TestThenAcceptVsThenAcceptAsync();
        testingObject.testAcceptVsAccepAsync();
        testingObject.test2();

        log.info("in main");
    }

    public void testAcceptVsAccepAsync() throws ExecutionException, InterruptedException {


        /**
         * here we are using the ExecutorService with a singleThreadExecutor where the thread is given a name of "deadpool"
         */
        try (ExecutorService executorService = Executors.newSingleThreadExecutor(
                anyRunnableTask -> new Thread(anyRunnableTask, "dead-pool"))) {

            /**
             * here the future object we are passing a task which is printing the current task and returning the 42 value and also we are passing the
             * executor
             */
            CompletableFuture<Integer> future = CompletableFuture
                    .supplyAsync(() -> {
                        log.info("Current thread name: {}", Thread.currentThread().getName());
                        return 42;
                    }, executorService);
            future.get();
        }
    }

    public void test2() {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor(r -> new Thread(r, "dead-pool"))) {

            CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName());
                return 42;
            }, executorService);


            CompletableFuture<String> future2 = future1.thenApply(i -> {
                System.out.println(Thread.currentThread().getName());
                return i.toString();
            });
        }

    }

    public void test2WithThreadBlockage(){
        try (ExecutorService executorService = Executors.newSingleThreadExecutor(r -> new Thread(r, "dead-pool"))) {

            CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
                System.out.println(Thread.currentThread().getName());
                return 42;
            }, executorService);


            CompletableFuture<String> future2 = future1.thenApply(i -> {

                //Thread.sleep(Integer.MAX_VALUE);

                System.out.println(Thread.currentThread().getName());
                return i.toString();
            });
        }
    }
}
