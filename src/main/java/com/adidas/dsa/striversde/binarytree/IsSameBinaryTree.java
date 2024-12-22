package com.adidas.dsa.striversde.binarytree;

public class IsSameBinaryTree {
  /**
   *
   * Here if both p and q == null we return true
   *
   * if we find same p != null and q == null we return false
   * vice versa p == null and q != null we return false;
   *
   * and then if both not null we check if the value is same and try the same
   * for the left and the right part
   */
  public boolean isSameTree(TreeNode p, TreeNode q) {
    if(p == null && q == null) return true;
    if(p == null || q == null) return false;
    return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
  }
}
