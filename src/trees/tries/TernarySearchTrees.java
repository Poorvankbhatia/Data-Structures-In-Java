/*

A ternary search tree is a special trie data structure where the child nodes of a standard trie are 
ordered as a 
binary search tree.

Representation of ternary search trees:
Unlike trie(standard) data structure where each node contains 26 pointers for its children, each node
 in a ternary 
search tree contains only 3 pointers:
1. The left pointer points to the node whose value is less than the value in the current node.
2. The equal pointer points to the node whose value is equal to the value in the current node.
3. The right pointer points to the node whose value is greater than the value in the current node.




 */

package trees.tries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 4/17/15.
 */

class TSTNode {

    public char data;
    public boolean isLeaf;
    public TSTNode left;
    public TSTNode right;
    public TSTNode middle;

    public TSTNode(char data) {

        this.data = data;
        isLeaf = false;
        left = null;
        right = null;
        middle = null;

    }

}


class TSTree {

    public TSTNode root;
    public List<TSTNode> al;

    public TSTree() {

        root = null;
        al = new ArrayList<>();
    }

    public boolean isEmpty() {

        return root == null;
    }

    public void insert(String word) {

        root = insert(root, word.toCharArray(), 0);

    }

    public TSTNode insert(TSTNode node, char[] word, int pos) {

        if (node == null) {
            node = new TSTNode(word[pos]);
        }
        if (word[pos] > node.data) {
            node.right = insert(node.right, word, pos);
        } else if (word[pos] < node.data) {
            node.left = insert(node.left, word, pos);
        } else {
            if (pos < word.length - 1) {
                node.middle = insert(node.middle, word, pos + 1);
            } else {
                node.isLeaf = true;
            }
        }
        return node;
    }

    public void delete(String word) {

        delete(root, word.toCharArray(), 0);

    }

    public void delete(TSTNode node, char[] word, int pos) {

        if (node == null) {
            return;
        }

        if (word[pos] > node.data) {
            delete(node.right, word, pos);
        } else if (word[pos] < node.data) {
            delete(node.left, word, pos);
        } else {
            if (node.isLeaf && pos == word.length - 1) {
                node.isLeaf = false;
            }
            if (pos < word.length - 1) {
                delete(node.middle, word, pos + 1);
            }
        }

    }

    public boolean search(String word) {
        return search(root, word.toCharArray(), 0);
    }

    public boolean search(TSTNode node, char[] word, int pos) {

        if (node == null) {
            return false;
        }
        if (word[pos] > node.data) {
            return search(node.right, word, pos);
        } else if (word[pos] < node.data) {
            return search(node.left, word, pos);
        } else {
            if (pos == word.length - 1) {
                return node.isLeaf;
            }
            return search(node.middle, word, pos + 1);
        }

    }


}

public class TernarySearchTrees {

    public static void main(String[] args) {

        TSTree tree = new TSTree();
        tree.insert("cat");
        tree.insert("cats");
        tree.insert("bug");
        tree.insert("up");

        System.out.println("Is bug present - " + tree.search("bug"));

        tree.delete("bug");

        System.out.println("Is bug present - " + tree.search("bug"));
    }

}


/*

One of the advantage of using ternary search trees over tries is that ternary search trees are a more space 
efficient (involve only three pointers per node as compared to 26 in standard tries). 
Further, ternary search trees can be used any time a hashtable would be used to store strings.

Tries are suitable when there is a proper distribution of words over the alphabets so that spaces are utilized 
most efficiently. Otherwise ternary search trees are better. Ternary search trees are efficient to use(in terms of 
space) when the strings to be stored share a common prefix.

Applications of ternary search trees:
1. Ternary search trees are efficient for queries like “Given a word, find the next word in dictionary(near-neighbor 
lookups)” or “Find all telephone numbers starting with 9342 or “typing few starting characters in a web browser 
displays all website names with this prefix”(Auto complete feature)”.

2. Used in spell checks: Ternary search trees can be used as a dictionary to store all the words. Once the word 
is typed in an editor, the word can be parallely searched in the ternary search tree to check for correct spelling.

 */