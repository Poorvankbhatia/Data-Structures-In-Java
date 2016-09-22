package trees.tree;

public class CheckBST {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        if (isBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE)) {
            System.out.println("Its a BST");
        } else {
            System.out.println("Nope");
        }

    }
    
    
    /*
      traverses down the tree keeping track of the narrowing min and max allowed values as it goes,
      looking at each node only once.
     */

    private static boolean isBST(Node root, int max, int min) {

        if (root == null) {
            return true;
        } else if (root.info < min || root.info > max) {
            return false;
        }

        return (isBST(root.left, root.info - 1, min) && isBST(root.right, max, root.info + 1));


    }

}