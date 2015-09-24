package trees;

/**
 * Created by poorvank on 8/13/15.
 */
public class LCAMain {

    private static final int TWO_NODES_FOUND = 2;
    private static final int ONE_NODES_FOUND = 1;
    private static final int NO_NODES_FOUND = 0;

    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);
        Node x = null;
        if ((x = findLCA(root, root.right, root.right.right)) != null) {
            System.out.println(x.info);
        } else {
            System.out.println("Not found");
        }

    }

    private static int covers(Node root, Node p, Node q) {

        int value = NO_NODES_FOUND;

        if (root == null) {
            return value;
        }

        if (root == p || root == q) {
            value = 1;
        }

        value += covers(root.left, p, q);

        if (value == TWO_NODES_FOUND) {
            return value;
        }

        return value + covers(root.right, p, q);

    }


    private static Node findLCA(Node root, Node p, Node q) {

        if (q == p && (root.left == q || root.right == q)) {
            return root;
        }
        int nodesFromLeft = covers(root.left, p, q);
        if (nodesFromLeft == TWO_NODES_FOUND) {
            if (root.left == p || root.left == q) {
                return root.left;
            } else {
                return findLCA(root.left, p, q);
            }
        } else if (nodesFromLeft == ONE_NODES_FOUND) {
            if (root == p) {
                return p;
            } else if (root == q) {
                return q;
            }
        }


        int nodesFromRight = covers(root.right, p, q);
        if (nodesFromRight == TWO_NODES_FOUND) {
            if (root.right == p || root.right == q) {
                return root.right;
            } else {
                return findLCA(root.right, p, q);
            }
        } else if (nodesFromRight == ONE_NODES_FOUND) {
            if (root == p) {
                return p;
            } else if (root == q) {
                return q;
            }
        }

        if (nodesFromLeft == ONE_NODES_FOUND && nodesFromRight == ONE_NODES_FOUND) {
            return root;
        }

        return null;
    }

}
