package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

/**
 * Same logic as the KthSmallestElementInABST but only catch is
 *
 * There are two ways to do this we traverse every element to find the count of the elements
 * let's say there are n elements and we require the kth element so all we need is the
 * n-k th element
 *
 * But there is another better way the better way for this is for getting the kth largest element
 * we can do something instead of the regular Left, Root, Right we will do a reverse inorder
 *
 * Right, Root, Left, it's same like we keep adding elements in the reverse order
 *
 * instead of adding elements we are simply keeping a count so once the count == k we return the
 * root.val for the particular root where count = k
 */
public class KthLargestElementInABST {
  // return the Kth largest element in the given BST rooted at 'root'
  public int kthLargest(TreeNode root, int k) {
    int[] inorderCounter = new int[]{0};
    return helper(root, k, inorderCounter);
  }

  private int helper(TreeNode root, int k, int[] inorderCounter){
    if(root == null) return -1 ;
    int left = helper(root.right, k, inorderCounter);
    inorderCounter[0]++;
    if(inorderCounter[0] == k)
      return root.val;
    int right = helper(root.left, k, inorderCounter);

    return left != -1 ? left:right;
  }
}
