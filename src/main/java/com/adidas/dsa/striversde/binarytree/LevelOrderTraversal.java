package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
  /**
   *
   * @param root given the root of a binary Tree
   * @return the level orderTraversal of the binary tree level by level
   *
   *
   *
   *                          20
   *  *                     /    \
   *  *                   8       22
   *  *                 /   \     /  \
   *  *               5      3 4     25
   *  *                      /    \
   *  *                  10       14
   *
   *
   *  Answer: [[20], [8, 22], [5, 3, 4, 25], [10, 14]
   *
   *  Following is the standard way of performing the level order Traversal in the binary Tree which is okay
   *
   *  using a queue and level by level and adding values in the queue
   *
   *
   */
  public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levelOrderTraversal = new ArrayList<>();
        if(root == null) return levelOrderTraversal;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for(int i = 0 ; i < size; i++){
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                if(currentNode.left != null)
                    queue.add(currentNode.left);
                if(currentNode.right != null)
                    queue.add(currentNode.right);
            }

            levelOrderTraversal.add(currentLevel);
        }

        return levelOrderTraversal;

  }

  /**
   *  This is the non-standard but interesting way of doing the level orderTraversal
   *
   *  In the helper method we go down level by level increasing the level at each function call
   *
   *  let's say in the first level when we don't have any inside array to add elements
   *
   *  like at node 20 at level 0 :
   *  when the levelOrderTraversal Array == [] empty
   *
   *  if we do get(currentLevel).add(element) it will throw arrayIndexOutOfBoundsException
   *
   *  so what we do if currentLevel == currentSize of the ds
   *
   *  we add a new ArrayList<> [[]],  so we get for the 0-index a particular place to add the value
   *
   *  similarly for left in node 8 above
   *  with the current levelOrderTraversal = [[20]],
   *  currentLevel = 1;
   *  currentSize of levelOrderTraversal = 1
   *
   *  so we add another new ArrayList<> like this, levelOrderTraversal = [[20], []]
   *
   *  and add the element [[20], [8]] and keep moving on...
   *
   */

  public List<List<Integer>> levelOrderUsingRecursion(TreeNode root) {
    List<List<Integer>> levelOrderTraversal = new ArrayList<>();
    if(root == null) return levelOrderTraversal;
    helper(root, 0, levelOrderTraversal);
    return levelOrderTraversal;
  }

  private void helper(TreeNode root, int currentLevel, List<List<Integer>> levelOrderTraversal){
    if(root == null) return ;

    if(currentLevel == levelOrderTraversal.size())
      levelOrderTraversal.add(new ArrayList<>());

    levelOrderTraversal.get(currentLevel).add(root.val);

    helper(root.left, currentLevel + 1, levelOrderTraversal);
    helper(root.right, currentLevel + 1, levelOrderTraversal);
  }
}
