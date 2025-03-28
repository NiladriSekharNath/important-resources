package com.adidas.dsa.striversde.graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/***
 * Intuition: Since we need to find the minimum number of multiplications in order to attain the end number from the start number,
 * the one standard algorithm that we can think of for finding the shortest/minimum paths is of course Dijkstra’s Algorithm.
 * But a question may arise in our minds: how can we depict this problem in the form of a graph? So let us understand this
 * through an illustration :
 *
 * Given to us src = 3, end = 30 and [2, 5, 7] we need to find the minimum number of steps to reach destination from source
 * and we would have to mod our result
 *
 * . At each step, start is multiplied with any number in the array and then mod operation with 100000 is done to get the new start.
 *
 *            src, mul
 *              (3,0)
 *           x2 / x5|  x7\
 *         (16,1) (15,1) (21, 1)
 *         /  |  \
 *  (12,2) (30,2) (42,2)
 *
 *  As per the above image, we can clearly make out that the numbers after multiplication with the start number can be
 *  considered as nodes of a graph ranging from 0 to 99999 and the edges from each node will be the size of the given
 *  array i.e. the number of ways in which we can multiply a number.
 *  We update the distance array whenever we find a lesser number of multiplications in order to reach a node.
 *  In this way, whenever we reach the end number, the multiplications needed to reach it would always be minimum.
 *
 *
 *  Approach:
 *
 * This problem can be visualized as a graph problem as we need to find the minimum number of steps to reach an end
 * number from the start following a number of multiplications. We would be solving it using Dijkstra's Algorithm.
 *
 * Initial configuration:
 *
 * Queue is taken as MinHeap could have worked but since the steps are increasing upwards only 1,2,3 -- every time queue
 * would suffice
 *
 * Queue: Define a Queue which would contain pairs of the type {steps, num }, where ‘steps’ indicates the currently
 * updated value of no. of steps taken to reach from source to the current ‘num’.
 * Distance Array: Define a distance array that would contain the minimum no. of multiplications/distance
 * from the start number to the current number. If a cell is marked as ‘infinity’ then it is treated as unreachable/unattained.
 * Start and End: Define the start and the end value which we have to reach through a series of multiplications.
 * The Algorithm consists of the following steps :
 *
 * Start by creating a queue that stores the step-num pairs in the form {steps, num} and a dist array with each node
 * initialized with a very large number ( to indicate that they’ve not been attained initially). The size of the ‘dist’
 * array is set to 100000 because it is the maximum number of distinct numbers that can be generated.
 *
 * We push the start number to the queue along with its steps marked as ‘0’ initially because we’ve just started the
 * algorithm.
 *
 * Pop the element from the front of the queue and look out for its adjacent nodes (here, adjacent nodes can be
 * regarded as the numbers which we get when we multiply the start number by each element from the arr).
 *
 * If the current dist value for a number is better than the previous distance indicated by the distance array,
 * we update the distance/steps in the array and push it to the queue.
 *
 * We repeat the above three steps until the queue becomes empty or we reach the end number.
 *
 * Return the calculated number of steps after we reach the end number. If the queue becomes empty and
 * we don’t encounter the required number, return ‘-1’ indicating that the following number
 * is unattainable by multiplying the start number any number of times.
 *
 */
public class MinimumMultiplicationsToReachEnd {
  int minimumMultiplications(int[] nums, int start, int end) {
    int  modValue = (int)100_000;
    int[] dist = new int[modValue];

    if(start == end) return 0;

    Arrays.fill(dist, (int) 1e9);
    dist[start] = 0;

    Queue<int[]> queue = new LinkedList<>();

    queue.add(new int[]{dist[start], start});

    while(!queue.isEmpty()){
      int[] currentPair = queue.poll();

      int currentSteps = currentPair[0], currentNode = currentPair[1];
      for(int neighbour : nums){
        int targetNode = (currentNode * neighbour) % modValue;
        if(currentSteps + 1 < dist[targetNode]){
          dist[targetNode] = currentSteps + 1;

          /**
           * here after calculating of our target we don't check if we can get a better path after this step because we won't find
           * a better path as let's say we found one target let's say 75 at this steps = 4, any other step after this
           * would be either equal or more since the steps are increasing strictly(or monotonically)
           * so we don't need to compute extra for other steps
           *
           * and simply return
           */
          if(targetNode == end) return currentSteps + 1;
          queue.add(new int[]{dist[targetNode], targetNode});

        }


      }
    }

    return -1;
  }
}
