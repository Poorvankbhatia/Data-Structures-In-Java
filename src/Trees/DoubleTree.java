package Trees;

public class DoubleTree {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println("In order before - ");
        Traversal.in_Order(root);
        System.out.println("In order after - ");
        Traversal.in_Order(conversionDT(root));


    }

    private static Node conversionDT(Node root) {

        if (root == null) {
            return null;
        }

        Node tmp = null;


        if (root.right != null) {
            root.right = conversionDT(root.right);
        }
        if (root.left != null) {
            root.left = conversionDT(root.left);
        }
        tmp = new Node(root.info);
        tmp.left = root.left;
        root.left = tmp;


        return root;

    }

}