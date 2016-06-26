/*

Given a sequence of words, print all anagrams together 
Given an array of words, print all anagrams together. 
For example, if the given array is {“cat”, “dog”, “tac”, “god”, “act”}, 
then output may be “cat tac act dog god”.

TRIE SOLUTION

 */


package trees.tries;

import utility.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by poorvank on 3/30/15.
 */


class AnagramTrie extends Trie<ArrayList<String>> {

    public AnagramTrie() {
        super();
    }

    public void put(String key, String s) {
        ArrayList<String> list = get(key);
        if(list!=null && list.size()!=0) {
            list.add(s);
        }
        else {
            list = new ArrayList<>();
            list.add(s);
            super.put(key,list);
        }
    }

    public void print() {
        for (String key : keys()) {
            List<String> list = get(key);
            System.out.println(list.toString());
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
            trie.put(String.valueOf(element), s);
            i++;
        }

        System.out.println("Anagrams are as follows : ");
        trie.print();
        System.out.println("Total size = " + trie.getSize());

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