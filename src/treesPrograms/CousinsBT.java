/*

Given the binary Tree and the two nodes say ‘a’ and ‘b’,
 determine whether the two nodes are cousins of each other or not.

Two nodes are cousins of each other if they are at same level and have different parents.

Example

     6
   /   \
  3     5
 / \   / \
7   8 1   3
Say two node be 7 and 1, result is TRUE.
Say two nodes are 3 and 5, result is FALSE.
Say two nodes are 7 and 5, result is FALSE.


 */

package treesPrograms;

/**
 * Created by poorvank on 6/9/15.
 */
public class CousinsBT {

    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);

        Node n1 = root.right.right;
        Node n2 = root.left.left;

        System.out.println(areCousin(root, n1, n2));

    }

    private static boolean areCousin(Node root, Node n1, Node n2) {

        if (level(root, n1, 1) == level(root, n2, 1) && !(isSibling(root, n1, n2))) {
            return true;
        }

        return false;
    }

    private static int level(Node root, Node n1, int level) {

        if (root == null) {
            return 0;
        }
        if (n1 == root) {
            return level;
        }

        int l = level(root.left, n1, level + 1);
        if (l != 0) {
            return l;
        } else {
            return level(root.right, n1, level + 1);
        }

    }

    private static boolean isSibling(Node root, Node n1, Node n2) {

        if (root == null) {
            return false;
        }

        return ((root.left == n1 && root.right == n2) || (root.right == n1 && root.left == n2)
                || isSibling(root.left, n1, n2) || (isSibling(root.right, n1, n2)));

    }


}


/*

The idea is to find level of one of the nodes. Using the found level, check if ‘a’ 
and ‘b’ are at this level. If ‘a’ and ‘b’ are at given level, then finally check if 
they are not children of same parent.

 */