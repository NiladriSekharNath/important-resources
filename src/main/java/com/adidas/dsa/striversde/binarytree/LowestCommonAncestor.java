package com.adidas.dsa.striversde.binarytree;

/**
 * Solution to find the lowestCommon Ancestor to find for given nodes p and q
 *
 *                          20
 *  *                     /    \
 *  *                   8       22
 *  *                 /   \     /  \
 *  *               5      3 4     25
 *  *                      /    \
 *  *                  10       14
 *
 *  let's say if we are tasked to find the lowest common ancestor of the nodes
 *
 *  LCA(5,3) = 8
 *
 *  LCA(8,10) = 8
 *
 *  LCA(8, 22) = 20
 *
 *  we are doing going left, right in every node once we find p or q we simply return.
 *  Let's say we are searching for LCA(10, 14) = 4 what we are doing
 *
 *  if we find from a certain node left!= null and the right == null we return left, and if we find left == null and right != null
 *  we return right
 *
 *  if we get both left != null and right != null we return the root
 *
 *  let's say at node4 we get left = 10 and right = 14 we return node 4
 *
 *  again at node 22 we get left = 4 and right = null, so we return from node 22, left (= node4)
 *
 *  Point:
 *  One point to note for the LCA(8, 10) if we find a node
 *  8 if we think we don't need to search for 10 it is correct we don't need to search for 10 as LCA of a node can be that
 *  node itself
 *
 *  LCA(8, 10) would be 8, if they are on the same path like 8 is higher and 10 is on the same lower path, either left
 *  or right of 8
 */
public class LowestCommonAncestor {


  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if(root == null) return null;
    return helper(root, p, q);
  }
  private TreeNode helper(TreeNode root, TreeNode p, TreeNode q){

    if(root == null) return null;

    if(root == p || root == q) return root;

    TreeNode left = helper(root.left, p, q);
    TreeNode right = helper(root.right, p, q);

    if(left != null && right != null)
      return root;

    return left != null ? left : right;

  }

  public static void main(String[] args){

    TreeNode node3 = new TreeNode(3);
    TreeNode node5 = new TreeNode(5);
    TreeNode node1 = new TreeNode(1);
    TreeNode node6 = new TreeNode(6);
    TreeNode node2 = new TreeNode(2);
    TreeNode node0 = new TreeNode(0);
    TreeNode node8 = new TreeNode(8);
    TreeNode node7 = new TreeNode(7);
    TreeNode node4 = new TreeNode(4);

    node3.left = node5;
    node3.right = node1;

    node5.left = node6;
    node5.right = node2;

    node1.left = node0;
    node1.right = node8;

    node2.left = node7;
    node2.right = node4;

    new LowestCommonAncestor().lowestCommonAncestor(node3, node5, node4);

  }
}
