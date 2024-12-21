package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Given a binary tree, return an array where elements represent the bottom view of the binary tree from left to right.
 *
 * Note: If there are multiple bottom-most nodes for a horizontal distance from the root, then the latter one in the level traversal is considered. For example, in the below diagram, 3 and 4 are both the bottommost nodes at a horizontal distance of 0, here 4 will be considered.
 *
 *                       20
 *                     /    \
 *                   8       22
 *                 /   \     /  \
 *               5      3 4     25
 *                      /    \
 *                  10       14
 *
 * For the above tree, the output should be 5 10 4 14 25.
 *
 *
 * Solution approach for this one we are let's say we are doing the vertical order traversal wherein the nodes have values like
 *
 * node 20 has value -> 0 and node 8 has value -> -1 and node 22 has value -> +1 so, we go level by level keep assigning values
 * (previous value -1) for left child and (previous value + 1) for the right child
 *
 * now the bottom view of the Binary Tree is the "last value on each level", and we store it in a map in sorted order (TreeMap)
 *
 *
 * [{-2 : 5}, {-1, 10}(we get 8, 10, we take 10), {0, 4}(we get 20, 3, 4 last value), {+1, 14}(we get 22,14), {+2, 25}]
 *
 */
public class BottomView {
  public List<Integer> bottomView(TreeNode root){
    List<Integer> bottomView = new ArrayList<>();
    if(root == null) return bottomView;
    Map<Integer, Integer> hdVsValueMap = new TreeMap<>();
    Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
    queue.add(new Pair<>(root, 0));
    while(!queue.isEmpty()){
      /**
       * Here the size loop is not done as the size partition is not required because
       * if we need a level by level analysis then it would have been necessary
       */
      Pair<TreeNode, Integer> currentPair = queue.poll();
      TreeNode currentNode = currentPair.node;
      Integer hd = currentPair.value;
      hdVsValueMap.put(hd, currentNode.val);
      if(currentNode.left != null)
        queue.add(new Pair<>(currentNode.left, hd - 1));
      if(currentNode.right != null)
        queue.add(new Pair<>(currentNode.right, hd + 1));
    }

    for(var eachEntry : hdVsValueMap.entrySet()){
      bottomView.add(eachEntry.getValue());
    }
    return bottomView;
  }


  private class Pair<K,V>{
    K node;
    V value;
    public Pair(K node, V value){
      this.node = node;
      this.value = value;
    }
  }

  public static void main(String[] args){

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node5.right = node6;

    new BottomView().bottomView(node1);
  }
}
