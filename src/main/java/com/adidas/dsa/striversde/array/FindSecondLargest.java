package com.adidas.dsa.striversde.array;

import lombok.extern.slf4j.Slf4j;

/**
 * Given an array and find the second largest in the array, without using extra space and in
 * a single pass
 *
 *
 * array[] = [7, 8, 9, 6, 5, 10]
 *
 * secondHighest = 9
 */
@Slf4j
public class FindSecondLargest {
  public int findSecondHighest(int[] nums){
    int secondHighest = -1, highest = -1;
    for(int num : nums){
      if(num > highest){
        secondHighest = highest;
        highest = num;
      }
      else if(secondHighest < num && num < highest) {
        secondHighest = num;
      }
    }

    return secondHighest;
  }

  public static void main(String[] args){
    log.info("Second highest number in array [7, 8, 9, 6, 5, 10] is : {}",
        new FindSecondLargest().findSecondHighest(new int[]{7, 8, 9, 6, 5, 10}));
  }
}
