package Trees;

public class FoldableTree {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        if (isFoldable(root)) {
            System.out.println("Foldable");
        } else {
            System.out.println("Nope");
        }

    }

    private static boolean isFoldable(Node root) {

        if (root == null) {
            return true;
        }

        return isFoldUtil(root.right, root.left);

    }

    private static boolean isFoldUtil(Node node1, Node node2) {

        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        return (isFoldUtil(node1.left, node2.right) && isFoldUtil(node1.right, node2.left));
    }

}