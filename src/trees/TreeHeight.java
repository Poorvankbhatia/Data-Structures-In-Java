package trees;

public class TreeHeight {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println("Height of root is - " + height(root));

    }

    public static int height(Node root) {

        if (root == null) {
            return 0;
        } else {
            return maxOfTwoNo(height(root.left), height(root.right)) + 1;
        }

    }

    private static int maxOfTwoNo(int a, int b) {

        return a > b ? a : b;
    }
}