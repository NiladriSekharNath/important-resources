package com.adidas.multithreading.notifywait.resources.testingfiles;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringJoiner;

@Slf4j
public class MatrixMultiplicator {
    private static final int N = 10;

    private static final String INPUT_FILE = "C:\\Users\\nathnil\\Downloads\\design-patterns\\design-patterns\\src\\main\\java\\com\\adidas\\multithreading\\notifywait\\resources\\testingfiles\\matrices";

    private static final String OUTPUT_FILE = "C:\\Users\\nathnil\\Downloads\\design-patterns\\design-patterns\\src\\main\\java\\com\\adidas\\multithreading\\notifywait\\resources\\testingfiles\\matrices_result.txt";


    public static void main(String args[]) throws IOException {
        ThreadSafeQueue threadSafeQueue = new ThreadSafeQueue();
        File inputFile = new File(INPUT_FILE);
        File outputFile = new File(OUTPUT_FILE);

        MatricesReaderProducer matricesReaderProducer = new MatricesReaderProducer(new FileReader(inputFile), threadSafeQueue);
        MatricesMultiplierConsumer matricesMultiplierConsumer = new MatricesMultiplierConsumer(threadSafeQueue, new FileWriter(outputFile));

        matricesReaderProducer.start();
        matricesMultiplierConsumer.start();
    }

    private static class MatricesMultiplierConsumer extends Thread {
        private ThreadSafeQueue threadSafeQueue;
        private FileWriter fileWriter;

        public MatricesMultiplierConsumer(ThreadSafeQueue threadSafeQueue, FileWriter fileWriter) {
            this.threadSafeQueue = threadSafeQueue;
            this.fileWriter = fileWriter;
        }

        @Override
        public void run() {
            while (true) {
                MatricesPair matricesPair = threadSafeQueue.remove();
                if (matricesPair == null) {
                    log.info("No more matrices to read from the queue, consumer is terminating");
                    break;
                }
                float[][] result = multiplyMatrices(matricesPair.matrix1, matricesPair.matrix2);
                try {
                    saveMatrixToFile(fileWriter, result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private float[][] multiplyMatrices(float[][] matrix1, float[][] matrix2) {
            float[][] result = new float[N][N];
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    for (int k = 0; k < N; k++) {
                        result[r][c] += matrix1[r][k] * matrix2[k][c];
                    }
                }
            }
            return result;
        }

        private void saveMatrixToFile(FileWriter fileWriter, float[][] resultMatrix) throws IOException {
            for (int r = 0; r < N; r++) {
                StringJoiner stringJoiner = new StringJoiner(",");
                for (int c = 0; c < N; c++) {
                    stringJoiner.add(String.format("%.2f", resultMatrix[r][c]));

                }
                fileWriter.write(stringJoiner.toString());
                fileWriter.write("\n");
            }
            fileWriter.write("\n");
        }
    }


    private static class MatricesReaderProducer extends Thread {
        private Scanner scanner;
        private ThreadSafeQueue threadSafeQueue;

        public MatricesReaderProducer(FileReader reader, ThreadSafeQueue threadSafeQueue) {
            this.scanner = new Scanner(reader);
            this.threadSafeQueue = threadSafeQueue;

        }

        @Override
        public void run() {
            while (true) {
                float[][] matrix1 = readMatrix();
                float[][] matrix2 = readMatrix();
                if (matrix1 == null || matrix2 == null) {
                    threadSafeQueue.terminate();
                    log.info("No more matrices to read. Producer Thread is terminating");
                    return;
                }


                MatricesPair matricesPair = new MatricesPair(matrix1, matrix2);
                threadSafeQueue.add(matricesPair);
            }
        }

        private float[][] readMatrix() {
            float[][] matrix = new float[N][N];
            for (int r = 0; r < N; r++) {
                if (!scanner.hasNext()) {
                    return null;
                }
                String[] line = scanner.nextLine().split(",");
                for (int c = 0; c < N; c++) {
                    matrix[r][c] = Float.valueOf(line[c]);
                }
            }
            scanner.nextLine();
            return matrix;
        }
    }


    private static class ThreadSafeQueue {
        /**
         * Non-thread safe queue here
         */
        private Queue<MatricesPair> queue = new LinkedList<>();
        /**
         * to indicate if our queue contains any matrices or not
         */
        private boolean isEmpty = true;

        /**
         * isTerminate boolean flag is used to signal the consumer thread to stop consuming
         * as the producer thread has nothing to offer
         */
        private boolean isTerminated = false;

        private static final int CAPACITY = 5;


        /**
         * used by the producer thread to add a matrix pair to the queue
         *
         * @param matricesPair
         */
        public synchronized void add(MatricesPair matricesPair) {
            /**
             * please understand that there is back pressure applied here in this level
             * when the queue.size() == capacity then all the producer threads are waiting here on this level waiting to be waked up by
             * some notifyAll() kind of method
             *
             * please see the com/adidas/multithreading/notifywait/resources/learningfiles/queue-size-growing-without-back-pressure.png
             * and the following example here to understand why this is necessary
             *
             * as if the inputs to the matrices here are from a datasource or a bigger file there would be out of Memory
             * app crashes similar to this graph here com/adidas/multithreading/notifywait/resources/learningfiles/queue_size_growing_without_back_pressure_possibly_explosion_and_app_crashing.png
             *
             */
            while (queue.size() == CAPACITY) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.add(matricesPair);
            isEmpty = false;
            /**
             * this will notify if a consumer is waiting to wake up
             */
            notify();
        }

        /**
         * used by the consumer method to remove a pair from the queue
         * and consume it
         */
        public synchronized MatricesPair remove() {
            /***
             * this conditions is checked by the consumer everytime to find out if the queue is empty or
             * producer asked to terminate the consumer
             *
             */
            MatricesPair matricesPair = null;
            while (isEmpty && !isTerminated) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }

            }

            /***
             * once the condition is not met, we exit from the loop
             */

            if (queue.size() == 1)
                isEmpty = true;

            /**
             * as highlighted in this comments below in terminate() method notifyAll() comments  :
             *
             *  all the consumers are returned from here,  when all the consumers are done(like the queue is empty or the queue is terminated)
             *  we return from here
             *
             * when the terminate() method notifyAll() is called
             */

            if (queue.size() == 0 && isTerminated) {
                return null;
            }

            log.info("queue size : {}", queue.size());

            matricesPair = queue.remove();

            if (queue.size() == CAPACITY - 1)
                notifyAll();

            return matricesPair;
        }


