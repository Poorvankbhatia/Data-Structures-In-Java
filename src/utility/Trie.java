package utility;

/**
 * Created by poorvank on 25/06/16.
 */


public class Trie<Item> {

    private static final int R = 256;
    private Node root;
    private int size;


    private static class Node {
        private Object value;
        private Node[] childArray = new Node[R];
    }

    public Trie() {
        root = new Node();
        size = 0;
    }

    public Item get(String key) {
        Node x = get(root,key,0);
        if(x!=null) {
            if(x.value!=null) {
                return (Item)x.value;
            }
        }
        return null;
    }


    private Node get(Node x,String key,int d) {
        if(x==null) {
            return null;
        }
        if(d==key.length()) {
            return x;
        }
        char c = key.charAt(d);
        return get(x.childArray[c],key,d+1);
    }

    public boolean contains(String key) {
        return get(key)!=null;
    }

    public void put(String key,Item item) {
        if (item==null) {
            delete(key);
        }
        else {
            root = put(root,key,0,item);
        }
    }

    private Node put(Node x,String key,int d,Item item) {
        if(x==null) {
            x = new Node();
        }
        if(d==key.length()) {
            if(x.value==null) {
                size++;
            }
            x.value = (item);
            return x;
        }
        char c = key.charAt(d);
        x.childArray[c] = put(x.childArray[c],key,d+1,item);
        return x;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize()==0;
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> results = new Queue<>();
        Node x = get(root,prefix,0);
        collect(x,new StringBuilder(prefix),results);
        return results;
    }

    private void collect(Node x,StringBuilder prefix,Queue<String> results) {
        if(x==null) {
            return;
        }
        if(x.value!=null) {
            results.enqueue(prefix.toString());
        }
        for (char c=0;c<R;c++) {
            prefix.append(c);
            collect(x.childArray[c],prefix,results);
            prefix.deleteCharAt(prefix.length()-1);
        }
    }

    public String longestPrefixOf(String key) {
        int length = longestPrefixOf(root,key,0,-1);
        if(length==-1) {
            return null;
        }
        return key.substring(0,length);
    }

    // returns the length of the longest string key in the subtrie
    // rooted at x that is a prefix of the query string,
    private int longestPrefixOf(Node x,String query,int d,int length) {
        if(x==null) {
            return length;
        }
        if(x.value!=null) {
            length = d;
        }
        if(d==query.length()){
            return length;
        }
        char c = query.charAt(d);
        return longestPrefixOf(x.childArray[c],query,d+1,length);
    }

    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> results = new Queue<>();
        collect(root, new StringBuilder(), pattern, results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, String pattern, Queue<String> results) {
        if (x == null) return;
        int d = prefix.length();
        if (d == pattern.length() && x.value != null)
            results.enqueue(prefix.toString());
        if (d == pattern.length())
            return;
        char c = pattern.charAt(d);
        if (c == '.') {
            for (char ch = 0; ch < R; ch++) {
                prefix.append(ch);
                collect(x.childArray[ch], prefix, pattern, results);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        else {
            prefix.append(c);
            collect(x.childArray[c], prefix, pattern, results);
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }


    public void delete(String key) {
        root = delete(root,key,0);
    }

    private Node delete(Node x,String key,int d) {
        if(x==null) {
            return null;
        }
        if(d==key.length()) {
            if(x.value!=null) {
                size--;
            }
            x.value = null;
        } else {
            char c = key.charAt(d);
            x.childArray[c] = delete(x.childArray[c],key,d+1);
        }

        //Remove subtree rooted at x , iff entire subtree is empty
        if(x.value!=null) {
            return x;
        }
        for (int c=0;c<R;c++) {
            if(x.childArray[c]!=null) {
                return x;
            }
        }

        return null;

    }


    public static void main(String[] args) {

        Trie<Boolean> trie = new Trie<>();
        trie.put("by",Boolean.TRUE);
        trie.put("sea",Boolean.TRUE);
        trie.put("sells",Boolean.TRUE);
        trie.put("she",Boolean.TRUE);
        trie.put("shells",Boolean.TRUE);
        trie.put("the",Boolean.TRUE);

        System.out.println("All keys:");
        for (String keys : trie.keys()) {
            System.out.println(keys);
        }

        System.out.println("Keys which are prefix to - " + "sh");
        for (String keys : trie.keysWithPrefix("sh")) {
            System.out.println(keys);
        }

        System.out.println("Logest prefix for - shellsort is = " + trie.longestPrefixOf("shellsort"));

        System.out.println("Keys matching the regex - ..e ");
        for (String keys : trie.keysThatMatch("..e")) {
            System.out.println(keys);
        }



    }


}

/*

The linked structure (shape) of a trie is independent of the key insertion/
deletion order: there is a unique trie for any given set of keys.

Proof: Immediate, by induction on the subtries.
This fundamental fact is a distinctive feature of tries: for all of the other search tree
structures that we have considered so far, the tree that we construct depends both on
the set of keys and on the order in which we insert those keys.

Worst-case time bound for search and insert. How long does it take to find the value
associated with a key? For BSTs, hashing, and other methods in Chapter 4, we needed
mathematical analysis to study this question, but for tries it is very easy to answer:
Proposition G. The number of array accesses when searching in a trie or inserting
a key into a trie is at most 1 plus the length of the key.
Proof: Immediate from the code. The recursive get() and put() implementations
carry an argument d that starts at 0, increments for each call, and is used to stop the
recursion when it reaches the key length.

From a theoretical standpoint, the implication of Proposition G is that tries are optimal
for search hit—we could not expect to do better than search time proportional to
the length of the search key. Whatever algorithm or data structure we are using, we cannot
know that we have found a key that we seek without examining all of its characters.
From a practical standpoint this guarantee is important because it does not depend on
the number of keys : when we are working with 7-character keys like license plate numbers,
we know that we need to examine at most 8 nodes to search or insert; when we are
working with 20-digit account numbers, we only need to examine at most 21 nodes to
search or insert.

The number of links in a trie is between RN and RNw, where w is
the average key length.
Proof: Every key in the trie has a node containing its associated value that also has
R links, so the number of links is at least RN. If the first characters of all the keys are
different, then there is a node with R links for every key character, so the number of
links is R times the total number of key characters, or RNw.


The complexity of creating a trie is O(W*L), where W is the number of words, and L is an average length of the word:
you need to perform L lookups on the average for each of the W words in the set.

Same goes for looking up words later: you perform L steps for each of the W words.

Hash insertions and lookups have the same complexity: for each word you need to check equality, which takes O(L),
for the overall complexity of O(W*L).

If you need to look up entire words, hash table is easier. However, you cannot look up words by their prefix using a
hash table; If prefix-based lookups are of no interest to you, use a hash table; otherwise, use a trie.



 */
