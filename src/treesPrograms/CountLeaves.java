package treesPrograms;

public class CountLeaves {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println("Number of leaves in tree are - " + count(root));

    }

    private static int count(Node root) {

        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        return count(root.left) + count(root.right);

    }

}