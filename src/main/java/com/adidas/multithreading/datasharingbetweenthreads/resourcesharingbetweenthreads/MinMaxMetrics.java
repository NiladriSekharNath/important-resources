package com.adidas.multithreading.datasharingbetweenthreads.resourcesharingbetweenthreads;

public class MinMaxMetrics {

    // Add all necessary member variables

    /**
     * Initializes all member variables
     */

    private volatile long minValue;
    private volatile long maxValue ;
    public MinMaxMetrics() {
        this.minValue = Long.MAX_VALUE;
        this.maxValue = Long.MIN_VALUE;
    }

    /**
     * Adds a new sample to our metrics.
     */
    public synchronized void addSample(long newSample) {
        minValue = Math.min(minValue, newSample);
        maxValue = Math.max(maxValue, newSample);
    }

    /**
     * Returns the smallest sample we've seen so far.
     */
    public long getMin() {
        return minValue;
    }

    /**
     * Returns the biggest sample we've seen so far.
     */
    public long getMax() {
        return maxValue ;
    }
}
