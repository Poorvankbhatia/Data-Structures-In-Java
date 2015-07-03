package treesPrograms;

public class FixSwapNodes {

    private static Node previous = null, first = null, middle = null, last = null;

    public static void main(String[] args) {

        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(8);
        root.left.left = new Node(2);
        root.left.right = new Node(20);

        System.out.println("In Order before ");
        Traversal.in_Order(root);
        System.out.println("\nIn Order after ");
        Traversal.in_Order(correctBST(root));

    }

    private static Node correctBST(Node root) {

        correctBSTUtil(root);

        if (last != null) {
            swapNodes(first, last);
        } else {
            swapNodes(first, middle);
        }

        return root;
    }

    private static Node correctBSTUtil(Node root) {

        if (root != null) {

            Node newPrevious = correctBSTUtil(root.left);

            previous = newPrevious != null ? newPrevious : previous;

            if (previous != null && root.info < previous.info) {

                if (first == null) {
                    first = previous;
                    middle = root;
                } else {
                    last = root;
                }

            }
            previous = root;

            newPrevious = correctBSTUtil(root.right);

            return newPrevious != null ? newPrevious : previous;

        }

        return root;
    }

    private static void swapNodes(Node a, Node b) {

        int tmp = 0;

        if (a != null && b != null) {
            tmp = a.info;
            a.info = b.info;
            b.info = tmp;
        }

    }

}

/*

We have maintain three pointers, first, middle and last.
When we find the first point where current node value is smaller than previous node value
, we update the first with the previous node & middle with the current node. When we find 
the second point where current node value is smaller than previous node value, we update the last with the current node. 
In case #2, we will never find the second point. So, last pointer will not be updated. After processing,
 if the last node value is null, then two swapped nodes of BST are adjacent.


Method 2 : 
The inorder traversal of a BST produces a sorted array. So a simple method is to store inorder 
traversal of the input tree in an auxiliary array. Sort the auxiliary array. Finally, insert the auxiilary 
array elements back to the BST, keeping the structure of the BST same. Time complexity of this method is O(nLogn)
 and auxiliary space needed is O(n).

 */