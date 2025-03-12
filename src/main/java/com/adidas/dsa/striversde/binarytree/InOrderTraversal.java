package com.adidas.dsa.striversde.binarytree;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * InOrder Traversal is Left Middle Right Recursive Solution
 */
@Slf4j
public class InOrderTraversal {
  public List<Integer> inorderTraversal(TreeNode root) {
    return helper(root);
  }

  private List<Integer> helper(TreeNode root){
    List<Integer> inOrderTraversal = new ArrayList<>();
    if(root == null) return inOrderTraversal;
    /**
     * here we may have a doubt that what happens in the empty array case like when in the case where root = 1 and no left and right child
     * and when we call root.left and we return an empty array, there is a helper method in this to prove that in addAll we don't need to explicitly
     * handle the empty array/collection when added to a different collection
     */
    inOrderTraversal.addAll(helper(root.left));
    inOrderTraversal.add(root.val);
    inOrderTraversal.addAll(helper(root.right));
    return inOrderTraversal;
  }

  public List<Integer> inorderTraversalIterative(TreeNode root) {
    List<Integer> inorderTraversal = new ArrayList<>();
    if(root == null) return inorderTraversal;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode tempNode = root;
    while(tempNode != null || !stack.isEmpty()){
      if(tempNode != null){
        stack.push(tempNode);
        tempNode = tempNode.left;
      }
      else {
        tempNode = stack.pop();
        inorderTraversal.add(tempNode.val);
        tempNode = tempNode.right;
      }
    }
    return inorderTraversal;

  }

  public void testArrayList(List<Integer> elements){
    List<Integer> arrayList = new ArrayList<>();
    arrayList.addAll(elements);
    log.info("Elements in arrayList: {}", arrayList);

  }

  /**
   * Watch Striver's video
   *
   *
   *
   *
   */
  private List<Integer> inOrderTraversalMorris(TreeNode root){
    List<Integer> inorder = new ArrayList<>();
    if(root == null) return inorder;
    TreeNode curr = null;
    while(curr != null){
      if(curr.left == null){
        inorder.add(curr.val);
        curr = curr.right;
      }
      else{
        TreeNode pre = curr.left;
        while(pre.right != null && pre.right != curr){
          pre = pre.right;
        }
        if(pre.right == null){
          pre.right = curr;
          curr = curr.left;
        }
        else{
          pre.right = null;
          inorder.add(curr.val);
          curr = curr.right;
        }
      }
    }

    return inorder;
  }

  public static void main(String[] args){

    //new InOrderTraversal().testArrayList(new ArrayList<>());

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node5.right = node6;

    new InOrderTraversal().inOrderTraversalMorris(node1);
  }
}
