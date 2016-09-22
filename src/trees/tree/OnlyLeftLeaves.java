/*

Number of Nodes in binary tree having only left child

 */

package trees.tree;

/**
 * Created by poorvank on 12/27/15.
 */
public class OnlyLeftLeaves {

    public static void main(String[] args) {

        Node root = new Node(10);
        root.left = new Node(8);
        root.left.left = new Node(3);
        //root.left.right = new Node(5);
        root.right = new Node(2);
        root.right.left = new Node(2);

        System.out.println(leftCount(root));

    }

    private static int leftCount(Node root) {

        if (root == null || (root.left == null && root.right == null)) {
            return 0;
        } else if (root.left != null && root.right == null) {
            return 1 + leftCount(root.left);
        } else {
            return leftCount(root.left) + leftCount(root.right);
        }

    }

}
