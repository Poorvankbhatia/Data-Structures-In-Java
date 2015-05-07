/*

Inorder Successor in Binary Search Tree
In Binary Tree, Inorder successor of a node is the next node in Inorder traversal of the Binary Tree.
 Inorder Successor is NULL for the last node in Inoorder traversal.
In Binary Search Tree, Inorder Successor of an input node can also be defined as the node with the 
smallest key greater than the key of input node. So, it is sometimes important to find next node in sorted order.

 */

package Trees;

/**
 * Created by poorvank on 5/7/15.
 */
public class InorderSuccessor {
    
    public static void main(String[] args) {
        
        Node root = Input.treeInput();
        if(successor(root,root.right)!=null)
        System.out.println("successor is - " + successor(root,root.right).info);
        else 
        System.out.println("No successor");
        
    }
    
    private static Node successor(Node root,Node n) {
        
        
        
        if(n.right!=null) {
            return minValue(n.right);
        }
        
        Node successor = null;
        
        while (root!=null) {
            
            if(n.info < root.info) {
                successor = root;
                root = root.left;
            }
            else if(n.info > root.info) {
                root = root.right;
            }
            else {
                break;
            }
            
        }
        
        return successor;
        
    }
    
    private static Node minValue(Node root) {
        
        if(root!=null) {
            
            Node current = root;
            
            while (current.left!=null) {
                current = current.left;
            }
            
            return current;
        }
        
        return null;
        
    }
    
}


/*

Parent pointer is NOT needed in this algorithm. 
The Algorithm is divided into two cases on the basis of right subtree of the input node being empty or not.

Input: node, root // node is the node whose Inorder successor is needed.
output: succ // succ is Inorder successor of node.

1) If right subtree of node is not NULL, then succ lies in right subtree. Do following.
Go to right subtree and return the node with minimum key value in right subtree.
2) If right sbtree of node is NULL, then start from root and us search like technique. Do following.
Travel down the tree, if a node’s data is greater than root’s data then go right side, otherwise go to left side.


USING A PARENT POINTER

1) If right subtree of node is not NULL, then succ lies in right subtree. Do following.
Go to right subtree and return the node with minimum key value in right subtree.
2) If right sbtree of node is NULL, then succ is one of the ancestors. Do following.
Travel up using the parent pointer until you see a node which is left child of it’s parent. 
The parent of such a node is the succ.(consider last node)

 */