package com.adidas.dsa.striversde.binarytree;

import java.util.Stack;

/***
 * Given a Binary Tree
 *                    10
 *                   / \
 *                 12   15
 *                / \   /
 *               25 30 36
 *
 *  We need to convert the binary Tree to doubly-linked list
 *
 *  this way it should look like this
 *
 *  25 <=> 12 <=> 30 <=> 10 <=> 36 <=> 15
 *
 *
 * in other way the inorder traversal should be this
 *
 * and all nodes in the inorder traversal are connected so
 *
 * like a double linked list has nodes  -> next, previous
 *
 * and binary tree has nodes -> left, right
 *
 * previous = left
 * right = next
 *
 * so one way to do this is by using the BT Iterator taking a previous updating the current value
 * and updating previous to current
 */
public class BinaryTreeToDoublyLinkedList {
  TreeNode bToDLL(TreeNode root){
    BTIterator bt = new BTIterator(root);
    TreeNode previous = bt.getNext();
    TreeNode newRoot = previous;
    while(bt.hasNext()){
      TreeNode currentNode = bt.getNext();
      previous.right = currentNode;
      currentNode.left = previous;
      previous = currentNode;
    }
    return newRoot;
  }


  private class BTIterator{
    Stack<TreeNode> stack = new Stack<>();
    private BTIterator(TreeNode TreeNode){
      pushLeftElementsToStack(TreeNode);
    }

    private boolean hasNext(){
      return !stack.isEmpty();
    }

    private TreeNode getNext(){
      TreeNode currentTreeNode = stack.pop();
      if(currentTreeNode.right != null){
        pushLeftElementsToStack(currentTreeNode.right);
      }

      return currentTreeNode;
    }

    private void pushLeftElementsToStack(TreeNode root){
      TreeNode currentTreeNode = root;
      while(currentTreeNode != null){
        stack.push(currentTreeNode);
        currentTreeNode = currentTreeNode.left;
      }

    }
  }

  public static void main(String[] args){

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);

    node1.left = node3;
    node1.right = node2;

    new BinaryTreeToDoublyLinkedList().bToDLL(node1);
  }

  /**
   * Different way to do this is by using the standard inorder Traversal recursion
   *
   * we could have done this by using global prev, head variables instead we did this
   * using a preHead array of size 2 where prev = prevHead[0] and head = prevHead[1]
   *
   * so using this idea we do this particular trick
   *
   * go left() helper(root.left)
   *
   * processing steps :
   *
   * if prev == null {
   *   head = root ; we assign the head of the linked list in our case 25
   * }else {
   *   prev.right = root (current root) imagine at node12, prev (currently 25) 25.next = 12 (speaking in Doubly-linked list terms)
   *   root.left = prev (current root) imagine at node12, prev(currently 25) 12.prev = 25 (speaking in Doubly-linked list terms)
   *
   * }
   *
   * prev = root ; updating the current root as prev
   *
   */

  TreeNode bToDLL_Recursive(TreeNode root){
    TreeNode[] prevHead = new TreeNode[]{null, null};
    helper(root, prevHead);
    return prevHead[1];
  }

  private void helper(TreeNode root, TreeNode[] prevHead){
    if(root == null) return;

    helper(root.left, prevHead);

    if(prevHead[0] == null){
      prevHead[1] = root;
    }else{
      prevHead[0].right = root;
      root.left = prevHead[0];
    }

    prevHead[0] = root;

    helper(root.right, prevHead);
  }
}
