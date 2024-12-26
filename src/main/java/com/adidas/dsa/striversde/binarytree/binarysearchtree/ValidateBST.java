package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

/**
 * Validate a BST, for every node we are giving a range
 *
 *        5
 *       / \
 *      1   9
 *         / \
 *        4   10
 *
 * answer : false
 *
 * for every node we are giving a range
 *
 * this is not BST because in the right subtree starting with node9
 *
 * every node to the left of 9, should lie in the range > 5 and < 9
 *
 * Note : equals to 5 not considered
 *
 * and for the first node(initially at node 5) we are setting a minValue and maxValue as -infinity and +infinity
 * respectively
 *
 *
 */

public class ValidateBST {
  public boolean isValidBST(TreeNode root) {
    return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
  }
  private boolean helper(TreeNode root, long minValue, long maxValue){
    if(root == null) return true;

    return root.val > minValue && root.val < maxValue &&
        helper(root.left, minValue, root.val) && helper(root.right, root.val, maxValue);
  }
}
