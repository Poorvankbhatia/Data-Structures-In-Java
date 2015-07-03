package treesPrograms;

import java.util.ArrayList;

public class RootToLeaf {

    private static int maxSum = Integer.MAX_VALUE;
    private static ArrayList<Integer> finalList = new ArrayList<>();

    public static void main(String[] args) {

        Node root = new Node(8);
        root.left = new Node(4);
        root.left.left = new Node(3);
        root.left.right = new Node(1);
        root.right = new Node(5);
        root.right.right = new Node(11);
        ArrayList<Integer> list = new ArrayList<Integer>();
        path(root, list, 0);
        System.out.println("Final list is - " + finalList.toString() + " and minimum sum is " + maxSum);

    }

    private static void path(Node root, ArrayList<Integer> list, int s) {

        if (root == null) {
            return;
        } else {
            list.add(root.info);
            s = s + root.info;
        }

        if ((root.left == null && root.right == null)) {
            System.out.println(list);
            if (maxSum > s) {
                maxSum = s;
                finalList = new ArrayList<>(list);
            }
            return;
        }

        path(root.left, new ArrayList<Integer>(list), s);
        path(root.right, new ArrayList<Integer>(list), s);

    }

}