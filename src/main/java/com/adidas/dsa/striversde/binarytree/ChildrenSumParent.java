package com.adidas.dsa.striversde.binarytree;

public class ChildrenSumParent {
  public static int isSumProperty(TreeNode root)
  {
    return helper(root) != -1 ? 1 : 0;

  }

  private static int helper(TreeNode root){

    if(root.left == null && root.right == null)
      return root.val;

    int leftSum = 0, rightSum = 0;
    if(root.left != null)
      leftSum = helper(root.left);

    if(leftSum == -1){
      return -1;
    }

    if(root.right != null)
      rightSum = helper(root.right);

    if(rightSum == -1) return -1;

    if(root.val != leftSum + rightSum)
      return -1;

    return root.val;

  }
}
