package com.adidas.dsa.striversde.binarytree;

import java.util.HashMap;
import java.util.Map;

/**
 * Construct a binary Tree from preorder and inorder traversals
 *
 * preorder[] = [3, 9, 20, 15, 7]
 * inorder[] = [9, 3, 15, 20, 7]
 *
 * tree constructed is
 *
 *                     3
 *                    / \
 *                   9  20
 *                     / \
 *                    15  7
 *
 *
 * Solution approach to this :
 *
 * everytime at the start we find the first element in the preorder as the root
 *
 * preorder = Root, left, right
 * inorder = left, root, right
 *
 * so first element is always the root
 *
 * we try to find the element's index which is 3 in this case in the inorder traversal
 *
 * which we get [9, 3, 15, 20, 7] at index = 1
 *
 * now we can continue the rest the left elements are part of the left subtree tree and the right elements are part of the right
 * subtree
 *      3
 *    /  \
 * [9]  [15, 20,7]
 *
 * now since we can see we have 1 element to the left of element 3 and 3 elements on the right of element 3
 *
 * we can find the next index in the preorder also [3, 9, 20, 15, 7] where can say the next element after element 3
 * 1 and 3 elements [20, 15, 7] as the candidates of left and right subtree
 *
 * so what we do is for the left subtree in the function call we calculate the number of elements in the left of 3
 *
 * in   int index = indexMap.get(preorder[lowP]);
 *
 *     int leftElements = (index - 1) - lowI + 1; where we can simplify as (index - lowI) and we know the next element
 *     itself is the starting point of the next preorder candidate so the left sub-tree
 *
 *     leftSubtree : lowP, highP -> [lowP + 1, lowP + leftElements]
 *     rightSubtree : lowP, highP -> [lowP + leftElements + 1, highP] because for the right subtree we are starting
 *                                                                    from the next element after lowP + leftElements + 1
 *
 *     and we keep continuing on the recursion
 *
 */
public class ConstructBinaryTreeFromInOrderAndPreOrder {
  public TreeNode buildTree(int[] preorder, int[] inorder) {
    if(preorder.length == 0 || inorder.length == 0) return null;
    Map<Integer,Integer> indexMap = new HashMap<>();

    for(int i = 0 ; i < inorder.length; i++){
      indexMap.put(inorder[i], i);
    }

    return helper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, indexMap);
  }

  private TreeNode helper(int[] preorder, int lowP, int highP, int[] inorder, int lowI, int highI, Map<Integer, Integer> indexMap){

    if(lowP > highP || lowI > highI) return null;

    TreeNode root = new TreeNode(preorder[lowP]);

    int index = indexMap.get(preorder[lowP]);

    int leftElements = (index - 1) - lowI + 1;

    root.left = helper(preorder, lowP + 1, lowP + leftElements, inorder, lowI, index - 1, indexMap);
    root.right = helper(preorder, lowP + leftElements + 1, highP, inorder, index + 1, highI, indexMap);

    return root;
  }

  public static void main(String[] args){
    new ConstructBinaryTreeFromInOrderAndPreOrder().buildTree(new int[]{1, 2}, new int[]{1, 2});
  }
}
