package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

/**
 *  Given an BST and an 'x' we are tasked to find ciel,
 *  *
 *  * ciel is the smallest no in BST >= x
 *
 *  2,3,4,5,6,9,10,11,13,14
 *
 *  ceil of 9: 9
 *  ceil of 7: 9
 */
public class CeilInBST {
  public  static int findCeil(TreeNode root, int x) {
    TreeNode current = root;
    int ceil = -1;
    while(current != null){
      if(current.val == x){
        return current.val;
      }
      else if(x > current.val)
        current = current.right;
      else{
        ceil = current.val;
        current = current.left;
      }
    }
    return ceil;
  }
}
