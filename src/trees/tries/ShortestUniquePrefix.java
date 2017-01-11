/*

Given an array of words, find all shortest unique prefixes to represent each word in the given array. Assume that no word is prefix of another.

Examples:

Input: arr[] = {"zebra", "dog", "duck", "dove"}
Output: dog, dov, du, z
Explanation: dog => dog
             dove = dov
             duck = du
             z   => zebra

Input: arr[] =  {"geeksgeeks", "geeksquiz", "geeksforgeeks"};
Output: geeksf, geeksg, geeksq}


 */
package trees.tries;

import utility.Trie;

/**
 * Created by poorvank on 09/01/17.
 */
public class ShortestUniquePrefix {

    Trie<Integer> trie;

    public ShortestUniquePrefix() {
        trie = new Trie<>();
    }

    public void printShortestPrefixes(String[] arr) {

        for (String s : arr) {
            trie.putWithFrequency(s,1);
        }

        for (String s : arr) {
            System.out.println(s + " - " + trie.getEntirePrefixWithValue(s,1));
        }

        System.out.println(trie.getSize());
    }

    public static void main(String[] args) {

        String[] arr =  new String[]{"dog", "duck", "dove","zebra"};

        String[] arr2 = new String[]{"geeksgeeks", "geeksquiz", "geeksforgeeks"};
        new ShortestUniquePrefix().printShortestPrefixes(arr2);


    }

}

/*

An Efficient Solution is to use Trie. The idea is to maintain a count in every node. Below are steps.

1) Construct a Trie of all words. Also maintain frequency of every node (Here frequency is number of times node is visited during insertion).
Time complexity of this step is O(N) where N is total number of characters in all words.

2) Now, for every word, we find the character nearest to the root with frequency as 1. The prefix of the word is path from root to this character.
To do this, we can traverse Trie starting from root. For every node being traversed, we check its frequency. If frequency is one, we print all
characters from root to this node and don’t traverse down this node.

Time complexity if this step also is O(N) where N is total number of characters in all words.

                root
                / \
         (d, 3)/   \(z, 1)
              /     \
          Node1     Node2
           / \          \
     (o,2)/   \(u,1)     \(e,1)
         /     \          \
   Node1.1    Node1.2     Node2.1
      /  \         \            \
(g,1)/    \ (t,1)   \(c,1)       \(b,1)
    /      \         \            \
   Leaf   Leaf       Node1.2.1     Node2.1.1
   (dog)  (dot)        \               \
                         \(k, 1)          \(r, 1)
                          \                \
                          Leaf           Node2.1.1.1
                          (duck)              \
                                                \(a,1)
                                                 \
                                                 Leaf
                                                 (zebra)

 */