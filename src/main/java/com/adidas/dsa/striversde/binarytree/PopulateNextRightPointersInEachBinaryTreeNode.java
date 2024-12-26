package com.adidas.dsa.striversde.binarytree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ou are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL
 *
 *
 *                          20
 *  *                     /    \
 *  *                   8       22
 *  *                 /   \     /  \
 *  *               5      3 4     25
 *
 *  Convert the above tree to this below point
 *
 *                             20
 *  *  *                     /    \
 *  *  *                   8 ---->  22
 *  *  *                /   \      /   \
 *  *  *              5 --> 3 ---> 4--> 25
 *  *
 */
public class PopulateNextRightPointersInEachBinaryTreeNode {

  /**
   *
   * Used two approaches here using a BFS approach for each level we take previous pointer
   * and only assign the currentnode with the previous pointer when i > 0 (except the first node in the level)
   * we do this steps level-by-level
   */
  public Node connect_BFSApproach(Node root) {
        Queue<Node> queue = new LinkedList<>();
        if(root == null) return root;
        queue.add(root);

        while(!queue.isEmpty()){
            int size = queue.size();
            Node previous = null;
            for(int i = 0 ; i < size; i++){

                Node currentNode = queue.poll();

                if(i > 0){
                    previous.next = currentNode;
                }

                if(currentNode.left != null){
                    queue.add(currentNode.left);
                }
                if(currentNode.right != null){
                    queue.add(currentNode.right);
                }
                previous = currentNode;
            }
        }

        return root;
    }

  /**
   *
   * Recursive approach for a root, we
   * point the left child of the root to the right child for the same node
   *
   * only point is, during recursive call when root == node8, in the node8 after leftchild, node5 points to node3,
   * we need node3 to point to the left child node4, of root node22, we check root.next != null which is done in
   * the previous recursive call(root = node20), as this is already updated then we point using this line
   *
   * if(root.right != null && root.next != null) root.right.next = root.next.left;
   *
   * ((8).right.next == 3.next) = (22.next.left == 4)
   */

  public Node connect_recursiveApproach(Node root){
    if(root == null) return root;
    if(root.left != null) root.left.next = root.right;
    if(root.right != null && root.next != null) root.right.next = root.next.left;
    connect_recursiveApproach(root.left);
    connect_recursiveApproach(root.right);
    return root ;
  }

  class Node{
    int val;
    Node left;
    Node right ;
    Node next;
    public Node(){}
  }
}
