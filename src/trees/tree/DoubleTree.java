/*

Write a program that converts a given tree to its Double tree. 
To create Double tree of the given tree, create a new duplicate for each node, and insert the duplicate as 
the left child of the original node.

So the tree…

    2
   / \
  1   3
is changed to…

       2
      / \
     2   3
    /   /
   1   3
  /
 1
And the tree

            1
          /   \
        2      3
      /  \
    4     5
is changed to

               1
             /   \
           1      3
          /      /
        2       3
      /  \
     2    5
    /    /
   4   5
  /   
 4    

 */

package trees.tree;

public class DoubleTree {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println("In order before - ");
        Traversal.in_Order(root);
        System.out.println("In order after - ");
        Traversal.in_Order(conversionDT(root));


    }

    private static Node conversionDT(Node root) {

        if (root == null) {
            return null;
        }

        Node tmp = null;


        if (root.right != null) {
            root.right = conversionDT(root.right);
        }
        if (root.left != null) {
            root.left = conversionDT(root.left);
        }
        tmp = new Node(root.info);
        tmp.left = root.left;
        root.left = tmp;


        return root;

    }

}

/*

Recursively convert the tree to double tree in postorder fashion. For each node, first convert the left 
subtree of the node, then right subtree, finally create a duplicate 
node of the node and fix the left child of the node and left child of left child.

 */