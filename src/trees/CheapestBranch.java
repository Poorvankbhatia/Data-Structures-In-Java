/*

Return branch with minimum sum

 */

package trees;

/**
 * Created by poorvank on 6/25/15.
 */
public class CheapestBranch {

    public static void main(String[] args) {

        Node root = new Node(8);
        root.left = new Node(4);
        root.left.left = new Node(3);
        root.left.right = new Node(1);
        root.right = new Node(5);
        root.right.right = new Node(11);

        System.out.println(sumBranch(root));

    }

    private static int sumBranch(Node root) {

        if (root == null) {
            return Integer.MAX_VALUE;
        }

        if (root.left == null && root.right == null) {
            return root.info;
        }

        int r = sumBranch(root.right);
        int l = sumBranch(root.left);

        return root.info + Math.min(l, r);
    }

}
