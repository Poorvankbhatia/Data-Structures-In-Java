/*

Given two Binary Search Trees(BST), print the elements of both BSTs in sorted form.
The expected time complexity is O(m+n) where m is the number of nodes in first tree and
n is the number of nodes in second tree. Maximum allowed auxiliary space is O(height of the first tree + height of the second tree).

Examples:

First BST
       3
    /     \
   1       5
Second BST
    4
  /   \
2       6
Output: 1 2 3 4 5 6


First BST
          8
         / \
        2   10
       /
      1
Second BST
          5
         /
        3
       /
      0
Output: 0 1 2 3 5 8 10

 */
package trees.tree;
import utility.Stack;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank.b on 10/04/17.
 */
public class MergeBST {

    public List<Integer> merge(Node root1, Node root2) {

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        List<Integer> result = new ArrayList<>();

        if(root1==null) {
            inorder(root2,result);
            return result;
        }

        if(root2==null) {
            inorder(root1,result);
            return result;
        }

        while (!stack1.isEmpty() || !stack2.isEmpty() || root1!=null || root2!=null) {

            if(root1!=null || root2!=null) {

                if(root1!=null) {
                    stack1.push(root1);
                    root1 = root1.left;
                }
                if(root2!=null) {
                    stack2.push(root2);
                    root2 = root2.left;
                }

            } else {

                if(stack1.isEmpty()) {
                    while (!stack2.isEmpty()) {
                        Node current = stack2.pop();
                        current.left=null;
                        inorder(current,result);
                    }
                    return result;
                }

                if(stack2.isEmpty()) {
                    while (!stack1.isEmpty()) {
                        Node current = stack1.pop();
                        current.left=null;
                        inorder(current,result);
                    }
                    return result;
                }

                root1 = stack1.pop();
                root2 = stack2.pop();

                if(root1.info<root2.info) {
                    result.add(root1.info);
                    root1 = root1.right;
                    stack2.push(root2);
                    root2=null;
                } else {
                    result.add(root2.info);
                    root2 = root2.right;
                    stack1.push(root1);
                    root1=null;
                }


            }

        }

        return result;

    }

    private void inorder(Node root,List<Integer> list) {

        if (root!=null) {
            inorder(root.left,list);
            list.add(root.info);
            inorder(root.right,list);
        }

    }

    public static void main(String[] args) {
        Node root1 = new Node(3);
        root1.left = new Node(1);
        root1.right = new Node(5);


        Node root2 = new Node(4);
        root2.left = new Node(2);
        root2.right = new Node(6);

        System.out.print(new MergeBST().merge(root1,root2));
    }

}

/*

Time Complexity: O(m+n)
Auxiliary Space: O(height of the first tree + height of the second tree)

The idea is to use iterative inorder traversal. We use two auxiliary stacks for two BSTs. Since we need to print the elements in sorted form,
whenever we get a smaller element from any of the trees, we print it. If the element is greater, then we push it back to stack for the next iteration.

 */