/*

Remove all nodes which don’t lie in any path with sum>= k
Given a binary tree, a complete path is defined as a path from root to a leaf. 
The sum of all nodes on that path is defined as the sum of that path. Given a number K, 
you have to remove (prune the tree) all nodes which don’t lie in any path with sum>=k.

Note: A node can be part of multiple paths. So we have to delete it only in case when all 
paths from it have sum less than K.

Consider the following Binary Tree
          1 
      /      \
     2        3
   /   \     /  \
  4     5   6    7
 / \    /       /
8   9  12      10
   / \           \
  13  14         11
      / 
     15 

For input k = 20, the tree should be changed to following
(Nodes with values 6 and 8 are deleted)
          1 
      /      \
     2        3
   /   \        \
  4     5        7
   \    /       /
    9  12      10
   / \           \
  13  14         11
      / 
     15 

For input k = 45, the tree should be changed to following.
      1 
    / 
   2   
  / 
 4  
  \   
   9    
    \   
     14 
     /
    15 


 */

package Trees;

/**
 * Created by poorvank on 6/23/15.
 */
public class RemoveNodes {
    
    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(9);
        root.right = new Node(14);
        root.right.right = new Node(23);
        root.right.left = new Node(16);
        
        System.out.println("Before removal");
        Traversal.in_Order(root);
        
        root = remove(root,21);

        System.out.println("\nAfter removal");
        Traversal.in_Order(root);
        
        
    }
    
    private static Node remove(Node root,int sum) {
        
        if(root==null) {
            return null;
        }
        
        root.left = remove(root.left , sum-root.info);
        root.right = remove(root.right , sum-root.info);
        
        if(root.left== null && root.right==null) {
            
            if(root.info<sum) {
                return null;
            }
            
        }
        
        return root;
        
    }
    
}

/*

The idea is to keep reducing the sum when traversing down. When we reach a leaf and sum is greater than the leaf’s data,
 then we delete the leaf. Note that deleting nodes may convert a non-leaf node to a leaf node and if the data for 
 the converted leaf node is less than the current sum, then the converted leaf should also be deleted.

 */