package Trees;

import java.util.ArrayList;
import java.util.Scanner;

public class PathSum {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number to check - ");
        int a = sc.nextInt();
        sum(root, new ArrayList<Integer>(), a, 0);


    }

    private static void sum(Node root, ArrayList<Integer> list, int check, int currentSum) {


        if (root == null) {
            return;
        }

        currentSum += root.info;

        list.add(root.info);


        if (root.left == null && root.right == null) {
            if (currentSum == check) {
                System.out.println("Sum found - " + list.toString());
            }
            return;
        }


        if (root.left != null) {
            sum(root.left, new ArrayList<Integer>(list), check, currentSum);
        }
        if (root.right != null) {
            sum(root.right, new ArrayList<Integer>(list), check, currentSum);
        }


    }

}