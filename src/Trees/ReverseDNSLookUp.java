/*

Reverse DNS look up is using an internet IP address to find a domain name. For example,
 if you type 74.125.200.106 in browser, it automatically redirects to google.in.

 */

package Trees;


import java.util.LinkedList;

class TrieNode {

    char content;
    int count = 0;
    boolean isLeaf;
    String url;
    LinkedList<TrieNode> childList;


    public TrieNode(char ch) {

        content = ch;
        count = 0;
        childList = new LinkedList<TrieNode>();
        url = "";
        isLeaf = false;
    }

    //Method which returns a sub node with content as character

    public TrieNode subNode(char ch) {

        if (this.childList != null) {

            for (TrieNode node : childList) {
                if (node.content == ch) {
                    return node;
                }
            }

        }
        return null;

    }

}


class Trie {

    public TrieNode root;

    public Trie() {

        root = new TrieNode(' ');

    }

    public void insert(String ipString, String url) {

        String searchResult = search(ipString);
        if (searchResult != null && !searchResult.equals("")) {
            System.out.println("Already present DNS with domain name - " + searchResult);
            return;
        } else {
            TrieNode current = root;

            for (char ch : ipString.toCharArray()) {

                try {
                    TrieNode subNode = current.subNode(ch);

                    if (subNode != null) {
                        current = subNode;
                    } else {
                        current.childList.add(new TrieNode(ch));
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

        TrieNode current =root;
        
        for (char ch : ipString.toCharArray()) {
            
            TrieNode subNode = current.subNode(ch);
            
            if(subNode==null) {
                return null;
            }
            else {
                current =subNode;
            }
            
        }
        
        if(current.isLeaf) {
            return current.url;
        } else {
            return null;
        }

    }


}

public class ReverseDNSLookUp {

    public static void main(String[] args) {


        Trie lookUPTrie = new Trie();
        lookUPTrie.insert("107.108.11.123","www.samsung.com");
        lookUPTrie.insert("107.109.123.255","www.samsung.net");
        lookUPTrie.insert("74.125.200.106","www.google.in");
        
        System.out.println("domain for 107.109.123.255  is " + lookUPTrie.search("107.109.123.255"));
        System.out.println("domain for 107.109.123.245  is " + lookUPTrie.search("107.109.123.245"));
        
    }

}


