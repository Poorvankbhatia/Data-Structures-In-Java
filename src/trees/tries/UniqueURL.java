/*

Given a very long list of URLs, find the first URL which is unique ( occurred exactly once ).


 */

package trees.tries;

import java.util.LinkedList;

/**
 * Created by poorvank on 4/23/15.
 */

class DLLNode {

    public String url;
    public DLLNode next;
    public DLLNode previous;

    public DLLNode(String url) {

        this.url = url;
        next = null;
        previous = null;

    }

}

class DLList {

    public DLLNode head;

    public DLList() {
        head = null;
    }

    public DLLNode addToList(String url) {

        if (head == null) {
            head = new DLLNode(url);
        } else {
            DLLNode temp;
            temp = head;
            head = new DLLNode(url);
            head.next = temp;
            temp.previous = head;
        }

        return head;

    }

    public void deleteFromList(DLLNode node) {

        if (node.previous != null && node.next != null) {
            node.previous.next = node.next;
            node.next.previous = node.previous;
            return;
        }
        //When the last Node is to be deleted
        else if (node.previous != null) {
            node.previous.next = null;
            return;
        }
        //will only happen when the first node is to be deleted
        else if (node.next != null) {
            head = node.next;
            node.next.previous = null;
            return;
        }
        //Only Node present
        else {
            head = null;
        }
    }

    public void printList() {

        DLLNode temp = head;
        while (temp != null) {
            System.out.print(temp.url + " ");
            temp = temp.next;
        }

    }

}

class UTrieNode {

    public LinkedList<UTrieNode> childList;
    public DLLNode dllNode;
    char content;
    boolean isLeaf;
    int count;

    public UTrieNode(char content) {

        this.content = content;
        isLeaf = false;
        count = 0;
        childList = new LinkedList<>();
        dllNode = null;

    }

    public UTrieNode subNode(char c) {

        if (childList != null) {

            for (UTrieNode uTrieNode : childList) {

                if (uTrieNode.content == c) {
                    return uTrieNode;
                }

            }

        }

        return null;
    }

}

class UTrie {

    public UTrieNode root;
    DLList list = new DLList();

    public UTrie() {

        root = new UTrieNode(' ');

    }

    public void insert(String url) {
        UTrieNode node;
        if ((node = search(url)) != null) {
            System.out.println("Already present - " + url);
            if (node.dllNode == null) {
                System.out.println("dllNode is null");
            } else {
                System.out.println("dllNode is present");
                list.deleteFromList(node.dllNode);
                node.dllNode = null;
            }
        } else {

            System.out.println("Creating new Trie for " + url);

            UTrieNode current = root;

            for (char ch : url.toCharArray()) {

                UTrieNode subNode = current.subNode(ch);

                if (subNode == null) {
                    current.childList.add(new UTrieNode(ch));
                    current = current.subNode(ch);
                } else {
                    current = subNode;
                }

                current.count++;

            }

            current.isLeaf = true;
            current.dllNode = list.addToList(url);

        }
        list.printList();
        System.out.println("\n");

    }

    public UTrieNode search(String url) {

        UTrieNode current = root;

        for (char ch : url.toCharArray()) {

            UTrieNode subNode = current.subNode(ch);

            if (subNode == null) {
                return null;
            } else {
                current = subNode;
            }

        }

        if (current.isLeaf) {
            return current;
        } else {
            return null;
        }

    }

}

public class UniqueURL {

    public static void main(String[] args) {

        String[] urls = new String[]{"http://www.google.com/",
                "http://www.yahoo.com/",
                "http://www.amazon.com/",
                "http://www.apache.com/",
                "http://www.google.com/",
                "http://www.mycar.com/",
                "http://www.microsoft.com/",
                "http://www.casandra.com/",
                "http://www.apache.com/",
                "http://www.google.com/",
                "http://www.oracle.com/",
                "http://www.yahoo.com/",
                "http://www.oracle.com/",
                "http://www.facebook.com/",
                "http://www.pandora.com/",
                "http://www.microsoft.com/",
                "http://www.google.com/",
                "http://www.mycar.com/",
                "http://www.amazon.com/",
                "http://www.apple.com/",
        };

        UTrie uTrie = new UTrie();

        for (String url : urls) {
            uTrie.insert(url);
        }

        System.out.println("\nFinal unique list is -: ");
        uTrie.list.printList();

    }


}


/*

can solve this in O(n) using a combination of trie and linked list. The leaf node of a trie maintains a 
flag to record duplicate urls and
pointer to a node in a link list. If you encounter a new url, add a node to the head 
of the linked list and set the pointer in the trie. Whenever you encounter a url that 
is already in the trie, if the flag is not set, then set the flag, delete the node from the 
linked list and set pointer to null. If the flag is already set, then ignore and read the next url. 
After processing all the urls, the link list should only contain the unique urls and the node at the tail 
is the first unique url from the list. For n urls, inserting urls into the trie in O(n) and link list 
operations are all constant time. The node could just keep the index of the url in the list so that we 
don't have to store urls in the link list as well.


The trie insertion time is k, where k is the length of the string being inserted. 
So the total time is O(nk). Since the length of the URL's doesn't depend on n, though, 
and can be expected to be relatively constant, this is O(n).


Deletion from a doubly linked list takes O(1) time
 */