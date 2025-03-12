package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigZagLevelOrderTraversal {
  /**
   * Same logic as the level order Traversal we need a flag or counter for the odd level we add the current level list in a reverse order
   *
          20
   *     /  \
   *    9    15
   *        /  \
   *       12   27
   *
   * ZigzagLevelOrder Traversal :
   * <p>
   * [[20], [15, 9], [12, 27]]
   */

  public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
    List<List<Integer>> zigzagLevelOrderTraversal = new ArrayList<>();
    if (root == null) return zigzagLevelOrderTraversal;
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    int currentLevel = 0;
    while (!queue.isEmpty()) {
      int size = queue.size();
      List<Integer> currentLevelList = new ArrayList<>();
      for (int i = 0; i < size; i++) {
        TreeNode currentNode = queue.poll();
        currentLevelList.add(currentNode.val);


        if (currentNode.left != null) {
          queue.add(currentNode.left);
        }
        if (currentNode.right != null) {
          queue.add(currentNode.right);
        }


      }

      if (currentLevel % 2 == 1) {
        Collections.reverse(currentLevelList);
      }

      currentLevel++;

      zigzagLevelOrderTraversal.add(currentLevelList);
    }

    return zigzagLevelOrderTraversal;


  }

}
