/*

®®®®®®®®®®®®®®®®
Given a dictionary of words and an input string, find the longest prefix of the string which is also a word in dictionary.

 */

package treesPrograms;

import java.util.LinkedList;

/**
 * Created by poorvank on 3/24/15.
 */

class PrefixTrieNode {

    public char ch;
    LinkedList<PrefixTrieNode> childList;
    boolean isLeaf;
    int count;

    public PrefixTrieNode(char ch) {

        this.ch = ch;
        childList = new LinkedList<PrefixTrieNode>();
        isLeaf = false;
        count = 0;

    }

    public PrefixTrieNode subNode(char ch) {

        if (childList != null) {

            for (PrefixTrieNode node : childList) {

                if (node.ch == ch) {
                    return node;
                }

            }

        }

        return null;

    }

}

class PrefixTrie {

    public PrefixTrieNode root;

    public PrefixTrie() {

        root = new PrefixTrieNode(' ');

    }

    public void insert(String word) {

        if (search(word)) {
            System.out.println("Word has already been added");
            return;
        }

        PrefixTrieNode current = root;

        for (char ch : word.toCharArray()) {

            PrefixTrieNode subNode = current.subNode(ch);

            if (subNode != null) {
                current = subNode;
            } else {
                current.childList.add(new PrefixTrieNode(ch));
                current = current.subNode(ch);
            }

            current.count++;

        }

        current.isLeaf = true;

    }

    private boolean search(String word) {

        PrefixTrieNode current = root;

        for (char ch : word.toCharArray()) {

            PrefixTrieNode subNode = current.subNode(ch);

            if (subNode == null) {
                return false;
            } else {
                current = subNode;
            }

        }

        return current.isLeaf;


    }


    public String longestPrefixMatching(String word) {

        int maxLen = 0;
        StringBuilder utilString = new StringBuilder();
        String longestPrefix = "";

        PrefixTrieNode current = root;

        for (char ch : word.toCharArray()) {

            PrefixTrieNode subNode = current.subNode(ch);

            if (subNode == null) {
                //maxlen ==0 and subnode null only when no match
                if (maxLen == 0) {
                    return "";
                } else {
                    break;
                }

            } else {
                current = subNode;
                //utilString only to append the current node and will become prefix only if a leaf node is found
                utilString.append(ch);
                if (current.isLeaf) {
                    maxLen++;
                    longestPrefix = utilString.toString();
                }
            }

        }

        return longestPrefix;


    }

}

public class LongestPrefixMatching {

    public static void main(String[] args) {

        PrefixTrie dict = new PrefixTrie();

        dict.insert("are");
        dict.insert("area");
        dict.insert("base");
        dict.insert("cat");
        dict.insert("cater");
        dict.insert("basement");

        String input = "caterer";
        System.out.print(input + ":   ");
        System.out.println(dict.longestPrefixMatching(input));

        input = "basement";
        System.out.print(input + ":   ");
        System.out.println(dict.longestPrefixMatching(input));

        input = "are";
        System.out.print(input + ":   ");
        System.out.println(dict.longestPrefixMatching(input));

        input = "arex";
        System.out.print(input + ":   ");
        System.out.println(dict.longestPrefixMatching(input));

        input = "basemexz";
        System.out.print(input + ":   ");
        System.out.println(dict.longestPrefixMatching(input));

        input = "xyz";
        System.out.print(input + ":   ");
        System.out.println(dict.longestPrefixMatching(input));

    }


}


/*

//even if string for the given input is ba utilString will be ba but longest prefix will be "" only

 */