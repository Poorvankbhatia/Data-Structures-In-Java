/*

Given a sequence of words, print all anagrams together 
Given an array of words, print all anagrams together. 
For example, if the given array is {“cat”, “dog”, “tac”, “god”, “act”}, 
then output may be “cat tac act dog god”.

TRIE SOLUTION

 */


package treesPrograms;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by poorvank on 3/30/15.
 */

class IndexNode {

    public int index;
    public IndexNode next;

    public IndexNode(int index) {
        this.index = index;
        next = null;
    }

}


class AnagramNode {

    public char content;
    public boolean isLeaf;
    public LinkedList<AnagramNode> childList;
    public int count;
    public IndexNode indexNode;


    public AnagramNode(char c) {
        content = c;
        count = 0;
        childList = new LinkedList<>();
        isLeaf = false;
        indexNode = null;
    }

    public AnagramNode subNode(char c) {

        if (childList != null) {

            for (AnagramNode node : childList) {
                if (node.content == c) {
                    return node;
                }
            }

        }

        return null;

    }

}

class AnagramTrie {

    public AnagramNode root;

    public AnagramTrie() {
        root = new AnagramNode(' ');
    }

    public void insert(String word, int arrayIndex) {

        IndexNode indexNode = search(word);

        if (indexNode != null) {
            // System.out.println("Already present in trie ~ " + word);
            while (indexNode.next != null) {
                indexNode = indexNode.next;
            }
            indexNode.next = new IndexNode(arrayIndex);
            return;
        }

        AnagramNode current = root;

        for (char ch : word.toCharArray()) {

            AnagramNode subNode = current.subNode(ch);

            if (subNode != null) {
                current = subNode;
            } else {
                current.childList.add(new AnagramNode(ch));
                current = current.subNode(ch);
            }
            current.count++;
        }

        current.isLeaf = true;
        if (current.indexNode == null) {
            current.indexNode = new IndexNode(arrayIndex);
        }

    }

    private IndexNode search(String word) {

        AnagramNode current = root;

        for (char ch : word.toCharArray()) {

            AnagramNode subNode = current.subNode(ch);

            if (subNode == null) {
                return null;
            } else {
                current = subNode;
            }

        }

        if (current.isLeaf) {
            return current.indexNode;
        }

        return null;
    }

    public void print(AnagramNode root, String[] array) {

        if (root == null) {
            return;
        }

        if (root.isLeaf) {

            IndexNode current = root.indexNode;

            while (current != null) {
                System.out.print(array[current.index] + " ");
                current = current.next;
            }
            System.out.println();

        }

        for (int i = 0; i < root.childList.size(); i++) {
            print(root.childList.get(i), array);
        }

    }

}


public class PrintAnagrams {

    public static void main(String[] args) {
        String[] array = new String[]{"art", "tar", "top", "pot", "neat", "rat", "tape", "nate", "peat", "random"};
        AnagramTrie trie = new AnagramTrie();
        int i = 0;
        for (String s : array) {
            char[] element = s.toCharArray();
            Arrays.sort(element);
            trie.insert(String.valueOf(element), i);
            i++;
        }

        System.out.println("Anagrams are as follows : ");
        trie.print(trie.root, array);

    }

}


/*


Trie data structure can be used for a more efficient solution.
Insert the sorted order of each word in the trie. 
Since all the anagrams will end at the same leaf node.
 We can start a linked list at the leaf nodes where each node represents the index of the
  original array of words. Finally, traverse the Trie. While traversing the Trie, 
  traverse each linked list one line at a time. Following are the detailed steps.

1) Create an empty Trie
2) One by one take all words of input sequence. Do following for each word
…a) Copy the word to a buffer.
…b) Sort the buffer
…c) Insert the sorted buffer and index of this word to Trie. Each leaf node of 
Trie is head of a Index list. The Index list stores index of words in original sequence. 
If sorted buffe is already present, we insert index of this word to the index list.
3) Traverse Trie. While traversing, if you reach a leaf node, traverse the index list.
 And print all words using the index obtained from Index list.


 */