package com.adidas.dsa.striversde.linkedlist;

import com.adidas.dsa.striversde.linkedlist.node.ListNode;

/**
 * Question : Rotate a linked list to the right by k times  :
 *
 * 1 -> 2 -> 3 -> 4 -> 5
 *
 * After k = 2 times
 *
 * 4 -> 5 -> 1 -> 2 -> 3
 *
 * Approach :
 *
 * 'k' can be any number greater than the the length of the list so we perform
 *
 * k = k % lengthOfList
 *
 * reason for this is we would have the same list if the rotated after
 *
 * example :
 *
 * 0 -> 1 -> 2, k = 4
 *
 * after 1st rotation
 *
 * 2 -> 0 -> 1
 *
 * after 2nd rotation
 *
 * 1 -> 2 -> 0
 *
 * after 3rd rotation
 *
 * 0 -> 1 -> 2
 *
 * after 4th rotation
 *
 * 2 -> 0 -> 1
 *
 * after performing the above approach we are now doing this find the slowpointer(by the (RemoveNthNodeFromEndOfList) approach and the next of this slowPointer is the point to be rotated
 * so we pick the nextPart and attach the same to head of the list
 *
 *
 *
 */
public class RotateLinkedList {
  public ListNode rotateRight(ListNode head, int k) {
    if(head == null || head.next == null ) return head;
    int listLength = len(head);
    k %= listLength;

    if(k == 0) return head;

    ListNode slowPointer = head, fastPointer = head, rotatedHead = null;
    for(int i = 0; i<k ; i++){
      fastPointer = fastPointer.next;
    }

    while(fastPointer.next!=null){
      fastPointer = fastPointer.next;
      slowPointer = slowPointer.next;
    }

    rotatedHead = slowPointer.next;
    slowPointer.next = null;
    fastPointer.next = head;

    return rotatedHead;
  }

  private int len(ListNode head){
    int count = 0;
    ListNode temp = head;
    while(temp != null){
      temp = temp.next;
      count ++ ;
    }
    return count;
  }
}

