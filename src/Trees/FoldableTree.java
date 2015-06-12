/*

A tree can be folded if left and right subtrees of the tree are structure wise mirror image of each other.
 An empty tree is considered as foldable.

Consider the below trees:
(a) and (b) can be folded.
(c) and (d) cannot be folded.

(a)
       10
     /    \
    7      15
     \    /
      9  11

(b)
        10
       /  \
      7    15
     /      \
    9       11

(c)
        10
       /  \
      7   15
     /    /
    5   11

(d)

         10
       /   \
      7     15
    /  \    /
   9   10  12

 */

package Trees;

public class FoldableTree {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        if (isFoldable(root)) {
            System.out.println("Foldable");
        } else {
            System.out.println("Nope");
        }

    }

    private static boolean isFoldable(Node root) {

        if (root == null) {
            return true;
        }

        return isFoldUtil(root.right, root.left);

    }

    private static boolean isFoldUtil(Node node1, Node node2) {

        if (node1 == null && node2 == null) {
            return true;
        }

        if (node1 == null || node2 == null) {
            return false;
        }

        return (isFoldUtil(node1.left, node2.right) && isFoldUtil(node1.right, node2.left));
    }

}

/*

There are mainly two functions:

// Checks if tree can be folded or not

IsFoldable(root)
1) If tree is empty then return true
2) Else check if left and right subtrees are structure wise mirrors of
    each other. Use utility function IsFoldableUtil(root->left,
    root->right) for this.
// Checks if n1 and n2 are mirror of each other.

IsFoldableUtil(n1, n2)
1) If both trees are empty then return true.
2) If one of them is empty and other is not then return false.
3) Return true if following conditions are met
   a) n1->left is mirror of n2->right
   b) n1->right is mirror of n2->left

 */