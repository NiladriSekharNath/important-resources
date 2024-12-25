package com.adidas.dsa.striversde.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * Similar solution as the constructBinaryTree From Inorder and PreOrder question
 *
 * Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 *
 *
 *                        3
 *  *                   /  \
 *  *                  9   20
 *  *                     / \
 *  *                    15  7
 *
 * so the main idea of this problem is we have
 *
 * inorder  [9, 3, 15, 20, 7]       -> left, root, right
 * postorder [9, 15, 7, 20, 3]      -> left, right, root
 *
 * so we are doing the same thing just that we are doing from the right side now
 *
 * so since the root is on the right
 *
 * we have the tree as
 *
 *         3
 *  *    /  \
 *  * [9]  [15, 20,7]
 *
 *  we continue the rest of the problem as we find the right subtree as we find the index of the rootelement 3 and so 3 elements
 *
 *  from the right of the tree in inorder are on the right subtree [15, 20, 7] (right), and one element from the left [9]
 *
 *  we calculate the number of elements on the right subtree = highP - (index + 1) + 1 === highP - index
 *
 *  so left subtree [inorder, lowI, index-1, postorder, lowP, highP - rightElements - 1
 *
 *  and right Subtree = [inorder, index + 1, high, postorder, highP - rightElements, highP - 1]
 *
 *                for the right subtree we get that the previous element to the current root is the starting right
 *                element so highP - 1,
 *
 *                [9, 15, 7, 20, 3] -> index [1, 3], in order to get the lowP we know from the inorder there are 3 elements
 *                on the right = (highP - 1) - rightElements + 1 -> (4 - 1) - 3 + 1 -> 3 - 3 + 1 -> 1
 *
 *
 *                Also lowP and highP here represents the index of LOWPOSTORDER and highPostorder
 *
 *
 *
 */
public class ConstructBinaryTreeFromInorderAndPostOrder {
  public TreeNode buildTree(int[] inorder, int[] postorder) {
    if(inorder.length == 0 || postorder.length == 0) return null;

    Map<Integer, Integer> indexMap = new HashMap<>();
    for(int index = 0; index < inorder.length; index++){
      indexMap.put(inorder[index], index);
    }

    return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, indexMap);
  }

  private TreeNode helper(int[] inorder, int lowI, int highI, int[] postorder, int lowP, int highP, Map<Integer, Integer> indexMap){

    if(lowP > highP || lowI > highI) return null;

    int rootValue = postorder[highP];

    TreeNode root = new TreeNode(rootValue);

    int index = indexMap.get(rootValue);

    int rightElements = highI - index;

    root.left = helper(inorder, lowI, index - 1, postorder, lowP, highP - rightElements - 1, indexMap);
    root.right = helper(inorder, index + 1, highI, postorder, highP - rightElements, highP - 1, indexMap);

    return root;
  }

  public static void main(String[] args){
    new ConstructBinaryTreeFromInorderAndPostOrder().buildTree(new int[]{9, 3, 15, 20, 7}, new int[]{ 9, 15, 7, 20, 3});
  }
}
