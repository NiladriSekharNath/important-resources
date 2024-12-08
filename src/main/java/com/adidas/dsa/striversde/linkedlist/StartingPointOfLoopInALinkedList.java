package com.adidas.dsa.striversde.linkedlist;

import com.adidas.dsa.striversde.linkedlist.node.ListNode;

/**
 * Approach : Same as detecting cycle in a list using a slowPointer and a fastPointer moving at
 * twice the speed and once they meet if they don't meet and the value is null we point the slowPointer to head and keep the moving at the same speed now
 * slow and fast when they meet that is the starting point
 *
 * Time Complexity: O(N) The code traverses the entire linked list once, where 'n' is the number of nodes in the list. This traversal has a linear time complexity, O(n).
 *
 * Space Complexity : O(1) The code uses only a constant amount of additional space, regardless of the linked list's length. This is achieved by using two pointers (slow and fast) to detect the loop without any significant extra memory usage, resulting in constant space complexity, O(1).
 */
public class StartingPointOfLoopInALinkedList {
  public ListNode detectCycle(ListNode head) {
  ListNode slowPointer = head, fastPointer = head ;
  while(fastPointer != null && fastPointer.next != null){
    slowPointer = slowPointer.next;
    fastPointer = fastPointer.next.next;

    if(slowPointer == fastPointer) break;
  }

  if(fastPointer == null || fastPointer.next == null) return null;

  slowPointer = head;
  while(slowPointer != null && fastPointer != null){
    if(slowPointer == fastPointer) break;
    slowPointer = slowPointer.next;
    fastPointer = fastPointer.next;

  }
  return slowPointer;
}
}
