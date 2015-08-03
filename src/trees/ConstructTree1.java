//Construct a tree from in order and pre order
package trees;

import java.util.Scanner;

public class ConstructTree1 {

    private static int preIndex = 0;

    public static void main(String[] args) {

        int[] preOrder = new int[7];
        System.out.println("Enter pre order - ");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 7; i++) {
            preOrder[i] = sc.nextInt();
        }
        int[] inOrder = new int[7];
        System.out.println("Enter in order - ");
        for (int i = 0; i < 7; i++) {
            inOrder[i] = sc.nextInt();
        }


        System.out.println("Post order is");
        Traversal.post_Order(construct(0, 6, inOrder, preOrder));


    }

    private static Node construct(int start, int end, int[] inOrder, int[] preOrder) {


        if (start > end) {
            return null;
        }

        Node treeNode = new Node(preOrder[preIndex++]);

        if (start == end) {
            return treeNode;
        }

        int index = search(inOrder, treeNode.info, start, end);

        treeNode.left = construct(start, index - 1, inOrder, preOrder);
        treeNode.right = construct(index + 1, end, inOrder, preOrder);

        return treeNode;


    }

    private static int search(int[] inOrder, int val, int start, int end) {

        for (int i = start; i <= end; i++) {
            if (val == inOrder[i]) {
                return i;
            }
        }
        return -1;

    }


} 

/*

O(n^2). Worst case occurs when tree is left skewed. 
Example Preorder and Inorder traversals for worst case are {A, B, C, D} and {D, C, B, A}.
 */