package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

/**
 * Given a binary SearchTree find the kthSmallestElement in a BST
 *
 * In-order to find the kthSmallestElement in a binarySearchTree we can use the property
 *
 * that the BinarySearchTree's inorder traversal as always sorted
 *
 * now if we have 1,2,3,4,5,6,7,8 as the inorder-traversal
 *
 * k = 4, (4th Smallest Element in BST) is 4
 *
 * Now this helps us with the approach
 *
 * Storing the inorder traversal of the BST in a list and returning the kth element
 *
 * SC: O(N) since we are storing the elements in a list
 *
 * So working on this idea we can do this :
 *
 * instead of storing if we keep a counter variable and keep incrementing in the inorder traversal
 * the root when the counter variable's value is equal to the kth value we return from there
 * and we don't need to traverse the entire BST
 *
 * so here just for the cases we if we find the binary search tree is like this
 *
 *                          5
 *                        /  \
 *                       3   7
 *                     / \  / \
 *                    1  4 6  8
 *                    \
 *                     2
 *
 * let's say we get 4 as the answer, so only thing we do here return 4 to node3 but when
 * we return it will go to the previous function call at node3, so we do this
 *
 * make a root either return -1 or the answer which is 4 in our case
 *
 * so node3 return -1 and then node4 returns answer, the answer could be found at either left or right
 * so if answer != -1 ? we return answer (left or right)
 *
 *
 */
public class KthSmallestElementInABST {
  public int kthSmallest(TreeNode root, int k) {
    int[] inorderCounter = new int[]{0};
    return helper(root, k, inorderCounter);
  }

  private int helper(TreeNode root, int k, int[] inorderCounter){
    if(root == null) return -1 ;
    int left = helper(root.left, k, inorderCounter);
    inorderCounter[0]++;
    if(inorderCounter[0] == k)
      return root.val;
    int right = helper(root.right, k, inorderCounter);

    return left != -1 ? left:right;
  }
}
