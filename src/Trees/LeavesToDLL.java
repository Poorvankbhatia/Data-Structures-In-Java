/*

Given a Binary Tree, extract all leaves of it in a Doubly Linked List (DLL).
Note that the DLL need to be created in-place. Assume that the node structure
of DLL and Binary Tree is same, only the meaning of left and right pointers are different.
In DLL, left means previous pointer and right means next pointer.

Let the following be input binary tree
        1
     /     \
    2       3
   / \       \
  4   5       6
 / \         / \
7   8       9   10


Output:
Doubly Linked List
7<->8<->5<->9<->10

Modified Tree:
        1
     /     \
    2       3
   /         \
  4           6
 */

package trees;

/**
 * Created by poorvank on 6/10/15.
 */

public class LeavesToDLL {


    private static Node head = null;
    
    public static void main(String[] args) {
        
        Node root = new Node(1);
        root.left = new Node(3);
        root.right = new Node(5);
        root.left.left = new Node(2);
        root.left.right = new Node(9);
        
        System.out.println("Inorder before ");
        Traversal.in_Order(root);
        System.out.println("\nInorder after ");
        Traversal.in_Order(convert(root));
        System.out.println("\nList is : ");
        printList(head);
        
    }
    
    private static void printList(Node head) {
        
        Node current = head;
        
        while (current!=null) {
            System.out.print(current.info + " ");
            current = current.right;
        }
        
    }
    
    private static Node convert(Node root) {
        
        if(root == null) {
            return null;
        }
        
        if(root.left==null && root.right==null) {
            
            root.right=head;
            
            if(head!=null) {
                head.left = root;
            }
            
            head = root;
            
            return null;
            
        }

        root.right = convert(root.right);
        root.left = convert(root.left);
        
        return root;
        
    }
    
}


/*

We need to traverse all leaves and connect them by changing their left and right pointers. 
We also need to remove them from Binary
 Tree by changing left or right pointers in parent nodes. There can be many ways to solve this. 
 In the following implementation, we add leaves at the beginning of current linked list and update 
 head of the list using pointer to head pointer. Since we insert at the beginning, we need to process 
 leaves in reverse order. For reverse order, we first traverse the right subtree then the left subtree. 
 We use return values to update left or right pointers in parent nodes.

 */