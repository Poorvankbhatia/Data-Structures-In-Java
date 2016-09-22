package trees.tree;

/**
 * Created by poorvank on 3/23/15.
 */
public class DeepestLeftLeaf {

    private static int max = 0;
    private static Node leftNode = null;

    public static void main(String[] args) {

        Node root = Input.treeInput();
        deepestLeft(root, 0, true);
        System.out.println("Deepest left leaf node is - " + leftNode.info);


    }

    private static void deepestLeft(Node root, int level, boolean isLeft) {

        if (root == null) {
            return;
        }

        if (isLeft && root.left == null && root.right == null) {
            if (level > max) {
                max = level;
                leftNode = root;
            }
        }

        deepestLeft(root.left, level + 1, true);
        deepestLeft(root.right, level + 1, false);


    }

}
