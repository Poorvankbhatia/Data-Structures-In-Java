/*

Check whether a binary tree is a full binary tree or not
A full binary tree is defined as a binary tree in which all nodes have either zero or two child nodes. 
Conversely, there is no node in a full binary tree, which has one child node.

 */

package Trees;

/**
 * Created by poorvank on 6/22/15.
 */
public class FullBinaryTree {
    
    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);
        
        System.out.print(checkFullBT(root));
        
    }
    
    private static boolean checkFullBT(Node root) {
        
        if(root==null) {
            return true;
        }
        
        if(root.left==null && root.right==null) {
            return true;
        }
        
        return ((root.left!=null && root.right!=null) && (checkFullBT(root.left) && checkFullBT(root.right)));
        
    }
    
}


/*

To check whether a binary tree is a full binary tree we need to test the following cases:-

1) If a binary tree node is NULL then it is a full binary tree.
2) If a binary tree node does have empty left and right sub-trees, then it is a full binary tree by definition
3) If a binary tree node has left and right sub-trees, then it is a part of a full binary tree by definition. 
In this case recursively check if the left and right sub-trees are also binary trees themselves.
4) In all other combinations of right and left sub-trees, the binary tree is not a full binary tree.

 */