/*

Given a binary tree, where every node value is a Digit from 1-9 .
Find the sum of all the numbers which are formed from root to leaf paths.

For example consider the following Binary Tree.

                                          6
                                      /      \
                                    3          5
                                  /   \          \
                                 2     5          4  
                                      /   \
                                     7     4
  There are 4 leaves, hence 4 root to leaf paths:
   Path                    Number
  6->3->2                   632
  6->3->5->7               6357
  6->3->5->4               6354
  6->5>4                    654   
Answer = 632 + 6357 + 6354 + 654 = 13997 

 */

package trees.tree;

/**
 * Created by poorvank on 6/15/15.
 */
public class RootToLeafSum {

    public static void main(String[] args) {

        Node root = new Node(1);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(4);
        root.right.right = new Node(6);

        System.out.println(sum(root, 0));


    }

    private static int sum(Node root, int val) {

        if (root == null) {
            return 0;
        }

        val = (val * 10) + root.info;

        if (root.right == null && root.left == null) {
            System.out.println(val);
            return val;
        }

        return sum(root.left, val) + sum(root.right, val);


    }

}


/*


The idea is to do a preorder traversal of the tree. In the preorder traversal, 
keep track of the value calculated till the current node, let this value be val. 
For every node, we update the val as val*10 plus node’s data.

 */