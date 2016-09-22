/*

Find the first non-repeating character from a stream of characters
Given a stream of characters, find the first non-repeating character from stream. 
You need to tell the first non-repeating character in O(1) time at any moment.

 */

package miscellaneous;

/**
 * Created by poorvank on 8/27/15.
 */


public class FirstNonRepeatingCharacterStream {


    private static class DLNode {

        char ch;
        DLNode next;
        DLNode prev;

        public DLNode(char ch) {
            this(ch, null, null);
        }

        public DLNode(char ch, DLNode next, DLNode prev) {
            this.ch = ch;
            this.next = next;
            this.prev = prev;
        }

    }

    private static class DLList {

        DLNode head, tail;

        public DLList() {
            head = null;
            tail = null;
        }

        public void addToList(char ch) {

            if (head == null) {
                head = new DLNode(ch);
                tail = head;
            } else {
                DLNode temp = new DLNode(ch);
                tail.next = temp;
                temp.prev = tail;
                tail = temp;
            }

        }

        public void deleteFromList(DLNode node) {

            if (node != tail && node != head) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            } else if (head == tail) {
                head = tail = null;
            } else if (node == tail) {
                tail = tail.prev;
                tail.next = null;
            } else {
                head = head.next;
                head.prev = null;
            }


        }

    }

    public static void main(String[] args) {

        boolean[] repeated = new boolean[256];
        DLNode[] inDLL = new DLNode[256];
        DLList list = new DLList();

        String stream = "geeksforgeeksandgeeksquizfor";

        for (int i = 0; i < stream.length(); i++) {

            char ch = stream.charAt(i);

            System.out.println("Reading - " + ch + " from stream");

            if (!repeated[ch]) {

                if (inDLL[ch] == null) {

                    list.addToList(ch);
                    inDLL[ch] = list.tail;
                } else {
                    list.deleteFromList(inDLL[ch]);
                    System.out.println("deleted - " + ch);
                    inDLL[ch] = null;
                    repeated[ch] = true;
                }


            }

            if (list.head != null) {
                System.out.println("First Non repeating character - " + list.head.ch);
            }

        }

    }

}


/*

The idea is to use a DLL (Doubly Linked List) to efficiently get the first non-repeating character from a stream. 
The DLL contains all non-repeating characters in order, i.e., the head of DLL contains first non-repeating character, 
the second node contains the second non-repeating and so on.
We also maintain two arrays: one array is to maintain characters that are already visited two or more times, we call it 
repeated[], the other array is array of pointers to linked list nodes, we call it inDLL[]. The size of both arrays is
 equal to alphabet size which is typically 256.

1) Create an empty DLL. Also create two arrays inDLL[] and repeated[] of size 256. 
   inDLL is an array of pointers to DLL nodes. repeated[] is a boolean array, 
   repeated[x] is true if x is repeated two or more times, otherwise false. 
   inDLL[x] contains pointer to a DLL node if character x is present in DLL, 
   otherwise NULL. 

2) Initialize all entries of inDLL[] as NULL and repeated[] as false.

3) To get the first non-repeating character, return character at head of DLL.

4) Following are steps to process a new character 'x' in stream.
  a) If repeated[x] is true, ignore this character (x is already repeated two
      or more times in the stream) 
  b) If repeated[x] is false and inDLL[x] is NULL (x is seen first time)
     Append x to DLL and store address of new DLL node in inDLL[x].
  c) If repeated[x] is false and inDLL[x] is not NULL (x is seen second time)
     Get DLL node of x using inDLL[x] and remove the node. Also, mark inDLL[x] 
     as NULL and repeated[x] as true.
Note that appending a new node to DLL is O(1) operation if we maintain tail pointer. Removing a node from DLL is 
also O(1). So both operations, addition of new character and finding first non-repeating character take O(1) time

 */