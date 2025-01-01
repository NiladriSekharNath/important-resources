package com.adidas.dsa.striversde.heaps;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Solution 1: MaxHeap to store a half of low numbers, MinHeap to store a half of high numbers
 *
 * let's say we have numbers like
 *
 * 5,4,3,2,1
 *
 * maxHeap : [1,2,3] minHeap: [4,5]
 *
 * if maxHeap will have a greater size the minHeap but only by 1 that also, then only we get the median as maxHeap.peek() if odd
 *
 * The idea is to divide numbers into 2 balanced halves, one half low stores low numbers, the other half high stores high numbers.
 * To access the median in O(1), we need a data structure that give us the maximum of low half and the minimum of high half in O(1).
 * That's where maxHeap and minHeap come into play.
 *
 * We use maxHeap to store a half of low numbers, top of the maxHeap is the highest number among low numbers.
 * We use minHeap to store a half of high numbers, top of the minHeap is the lowest number among high numbers.
 *
 * We need to balance the size between maxHeap and minHeap while processing. Hence after adding k elements,
 *
 * If k = 2 * i then maxHeap.size = minHeap.size = i
 * If k = 2 * i + 1, let maxHeap store 1 element more than minHeap, then maxHeap.size = minHeap.size + 1.
 *
 * When adding a new number num into our MedianFinder:
 *
 * Firstly, add num to the maxHeap, now maxHeap may contain the big element (which should belong to minHeap).
 * So we need to balance, by removing the highest element from maxHeap, and offer it to minHeap.
 *
 * Now, the minHeap might hold more elements than maxHeap,
 * in that case, we need to balance the size, by removing the lowest element from minHeap and offer it back to maxHeap.
 * When doing findMedian():
 *
 * If maxHeap.size > minHeap.size return top of the maxHeap, which is the highest number among low numbers.
 * Else if maxHeap.size == minHeap return the (maxHeap.top() + minHeap.top()) / 2.
 */
public class MedianInARunningStream {
  PriorityQueue<Integer> maxHeap ;
  PriorityQueue<Integer> minHeap ;

  public MedianInARunningStream() {
    this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    this.minHeap = new PriorityQueue<>();
  }

  public void addNum(int num) {
    maxHeap.add(num);
    minHeap.add(maxHeap.poll());
    if(minHeap.size() > maxHeap.size())
      maxHeap.add(minHeap.poll());
  }

  public double findMedian() {
    if(maxHeap.size() > minHeap.size())
      return maxHeap.peek();
    return (maxHeap.peek() + minHeap.peek())/2.0;
  }

}
