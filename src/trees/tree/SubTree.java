package trees.tree;

public class SubTree {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        Node subRoot = Input.treeInput();

        if (isSubTree(root, subRoot)) {
            System.out.println("Subtree");
        } else {
            System.out.println("Nope");
        }

    }

    public static boolean isSubTree(Node root, Node subRoot) {

        if (subRoot == null) {
            return true;
        }

        if (root == null) {
            return false;
        }

        if (isIdentical(root, subRoot)) {
            return true;
        }

        return (isIdentical(root.left, subRoot) || isIdentical(root.right, subRoot));


    }

    public static boolean isIdentical(Node root1, Node root2) {

        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null || root2 == null) {
            return false;
        }


        return (root1.info == root2.info && isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right));


    }


}

/*


Time worst case complexity of above solution is O(mn) where m and n are number of nodes in given two trees.tra

 */