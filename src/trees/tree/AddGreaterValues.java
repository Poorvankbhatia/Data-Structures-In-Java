/*

Given a Binary Search Tree (BST),
 modify it so that all greater values in the given BST are added to every node. For example, consider the following BST.

              50
           /      \
         30        70
        /   \      /  \
      20    40    60   80 

The above tree should be modified to following 

              260
           /      \
         330        150
        /   \       /  \
      350   300    210   80

 */

package trees.tree;

/**
 * Created by poorvank on 6/8/15.
 */
public class AddGreaterValues {

    private static int sum = 0;

    public static void main(String[] args) {

        Node root = Input.treeInput();
        root = new AddGreaterValues().reverseInorder(root);
        Traversal.in_Order(root);

    }

    private Node reverseInorder(Node root) {

        if (root == null) {
            return null;
        }
        reverseInorder(root.right);
        sum = sum + root.info;
        root.info = sum;
        reverseInorder(root.left);
        return root;

    }

}


/*

We can do it using a single traversal. The idea is to use following BST property. 
If we do reverse Inorder traversal of BST, we get all nodes in decreasing order. 
We do reverse Inorder traversal and keep track of the sum of all nodes visited so far, we add this sum to every node.

 */