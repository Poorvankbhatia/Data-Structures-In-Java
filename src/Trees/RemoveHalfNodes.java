/*

Given A binary Tree, how do you remove all the half nodes (which has only one child)? 
Note leaves should not be touched as they have both children as NULL.

 */

package Trees;

/**
 * Created by poorvank on 3/24/15.
 */
public class RemoveHalfNodes {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        traversal.in_Order(root);
        System.out.println("\nAfter removal -");
        traversal.in_Order(fullTree(root));

    }

    private static Node fullTree(Node root) {

        if (root == null) {
            return null;
        }

        root.left = fullTree(root.left);
        root.right = fullTree(root.right);

        if (root.left == null && root.right == null) {
            return root;
        }

        if (root.left == null) {

            Node current = root.right;
            root = null;
            return current;

        }

        if (root.right == null) {

            Node current = root.left;
            root = null;
            return current;

        }

        return root;

    }

}

/*

  The idea is to use post-order traversal to solve this problem efficiently. 
  We first process the left children, then right children, and finally the node itself. 
  So we form the new tree bottom up, starting from the leaves towards the root. 
  By the time we process the current node, both its left and right subtrees were already processed. 
  O(n)
  
 */