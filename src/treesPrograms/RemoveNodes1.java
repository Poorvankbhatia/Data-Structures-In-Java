/*

Remove nodes on root to leaf paths of length < K
Given a Binary Tree and a number k, remove all nodes that lie only on root to leaf path(s) of length smaller than k. 
If a node X lies on multiple root-to-leaf paths and if any of the paths has path length >= k, 
then X is not deleted from Binary Tree. In other words a node is deleted if all paths going through
 it have lengths smaller than k.

 */


package treesPrograms;

public class RemoveNodes1 {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println("inorder before ");
        Traversal.in_Order(root);
        System.out.println("\ninorder after ");
        remove(root, 0, 2);
        Traversal.in_Order(root);

    }

    private static Node remove(Node root, int level, int k) {

        if (root == null) {
            return null;
        }

        root.left = remove(root.left, level + 1, k);
        root.right = remove(root.right, level + 1, k);

        if (root.left == null && root.right == null && level < k) {
            root = null;
        }

        return root;

    }

}

/*

            1
           /      \
         2          3
      /     \         \
    4         5        6
  /                   /
 7                   8 
Input: Root of above Binary Tree
       k = 4

Output: The tree should be changed to following  
           1
        /     \
      2          3
     /             \
   4                 6
 /                  /
7                  8
There are 3 paths 
i) 1->2->4->7      path length = 4
ii) 1->2->5        path length = 3
iii) 1->3->6->8    path length = 4 
There is only one path " 1->2->5 " of length smaller than 4.  
The node 5 is the only node that lies only on this path, so 
node 5 is removed.
Nodes 2 and 1 are not removed as they are parts of other paths
of length 4 as well.

If k is 5 or greater than 5, then whole tree is deleted. 

If k is 3 or less than 3, then nothing is deleted.



The idea here is to use post order traversal of the tree. Before removing a node we need to check that 
all the children of that node in the shorter path are already removed. There are 2 cases for a node whose child or cho
i) This node becomes a leaf node in which case it needs to be deleted.
ii) This node has other child on a path with path length >= k. In that case it needs not to be deleted.

 */