package trees.tries;

import java.util.LinkedList;

class TrieNode2 {

    char content;
    boolean isLeaf;
    int count;
    LinkedList<TrieNode2> childList;

    public TrieNode2(char ch) {

        content = ch;
        isLeaf = false;
        count = 0;
        childList = new LinkedList<TrieNode2>();

    }

    public TrieNode2 subNode(char ch) {

        if (childList != null) {
            for (TrieNode2 everyNode : childList) {
                if (everyNode.content == ch) {
                    return everyNode;
                }
            }
        }
        return null;

    }

}

class Trie2 {

    public TrieNode2 root;

    public Trie2() {

        root = new TrieNode2(' ');

    }

    public void delete(String word) {

        if (!search(word)) {
            System.out.println("Word Not present");
            return;
        }

        TrieNode2 current = root;

        for (char ch : word.toCharArray()) {

            TrieNode2 child = current.subNode(ch);

            if (child.count == 1) {
                current.childList.remove(child);
                return;
            } else {
                child.count--;
                current = child;
            }


        }

        current.isLeaf = false;

    }

    public void insert(String word) {

        TrieNode2 current = root;

        if (search(word)) {
            return;
        }

        for (char ch : word.toCharArray()) {

            TrieNode2 subNode = current.subNode(ch);

            if (subNode != null) {
                current = subNode;
            } else {
                current.childList.add(new TrieNode2(ch));
                current = current.subNode(ch);
            }

            current.count++;

        }

        current.isLeaf = true;

    }

    public boolean search(String word) {

        TrieNode2 current = root;

        for (char ch : word.toCharArray()) {

            TrieNode2 subNode = current.subNode(ch);

            if (subNode == null) {
                return false;
            } else {
                current = subNode;
            }

        }

        return current.isLeaf;

    }

}

public class TrieDelete {

    public static void main(String[] args) {

        Trie2 trie2 = new Trie2();
        trie2.insert("hello");
        trie2.insert("hye");
        trie2.insert("is");
        trie2.insert("if");

        if (trie2.search("is")) {
            System.out.println("Present");
        } else {
            System.out.println("Nope! not present");
        }

        trie2.delete("is");

        if (trie2.search("is")) {
            System.out.println("Present");
        } else {
            System.out.println("Nope! not present");
        }


    }

}

/*

Trie is an efficient information retrieval data structure. Using trie, 
search complexities can be brought to optimal limit (key length). 
If we store keys in binary search tree, a well balanced BST will need time proportional to M * log N,
 where M is maximum string length and N is number of keys in tree. Using trie, we can search the key in O(M)
  time.
  However the penalty is on trie storage requirements.

Every node of trie consists of multiple branches. Each branch represents a possible character of keys. 
We need to mark the last node of every key as leaf node. A trie node field value will be used to distinguish
 the node as 
leaf node (there are other uses of the value field). 

 */
    
