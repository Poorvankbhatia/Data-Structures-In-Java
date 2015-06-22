package Trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 6/22/15.
 */
public class ReverseLevelOrderTraversal {
    
    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);
        
        printReverseLevelOrder(root);
        
        
    }
    
    private static void printReverseLevelOrder(Node root) {
        
        Stack stack = new Stack();
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(root);
        
        while (!queue.isEmpty()) {
            
            Node a = queue.remove();
            stack.push(a);
            
            if(a.right!=null) {
                queue.add(a.right);
            }
            
            if(a.left!=null) {
                queue.add(a.left);
            }
            
        }
        
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().info + " ");
        }
        
    }
    
}


/*

The idea is to use a stack to get the reverse level order. 
If we do normal level order traversal and instead of printing a node, 
push the node to a stack and then print contents of stack, we get “5 4 3 2 1″
 for above example tree, but output should be “4 5 2 3 1″. So to get the correct sequence 
 (left to right at every level), we process children of a node in reverse order, we first push 
 the right subtree to stack, then left subtree.

 */