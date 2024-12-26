package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

/**
 * simple go left for lower value and go right for the higher value
 *
 * TC (O(logn))
 * SC (O(logh)) as h = height of the BST as it is always height balanced
 */
public class SearchInABinarySearchTree {
  public TreeNode searchBST(TreeNode root, int val) {
    if(root == null) return root;
    if(val == root.val) return root;

    TreeNode found = null;
    if(val < root.val)
      found = searchBST(root.left, val);
    else
      found = searchBST(root.right, val);

    return found;
  }

  /**
   * Iterative solution
   *
   * */
  public TreeNode searchBSTIterative(TreeNode root, int val) {
    while(root != null && root.val != val){
      root = (val < root.val) ? root.left : root.right;
    }
    return root;
  }

}
