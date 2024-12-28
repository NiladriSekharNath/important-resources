package com.adidas.dsa.striversde.heaps;

import java.util.PriorityQueue;

/**
 * So basically we are asked to find the kthLargest element in a stream
 *
 * Optimized Solution Using a Min-Heap:
 * A min-heap is a binary heap where the root is the smallest element.
 *
 * Approach:
 *
 * Maintain a min-heap of size
 *
 * k.
 * For each new number:
 * Add the number to the heap.
 * If the heap size exceeds
 *
 * k, remove the smallest element (root).
 * The root of the heap will always be the kth largest element because:
 * The heap contains the largest
 *
 * k elements from the stream.
 * Among them, the smallest one is the kth largest overall.
 *
 * if k = 3 that means 3rd highest element in the stream
 *
 * [1,2,3,4,5]
 * [5,4,3,2,1]
 *
 * 3rd highest(k = 3) = 3
 *
 * min heap will hold after adding 1,2,3,4,5
 *
 * [3,4,5] highest would be 5 2nd highest would be 4 and third highest would be 3
 */

public class KthLargestElementInAStream {
  PriorityQueue<Integer> minHeap = new PriorityQueue<>();
  int maxSize = 0;
  public KthLargestElementInAStream(int k, int[] nums) {
    this.maxSize = k;
    addElementsToMinHead(k, nums);

  }

  public int add(int val) {
    int kthLargestValue = 0;
    if(minHeap.size() <= maxSize)
      minHeap.add(val);
    while(minHeap.size() > maxSize)
      minHeap.poll();
    return kthLargestValue = minHeap.peek();
  }

  private void addElementsToMinHead(int k, int[] nums){
    for(Integer ele : nums){
      add(ele);
    }
  }
}
