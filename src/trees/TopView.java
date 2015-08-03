package trees;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class TopView {

    public static void main(String[] args) {

        customNode2 root = customInput2.treeInput();
        top(root);
    }

    private static void top(customNode2 root) {

        if (root == null) {
            return;
        }

        HashSet<Integer> set = new HashSet<Integer>();

        Queue<customNode2> queue = new LinkedList<customNode2>();

        queue.add(root);

        while (!queue.isEmpty()) {

            customNode2 current = queue.remove();
            int hd = current.hd;

            if (!set.contains(hd)) {
                System.out.print(current.info + " ");
                set.add(hd);
            }

            if (current.left != null) {
                current.left.hd = hd + 1;
                queue.add(current.left);
            }
            if (current.right != null) {
                current.right.hd = hd - 1;
                queue.add(current.right);
            }

        }

    }

}

/*

We do a level order traversal so that the topmost node at a horizontal node 
is visited before any other node of same horizontal distance below it. 
Hashing is used to check if a node at given horizontal distance is seen or not.

 */