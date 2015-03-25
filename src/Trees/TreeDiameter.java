package Trees;


import Trees.Input;
import Trees.Node;
import Trees.TreeHeight;

class Height {

    public int value;

    public Height(int value) {
        this.value = value;
    }

}

public class TreeDiameter {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        System.out.print("diameter of tree is - " + diameter(root));

    }

    private static int diameter(Node root) {

        if (root == null) {
            return 0;
        }

        int lHeight = TreeHeight.height(root.left);
        int rHeight = TreeHeight.height(root.right);

        int lDiameter = diameter(root.left);
        int rDiameter = diameter(root.right);
        
        
        /* Return max of following three
        1) Diameter of left subtree
        2) Diameter of right subtree
        3) Height of left subtree + height of right subtree + 1 */
        return Input.maxOfTwoNo((lHeight + rHeight + 1), Input.maxOfTwoNo(lDiameter, rDiameter));

    }

}