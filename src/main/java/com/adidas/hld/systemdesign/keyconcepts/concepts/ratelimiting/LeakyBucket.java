package com.adidas.hld.systemdesign.keyconcepts.concepts.ratelimiting;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The Leaky Bucket algorithm is similar to Token Bucket but focuses on smoothing out bursty traffic.
 *
 * How it works:
 * Imagine a bucket with a small hole in the bottom.
 *
 * Requests enter the bucket from the top.
 *
 * The bucket processes ("leaks") requests at a constant rate through the hole.
 *
 * If the bucket is full, new requests are discarded.
 *
 *
 * Pros:
 *
 * Processes requests at a steady rate, preventing sudden bursts from overwhelming the system.
 *
 * Provides a consistent and predictable rate of processing requests.
 *
 * Cons:
 * Does not handle sudden bursts of requests well; excess requests are immediately dropped.
 *
 * Slightly more complex to implement compared to Token Bucket.
 */
public class LeakyBucket {
  private final long capacity;
  private final long leakRate;
  private final Queue<Instant> bucket;
  private Instant lastLeakTimeStamp;

  public LeakyBucket(long capacity, long leakRate) {
    this.capacity = capacity;
    this.leakRate = leakRate;
    this.bucket = new LinkedList<>();
    this.lastLeakTimeStamp = Instant.now();
  }

  /**
   *
   * Per second requests are leaked first based on the leakRate till the "toLeakRequest" are reached
   * and if the bucket.size < capacity meaning
   * bucket can hold additional request then requests are allowed in the bucket otherwise requests are dropped
   */

  public synchronized boolean allowRequest() {
    leak();

    if (bucket.size() < capacity) {
      bucket.offer(Instant.now());
      return true;
    }

    return false;
  }

  private void leak() {
    Instant currentTimeStamp = Instant.now();

    long duration = currentTimeStamp.toEpochMilli() - lastLeakTimeStamp.toEpochMilli();

    int toLeakRequests = (int) (leakRate * duration / 1000);

    for(int i = 0 ; i < toLeakRequests && bucket.size() > 0 ; i++){
      bucket.poll();
    }

    lastLeakTimeStamp = currentTimeStamp;
  }
}
