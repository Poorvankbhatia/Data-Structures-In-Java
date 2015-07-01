/*

Find the maximum path sum between two leaves of a binary tree
Given a binary tree in which each node element contains a number. 
Find the maximum possible sum from one leaf node to another.

 */

package trees;

/**
 * Created by poorvank on 6/12/15.
 */
public class MaximumLeavesSum {
    
    private static int result = 0;
    
    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);
        
        maxSum(root);
        
        System.out.println(result);
        
    }
    
    private static int maxSum(Node root) {
        
        if(root == null) {
            return 0;
        }
        
        int left = maxSum(root.left);
        int right = maxSum(root.right);
        
        int currentSum = left + right + root.info;
        
        if(result<currentSum) {
            result = currentSum;
        }
        
        return Math.max(left,right)+root.info;
        
    }
    
}


/*

We can find the maximum sum using single traversal of binary tree. 
The idea is to maintain two values in recursive calls
1) Maximum root to leaf path sum for the subtree rooted under current node.
2) The maximum path sum between leaves (desired output).

For every visited node X, we find the maximum root to leaf sum in left and right 
subtrees of X. We add the two values with X->data, and compare the sum with maximum path sum found so far.

 */