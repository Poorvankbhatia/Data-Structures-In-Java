/*

Given a Binary Tree, find vertical sum of the nodes that are in same vertical line. 
Print all sums through different vertical lines.

Examples:

      1
    /   \
  2      3
 / \    / \
4   5  6   7
The tree has 5 vertical lines

Vertical-Line-1 has only one node 4 => vertical sum is 4
Vertical-Line-2: has only one node 2=> vertical sum is 2
Vertical-Line-3: has three nodes: 1,5,6 => vertical sum is 1+5+6 = 12
Vertical-Line-4: has only one node 3 => vertical sum is 3
Vertical-Line-5: has only one node 7 => vertical sum is 7

So expected output is 4, 2, 12, 3 and 7

 */

package treesPrograms;

import java.util.HashMap;

public class VerticalSum {

    private static HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

    public static void main(String[] args) {

        Node root = Input.treeInput();
        sum(root, 0);
        System.out.println(hashMap);

    }

    private static void sum(Node root, int hd) {

        if (root == null) {
            return;
        }

        sum(root.left, hd - 1);

        int prevKey = (hashMap.get(hd) == null ? 0 : hashMap.get(hd));

        hashMap.put(hd, prevKey + root.info);

        sum(root.right, hd + 1);

    }

}

/*

We need to check the Horizontal Distances from root for all nodes. 
If two nodes have the same Horizontal Distance (HD), then they are on same vertical line. 
The idea of HD is simple. HD for root is 0, a right edge (edge connecting to right subtree) is considered as +1 
    horizontal distance and a left edge is considered as -1 horizontal distance.


We can do inorder traversal of the given Binary Tree. While traversing the tree, we can recursively calculate HDs.
 We initially pass the horizontal distance as 0 for root. 
 For left subtree, we pass the Horizontal Distance as Horizontal distance of root minus 1. 
 For right subtree, we pass the Horizontal Distance as Horizontal Distance of root plus 1.
 */