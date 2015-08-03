package trees;

import java.util.Scanner;

public class ArrayToBST {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of integers");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter numbers");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Traversal.pre_Order(createTree(arr, 0, n - 1));

    }

    private static Node createTree(int[] arr, int start, int end) {

        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;

        Node root = new Node(arr[mid]);

        root.left = createTree(arr, start, mid - 1);

        root.right = createTree(arr, mid + 1, end);

        return root;

    }

}