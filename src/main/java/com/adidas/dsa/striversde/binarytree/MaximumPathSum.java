package com.adidas.dsa.striversde.binarytree;

/**
 * Same logic as the max height and max diameter of the tree problem
 *
 * only catch of this problem is
 *
 * for this tree
 *
 *                            20
 *                          /    \
 *                       15      50
 *                             /   \
 *                           -23  -15
 *
 *  For this binary tree we are finding the max path for this binary tree
 *  as the path [15, 20, 50]
 *
 *  for the node 50 we get left path = -23 and the right path = -15, so we are not considering those paths in our answer
 *  instead we are just considering the node 50
 *
 *  Just a note here path is not root to leaf path
 *
 *  so half path 15 -> 20 -> 50 is a path in the binary tree
 *
 */
public class MaximumPathSum {


  public int maxPathSum(TreeNode root) {

    int[] maxPathSum = new int[1];
    maxPathSum[0] = Integer.MIN_VALUE;
    if(root == null) return 0 ;
    helper(root, maxPathSum);

    return maxPathSum[0];

  }

  private int helper(TreeNode root, int[] maxPathSum){

    if(root == null) return 0;

    int leftPathSum = Math.max(0, helper(root.left, maxPathSum));
    int rightPathSum = Math.max(0, helper(root.right, maxPathSum));

    maxPathSum[0] = Math.max(maxPathSum[0], leftPathSum + rightPathSum + root.val);

    return root.val + Math.max(leftPathSum, rightPathSum);

  }

  public static void main(String[] args){

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);

    node1.left = node2;
    node1.right = node3;

    System.out.println(new MaximumPathSum().maxPathSum(node1));
  }
}
