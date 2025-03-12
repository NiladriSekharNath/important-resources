package com.adidas.dsa.striversde.customdatastructure.caches;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 *
 * Implement the LFUCache class:
 *
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or inserts the key if not already present
 * .
 * When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item.
 * For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 * To determine the least frequently used key, a use counter is maintained for each key in the cache.
 * The key with the smallest use counter is the least frequently used key.
 *
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation).
 * The use counter for a key in the cache is incremented either a get or put operation is called on it.
 *
 * The functions get and put must each run in O(1) average time complexity.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * Output
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 *
 * Explanation
 * // cnt(x) = the use counter for key x
 * // cache=[] will show the last used order for tiebreakers (leftmost element is  most recent)
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // return 1
 *                  // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 2 is the LFU key because cnt(2)=1 is the smallest, invalidate 2.
 *                  // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // Both 1 and 3 have the same cnt, but 1 is LRU, invalidate 1.
 *                  // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // return -1 (not found)
 * lfu.get(3);      // return 3
 *                  // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // return 4
 *                  // cache=[4,3], cnt(4)=2, cnt(3)=3
 *
 *
 * Constraints:
 *
 * 1 <= capacity <= 104
 * 0 <= key <= 105
 * 0 <= value <= 109
 * At most 2 * 105 calls will be made to get and put.
 *
 *
 * Problem Statement : We are tasked to design the Least Frequently Used Cache ->
 *
 * The LFU (Least Frequently Used) Cache is a cache replacement policy that removes the least frequently accessed items
 * when the cache reaches its capacity. Each item in the cache has a frequency count,
 * which increases every time the item is accessed. When the cache is full, the item with the lowest frequency count is evicted.
 * If multiple items have the same frequency, the least recently used among them is removed.
 *
 *
 * so what we are doing is having this below structures
 *
 * NodesMap<NodeKey, Node>
 * frequencyMap<NodeFrequency, LRUCachesListOfThisFrequency>
 * capacity for the LFU cache
 *
 * minFrequency -> storing the lowest frequency of the LFU Caches
 *
 * so idea is
 *
 *     1 -> node1 -> node2 -> node3 -> ... -> [LRU - CACHE - 1]
 *     2 -> node15 -> node16 -> node 17 -> ... [LRU - CACHE - 2]
 *
 *
 *     so we have all nodes stored in LRU caches with their respective frequencies
 *     So what we are doing if we say perform any kind of operation(get/put) in the node3 which has frequency = 1
 *     we remove from that list and add it the list, LRU-CACHE - 2 list with frequency 2
 *
 *
 *
 *
 *
 *
 */

public class LFUCache {
  int capacity;
  int size ;
  int minFrequency ;
  Map<Integer, Node> nodesMap ;
  Map<Integer, LRUCache> frequencyMap;

  public LFUCache(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    this.minFrequency = 0;
    this.nodesMap = new HashMap<>();
    this.frequencyMap = new HashMap<>();
  }

  public int get(int key) {
    if(!nodesMap.containsKey(key)) return -1;

    Node currentNode = nodesMap.get(key);

    update(currentNode);

    return currentNode.value;

  }

  public void put(int key, int value) {
    /**
     * handling the case if capacity = 0 then we are returning and not inserting anything into the cache
     */
    if(capacity == 0) return;
    if(nodesMap.containsKey(key)){
      Node currentNode = nodesMap.get(key);
      currentNode.value = value;
      update(currentNode);
    }
    else{
      size++;

      /**
       * if the size > capacity we simply find the minimumFreqCacheList and return from the tail
       * meaning we have more keys than the desired capacity
       */
      if(size > capacity){
        LRUCache minFreqList = frequencyMap.get(minFrequency);
        nodesMap.remove(minFreqList.tail.prev.key);
        minFreqList.delete(minFreqList.tail.prev);
        size--;
      }

      /**
       * now here once we add a new value undoubtedly the minFrequency would be 1 and the similar approach follows
       */

      minFrequency = 1;

      Node newNode = new Node(key, value);

      LRUCache currList = frequencyMap.getOrDefault(1, new LRUCache());

      currList.insert(newNode) ;
      frequencyMap.put(1, currList);
      nodesMap.put(key, newNode);
    }
  }

  private void update(Node currentNode) {

    int currFreq = currentNode.freq;

    /**
     * this one we are not doing getOrDefault because here for the curr frequency there will be a list if the
     * currentNode that we are updating is present
     */
    LRUCache currList = frequencyMap.get(currFreq);
    currList.delete(currentNode);

    /**
     * 1 -> node1
     * 2 -> node2 -> node3
     *
     * minFrequency = 1
     *
     * here in the cache if we perform any kind of operation on the node1 we would be doing this
     *
     * removing the node from the frequency = 1 and adding it to next one 2's list if present however
     *
     * if the currentList.size == 0 here because after 'node1' removal we get 1 -> null
     *
     * we increment the minFrequency by 1
     *
     * minFrequency = 2
     *
     *
     */

    if(currFreq == minFrequency && currList.size == 0) minFrequency++;

    currentNode.freq++;

    LRUCache nextList = frequencyMap.getOrDefault(currentNode.freq, new LRUCache());

    nextList.insert(currentNode);

    frequencyMap.put(currentNode.freq, nextList);

  }

  private class LRUCache{
    int size = 0;
    Node head;
    Node tail;
    public LRUCache(){

      this.head = new Node(0, 0);
      this.tail = new Node(0, 0);

      head.next = tail ;
      tail.prev = head;

    }

    private void insert(Node currentNode){

      currentNode.next = head.next;
      currentNode.prev = head;
      head.next = currentNode;
      currentNode.next.prev = currentNode;

      size++;
    }

    private void delete(Node node){
      node.prev.next = node.next;
      node.next.prev = node.prev;
      size--;
    }

  }



  private class Node{
    int key;
    int value;
    int freq;
    Node prev;
    Node next;

    private Node(int key, int value){
      this.key = key;
      this.value = value;
      this.freq = 1 ;
    }
  }
}
