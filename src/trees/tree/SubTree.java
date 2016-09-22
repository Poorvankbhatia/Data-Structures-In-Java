/*

Check if a binary tree is subtree of another binary tree
Given two binary trees, check if the first tree is subtree of the second one.
A subtree of a tree T is a tree S consisting of a node in T and all of its descendants in T.
The subtree corresponding to the root node is the entire tree; the subtree corresponding to any other node is called a proper subtree.

For example, in the following case, tree S is a subtree of tree T.

        Tree 2
          10
        /    \
      4       6
       \
        30


        Tree 1
              26
            /   \
          10     3
        /    \     \
      4       6      3
       \
        30


 */

package trees.tree;

public class SubTree {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        Node subRoot = Input.treeInput();

        if (isSubTree(root, subRoot)) {
            System.out.println("Subtree");
        } else {
            System.out.println("Nope");
        }

    }

    public static boolean isSubTree(Node root, Node subRoot) {

        if (subRoot == null) {
            return true;
        }

        if (root == null) {
            return false;
        }

        if (isIdentical(root, subRoot)) {
            return true;
        }

        return (isIdentical(root.left, subRoot) || isIdentical(root.right, subRoot));


    }

    public static boolean isIdentical(Node root1, Node root2) {

        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }


        return (root1.info == root2.info && isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right));


    }


}

/*


Time worst case complexity of above solution is O(mn) where m and n are number of nodes in given two trees.tra

 */