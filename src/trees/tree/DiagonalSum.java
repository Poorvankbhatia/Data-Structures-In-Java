/*

See Images
 */

package trees.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class customNode1 {

    public int info;
    public customNode1 left;
    public customNode1 right;
    public int vd;

    public customNode1(int info, int vd) {
        this(info, null, null, vd);
    }

    public customNode1(int info, customNode1 left, customNode1 right, int vd) {
        this.info = info;
        this.left = left;
        this.right = right;
        this.vd = vd;
    }

}

class customTree1 {

    public customNode1 root;

    public customTree1() {
        root = null;
    }

    public static customNode1 search(int n, customNode1 root) {

        if (root.info == n) {
            return root;
        }

        if (root.info < n) {
            return search(n, root.right);
        }

        if (root.info > n) {
            return search(n, root.left);
        }

        return null;

    }

    public customNode1 customInsert(int info, customNode1 root) {
        if (root == null) {
            root = new customNode1(info, 0);
        } else if (info < root.info) {
            root.left = customInsert(info, root.left);
        } else if (info > root.info) {
            root.right = customInsert(info, root.right);
        } else {
            System.out.println("Node already present");
        }
        return root;
    }

}

class customInput1 {

    public static customNode1 treeInput() {

        System.out.println("Creating binary tree\n");
        System.out.println("Enter nodes(6) : ");
        customNode1 root = null;
        customTree1 tree = new customTree1();

        for (int i = 0; i <= 5; i++) {
            root = tree.customInsert(Integer.parseInt((new Scanner(System.in)).nextLine()), root);
        }

        System.out.println("Root is = " + root.info);

        return root;

    }

}


public class DiagonalSum {

    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public static void main(String[] args) {

        customNode1 root = customInput1.treeInput();
        fillDiagonalMap(root);
        System.out.println(map);

    }

    public static void fillDiagonalMap(customNode1 root) {

        Queue<customNode1> queue = new LinkedList<customNode1>();

        queue.add(root);

        while (!queue.isEmpty()) {

            customNode1 current = queue.remove();

            int vd = current.vd;

            while (current != null) {
                int prevSum = (map.get(vd) == null) ? 0 : map.get(vd);
                map.put(vd, prevSum + current.info);


                // If for any node the left child is not null add
                // it to the queue for future processing.

                if (current.left != null) {
                    current.left.vd = vd + 1;
                    queue.add(current.left);
                }

                current = current.right;

            }

        }


    }

}

/*
The idea is to keep track of vertical distance from top diagonal passing through root. 
We increment the vertical distance we go down to next diagonal.
1. Add root with vertical distance as 0 to the queue.
2. Process the sum of all right child and right of right child and so on.
3. Add left child current node into the queue for later processing. 
The vertical distance of left child is vertical distance of current node plus 1.
4. Keep doing 2nd, 3rd and 4th step till the queue is empty.

 */