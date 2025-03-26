package com.adidas.hld.systemdesign.keyconcepts.concepts.ratelimiting;

import java.time.Instant;

/**
 * The Token Bucket algorithm is one of the most popular and widely used rate limiting approaches due to its simplicity and effectiveness.
 *
 * How It Works:
 * Imagine a bucket that holds tokens.
 *
 * The bucket has a maximum capacity of tokens.
 *
 * Tokens are added to the bucket at a fixed rate (e.g., 10 tokens per second).
 *
 * When a request arrives, it must obtain a token from the bucket to proceed.
 *
 * If there are enough tokens, the request is allowed and tokens are removed.
 *
 * If there aren't enough tokens, the request is dropped.
 *
 *
 * Pros:
 *
 * Relatively straightforward to implement and understand.
 *
 * Allows bursts of requests up to the bucket's capacity, accommodating short-term spikes.
 *
 * Cons:
 *
 * The memory usage scales with the number of users if implemented per-user.
 *
 * It doesn’t guarantee a perfectly smooth rate of requests.
 */
public class TokenBucket {
  /**
   * maximum number of tokens the bucket can hold
   */
  private final long capacity ;

  /**
   * Rate at which tokens are added to the bucket (tokens per second)
   */
  private final double fillRate;
  /**
   * Current number of tokens in the bucket
   */
  private double currentTokens;
  /**
   * Last Time we refilled the bucket
   */
  private Instant lastTimeStampTokenFilled;

  public TokenBucket(long capacity, double flowRate){
    this.capacity = capacity ;
    this.fillRate = flowRate;
    this.currentTokens = capacity ;
    lastTimeStampTokenFilled = Instant.now();
  }

  /**
   *
   * The idea in the allowRequest method is:
   *
   * Call refill() first:
   *
   * Check how much time has passed since the last refill.
   * Calculate how many tokens should have been added based on the fill rate.
   * Add those tokens to the bucket (up to the capacity limit).
   * Check token availability:
   *
   * After refilling, check if the current token count is enough to fulfill the request.
   * If enough tokens are available → consume the tokens and allow the request. ✅
   * If not enough tokens are available → deny the request. ❌
   *
   *
   */

  public synchronized boolean allowRequest(int tokens){
    /**
     * first check if tokens can be refilled
     */
    refill();

    /**
     * if tokens are not available then we drop the requests
     */

    if(this.currentTokens < tokens){
      return false;
    }

    this.currentTokens -= tokens;
    return true;

  }

  private void refill() {
    Instant currentTime = Instant.now();

    long duration = currentTime.toEpochMilli() - lastTimeStampTokenFilled.toEpochMilli();

    double tokensToAdd =  fillRate * duration / 1000 ;

    this.currentTokens = Math.min(capacity, this.currentTokens + tokensToAdd);

    this.lastTimeStampTokenFilled = currentTime;
  }

  public static void main(String args[]) throws InterruptedException {
    TokenBucket limiter = new TokenBucket(10, 1.0); // Capacity 10 tokens, 1 token/sec fill rate

    // Send 15 requests
    for (int i = 0; i < 15; i++) {
      System.out.println("Request " + (i + 1) + ": " + limiter.allowRequest(1));
      Thread.sleep(100);  // Sleep for 100ms
    }

    // Wait 5 seconds for tokens to refill
    System.out.println("Waiting for bucket to refill...");
    Thread.sleep(5000);

    // Try another request
    System.out.println("Request after refill: " + limiter.allowRequest(1));

  }
}
