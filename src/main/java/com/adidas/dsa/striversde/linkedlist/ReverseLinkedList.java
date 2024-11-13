package com.adidas.dsa.striversde.linkedlist;

import com.adidas.dsa.striversde.linkedlist.node.ListNode;

/**
 * Recursive Approach
 * <p>
 * Time Complexity: O(N) This is because we traverse the linked list twice: once to push the values onto the stack, and once to pop the values and update the linked list. Both traversals take O(N) time.
 * <p>
 * Space Complexity : O(1) No additional space is used explicitly for data structures or allocations during the linked list reversal process. However, it's important to note that there is an implicit use of stack space due to recursion. This recursive stack space stores function calls and associated variables during the recursive traversal and reversal of the linked list. Despite this, no extra memory beyond the program's existing execution space is allocated, hence maintaining a space complexity of O(1).
 */
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
   * @param head head is the head of the linked list
   * @return we are returning the reversed list
   * <p>
   * 1 -> 2 -> 3 -> 4 -> 5
   * <p>
   * returned
   * <p>
   * 5 -> 4 -> 3 -> 2 -> 1
   * <p>
   * <p>
   * the video link to the solution : https://www.youtube.com/watch?v=D2vI2DNJGd8&t=1595s
   * <p>
   * the main objective of the recursive solutions is to practice the recursion again with this example.
   * <p>
   * so how we are approaching recursion here is this way
   * <p>
   * we are breaking the entire problem in smaller parts and trying to solve the problem
   * <p>
   * example dry run:
   * <p>
   * prob : 1 -> 2 -> 3 -> 4 -> 5 , (pro - tip: let's ask this question, can we solve this problem? If yes then good otherwise break into sub-problem)
   * <p>
   * sub-prob : 2 -> 3 -> 4 -> 5 (pro - tip: let's ask this question, can we solve this problem? If yes then good otherwise break into sub-problem)
   * <p>
   * sub-prob : 3 -> 4 -> 5 (pro - tip: let's ask this question, can we solve this problem? If yes then good otherwise break into sub-problem)
   * <p>
   * sub-prob : 4 -> 5 (pro - tip: let's ask this question, can we solve this problem? If yes then good otherwise break into sub-problem)
   * <p>
   * sub-prob : 5 (pro - tip: let's ask this question, can we solve this problem? If yes then good otherwise break into sub-problem)
   * this one, yes we can solve this sub-problem, we simply return from here
   * <p>
   * next : 4 -> 5 , now here we try to solve this smaller problem, we make 5 -> 4 ... and we continue upwards till we find a pattern
   * <p>
   * ...
   */
  public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;

    /**
     * this line : ListNode nextNode = head.next, works because at that point let's say at the third last step in
     * the recursive flow
     *
     * when ,
     * head -> 3 -> 4 -> 5
     *
     * and recursively we got 5 -> 4
     * <p>
     * the head 3 is still pointing to 4 ( 3 -> 4) something like ( 5 -> 4 <- 3 <- head)
     * </p>
     */

    ListNode lastNode = reverseList(head.next);
    ListNode nextNode = head.next;
    nextNode.next = head;
    head.next = null;
    return lastNode;

  }

}
