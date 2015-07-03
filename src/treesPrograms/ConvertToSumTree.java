package treesPrograms;

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