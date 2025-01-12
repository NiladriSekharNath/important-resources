package com.adidas.dsa.striversde.heaps;

/**
 * So basically we are asked to find the kthLargest element in a stream
 *  *
 *  * Optimized Solution Using a Min-Heap:
 *  * A min-heap is a binary heap where the root is the smallest element.
 *  *
 *  * Approach:
 *  *
 *  * Maintain a min-heap of size k.
 *  * For each new number:
 *  * Add the number to the heap.
 *  * If the heap size exceeds
 *  *
 *  * k, remove the smallest element (root).
 *  * The root of the heap will always be the kth largest element because:
 *  * The heap contains the largest
 *  *
 *  * k elements from the stream.
 *  * Among them, the smallest one is the kth largest overall.
 *  *
 *  * if k = 3 that means 3rd highest element in the stream
 *  *
 *  * [1,2,3,4,5]
 *  * [5,4,3,2,1]
 *  *
 *  * 3rd highest(k = 3) = 3
 *  *
 *  * min heap will hold after adding 1,2,3,4,5
 *  *
 *  * [3,4,5] highest would be 5, 2nd highest would be 4 and 3rd highest would be 3
 *
 *  same as the minHeap approach in find the kth largest element in the stream
 *
 *  however we could do this better way because since the previously the stream was increasing we could not
 *  apply quick sort now since the stream is increasing it would not give us result in optimal time frame
 *
 *  hence instead we do the quick select approach in the quick sort
 *
 *  let's say we have an array :
 *
 *  [3,2,1,5,6,4] and we require the k = 3 which means we require the 3rd largest element in the array
 *
 *  [1,2,3,4,5,6] so it's like saying 1st largest = 6, 2nd largest = 5, 3rd largest = 3
 *
 *  if we say from the reverse it's same as saying give me (size - k)th smallest element = (6 - 3) = element at 3rd index
 *
 *  so the value is at 4, now in order to find this we don't need to sort the entire array we just need to place the
 *  element 4 at the correct index = 3 if we are able to place that, then we get our answer
 *
 *  now if let's say while searching for the pivot we managed to place a pivot for the index = 2
 *
 *  which means we are able to place index 2 at correct position then all elements will be less than the index at 2
 *
 *  we need to search for the correct pivot (3) to the right then we simply call the right recursion
 *
 *  low = pivot + 1; , high = high(still) return quickSelect(nums,pivot + 1, high, kthSmallest);
 *
 *  again if we found a pivot of 5 and we need 3 then we need to go left recurion
 *
 *  otherwise low = low ,high = pivot - 1; -> return quickSelect(nums,low, pivot - 1, kthSmallest);
 */
public class KthLargestElementInAnArray {
  public int findKthLargest(int[] nums, int k) {
    int index = quickSelect(nums,0, nums.length-1,nums.length-k);
    return nums[index];

  }

  public int quickSelect(int[] nums,int low, int high, int kthSmallest) {
    if(low==high) return low;
    int pivot = partition(nums,low, high);

    if (pivot == kthSmallest)
      return pivot;
    else if (pivot < kthSmallest)//go right
      return quickSelect(nums,pivot + 1, high, kthSmallest);
    else//go left
      return quickSelect(nums,low, pivot - 1, kthSmallest);

  }

  /**
   * During partition we are simply keeping the pivot to the low, and then taking two pointers left and right
   *
   * we keep all elements <= pivot on the left and > pivot we keep on the right
   *
   * lastly, when left > right, that means right is in the territory of the lesser values so all we do is keep is
   *
   * swap low, right -> because we took the pivot at the low index
   *
   */
  public int partition(int[] nums,int low, int high) {
    int pivotVal = nums[low];

    int left = low, right = high;
    while(left < right){
      while(nums[left] <= pivotVal && left <= high-1) left++;
      while(nums[right] > pivotVal  && right >= low + 1) right--;
      if(left < right) swap(nums, left, right);

    }
    swap(nums, low, right);

    return right;

  }

  public void swap(int[]nums,int index1, int index2) {
    int temp = nums[index2];
    nums[index2] = nums[index1];
    nums[index1] = temp;
  }


}
