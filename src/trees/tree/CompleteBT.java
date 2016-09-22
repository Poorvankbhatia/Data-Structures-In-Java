/*


Given a Binary Tree, write a function to check whether the given Binary Tree is Complete Binary Tree or not.

A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all nodes are as far left as possible. See following examples.

The following trees are examples of Complete Binary Trees
    1
  /   \
 2     3
  
       1
    /    \
   2       3
  /
 4

       1
    /    \
   2      3
  /  \    /
 4    5  6
The following trees are examples of Non-Complete Binary Trees
    1
      \
       3
  
       1
    /    \
   2       3
    \     /  \   
     4   5    6

       1
    /    \
   2      3
         /  \
        4    5
Source: Write an algorithm to check if a tree is complete binary tree or not

 */

package trees.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 7/9/15.
 */
public class CompleteBT {

    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(2);
        root.right = new Node(45);
        root.right.left = new Node(34);

        if (isCompleteBT(root)) {
            System.out.println("Is complete BT");
        } else {
            System.out.println("Nope");
        }


    }

    /**
     * *
     *
     * @param root
     * @return
     */
    private static boolean isCompleteBT(Node root) {


        if (root == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();

        //Flag to check when a non full node is encountered
        boolean flag = false;

        queue.add(root);

        while (!queue.isEmpty()) {


            Node node = queue.remove();


            if (node.left != null) {

                if (flag) {
                    return false;
                }

                queue.add(node.left);

            } else {

                flag = true;

            }


            if (node.right != null) {


                if (flag) {
                    return false;
                }

                queue.add(node.right);

            } else {
                flag = true;
            }

        }

        return true;

    }

}


/*

level order traversal post can be easily modified to check whether a tree is Complete or not.
 To understand the approach, let us first define a term ‘Full Node’.
 A node is ‘Full Node’ if both left and right children are not empty (or not NULL).
The approach is to do a level order traversal starting from root. In the traversal, once a node is found which is NOT a Full Node, all the following nodes must be leaf nodes.
Also, one more thing needs to be checked to handle the below case: If a node has empty left child, then the right child must be empty.

    1
  /   \
 2     3
  \
   4
   
Time Complexity: O(n) where n is the number of nodes in given Binary Tree

Auxiliary Space: O(n) for queue.

 */