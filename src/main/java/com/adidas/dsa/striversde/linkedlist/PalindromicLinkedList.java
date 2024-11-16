package com.adidas.dsa.striversde.linkedlist;

import com.adidas.dsa.striversde.linkedlist.node.ListNode;

/**
 * Approach : find the middle part of the linked list and reverse from the middle part
 * and check from the head and the middle part with two pointers if the values match then the linked list is palindromic
 *
 * 1 -> 2 -> 2 -> 1
 *
 * 1 -> 2 -> 1 -> 2  so from the middle it is palindromic
 *
 * TTime Complexity: O (2* N) The algorithm traverses the linked list twice, dividing it into halves. During the first traversal, it reverses one-half of the list, and during the second traversal, it compares the elements of both halves. As each traversal covers N/2 elements, the time complexity is calculated as O(N/2 + N/2 + N/2 + N/2), which simplifies to O(2N), ultimately representing O(N).
 *
 * Space Complexity: O(1) The approach uses a constant amount of additional space regardless of the size of the input linked list. It doesn't allocate any new data structures that depend on the input size, resulting in a space complexity of O(1).
 */
public class PalindromicLinkedList {
  public boolean isPalindrome(ListNode head) {
    /*ListNode dummy = new ListNode(0, head), slowPointer = dummy;*/

    ListNode slowPointer = head, fastPointer = head;
    /*int lengthOfList = len(head);
    double length = lengthOfList / 2.0;
    for (int i = 0; i < (int) Math.ceil(length); i++) {
      slowPointer = slowPointer.next;

    }*/
    while(fastPointer.next != null && fastPointer.next.next != null){
      slowPointer = slowPointer.next;
      fastPointer = fastPointer.next;
    }

    slowPointer.next = reverse(slowPointer.next);

    return validatePalindrome(head, slowPointer.next);
  }

  private ListNode reverse(ListNode head) {
    ListNode prev = null, current = head, next = null;
    while (current != null) {
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }

    return prev;
  }

  private boolean validatePalindrome(ListNode head, ListNode middlePointer) {
    ListNode slowPointer = head;
    while (middlePointer != null) {
      if (slowPointer.val != middlePointer.val)
        return false;
      slowPointer = slowPointer.next;
      middlePointer = middlePointer.next;
    }
    return true;
  }

  private int len(ListNode head) {
    ListNode tempPointer = head;
    int count = 0;
    while (tempPointer != null) {
      count++;
      tempPointer = tempPointer.next;
    }
    return count;

  }

  public static void main(String[] args) {
    ListNode node0 = new ListNode(1);
    ListNode node1 = new ListNode(0);
    ListNode node2 = new ListNode(1);


    new PalindromicLinkedList().isPalindrome(node0);

  }
}