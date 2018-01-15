/*

Consider a Linked List with each Node, in addition to having a 'next' pointer also has a 'random' pointer.
The 'random' pointer points to some random other Node on the linked list. It may also point to NULL.
To simplify things, no two 'random' pointers will point to the same node, but more than 1 Node's random pointer can point to NULL.

Now we are required to reverse the direction of all the pointers (both the 'next' and 'random') of the Linked list.
The constraint is the solution MUST be O(1) space complexity (A constant number of new nodes can be created but not proportional
to the length of the list)

 */
package linkedlist;

/**
 * Created by poorvank.b on 14/01/18.
 */
public class ReverseLinkedListWithRandomPointers {

    private static class SpecialNode {

        int info;
        SpecialNode nextLink;
        SpecialNode jumpLink;

        public SpecialNode(int info, SpecialNode nextLink, SpecialNode jumpLink) {
            this.info = info;
            this.nextLink = nextLink;
            this.jumpLink = jumpLink;
        }

        public SpecialNode(int info) {
            this(info,null,null);
        }
    }

    public static SpecialNode reverseList(SpecialNode head) {

        if(head==null || head.nextLink==null) {
            return head;
        }

        SpecialNode current=head;
        SpecialNode prev=null;
        SpecialNode next;

        while (current!=null) {
            next = current.nextLink;
            current.nextLink=prev;
            prev = current;
            current = next;
        }

        SpecialNode newHead = prev;

        while (prev != null) {
            if(prev.jumpLink==null) {
                current = prev;
                SpecialNode parent = findParent(current,newHead);
                while (parent!=null) {
                    current = parent;
                    parent = findParent(current,newHead);
                }
                reverseRandom(current);
            }
            prev = prev.nextLink;
        }


        for (current=newHead;current!=null;current=current.nextLink) {
            if(current.jumpLink!=null && current.jumpLink.info==Integer.MAX_VALUE) {
                current.jumpLink=null;
            }
        }

        return newHead;

    }

    private static SpecialNode findParent(SpecialNode node,SpecialNode head) {
        SpecialNode temp = head;
        while (temp!=null && temp.jumpLink!=node) {
            temp=temp.nextLink;
        }
        return temp;
    }

    private static void reverseRandom(SpecialNode head) {

        SpecialNode current = head;
        //This stops us from re-reversing a list
        SpecialNode prev = new SpecialNode(Integer.MAX_VALUE);
        SpecialNode next;
        while (current!=null) {
            next = current.jumpLink;
            current.jumpLink = prev;
            prev = current;
            current = next;
        }
    }

    private static void printList(SpecialNode head) {
        SpecialNode temp = head;
        while (temp!=null) {
            System.out.println(temp.info+" next "+(temp.nextLink!=null?temp.nextLink.info:null) +" random "+(temp.jumpLink!=null?temp.jumpLink.info:null));
            temp = temp.nextLink;
        }
        System.out.println("\n");
    }


    public static void main(String[] args) {
        SpecialNode s1 = new SpecialNode(1);
        SpecialNode s2 = new SpecialNode(2);
        SpecialNode s3 = new SpecialNode(3);
        SpecialNode s4 = new SpecialNode(4);
        s1.nextLink = s2;
        s1.jumpLink = s3;
        s2.nextLink = s3;
        s2.jumpLink = s1;
        s3.nextLink = s4;
        s3.jumpLink = s4;
        s4.nextLink = null;
        s4.jumpLink = null;

        printList(s1);

        SpecialNode newHead = reverseList(s1);

        printList(newHead);;

    }

}

/*

https://stackoverflow.com/questions/8410714/reversing-a-linkedlist-with-nodes-having-a-random-pointer

 */