        /***
         * terminate method, called by the producer to let the consumer know to terminate its
         * thread as the queue becomes empty
         */
        public synchronized void terminate() {
            isTerminated = true;
            /**
             * let's understand this purpose of this code for the notifyAll(), why did we add here with a question and answer
             *
             *
             *
             notify() vs notifyAll()

             Question:

                 In the matrix multiplication wait-notify code,  we have used notifyAll() inside remove and
                 terminate methods but notify() in add method of ThreadSafeQueue class. Why is such a difference?
                 Since, we have just one producer and one consumer each, can't we just use notify() for all 3 methods?

                 If we have multiple producers and consumers in the same application, can we use notifyAll() for all 3 methods?



             Answer:
                 When we have one producer and one consumer it really doesn't matter.

                 But when we have multiple consumers, then we want to wake up only one consumer when we add a new matrix,

                 After all only one consumer can process one task right? So in the add(..) ,

                 we want to call notify() (to notify only one consumer) not notifyAll().


                 If we have multiple producers, then once a consumer consumes a matrix and the queue size is now is CAPACITY - 1,

                 we want to let all producers know that they now have space in the queue to produce a new item.

                 We don't know which producer has new work so in the remove() we call notifyAll() inside the if statement.

                 We don't really have multiple producers in this example, but we could.


                 And finally in the terminate() (when our producer is out of tasks) we want to wake all the consumers up,

                 so they can terminate, that is why we are using notifyAll()., also see the place where the above is called
             *
             *
             */


            notifyAll();

        }

    }

    private static class MatricesPair {
        public float[][] matrix1;
        public float[][] matrix2;

        public MatricesPair(float[][] matrix1, float[][] matrix2) {
            this.matrix1 = matrix1;
            this.matrix2 = matrix2;
        }
    }
}
