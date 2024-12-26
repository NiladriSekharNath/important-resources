package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

/**
 *                             6
 *  *  *                     /    \
 *  *  *                   2       8
 *  *  *                 /   \    /  \
 *  *  *               0      4  7    9
 *  *  *                    /  \
 *  *  *                   3   5
 *
 *  Lowest common ancestor of the BST
 *
 *  LCA (3,5) = 4
 *
 *  Now the approach is simple if as either p, q (3,5) has to both lie on the left or both on the right if there is part
 *  let's for LCA (2,8) since here only at node6 there is part that is the LCA
 *
 *  so if both p,q value's less than root then we go left
 *  and if both p,q value's greater than root then we go right
 *  *
 */
public class LowestCommonAncestorBST {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q){
    if(root == null || root == p || root == q) return root ;

    TreeNode left = null, right = null;

    if(p.val < root.val && q.val < root.val)
      left = lowestCommonAncestor(root.left, p, q);
    else if(p.val > root.val && q.val > root.val)
      right = lowestCommonAncestor(root.right, p, q);
    else
      return root;

    return left != null ? left: right;
  }

  public TreeNode lowestCommonAncestorStrivers(TreeNode root, TreeNode p, TreeNode q){
    if(root == null) return root ;
    if(p.val < root.val && q.val < root.val)
      return lowestCommonAncestorStrivers(root.left, p, q);
    else if(p.val> root.val && q.val > root.val)
      return lowestCommonAncestorStrivers(root.right, p, q);
    return root;
  }
}
