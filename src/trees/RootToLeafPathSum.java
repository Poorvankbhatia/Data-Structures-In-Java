/*

Root to leaf path sum equal to a given number
Given a binary tree and a number, return true if the tree has a root-to-leaf path such that 
adding up all the values along the path equals the given number. Return false if no such path can be found.


            10
          /   \
        8      2
      /  \    /
    3     5  2
    
    For example, in the above tree root to leaf paths exist with following sums.

    21 –> 10 – 8 – 3
    23 –> 10 – 8 – 5
    14 –> 10 – 2 – 2
    
    
 */


package trees;

/**
 * Created by poorvank on 12/27/15.
 */
public class RootToLeafPathSum {

    static int sum = 0;

    public static void main(String[] args) {

        Node root = new Node(10);
        root.left = new Node(8);
        root.left.left = new Node(3);
        root.left.right = new Node(5);
        root.right = new Node(2);
        root.right.left = new Node(2);

        int n = 21;
        System.out.println(isSumPresent(root, n));

    }


    private static boolean isSumPresent(Node root, int sum) {

        //In case of an empty tree
        if (root == null) {
            return (sum == 0);
        } else {

            int subSum = sum - root.info;

            if (subSum < 0) {
                return false;
            }

            if (subSum == 0 && root.left == null && root.right == null) {
                return true;
            } else {

                boolean ans = false;
                if (root.left != null) {
                    ans = ans || isSumPresent(root.left, subSum);
                }
                if (root.right != null) {
                    ans = ans || isSumPresent(root.right, subSum);
                }
                return ans;

            }

        }

    }


}
