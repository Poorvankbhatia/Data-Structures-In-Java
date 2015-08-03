package trees;


import java.util.Arrays;

public class BinaryTreeToBST {

    private static int[] inOrder = new int[5];
    private static int index = 0;

    public static void main(String[] args) {

        Node root = new Node(10);
        root.left = new Node(2);
        root.right = new Node(7);
        root.left.left = new Node(8);
        root.left.right = new Node(4);
        saveInOrder(root);

        Arrays.sort(inOrder);
        index = 0;

        System.out.println("In Order before ");
        Traversal.in_Order(root);
        System.out.println("\nIn Order after ");
        Traversal.in_Order(arrayToBST(root));


    }

    private static void saveInOrder(Node root) {

        if (root == null) {
            return;
        }

        saveInOrder(root.left);

        inOrder[index] = root.info;
        index++;

        saveInOrder(root.right);
    }

    private static Node arrayToBST(Node root) {

        if (root == null) {
            return null;
        }

        arrayToBST(root.left);

        root.info = inOrder[index];
        index++;

        arrayToBST(root.right);

        return root;

    }


}