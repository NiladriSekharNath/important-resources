package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * We are finding the left view of the binary Tree for this we are going to level by level and each level we are adding the first element in
 * the binary tree on the left side
 *
 * For this we are checking at a currentLevel if the current Level equal to size of the ds at that point we are adding the element
 * and doing a standard pre-order Traversal, [root, left, right]
 *
 *        1          level = 0, ds[] so we add 1
 *     2    3        level = 1, ds[1] so we add 2, here when we come from the left here to 3 on the right side we don't add coz our ds: [1, 2, 4, 6]
 *   4
 * 6
 *
 *
 */

public class LeftView {
  public List<Integer> leftView(TreeNode root) {
    List<Integer> leftView = new ArrayList<>();
    helper(root, 0, leftView);
    return leftView;
  }
  private void helper(TreeNode root, int currentLevel, List<Integer> leftView){
    if(root == null) return;
    if(currentLevel == leftView.size())
      leftView.add(root.val);
    helper(root.left, currentLevel + 1, leftView);
    helper(root.right, currentLevel + 1, leftView);

  }
  public static void main(String[] args){
    new LeftView().leftView(new TreeNode());
  }
}
