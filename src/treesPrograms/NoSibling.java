/*

Given a Binary Tree, print all nodes that don’t have a sibling (a sibling is a node that has same parent.
 In a Binary Tree, there can be at most one sibling). Root should not be printed as root cannot have a sibling.

 */

package treesPrograms;

/**
 * Created by poorvank on 6/16/15.
 */
public class NoSibling {
    
    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(4);
        root.right.right = new Node(6);


        printNodes(root);
        
    }
    
    private static void printNodes(Node root) {
        
        if(root == null) {
            return;
        }
        
        if(root.left!=null && root.right!=null) {
            printNodes(root.right);
            printNodes(root.left);
        }
        
        else if(root.right!=null) {
            System.out.println(root.right.info);
            printNodes(root.right);
        }

        else if(root.left!=null) {
            System.out.println(root.left.info);
            printNodes(root.left);
        }
        
    }
    
}


/*

This is a typical tree traversal question. We start from root and check if 
the node has one child, if yes then print the only child of that node. 
If node has both children, then recur for both the children.

 */