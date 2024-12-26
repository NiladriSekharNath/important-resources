package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * We are given a preorder traversal of a BST, we are required to create a BST from that
 *
 * example : [8, 5, 1, 7, 10, 12]
 * Output Tree:
 *
 *                               8
 *                             /  \
 *                            5   10
 *                           / \    \
 *                          1   7   12
 *
 *
 *  since preorder = Root, Left, Right
 *
 *  Approach: My Approach is take the first element and select every element after the first element which would be either
 *  larger or smaller, smaller elements are added to the left list and larger elements are added to rightList
 *  and, we are sending the list to the left and then to the rightnode
 *
 *
 *  Optimal approach : we are following the principle and the same concept of validate BST wherein we keep an upperbound
 *  to keep track
 *
 *  let's say for a node for our example in the node8 we have, when we go left we know left side would have an upper bound
 *  of 8 and right side should be higher, again in the node5 when we go left we know upper bound =5 at node5 for the
 *  left side, and on the rightside we are sending the same upper bound as the one flowed from node8 which is upperbound
 *  is 8, meaning we can have any number greater than 5 but less than 8 on the right of 5
 *
 *  Also for this we keep an global variable index in a size 1 array to keep track of the next element
 *
 *
 *
 */
public class ConstructBinarySearchTreeFromPreOrderTraversal {
  public TreeNode bstFromPreorder(int[] preorder) {
    return helper(Arrays.stream(preorder).boxed().collect(Collectors.toList()));
  }

  private TreeNode helper(List<Integer> preorder){
    if(preorder.size() == 0) return null;
    TreeNode rootNode = new TreeNode(preorder.get(0));
    List<Integer> left = new ArrayList<>();
    List<Integer> right = new ArrayList<>();
    for(int i = 1; i < preorder.size(); i++){
      if(preorder.get(i) < rootNode.val)
        left.add(preorder.get(i));
      else
        right.add(preorder.get(i));
    }
    rootNode.left = helper(left);
    rootNode.right = helper(right);
    return rootNode;
  }

  public TreeNode bstFromPreorderOptimalSolution(int[] preorder){
    return helperOptimalSolution(preorder, new int[]{0}, Integer.MAX_VALUE);
  }
  private TreeNode helperOptimalSolution(int[] preorder, int[] currentIndex, int upperBound){
    if(currentIndex[0] >= preorder.length || preorder[currentIndex[0]] > upperBound) return null;

    TreeNode rootNode = new TreeNode(preorder[currentIndex[0]++]);

    rootNode.left = helperOptimalSolution(preorder, currentIndex, rootNode.val);
    rootNode.right = helperOptimalSolution(preorder, currentIndex, upperBound);

    return rootNode;

  }
}
