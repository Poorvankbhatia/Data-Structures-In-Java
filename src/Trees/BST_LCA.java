package Trees;

import java.util.Scanner;

public class BST_LCA {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println("Enter node 1 : ");
        Scanner sc = new Scanner(System.in);
        int n1 = sc.nextInt();
        System.out.println("Enter node 2 : ");
        int n2 = sc.nextInt();
        System.out.print("LCA Of two nodes is - " + returnLCA(root, n1, n2));

    }

    private static int returnLCA(Node root, int n1, int n2) {

        if (root.info > n1 && root.info <= n2) {
            return root.info;
        } else if (root.info > n1 && root.info > n2) {
            return returnLCA(root.left, n1, n2);
        } else if (root.info < n1 && root.info < n2) {
            return returnLCA(root.right, n1, n2);
        } else {
            return -1;
        }

    }

}