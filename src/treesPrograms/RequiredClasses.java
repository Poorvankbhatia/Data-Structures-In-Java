package treesPrograms;

import java.util.Scanner;

class Tree {

    public Node root;

    public Tree() {
        root = null;
    }

    public static Node search(int n, Node root) {

        if (root.info == n) {
            return root;
        }

        if (root.info < n) {
            return search(n, root.right);
        }

        if (root.info > n) {
            return search(n, root.left);
        }

        return null;

    }

    public static Node rightRotation(Node node) {

        Node temp;
        temp = node.left;

        if (temp != null) {
            node.left = temp.right;
            temp.right = node;
        }

        return temp;
    }

    public static Node leftRotation(Node node) {

        Node temp;
        temp = node.right;

        if (temp != null) {
            node.right = temp.left;
            temp.left = node;
        }

        return temp;

    }

    public Node insert(int info, Node root) {
        if (root == null) {
            root = new Node(info);
        } else if (info < root.info) {
            root.left = insert(info, root.left);
        } else if (info > root.info) {
            root.right = insert(info, root.right);
        } else {
            System.out.println("Node already present");
        }
        return root;
    }

}

class Input {

    public static Node treeInput() {

        System.out.println("Creating binary tree\n");
        System.out.println("Enter nodes(5) : ");
        Node root = null;
        Tree tree = new Tree();

        for (int i = 0; i < 5; i++) {
            root = tree.insert(Integer.parseInt((new Scanner(System.in)).nextLine()), root);
        }

        System.out.println("Root is = " + root.info);

        return root;

    }

    public static int maxOfTwoNo(int a, int b) {

        return a > b ? a : b;
    }

}

class Stack {

    public int top = -1;
    int MAX = 100;
    public Node[] stack = new Node[MAX];

    public Node peek() {

        return stack[top];

    }

    public void push(Node a) {

        top++;
        if (isFull()) {
            System.out.println("Stack OverFlow");
        }
        stack[top] = a;

    }

    public boolean isFull() {

        if (top == MAX - 1) {
            return true;
        }
        return false;

    }

    public boolean isEmpty() {

        if (top == -1) {
            return true;
        }
        return false;
    }

    public Node pop() {

        if (!isEmpty()) {
            Node a = stack[top];
            top--;
            return a;
        }
        System.out.println("Stack Underflow");
        return null;

    }

}

public class RequiredClasses {

    public static void main(String[] args) {

        Input.treeInput();

    }

}