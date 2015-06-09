package Trees;

public class ChildrenSumProperty {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        if (fulfillsSumProperty(root)) {
            System.out.print("children Sum Property exists");
        } else {
            System.out.println("Nope");
        }

    }

    private static boolean fulfillsSumProperty(Node root) {

        if (root == null || (root.left==null && root.right==null)) {
            return true;
        }

        int leftValue = 0, rightValue = 0;

        if (root.left != null) {
            leftValue = root.left.info;
        }
        if (root.right != null) {
            rightValue = root.right.info;
        }

        return ((root.info == leftValue + rightValue) && fulfillsSumProperty(root.left) && fulfillsSumProperty(root.right));

    }

}

/*

O(n), we are doing a complete traversal of the tree.

 */