/*

Two trees are called isomorphic if one of them can be obtained from other by a series of flips, 
i.e. by swapping left and right children of a number of nodes. 
Any number of nodes at any level can have their children swapped. Two empty trees are isomorphic.

 */

package Trees;

public class Isomorphic {

    public static void main(String[] args) {

        Node root1 = Input.treeInput();
        Node root2 = Input.treeInput();
        System.out.println(isIsomorphic(root1, root2));


    }

    private static boolean isIsomorphic(Node root1, Node root2) {

        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }

        if (root1.info != root2.info) {
            return false;
        }

        return ((isIsomorphic(root1.left, root2.left) && isIsomorphic(root1.right, root2.right)) ||
                (isIsomorphic(root1.left, root2.right) && isIsomorphic(root1.right, root2.left)));

    }

}

/*

We simultaneously traverse both trees. Let the current internal nodes of two trees being traversed be n1 and n2 respectively. There are following two conditions for subtrees rooted with n1 and n2 to be isomorphic.
1) Data of n1 and n2 is same.
2) One of the following two is true for children of n1 and n2
……a) Left child of n1 is isomorphic to left child of n2 and right child of n1 is isomorphic to right child of n2.
……b) Left child of n1 is isomorphic to right child of n2 and right child of n1 is isomorphic to left child of n2.

 */