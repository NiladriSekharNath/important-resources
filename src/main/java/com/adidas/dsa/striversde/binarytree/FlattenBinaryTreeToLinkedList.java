package com.adidas.dsa.striversde.binarytree;

public class FlattenBinaryTreeToLinkedList {
  public void flatten(TreeNode root) {
    helper(root);
  }

  private TreeNode helper(TreeNode root){
    if(root == null) return root;
    if(root.left == null && root.right == null) return root;
    TreeNode left = helper(root.left);
    TreeNode right = helper(root.right);

    return merge(root, left, right);

  }

  private TreeNode merge(TreeNode root, TreeNode left, TreeNode right){
    root.left = null;

    if(left == null) return root;

    root.right = left;


    TreeNode currentPointer = root ;

    while(currentPointer.right != null){
      currentPointer = currentPointer.right;
    }

    currentPointer.right = right;
    return root;
  }

  public static void main(String[] args){

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);

    node1.left = node2;
    node1.right = node5;

    node2.left = node3;
    node2.right = node4;

    node5.right = node6;


    new FlattenBinaryTreeToLinkedList().flatten(node1);
  }
}
