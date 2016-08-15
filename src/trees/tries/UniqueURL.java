/*

Given a very long list of URLs, find the first URL which is unique ( occurred exactly once ).


 */

package trees.tries;

import utility.DLLNode;
import utility.DoublyLinkList;
import utility.Trie;

import java.util.Iterator;

public class UniqueURL {

    Trie<DLLNode<String>> trie  = new Trie<>();
    DoublyLinkList<String> stringDoublyLinkList = new DoublyLinkList<>();

    public void printUniqueUrl(String[] urls) {

        for (String url : urls) {
            if(trie.contains(url)) {
                DLLNode value = trie.get(url);
                //Check if the node is already deleted
                if(value.getNext()!=null || value.getPrevious()!=null) {
                    stringDoublyLinkList.delete(value);
                }
            } else {
                DLLNode<String> node = new DLLNode<>(url);
                trie.put(url,node);
                stringDoublyLinkList.add(node);
            }
        }

        Iterator<String> listIterator = stringDoublyLinkList.iterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
    }


    public static void main(String[] args) {

        String[] urls = new String[]{"http://www.google.com/","http://www.google.com/","http://www.google.com/",
                "http://www.yahoo.com/",
                "http://www.amazon.com/",
                "http://www.apache.com/",
                "http://www.google.com/",
                "http://www.mycar.com/",
                "http://www.microsoft.com/",
                "http://www.casandra.com/",
                "http://www.apache.com/",
                "http://www.google.com/",
                "http://www.oracle.com/",
                "http://www.yahoo.com/",
                "http://www.oracle.com/",
                "http://www.facebook.com/",
                "http://www.pandora.com/",
                "http://www.microsoft.com/",
                "http://www.google.com/",
                "http://www.mycar.com/",
                "http://www.amazon.com/",
                "http://www.apple.com/",
        };

        UniqueURL uniqueURL = new UniqueURL();
        uniqueURL.printUniqueUrl(urls);



    }


}


/*

can solve this in O(n) using a combination of trie and linked list. The leaf node of a trie maintains a 
flag to record duplicate urls and
pointer to a node in a link list. If you encounter a new url, add a node to the head 
of the linked list and set the pointer in the trie. Whenever you encounter a url that 
is already in the trie, if the flag is not set, then set the flag, delete the node from the 
linked list and set pointer to null. If the flag is already set, then ignore and read the next url. 
After processing all the urls, the link list should only contain the unique urls and the node at the tail 
is the first unique url from the list. For n urls, inserting urls into the trie in O(n) and link list 
operations are all constant time. The node could just keep the index of the url in the list so that we 
don't have to store urls in the link list as well.


The trie insertion time is k, where k is the length of the string being inserted. 
So the total time is O(nk). Since the length of the URL's doesn't depend on n, though, 
and can be expected to be relatively constant, this is O(n).


Deletion from a doubly linked list takes O(1) time
 */