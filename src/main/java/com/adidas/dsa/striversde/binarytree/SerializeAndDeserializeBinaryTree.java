package com.adidas.dsa.striversde.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a Binary Tree we are serializing and deserializing that binary Tree
 *
 *                1
 *               / \
 *              2   3
 *                /  \
 *               4   5
 *
 *  After Serialization, we get the value as :
 *
 *  1,2,N,N,3,4,N,N,5,N,N
 *
 *  In order to deserialize this we feed this to a Queue after splitting by comma
 *
 *  so our queue contains [1 2 N N 3 4 N N 5 N N]
 *
 *  now we poll every value from queue if currentValue = "N" then we return null otherwise we assign to the left and then
 *  we assign to the right
 *
 *
 */
public class SerializeAndDeserializeBinaryTree {
  public String serialize(TreeNode root) {
    if(root == null) return "N";
    String left = serialize(root.left);
    String right = serialize(root.right);
    return String.format("%s,%s,%s", root.val, left, right);
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    return helper(new LinkedList<>(Arrays.asList(data.split(","))));
  }

  private TreeNode helper(Queue<String> data){
    /**
     * if(data.size() == 0) return null;
     *
     * we don't need the data.size() because at last step when
     *
     * for node5's right when queue gets "N" then we return from the queue
     *
     * which returns to node5's -> root.right and then root is returned
     *
     * which then returns node5 to the node1's right which then returns the node1 as the root of the given tree and there
     * we stop
     *
     * after which there is no call to an empty queue, so we don't need additional check
     */


    String val = data.poll();

    if(val.equals("N") ) return null;

    TreeNode root = new TreeNode(Integer.parseInt(val));

    root.left = helper(data);
    root.right = helper(data);

    return root;
  }


  public static void main(String args[]){
    TreeNode node1 = new TreeNode(1);
    TreeNode node2 = new TreeNode(2);
    TreeNode node3 = new TreeNode(3);
    TreeNode node4 = new TreeNode(4);
    TreeNode node5 = new TreeNode(5);

    node1.left = node2;
    node1.right = node3;

    node3.left = node4;
    node3.right = node5;

    String value = new SerializeAndDeserializeBinaryTree().serialize(node1);

    new SerializeAndDeserializeBinaryTree().deserialize(value);


  }
}
