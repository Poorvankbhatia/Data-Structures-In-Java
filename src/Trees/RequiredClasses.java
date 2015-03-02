package Trees;

import java.util.Scanner;

class Node {

    public int info;
    public Node left;
    public Node right;

    public Node(int info) {
        this(info,null,null);
    }

    public Node(int info,Node left,Node right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

class Tree {

    public Node root;

    public Tree() {
        root = null;
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

        for (int i=0;i<5;i++) {
            root = tree.insert(Integer.parseInt((new Scanner(System.in)).nextLine()), root);
        }

        System.out.println("Root is = " + root.info);

        return root;

    }
    
}

class Stack {
    
    int MAX = 100;
    public Node[] stack = new Node[MAX];
    public int top = -1;
    
    public void push(Node a) {
        
        top++;
        if(isFull()) {
            System.out.println("Stack OverFlow");
        }
        stack[top] = a;
        
    }
    
    public boolean isFull() {
        
        if(top == MAX-1) {
            return true;
        }
        return false;
        
    }
    
    public boolean isEmpty() {
        
        if(top == -1) {
            return true;
        }
        return false;
    }
    
    public Node pop() {
        
        if(!isEmpty()) {
            Node a = stack[top];
            top --;
            return a;
        }
        System.out.println("Stack Underflow");
        return null;
        
    }
    
}