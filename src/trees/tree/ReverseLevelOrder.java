package trees.tree;

public class ReverseLevelOrder {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        int height = TreeHeight.height(root);
        for (int i = height; i >= 1; i--) {
            reverse(root, i);
        }

    }

    private static void reverse(Node root, int level) {

        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.info + " ");
        } else if (level > 1) {

            reverse(root.left, level - 1);
            reverse(root.right, level - 1);

        }


    }

}


//Can also be done using a queue and a map

