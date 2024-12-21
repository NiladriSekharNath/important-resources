package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.List;

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
}
