package com.adidas.dsa.striversde.linkedlist;

import com.adidas.dsa.striversde.linkedlist.node.ListNode;

/**
 * Approach : Take each group and reverse it and connect it
 */
public class ReverseNodesInKGroups {


  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode temp = head, kthNode = null, prev = null;

    kthNode = findKthNode(temp, k - 1);

    ListNode next = null;

    if (kthNode == null) return head;

    next = kthNode.next;
    kthNode.next = null;
    reverse(temp);
    prev = temp;
    prev.next = reverseKGroup(next, k);


    return kthNode;
  }

  private ListNode findKthNode(ListNode head, int k) {
    ListNode temp = head;
    for (int i = 0; i < k && temp != null; i++) {
      temp = temp.next;

    }
    return temp;
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

  public static void main(String[] args) {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);
    ListNode node5 = new ListNode(5);

    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;

    new ReverseNodesInKGroups().reverseKGroup(node1, 2);
  }
}
