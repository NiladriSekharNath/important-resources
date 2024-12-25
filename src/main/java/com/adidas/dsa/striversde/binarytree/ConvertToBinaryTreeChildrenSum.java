package com.adidas.dsa.striversde.binarytree;

/**
 * Given a binary Tree convert the nodes such that nodes in the tree hold the children sum property
 *
 * Note :
 *  1. You can only increment the value of the nodes, in other words, the modified value must be at least equal to the original value of that node.
 *  2. You can not change the structure of the original binary tree.
 *  3. A binary tree is a tree in which each node has at most two children.
 *  4. You can assume the value can be 0 for a NULL node and there can also be an empty tree.
 *
 *
 * example1:
 *                         2                           45
 *                       /  \                        /   \
 *                      35   10        ->           35    10
 *                     / \  / \                    / \   / \
 *                    2  3 5  2                   10 15 8  2
 *
 * example2:
 *
 *                           50
 *  *                       /  \
 *  *                      7    2
 *  *                     / \  / \
 *  *                    3  5 1  30
 *
 *  There are many possible values for this problem
 *
 *  okay we are tasked to make the nodes satisfy the children sum property
 *
 *  so when we are able to do that :
 *
 *  so we can to do the "maximum depth of the binary tree" approach like going down and then going up
 *  in example 1 : like adding checking 35 is less than 2 + 3 so we add the values and then at node35 and node10 left
 *  and right having values 35 and 10 we can adjust the node2 value to be changed from 2 -> 45
 *
 *  that would satisfy our problem for example1:
 *
 *  This approach would fail for example 2 :
 *
 *  as if node7 value we change from 7 -> 8 and node2 we change from 2 -> 32
 *
 *  that would still give us 39 less than 50 so not possible and it would fail
 *
 *  as there is still a shortage of value here which would not help our case
 *
 *  So the optimal approach follows :
 *
 *  We take from a root the leftChild Value and the rightChildValue let's say for example1 where node2
 *  has a value 2 and leftchild has value 35 and rightChild has a value 10
 *
 *  so childValue = 45
 *
 *  so since in our case we write this code
 *
 *   if(childValue >= root.val) root.val = childValue;
 *     else {
 *       if(root.left != null) root.left.val = root.val;
 *       if(root.right != null) root.right.val = root.val;
 *
 *     }
 *
 *   since childValue >= root.value [ 45 >= 2] we change the value from 2 to 45
 *
 *   otherwise for the example 2
 *
 *   for node50 we change the leftChild value to 50 and rightChild value to 50
 *
 *   and keep propagating the same
 *
 *   *** in the backtracking step
 *       we simply calculate the leftchild value and rightChild value and update the value of the root
 *       hence there is no shortage problem like in our previous approach
 *
 *
 *
 * TC : O(N)
 * SC : O(height of tree) worst case O(n) if skew tree
 *
 *
 *
 *
 */
public class ConvertToBinaryTreeChildrenSum {
  public static void changeTree(TreeNode root) {
    if(root == null) return ;
    int childValue = 0;
    if(root.left != null){
      childValue += root.left.val;
    }

    if(root.right != null){
      childValue += root.right.val;
    }

    if(childValue >= root.val) root.val = childValue;
    else {
      if(root.left != null) root.left.val = root.val;
      if(root.right != null) root.right.val = root.val;

    }

    changeTree(root.left);
    changeTree(root.right);

    /**
     * *** backtracking step
     */
    int totalValue = 0;
    if(root.left != null) totalValue += root.left.val;
    if(root.right != null) totalValue += root.right.val;
    if(root.left != null || root.right != null) root.val = totalValue;
  }
}
