/*

Check if a binary tree is subtree of another binary tree
Given two binary trees, check if the first tree is subtree of the second one.
A subtree of a tree T is a tree S consisting of a node in T and all of its descendants in T.
The subtree corresponding to the root node is the entire tree; the subtree corresponding to any other node is called a proper subtree.

For example, in the following case, tree S is a subtree of tree T.

        Tree 2
          10
        /    \
      4       6
       \
        30


        Tree 1
              26
            /   \
          10     3
        /    \     \
      4       6      3
       \
        30


 */

package trees.tree;

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

        return listToString(inOrderRoot).contains(listToString(inOrderSubRoot)) && listToString(preOrderRoot).contains(listToString(preOrderSubRoot));

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
