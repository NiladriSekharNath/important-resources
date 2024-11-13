package com.adidas.dsa.striversde.linkedlist;

import com.adidas.dsa.striversde.linkedlist.node.ListNode;

/**
 * Approach : since we are given the current Node of the list we cannot delete that particular node
 * unless what we should do is copy the nextNode element and so we overwrite the current node's value with the
 * next node value and we remove the next node
 *
 * 4 -> 5 -> 1 -> 9  (Node to be deleted is 5)
 * ...
 * Copy next node's value
 * ...
 * 4 -> 1 ->  1 -> 9
 *
 * and delete the 3rd node from here since it's value is already deleted now
 *
 * 4 ->1 -------->9
 *
 *
 *
 * TC : O(1)
 * SC : O(1)
 */
public class DeleteGivenNodeInLinkedList {


  public void deleteNode(ListNode node) {
    ListNode nextNode = node.next;
    copyNextNodeValue(node, nextNode);
    ListNode nodeToDelete = node.next;
    node.next = node.next.next;
    nodeToDelete.next = null;
  }
  private void copyNextNodeValue(ListNode node, ListNode nextNode){
    node.val = nextNode.val;
  }
}
