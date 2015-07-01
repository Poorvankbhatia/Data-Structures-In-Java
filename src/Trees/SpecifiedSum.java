/*

Root to leaf path sum equal to a given number
Given a binary tree and a number, return true if the tree has a root-to-leaf 
path such that adding up all the values along the path equals the given number. 
Return false if no such path can be found.

 */

package trees;

import java.util.Scanner;

/**
 * Created by poorvank on 4/28/15.
 */
public class SpecifiedSum {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        Scanner sc = new Scanner(System.in);
        int sum = sc.nextInt();

        System.out.println("Is sum = " + sum + " present " + checkSum(root, sum));

    }

    private static boolean checkSum(Node root, int sum) {

        if (root == null) {
            return (sum == 0);
        } else {

            boolean flag = false;

            int subSum = sum - root.info;
            if (subSum == 0 && root.left == null && root.right == null) {
                return true;
            }

            if (root.left != null) {
                flag = flag || checkSum(root.left, subSum);
            }
            if (root.right != null) {
                flag = flag || checkSum(root.right, subSum);
            }

            return flag;

        }

    }

}


/*

Algorithm:
Recursively check if left or right child has path sum equal to ( number – value at current node)

True || false == TRUE

 */