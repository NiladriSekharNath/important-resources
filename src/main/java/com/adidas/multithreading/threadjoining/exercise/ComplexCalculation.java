package com.adidas.multithreading.threadjoining.exercise;

import java.math.BigInteger;

public class ComplexCalculation {
    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2) throws InterruptedException {
        BigInteger result;
        /*
            Calculate result = ( base1 ^ power1 ) + (base2 ^ power2).
            Where each calculation in (..) is calculated on a different thread
        */

        PowerCalculatingThread resultThread1 = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread resultThread2 = new PowerCalculatingThread(base2, power2);

        resultThread1.start();
        resultThread2.start();

        resultThread1.join();
        resultThread2.join();

        result = resultThread1.getResult().add(resultThread2.getResult());

        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        @Override
        public void run() {
            this.result = getPower(base, power);
        }


        private BigInteger getPower(BigInteger base, BigInteger power){
            BigInteger result = BigInteger.ONE;
            for (BigInteger iterator = BigInteger.ZERO; iterator.compareTo(power) != 0; iterator = iterator.add(BigInteger.ONE)) {

                result = result.multiply(base);

            }
            return result;
        }

        public BigInteger getResult() { return result; }
    }
}
