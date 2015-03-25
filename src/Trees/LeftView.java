package Trees;

import Trees.Input;
import Trees.Node;

public class LeftView {

    private static int max = 0;

    public static void main(String[] args) {

        Node root = Input.treeInput();
        printLV(root, 1);

    }

    private static void printLV(Node root, int level) {

        if (root == null) {
            return;
        }

        if (level > max) {
            System.out.print(root.info + " ");
            max = level;
        }

        //Just changing the order of below statements changes left to right view

        printLV(root.left, level + 1);
        printLV(root.right, level + 1);

    }


}

/*

The problem can also be solved using simple recursive traversal. 
We can keep track of level of a node by passing a parameter to all recursive calls. 
The idea is to keep track of maximum level also. Whenever we see a node whose level 
is more than maximum level so far, we print the node because this is the first node in its level

 */