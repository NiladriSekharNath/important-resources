package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Similar to left side view of the Binary Tree
 *
 * but we add the right value and do reverse pre-order traversal [root, right, left]
 */
public class RightView {



  public List<Integer> rightSideView(TreeNode root) {

    // Function to return list containing elements of right view of binary tree.

    List<Integer> rightViewTraversal = new ArrayList<>();
    helper(root, 0, rightViewTraversal);
    return rightViewTraversal;
  }

  private void helper(TreeNode root, int currentLevel, List<Integer> rightViewTraversal) {
    if (root == null) return;
    if (currentLevel == rightViewTraversal.size()) {
      rightViewTraversal.add(root.val);

    }
    helper(root.right, currentLevel + 1, rightViewTraversal);
    helper(root.left, currentLevel + 1, rightViewTraversal);
  }


}
