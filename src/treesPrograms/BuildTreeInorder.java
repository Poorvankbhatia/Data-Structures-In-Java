/*


Construct Special Binary Tree from given Inorder traversal
Given Inorder Traversal of a Special Binary Tree in which key of 
every node is greater than keys in left and right children, construct the Binary Tree and return root.

Examples:

Input: inorder[] = {5, 10, 40, 30, 28}
Output: root of following tree
         40
       /   \
      10     30
     /         \
    5          28 

 */

package treesPrograms;

/**
 * Created by poorvank on 6/10/15.
 */
public class BuildTreeInorder {
    
    public static void main(String[] args) {
        
        int[] inorder = new int[]{5, 10, 40, 30, 28};
        Traversal.in_Order(buildTree(inorder,0,inorder.length-1));
        
    }
    
    private static Node buildTree(int[] inorder,int start,int end) {
        
        if(start>end) {
            return null;
        }
        
        int i = maxIndex(inorder,start,end);
        
        Node root = new Node(inorder[i]);
        
        if(start == end) {
            return root;
        }
        
        root.left = buildTree(inorder,start,i-1);
        root.right = buildTree(inorder,i+1,end);
        
        
        return root;
    }
    
    private static int maxIndex(int[] arr,int start,int end) {
        
        int max = start;
        
        for (int i = start+1;i<=end;i++) {
            
            if(arr[i] > arr[max]) {
                max = i;
            }
            
        }
        
        return max;
        
    }
    
}


/*

Algorithm: buildTree()
1) Find index of the maximum element in array. The maximum element must be root of Binary Tree.
2) Create a new tree node ‘root’ with the data as the maximum value found in step 1.
3) Call buildTree for elements before the maximum element and make the built tree as left subtree of ‘root’.
5) Call buildTree for elements after the maximum element and make the built tree as right subtree of ‘root’.
6) return ‘root’.

 */