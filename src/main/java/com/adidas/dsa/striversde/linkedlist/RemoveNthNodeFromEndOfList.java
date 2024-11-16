package com.adidas.dsa.striversde.linkedlist;

import com.adidas.dsa.striversde.linkedlist.node.ListNode;

/**
 * solution is just we need two pointers a fastpointer and a slowPointer that would do this,
 * fastPointer would move ahead till n times till the n == 0 and then slowPointer and fastpointer moves one-by-one till the
 * fastPointer points to the last ListNode then the slowPointer is pointing the ListNode that has the address of the pointer to be deleted
 * <p>
 * TC : O(n), SC: O(1)
 */
public class RemoveNthNodeFromEndOfList {

  /**
   * self approach using a dummy ListNode, could also be done without a dummy ListNode
   */
  public ListNode removeNthFromEnd(ListNode head, int n) {
    ListNode dummy = new ListNode(-1);
    dummy.next = head;
    ListNode slowPointer = dummy, fastPointer = dummy;
    for (int i = 0; i < n && fastPointer != null; i++) {
      fastPointer = fastPointer.next;
    }
    if (fastPointer == null) return head;
    while (fastPointer.next != null) {
      slowPointer = slowPointer.next;
      fastPointer = fastPointer.next;
    }
    slowPointer.next = slowPointer.next.next;
    return dummy.next;
  }

  /**
   * this approach almost similar but without the dummy node, the purpose for this dummy node is
   * the dummy one is if we have let's say 5 nodes in the list 1 -> 2 -> 3 -> 4 -> 5 then the node to be deleted that one is
   * 6th node then there is a problem for my case I have not deleted anything just returned the list
   */

  public ListNode deleteNthNodefromEnd(ListNode head, int N) {
    // Create two pointers, fastp and slowp
    ListNode fastp = head;
    ListNode slowp = head;

    // Move the fastp pointer N nodes ahead
    for (int i = 0; i < N; i++)
      fastp = fastp.next;

    // If fastp becomes null, the Nth ListNode from the end is the head
    if (fastp == null)
      return head.next;

    // Move both pointers until fastp reaches the end
    while (fastp.next != null) {
      fastp = fastp.next;
      slowp = slowp.next;
    }

    // Delete the Nth ListNode from the end
    ListNode delNode = slowp.next;
    slowp.next = slowp.next.next;
    delNode = null;
    return head;
  }
}
