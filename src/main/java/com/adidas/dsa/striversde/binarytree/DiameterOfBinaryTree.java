package com.adidas.dsa.striversde.binarytree;

public class DiameterOfBinaryTree {

  /**
   *  Okay this solution does not work even though idea is correct reason being in the line "max = Math.max(max, left + right)"
   *
   *  Integer class is immutable so once it is assigned at Integer max = Integer.MIN_VALUE; any new assignments/updations occur
   *
   *  the updated value is stored in a different object location and since we are not returning the location or using the location
   *  we lost the value
   *
   *  instead the idea is, we take array of size 1 or a wrapper class holding the value of integer max
   *
   *
   *
   public int diameterOfBinaryTree(TreeNode root) {
    Integer max = Integer.MIN_VALUE;
    helper(root, max);
    return max;
  }
  private int helper(TreeNode root, Integer max){
    if(root == null) return 0;
    int left = helper(root.left, max);
    int right = helper(root.right, max);
    max = Math.max(max, left + right );
    return 1 + Math.max(left, right);
  }
*/

  public int diameterOfBinaryTree(TreeNode root) {
    int[] maxValue = new int[1];
    helper(root, maxValue);
    return maxValue[0];
  }
  private int helper(TreeNode root, int[] maxValue){
    if(root == null) return 0;
    int left = helper(root.left, maxValue);
    int right = helper(root.right, maxValue);
    maxValue[0] = Math.max(maxValue[0], left + right );
    return 1 + Math.max(left, right);
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

    new DiameterOfBinaryTree().diameterOfBinaryTree(node1);

  }
}
