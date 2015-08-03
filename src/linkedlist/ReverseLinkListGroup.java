package linkedlist;

/**
 * Created by poorvank on 6/6/15.
 */

class RGLLNode {

    Integer info;
    RGLLNode link;

    public RGLLNode(int n) {

        this(n, null);
    }

    public RGLLNode(int n, RGLLNode node) {

        info = n;
        link = node;

    }

}


public class ReverseLinkListGroup {

    public static void main(String[] args) {

        RGLLNode head = new RGLLNode(1);
        head.link = new RGLLNode(2);
        head.link.link = new RGLLNode(3);
        head.link.link.link = new RGLLNode(4);
        head.link.link.link.link = new RGLLNode(5);
        head.link.link.link.link.link = new RGLLNode(6);

        printList(head);

        head = reverseGroups(head, 2);

        printList(head);


    }

    private static RGLLNode reverseGroups(RGLLNode head, int k) {

        RGLLNode current = head, next = null, previous = null;

        int count = 0;

        while (current != null && count < k) {

            next = current.link;
            current.link = previous;
            previous = current;
            current = next;
            count++;

        }

        if (next != null) {
            head.link = reverseGroups(next, k);
        }

        return previous;
    }

    private static void printList(RGLLNode node) {

        RGLLNode temp = node;

        while (temp != null) {
            System.out.print(temp.info + " ");
            temp = temp.link;
        }

        System.out.println();

    }

}
