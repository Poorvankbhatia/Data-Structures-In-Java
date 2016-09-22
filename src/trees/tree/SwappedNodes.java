/*

Two nodes of a BST are swapped, correct the BST
Two of the nodes of a Binary Search Tree (BST) are swapped. Fix (or correct) the BST.

Input Tree:
         10
        /  \
       5    8
      / \
     2   20

In the above tree, nodes 20 and 8 must be swapped to fix the tree.
Following is the output tree
         10
        /  \
       5    20
      / \
     2   8

 */

package trees.tree;

/**
 * Created by poorvank on 5/6/15.
 */
public class SwappedNodes {

    public static Node root = null, first = null, middle = null, last = null, previous = null;

    public static void main(String[] args) {

        root = new Node(5);
        root.left = new Node(2);
        root.left.right = new Node(9);
        root.right = new Node(7);
        root.right.right = new Node(4);

        System.out.println("Before in order sequence");
        Traversal.in_Order(root);
        root = correctTree(root);
        System.out.println("\nAfter correction in order sequence");
        Traversal.in_Order(root);


    }

    private static Node correctTree(Node root) {

        correctTreeUtil(root);


        if (first != null && last != null) {
            swap(first, last);
        } else if (first != null && middle != null) {
            swap(first, middle);
        }

        return root;

    }

    private static void swap(Node n1, Node n2) {

        int temp = n1.info;
        n1.info = n2.info;
        n2.info = temp;

    }

    private static void correctTreeUtil(Node root) {

        if (root != null) {

            correctTreeUtil(root.left);

            if (previous != null && root.info < previous.info) {

                if (first == null) {
                    first = previous;
                    middle = root;
                    System.out.println();
                } else {
                    last = root;
                }

            }

            previous = root;
            correctTreeUtil(root.right);

        }

    }

}


/*

We can solve this in O(n) time and with a single traversal of the given BST. Since inorder traversal of BST 
is always a sorted array, the problem can be reduced to a problem where two elements of a sorted array are swapped. 
There are two cases that we need to handle:

1. The swapped nodes are not adjacent in the inorder traversal of the BST.

 For example, Nodes 5 and 25 are swapped in {3 5 7 8 10 15 20 25}. 
 The inorder traversal of the given tree is 3 25 7 8 10 15 20 5 
If we observe carefully, during inorder traversal, we find node 7 is smaller than the previous visited node 25. 
Here save the context of node 25 (previous node). Again, we find that node 5 is smaller than the previous node 20. 
This time, we save the context of node 5 ( current node ). Finally swap the two node’s values.

2. The swapped nodes are adjacent in the inorder traversal of BST.

  For example, Nodes 7 and 8 are swapped in {3 5 7 8 10 15 20 25}. 
  The inorder traversal of the given tree is 3 5 8 7 10 15 20 25 
Unlike case #1, here only one point exists where a node value is smaller than previous node value. e.g. 
node 7 is smaller than node 8.

How to Solve? We will maintain three pointers, first, middle and last. When we find the first point where 
current node value is smaller than previous node value, we update the first with the previous node & middle 
with the current node. When we find the second point where current node value is smaller than previous node value, 
we update the last with the current node. In case #2, we will never find the second point. 
So, last pointer will not be updated. After processing, 
if the last node value is null, then two swapped nodes of BST are adjacent.

 */