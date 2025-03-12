package com.adidas.hld.systemdesign.keyconcepts.ratelimiting;

import java.time.Instant;

/**
 * This algorithm combines the Fixed Window Counter and Sliding Window Log approaches for a more accurate and efficient solution.
 *
 * Instead of keeping track of every single request’s timestamp as the sliding log does,
 * it focus on the number of requests from the last window.
 *
 * So, if you are in 75% of the current window, 25% of the weight would come from the previous window, and the rest from the current one:
 *
 * weight = (100 - 75)% * lastWindowRequests + currentWindowRequests
 *
 * Now, when a new request comes, you add one to that weight (weight + 1). If this new total crosses our set limit,
 * we have to reject the request.
 *
 * How it works:
 * Keep track of request count for the current and previous window.
 *
 * Calculate the weighted sum of requests based on the overlap with the sliding window.
 *
 * If the weighted sum is less than the limit, allow the request.
 *
 *
 * Pros:
 *
 * More accurate than Fixed Window Counter.
 *
 * More memory-efficient than Sliding Window Log.
 *
 * Smooths out edges between windows.
 *
 * Cons:
 * Slightly more complex to implement.
 *
 */
public class SlidingWindowCounter {


  private final long windowSizeInSeconds;   // Size of the sliding window in seconds
  private final long maxRequestsPerWindow;  // Maximum number of requests allowed in the window
  private long currentWindowStart;          // Start time of the current window
  private long previousWindowCount;         // Number of requests in the previous window
  private long currentWindowCount;          // Number of requests in the current window

  public SlidingWindowCounter(long windowSizeInSeconds, long maxRequestsPerWindow) {
    this.windowSizeInSeconds = windowSizeInSeconds;
    this.maxRequestsPerWindow = maxRequestsPerWindow;
    this.currentWindowStart = Instant.now().getEpochSecond();
    this.previousWindowCount = 0;
    this.currentWindowCount = 0;
  }

  public synchronized boolean allowRequest() {
    long now = Instant.now().getEpochSecond();
    long timePassedInWindow = now - currentWindowStart;

    // Check if we've moved to a new window
    if (timePassedInWindow >= windowSizeInSeconds) {
      previousWindowCount = currentWindowCount;
      currentWindowCount = 0;
      currentWindowStart = now;
      timePassedInWindow = 0;
    }

    /**
     * (windowSizeInSeconds - timePassedInWindow) / windowSizeInSeconds: This fraction tells you how much of the
     * previous window's influence should still be considered.
     *
     * For example, if 10 seconds have passed in a 60-second window, 50/60 of the previous count is still relevant.
     * Multiplying this fraction by previousWindowCount: It scales the previous count down based on the
     * fraction of time remaining in the current window.
     *
     *  Example Setup:
     *
     * Window size: 60 seconds
     * Max requests: 3
     * Previous window requests: 3
     * Current window requests: 2
     * Current window start: 100
     * Request comes at: 130
     * So, 30 seconds have passed in the current window (timePassedInWindow = 30)
     *
     * weightedCount = 3 * ((60 - 30) / 60.0) + 2;
     *
     *
     * weightedCount = 3 * (30 / 60.0) + 2;
     * weightedCount = 3 * 0.5 + 2;
     * weightedCount = 1.5 + 2;
     * weightedCount = 3.5;
     *
     * SEE NOW THE 3.5 < 3 SO requests are dropped
     *
     */

    // Calculate the weighted count of requests
    double weightedCount = previousWindowCount * ((windowSizeInSeconds - timePassedInWindow) / (double) windowSizeInSeconds)
        + currentWindowCount;

    if (weightedCount < maxRequestsPerWindow) {
      currentWindowCount++;  // Increment the count for this window
      return true;           // Allow the request
    }
    return false;  // We've exceeded the limit, deny the request
  }
}

