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
        return x!=null? (Item)x.value:null;
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
            x.value = item;
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

    private void collect(Node x,StringBuilder prefix,Queue results) {
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
