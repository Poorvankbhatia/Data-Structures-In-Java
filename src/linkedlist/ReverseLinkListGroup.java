package linkedlist;

/**
 * Created by poorvank on 6/6/15.
 */

class RGLLNode {

    Integer info;
    RGLLNode nextLink;

    public RGLLNode(int n) {

        this(n, null);
    }

    public RGLLNode(int n, RGLLNode node) {

        info = n;
        nextLink = node;

    }

}


public class ReverseLinkListGroup {

    public static void main(String[] args) {

        RGLLNode head = new RGLLNode(1);
        head.nextLink = new RGLLNode(2);
        head.nextLink.nextLink = new RGLLNode(3);
        head.nextLink.nextLink.nextLink = new RGLLNode(4);
        head.nextLink.nextLink.nextLink.nextLink = new RGLLNode(5);
        head.nextLink.nextLink.nextLink.nextLink.nextLink = new RGLLNode(6);

        printList(head);

        head = reverseGroups(head, 4);

        printList(head);

        System.out.println("Reversing List via recursion!");

        head = reverseUsingRecursion(head);

        printList(head);

    }

    private static RGLLNode reverseUsingRecursion(RGLLNode head) {


        if (head == null) {
            return null;
        }

        RGLLNode first = head;
        RGLLNode rest = head.nextLink;

        if (rest == null) {
            return first;
        }

        rest = reverseUsingRecursion(rest);

        first.nextLink.nextLink = first;

        first.nextLink = null;

        return rest;

    }

    private static RGLLNode reverseGroups(RGLLNode head, int k) {

        RGLLNode current = head, next = null, previous = null;

        int count = 0;

        while (current != null && count < k) {

            next = current.nextLink;
            current.nextLink = previous;
            previous = current;
            current = next;
            count++;

        }

        if (next != null) {
            head.nextLink = reverseGroups(next, k);
        }

        return previous;
    }

    private static void printList(RGLLNode node) {

        RGLLNode temp = node;

        while (temp != null) {
            System.out.print(temp.info + " ");
            temp = temp.nextLink;
        }

        System.out.println();

    }

}
