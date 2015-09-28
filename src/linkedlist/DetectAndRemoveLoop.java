/*

Detect and Remove Loop in a Linked List

 */
package linkedlist;

/**
 * Created by poorvank on 8/11/15.
 */
public class DetectAndRemoveLoop {

    public static void main(String[] args) {

        LLNode head = new LLNode(1);
        head.link = new LLNode(2);
        head.link.link = new LLNode(3);
        head.link.link.link = new LLNode(4);
        head.link.link.link.link = new LLNode(5, head.link);

        System.out.println("Before -> " + head.link.link.link.link.link.info);

        if (detect(head)) {
            while (head != null) {
                System.out.print(head.info + " ");
                head = head.link;
            }
        } else {
            System.out.println("No Loop Found");
        }

    }

    private static boolean detect(LLNode head) {

        LLNode slowPtr = head, fastPtr = head;

        while (slowPtr != null && fastPtr != null && fastPtr.link != null) {

            slowPtr = slowPtr.link;
            fastPtr = fastPtr.link.link;

            if (slowPtr == fastPtr) {

                removeLoop(head, slowPtr);
                return true;

            }

        }

        return false;

    }

    private static void removeLoop(LLNode head, LLNode loopNode) {

        LLNode hare = loopNode;
        LLNode tortoise = head;

        while (hare != tortoise) {
            hare = hare.link;
            tortoise = tortoise.link;
        }

        while (tortoise.link != hare) {
            tortoise = tortoise.link;
        }

        tortoise.link = null;

    }

}


/*

Detecting Loop:

Have two pointers, classically called hare and tortoise. Move hare by 2 steps and tortoise by 1. 
If they meet at some point, 
then there is surely a cycle and the meeting point is obviously inside the cycle.

Finding length of Loop:

Keep one pointer fixed at meeting point while increment the other until they are same again. 
Increment a counter as you go along and the counter value at meet will be the length of cycle.

Find the start of cycle

Take one pointer to start of the list and keep the other at the meeting point.
 Now increment both by one and the meet point is the start of loop. I verified the method using some cases 
 on paper, but I don't understand why it should work.

Read Floyd Detection Algorithm notes

Another soln

1) Detect Loop using Floyd’s Cycle detection algo and get the pointer to a loop node.
2) Count the number of nodes in loop. Let the count be k.
3) Fix one pointer to the head and another to kth node from head.
4) Move both pointers at the same pace, they will meet at loop starting node.
5) Get pointer to the last node of loop and make next of it as NULL.

 */