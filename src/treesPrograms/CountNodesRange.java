/*

Count BST nodes that lie in a given range
Given a Binary Search Tree (BST) and a range, count number of nodes that lie in the given range.

Examples:

Input:
        10
      /    \
    5       50
   /       /  \
 1       40   100
Range: [5, 45]

Output:  3
There are three nodes in range, 5, 10 and 40

 */

package treesPrograms;

/**
 * Created by poorvank on 7/3/15.
 */
public class CountNodesRange {

    public static void main(String[] args) {

        Node root = new Node(10);
        root.left = new Node(5);
        root.left.left = new Node(1);
        root.right = new Node(50);
        root.right.right = new Node(100);
        root.right.left = new Node(40);

        System.out.println("\n" + countNodes(root, 5, 45));


    }

    private static int countNodes(Node root, int low, int high) {

        if (root == null) {
            return 0;
        }

        if (root.info >= low && root.info <= high) {
            System.out.print(root.info + " ");
            return 1 + (countNodes(root.left, low, high) + countNodes(root.right, low, high));
        }

        if (root.info > high) {
            return countNodes(root.left, low, high);
        } else if (root.info < low) {
            return countNodes(root.right, low, high);
        }

        return 0;

    }

}


/*

The idea is to traverse the given binary search tree starting from root.
For every node being visited, check if this node lies in range, 
if yes, then add 1 to result and recur for both of its children. If current node is smaller than low value of range,
then recur for right child, else recur for left child.

 */