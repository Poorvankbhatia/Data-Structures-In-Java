package treesPrograms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 6/9/15.
 */
public class SubTree2 {

    private static List<Integer> inOrderRoot = new ArrayList<>();
    private static List<Integer> inOrderSubRoot = new ArrayList<>();
    private static List<Integer> preOrderRoot = new ArrayList<>();
    private static List<Integer> preOrderSubRoot = new ArrayList<>();

    public static void main(String[] args) {

        Node root = new Node(6);
        root.left = new Node(3);
        root.right = new Node(9);
        root.left.right = new Node(5);
        root.left.left = new Node(2);

        Node subRoot = new Node(3);
        subRoot.right = new Node(5);
        subRoot.left = new Node(2);

        System.out.println("is Subtree " + isSubTree(root, subRoot));

    }

    private static boolean isSubTree(Node root, Node subRoot) {

        setInOrder(root, true);
        setPreOrder(root, true);

        setInOrder(subRoot, false);
        setPreOrder(subRoot, false);

        System.out.println(inOrderRoot.toString() + " " + inOrderSubRoot.toString());
        System.out.println(preOrderRoot.toString() + " " + preOrderSubRoot.toString());

        if (listToString(inOrderRoot).contains(listToString(inOrderSubRoot)) && listToString(preOrderRoot).contains(listToString(preOrderSubRoot))) {
            return true;
        }

        return false;

    }

    private static void setInOrder(Node node, boolean flag) {

        if (node == null) {
            return;
        }
        setInOrder(node.left, flag);
        if (flag) {
            inOrderRoot.add(node.info);
        } else {
            inOrderSubRoot.add(node.info);
        }
        setInOrder(node.right, flag);

    }

    private static String listToString(List<Integer> list) {

        StringBuilder sb = new StringBuilder();

        for (int i : list) {
            sb.append(i);
        }

        return sb.toString();

    }

    private static void setPreOrder(Node node, boolean flag) {

        if (node == null) {
            return;
        }
        if (flag) {
            preOrderRoot.add(node.info);
        } else {
            preOrderSubRoot.add(node.info);
        }
        setPreOrder(node.left, flag);
        setPreOrder(node.right, flag);

    }

}
