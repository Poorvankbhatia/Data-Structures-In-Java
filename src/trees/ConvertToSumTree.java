/*


Convert a given tree to its Sum Tree
Given a Binary Tree where each node has positive and negative values. Convert this to a tree where each node contains the sum of the left and right sub trees in the original tree. The values of leaf nodes are changed to 0.

For example, the following tree

                  10
               /      \
	         -2        6
           /   \      /  \
	      8     -4    7    5
should be changed to

                 20(4-2+12+6)
               /      \
	        4(8-4)      12(7+5)
           /   \      /  \
	      0      0    0    0


 */

package trees;

public class ConvertToSumTree {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        Traversal.pre_Order(root);
        convert(root);
        System.out.println();
        Traversal.pre_Order(root);

    }

    private static int convert(Node root) {

        if (root == null) {
            return 0;
        }

        int old_val = root.info;

        root.info = convert(root.left) + convert(root.right);

        return old_val + root.info;

    }

}