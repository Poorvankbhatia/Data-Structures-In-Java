package trees;

public class SumOfLeftLeaves {

    private static int sum = 0;

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println("Left leaf sum - " + leftLeafSum(root));

    }

    private static int leftLeafSum(Node root) {


        if (root != null) {

            if (isLeaf(root.left)) {
                sum += root.left.info;
            } else {
                leftLeafSum(root.left);
            }

            leftLeafSum(root.right);

        }

        return sum;


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