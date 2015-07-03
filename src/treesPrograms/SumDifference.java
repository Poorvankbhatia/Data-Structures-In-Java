/*

Difference between sums of odd level and even level nodes of a Binary Tree

 */

package treesPrograms;

/**
 * Created by poorvank on 6/6/15.
 */
public class SumDifference {
    
    public static void main(String[] args) {
            
        Node root =Input.treeInput();
        System.out.println(difference(root));
        
    }
    
    public static int difference(Node root) {
        
        if(root==null) {
            return 0;
        }
        
        return root.info-(difference(root.left) + difference(root.right));
        
    }
    
}


/*

The problem can also be solved using simple recursive traversal. 
We can recursively calculate the required difference as, 
value of root’s data subtracted by the difference for subtree under left child and the 
difference for subtree under right child.

 */