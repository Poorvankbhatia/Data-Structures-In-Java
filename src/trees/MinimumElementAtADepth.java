/*
Given a binary tree. Find the minimum element at a given depth
 */

package trees;

/**
 * Created by poorvank on 8/4/15.
 */
public class MinimumElementAtADepth {

    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(10);
        root.right = new Node(45);
        root.right.right = new Node(46);
        root.right.left = new Node(34);

        System.out.println(minimum(root, 1));

    }

    private static int minimum(Node node, int depth) {

        if (node == null) {
            return Integer.MAX_VALUE;
        }

        if (depth == 0) {
            return node.info;
        }

        int left = minimum(node.left, depth - 1);
        int right = minimum(node.right, depth - 1);

        return Math.min(left, right);

    }

}
