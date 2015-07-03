/*

Given a binary tree, print it vertically. The following example illustrates vertical order traversal.

           1
        /    \
       2      3
      / \    / \
     4   5  6   7
             \   \
              8   9


The output of print this tree vertically will be:
4
2
1 5 6
3 8
7
9

 */

package treesPrograms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by poorvank on 6/19/15.
 */
public class PrintVerticalOrder {

    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(5);
        root.left.right = new Node(3);
        root.right = new Node(4);
        root.right.left = new Node(6);

        order(root);

    }

    private static void order(Node root) {

        Map<Integer, List<Node>> map = new TreeMap<>();
        fillMap(root, map, 0);

        //System.out.print(map.toString());

        for (Map.Entry<Integer, List<Node>> entry : map.entrySet()) {

            for (int i = 0; i < entry.getValue().size(); i++) {
                System.out.print(entry.getValue().get(i).info + " ");
            }

            System.out.println();
        }

    }

    private static void fillMap(Node root, Map<Integer, List<Node>> map, int hd) {

        if (root == null) {
            return;
        }

        if (map.containsKey(hd)) {
            map.get(hd).add(root);
        } else {
            List<Node> list = new ArrayList<>();
            list.add(root);
            map.put(hd, list);
        }

        fillMap(root.left, map, hd + 1);
        fillMap(root.right, map, hd - 1);

    }

}


/*

n this post, an efficient solution based on hash map is discussed. We need to check the Horizontal Distances
 from root for all nodes. If two nodes have the same Horizontal Distance (HD), then they are on same vertical line.
  The idea of HD is simple. HD for root is 0, a right edge (edge connecting to right subtree) is considered as +1
   horizontal distance and a left edge is considered as -1 horizontal distance. For example, in the above tree,
    HD for Node 4 is at -2, HD for Node 2 is -1, HD for 5 and 6 is 0 and HD for node 7 is +2.
We can do inorder traversal of the given Binary Tree. While traversing the tree, we can recursively calculate HDs. 
We initially pass the horizontal distance as 0 for root. For left subtree, we pass the Horizontal 
Distance as Horizontal distance of root minus 1. For right subtree, we pass the Horizontal 
Distance as Horizontal Distance of root plus 1. For every HD value, we maintain a list of nodes in a hasp map. 
Whenever we see a node in traversal, we go to the hash map entry and add the node to the hash map using HD as a key in map.

 */