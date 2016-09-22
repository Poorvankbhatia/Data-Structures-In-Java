package trees.tree;

public class PrintRangeKeys {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println("Range keys are - ");
        printKeys(10, 22, root);

    }

    private static void printKeys(int k1, int k2, Node root) {

        if (root == null) {
            return;
        }

        if (root.info > k1) {
            printKeys(k1, k2, root.left);
        }

        if (root.info >= k1 && root.info <= k2) {
            System.out.println(root.info);
        }

        if (root.info < k2) {
            printKeys(k1, k2, root.right);
        }

    }

}


/*

  Algo used : 
  
  1) If value of root’s key is greater than k1, then recursively call in left subtree.
2) If value of root’s key is in range, then print the root’s key.
3) If value of root’s key is smaller than k2, then recursively call in right subtree.

 */