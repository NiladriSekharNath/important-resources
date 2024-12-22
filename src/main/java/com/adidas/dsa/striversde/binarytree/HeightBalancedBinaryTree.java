package com.adidas.dsa.striversde.binarytree;

/**
 * Check if a Binary Tree is Height Balanced or Not
 * <p>
 * A height-balanced binary tree is a binary tree in which the depth of the two subtrees of every node
 * never differs by more than one.
 */
public class HeightBalancedBinaryTree {
  /**
   * My solution, this idea is correct but we are trying for everynode even if we get a false
   * which is not optimal
   *
   * We take one flag value memory location array to store the value and we keep
   * performing the & operation true & false = false
   *
   */
  public boolean isBalanced(TreeNode root) {
    boolean[] isHB = new boolean[1];
    isHB[0] = true;
    if(root == null) return true;
    helper(root, isHB);
    return isHB[0];

  }

  private int helper(TreeNode root, boolean[] isHB){
    if(root == null) return 0;
    int left = helper(root.left, isHB);
    int right = helper(root.right, isHB);
    isHB[0] = (isHB[0] & Math.abs(left - right) <= 1);
    return 1 + Math.max(left, right);
  }

  /**
   *
   * This is more optimal as when we find the false value we straight away return false and we don't need to check other
   * nodes we don't need a flag,
   *
   * we are simply checking if we get a height > 1 we are returning -1, if we find a -1 in left,
   * we are not trying for right
   * and we move on
   */
  public boolean isBalancedStrivers(TreeNode root) {

    if(root == null) return true;
    int result = helperStrivers(root);
    /**
     * or this could have been returned...
     *
     * return result != -1;
     */
    return result == -1 ? false: true;

  }

  private int helperStrivers(TreeNode root){

    if(root == null) return 0;

    int left = helperStrivers(root.left);
    if(left == -1 ) return -1;

    int right = helperStrivers(root.right);
    if(right == -1) return -1;

    if(Math.abs(left - right) > 1)
      return -1;

    return 1 + Math.max(left, right);
  }
}
