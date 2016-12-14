/*

How to implement LRU caching scheme? What data structures should be used?

We are given total possible page numbers that can be referred. 
We are also given cache (or memory) size (Number of page frames that cache can hold at a time). 
The LRU caching scheme is to remove the least recently used frame when the cache is full and 
a new page is referenced which is not there in cache.

 */

package miscellaneous;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank on 8/11/15.
 */





public class LRUCache {

    private static class DLLNode {

        public int key;
        public int value;
        public DLLNode next;
        public DLLNode prev;

        public DLLNode(int key,int value, DLLNode next, DLLNode prev) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.prev = prev;
        }

    }

    private static class Cache {

        int capacity;
        Map<Integer, DLLNode> map = new HashMap<>();
        DLLNode head = null;
        DLLNode end = null;

        public Cache(int capacity) {
            this.capacity = capacity;
        }

        public int get(int key) {

            if (map.containsKey(key)) {
                DLLNode node = map.get(key);
                remove(node);
                setHead(node);
                return node.value;
            }

            return -1;
        }

        private void setHead(DLLNode node) {

            node.next = head;
            node.prev = null;
            if (head != null) {
                head.prev = node;
            }

            head = node;

            if (end == null) {
                end = head;
            }

        }

        public void remove(DLLNode n) {

            if (n.prev != null) {
                n.prev.next = n.next;
            } else {
                head = n.next;
            }

            if (n.next != null) {
                n.next.prev = n.prev;
            } else {
                end = n.prev;
            }

        }

        public void set(int key, int value) {

            if (map.containsKey(key)) {
                DLLNode old = map.get(key);
                old.value = value;
                remove(old);
                setHead(old);
            } else {
                DLLNode created = new DLLNode(key,value, null, null);
                if (map.size() >= capacity) {
                    map.remove(end.key);
                    remove(end);
                    setHead(created);
                } else {
                    setHead(created);
                }
                map.put(key, created);
            }


        }

    }

    public static void main(String[] args) {

        Cache cache = new Cache(5);
        cache.set(1, 23);
        cache.set(2, 24);
        cache.set(3, 243);
        cache.set(4, 43);


        cache.get(3);
        System.out.println(cache.head.value);
    }

}



/*

We use two data structures to implement an LRU Cache.

1. A Queue which is implemented using a doubly linked list. The maximum size of the queue will be equal to
 the total number of frames available (cache size).
The most recently used pages will be near front end and least recently pages will be near rear end.

2. A Hash with page number as key and address of the corresponding queue node as value.

When a page is referenced, the required page may be in the memory. If it is in the memory, we need to detach 
the node of the list and bring it to the front of the queue.
If the required page is not in the memory, we bring that in memory. In simple words, we add a new node
 to the front of the queue and update the corresponding node address in the hash. 
 If the queue is full, i.e. all the frames are full, we remove a node from the rear of queue, 
 and add the new node to the front of queue.

Note: Initially no page is in the memory.

 */