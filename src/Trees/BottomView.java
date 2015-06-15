package Trees;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class customNode2 {

    public int info;
    public customNode2 left;
    public customNode2 right;
    public int hd; //horizontal distance

    public customNode2(int info, int hd) {
        this(info, null, null, hd);
    }

    public customNode2(int info, customNode2 left, customNode2 right, int hd) {
        this.info = info;
        this.left = left;
        this.right = right;
        this.hd = hd;
    }

}

class customTree2 {

    public customNode2 root;

    public customTree2() {
        root = null;
    }

    public static customNode2 search(int n, customNode2 root) {

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

    public customNode2 customInsert(int info, customNode2 root) {
        if (root == null) {
            root = new customNode2(info, 0);
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

class customInput2 {

    public static customNode2 treeInput() {

        System.out.println("Creating binary tree\n");
        System.out.println("Enter nodes(6) : ");
        customNode2 root = null;
        customTree2 tree = new customTree2();

        for (int i = 0; i <= 5; i++) {
            root = tree.customInsert(Integer.parseInt((new Scanner(System.in)).nextLine()), root);
        }

        System.out.println("Root is = " + root.info);

        return root;

    }

}

public class BottomView {

    private static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

    public static void main(String[] args) {

        customNode2 root = customInput2.treeInput();
        bottom(root);
        System.out.println(map);

    }


    private static void bottom(customNode2 root) {

        if (root == null) {
            return;
        }

        Queue<customNode2> queue = new LinkedList<customNode2>();

        int hd = root.hd;

        queue.add(root);

        while (!queue.isEmpty()) {

            customNode2 current = queue.remove();

            hd = current.hd;

            // Put the de queued tree node to TreeMap having key
            // as horizontal distance. Every time we find a node
            // having same horizontal distance we need to replace
            // the data in the map.
            map.put(hd, current.info);

            if (current.left != null) {
                current.left.hd = hd - 1;
                queue.add(current.left);
            }

            if (current.right != null) {
                current.right.hd = hd + 1;
                queue.add(current.right);
            }

        }


    }

}

/*


The following are steps to print Bottom View of Bianry Tree.
1. We put tree nodes in a queue for the level order traversal.
2. Start with the horizontal distance(hd) 0 of the root node, keep on adding left child to queue 
along with the horizontal
 distance as hd-1 and right child as hd+1.
3. Also, use a TreeMap which stores key value pair sorted on key.
4. Every time, we encounter a new horizontal distance or an existing horizontal distance put the node data for 
the horizontal distance as key. For the first time it will add to the map, next time it will replace the value. 
This will make sure that the bottom most element for that horizontal distance is present in the map and if you see 
the tree from beneath that you will see that element.

 */