package com.adidas.dsa.striversde.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a Binary Tree we are finding the max width of the binary Tree
 *
 *                          20
 *  *                     /    \
 *  *                   8       22
 *  *                 /   \     /  \
 *  *               5      13   14    25
 *  *                      /    \
 *  *                  10       16
 *
 *  Here in the example we are getting the answer let's say maxWidth = 4 ( 6 - 3 + 1)
 *
 *  Approach is we start indexing the binary Tree level by level if we start with 0-based indexing we have,
 *  so leftChild = (2 * i) + 1, rightChild = (2 * i) + 2, where 'i' is the index of the root(or parent)
 *
 *  we are writing (node : index)
 *
 *  Level 0 -> 20 : 0 (i = 0)
 *  Level 1 -> 8 : 1 [(2 * i) + 1 -> 2 * 0 + 1], 22 : 2 [(2 * i) + 2 -> 2 * 0 + 2]
 *  Level 2 -> 5 : 3 [(2 * 1) + 1), 13 : 4, 14 : 5, 25 : 6
 *  ...
 *
 *  so we get maxWidth = 6 - 3 + 1
 *
 *  now same logic is just followed we do a level-by-level traversal, and we keep doing find the leftmost index width and the rightMost
 *  index Width, and we do (rightMostIndexWidth - leftMostIndexWidth + 1)
 *
 *  Now one adjustment is done in the code : if we keep getting a skew tree :
 *
 *                     1  index = 0
 *                    /
 *                   2   index = 1
 *                  /
 *                 3    index = 3 (2 * 1 + 1)
 *                /
 *               4     index = 7 (2 * 3 + 1)
 *               ... ...
 *               ... ...
 *               ... ... so the index keeps increasing in the factor of in the left and right side by (2*n + 1, 2n + 2) respectively.
 *                       This might overflow
 *
 * So in order to adjust the index
 *
 *                             20
 *  *  *                     /    \
 *  *  *                   8       22
 *  *  *                 /   \     /  \
 *  *  *               5      13  14    25
 *  *  *                      /    \
 *  *  *                  10       16
 *  *
 *
 * we are doing this at each level we take the minimum of the level and minus it then calculate
 *
 * let's say for the node 8 in level 1(0 - based) instead of taking 3(2 * 1 + 1) for the node 5 we
 * do this 2 * ( 1 - 1 ) + 1 effectively getting for the node 5 index = 1, and for the node 22, when computing for the
 * node 4, instead of 5(2 * 2 + 1) we get 2 * (2 - 1) + 1 = 2 * 1 + 1 = 3
 *
 */
public class WidthOfBinaryTree {


  public int widthOfBinaryTree(TreeNode root) {
    int maxWidth = Integer.MIN_VALUE;
    Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
    queue.add(new Pair<>(root, 0));
    while(!queue.isEmpty()){
      int size = queue.size();
      int minWidthInLevel = queue.peek().width;
      int leftWidth = 0;
      int rightWidth = 0;
      for(int i = 0; i< size; i++){
        Pair<TreeNode, Integer> currentPair = queue.poll();
        TreeNode currentNode = currentPair.node;
        int width = currentPair.width;

        if(i == 0){
          leftWidth = width;
        }

        if(i == size - 1)
          rightWidth = width;

        if(currentNode.left != null){
          queue.add(new Pair<>(currentNode.left, (width - minWidthInLevel) * 2 + 1));
        }
        if(currentNode.right != null){

          queue.add(new Pair<>(currentNode.right, (width - minWidthInLevel) * 2 + 2));
        }

      }

      maxWidth = Math.max(maxWidth, rightWidth - leftWidth + 1);

    }

    return maxWidth;
  }

  private class Pair<K, V extends Integer> {
    K node ;
    V width;
    public Pair(K node, V width){
      this.node = node;
      this.width = width;
    }
  }


  public static void main(String[] args){

    TreeNode node1 = new TreeNode(1);
    TreeNode node3_1 = new TreeNode(3);
    TreeNode node2 = new TreeNode(2);
    TreeNode node5 = new TreeNode(5);
    TreeNode node3_2 = new TreeNode(3);
    TreeNode node9 = new TreeNode(9);

    node1.left = node3_1;
    node1.right = node2;
    node3_1.left = node5;
    node3_1.right = node3_2;
    node2.right = node9;

    new WidthOfBinaryTree().widthOfBinaryTree(node1);
  }
}
