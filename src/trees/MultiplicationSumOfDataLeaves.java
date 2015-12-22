/*

Find multiplication of sums of data of leaves at same levels
Given a Binary Tree, return following value for it.
1) For every level, compute sum of all leaves if there are leaves at this level. Otherwise ignore it.
2) Return multiplication of all sums.

Examples:

Input: Root of below tree
         2
       /   \
      7     5
             \
              9
Output: 63
First levels doesn't have leaves. Second level
has one leaf 7 and third level also has one
leaf 9.  Therefore result is 7*9 = 63


Input: Root of below tree
         2
       /   \
     7      5
    / \      \
   8   6      9
      / \    /  \
     1  11  4    10

Output: 208
First two levels don't have leaves. Third
level has single leaf 8. Last level has four
leaves 1, 11, 4 and 10. Therefore result is
8 * (1 + 11 + 4 + 10)  

 */
package trees;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 12/13/15.
 */
public class MultiplicationSumOfDataLeaves {
    
    public static void main(String[] args) {

        Node root = new Node(2);
        root.left = new Node(7);
        root.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(3);
        
        System.out.println(resultMultiplication(root));
        
        
    }
    
    private static boolean isLeaf(Node root) {
        
        return (root!=null && root.right==null && root.left==null);
        
    }
    
    
    private static int resultMultiplication(Node root) {
        
        int mul = 1;
        Queue<Node> queue = new LinkedList<>();
        
        queue.add(root);
        boolean foundLeaf = false;
        
        while (!queue.isEmpty()) {
            
            int size = queue.size();

            int sum =0;
            while (size>0) {

                Node current = queue.remove();
                if(isLeaf(current)) {
                    sum += current.info;
                    foundLeaf = true;
                }
                if(current.left!=null) {
                    queue.add(current.left);
                }
                if(current.right!=null) {
                    queue.add(current.right);
                }
                
                size--;
            }
            
            if(foundLeaf)  {
                mul *= sum;
            }

        }
        
        return mul;
        
    }
}

/*

An Efficient Solution is to use Queue based level order traversal. While doing the traversal, process all different 
levels separately. For every processed level, check if it has a leaves. If it has then compute sum of leaf nodes. 
Finally return product of all sums.

 */