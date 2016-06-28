/*
A ternary search tree is a special trie data structure where the child nodes of a standard trie are 
ordered as a 
binary search tree.

Representation of ternary search trees:
Unlike trie(standard) data structure where each node contains 26 pointers for its children, each node
 in a ternary 
search tree contains only 3 pointers:
1. The left pointer points to the node whose value is less than the value in the current node.
2. The equal pointer points to the node whose value is equal to the value in the current node.
3. The right pointer points to the node whose value is greater than the value in the current node.

A ternary tree is a data structure that solves the memory problem of tries in a more clever way. 
To avoid the memory occupied by unnecessary pointers, each trie node is represented as a tree-within-a-tree 
rather than as an array. Each non-null pointer in the trie node gets its own node in a ternary search tree.

http://igoro.com/archive/efficient-auto-complete-with-a-ternary-search-tree/

 */

package trees.tries;


import utility.Queue;

public class TernarySearchTrees<Item> {

    private int size;
    private Node<Item> root;

    private class Node<item> {
        private Item value;
        private Node<Item> left,middle,right;
        private char c;
    }

    public boolean contains(String key) {
        return get(key)!=null;
    }

    public Item get(String key) {
        if(key==null) {
            throw new NullPointerException("Null key passed");
        }
        Node<Item> node = get(root,0,key);
        if(node==null) {
            return null;
        }
        return node.value;
    }

    private Node<Item> get(Node<Item> x,int d,String key) {
        if(x==null) {
            return null;
        }
        char c = key.charAt(d);
        if(c>x.c) {
            return get(x.right,d,key);
        } else if(c<x.c) {
            return get(x.left,d,key);
        } else if(d<key.length() - 1) {
            return get(x.middle,d+1,key);
        } else {
            return x;
        }
    }

    public void put(String key,Item item) {
        if(!contains(key)) {
            size++;
        }
        root = put(root,key,item,0);
    }

    private Node<Item> put(Node<Item> x,String key,Item item,int d) {
        char c = key.charAt(d);
        if(x==null) {
            x = new Node<>();
            x.c = c;
        }
        if(c<x.c) {
            x.left = put(x.left,key,item,d);
        } else if(c>x.c) {
            x.right = put(x.right,key,item,d);
        } else if(d<key.length()-1) {
            x.middle = put(x.middle,key,item,d+1);
        } else {
            x.value = item;
        }
        return x;
    }

    public String longestPrefixOf(String key) {
        Node<Item> x = root;
        int length = 0,i=0;
        while (x!=null && i<key.length()) {
            char ch = key.charAt(i);
            if(ch<x.c) {
                x = x.left;
            } else if(ch>x.c) {
                x = x.right;
            } else {
                i++;
                if(x.value!=null) {
                    length = i;
                }
                x = x.middle;
            }
        }

        return key.substring(0,length);
    }

    public Iterable<String> keys() {
        Queue<String> results = new Queue<>();
        collect(root,new StringBuilder(),results);
        return results;
    }


    public Iterable<String> keysWithPrefix(String prefix) {
        Queue<String> queue = new Queue<String>();
        Node<Item> x = get(root, 0, prefix);
        if (x == null) return queue;
        if (x.value != null) queue.enqueue(prefix);
        collect(x.middle, new StringBuilder(prefix), queue);
        return queue;
    }

    private void collect(Node<Item> x, StringBuilder prefix, Queue<String> queue) {
        if (x == null) return;
        collect(x.left,  prefix, queue);
        if (x.value != null) queue.enqueue(prefix.toString() + x.c);
        collect(x.middle,   prefix.append(x.c), queue);
        prefix.deleteCharAt(prefix.length() - 1);
        collect(x.right, prefix, queue);
    }

    public Iterable<String> keysThatMatch(String pattern) {
        Queue<String> queue = new Queue<String>();
        collect(root, new StringBuilder(), 0, pattern, queue);
        return queue;
    }

    private void collect(Node<Item> x, StringBuilder prefix, int i, String pattern, Queue<String> queue) {
        if (x == null) return;
        char c = pattern.charAt(i);
        if (c == '.' || c < x.c) collect(x.left, prefix, i, pattern, queue);
        if (c == '.' || c == x.c) {
            if (i == pattern.length() - 1 && x.value != null) queue.enqueue(prefix.toString() + x.c);
            if (i < pattern.length() - 1) {
                collect(x.middle, prefix.append(x.c), i+1, pattern, queue);
                prefix.deleteCharAt(prefix.length() - 1);
            }
        }
        if (c == '.' || c > x.c) collect(x.right, prefix, i, pattern, queue);
    }

    public static void main(String[] args) {

        TernarySearchTrees<Boolean> trie = new TernarySearchTrees<>();
        trie.put("by",Boolean.TRUE);
        trie.put("sea",Boolean.TRUE);
        trie.put("sells",Boolean.TRUE);
        trie.put("she",Boolean.TRUE);
        trie.put("shells",Boolean.TRUE);
        trie.put("the",Boolean.TRUE);

        System.out.println("All keys:" + trie.size);
        for (String keys : trie.keys()) {
            System.out.println(keys);
        }

        System.out.println("Keys which are prefix to - " + "sh");
        for (String keys : trie.keysWithPrefix("sh")) {
            System.out.println(keys);
        }

    }

}


/*

One of the advantage of using ternary search trees over tries is that ternary search trees are a more space 
efficient (involve only three pointers per node as compared to 26 in standard tries). 
Further, ternary search trees can be used any time a hashtable would be used to store strings.

Tries are suitable when there is a proper distribution of words over the alphabets so that spaces are utilized 
most efficiently. Otherwise ternary search trees are better. Ternary search trees are efficient to use(in terms of 
space) when the strings to be stored share a common prefix.

Applications of ternary search trees:
1. Ternary search trees are efficient for queries like “Given a word, find the next word in dictionary
(near-neighbor 
lookups)” or “Find all telephone numbers starting with 9342 or “typing few starting characters in a web browser 
displays all website names with this prefix”(Auto complete feature)”.

2. Used in spell checks: Ternary search trees can be used as a dictionary to store all the words. Once the word 
is typed in an editor, the word can be parallely searched in the ternary search tree to check for correct 
\spelling.


At each step, you are reducing the size of the searchable range by a constant factor (in this case 3). 
If you find your element after n steps, then the searchable range has size N = 3n.
Inversely, the number of steps that you need until you find the element is the logarithm of the size
of the collection. That is, the runtime is O(log N). A little further thought shows that you can also
always construct situations where you need all those steps, so the worst-case runtime is actually Θ(log N).


The number of links in a TST built from N string keys of average
length w is between 3N and 3Nw.

 */