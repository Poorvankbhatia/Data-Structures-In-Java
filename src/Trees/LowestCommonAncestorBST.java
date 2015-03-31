package Trees;

/**
 * Created by poorvank on 3/25/15.
 */
public class LowestCommonAncestorBST {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.println(LCA(35, 10, root));

    }

    public static int LCA(int k1, int k2, Node root) {

        while (root != null) {

            if (root.info > k1 && root.info > k2) {
                root = root.left;
            }

            if (root.info < k1 && root.info < k2) {
                root = root.right;
            } else break;

        }

        return root.info;


    }


}
