/*
Maximum Consecutive Increasing Path Length in Binary Tree
Given a Binary Tree find the length of the longest path which comprises of nodes with consecutive values in increasing order.
Every node is considered as a path of length 1.
Examples:

       10
      /    \
     /      \
    11        9
   / \        /\
  /   \      /  \
13    12    13   8
Maximum Consecutive Path Length is 3 (10, 11, 12)
Note: 10, 9 ,8 is NOT considered since
the nodes should be in increasing order.

	    5
          /  \
         /    \
        8      11
        /        \
       /          \
       9	  10
      /	          /
     /	         /
    6           15
Maximum Consecutive Path Length is 2 (8, 9).


 */

package trees.tree;

/**
 * Created by poorvank on 13/12/16.
 */
public class MaximumConsecutivePath {

    public int maxLenPath(Node root) {

        return maxLenPathUtil(root,1);

    }

    private int maxLenPathUtil(Node root,int currentLength) {

        if(root!=null) {


            if(root.left==null && root.right==null) {
                return currentLength;
            }

            int leftLength = 0,rightLength =0;

            if(root.left!=null) {
                leftLength = root.left.info-root.info==1?maxLenPathUtil(root.left,currentLength+1):maxLenPathUtil(root.left,currentLength);
            }
            if(root.right!=null) {
                rightLength = root.right.info-root.info==1?maxLenPathUtil(root.right,currentLength+1):maxLenPathUtil(root.right,currentLength);
            }

            return Math.max(Math.max(leftLength,rightLength),currentLength);

        }

        return currentLength;

    }

    public static void main(String[] args) {
        Node root = new Node(5);
        root.left = new Node(8);
        root.left.left = new Node(9);
        root.left.left.left = new Node(6);
        root.right = new Node(11);
        root.right.right = new Node(10);
        root.right.right.left = new Node(15);

        System.out.println(new MaximumConsecutivePath().maxLenPath(root));
    }

}

/*

Every node in the Binary Tree can either become part of the path which is starting from one of its parent node or a new path can start from this
 node itself. The key is to recursively find the path length for the left and right
sub tree and then return the maximum. Some cases need to be considered while traversing the tree which are discussed below.

 */