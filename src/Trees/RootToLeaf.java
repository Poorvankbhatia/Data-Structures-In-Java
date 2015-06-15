package Trees;

import java.util.ArrayList;

public class RootToLeaf {

    //private static int length;

    public static void main(String[] args) {

        Node root = Input.treeInput();
        ArrayList<Integer> list = new ArrayList<Integer>();
        path(root, list);

    }

    private static void path(Node root, ArrayList<Integer> list) {

        if (root != null) {
            list.add(root.info);
        }

        if (root == null || (root.left == null && root.right == null)) {
            System.out.println(list);
            return;
        }

        path(root.left, new ArrayList<Integer>(list));
        path(root.right, new ArrayList<Integer>(list));

    }

}