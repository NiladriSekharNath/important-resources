package com.adidas.dsa.striversde.customdatastructure.caches;

import java.util.HashMap;
import java.util.Map;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *
 * Implement the LRUCache class:
 *
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the
 * cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * Explanation
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // cache is {1=1}
 * lRUCache.put(2, 2); // cache is {1=1, 2=2}
 * lRUCache.get(1);    // return 1
 * lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 * lRUCache.get(2);    // returns -1 (not found)
 * lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 * lRUCache.get(1);    // return -1 (not found)
 * lRUCache.get(3);    // return 3
 * lRUCache.get(4);    // return 4
 *
 *
 * Constraints:
 *
 * 1 <= capacity <= 3000
 * 0 <= key <= 104
 * 0 <= value <= 105
 * At most 2 * 105 calls will be made to get and put.
 *
 *
 * We are given to design a fixed capacity LRU (Least Recently Used) Cache to Tackle this problem
 *
 * we are using doubly-linked list node
 * Node(key, value, next, prev)
 * map<NodeKey, node> -> (this map stores the <nodeKey, node>
 *
 *   this purpose of the map is to give us the node in O(1) time and also to track the size of the LRU cache
 *
 *
 *   and since it is a doubly linked list we are have direct access to the element
 *
 *   now the general idea is we are maintaining a capacity(as provided)
 *
 *   when the size == capacity we simply delete from the end of the list (pointed by tail)
 *
 *   head <-> tail
 *    every new element we are adding to the front of the list (after head)
 *
 *    but if there is get or put operation where the node is present we are doing is we are deleting the node from the
 *    tail and we are adding it back to the front the node
 *
 */
public class LRUCache {
  private Node head ;
  private Node tail ;
  private int capacity = 0;
  Map<Integer, Node> nodeKeyVsNode ;

  public LRUCache(int capacity) {

    this.head = new Node(0, 0);
    this.tail = new Node(capacity, 0);

    head.next = tail;
    tail.previous = head;
    this.capacity = capacity;
    this.nodeKeyVsNode = new HashMap<>();
  }

  public int get(int key) {

    if(!nodeKeyVsNode.containsKey(key)) return -1;

    Node node = nodeKeyVsNode.get(key);

    deleteFromCache(node);
    insertAfterHead(node);

    return node.value;

  }

  /**
   *
   * @param key
   * @param value
   *
   * key, value this is added to the Cache
   *
   * if value is found instead of fetching the value and modifying the value we are simply
   * removing the (last value from the tail) adding it back to front
   *
   */
  public void put(int key, int value) {

    if(nodeKeyVsNode.containsKey(key)) {
      deleteFromCache(nodeKeyVsNode.get(key));
    }
    else if(nodeKeyVsNode.size() == capacity){
      deleteFromCache(tail.previous);

    }

    insertAfterHead(new Node(key, value));

  }
  private void deleteFromCache(Node node){
    /**
     * we don't need to check if the map contains the node
     * where we are calling we are checking if the node is present in the map
     */

    nodeKeyVsNode.remove(node.key);

    node.previous.next = node.next;
    node.next.previous = node.previous;

  }

  private void insertAfterHead(Node node){

    Node nextNodeAfterHead = head.next;


    head.next = node;
    node.previous = head;

    nextNodeAfterHead.previous = node;
    node.next = nextNodeAfterHead;

    nodeKeyVsNode.put(node.key, node);
  }



  private class Node{
    int key;
    int value;
    Node previous;
    Node next;

    public Node(int key, int value){
      this.key = key ;
      this.value = value;
    }
  }
}
