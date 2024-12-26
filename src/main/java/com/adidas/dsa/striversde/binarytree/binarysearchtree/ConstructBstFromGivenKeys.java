package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

/**
 * Given an integer array nums where the elements are sorted in ascending order, convert it to a
 * height-balanced
 *  binary search tree.
 *
 *  so given to us a sorted array
 *
 *  [-15, -10, -3, 0, 5, 9, 12]
 *
 *  the idea is in order to ensure the tree is height balanced we do this
 *
 *  we take the array from low to high and at every call find the mid from low to high
 *
 *  once we find the mid, the mid becomes the root of our tree then the
 *  left subtree ->  [low, mid - 1]
 *  and right subtree -> [mid + 1, high]
 *
 * and we continue with the recursion
 *
 */
public class ConstructBstFromGivenKeys {
  public TreeNode sortedArrayToBST(int[] nums) {
    return helper(nums, 0, nums.length -1);
  }

  private TreeNode helper(int[] nums, int low, int high){
    if(low > high) return null;
    int mid = low + (high - low)/2;
    TreeNode rootNode = new TreeNode(nums[mid]);

    rootNode.left = helper(nums, low, mid - 1);
    rootNode.right = helper(nums, mid+1, high);

    return rootNode;
  }
}
