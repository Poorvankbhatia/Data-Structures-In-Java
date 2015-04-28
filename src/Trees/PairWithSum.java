/*

Given a Balanced Binary Search Tree and a target sum, write a function that returns true if there is a 
pair with sum equals to target sum, otherwise return false. Expected time complexity is O(n) and only O(Logn) 
extra space can be used. 
Any modification to Binary Search Tree is not allowed. Note that height of a Balanced BST is always O(Logn).

 */

package Trees;

/**
 * Created by poorvank on 4/28/15.
 */
public class PairWithSum {
    
    public static void main(String[] args) {
        
        Node root = Input.treeInput();
        sumPair(90,root);
        
    }
    
    private static void sumPair(int sum,Node root) {
        
        boolean done1 = false,done2 = false;
        Node current1 = root,current2 = root;
        int val1=0,val2=0;
        
        Stack stack1 = new Stack();
        Stack stack2 = new Stack();
        
        while (true) {
            
            while (!done1) {
                
                if (current1!=null) {
                    stack1.push(current1);
                    current1 = current1.left;
                }
                else {
                    /*
                       if it is empty then it makes done1 = 1 it takes out from the loop.
                       if we did not check isEmpty() condition,
                       it always try to pop element from stack and in Empty case it will throw RTE.
                     */
                    if(stack1.isEmpty()) {
                        done1 = true;
                    } else {
                        current1 = stack1.pop();
                        val1 = current1.info;
                        current1 = current1.right;
                        done1 = true;
                    }
                }
                
            }

            while (!done2) {

                if (current2!=null) {
                    stack2.push(current2);
                    current2 = current2.right;
                }
                else {
                    if(stack2.isEmpty()) {
                        done2 = true;
                    } else {
                        current2 = stack2.pop();
                        val2 = current2.info;
                        current2 = current2.left;
                        done2 = true;
                    }
                }

            }
            
            if((val1!=val2) && val1 + val2 == sum) {
                System.out.println("Found sum pair - " + val1 + " " + val2);
                break;
            }
            
            else if(val1 + val2 > sum) {
                done2 = false;
            }

            else if(val1 + val2 < sum) {
                done1 = false;
            }
            
            if(val1 >= val2) {
                System.out.println("No pair found" );
                break;
            }
            
        }
        
        
    }
    
}


/*

The Brute Force Solution is to consider each pair in BST and check whether the sum equals to X. 
The time complexity of this solution will be O(n^2).

A Better Solution is to create an auxiliary array and store Inorder traversal of BST in the array. 
The array will be sorted as Inorder traversal of BST always produces sorted data. Once we have the Inorder traversal, 
we can pair in O(n) time (See this for details). This solution works in O(n) time, but requires O(n) auxiliary space.

A space optimized solution is discussed in previous post. The idea was to first in-place convert BST to Doubly 
Linked List (DLL), then find pair in sorted DLL in O(n) time. This solution takes O(n) time and O(Logn) extra space, 
but it modifies the given BST.

The solution discussed below takes O(n) time, O(Logn) space and doesn’t modify BST. The idea is same as finding 
the pair in sorted array (See method 1 of this for details). We traverse BST in Normal Inorder and Reverse 
Inorder simultaneously. In reverse inorder, we start from the rightmost node which is the maximum value node. 
In normal inorder, we start from the left most node which is minimum value node. We add sum of current nodes in 
both traversals and compare this sum with given target sum. If the sum is same as target sum, we return true. 
If the sum is more than target sum, we move to next node in reverse inorder traversal, otherwise we move to next node 
in normal inorder traversal. If any of the traversals is finished without finding a pair, we return false

Normally It'll never contain all the nodes in both stack.
max no of nodes in stakes is equals to height of bst.
so avg case complexity is O(logn).
worst case complexity is O(n). when tree is skew.

 */