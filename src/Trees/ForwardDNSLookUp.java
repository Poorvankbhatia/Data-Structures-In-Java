package Trees;

import java.util.LinkedList;

class ForwardTrieNode {

    public char content;
    public int count;
    public LinkedList<ForwardTrieNode> childList;
    public String url;
    public boolean isLeaf;

    public ForwardTrieNode(char content) {

        this.content = content;
        count = 0;
        childList = new LinkedList<ForwardTrieNode>();
        url = "";
        isLeaf = true;

    }

    public ForwardTrieNode subNode(char ch) {

        if (childList != null) {

            for (ForwardTrieNode node : childList) {
                if (node.content == ch) {
                    return node;
                }
            }

        }

        return null;

    }


}


class ForwardTrie {

    public ForwardTrieNode root;

    public ForwardTrie() {
        root = new ForwardTrieNode(' ');
    }

    public String search(String word) {

        ForwardTrieNode current = root;

        for (char ch : word.toCharArray()) {

            ForwardTrieNode subNode = current.subNode(ch);

            if (subNode == null) {
                return null;
            } else {
                current = subNode;
            }

        }

        if (current.isLeaf) {
            return current.url;
        }
        return null;

    }

    public void insert(String url, String ipAddress) {

        if (search(url) != null) {
            System.out.println("Already present url  ");
        } else {

            ForwardTrieNode current = root;

            for (char ch : url.toCharArray()) {

                ForwardTrieNode subNode = current.subNode(ch);

                if (subNode == null) {
                    current.childList.add(new ForwardTrieNode(ch));
                    current = current.subNode(ch);
                } else {
                    current = subNode;
                }

                current.count++;

            }

            current.isLeaf = true;
            current.url = ipAddress;

        }

    }

}

public class ForwardDNSLookUp {

    public static void main(String[] args) {

        ForwardTrie trie = new ForwardTrie();

        trie.insert("www.samsung.com", "107.108.11.123");
        trie.insert("www.samsung.net", "107.109.123.255");
        trie.insert("www.google.com", "74.125.200.106");


        System.out.println("domain for google  is " + trie.search("www.google.com"));
        System.out.println("domain for samsung.com  is " + trie.search("www.samsung.com"));

    }

}
