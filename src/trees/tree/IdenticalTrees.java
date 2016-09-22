package trees.tree;

public class IdenticalTrees {

    public static void main(String[] args) {

        Node root1 = Input.treeInput();
        Node root2 = Input.treeInput();
        if (isIdentical(root1, root2)) {
            System.out.println("Identical");
        } else {
            System.out.println("Nope");
        }

    }

    public static boolean isIdentical(Node root1, Node root2) {

        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 != null && root2 != null) {
            return (root1.info == root2.info && isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right));
        } else {
            return false;
        }
    }

}