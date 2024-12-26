package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;
import java.util.Stack;

/**
 * Two ways to solve this question if we are given to design this iterator one way would be to do a inorder traversal and store the
 * items in a List and then take a current Pointer, initially pointing to zero and keep incrementing the current pointer
 * till the last element of the list is reached
 *
 * If the counter >= size of the list then we get the inorder hasNext() as false
 *
 * TC : O(1) for each nextCall
 * SC : O(N) for the inorder traversal storage
 *
 * we can further optimize the SC of the solution for this one
 *
 * Inorder to do that we are borrowing the concept of the inorder traversal, inorder -> left, root, right
 *
 * Once we get a root in the constructor we take a stack and go left till null and push all the elements along the way
 *
 * Let's say we get this
 *
 *                        7
 *                       / \
 *                      3  15
 *                        /  \
 *                       4   20
 *
 * when we get node7 we push all elements to the left starting with 7, onto the stack
 *
 * [3
 *  7
 *  ]
 *
 *  Now when the getNext() is called we pop 3, check for that element if right exist, if it does not we don't do anything
 *  if it does then what we do is keep point the currentpointer to the right element and keep pushing elements
 *  to the left side again on the stack
 *
 *  Like for the case of node7, we push node15 and then node4, which means the node4 is on top of the stack as LIFO
 *
 *  so the
 *  TC : O(1) approximately because not all calls have right calls as for n next() calls if we keep adding, we get an
 *  average of O(n/n) -> O(1)
 *  SC : O(h) where h is the height of the tree
 *
 */
public class BSTIterator {

  Stack<TreeNode> stack = new Stack<>();

  public BSTIterator(TreeNode root) {
    pushElementsToStack(root);
  }

  public int next() {
    int value = -1;
    if (hasNext()) {
      TreeNode currentNode = stack.pop();
      value = currentNode.val;
      if (currentNode.right != null)
        pushElementsToStack(currentNode.right);
    }

    return value;

  }

  public boolean hasNext() {
    return !stack.isEmpty();
  }

  private void pushElementsToStack(TreeNode root) {
    TreeNode currentNode = root;
    while (currentNode != null) {
      stack.push(currentNode);
      currentNode = currentNode.left;
    }
  }

}
