package trees.tree;

public class SizeOfATree {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println("Size of a tree is " + size(root));

    }

    private static int size(Node root) {

        if (root == null) {
            return 0;
        } else {
            return size(root.left) + 1 + size(root.right);
        }

    }

}