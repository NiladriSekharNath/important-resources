package com.adidas.multithreading.threadjoining;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.*;

/**
 * Since in Threads we are not sure of the way or the order in which the threads
 * are executing we can't predict the way the threads are going to work for us to effectively use the threads to execute parallely
 * also wait for the results for them to work, for example let's say we have two Threads Thread A and Thread B where which if
 * we are not sure of the order of the execution one thread can perform concurrently or parallely or Thread B completes before Thread A,
 * and we need Thread A's result as input for Thread B we would have to make Thread B stop executing.
 * <p>
 * Problem Statement we are calculating the factorial for large numbers since there are a
 * lot of numbers we calculate each number in different threads
 *
 * understanding this context as to why do we say the main thread starts the new thread using thread.start() https://gemini.google.com/app/6ff794e783d3c36e
 *
 * okay also there is another way to perform the result which is shown in the exercise threadJoining/exercise/ComplexCalculation.java
 */

@Slf4j
public class ThreadJoining {
    public static void main(String args[]) throws InterruptedException {
        /**
         * if we see the value of the first number in the list which is very large we can see
         * when the threads are working concurrently all these threads
         * the main thread is still waiting for the completion on the first thread
         * factorial of the number "10000000L" which is because it's a big number and other threads are completed,
         * which we can see from the picture in the resources ->  "threadjoining/resources/learningimages/Main_thread_waiting_on_the_first_running_long_thread.png
         * so to handle this case we add a time in the thread.join method is till the time main thread will wait for completion of the worker thread results
         *
         * but even if we add the join method our main method though would stop waiting and move on to the next thread join in the loop
         * and other following instructions but that doesn't mean our code execution is completed the program would not stop gracefully what we should
         * do instead is make the thread as daemon or throw interrupts from outside
         */
        List<Long> numbers = Arrays.asList(10000000L, 3435L, 3434L, 23234L, 4656L, 23L, 5556L);
        List<Thread> threads = new ArrayList<>();

        Map<Thread, Factorial> resultMap = new LinkedHashMap<>();

        for(long number : numbers){
            Factorial factorialResult = new Factorial(number);
            Thread eachThread = new Thread(factorialResult);
            threads.add(eachThread);
            resultMap.put(eachThread, factorialResult);
        }

        for(Thread eachThread : threads){
            eachThread.setDaemon(true);
            eachThread.start();
            /**
             * we did not add the eachThread.join() here after every thread start because the threads
             * would then start after, one thread is completed because the main thread is waiting on the result of the previous join,
             * so we did not start all the threads concurrently
             */
        }

        /**
         * we would have to add the join method between the place where the race condition is happening
         *
         * so by the time the for loop from line 43 to 45 is completed we would have all the results completed
         * for the next call in the main thread
         *
         *
         *
         join() method clarification
         Question : -----
            According to what I have read in online sources, whenever a thread executes join() method, that thread called by the thread(mostly the main Thread)
            goes to waiting state,
            and it will be in waiting state till the completion of the threads task on which its called.
            In our example here, thread.join() which is inside for loop is called by main thread
            and main thread waits until all threads finishes the task (factorial here).
            My confusion is, when main thread executes thread.join() executes for the first time, it goes to waiting state.
            Then, how can it execute thread.join() for rest of the threads in the for loop ?

         Instructor Answer: ----

             Hi Developer,

                 "Then, how can it execute thread.join() for rest of the threads in the for loop ?"

                 It doesn't, until the first thread.join() returns.

                 So if we have 2 threads that the main thread is waiting on, and the main thread calls thread.join() in a loop on each of those threads,

                 the main thread blocks on the first thread.join() then when the first thread terminates

                 then the main thread wakes up and proceeds to call thread.join() on the next thread (next iteration).

                 If by that time the second thread already terminated, then thread.join() returns immediately, otherwise the main thread waits.

                 I hope it helps,


         Question :---
            Then why are we using multiple threads here? As far as I understand, they run the tasks subsequently not in parallel, cause each thread waits after the other.


         Instructor :---
             Hi Developer,

                 We DO run the threads concurrently.
                 Notice that we start ALL the threads first.

                 Only after we started all of them, the main thread proceeds to attempt to join on each of the threads.

                 While the main thread is waiting for the first thread to terminate, other background threads are running concurrently.

         *
         */
        for(Thread eachThread : threads){
            eachThread.join(2000);
        }

        int iterator = 0 ;
        for(Map.Entry<Thread, Factorial> eachMap : resultMap.entrySet()){
            Factorial factorialResult = eachMap.getValue();
            /**
             * here when we try to get the result for the value for the factorial of some number, we need to understand that
             * when the main thread is requesting for the result of the factorial, the factorial threads have not yet completed calculating
             * and we are requesting the value from the main thread, this is called the race condition where the main thread is already
             * requesting result and the other factorial threads are still computing, so we would need to join(stop) the threads to
             * make the main thread wait for completion of the factorial threads
             *
             * so the race condition is in between the  "eachThread.start();" on line 35 and the "factorialResult.isFinished()" on line 53
             */
            if(factorialResult.isFinished())
                log.info("Factorial of number: {}, is result: {}", numbers.get(iterator++), factorialResult.getResult());
            else
                log.info("Factorial of number: {}, is result: {}", numbers.get(iterator++), "result is still computing");
        }


    }

    private static class Factorial implements Runnable {

        private long number;
        private BigInteger result;
        private boolean finished;

        public Factorial(long number) {
            this.number = number;
            this.result = BigInteger.ONE;
        }

        @Override
        public void run() {
            this.result = factorial(number);
            this.finished = true;
        }

        private BigInteger factorial(long number) {
            if (number == 0) return result;
            for (long iterator = 1; iterator <= number; iterator += 1) {
                this.result = this.result.multiply(BigInteger.valueOf(iterator));
            }
            return result;
        }

        public boolean isFinished() {
            return this.finished;
        }

        public BigInteger getResult() {
            return this.result;
        }
    }

}
