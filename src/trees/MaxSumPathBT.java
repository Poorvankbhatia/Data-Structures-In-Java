package trees;

/**
 * Created by poorvank on 8/28/15.
 */
public class MaxSumPathBT {
    
    private static int result = Integer.MIN_VALUE;
    
    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(9);
        root.left.right = new Node(8);
        root.right = new Node(3);
        root.right.left = new Node(-4);
        root.right.right = new Node(6);
        maxPathSum(root);
        System.out.println(result);
        
    }
    
    private static int maxPathSum(Node root) {
        
        if(root==null) {
            return 0;
        }
        
        int left = maxPathSum(root.left);
        int right = maxPathSum(root.right);
        
        //Max value as root
        int max = Math.max(Math.max(left,right)+root.info,root.info);

        // MaxForSubtree represents the sum when the Node under
        // consideration is the root of the maxsum path and no
        // ancestors of root are there in max sum path
        int maxForSubTree = Math.max(left+right+root.info,max);
        
        result = Math.max(result,maxForSubTree);
        
        return max;
        
    }
    
}

/*

check images


For each node there can be four ways that the max path goes through the node:
1. Node only
2. Max path through Left Child + Node
3. Max path through Right Child + Node
4. Max path through Left Child + Node + Max path through Right Child

The idea is to keep trace of four paths and pick up the max one in the end. An important thing to note is, 
root of every subtree need to return maximum path sum such that at most one child of root is involved.
 This is needed for parent function call.
 In below code, this sum is stored in ‘max_single’ and returned by the recursive function.

 */