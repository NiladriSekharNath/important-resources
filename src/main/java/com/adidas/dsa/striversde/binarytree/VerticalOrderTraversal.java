package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Same as TopView and Bottom View we calculate the horizontal distance and keep a track of the values and add them to the
 * TreeMap but here only difference is
 *
 *   for a binary tree:
 *
 *            1
 *          /  \
 *         2    3
 *       /  \  / \
 *      4   6 5   7
 *
 *
 *    so as per the vertical order traversal we get
 *
 *    [-2 :[4], -1:[2], 0:[1,6,5], 1:[3], 2:[7]]
 *
 *    so the point is here please observer for the hd = 0, ideally it should have been 1, 5, 6 as the normal order of traversal
 *    but here the order is 1, 6, 5, so values are sorted initially by the level if levels are same we take the lowest
 *    value first so the order is 1, 5, 6
 *
 */
public class VerticalOrderTraversal {
  public List<List<Integer>> verticalTraversal(TreeNode root) {

    List<List<Integer>> verticalOrderTraversal = new ArrayList<>();

    if(root == null) return verticalOrderTraversal;

    Map<Integer, List<NodeInfo>> hdVsValueMap = new TreeMap<>();
    Queue<NodeInfo> queue = new LinkedList<>();
    queue.add(new NodeInfo(root, 0, 0));
    while(!queue.isEmpty()){

      NodeInfo currentNodeInfo = queue.poll();
      TreeNode currentNode = currentNodeInfo.node;
      Integer hd = currentNodeInfo.horizontalDistance;
      Integer currentLevel = currentNodeInfo.level;

      List<NodeInfo> currentList = hdVsValueMap.getOrDefault(hd, new ArrayList<>());
      currentList.add(currentNodeInfo);


      hdVsValueMap.put(hd, currentList);

      if(currentNode.left != null)
        queue.add(new NodeInfo(currentNode.left, currentLevel + 1,  hd - 1));
      if(currentNode.right != null)
        queue.add(new NodeInfo(currentNode.right, currentLevel+ 1, hd + 1));
    }

    for(var eachEntry: hdVsValueMap.entrySet()){

      Collections.sort(eachEntry.getValue(), (entry1, entry2) -> entry1.level != entry2.level ?
          entry1.level - entry2.level :
          entry1.node.val - entry2.node.val);


      verticalOrderTraversal.add(new ArrayList<>(eachEntry.getValue().stream().map(entry -> entry.node.val).collect(Collectors.toList())));
    }



    return verticalOrderTraversal;
  }

  private class NodeInfo{
    TreeNode node;
    int level;
    int horizontalDistance;

    public NodeInfo(TreeNode node, int level, int horizontalDistance){
      this.node = node;
      this.level = level;
      this.horizontalDistance = horizontalDistance;
    }
  }

  public static void main(String[] args){

    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);
    TreeNode node6 = new TreeNode(6);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node5.right = node6;

    new VerticalOrderTraversal().verticalTraversal(node1);
  }
}
