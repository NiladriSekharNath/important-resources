package com.adidas.dsa.striversde.binarytree;

/**
 * Given a tree check if it's mirror of itself that is symmetric around it's center
 * <p>
 * 1
 * /  \
 * 2    2
 * /  \  / \
 * 3   4 4   3
 * <p>
 * Tree is symmetric around it's center
 * <p>
 * If we place a mirror across node1, 1st level node2 on left and node2 on right is same
 * <p>
 * next level for the nodes left node3 is equal to the right node3
 * and right node4 == node4 on the left
 * <p>
 * So our logic goes very simply as ignoring the root
 * <p>
 * any leftsubtree.left == rightsubtree.right and
 * leftSubtree.right == rightsubtree.left
 * <p>
 * and follow along the same code as the isSameTree
 */
public class SymmetricTree {
  public boolean isSymmetric(TreeNode root) {
    if (root == null) return true;
    return isSameTree(root.left, root.right);
  }

  private boolean isSameTree(TreeNode leftT, TreeNode rightT) {
    if (leftT == null && rightT == null) return true;
    if (leftT == null || rightT == null) return false;
    return (leftT.val == rightT.val) && isSameTree(leftT.left, rightT.right)
        && isSameTree(leftT.right, rightT.left);
  }
}
