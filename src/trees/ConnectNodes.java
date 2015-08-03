/*

Write a function to connect all the adjacent nodes at the same level in a binary tree

 */

package trees;

class CTreeNode {

    Integer info;
    CTreeNode left;
    CTreeNode right;
    CTreeNode nextRight = null;

    public CTreeNode(int n) {

        this(n, null, null);

    }

    public CTreeNode(int n, CTreeNode left, CTreeNode right) {

        info = n;
        this.left = left;
        this.right = right;

    }

}

class ConnectSameLevel {

    public static void connect(CTreeNode root) {

        root.nextRight = null;
        connectRecur(root);

    }

    private static void connectRecur(CTreeNode root) {

        if (root == null) {
            return;
        }

        if (root.nextRight != null) {
            connectRecur(root.nextRight);
        }

        if (root.left != null) {

            if (root.right != null) {
                root.left.nextRight = root.right;
                root.right.nextRight = getNextRight(root);
            } else {
                root.left.nextRight = getNextRight(root);
            }

            connectRecur(root.left);
        } else if (root.right != null) {

            root.right.nextRight = getNextRight(root);
            connectRecur(root.right);

        }
        /*else {
            connectRecur(getNextRight(root));
        }*/

    }

    private static CTreeNode getNextRight(CTreeNode node) {

        CTreeNode temp = node.nextRight;

        while (temp != null) {

            if (temp.left != null) {
                return temp.left;
            } else if (temp.right != null) {
                return temp.right;
            }
            temp = temp.nextRight;

        }

        return null;

    }

}

public class ConnectNodes {

    public static void main(String[] args) {

        CTreeNode root = new CTreeNode(1);
        root.left = new CTreeNode(2);
        root.right = new CTreeNode(3);
        root.right.left = new CTreeNode(4);
        root.right.right = new CTreeNode(5);

        ConnectSameLevel.connect(root);


        System.out.println(root.right.left.nextRight.info);

    }

}
