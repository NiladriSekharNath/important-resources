package com.adidas.dsa.striversde.linkedlist;

import java.util.PriorityQueue;

/**
 *
 * Approach : Recursively break into smaller list and merge into single list
 *
 * Time Complexity: O( N*(2M) ) ~ O(2 N*M)where N is the length of the linked list along the next pointer and M is the length of the linked list along the child pointers.
 *
 * The merge operation in each recursive call takes time complexity proportional to the length of the linked lists being merged as they have to iterate over the entire lists. Since the vertical depth of the linked lists is assume to be M, the time complexity for a single merge operation is proportional to O(2*M).
 * This operation operation is performed N number of times (to each and every node along the next pointer list) hence the resultant time complexity becomes: O(N* 2M).
 * Space Complexity : O(1) as this algorithm uses no external space or additional data structures to store values. But a recursive stack uses O(N) space to build the recursive calls for each node along the next pointer list.
 */
public class FlattenMultiLevelLinkedList {
  public Node flatten(Node root) {
    return makeFlattenedListFromMinHeap(flattenHelper(root, new PriorityQueue<>((node1, node2) -> node1.data - node2.data)));
  }

  private PriorityQueue<Node> flattenHelper(Node root, PriorityQueue<Node> minHeap) {
    if (root == null) return null;
    Node bottom = root.bottom, next = root.next;
    minHeap.add(root);
    root.bottom = null;
    root.next = null;
    flattenHelper(next, minHeap);
    flattenHelper(bottom, minHeap);
    return minHeap;
  }

  private Node makeFlattenedListFromMinHeap(PriorityQueue<Node> minHeap) {
    Node head = minHeap.poll(), temp = head;
    while (!minHeap.isEmpty()) {
      temp.bottom = minHeap.poll();
      temp = temp.bottom;
    }
    return head;
  }

  /**
   * Approach 2
   *
   */
  Node flattenUsingMergeProperties(Node root) {
    return flattenHelper(root);
  }

  private Node flattenHelper(Node root) {
    if (root == null || root.next == null) {
      return root;
    }

    Node mergedHead = flattenHelper(root.next);
    return merge(root, mergedHead);


  }

  private Node merge(Node bottom, Node right) {
    Node dummy = new Node(-1), temp = dummy, bottomPointer = bottom, rightPointer = right;

    while (bottomPointer != null && rightPointer != null) {
      if (bottomPointer.data <= rightPointer.data) {
        temp.bottom = new Node(bottomPointer.data);
        bottomPointer = bottomPointer.bottom;
      } else {
        temp.bottom = new Node(rightPointer.data);
        rightPointer = rightPointer.bottom;
      }
      temp = temp.bottom;
    }

    if (bottomPointer == null) {
      temp.bottom = rightPointer;
    } else {
      temp.bottom = bottomPointer;
    }

    return dummy.bottom;

  }

  public static class Node {
    int data;
    Node next;
    Node bottom;

    public Node(int data) {
      this.data = data;
      this.next = null;
      this.bottom = null;
    }
  }


  public static void main(String[] args) {

    Node root5 = new Node(5);
    Node root7 = new Node(7);
    Node root8 = new Node(8);
    Node root9 = new Node(9);
    Node root10 = new Node(10);
    Node root11 = new Node(11);
    Node root12 = new Node(12);
    Node root19 = new Node(19);

    root5.bottom = root7;
    root5.next = root10;
    root7.bottom = root8;
    root8.next = root9;
    root9.next = root11;
    root10.bottom = root12;
    root10.next = root19;




    /* new FlattenMultiLevelLinkedList().flatten(root5);*/
    new FlattenMultiLevelLinkedList().flattenUsingMergeProperties(root5);
  }
}
