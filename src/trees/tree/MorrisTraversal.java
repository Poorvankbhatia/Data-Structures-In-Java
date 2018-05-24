/*

Inorder Tree Traversal without recursion and without stack!

 */
package trees.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 25/05/18.
 */
public class MorrisTraversal {

    public List<Integer> printInorder(Node root) {

        List<Integer> list = new ArrayList<>();

        if(root==null) {
            return list;
        }

        Node current = root;

        while (current!=null) {

            if(current.left==null) {
                list.add(current.info);
                current = current.left;
            } else {
                Node prev = current.left;
                while (prev.right!=null && prev.right!=current) {
                    prev = prev.right;
                }

                if(prev.right==null) {
                    prev.right = current;
                    current = current.left;
                } else {
                    prev.right = null;
                    list.add(current.info);
                    current = current.right;
                }

            }

        }

        return list;

    }

}

/*

Using Morris Traversal, we can traverse the tree without using stack and recursion. The idea of Morris Traversal is based on Threaded Binary Tree.
In this traversal, we first create links to Inorder successor and print the data using these links, and finally revert the changes to restore original tree.

1. Initialize current as root
2. While current is not NULL
   If current does not have left child
      a) Print current’s data
      b) Go to the right, i.e., current = current->right
   Else
      a) Make current as right child of the rightmost
         node in current's left subtree
      b) Go to this left child, i.e., current = current->left
Although the tree is modified through the traversal, it is reverted back to its original shape after the completion.

 */