/*

Check if each internal node of a BST has exactly one child
Given Preorder traversal of a BST, check if each non-leaf node has only one child. 
Assume that the BST contains unique entries.

Examples

Input: pre[] = {20, 10, 11, 13, 12}
Output: Yes
The give array represents following BST. In the following BST, every internal
node has exactly 1 child. Therefor, the output is true.
        20
       /
      10
       \
        11
          \
           13
           /
         12

 */

package trees;

import java.util.Scanner;

public class BSTOneNode {

    public static void main(String[] args) {

        int[] preOrder = new int[5];
        System.out.print("Enter preorder array ");
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; i++) {
            preOrder[i] = sc.nextInt();
        }
        System.out.print("Every child has one node " + isOnlyChild(preOrder));

    }

    private static boolean isOnlyChild(int[] preOrder) {

        for (int i = 0; i < preOrder.length - 1; i++) {

            int nextDiff = preOrder[i] - preOrder[i + 1];
            int lastDiff = preOrder[i] - preOrder[preOrder.length - 1];

            if (nextDiff * lastDiff < 0) {
                return false;
            }

        }
        return true;

    }

}

/*

Since all the descendants of a node must either be larger or smaller than the node. We can do following for every node in a loop.
1. Find the next preorder successor (or descendant) of the node.
2. Find the last preorder successor (last element in pre[]) of the node.
3. If both successors are less than the current node, or both successors are greater than the current node, then continue.
 Else, return false.
 
 

 */