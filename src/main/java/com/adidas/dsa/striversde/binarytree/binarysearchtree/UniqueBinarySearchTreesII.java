package com.adidas.dsa.striversde.binarytree.binarysearchtree;

import com.adidas.dsa.striversde.binarytree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an integer n, return all the structurally unique BST's (binary search trees), which has exactly n nodes of unique values from 1 to n. Return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 3
 * Output: [[1,null,2,null,3],[1,null,3,2],[2,1,3],[3,1,null,null,2],[3,2,null,1]]
 * Example 2:
 *
 * Input: n = 1
 * Output: [[1]]
 *
 *
 * Approach is quite simple :
 *
 * let's say we have a numbers from n = 5, 1,2,3,4,5
 *
 * so here if we take all numbers from 1,2,3,4,5
 *
 * we start loop from 1 to n :
 *
 * let's say for a particular iteration we take 3 : start = 3 as the root of the tree
 *
 * we say giveMeAllPossibleTrees with roots from 1,2 -> helper(1,2) -> leftsubtrees
 * again we say giveMeAllPossibleTrees with roots from 4,5 -> helper(4,5) -> right subtrees
 *
 * let's say from left using 1 as the root
 *
 * we get: root = 1
 *
 *        1
 *         \
 *          2
 * and using 2: as the root and with 1
 *
 *      2
 *     /
 *    1
 *
 * and with 4,5 we get
 * using 4:
 *
 *    4
 *     \
 *      5
 *
 * and using 5 as the root
 *
 *    5
 *   /
 *  4
 *
 * now once we have 2 on the left and 2 on the right we do a cross product to find our actual answer using 3 as the root
 *
 * let's
 * say
 *
 *           1         2   4        5
 *  *         \       /     \      /
 *  *          2     1       5    4
 *
 *  if we number the above 1,2,3,4 respectively or L1, L2, R1, R2,
 *
 *  we do a cross L1-3-R1, L1-3-R2, L2-3-R1, L2-3-R2 (meaning L1 *(R1, R2), L2 *(R1, R2))
 *
 *  getting the trees as
 *
 *
 *        3               3            3             3
 *      /  \            /  \         /  \          /  \
 *     1    4          1    5       2   4         2    5
 *     \    \          \    /      /    \        /    /
 *      2    5          2  4      1      5      1    4
 *
 *
 */
public class UniqueBinarySearchTreesII {
  public List<TreeNode> generateTrees(int n){
    return helper(1, n, new HashMap<>());
  }

  private List<TreeNode> helper(int low, int high, Map<String, List<TreeNode>> map){
    List<TreeNode> resultList = new ArrayList<>();
    if(low > high) {
      resultList.add(null);
      return resultList;
    }

    String lowHigh = String.format("%s_%s", low, high);
    if(map.containsKey(lowHigh)) return map.get(lowHigh);

    for(int start = low ; start <= high ; start++){

      List<TreeNode> leftTrees = helper(low, start - 1, map);
      List<TreeNode> rightTrees = helper(start + 1, high, map);

      for(TreeNode eachLeftSubTree : leftTrees){
        for(TreeNode eachRightSubTree : rightTrees){
          TreeNode root = new TreeNode(start);
          root.left = eachLeftSubTree;
          root.right = eachRightSubTree;
          resultList.add(root);
        }
      }
    }

    map.put(lowHigh, resultList);

    return resultList;
  }


  public static void main(String args[]){
    new UniqueBinarySearchTreesII().generateTrees(3);
  }
}
