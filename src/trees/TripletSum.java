package trees;

import java.util.ArrayList;
import java.util.List;

public class TripletSum {

    private static List<Integer> inOrder = new ArrayList<Integer>();

    public static void main(String[] args) {

        Node root = Input.treeInput();
        storeInOrder(root);
        if (!sumElements(root, 20)) {
            System.out.println("Not present");
        }


    }

    private static void storeInOrder(Node root) {

        if (root == null) {
            return;
        }

        storeInOrder(root.left);
        inOrder.add(root.info);
        storeInOrder(root.right);

    }

    private static boolean sumElements(Node root, int sum) {

        for (int i = 0; i < inOrder.size(); i++) {

            int l = i + 1;
            int r = inOrder.size() - 1;

            while (l < r) {

                if (inOrder.get(i) + inOrder.get(l) + inOrder.get(r) == sum) {

                    System.out.println("Elements are -> " + inOrder.get(i) + " " + inOrder.get(l) + " " + inOrder.get(r));
                    return true;

                } else if (inOrder.get(i) + inOrder.get(l) + inOrder.get(r) < sum) {
                    l++;
                } else {
                    r--;
                }

            }

        }

        return false;


    }

}


/*

create an auxiliary array and store Inorder traversal of BST in the array. 
The array will be sorted as Inorder traversal of BST always produces sorted data. 
Once we have the Inorder traversal, we can use method 2 of this post to find the triplet with sum equals to 0. 
This solution works in O(n^2) time, but requires O(n) auxiliary space.

 */