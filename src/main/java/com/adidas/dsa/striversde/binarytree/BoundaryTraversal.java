package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * for a Binary Tree we need the boundary Traversal
 *
 *                  20
 *                /   \
 *               15   16
 *              / \    \
 *             7  15   18
 *             \        \
 *             13       19
 *
 * boundary Traversal : [20, 15, 7, 13, 15, 19, 18, 16] anti-clockwise
 *
 * so we are doing as the normal way :
 *
 * 3 steps :
 *
 * Adding the root separately : as it is common for both left and right : [20]
 *
 * LeftBoundary without leaves; [20, 15, 7] existing list has 20
 * Add leaves : [20, 15, 7, 13, 15, 18]
 * RightBoundary reverseOrder without Leaves [16, 18] so we add this to a stack and when popped we add [18, 16] to the resultList
 *
 * 20, 15, 7, 13, 15, 18, 19, 18, 16
 *
 */

public class BoundaryTraversal {
  public static List<Integer> traverseBoundary(TreeNode root){
    List<Integer> boundaryTraversal = new ArrayList<>();

    if(root == null) return boundaryTraversal;

    /**
     * add common root
     */

    if(!isLeafNode(root))
      boundaryTraversal.add(root.val);


    addLeftBoundary(root, boundaryTraversal);
    addLeaves(root, boundaryTraversal);
    addRightBoundary(root, boundaryTraversal);

    return boundaryTraversal;
  }

  private static void addLeftBoundary(TreeNode root, List<Integer> boundaryTraversal){

    /**
     *  we go left as we already added root
     */

    TreeNode currentNode = root.left;

    while(currentNode != null){

      /**
       *  If it's a leaf node we don't add, otherwise we keep adding
       */

      if(!isLeafNode(currentNode)){
        boundaryTraversal.add(currentNode.val);
      }


      /**
       *  we check first if left not equals to null, then we go left, since left boundary we don't need right on the same level,
       *  if left is null then only we move right
       */

      if(currentNode.left != null)
        currentNode = currentNode.left;
      else
        currentNode = currentNode.right;
    }
  }

  private static void addLeaves(TreeNode root, List<Integer> boundaryTraversal){
    if(root == null) return;

    if(isLeafNode(root))
      boundaryTraversal.add(root.val);

    addLeaves(root.left, boundaryTraversal);
    addLeaves(root.right, boundaryTraversal);
  }

  private static void addRightBoundary(TreeNode root, List<Integer> boundaryTraversal){

    /**
     * same logic we do for the rightBoundary, like in the leftBoundary, we don't need the left value as in the right boundary
     * the right Value is only required
     */

    TreeNode currentNode = root.right;

    Stack<TreeNode> stack = new Stack<>();

    while(currentNode != null){

      if(!isLeafNode(currentNode)){
        stack.add(currentNode);
      }

      if(currentNode.right != null)
        currentNode = currentNode.right;
      else
        currentNode = currentNode.left;
    }

    while(!stack.isEmpty())
      boundaryTraversal.add(stack.pop().val);

  }

  private static boolean isLeafNode(TreeNode root){
    return root.left == null && root.right == null;
  }
}
