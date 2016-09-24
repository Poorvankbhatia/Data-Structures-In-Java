/*

Convert an arbitrary Binary Tree to a tree that holds Children Sum Property
Question: Given an arbitrary binary tree, convert it to a binary tree that holds Children Sum Property. You can only
increment data values in any node (You cannot change structure of tree and cannot decrement value of any node).

For example, the below tree doesn’t hold the children sum property, convert it to a tree that holds the property.

             50
           /     \
         /         \
       7             2
     / \             /\
   /     \          /   \
  3        5      1      30

 */

package trees.tree;

public class TreeToSumTree {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.print("Before conversion\n");
        Traversal.in_Order(root);
        System.out.print("\nAfter conversion\n");
        Traversal.in_Order(convertToSumTree(root));


    }

    private static Node convertToSumTree(Node root) {

        if ((root == null) || (root.left == null && root.right == null)) {
            return root;
        }

        Node lRoot = convertToSumTree(root.left);
        Node rRoot = convertToSumTree(root.right);

        int lvalue = 0, rValue = 0;

        if (lRoot != null) {
            lvalue = lRoot.info;
        }

        if (rRoot != null) {
            rValue = rRoot.info;
        }

        int diff = lvalue + rValue - root.info;

        if (diff > 0) {
            root.info = root.info + diff;
        }
        if (diff < 0) {
            root = increment(root, diff);
        }
        return root;
    }


    private static Node increment(Node root, int diff) {

        if (root.left != null) {
            root.left.info += Math.abs(diff);
            increment(root.left, diff);
        } else if (root.right != null) {
            root.right.info += Math.abs(diff);
            increment(root.right, diff);
        }
        return root;
    }

}

/*

Algorithm:
Traverse given tree in post order to convert it, i.e., first change left and right children to hold the children sum property
then change the parent node.

Let difference between node’s data and children sum be diff.

     diff = node’s children sum - node’s data
If diff is 0 then nothing needs to be done.

If diff > 0 ( node’s data is smaller than node’s children sum) increment the node’s data by diff.

If diff < 0 (node’s data is greater than the node's children sum) then increment one child’s data. We can choose to increment either
left or right child if they both are not NULL. Let us always first increment the left child. Incrementing a child changes the subtree’s
children sum property so we need to change left subtree also. So we recursively increment the left child. If left child is empty then we
recursively call increment() for right child. Let us run the algorithm for the given example. First convert the left subtree (increment 7 to 8).

             50
           /     \
         /         \
       8             2
     / \             /\
   /     \          /   \
  3        5      1      30
Then convert the right subtree (increment 2 to 31)

          50
        /    \
      /        \
    8            31
   / \           / \
 /     \       /     \
3       5    1       30
Now convert the root, we have to increment left subtree for converting the root.

          50
        /    \
      /        \
    19           31
   / \           /  \
 /     \       /      \
14      5     1       30
Please note the last step – we have incremented 8 to 19, and to fix the subtree we have incremented 3 to 14.
 
 O(n^2), Worst case complexity is for a skewed tree such that nodes are in decreasing order from root to leaf.

 */