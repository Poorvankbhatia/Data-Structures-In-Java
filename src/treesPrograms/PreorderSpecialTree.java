/*

Given an array ‘pre[]’ that represents Preorder traversal of a spacial binary tree where every node has either 0 or 2 children. 
One more array ‘preLN[]’ is given which has only two possible values ‘L’ and ‘N’. 
The value ‘L’ in ‘preLN[]’ indicates that the corresponding node in Binary Tree is a
 leaf node and value ‘N’ indicates that the corresponding node is non-leaf node.
  Write a function to construct the tree from the given two arrays.

 */

package treesPrograms;

public class PreorderSpecialTree {

    private static int index = 0;

    public static void main(String[] args) {

        int[] pre = new int[]{10, 30, 20, 5, 15};
        char[] preLN = new char[]{'N', 'N', 'L', 'L', 'L'};
        Node root = constructTree(new Node(0), pre, preLN);
        Traversal.in_Order(root);

    }

    private static Node constructTree(Node root, int[] pre, char[] preLN) {

        root = new Node(pre[index]);
        index++;


        if (preLN[index - 1] == 'N') {

            //System.out.println(root.info);
            root.left = constructTree(root.left, pre, preLN);
            root.right = constructTree(root.right, pre, preLN);

        }

        return root;


    }

}