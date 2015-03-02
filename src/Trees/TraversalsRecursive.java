/*
  Traversals in a recursive manner
 */
package Trees;


class traversal {

    public static void pre_Order(Node node) {
       
        if(node == null) {
            return;
        }
        System.out.print(node.info + " ");
        pre_Order(node.left);
        pre_Order(node.right);
        
    }
    
    public static void post_Order(Node node) {
        
        if(node == null) {
            return;
        }
        post_Order(node.left);
        post_Order(node.right);
        System.out.print(node.info + " ");
        
    }
    
    public static void in_Order(Node node) {
        
        if (node == null) {
            return;
        }
        in_Order(node.left);
        System.out.print(node.info + " ");
        in_Order(node.right);
        
    }
    
}

public class TraversalsRecursive {
    
    public static void main(String[] args) {
        
        Node root = Input.treeInput();
        System.out.println("InOrder is :");
        traversal.in_Order(root);
        System.out.println("\nPreOrder is :");
        traversal.pre_Order(root);
        System.out.println("\nPostOrder is :");
        traversal.post_Order(root);
    }
    
}


/*

  Time Complexity: O(n)
Let us prove it:

Complexity function T(n) — for all problem where tree traversal is involved — can be defined as:

T(n) = T(k) + T(n – k – 1) + c

Where k is the number of nodes on one side of root and n-k-1 on the other side.

Let’s do analysis of boundary conditions

Case 1: Skewed tree (One of the subtrees is empty and other subtree is non-empty )

k is 0 in this case.
T(n) = T(0) + T(n-1) + c, replace n by n-1 and then substitute value of T(n-1)
T(n) = 2T(0) + T(n-2) + 2c
T(n) = 3T(0) + T(n-3) + 3c
T(n) = 4T(0) + T(n-4) + 4c

…………………………………………
………………………………………….
T(n) = (n-1)T(0) + T(1) + (n-1)c
T(n) = nT(0) + (n)c

Value of T(0) will be some constant say d. (traversing a empty tree will take some constants time)

T(n) = n(c+d)
T(n) = (-)(n) (Theta of n)

Case 2: Both left and right subtrees have equal number of nodes.

T(n) = 2T(|_n/2_|) + c
 */