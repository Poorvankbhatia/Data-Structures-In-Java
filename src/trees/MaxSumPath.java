package trees;

public class MaxSumPath {

    private static Node maxLeaf = null;
    private static int cSum = Integer.MIN_VALUE;

    public static void main(String[] args) {

        Node root = Input.treeInput();
        printPath(root, leaf(root, 0));

    }

    private static Node leaf(Node root, int rSum) {

        if (root == null) {
            return root;
        }

        int current_sum = rSum + root.info;

        if (root.right == null && root.left == null) {

            if (current_sum > cSum) {
                cSum = current_sum;
                maxLeaf = root;
            }


        }

        leaf(root.left, current_sum);
        leaf(root.right, current_sum);

        return maxLeaf;

    }

    private static void printPath(Node root, Node leaf) {

        if (root == leaf) {
            System.out.print(root.info + " ");
        } else if (root.info < leaf.info) {
            System.out.print(root.info + " ");
            printPath(root.right, leaf);
        } else if (root.info > leaf.info) {
            System.out.print(root.info + " ");
            printPath(root.left, leaf);
        }

    }

}