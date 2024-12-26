package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

import java.util.Stack;

/**
 * Given the root of a binary search tree and an integer k, return true if there exist two elements in the BST such that
 * their sum is equal to k, or false otherwise.
 *
 * Now first approach used is make a set which has the current node values, during recursion if we already found a value
 * that is containing the remaining value in the set (k - root.node), then we return true otherwise we keep traversing
 * both sides, this way we are able to find the answer, but we are not using the property of BST
 *
 * This way is also applicable for any BinaryTree
 *
 * now our task is to find a pair that is equal to the target sum K
 *
 * we do the BSTIterator way wherein we have two traversals
 *
 * inorder -> left, root, right -> this keeps the values in sorted order for BST in ascending
 * reverse inorder -> right -> root -> left, this keeps the values in sorted order for BST in descending
 *
 * once we have both handy
 *
 * we try a two pointer approach, initialize a low pointer and high pointer
 *
 * if (low + high) == target we return true
 * if(low + high < target), we increment low to get the nextValue
 * if(low + high > target), we decrement high to get the nextValue
 *
 * while(low < high){
 *       if(low + high == k) return true;
 *       else if(low + high < k) low = bstIterator.getNext();
 *       else high = bstIterator.getBefore();
 *     }
 *
 * above steps,
 * this we continue till our Low becomes greater than high and we return false
 *
 *
 *
 *
 */
public class TwoSumIVInputIsABST {

  /**public boolean findTarget(TreeNode root, int k) {
   Set<Integer> values = new HashSet<>();
   return helper(root, k, values);
   }
   private boolean helper(TreeNode root, int k, Set<Integer> values){
   if(root == null) return false;
   int remaining = k - root.val;
   if(values.contains(remaining))
   return true;

   values.add(root.val);

   return  helper(root.left, k, values) || helper(root.right, k, values);
   }**/



  public boolean findTarget(TreeNode root, int k){
    if(root == null) return false;

    BSTIterator bstIterator = new BSTIterator(root);

    int low = bstIterator.getNext();
    int high = bstIterator.getBefore();

    while(low < high){
      if(low + high == k) return true;
      else if(low + high < k) low = bstIterator.getNext();
      else high = bstIterator.getBefore();
    }

    return false;

  }

  private class BSTIterator{

    Stack<TreeNode> inorder = new Stack<>();
    Stack<TreeNode> reverseInorder = new Stack<>();

    private BSTIterator(TreeNode root){
      pushRightElementsToStack(root);
      pushLeftElementsToStack(root);
    }



    private boolean hasNext(){
      return !inorder.isEmpty();
    }
    private int getNext(){
      int nextValue = Integer.MIN_VALUE;
      if(hasNext()){
        TreeNode currentNode = inorder.pop();
        nextValue = currentNode.val;
        if(currentNode.right != null)
          pushLeftElementsToStack(currentNode.right);
      }
      return nextValue;
    }

    private boolean hasBefore(){
      return !reverseInorder.isEmpty();
    }

    private int getBefore(){
      int beforeValue = Integer.MAX_VALUE;
      if(hasBefore()){
        TreeNode currentNode = reverseInorder.pop();
        beforeValue = currentNode.val;
        if(currentNode.left != null)
          pushRightElementsToStack(currentNode.left);
      }

      return beforeValue;
    }

    private void pushLeftElementsToStack(TreeNode root) {
      TreeNode currentNode = root ;
      while(currentNode != null){
        inorder.push(currentNode);
        currentNode = currentNode.left;
      }
    }

    private void pushRightElementsToStack(TreeNode root) {
      TreeNode currentNode = root ;
      while(currentNode != null){
        reverseInorder.push(currentNode);
        currentNode = currentNode.right;
      }
    }

  }
}
