/*

Given Inorder Traversal of a Special Binary Tree in which key of every node is greater than keys
 in left and right children, construct the Binary Tree and return root.

 */

package treesPrograms;

public class SpecialBT1 {

    public static void main(String[] args) {

        int[] inOrder = new int[]{5, 10, 40, 30, 28};
        Traversal.pre_Order(constructTree(inOrder, 0, 4));


    }

    private static Node constructTree(int[] inOrder, int start, int end) {

        if (start > end) {
            return null;
        }

        int index = maxIndex(inOrder, start, end);
        Node root = new Node(inOrder[index]);

        root.left = constructTree(inOrder, start, index - 1);
        root.right = constructTree(inOrder, index + 1, end);

        return root;


    }

    private static int maxIndex(int[] arr, int start, int end) {

        int max = Integer.MIN_VALUE;
        int index = start;

        for (int i = start; i <= end; i++) {
            if (arr[i] > max) {
                max = arr[i];
                index = i;
            }
        }

        return index;

    }

}


/*
1) Find index of the maximum element in array. The maximum element must be root of Binary Tree.
2) Create a new tree node ‘root’ with the data as the maximum value found in step 1.
3) Call buildTree for elements before the maximum element and make the built tree as left subtree of ‘root’.
5) Call buildTree for elements after the maximum element and make the built tree as right subtree of ‘root’.
6) return ‘root’.
 */