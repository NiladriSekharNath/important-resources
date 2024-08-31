package com.adidas.multithreading.virtualthreads.learningfiles;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Please read this to understand the concept of virtual threads and how it runs under the hood:
 *
 * As soon as we create at least one virtual thread under the hood, the JVM creates a relatively small
 *
 * internal pool of platform threads.
 *
 * And now whenever the JVM wants to run a particular virtual thread thread A, it mounts it on one of
 *
 * the platform threads within its pool.
 *
 * When a virtual thread is mounted on a platform thread, that platform thread is called a carrier thread.
 *
 * Now, if the virtual thread finishes its execution, the JVM will unmount that thread from its carrier
 *
 * and make that platform thread available for other virtual threads.
 *
 * That virtual thread object now becomes garbage that the garbage collection can clean up at any time.
 *
 * However, in certain situations, if Thread A has not finished but is unable to make any progress at
 *
 * that time, the JVM will unmounted but save its current state on the heap.
 *
 * We will talk about what those situations are later in the course.
 *
 * That state includes its instruction pointer and the snapshot of the carrier thread stack.
 *
 * At that point, the JVM can use this platform thread to mount another virtual thread on it.
 *
 * For example, thread B later, when thread A is able to continue, the JVM can do one of the following.
 *
 * If another platform thread is currently available and does not carry any other virtual thread, it will
 *
 * simply mount virtual thread A on it.
 *
 * On the other hand, if there isn't any available platform thread at that moment, thread A will just
 *
 * have to wait when one of the virtual threads for instance thread B running on a carrier thread can no
 *
 * longer make any progress, the JVM will unmount thread B by copying its state from the carrier thread
 *
 * to the heap and then mount thread A back on that carrier thread by setting the carrier threads instruction
 *
 * pointer to that of the virtual thread A's instruction pointer and also copy the snapshot of thread A's stack
 *
 * data from the heap back to the carrier threads stack memory.
 *
 * It's worth pointing out that we, the developers, have very little control over the carrier threads
 *
 * and the scheduling of virtual threads on them.
 *
 * It is something that the JVM manages for us under the hood.
 *
 * So now that we understand what virtual threads are and how they work, let's demonstrate how to create
 *
 * and run them in practice.
 */

@Slf4j
public class VirtualThreadsExample {
    private static final int NUMBER_OF_VIRTUAL_THREADS = 10000;
    public static void main(String args[]) throws InterruptedException {
        Runnable runnable = () -> log.info("Inside thread : {}", Thread.currentThread());
        Thread platformThread = new Thread(runnable);

        /**
         *
         * another way of creating a platform thread in a more explicit way is as shown below
         *
         * Thread platformThread = Thread.ofPlatform().unstarted(runnable);
         *
         * so similarly if we want to create a virtual thread it is shown as below
         */

        Thread virtualThread = Thread.ofVirtual().unstarted(runnable);

        platformThread.start();
        platformThread.join();

        virtualThread.start();
        virtualThread.join();

        /***
         * On running the VirtualThread as shown below after running in the console it is shown as
         *
         * Inside thread : VirtualThread[#22]/runnable@ForkJoinPool-1-worker-1
         *
         * we see that the object of VirtualThread is created that has an id of 22 also this tells us
         *
         * that to schedule this and any future virtual threads, the JVM created an internal
         *
         * thread pool of platform threads, which is called Forkjoinpool number one.
         *
         * And then the JVM mounted our virtual thread on one of those worker threads, which is called Worker
         *
         * one.
         *
         * In order to understand that above better we do something like this
         *
         */

        /**
         * The first one was mounted on worker one and the second on Worker two.
         *
         * Now to see the relationship between the number of virtual threads and the number of platform threads
         *
         * within that Forkjoinpool, let's increase the number of virtual threads from 2 to 20.
         *
         * And now let's run the application.
         *
         * And as we can see, we indeed created 20 new virtual threads, each one with its own unique ID.
         *
         * However, based on their names, we can see that the JVM dynamically decided to create a pool of six
         *
         * platform threads to be their carriers, and all those virtual threads were scheduled to run on this
         *
         * small pool of threads.
         */

        /***
         * now on increasing the number of virtual threads to 1000 or 10000 we would see something like
         * this, Now, while we indeed have 1000 or 10000 virtual threads, the JVM created only 12 platform threads in its
         *
         * internal pool.
         *
         * And the reason it chose this number as the upper limit is because my computer has only 12 logical(virtual) cores(hyperthreading) and
         *
         * there is no point in creating more platform threads because the maximum number of threads we can run
         *
         * in parallel is 12.
         */

        List<Thread> threads = new ArrayList<>();

        for(int i = 0 ; i < NUMBER_OF_VIRTUAL_THREADS ; i++){
            Thread eachVirtualThread = Thread.ofVirtual().unstarted(runnable);
            threads.add(eachVirtualThread);
        }

        for(Thread eachVirtualThread : threads)
            eachVirtualThread.start();

        for(Thread eachVirtualThread : threads)
            eachVirtualThread.join();

        /***
         * new threads group to run and understand different example
         */

        log.info("------------------------------------------ different thread group example ------------------------");

        List<Thread> newBlockingThreads = new ArrayList<>();

        for(int i = 0 ; i < 2 ; i++){
            Thread eachVirtualThread = Thread.ofVirtual().unstarted(new BlockingTask());
            newBlockingThreads.add(eachVirtualThread);
        }

        for(Thread eachVirtualThread : newBlockingThreads)
            eachVirtualThread.start();

        for(Thread eachVirtualThread : newBlockingThreads)
            eachVirtualThread.join();

    }

    /***
     *  in order to demonstrate the process of mounting and unmounting we are doing a simple refractoring of the above code to
     *  help us understand the process of mounting and unmounting
     *
     *  17:41:26.454 [virtual-10039] INFO com.adidas.multithreading.virtualthreads.learningfiles.VirtualThreadsExample -- Inside thread before blocking Task VirtualThread[#10039]/runnable@ForkJoinPool-1-worker-5
     * 17:41:26.454 [virtual-10040] INFO com.adidas.multithreading.virtualthreads.learningfiles.VirtualThreadsExample -- Inside thread before blocking Task VirtualThread[#10040]/runnable@ForkJoinPool-1-worker-7
     * 17:41:27.469 [virtual-10039] INFO com.adidas.multithreading.virtualthreads.learningfiles.VirtualThreadsExample -- Inside thread after blocking Task VirtualThread[#10039]/runnable@ForkJoinPool-1-worker-5
     * 17:41:27.469 [virtual-10040] INFO com.adidas.multithreading.virtualthreads.learningfiles.VirtualThreadsExample -- Inside thread after blocking Task VirtualThread[#10040]/runnable@ForkJoinPool-1-worker-7
     *
     * now if we see the above logs here we see Virtual Thread id 10039 although gets mounted on platform thread 5 and then after it is unblocked it gets mounted back on platform thread 5 but it could have been the case
     * the platform thread gets mounted back on different platform thread which was pretty normal and outside the developers control
     * which was similarly for normal threads that we were creating previously
     *
     */

    private static class BlockingTask implements Runnable {

        @Override
        public void run() {
            log.info("Inside thread before blocking Task {}", Thread.currentThread());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            log.info("Inside thread after blocking Task {}", Thread.currentThread());

        }
    }

}
