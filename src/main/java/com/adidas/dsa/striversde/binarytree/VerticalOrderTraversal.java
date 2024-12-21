package com.adidas.dsa.striversde.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class VerticalOrderTraversal {
  public List<List<Integer>> verticalTraversal(TreeNode root) {

    List<List<Integer>> verticalOrderTraversal = new ArrayList<>();

    if(root == null) return verticalOrderTraversal;

    Map<Integer, List<Integer>> hdVsValueMap = new TreeMap<>();
    Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
    queue.add(new Pair<>(root, 0));
    while(!queue.isEmpty()){

      Pair<TreeNode, Integer> currentPair = queue.poll();
      TreeNode currentNode = currentPair.node;
      Integer hd = currentPair.value;

      List<Integer> currentList = hdVsValueMap.getOrDefault(hd, new ArrayList<>());
      currentList.add(currentNode.val);


      hdVsValueMap.put(hd, currentList);

      if(currentNode.left != null)
        queue.add(new Pair<>(currentNode.left, hd - 1));
      if(currentNode.right != null)
        queue.add(new Pair<>(currentNode.right, hd + 1));
    }

    for(var eachEntry : hdVsValueMap.entrySet()){
      verticalOrderTraversal.add(new ArrayList<>(eachEntry.getValue()));
    }

    return verticalOrderTraversal;
  }


  private class Pair<K,V>{
    K node;
    V value;
    public Pair(K node, V value){
      this.node = node;
      this.value = value;
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
