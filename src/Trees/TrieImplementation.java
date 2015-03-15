package Trees;


import java.util.LinkedList;

class TrieNode1 {

    char content;
    boolean isLeaf;
    int count;
    LinkedList<TrieNode1> childList;

    public TrieNode1(char c) {

        childList = new LinkedList<TrieNode1>();
        count = 0;
        content = c;
        isLeaf = false;

    }

    public TrieNode1 subNode(char c) {

        if (childList != null) {

            for (TrieNode1 everyChild : childList) {
                if (everyChild.content == c) {
                    return everyChild;
                }
            }

        }
        return null;

    }

}

class Trie1 {

    public TrieNode1 root;

    public Trie1() {

        root = new TrieNode1(' ');
    }

    public void insert(String word) {

        if (search(word)) {
            return;
        }

        TrieNode1 current = root;

        for (char ch : word.toCharArray()) {
            
            /* for every repeated node count increases,it is one for single words */

            TrieNode1 subNode = current.subNode(ch);

            if (subNode != null) {
                current = subNode;
            } else {
                current.childList.add(new TrieNode1(ch));
                current = current.subNode(ch);
            }
            current.count++;

        }

        current.isLeaf = true;

    }

    public boolean search(String word) {

        TrieNode1 current = root;

        for (char ch : word.toCharArray()) {

            TrieNode1 subNode = current.subNode(ch);

            if (subNode == null) {
                return false;
            } else {
                current = subNode;
            }
        }

        return current.isLeaf;

    }

}

public class TrieImplementation {

    public static void main(String[] args) {

        Trie1 trie1 = new Trie1();
        trie1.insert("hello");
        trie1.insert("hye");
        trie1.insert("is");
        trie1.insert("if");

        if (trie1.search("iss")) {
            System.out.println("Present");
        } else {
            System.out.println("Nope! not present");
        }

    }

}