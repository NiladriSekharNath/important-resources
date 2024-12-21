package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * PreOrderTraversal is Middle Left Right
 */
public class PreOrderTraversal {

  public List<Integer> preorderTraversal(TreeNode root) {
    return helper(root);
  }

  private List<Integer> helper(TreeNode root) {
    List<Integer> preOrderTraversal = new ArrayList<>();
    if (root == null) return preOrderTraversal;

    preOrderTraversal.add(root.val);
    preOrderTraversal.addAll(helper(root.left));
    preOrderTraversal.addAll(helper(root.right));
    return preOrderTraversal;
  }

  public List<Integer>  preorderTraversalIterative(TreeNode root){
    List<Integer> preOrderTraversal = new ArrayList<>();
    if(root == null) return preOrderTraversal;
    Stack<TreeNode> stack = new Stack<>();
    stack.push(root);
    while(!stack.isEmpty()){
      TreeNode currentNode = stack.pop();
      preOrderTraversal.add(currentNode.val);
      if(currentNode.right != null) stack.push(currentNode.right);
      if(currentNode.left != null) stack.push(currentNode.left);
    }
    return preOrderTraversal;
  }

  public List<Integer>  preorderTraversalMorris(TreeNode root){
    List<Integer> preorder = new ArrayList<>();
    if(root == null) return preorder;
    TreeNode curr = root;

    while(curr != null){
      if(curr.left == null){
        preorder.add(curr.val);
        curr = curr.right;
      }
      else{
        TreeNode pre = curr.left;
        while(pre.right != null && pre.right != curr){
          pre = pre.right;
        }
        if(pre.right == null){
          pre.right = curr;
          preorder.add(curr.val);
          curr = curr.left;
        }
        else{
          pre.right = null;

          curr = curr.right;
        }
      }
    }

    return preorder;
  }
}

