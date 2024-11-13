package com.adidas.dsa.striversde.linkedlist;

import com.adidas.dsa.striversde.linkedlist.node.ListNode;

/**
 * Approach : is using the slow pointer and fast pointer approach where the slow pointer
 * moves by one and fast pointer moves by two times and we get to the solution where
 * at the end slowPointer is at the middle
 *
 * <p>
 *
 * Time Complexity: O(N/2) The algorithm requires the 'fast' pointer to reach the end of the list which it does after approximately N/2 iterations (where N is the total number of nodes). Therefore, the maximum number of iterations needed to find the middle node is proportional to the number of nodes in the list, making the time complexity linear, or O(N/2) ~ O(N).
 *
 * <p>
 * Space Complexity : O(1) There is constant space complexity because it uses a constant amount of extra space regardless of the size of the linked list. We only use a few variables to keep track of the middle position and traverse the list, and the memory required for these variables does not depend on the size of the list.
 */
public class LinkedListMiddle {
  public ListNode middleNode(ListNode head) {
    ListNode slowPointer = head, fastPointer = head;
    while (fastPointer != null && fastPointer.next != null) {
      slowPointer = slowPointer.next;
      fastPointer = fastPointer.next.next;
    }

    return slowPointer;
  }
}
