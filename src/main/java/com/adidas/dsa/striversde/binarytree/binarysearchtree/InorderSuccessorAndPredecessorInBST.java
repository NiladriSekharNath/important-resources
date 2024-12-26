package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

/***
 *                                 6
 *  *  *  *                     /    \
 *  *  *  *                   2       8
 *  *  *  *                 /   \    /  \
 *  *  *  *               0      4  7    9
 *  *  *  *                    /  \
 *  *  *  *                   3   5
 *
 *  okay we are tasked to find the inorder successor and predecessor of a binary search tree
 *
 *  so, inorder of the BST is always sorted : 0, 2, 3, 4, 5, 6, 7, 8, 9
 *
 *  we know
 *  inorder-successor [4] = 5
 *  inorder-predecessor[4] = 3
 *
 *  basically think like binary search lower bound and upper bound where we store one valid answer and still go left or
 *  right depending on the condition
 *
 *  Here in "inorder pre" we need a "smaller value" so we go "left" if our root is greater than or equals to
 *  key, in hopes of finding a smaller value,
 *  if we find a smaller value let's say node2 which is a possible answer for node4, we store node2 as this might be a
 *  possible candidate then we keep exploring on the right of node2
 *
 *  at node4 which is equals to key we go left again
 *
 *
 *  For "inorder suc" we need a "higher value" so if we find a higher value let's say root6 for inorder_suc[4], we store
 *  the answer as 6 as this can be possible answer, and then we can't go right as other values are greater than 6
 *  so we move left, if we find a smaller value or equal value we go right, in hopes for a better candidate
 *  like we moved to right at node2 and again we moved to right at node4 to eventually reach our candidate 5
 *  till we get the answer
 */

public class InorderSuccessorAndPredecessorInBST {
  public static void findPreSuc(TreeNode root, TreeNode[] pre, TreeNode[] suc, int key) {
    suc[0] = null;
    pre[0] = null;

    helperPre(root, pre, key);
    helperSuc(root, suc, key);

  }

  private static void helperPre(TreeNode root, TreeNode[] pre, int key){
    if(root == null) return;
    if(root.val >= key)
      helperPre(root.left, pre, key);
    else{
      pre[0] = root;
      helperPre(root.right,pre, key);
    }
  }

  private static void helperSuc(TreeNode root, TreeNode[] suc, int key){
    if(root == null) return;
    if(root.val > key){
      suc[0] = root;
      helperSuc(root.left, suc, key);
    }else{
      helperSuc(root.right, suc, key);
    }
  }
}
