package com.adidas.dsa.striversde.linkedlist;

import com.adidas.dsa.striversde.linkedlist.node.ListNode;

/**
 * Approach : Same as detecting cycle in a list using a slowPointer and a fastPointer moving at
 *  * twice the speed and once they meet that point we can there is a cycle otherwise there is no cycle
 *
 *  Time Complexity: O(N), where N is the number of nodes in the linked list. This is because in the worst-case scenario,
 *  the fast pointer, which moves quicker, will either reach the end of the list (in case of no loop) or meet the slow pointer (in case of a loop)
 *  in a linear time relative to the length of the list.
 *
 * The key insight into why this is O(N) and not something slower is that each step of the algorithm reduces the distance
 * between the fast and slow pointers (when they are in the loop) by one. Therefore, the maximum number of steps needed for them
 * to meet is proportional to the number of nodes in the list.
 *
 *
 * Space Complexity : O(1) The code uses only a constantamount of additionalspace, regardless of the linked list's length.
 * This is achieved by using two pointers (slow and fast) to detect the loop without any significant extra memory usage,
 * resulting in constantspace complexity, O(1).
 */
public class HasCycle {
  public boolean hasCycle(ListNode head) {
    ListNode slowPointer = head, fastPointer = head;
    while (fastPointer != null && fastPointer.next != null) {


      slowPointer = slowPointer.next;
      fastPointer = fastPointer.next.next;

      if( slowPointer == fastPointer )
        return true;

    }
    return false;
  }
}
