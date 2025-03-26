package com.adidas.hld.systemdesign.keyconcepts.concepts.ratelimiting;

import java.time.Instant;

/**
 * The Fixed Window Counter algorithm divides time into fixed windows and counts requests in each window.
 *
 * How it works:
 * Time is divided into fixed windows (e.g., 1-minute intervals).
 *
 * Each window has a counter that starts at zero.
 *
 * New requests increment the counter for the current window.
 *
 * If the counter exceeds the limit, requests are denied until the next window.
 *
 * Pros:
 *
 * Easy to implement and understand.
 *
 * Provides clear and easy-to-understand rate limits for each time window.
 *
 * Cons:
 *
 * Does not handle bursts of requests at the boundary of windows well. Can allow twice the rate of requests at the edges of windows.
 *
 */
public class FixedWindowCounter {
  private final long windowSizeInSeconds;  // Size of each window in seconds
  private final long maxRequestsPerWindow; // Maximum number of requests allowed per window
  private long currentWindowStart;         // Start time of the current window
  private long requestCount;               // Number of requests in the current window

  public FixedWindowCounter(long windowSizeInSeconds, long maxRequestsPerWindow) {
    this.windowSizeInSeconds = windowSizeInSeconds;
    this.maxRequestsPerWindow = maxRequestsPerWindow;
    this.currentWindowStart = Instant.now().getEpochSecond();
    this.requestCount = 0;
  }

  public synchronized boolean allowRequest() {
    long currentTime = Instant.now().getEpochSecond();

    // Check if we've moved to a new window

    /**
     * HERE IF we notice the currentTime - currentWindowStart (at the start of the program) when it exceeds the
     * windowSizeInSeconds value
     *
     * let's say
     *
     * currentWindowStart = 0 (hypothetical)
     * currentTime = 61
     *
     * and windowSizeInSeconds = 60
     *
     * then we start a new window with new request in the window
     *
     */
    if (currentTime - currentWindowStart >= windowSizeInSeconds) {
      currentWindowStart = currentTime;  // Start a new window
      requestCount = 0;          // Reset the count for the new window
    }

    if (requestCount < maxRequestsPerWindow) {
      requestCount++;  // Increment the count for this window
      return true;     // Allow the request
    }
    return false;  // We've exceeded the limit for this window, deny the request
  }
}
