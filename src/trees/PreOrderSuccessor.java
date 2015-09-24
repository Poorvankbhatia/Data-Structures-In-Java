package trees;

/**
 * Created by poorvank on 8/18/15.
 */
public class PreOrderSuccessor {

    private static boolean find = false;

    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);

        Node x;
        if ((x = findSuccessorPreOrder(3, root)) != null) {
            System.out.println(x.info);
        }

    }

    private static Node findSuccessorPreOrder(int key, Node root) {

        if (root == null) {
            return root;
        }

        if (root.info == key) {

            find = true;

            if (root.left != null) {
                return root.left;
            } else if (root.right != null) {
                return root.right;
            }
            return null;

        } else {

            Node successor;

            successor = findSuccessorPreOrder(key, root.left);

            if (successor != null) {
                return successor;
            }

            if (find && successor == null) {
                return root.right;
            } else if (!find) {
                return findSuccessorPreOrder(key, root.right);
            } else {
                return null;
            }


        }
    }

}
