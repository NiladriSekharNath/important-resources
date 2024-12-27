package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

/**
 * Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 *
 *
 *
 * Example 1:
 *
 *                                     1
 *                                   /  \
 *                                  4    3
 *                                 / \  / \
 *                                2  4 2  5
 *                                       / \
 *                                      4   6
 *
 * Answer : 20
 *
 * Maximum sum in a valid Binary search tree is obtained in root node 3
 *
 *
 * Example2:
 *
 *                                        4
 *                                       /
 *                                      8
 *                                    /  \
 *                                   6    1
 *                                  /    / \
 *                                 9   -5   4
 *                                      \    \
 *                                     -3    10
 *
 *  Answer = 14
 *
 *  Maximum sum in a valid Binary search tree is obtained in root node with root = 4 -> [4, null, 10] following leetcode
 *  *  serializing strategy
 *
 *  Note the root at node 1 also is a binary search Tree [1, -5, 4, null, 3, null, 10]
 *  but here the maxSum = (1 + -5 + -3 + 4 + 10) = 7
 *
 *  since we got already a better answer
 *
 *
 *  Example3 :
 *
 *                               -4
 *                               / \
 *                             -5  -2
 *
 *  Answer = 0
 *  Here the maximum Binary search tree value obtained is at we may think it [-4 + -5 + -2] = -11 but our answer
 *  stored should have been at -2, however according to the leetcode solution our answer for this case if all negative
 *  should be zero
 *
 *
 *  Approach: Initial thought process for the question is in a binary search tree for a root
 *
 *  all left Values of a root < root.val < all right Values
 *
 *  so if we build up this approach is we first go left, then we go right and then we process a given node
 *
 *  [left, right, node] -> postorder traversal -> similar to any maxHeight, maxDepth, or any other similar approach
 *
 *  "so at every root we need to check if root's left side is a BST, root's right side is a BST,  and also the tree at
 *  the root is also a BST"
 *
 *  Inorder to check that we are doing this
 *
 *  Of All the trees on the left side, the maxValue (determined by left.maxNode) < root.value < Of all the trees on the
 *  right side minValue(determined by right.minNode)
 *
 * Like how it happens in a BST -> example :
 *
 *                                3
 *                               /
 *                              10
 *                            /  \
 *                          8    14
 *                         / \  /  \
 *                        7  9 12  16
 *
 *
 * For the root 10 and below if we see:
 * rightMost maxValue in the left tree 9 (left.maxNode) < 10
 * and leftMost minValue in the right tree 12 (right.minNode) -> 10 < 12
 *
 * which satisfies the condition : 9 < 10 < 12
 *
 * now the treeNode at 3 does not sastify the condition because 16 > 3, which is a way of saying if we have
 *
 * 16 at least the node at the root should be greater than 16 to be a valid bst. At least 17 or higher.
 *
 *                                   17
 *  *                               /
 *  *                              10
 *  *                            /  \
 *  *                          8    14
 *  *                         / \  /  \
 *  *                        7  9 12  16
 *
 *
 *  For the above cases what we need to do is let's say root with node3 is not a valid BST but the left subtree is one
 *
 *  so what we should do is
 *
 *  case 1: If node at root is valid BST along with left and right, then maxSum at node = root.val + left.maxSum + right.maxSum
 *  case 2: If node at root is not a valid BST then maxSum from that node = Math.max(left.maxSum, right.maxSum) and
 *    we flow the answer above
 *
 *  * now for a particular node we need minNode, maxNode, maxSum so we stored that in a container -> NodeInfo
 *
 *  Also if we consider Example2 :
 *
 *                                           4
 *  *                                       /
 *  *                                      8
 *  *                                    /  \
 *  *                                   6    1
 *  *                                  /    / \
 *  *                                 9   -5   4
 *  *                                      \    \
 *  *                                     -3    10
 *
 *
 *  Here tree at node1 is also a BST but here we will add the leftSum + rightSum + root.val but only problem here is
 *  if we add the left value(or right value, generally speaking any tree value with negatives)
 *  we would get a value of 7, instead of the BST
 *
 *  4
 *  \
 *  10
 *
 *  which would give us 14, now the solution here is we are keeping a maxNode[]{0} single value array as the global
 *  variable which would help us to keep track of all the answers because if we add as it's a valid BST we would reduce
 *  our actual answer.
 *
 *  Also negative trees, if negative answers were considered then we would have got the answer as -2 as it is the maximum
 *  sum BST and not (-4 + -5 + -11) again lowering our answer but question wants for negative trees as value 0
 *
 *   -4
 *  /  \
 * -5  -2
 *
 *  --------------------------------------------------------------------------------------------------------------------
 *  Not related to the question: but if we think in the concept of the maxPathSum with the below tree where we have
 *
 *    2
 *   /
 * -4
 *
 *  here what we did was leftSum = Math.max(0, helper(root.left)) which satisfies that condition there because
 * we needed to find the maxPath which is a path of 2, now if we apply the maxPath Sum here we would get the maxPathSum
 *
 * = 1 + 4 + 10 = 15 -> (left = 0, right = 14, node.val = 1) then we add the path = 15
 *
 *
 *
 *
 *                                               1
 *  *  *                                        / \
 *  *  *                                      -5   4
 *  *  *                                       \    \
 *  *  *                                      -3    10
 *
 *
 * but the BST we can't because though it's a BST the entire one but it would decrease our result
 * ---------------------------------------------------------------------------------------------------------------------
 *
 *
 *
 *
 *
 *
 */
public class MaximumSizeBSTInBinaryTree {
  public int maxSumBST(TreeNode root) {

    int[] maxOverall = new int[]{0};
    helper(root, maxOverall);
    return maxOverall[0];

  }

  private NodeInfo helper(TreeNode root, int[] maxOverall){
    if(root == null) return new NodeInfo(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);

    NodeInfo left = helper(root.left, maxOverall);
    NodeInfo right = helper(root.right, maxOverall);

    if((left.maxNode < root.val) && ( root.val < right.minNode)){
      maxOverall[0] = Math.max(maxOverall[0], left.maxSum + right.maxSum + root.val) ;
      return new NodeInfo(Math.min(root.val, left.minNode), Math.max(root.val, right.maxNode),
          left.maxSum + right.maxSum + root.val);
    }

    return new NodeInfo(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(left.maxSum, right.maxSum));
  }

  private class NodeInfo{
    public int minNode;
    public int maxNode;
    public int maxSum;
    public NodeInfo(int minNode, int maxNode, int maxSum){
      this.minNode = minNode;
      this.maxNode = maxNode;
      this.maxSum = maxSum;
    }
  }
}
