package com.adidas.dsa.striversde.linkedlist;

public class ReverseLinkedList {

  public ListNode reverseListIterative(ListNode head) {
    ListNode prev = null, current = head, next = null;
    while (current != null) {
      next = current.next;
      current.next = prev;
      prev = current;
      current = next;
    }
    return prev;
  }

  /**
   *
   * @param head head is the head of the linked list
   * @return we are returning the reversed list
   *
   * 1 -> 2 -> 3 -> 4 -> 5
   *
   * returned
   *
   * 5 -> 4 -> 3 -> 2 -> 1
   *
   *
   * the video link to the solution : https://www.youtube.com/watch?v=D2vI2DNJGd8&t=1595s
   *
   * the main objective of the recursive solutions is to practice the recursion again with this example.
   *
   * so how we are approaching recursion here is this way
   *
   * we are breaking the entire problem in smaller parts and trying to solve the problem
   *
   * example dry run:
   *
   * prob : 1 -> 2 -> 3 -> 4 -> 5 , (pro - tip: let's ask this question, can we solve this problem? If yes then good otherwise break into sub-problem)
   *
   *    sub-prob : 2 -> 3 -> 4 -> 5 (pro - tip: let's ask this question, can we solve this problem? If yes then good otherwise break into sub-problem)
   *
   *      sub-prob : 3 -> 4 -> 5 (pro - tip: let's ask this question, can we solve this problem? If yes then good otherwise break into sub-problem)
   *
   *           sub-prob : 4 -> 5 (pro - tip: let's ask this question, can we solve this problem? If yes then good otherwise break into sub-problem)
   *
   *                sub-prob : 5 (pro - tip: let's ask this question, can we solve this problem? If yes then good otherwise break into sub-problem)
   *                              this one, yes we can solve this sub-problem, we simply return from here
   *
   *                next : 4 -> 5 , now here we try to solve this smaller problem, we make 5 -> 4 ... and we continue upwards till we find a pattern
   *
   *                ...
   *
   */
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;

    ListNode lastNode = reverseList(head.next);
    ListNode nextNode = head.next;
    nextNode.next = head;
    head.next = null;
    return lastNode;

  }


  public class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }
}
