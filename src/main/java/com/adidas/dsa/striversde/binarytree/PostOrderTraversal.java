package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Post Order Solution is Left Right Middle
 *
 *
 * Input: root = [1,2,3,4,5,null,8,null,null,6,7,9]
 *
 * Output: [4,6,7,5,2,9,8,3,1]
 *
 * https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 *
 * Explanation:
 */
public class PostOrderTraversal {
  public List<Integer> postorderTraversal(TreeNode root) {
    return helper(root);
  }

  private List<Integer> helper(TreeNode root) {
    List<Integer> postOrderTraversal = new ArrayList<>();
    if (root == null) return postOrderTraversal;


    postOrderTraversal.addAll(helper(root.left));
    postOrderTraversal.addAll(helper(root.right));
    postOrderTraversal.add(root.val);
    return postOrderTraversal;
  }

  public List<Integer> postorderTraversalIterative(TreeNode root) {
    List<Integer> postOrderTraversal = new ArrayList<>();
    if (root == null) return postOrderTraversal;
    Stack<TreeNode> stack1 = new Stack<>();
    Stack<TreeNode> stack2 = new Stack<>();
    stack1.push(root);
    while (!stack1.isEmpty()) {
      TreeNode currentNode = stack1.pop();
      stack2.push(currentNode);

      if (currentNode.left != null) stack1.push(currentNode.left);
      if (currentNode.right != null) stack1.push(currentNode.right);
    }
    while (!stack2.isEmpty()) postOrderTraversal.add(stack2.pop().val);
    return postOrderTraversal;
  }

  public List<Integer> postorderTraversalIterativeOneStack(TreeNode root) {
    List<Integer> postOrderTraversal = new ArrayList<>();
    if(root == null) return postOrderTraversal;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode curr = root;
    while(curr != null || !stack.isEmpty()){
      if(curr != null){
        stack.push(curr);
        curr = curr.left;
      }else{
        TreeNode rightPointer = stack.peek().right;
        if(rightPointer == null){
          rightPointer = stack.pop();
          postOrderTraversal.add(rightPointer.val);
          /**
           * this part is done consider the example bigger example when we have 7 -> 5 -> 2 in the stack already which
           * means that part is already covered in the stack or already visited in the traversal once, if we
           * don't pop out first we would already pushing it inside again, which is not correct
           */
          while(!stack.isEmpty() && stack.peek().right == rightPointer){
            rightPointer = stack.pop();
            postOrderTraversal.add(rightPointer.val);

          }
        }else{
          curr = rightPointer;
        }
      }
    }
    return postOrderTraversal;
  }

  /**
   * same as preorder please just change left to right and right to left and
   * reverse the collection
   */
  public List<Integer> postorderTraversalMorris(TreeNode root) {

    List<Integer> postorder = new ArrayList<>();
    if(root == null) return postorder;
    TreeNode curr = root;

    while(curr != null){
      if(curr.right == null){
        postorder.add(curr.val);
        curr = curr.left;
      }
      else{
        TreeNode pre = curr.right;
        while(pre.left != null && pre.left != curr){
          pre = pre.left;
        }
        if(pre.left == null){
          pre.left = curr;
          postorder.add(curr.val);
          curr = curr.right;
        }
        else{
          pre.left = null;

          curr = curr.left;
        }
      }
    }
    Collections.reverse(postorder);

    return postorder;

  }
}
