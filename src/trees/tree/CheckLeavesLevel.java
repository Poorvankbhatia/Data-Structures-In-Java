/*

Check if all leaves are at same level

 */

package trees.tree;

/**
 * Created by poorvank on 6/9/15.
 */
public class CheckLeavesLevel {

    private static int leafLevel = 0;

    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);

        System.out.println(checkSameLevel(root, 0));

    }


    private static boolean checkSameLevel(Node root, int level) {

        if (root == null) {
            return true;
        }

        if (root.left == null && root.right == null) {

            if (leafLevel == 0) {
                leafLevel = level;
                return true;
            }

            return (level == leafLevel);

        }

        return checkSameLevel(root.left, level + 1) && checkSameLevel(root.right, level + 1);

    }

}


/*

The idea is to first find level of the leftmost leaf and store it in a variable leafLevel. 
Then compare level of all other leaves with leafLevel, 
if same, return true, else return false. We traverse the given Binary Tree in Preorder fashion.
 An argument leaflevel is passed to all calls. The value of leafLevel is initialized as 0 to 
 indicate that the first leaf is not yet seen yet. The value is updated when we find first leaf. 
 Level of subsequent leaves (in preorder) is compared with leafLevel.

 */