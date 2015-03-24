/*

Reverse DNS look up is using an internet IP address to find a domain name. For example,
 if you type 74.125.200.106 in browser, it automatically redirects to google.in.

 */

package Trees;


import java.util.LinkedList;

class ReverseTrieNode {

    char content;
    int count = 0;
    boolean isLeaf;
    String url;
    LinkedList<ReverseTrieNode> childList;


    public ReverseTrieNode(char ch) {

        content = ch;
        count = 0;
        childList = new LinkedList<ReverseTrieNode>();
        url = "";
        isLeaf = false;
    }

    //Method which returns a sub node with content as character

    public ReverseTrieNode subNode(char ch) {

        if (this.childList != null) {

            for (ReverseTrieNode node : childList) {
                if (node.content == ch) {
                    return node;
                }
            }

        }
        return null;

    }

}


class ReverseTrie {

    public ReverseTrieNode root;

    public ReverseTrie() {

        root = new ReverseTrieNode(' ');

    }

    public void insert(String ipString, String url) {

        String searchResult = search(ipString);
        if (searchResult != null && !searchResult.equals("")) {
            System.out.println("Already present DNS with domain name - " + searchResult);
            return;
        } else {
            ReverseTrieNode current = root;

            for (char ch : ipString.toCharArray()) {

                try {
                    ReverseTrieNode subNode = current.subNode(ch);

                    if (subNode != null) {
                        current = subNode;
                    } else {
                        current.childList.add(new ReverseTrieNode(ch));
                        current = current.subNode(ch);
                    }
                    current.count++;

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            current.isLeaf = true;
            current.url = url;
        }

    }

    public String search(String ipString) {

        ReverseTrieNode current = root;

        for (char ch : ipString.toCharArray()) {

            ReverseTrieNode subNode = current.subNode(ch);

            if (subNode == null) {
                return null;
            } else {
                current = subNode;
            }

        }

        if (current.isLeaf) {
            return current.url;
        } else {
            return null;
        }

    }


}

public class ReverseDNSLookUp {

    public static void main(String[] args) {


        ReverseTrie lookUPReverseTrie = new ReverseTrie();
        lookUPReverseTrie.insert("107.108.11.123", "www.samsung.com");
        lookUPReverseTrie.insert("107.109.123.255", "www.samsung.net");
        lookUPReverseTrie.insert("74.125.200.106", "www.google.in");

        System.out.println("domain for 107.109.123.255  is " + lookUPReverseTrie.search("107.109.123.255"));
        System.out.println("domain for 107.109.123.245  is " + lookUPReverseTrie.search("107.109.123.245"));

    }

}


