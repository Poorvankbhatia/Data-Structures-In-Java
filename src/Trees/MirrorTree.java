package Trees;

public class MirrorTree {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println("Inorder traversal before ");
        Traversal.in_Order(root);
        System.out.println("\nInorder traversal after ");
        root = mirror(root);
        Traversal.in_Order(root);

    }

    private static Node mirror(Node root) {

        if (root != null) {

            root.left = mirror(root.left);
            root.right = mirror(root.right);

            Node tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            return root;
        }
        return null;

    }

}