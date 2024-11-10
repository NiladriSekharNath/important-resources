package com.adidas.multithreading.async;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

@Slf4j
public class CompletableFuturesUnderstanding {

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        CompletableFuturesUnderstanding testingObject = new CompletableFuturesUnderstanding();

        testingObject.testCompletableFutures();
    }

    private void testCompletableFutures() throws ExecutionException, InterruptedException {
        /**
         * single task
         */
        Supplier<Long> supplierTask = () -> {
            try {
                return tasks(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        CompletableFuture<Long> test = CompletableFuture.supplyAsync(supplierTask);



        log.info("Completed Duration in, {}", test.get());

        /**
         * multiple task put everything in a list and execute each using a loop
         */

        Random random = new Random();

        List<Supplier<Long>> supplierList = new ArrayList<>();

        List<CompletableFuture<Long>> getValues = new ArrayList<>();

        int numberOfTasksToBePerformed = 5;
        for (int task = 1; task < numberOfTasksToBePerformed + 1 ; task++) {
            int finalTask = task;
            supplierList.add(() ->
            {
                try {
                    return tasks(finalTask);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

        }



        long startTime = System.currentTimeMillis();

        try(ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            for (int task = 0; task < numberOfTasksToBePerformed; task++) {
                getValues.add(CompletableFuture.supplyAsync(supplierList.get(task), executorService));
            }

            log.info("All tasks submitted : {} millis", (System.currentTimeMillis() - startTime));
        }


        List<Long> valuesOfTasks = new ArrayList<>();

        for(int task = 0 ; task < numberOfTasksToBePerformed ; task++){
            valuesOfTasks.add(getValues.get(task).get());

            log.info("Total Time taken for task id: {}, {} millis", task, valuesOfTasks.get(task));
        }

        log.info("All tasks completed : {} millis", (System.currentTimeMillis() - startTime));


    }

    public long tasks(int value) throws InterruptedException {

        if (value == 5)
            Thread.sleep(100000 * value);

        String threadName = Thread.currentThread().getName();

        long startTime = System.currentTimeMillis();
        //log.info("Task started by Thread : '{}', startTime : '{}'", threadName, startTime);
        for (int i = 0; i < value; i++) {
            log.info("Current in Thread : '{}' and tasks iterator Number : '{}'", threadName, i);
        }

        long endTime = System.currentTimeMillis();

        //log.info("Task completed by Thread : '{}', endTime : '{}'", threadName, endTime);
        return endTime - startTime;
    }

}
