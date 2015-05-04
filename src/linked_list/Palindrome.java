//Function to check if a singly linked list is palindrome

package linked_list;

/**
 * Created by poorvank on 4/28/15.
 */


public class Palindrome {

    public static void main(String[] args) {

        LLNode head = Input.listInput();
        System.out.println("\nIs palindrome " + isPalindrome(head));
        LinkedList.printList(head);
    }

    private static boolean isPalindrome(LLNode head) {

        LLNode slowPtr = head, fastPtr = head, midNode = null, prevSlowPtr = null, tmp = head;
        boolean flag = false;

        while (fastPtr != null && fastPtr.link != null) {

            fastPtr = fastPtr.link.link;
            prevSlowPtr = slowPtr;
            slowPtr = slowPtr.link;

        }

        //Odd number of nodes
        if (fastPtr != null) {
            midNode = slowPtr;
            slowPtr = slowPtr.link;
        }

        prevSlowPtr.link = null;

        slowPtr = LinkedList.reverse(slowPtr);

        flag = LinkedList.compare(tmp, slowPtr);

        slowPtr = LinkedList.reverse(slowPtr);

        if (midNode != null) {
            prevSlowPtr.link = midNode;
            midNode.link = slowPtr;
        } else {
            prevSlowPtr.link = slowPtr;
        }

        return flag;


    }

}


/*

METHOD 1 (Use a Stack)
A simple solution is to use a stack of list nodes. This mainly involves three steps.
1) Traverse the given list from head to tail and push every visited node to stack.
2) Traverse the list again. For every visited node, pop a node from stack and compare 
data of popped node with currently visited node.
3) If all nodes matched, then return true, else false.

Time complexity of above method is O(n), but it requires O(n) extra space. Following methods 
solve this with constant extra space.


METHOD 2 (By reversing the list)
This method takes O(n) time and O(1) extra space.
1) Get the middle of the linked list.
2) Reverse the second half of the linked list.
3) Check if the first half and second half are identical.
4) Construct the original linked list by reversing the second half again and attaching it back to the first half

 */