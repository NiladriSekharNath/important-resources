package com.adidas.dsa.striversde.binarytree;

/**
 * You are given the root of a binary tree. We install cameras on the tree nodes where each camera at a node can monitor its parent, itself, and its immediate children.
 *
 * Return the minimum number of cameras needed to monitor all nodes of the tree.
 *
 *
 * let's say for a tree we have this setting :
 *
 *                      1
 *                     /
 *                    2
 *                   / \
 *                  3   4
 *
 * minCamera : required = 1 if we placed a camera at node2 only
 *
 *
 * Idea is there are 3 types of node states
 *
 *
 * State 0: If node has no Camera and is not covered
 * State 1: If node has no Camera but is covered by Parent/Sibling
 * State 2: If node has a Camera and is covered
 *
 * we go totally bottom using the dfs approach (post order traversal approach) to the very bottom
 *
 * We check if a node is either 0 or 1 or 2 inorder to do that we check from it's children
 *
 * we need a minCamera variable globally(or passing as a size 1 array)
 *
 *  if left children or right children either 0 : then we need a camera at current node so we increase the count at root
 *  increase minCamera[0]++ and return the type of that node, which is 2 because we have a camera at the node
 *
 *  if left child has a camera or right Child has a camera, all we need to do for that is return 1 for the node
 *  because if left child is convered or the right child is covered we just need to cover the current root because if we
 *  cover the  parent we are good for the children nodes
 *
 *  if either the above cases don't satisfy then we should return 0 for the node as it that particular node is not covered
 *  and has no camera
 *
 *  For the base case when both the children are null (for a leaf node) we need to return 1 because we don't need a camera
 *  on a leaf because ideally we would be placing a camera at the parent only
 *
 *  Finally one edge case if we have a single node below example
 *
 *          0
 *         / \
 *
 *  from the base case we are getting 1 from left and 1 from right we so we return 0, now for this case
 *  our final answer would be zero from our solution(which is wrong) because for a node like that it would go uncovered
 *  as for a single node we need atleast one camera
 *
 *  so we add a check if we get 0 then we simply increase the minCamera requirement by 1 and return 1 as the final answer
 *
 *
 *
 *
 */
public class  BinaryTreeCameras {

  public int minCameraCover(TreeNode root) {
    int[] minCamera = new int[]{0};
    if((helper(root, minCamera)) < 1 ){
      minCamera[0]++;
    }
    return minCamera[0];
  }
  private int helper(TreeNode root, int[] minCamera){
    if(root == null) return 1;

    int left = helper(root.left, minCamera);
    int right = helper(root.right, minCamera);

    if(left == 0 || right == 0){
      minCamera[0]++;
      return 2;
    }
    if(left == 2 || right == 2){
      return 1;
    }

    return 0;
  }

  /**
   *
   *  -1 = not covered, 0 = covered, 1 = camera
   *
   *  for this approach if both left and right is null for a leaf node then we return -1
   *
   *  now if either left = -1 or right = -1 then we increase the count of the camera by 1, and return 1
   *
   *  now if either the left = 1 or right = 1 then we don't need to increase the count of the cameras we simply return 0
   *
   *  because current node is covered by the children
   *
   *  otherwise if it is not covered we return -1 as the node is not covered
   *
   *
   */
  public int minCameraCoverApproach2(TreeNode root) {
    if(root.left == null && root.right == null) return 1;
    int[] minCamera = new int[]{0};
    if(helper2(root, minCamera) == -1) {
      minCamera[0]++;
    }

    return minCamera[0];

  }

  /**
   *
   * @param root
   * @param minCamera
   * @return
   *
   *
   *         0
   *        /
   *       0
   *      / \
   *     0   0
   */
  private int helper2(TreeNode root, int[] minCamera){
    if(root.left == null && root.right == null){
      return -1 ;
    }
    int left = 0;
    if(root.left != null){
      left = helper2(root.left, minCamera);
    }
    int right = 0;
    if(root.right != null){
      right = helper2(root.right, minCamera);
    }

    if(left == -1 || right == -1){
      minCamera[0]++;
      return 1;
    }
    if(left == 1 || right == 1){
      return 0;
    }
    return -1;
  }
}
