/*

Reverse DNS look up is using an internet IP address to find a domain name. For example,
 if you type 74.125.200.106 in browser, it automatically redirects to google.in.

 */

package trees.tries;


import utility.Trie;

public class ReverseDNSLookUp {

    public static void main(String[] args) {


        Trie<String> trie = new Trie<>();


        trie.put("107.108.11.123", "www.samsung.com");
        trie.put("107.109.123.255", "www.samsung.net");
        trie.put("74.125.200.106", "www.google.in");

        System.out.println("domain for 107.109.123.255  is " + trie.get("107.109.123.255"));
        System.out.println("domain for 107.109.123.245  is " + trie.get("107.109.123.245"));

    }

}


