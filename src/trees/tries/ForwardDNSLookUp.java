package trees.tries;

import utility.Trie;

public class ForwardDNSLookUp {

    public static void main(String[] args) {

        Trie<String> trie = new Trie<>();

        trie.put("www.samsung.com", "107.108.11.123");
        trie.put("www.samsung.net", "107.109.123.255");
        trie.put("www.google.com", "74.125.200.106");


        System.out.println("domain for google  is " + trie.get("www.google.com"));
        System.out.println("domain for samsung.com  is " + trie.get("www.samsung.com"));

    }

}
