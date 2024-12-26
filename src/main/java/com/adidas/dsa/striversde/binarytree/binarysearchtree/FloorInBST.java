package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

/**
 * Solution : find floor in BST:
 *
 * floor of a number x
 *
 * is the highest possible such <= x
 *
 * 1,2,3,4
 * floor 5 = 4
 *
 * floor 4 = 4
 */
public class FloorInBST {
  public int floorInBST(TreeNode root, int x) {
    {
      int floor = -1;
      TreeNode current = root;
      while (current != null) {
        if (current.val == x)
          return current.val;
        else if (x > current.val) {
          floor = current.val;
          current = current.right;
        } else {
          current = current.left;
        }
      }

      return floor;
    }
  }

  public static void main(String[] args){

    TreeNode node10 = new TreeNode(10);
    TreeNode node5 = new TreeNode(5);
    TreeNode node15 = new TreeNode(15);
    TreeNode node2 = new TreeNode(2);
    TreeNode node6 = new TreeNode(6);

    node10.left = node5;
    node10.right = node15;
    node5.left = node2;
    node5.right = node6;

    new FloorInBST().floorInBST(node10, 4);
  }
}
