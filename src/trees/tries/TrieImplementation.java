package trees.tries;


import java.util.LinkedList;
import java.util.List;

class TrieNode1 {

    char content;
    boolean isLeaf;
    int count;
    List<TrieNode1> childList;

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

/*

The complexity of creating a trie is O(W*L), where W is the number of words, and L 
is an average length of the word: you need to perform L lookups on the average for each of the W words in the set.

Same goes for looking up words later: you perform L steps for each of the W words.

Hash insertions and lookups have the same complexity: for each word you need to check equality, 
which takes O(L), for the overall complexity of O(W*L).

If you need to look up entire words, hash table is easier. However, you cannot look up words by their prefix 
using a hash table; If prefix-based lookups are of no interest to you, use a hash table; otherwise, use a trie.


search complexities can be brought to optimal limit (key length). If we store keys in binary search tree, a
 well balanced BST will need time proportional to M * log N,
 where M is maximum string length and N is number of keys in tree. Using trie, we can search the key in O(M) time.


 */