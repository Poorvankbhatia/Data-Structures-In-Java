/*

  Given preorder traversal of a binary search tree, construct the BST.

 */

package Trees;

public class PreorderBST {
    
    private static int min = Integer.MIN_VALUE;
    private static int max = Integer.MAX_VALUE;
    private static int index = 0;
    
    public static void main(String[] args) {
        
        int[] preOrder = new int[]{10, 5, 1, 7, 40, 50};
        traversal.in_Order(constructTree(preOrder,preOrder.length,preOrder[0],min,max));
        
        
    }
    
    private static Node constructTree(int[] preOrder,int size,int key,int min,int max) {
        
        if(index>=size) {
            return null;
        }
        
        Node root = null;
        
        if(key>min && key<max) {
            
            root = new Node(key);
            index++;
            
            if(index<size) {
                root.left = constructTree(preOrder,size,preOrder[index],min,root.info);
                root.right = constructTree(preOrder,size,preOrder[index],root.info,max);
            }
            
        }
        
        
        return root;
        
    }
    
}

 /*
 
 The idea used here is inspired from method 3 of this post. 
 The trick is to set a range {min .. max} for every node. 
 Initialize the range as {INT_MIN .. INT_MAX}. 
 The first node will definitely be in range, so create root node. 
 To construct the left subtree, set the range as {INT_MIN …root->data}. 
 If a values is in the range {INT_MIN .. root->data}, the values is part part of left subtree. 
 To construct the right subtree, set the range as {root->data..max .. INT_MAX}.

 */