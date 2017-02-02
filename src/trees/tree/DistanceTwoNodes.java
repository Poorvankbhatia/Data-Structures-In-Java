/*

Find the distance between two keys in a binary tree, no parent pointers are given. 
Distance between two nodes is the minimum number of edges to be traversed to reach one node from other.

 */
package trees.tree;

/**
 * Created by poorvank on 6/15/15.
 */
public class DistanceTwoNodes {

    private static int distance = 0;
    private static int d1 = -1, d2 = -1;


    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);

        int k1 = 45, k2 = 3;

        //level of root is 0
        Node lca = findDistance(root, 45, 3, 0);

        if (d1 != -1 && d2 != -1) {
            System.out.println(distance);
        }


        // If n2 is ancestor of n1, consider n2 as root and find level
        // of n1 in subtree rooted with n2
        else if (d1 == -1) {
            System.out.println(findLevel(lca, k1, 0));
        }
        // If n1 is ancestor of n2, consider n1 as root and find level
        // of n2 in subtree rooted with n1
        else if (d2 == -1) {
            System.out.println(findLevel(lca, k2, 0));
        } else {
            System.out.println(-1);
        }


    }

    private static Node findDistance(Node root, int k1, int k2, int level) {

        if (root == null) {
            return null;
        }

        if (root.info == k1) {
            d1 = level;
            return root;
        }

        if (root.info == k2) {
            d2 = level;
            return root;
        }

        Node left = findDistance(root.left, k1, k2, level + 1);
        Node right = findDistance(root.right, k1, k2, level + 1);

        if (left != null && right != null) {

            distance = d1 + d2 - (2 * level);
            return root;

        }

        return (left != null) ? left : right;

    }

    private static int findLevel(Node root, int key, int level) {

        //if root not found
        if (root == null) {
            return -1;
        }

        if (root.info == key) {
            return level;
        }


        int l = findLevel(root.left, key, level + 1);
        return l != -1 ? l : findLevel(root.right, key, level + 1);

    }

}


/*

The distance between two nodes can be obtained in terms of lowest common ancestor. Following is the formula.

Dist(n1, n2) = Dist(root, n1) + Dist(root, n2) - 2*Dist(root, lca)
'n1' and 'n2' are the two given keys
'root' is root of given Binary Tree.
'lca' is lowest common ancestor of n1 and n2
Dist(n1, n2) is the distance between n1 and n2.

 */