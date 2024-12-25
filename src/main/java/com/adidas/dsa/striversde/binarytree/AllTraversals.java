package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Problem statement
 * You have been given a Binary Tree of 'N'
 *
 * nodes, where the nodes have integer values.
 *
 *
 *
 * Your task is to return the ln-Order, Pre-Order, and Post-Order traversals of the given binary tree.
 */
public class AllTraversals {
  public static List<List<Integer>> getTreeTraversal(TreeNode root) {
    List<List<Integer>> allTraversals = new ArrayList<>();
    for(int i = 0 ; i< 3; i++)
      allTraversals.add(new ArrayList<>());
    helper(root, allTraversals);
    return allTraversals;
  }

  private static void helper(TreeNode root, List<List<Integer>> allTraversals){
    if(root == null) return;
    allTraversals.get(1).add(root.val);
    helper(root.left, allTraversals);
    allTraversals.get(0).add(root.val);
    helper(root.right, allTraversals);
    allTraversals.get(2).add(root.val);
  }

  /**
   *
   *
   */
  public static List<List<Integer>> getTreeTraversalUsingSingleStack(TreeNode root) {
    List<List<Integer>> allTraversals = new ArrayList<>();
    if(root == null) return allTraversals;
    for(int i = 0 ; i< 3; i++)
      allTraversals.add(new ArrayList<>());

    Stack<Pair<TreeNode, Integer>> stack = new Stack();

    stack.push(new Pair<>(root, 1));

    while(!stack.isEmpty()){
      Pair<TreeNode, Integer> currentNodePair = stack.pop();
      TreeNode currentNode = currentNodePair.node;

      if( currentNodePair.value == 1){

        allTraversals.get(1).add(currentNode.val);
        currentNodePair.value++;
        stack.push(currentNodePair);
        if(currentNode.left != null){
          stack.push(new Pair<>(currentNode.left, 1));
        }
      }
      else if(currentNodePair.value == 2){
        allTraversals.get(0).add(currentNode.val);
        currentNodePair.value++;
        stack.push(currentNodePair);
        if(currentNode.right != null){
          stack.push(new Pair<>(currentNode.right, 1));
        }
      }
      else{
        allTraversals.get(2).add(currentNode.val);
      }

    }
    return allTraversals;
  }

  private static class Pair<K, V>{
    K node ;
    V value;
    public Pair(K node, V value){
      this.node = node;
      this.value = value;
    }
  }

  public static void main(String[] args){
    new AllTraversals().getTreeTraversalUsingSingleStack(null);
  }
}
