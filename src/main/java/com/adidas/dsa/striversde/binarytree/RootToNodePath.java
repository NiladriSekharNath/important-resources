package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * Using standard recursion and backtracking to find the path once we find the given path
 * we just return true and don't perform any other function calls
 */
public class RootToNodePath {
  public List<Integer> rootToNodePath(TreeNode root, int valueToFind){
    List<Integer> rootToNodePath = new ArrayList<>();
    if(root == null) return rootToNodePath;
    helper(root, valueToFind, rootToNodePath);
    return rootToNodePath;
  }

  private boolean helper(TreeNode root, int valueToFind, List<Integer> path) {
    if (root == null) return false;

    path.add(root.val);

    if (root.val == valueToFind)
      return true;

    if (helper(root.left, valueToFind, path) || helper(root.right, valueToFind, path))
      return true;

    path.remove(path.size() - 1);

    return false;
  }
}
