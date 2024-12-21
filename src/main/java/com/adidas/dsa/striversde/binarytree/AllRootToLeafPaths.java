package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Find all the root to leaf paths in a binary tree
 *
 * Input: root[] = [10, 20, 30, 40, 60]
 *          10
 *        /    \
 *       20    30
 *      /  \
 *     40   60
 * Output: [[10, 20, 40], [10, 20, 60], [10, 30]]
 *
 * Explanation: All possible paths: 10->20 ->40, 10->20->60 and 10->30
 *
 * Solution: Standard Recursion and Backtracking
 */
public class AllRootToLeafPaths {
  public static ArrayList<ArrayList<Integer>> Paths(TreeNode root) {
    ArrayList<ArrayList<Integer>> resultSet = new ArrayList<>();
    helper(root, new ArrayList<>(), resultSet);
    return resultSet;
  }

  private static void helper(TreeNode root, List<Integer> currentPath, ArrayList<ArrayList<Integer>> resultSet){
    if(root.left == null && root.right == null){
      currentPath.add(root.val);
      resultSet.add(new ArrayList<>(currentPath));
      currentPath.remove(currentPath.size() - 1);
      return;
    }

    currentPath.add(root.val);
    if(root.left != null)
      helper(root.left, currentPath, resultSet);
    if(root.right != null)
      helper(root.right, currentPath, resultSet);
    currentPath.remove(currentPath.size() - 1);
  }

  private static void helperDifferentApproach(TreeNode root, List<Integer> currentPath, ArrayList<ArrayList<Integer>> resultSet){

    if(root == null) return;

    currentPath.add(root.val);

    if(root.left == null && root.right == null){
      resultSet.add(new ArrayList<>(currentPath));
    }

    if(root.left != null)
      helper(root.left, currentPath, resultSet);
    if(root.right != null)
      helper(root.right, currentPath, resultSet);

    currentPath.remove(currentPath.size() - 1);
  }

}
