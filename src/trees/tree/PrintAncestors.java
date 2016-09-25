/*

Print Ancestors of a given node in Binary Tree
Given a Binary Tree and a key, write a function that prints all the ancestors of the key in the given binary tree.

For example, if the given tree is following Binary Tree and key is 7, then your function should print 4, 2 and 1.


              1
            /   \
          2      3
        /  \
      4     5
     /
    7

 */

package trees.tree;

public class PrintAncestors {

    private static int immediateAncestor = -1;

    public static void main(String[] args) {

        Node root = Input.treeInput();
        if (!ancestor(root, 9)) {
            System.out.println("No ancestor");
        }


        System.out.println("\nimmediate ancestor - " + immediateAncestor);

    }

    private static boolean ancestor(Node root, int target) {

        if (root == null) {
            return false;
        }

        if (root.info == target) {
            return true;
        }

        if (ancestor(root.left, target) || ancestor(root.right, target)) {
            if (immediateAncestor == -1) {
                immediateAncestor = root.info;
            }
            System.out.print(root.info + " ");
            return true;
        }

        return false;

    }

}