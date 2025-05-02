package com.adidas.hld.systemdesign.keyconcepts.concepts.ratelimiting;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The Sliding Window Log algorithm keeps a log of timestamps for each request and uses this to determine if a new request should be allowed.
 *
 * How it works:
 * Keep a log of request timestamps.
 *
 * When a new request comes in, remove all entries older than the window size.
 *
 * Count the remaining entries.
 *
 * If the count is less than the limit, allow the request and add its timestamp to the log.
 *
 * If the count exceeds the limit, request is denied.
 *
 * Pros:
 *
 * Very accurate, no rough edges between windows.
 *
 * Works well for low-volume APIs.
 *
 * Cons:
 *
 * Can be memory-intensive for high-volume APIs.
 *
 * Requires storing and searching through timestamps.
 *
 */
public class SlidingWindowLog {

  private final long windowSizeInSeconds;   // Size of the sliding window in seconds
  private final long maxRequestsPerWindow;  // Maximum number of requests allowed in the window
  private final Queue<Long> requestLog;     // Log of request timestamps

  public SlidingWindowLog(long windowSizeInSeconds, long maxRequestsPerWindow) {
    this.windowSizeInSeconds = windowSizeInSeconds;
    this.maxRequestsPerWindow = maxRequestsPerWindow;
    this.requestLog = new LinkedList<>();
  }

  /**
   * Determines whether a request is allowed based on the sliding window log algorithm.
   *
   * Example walkthrough:
   * - windowSizeInSeconds = 60, maxRequestsPerWindow = 3
   * - Request arrival times: [100, 120, 140, 180]
   *
   * Request at 100:
   *   - now = 100
   *   - windowStart = 100 - 60 = 40
   *   - Valid range: [40, 100]
   *   - Add 100 (within range)
   *
   * Request at 120:
   *   - now = 120
   *   - windowStart = 120 - 60 = 60
   *   - Valid range: [60, 120]
   *   - 100 is within range, so **keep 100**.
   *   - Add 120
   *
   * Request at 140:
   *   - now = 140
   *   - windowStart = 140 - 60 = 80
   *   - Valid range: [80, 140]
   *   - **100 is still within range, so keep 100.**
   *   - Add 140
   *
   * Request at 180:
   *   - now = 180
   *   - windowStart = 180 - 60 = 120
   *   - Valid range: [120, 180]
   *   - 100 is outside the range, so **remove 100**.
   *   - 120 and 140 are within range, **keep both**.
   *   - Add 180
   *
   * Final log state: [120, 140, 180]
   *
   * @return true if the request is allowed, false otherwise.
   */
  public synchronized boolean allowRequest() {
    long now = Instant.now().getEpochSecond();
    long windowStart = now - windowSizeInSeconds;

    // Remove timestamps that are outside the current window
    while (!requestLog.isEmpty() && requestLog.peek() <= windowStart) {
      requestLog.poll();
    }

    if (requestLog.size() < maxRequestsPerWindow) {
      requestLog.offer(now);  // Log this request
      return true;            // Allow the request
    }
    return false;  // We've exceeded the limit for this window, deny the request
  }

  public static void main(String[] args) throws Exception {
    var limiter = new SlidingWindowLog(60, 5);

    for(int i = 0 ; i < 10; i++){
      System.out.print(limiter.allowRequest());
      Thread.sleep(100);

    }

    Thread.sleep(60);
    System.out.println(limiter.allowRequest());


  }
}
