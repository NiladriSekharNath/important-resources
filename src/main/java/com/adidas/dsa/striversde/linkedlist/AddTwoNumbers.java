package com.adidas.dsa.striversde.linkedlist;

import com.adidas.dsa.striversde.linkedlist.node.ListNode;

/**
 * Approach : We can add these two numbers in the linked list using the problem while we are simulating normally
 * <p>
 * Time Complexity : O(max(len(l1), len(l2))
 */
public class AddTwoNumbers {
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode resultHead = new ListNode();
    ListNode tempPointer = resultHead, prev = null;

    int carry = 0;
    while (l1 != null || l2 != null) {
      int result = carry;
      if (l1 != null && l2 != null) {
        result += l1.val + l2.val;

      } else if (l1 != null) {
        result += l1.val;
      } else result += l2.val;
      carry = result >= 10 ? 1 : 0;
      tempPointer.val = result % 10;
      tempPointer.next = new ListNode();
      prev = tempPointer;
      tempPointer = tempPointer.next;
      l1 = l1 != null ? l1.next : l1;
      l2 = l2 != null ? l2.next : l2;

    }

    if (carry == 1) {
      prev.next = new ListNode(1);
    } else {
      prev.next = null;
    }
    return resultHead;

  }
}
