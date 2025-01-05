package com.adidas.dsa.striversde.binarytree;

/**
 *
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 *           1
 *          / \
 *         2   5
 *        / \   \
 *       3   4   6
 *              /
 *             7
 *
 * Answer:
 *            1
 *             \
 *              2
 *               \
 *                3
 *                 \
 *                  4
 *                   \
 *                    5
 *                     \
 *                      6
 *                       \
 *                        7
 *
 * okay this one is just to show that roots of the tree are flattened to look like a linkedlist but
 * here instead of taking the next we are making use of the right pointer
 *
 * which is also the preorder traversal of the list
 *
 * since we know preorder traversal is -> root, left, right
 *
 * so inorder to get 7 at then end the recursive solution is simply by going right first then left then processing root
 *
 * kind of like "right, left, root"
 *
 * and we are using a previous variable to point to the previous value
 *
 * since we are using going right first at the end when we are the root7 we are pointing, root.right to previous
 *
 * since previous is currently null we are pointing 7's right to null and 7's left to null and updating previous to
 * 7(root )
 *
 * when we are at the root6 we are now actually pointing the root6's right to 7 and making the root6's left to null
 * and updating the previous to root6 and continuing the recursion
 *
 *
 */
public class FlattenBinaryTreeToLinkedList {


  public void flatten(TreeNode root) {
    helper(root);
  }

  private TreeNode helper(TreeNode root){
    if(root == null) return root;
    if(root.left == null && root.right == null) return root;
    TreeNode left = helper(root.left);
    TreeNode right = helper(root.right);

    return merge(root, left, right);

  }

  private TreeNode merge(TreeNode root, TreeNode left, TreeNode right){
    root.left = null;

    if(left == null) return root;

    root.right = left;


    TreeNode currentPointer = root ;

    while(currentPointer.right != null){
      currentPointer = currentPointer.right;
    }

    currentPointer.right = right;
    return root;
  }


  public void flattenWithRecursion(TreeNode root) {
    TreeNode previous[] = new TreeNode[]{ null } ;
    helper(root, previous);

  }

  private void helper(TreeNode root, TreeNode[] previous){
    if(root == null) return;

    helper(root.right, previous);
    helper(root.left, previous);

    root.right = previous[0];
    root.left = null;
    previous[0] = root;

  }

  /**
   *
   * the morris way is pretty straight forward what we are doing is we are initially pointing the current to the root
   *
   * and till the current is not null we are in a loop
   *
   *              1
   *  *          / \
   *  *         2   5
   *  *        / \   \
   *  *       3   4   6
   *  *              /
   *  *             7
   *
   *  we are initially at root1 what we are doing is we are doing is if we somehow point the node4th's right to 5 and
   *  we point node1's right to 2 and make node1's left to null we are good
   *
   *  exactly that we are doing,
   *
   *  initially we are at root
   *
   *  we take a current pointer and initially it is pointing to the root and keep looping till the current is not null
   *  what we are doing is
   *         we check if root.left != null if it's not null in our case we find root2 we then keep moving right till
   *         we find a root where it's right is null, root4 and then we point that root's right to the right node of
   *         current
   *
   *         node4's.right = node1.right( currentnode = node1, node1.right = node5), also we point node1.right to node1.left
   *         node1.right = node1.left(node2) and then we make node1.left = null
   *
   *         since now node1's right is point node2 we simply go right and continue the same process with node3 and node4
   *
   *         and we are done
   *
   */
  public void flattenWithMorrisTraversal(TreeNode root){
    TreeNode current = root;
    while(current != null){

      if(current.left != null){
        TreeNode previous = current.left;

        while(previous.right != null){
          previous = previous.right;
        }

        previous.right = current.right;
        current.right = current.left;
        current.left = null;
      }
      current = current.right;
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
    node1.right = node5;

    node2.left = node3;
    node2.right = node4;

    node5.right = node6;


    new FlattenBinaryTreeToLinkedList().flatten(node1);
  }
}
