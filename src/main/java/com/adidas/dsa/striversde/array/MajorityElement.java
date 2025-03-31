package com.adidas.dsa.striversde.array;

/**
 * Given an array nums of size n, return the majority element.
 *
 * The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [3,2,3]
 * Output: 3
 * Example 2:
 *
 * Input: nums = [2,2,1,1,1,2,2]
 * Output: 2
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= n <= 5 * 104
 * -109 <= nums[i] <= 109
 *
 *
 * Follow-up: Could you solve the problem in linear time and in O(1) space?
 *
 *
 * [2, 2, 1, 1, 1, 2, 2]
 *
 * we need to find the element that is occurring > n/2 in the array, where n = size(array),
 *
 * ans = 2
 *
 *
 * Approach: We can obviously use a hashmap to track the number of times an element occur in the array and keep track of
 * the maximum element and keep updating
 *
 * however, there is an efficient algorithm in place the idea is Moore's Voting Algorithm
 *
 * First idea for this is an element that is appearing more than n/2 times
 * there should be ideally 1 element
 *
 *
 *
 * [2, 2, 1, 1, 1, 2, 2]
 *  |
 * cI
 *
 * we start at the pointer above and assume that element is the most dominant element in the array and initialize the count
 *  for the element as 1
 *
 *  what we do is keep adding in the array if we have count = 0 (which is initially)
 *
 *  we assume the current element to be the majority element -> currentElement = 2
 *
 *      now while count != 0
 *        we check if the currentElement -> nums[cI] == element, increment the count (count++)
 *        otherwise count--
 *
 *        [2, 2, 1, 1, 1, 2, 2]
 *         |        |
 *
 *         in this above sub-array as shown above, count = 1 -> 2 -> 1 -> 0
 *
 *         now the idea says that this 2 cannot be the majority element in the above subarray (count > n/2) as if it had
 *         been greater than n/2 then the count should not have been 0
 *
 *         -> like for this case [2, 2, 2, 1]
 *
 *  Now after one cycle of the operation is completed we are left with a element that is supposed to be the majority element
 *
 *
 *  we loop through again (count variable is meaningless for us so we reassign) and count the majority element,
 *  we check if the count of the element(majority) >  n/2, if yes we return the element
 *
 *  otherwise we return -1
 *
 *
 *
 *
 *
 *
 *
 */
public class MajorityElement {
  public int majorityElement(int[] nums) {
    int count = 0, element = -1, size = nums.length;

    for(int cI = 0; cI < size; cI++){
      if(count == 0){
        count = 1;
        element = nums[cI];
      }else if(element == nums[cI]) count++;
      else count--;
    }

    count = 0;

    for(int cI = 0 ; cI < size; cI++){
      if(nums[cI] == element) count++;
    }

    return count > size/2 ? element : -1;

  }
}
