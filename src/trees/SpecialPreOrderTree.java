/*

Construct a special tree from given preorder traversal
Given an array ‘pre[]’ that represents Preorder traversal of a spacial binary tree 
where every node has either 0 or 2 children. One more array ‘preLN[]’ is given which has 
only two possible values ‘L’ and ‘N’. The value ‘L’ in ‘preLN[]’ indicates that the 
corresponding node in Binary Tree is a leaf node and value ‘N’ indicates that the corresponding 
node is non-leaf node. Write a function to construct the tree from the given two arrays.

 */

package trees;

/**
 * Created by poorvank on 6/10/15.
 */
public class SpecialPreOrderTree {

    private static int index = 0;

    public static void main(String[] args) {

        int[] pre = new int[]{10, 30, 20, 5, 15};
        char[] preLN = new char[]{'N', 'N', 'L', 'L', 'L'};
        System.out.println("Inorder is - ");
        Traversal.in_Order(constructTree(pre, preLN, pre.length));
//        System.out.println(index);
    }

    private static Node constructTree(int[] pre, char[] preLN, int n) {


        //Otherwise the first 'N' is not recognised
        int i = index;

        if (index == n) {
            return null;
        }

        Node root = new Node(pre[index]);
        // System.out.println(index);
        index++;

        if (preLN[i] == 'N') {

            root.left = constructTree(pre, preLN, n);
            root.right = constructTree(pre, preLN, n);

        }

        return root;

    }

}
