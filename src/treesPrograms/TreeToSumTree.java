package treesPrograms;

public class TreeToSumTree {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.print("Before conversion\n");
        Traversal.in_Order(root);
        System.out.print("\nAfter conversion\n");
        Traversal.in_Order(convertToSumTree(root));


    }

    private static Node convertToSumTree(Node root) {

        if ((root == null) || (root.left == null && root.right == null)) {
            return root;
        }

        Node lRoot = convertToSumTree(root.left);
        Node rRoot = convertToSumTree(root.right);

        int lvalue = 0, rValue = 0;

        if (root.left != null) {
            lvalue = lRoot.info;
        }

        if (root.right != null) {
            rValue = rRoot.info;
        }

        int diff = lvalue + rValue - root.info;

        if (diff > 0) {
            root.info = root.info + diff;
        }
        if (diff < 0) {
            root = increment(root, diff);
        }
        return root;
    }


    private static Node increment(Node root, int diff) {

        if (root.left != null) {
            root.left.info += Math.abs(diff);
            increment(root.left, diff);
        } else if (root.right != null) {
            root.right.info += Math.abs(diff);
            increment(root.right, diff);
        }
        return root;
    }

}

/*
 
 O(n^2), Worst case complexity is for a skewed tree such that nodes are in decreasing order from root to leaf.

 */