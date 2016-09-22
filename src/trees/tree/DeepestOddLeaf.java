/*

Find depth of the deepest odd level leaf node

 */

package trees.tree;

/**
 * Created by poorvank on 6/11/15.
 */
public class DeepestOddLeaf {

    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);

        System.out.println(deepestLevel(root, 1));

    }

    private static int deepestLevel(Node root, int level) {

        if (root == null) {
            return 0;
        }

        if ((root.right == null && root.left == null) && level % 2 != 0) {
            return level;
        }

        return Math.max(deepestLevel(root.left, level + 1), deepestLevel(root.right, level + 1));

    }

}


/*


The idea is to recursively traverse the given binary tree and while traversing, 
maintain a variable “level” which will store the current node’s level in the tree. 
If current node is leaf then check “level” is odd or not. If level is odd then return it. 
If current node is not leaf, then recursively find maximum depth in left and right subtrees,
 and return maximum of the two depths.

 */