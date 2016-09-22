package trees.tree;

import java.util.HashMap;

/**
 * Created by poorvank on 6/23/15.
 */


public class Collision {

    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.right = new Node(3);
        root.right = new Node(45);
        root.right.left = new Node(46);

        HashMap<String, Integer> map = new HashMap<>();
        count(root, 0, 0, map);

    }

    private static void count(Node root, int level, int hd, HashMap<String, Integer> map) {

        if (root == null) {
            return;
        }

        count(root.left, level + 1, hd - 1, map);
        count(root.right, level + 1, hd + 1, map);

        String str = level + "," + hd;
        if (map.containsKey(str)) {
            System.out.print("Collision for - " + map.get(str) + " && " + root.info);
        } else {
            map.put(str, root.info);
        }

        return;

    }

}
