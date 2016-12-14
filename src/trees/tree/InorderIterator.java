/*

Supports previous(), next(), hasPrevious(), hasNext()
Space complexity should be O(logn)

 */
package trees.tree;


import utility.Stack;

import java.util.Iterator;

/**
 * Created by poorvank on 14/12/16.
 */
public class InorderIterator implements Iterator<Integer> {

    private Node previous;
    private Stack<Node> stack = new Stack<>();

    public InorderIterator(Node root) {
        previous = null;
        while (root!=null) {
            stack.push(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public Integer previous() {
        return previous!=null?previous.info:null;
    }

    public Integer next() {
        Node root = stack.pop();
        int result = root.info;
        if (root.right!=null) {
            root = root.right;
            while (root!=null) {
                stack.push(root);
                root = root.left;
            }
        }
        previous = root;
        return result;

    }


    public static void main(String[] args) {

        Node root = new Node(12);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.right = new Node(45);
        root.right.right = new Node(46);

        InorderIterator inorderIterator = new InorderIterator(root);

        while (inorderIterator.hasNext()) {
            Integer b =  inorderIterator.previous();
            System.out.println("previous - " + b);
            Integer a = inorderIterator.next();
            System.out.println("next - " + a);
        }

    }


}
