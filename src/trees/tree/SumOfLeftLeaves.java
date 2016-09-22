package trees.tree;

public class SumOfLeftLeaves {

    private static int sum = 0;

    public static void main(String[] args) {

        Node root = Input.treeInput();
        leftLeafSum(root);
        System.out.println("Left leaf sum - " + sum);

    }

    private static void leftLeafSum(Node root) {


        if (root != null) {

            if (isLeaf(root.left)) {
                sum += root.left.info;
            } else {
                leftLeafSum(root.left);
            }

            leftLeafSum(root.right);

        }

    }

    private static boolean isLeaf(Node root) {

        if (root == null) {
            return false;
        }

        if (root.left == null && root.right == null) {
            return true;
        }

        return false;
    }

}