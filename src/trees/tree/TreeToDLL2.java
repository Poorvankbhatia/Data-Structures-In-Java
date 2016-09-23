/*

Given a Binary Tree (Bt), convert it to a Singly LinkList. The left and right pointers in nodes are to be used as previous and next pointers respectively in converted DLL.
 The order of nodes in DLL must be same as Inorder of the given Binary Tree. The first node of Inorder traversal (left most node in BT) must be head node of the DLL.

 */

package trees.tree;

/**
 * Created by poorvank on 6/11/15.
 */
public class TreeToDLL2 {

    private static Node head = null;
    private static Node prev = null;

    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);

        convert(root);

        while (head != null) {
            System.out.print(head.info + " ");
            head = head.right;
        }


    }

    private static void convert(Node root) {

        if (root == null) {
            return;
        }

        convert(root.left);

        if (prev == null) {
            head = root;
        } else {
            root.left = prev;
            prev.right = root;
        }

        prev = root;

        convert(root.right);

    }

}

/*

The idea is to do inorder traversal of the binary tree. 
While doing inorder traversal, keep track of the previously visited node 
in a variable say prev. For every visited node, make it next of prev and previous of this node as prev.

 */