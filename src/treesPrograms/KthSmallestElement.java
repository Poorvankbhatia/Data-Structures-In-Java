/*

Find k-th smallest element in BST (Order Statistics in BST)
Given root of binary search tree and K as input, find K-th smallest element in BST.



 */
package treesPrograms;

import treesPrograms.Node;
import treesPrograms.Stack;

/**
 * Created by poorvank on 6/22/15.
 */
public class KthSmallestElement {

    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(35);
        root.right.right = new Node(46);

        int k = 4;
        System.out.print(smallestElement(root, 2));

    }

    private static int smallestElement(Node root, int k) {

        Stack stack = new Stack();
        Node crawl = root;

        while (crawl != null) {
            stack.push(crawl);
            crawl = crawl.left;
        }

        while (!stack.isEmpty() && (crawl = stack.pop()) != null) {

            if (k - 1 == 0) {
                break;
            } else {
                k = k - 1;
            }

            if (crawl.right != null) {

                crawl = crawl.right;

                while (crawl != null) {
                    stack.push(crawl);
                    crawl = crawl.left;
                }

            }

        }

        return crawl.info;

    }

}


/*

Method 2: Augmented  Tree Data Structure.

The idea is to maintain rank of each node. We can keep track of elements in a subtree of any node while 
building the tree. Since we need K-th smallest element, we can maintain number of elements of left subtree in every node.

Assume that the root is having N nodes in its left subtree. If K = N + 1, root is K-th node. If K < N, 
we will continue our search (recursion) for the Kth smallest element in the left subtree of root. If K > N + 1, 
we continue our search in the right subtree for the (K – N – 1)-th smallest element. Note that we need the count of 
elements in left subtree only.

Time complexity: O(h) where h is height of tree.

Algorithm:

start:
if K = root.leftElement + 1
   root node is the K th node.
   goto stop
else if K > root.leftElements
   K = K - (root.leftElements + 1)
   root = root.right
   goto start
else
   root = root.left
   goto srart

stop:

 */