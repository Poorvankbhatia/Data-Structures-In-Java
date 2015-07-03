/*

Given preorder traversal of a binary search tree, construct the BST.

For example, if the given traversal is {10, 5, 1, 7, 40, 50}, then the output should be root of following tree.

     10
   /   \
  5     40
 /  \      \
1    7      50

 */

package treesPrograms;

/**
 * Created by poorvank on 6/13/15.
 */
public class PreOrderBST2 {
    
    public static void main(String[] args) {
        
        int[] pre = new int[]{10, 5, 1, 7, 40, 50};
        Traversal.in_Order(constructTree(pre));
        
    }
    
    private static Node constructTree(int[] pre) {
        
        Node root = new Node(pre[0]);
        
        Stack stack = new Stack();
        
        stack.push(root);
        
        for (int i=1;i<pre.length;i++) {
            
            Node temp = null;
            
            
            while (!stack.isEmpty() && pre[i] > stack.peek().info) {
                temp = stack.pop();
            }
            
            if(temp==null) {
                
                stack.peek().left = new Node(pre[i]);
                stack.push(stack.peek().left);
                
            }
            else {
                temp.right = new Node(pre[i]);
                stack.push(temp.right);
            }
            
        }
        
        return root;
        
    }
    
}

/*

1. Create an empty stack.

2. Make the first value as root. Push it to the stack.

3. Keep on popping while the stack is not empty and the next value is 
greater than stack’s top value. Make this value as the right child of the last popped node. Push the new node to the stack.

4. If the next value is less than the stack’s top value, make this value as 
the left child of the stack’s top node. Push the new node to the stack.

5. Repeat steps 2 and 3 until there are items remaining in pre[].

 */