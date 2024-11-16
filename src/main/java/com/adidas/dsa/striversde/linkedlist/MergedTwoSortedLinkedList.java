package com.adidas.dsa.striversde.linkedlist;

import com.adidas.dsa.striversde.linkedlist.node.ListNode;

/**
 * Approach : we are using a dummyPointer and using that dummy pointer to move forward and point elements to the lower val list and moving
 * both the dummy and the list next
 *
 * <p>
 * Time Complexity: O(N1+N2) where N1 is the number of nodes in the first linked list and N1 in the second linked list as we traverse both linked lists in a single pass for merging without any additional loops or nested iterations.
 * <p>
 * Space Complexity : O(1) as no additional data structures or space is allocated for storing data, only a constant space for pointers to maintain for traversing the linked list.
 *
 */

public class MergedTwoSortedLinkedList {

  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null || list2 == null) return list1 == null ? list2 : list1;

    if (list1.val > list2.val) return mergeTwoLists(list2, list1);

    ListNode mergedHead = new ListNode(-1), tempNode = mergedHead;

    while (list1 != null && list2 != null) {
      if (list1.val <= list2.val) {
        tempNode.next = list1;
        list1 = list1.next;
      } else {
        tempNode.next = list2;
        list2 = list2.next;
      }

      tempNode = tempNode.next;
    }

    if (list1 == null) {
      tempNode.next = list2;
    } else {
      tempNode.next = list1;
    }

    return mergedHead.next;

  }


}
