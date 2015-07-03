/*

A given array represents a tree in such a way that the array value gives the parent node of that particular index. 
The value of the root node index would always be -1. Find the height of the tree.
Height of a Binary Tree is number of nodes on the path from root to the deepest leaf node, 
the number includes both root and leaf.

Input: parent[] = {1 5 5 2 2 -1 3}
Output: 4
The given array represents following Binary Tree 
         5
        /  \
       1    2
      /    / \
     0    3   4
         /
        6 


Input: parent[] = {-1, 0, 0, 1, 1, 3, 5};
Output: 5
The given array represents following Binary Tree 
         0
       /   \
      1     2
     / \
    3   4
   /
  5 
 /
6


 */


package treesPrograms;

/**
 * Created by poorvank on 4/20/15.
 */
public class ParentArrayBT {

    public static void main(String[] args) {

        int parent[] = {1, 5, 5, 2, 2, -1, 3};
        System.out.println("height of the tree - " + height(parent));

    }

    private static int height(int[] parent) {

        int[] depth = new int[parent.length];

        for (int i = 0; i < parent.length; i++) {
            fillDepthArray(depth, i, parent);
        }

        int height = depth[0];

        for (int i = 1; i < depth.length; i++) {
            if (height < depth[i]) {
                height = depth[i];
            }
        }

        return height;

    }

    private static void fillDepthArray(int[] depth, int i, int[] parent) {

        if (depth[i] != 0) {
            return;
        }
        if (parent[i] == -1) {
            depth[i] = 1;
            return;
        }
        if (depth[parent[i]] == 0) {
            fillDepthArray(depth, parent[i], parent);
        }

        depth[i] = depth[parent[i]] + 1;

    }

}


/*

An efficient solution can solve the above problem in O(n) time. 
The idea is to first calculate depth of every node and store in an array depth[]. 
Once we have depths of all nodes, we return maximum of all depths.
1) Find depth of all nodes and fill in an auxiliary array depth[].
2) Return maximum value in depth[].

Following are steps to find depth of a node at index i.
1) If it is root, depth[i] is 1.
2) If depth of parent[i] is evaluated, depth[i] is depth[parent[i]] + 1.
3) If depth of parent[i] is not evaluated, recur for parent and assign depth[i] as depth[parent[i]] + 1 (same as above).


 */