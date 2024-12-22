package com.adidas.dsa.striversde.binarytree;

public class MaximumDepthOfBinaryTree {
  /**
   * My solution
   * */
  public int maxDepth(TreeNode root) {
    if(root == null) return 0;
    return helper(root, 0);
  }
  private int helper(TreeNode root, int currentDepth){
    if(root == null) return currentDepth;
    int left = helper(root.left, currentDepth + 1);
    int right = helper(root.right, currentDepth + 1);
    return Math.max(left, right);
  }

  /**
   * Striver's Solution:
   *
   *  public int maxDepth(TreeNode root) {
   *
   *         if(root == null) return 0;
   *
   *         int left = maxDepth(root.left);
   *         int right = maxDepth(root.right);
   *
   *         return 1 + Math.max(left, right);
   *     }
   */
}
