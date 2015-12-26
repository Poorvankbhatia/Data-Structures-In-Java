package trees;

/**
 * Created by poorvank on 8/28/15.
 */
public class PrintNodesAtKDistance {

    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.left = new Node(30);
        root.right.right = new Node(46);

        Node target = root.left.left;
        printNodes(root, target, 4);


    }

    // Prints all nodes at distance k from a given target node.
// The k distant nodes may be upward or downward.  This function
// Returns distance of root from target node, it returns -1 if target
// node is not present in tree rooted with root.
    private static int printNodes(Node root, Node target, int k) {


        if (root == null) {
            return -1;
        }

        if (root == target) {
            printDownNodes(target, k);
            return 0;
        }

        int dl = printNodes(root.left, target, k);

        if (dl != -1) {

            if (dl + 1 == k) {
                System.out.println(root.info);
            } else {
                printDownNodes(root.right, k - dl - 2);
            }

            return 1 + dl;

        }

        int dr = printNodes(root.right, target, k);

        if (dr != -1) {

            if (dr + 1 == k) {
                System.out.println(root.info);
            } else {
                printDownNodes(root.left, k - dr - 2);
            }

            return 1 + dr;

        }

        return -1;

    }

    private static void printDownNodes(Node root, int k) {

        if (root == null || k < 0) {
            return;
        }

        if (k == 0) {
            System.out.println(root.info);
            return;
        }

        printDownNodes(root.left, k - 1);
        printDownNodes(root.right, k - 1);

    }

}

/*

There are two types of nodes to be considered.
1) Nodes in the subtree rooted with target node. For example if the target node is 8 and k is 2, then such nodes are 10 
and 14.
2) Other nodes, may be an ancestor of target, or a node in some other subtree. For target node 8 and k is 2, the node 22 
comes in this category.

Finding the first type of nodes is easy to implement. Just traverse subtrees rooted with the target node and decrement k 
in recursive call. When the k becomes 0, print the node currently being traversed .
Here we call the function as printkdistanceNodeDown().

How to find nodes of second type? For the output nodes not lying in the subtree with the target node as the root,
 we must go through all ancestors. For every ancestor, we find its distance from target node, let the distance be d, 
 now we go to other subtree (if target was found in left subtree, then we go to right subtree and vice versa) of the 
 ancestor and find all nodes at k-d distance from the ancestor.

 */