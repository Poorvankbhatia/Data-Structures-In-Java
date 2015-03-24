/*

Given a Binary Search Tree (BST), convert it to a Binary Tree such that every key of the original BST
 is changed to key plus sum of all greater keys in BST.

 */

package Trees;

public class SpecialBTFromBST {

    private static int sum = 0;

    public static void main(String[] args) {

        Node root = Input.treeInput();
        root = conversion(root);
        Traversal.in_Order(root);

    }

    private static Node conversion(Node root) {


        if (root == null) {
            return root;
        }

        conversion(root.right);

        sum += root.info;

        root.info = sum;

        conversion(root.left);

        return root;


    }

}

/*

Do reverse Inoorder traversal. Keep track of the sum of nodes visited so far. Let this sum be sum. 
For every node currently being visited, first add the key of this node to sum, i.e. sum = sum + node->key. 
Then change the key of current node to sum, i.e., node->key = sum.
When a BST is being traversed in reverse Inorder, for every key currently being visited, all keys
 that are already visited are all greater keys.

 */