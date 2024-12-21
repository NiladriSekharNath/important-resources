package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * You are given a binary tree, and your task is to return its top view. The top view of a binary tree is the set of nodes visible when the tree is viewed from the top.
 *
 * Note:
 *
 * Return the nodes from the leftmost node to the rightmost node.
 * If two nodes are at the same position (horizontal distance) and are outside the shadow of the tree, consider the leftmost node only.
 *
 * Examples:
 * Input: root[] = [1, 2, 3, N, 4, N, N, N, 5, N, 6]
 *        1
 *      /   \
 *     2     3
 *      \
 *       4
 *        \
 *         5
 *          \
 *           6
 * Output: [2, 1, 3, 6]
 * Explanation: Node 1 is the root and visible.
 * Node 2 is the left child and visible from the left side.
 * Node 3 is the right child and visible from the right side.
 * Nodes 4, 5, and 6 are vertically aligned, but only the lowest node 6 is visible from the top view. Thus, the top view is 2 1 3 6.
 *
 * So similar approach as the bottom View question just that we are taking the first value in each level
 */
public class TopView {
  static List<Integer> topView(TreeNode root) {
    List<Integer> topView = new ArrayList<>();
    if(root == null) return topView;
    Map<Integer, Integer> hdVsValueMap = new TreeMap<>();
    Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
    queue.add(new Pair<>(root, 0));
    while(!queue.isEmpty()){
      Pair<TreeNode, Integer> currentPair = queue.poll();
      TreeNode currentNode = currentPair.node;
      Integer hd = currentPair.value;
      if(!hdVsValueMap.containsKey(hd))
        hdVsValueMap.put(hd, currentNode.val);
      if(currentNode.left != null)
        queue.add(new Pair<>(currentNode.left, hd - 1));
      if(currentNode.right != null)
        queue.add(new Pair<>(currentNode.right, hd + 1));
    }

    for(var eachEntry : hdVsValueMap.entrySet()){
      topView.add(eachEntry.getValue());
    }
    return topView;
  }


  private static class Pair<K,V>{
    K node;
    V value;
    public Pair(K node, V value){
      this.node = node;
      this.value = value;
    }
  }
}
