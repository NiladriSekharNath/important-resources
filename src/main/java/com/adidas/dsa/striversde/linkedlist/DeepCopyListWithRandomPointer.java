package com.adidas.dsa.striversde.linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Approach 1  : Using a hashmap storing the address of the nodes , oldNodes with the duplicated(newly created nodes) use this information to
 * point the random pointers
 *
 * Time Complexity: O(2N) where N is the number of nodes in the linked list. The linked list is traversed twice, once for creating copies of each node and for the second time to set the next and random pointers for each copied node. The time to access the nodes in the map is O(1) due to hashing.
 *
 * Space Complexity : O(N)+O(N)where N is the number of nodes in the linked list as all nodes are stored in the map to maintain mappings and the copied linked lists takes O(N) space as well.
 *
 */
public class DeepCopyListWithRandomPointer {
  public Node copyRandomList(Node head) {
    Map<Node, Node> valueNewNodeMap = new HashMap<>();

    Node temp = head, newHead = new Node(-1), newTemp = newHead;

    int count = 0;

    while (temp != null) {
      Node newNode = new Node(temp.val);
      valueNewNodeMap.put(temp, newNode);
      newTemp.next = newNode;
      newTemp = newTemp.next;
      temp = temp.next;
      count++;
    }
    temp = head;
    newTemp = newHead.next;
    count = 0;

    while (temp != null) {
      if (temp.random != null) {
        Node randomNodeToBePointed = valueNewNodeMap.get(temp.random);
        newTemp.random = randomNodeToBePointed;

      }

      temp = temp.next;
      newTemp = newTemp.next;
      count++;
    }

    return newHead.next;
  }


  /**
   *
   *  Approach : Instead of putting the newly created pointers and storing the same in a hashmap we
   *  are connecting the pointers between the nodes of the original list
   *
   *  like for example
   *
   *
   *  7 -> 13 -> 10 (originally)
   *
   *  7 -> 7N -> 13 -> 13N -> 10 - 10N (N means newly created nodes)
   *
   *  and hence as following connecting the random and
   *
   *  then disconnecting the previously attached links
   *
   *  to restore the original and the new list
   *
   *
   *
   * */
  public Node copyRandomListSpaceOptimised(Node head){
    Node temp = head;
    while(temp != null){
      Node newNode = new Node(temp.val);
      newNode.next = temp.next;
      temp.next = newNode;
      temp = temp.next.next;
    }
    temp =  head;
    while(temp != null){
      if(temp.random != null){
        temp.next.random = temp.random.next;
      }
      temp = temp.next.next;
    }

    Node dummyNode = new Node(-1), res = dummyNode;
    temp = head;
    while(temp != null){
      res.next = temp.next;
      temp.next = temp.next.next;
      res = res.next;
      temp = temp.next;
    }

    List<List<Integer>> list = new ArrayList<>();


    return dummyNode.next;
  }

  public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
      this.val = val;
      this.next = null;
      this.random = null;
    }
  }
}
