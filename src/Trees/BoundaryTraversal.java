package Trees;

public class BoundaryTraversal {

    public static void main(String[] args) {

        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(8);
        root.left.left = new Node(2);
        root.left.right = new Node(20);

        System.out.print(root.info + " ");
        leftBoundary(root.left);
        printLeaves(root);
        rightBoundary(root.right);

    }


    //print only left part
    public static void leftBoundary(Node root) {

        if (root != null) {

            if (root.left != null) {
                System.out.print(root.info + " ");
                leftBoundary(root.left);
            } else if (root.right != null) {
                System.out.print(root.info + " ");
                leftBoundary(root.right);
            }

        }
    }

    public static void printLeaves(Node root) {

        if (root != null) {

            printLeaves(root.left);

            if (root.right == null && root.left == null) {
                System.out.print(root.info + " ");
            }

            printLeaves(root.right);

        }

    }

    public static void rightBoundary(Node root) {

        if (root != null) {

            if (root.right != null) {
                rightBoundary(root.right);
                System.out.print(root.info + " ");
            } else if (root.left != null) {
                rightBoundary(root.left);
                System.out.print(root.info + " ");
            }

        }

    }

}

/*

We break the problem in 3 parts:
1. Print the left boundary in top-down manner.
2. Print all leaf nodes from left to right, which can again be sub-divided into two sub-parts:
…..2.1 Print all leaf nodes of left sub-tree from left to right.
…..2.2 Print all leaf nodes of right subtree from left to right.
3. Print the right boundary in bottom-up manner.


 */