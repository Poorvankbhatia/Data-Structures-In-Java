/*

Design an efficient data structure for given operations
Design a Data Structure for the following operations. The data structure should be efficient enough to accommodate the
operations according to their frequency.

1) findMin() : Returns the minimum item.
   Frequency: Most frequent

2) findMax() : Returns the maximum item.
    Frequency: Most frequent

3) deleteMin() : Delete the minimum item.
    Frequency: Moderate frequent

4) deleteMax() : Delete the maximum item.
    Frequency: Moderate frequent

5) Insert() : Inserts an item.
    Frequency: Least frequent

6) Delete() : Deletes an item.
    Frequency: Least frequent.

 */

package trees.heap;

import utility.priorityQueueClasses.MaxPriorityQueue;
import utility.priorityQueueClasses.MinPriorityQueue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by poorvank on 11/06/16.
 */

@SuppressWarnings("unchecked")
class EfficientDSNode<T extends Comparable<T>> implements Comparable<EfficientDSNode<T>>  {

    private T info;
    private EfficientDSNode next;
    private EfficientDSNode previous;
    private Integer maxHeapIndex;
    private Integer minHeapIndex;

    public EfficientDSNode(T info) {
        this(info,null,null,0,0);
    }

    public EfficientDSNode(T info, EfficientDSNode next, EfficientDSNode previous, Integer maxHeapIndex, Integer minHeapIndex) {
        this.info = info;
        this.next = next;
        this.previous = previous;
        this.maxHeapIndex = maxHeapIndex;
        this.minHeapIndex = minHeapIndex;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public EfficientDSNode getNext() {
        return next;
    }

    public void setNext(EfficientDSNode next) {
        this.next = next;
    }

    public EfficientDSNode getPrevious() {
        return previous;
    }

    public void setPrevious(EfficientDSNode previous) {
        this.previous = previous;
    }

    public Integer getMaxHeapIndex() {
        return maxHeapIndex;
    }

    public void setMaxHeapIndex(Integer maxHeapIndex) {
        this.maxHeapIndex = maxHeapIndex;
    }

    public Integer getMinHeapIndex() {
        return minHeapIndex;
    }

    public void setMinHeapIndex(Integer minHeapIndex) {
        this.minHeapIndex = minHeapIndex;
    }

    @Override
    public int compareTo(EfficientDSNode<T> o) {
        return this.info.compareTo(o.getInfo());
    }
}

class EfficientList<Item extends Comparable<Item>> implements Iterable<Item> {

    private int size;
    private EfficientDSNode head;
    private EfficientDSNode tail;

    public EfficientList() {
        size = 0;
        head = null;
        tail = null;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public EfficientDSNode getHeadNode() {
        if(isEmpty()) {
            throw new NoSuchElementException("Link list is empty!");
        }
        return head;
    }

    public EfficientDSNode getTailNode() {
        if(isEmpty()) {
            throw new NoSuchElementException("Link list is empty!");
        }
        return tail;
    }

    public void add(Item item) {

        EfficientDSNode node = new EfficientDSNode(item);
        if(isEmpty()) {
            head = tail = node;
        } else {
            EfficientDSNode temp = head;
            head = node;
            head.setNext(temp);
            temp.setPrevious(head);
        }
        size++;

    }

    public void delete(EfficientDSNode node) {
        if(node==null) {
            throw new NullPointerException("Null node cannot be deleted");
        }
        if(node.getNext()!=null && node.getPrevious()!=null) {
            node.getPrevious().setNext(node.getNext());
            node.getNext().setPrevious(node.getPrevious());
        } else if(node.getNext()!=null) {
            head = node.getNext();
            head.setPrevious(null);
        } else if(node.getPrevious()!=null) {
            tail = node.getPrevious();
            tail.setNext(null);
        } else {
            head = tail = null;
        }
    }

    public void add(EfficientDSNode node) {
        if(isEmpty()) {
            head = tail = node;
        } else {
            EfficientDSNode temp = head;
            head = node;
            head.setNext(temp);
            temp.setPrevious(head);
        }
        size++;
    }

    public EfficientDSNode removeFirst() {

        if(isEmpty()) {
            throw new NoSuchElementException("Empty List");
        }
        EfficientDSNode current = head;
        head = head.getNext();
        if(head==null) {
            tail=null;
        }
        size--;
        return current;

    }



    public EfficientDSNode removeLast() {

        if(isEmpty()) {
            throw new NoSuchElementException("Empty List");
        }
        EfficientDSNode prev = tail;
        tail = tail.getPrevious();
        tail.setNext(null);
        if(tail==null) {
            head=null;
        }
        size--;
        return prev;

    }

    @Override
    public Iterator<Item> iterator() {
        return new EfficientDSListIterator();
    }

    private class EfficientDSListIterator implements Iterator<Item>  {

        EfficientDSNode<Integer> current = head;

        public boolean hasNext() {
            return current!=null;
        }

        public Item next() {
            Item item = (Item) current.getInfo();
            current = current.getNext();
            return item;
        }

    }

}

public class EfficientDS<Item extends Comparable<Item>> {

    private MaxPriorityQueue<EfficientDSNode<Item>> maxPriorityQueue;
    private MinPriorityQueue<EfficientDSNode<Item>> minPriorityQueue;
    EfficientList<EfficientDSNode<Item>> doublyLinkList;

    public EfficientDS() {
        maxPriorityQueue = new MaxPriorityQueue<>();
        minPriorityQueue = new MinPriorityQueue<>();
        doublyLinkList = new EfficientList<>();
    }

    private void insert(Item element) {
        EfficientDSNode<Item> node = new EfficientDSNode<Item>(element);
        doublyLinkList.add(node);
        int minHeapIndex = minPriorityQueue.insert(node);
        node.setMinHeapIndex(minHeapIndex);
        int maxHeapIndex = maxPriorityQueue.insert(node);
        node.setMaxHeapIndex(maxHeapIndex);
    }

    public Item getMaxElement() {
        return maxPriorityQueue.getMaximumElement().getInfo();
    }

    public Item getMinElement() {
        return minPriorityQueue.getMinimumElement().getInfo();
    }

    public Item deleteMinimum() {
        EfficientDSNode<Item> minimumValueNode = minPriorityQueue.delMin();
        doublyLinkList.delete(minimumValueNode);
        int maxHeapIndex = minimumValueNode.getMaxHeapIndex();
        maxPriorityQueue.deleteIndex(maxHeapIndex);
        Item info = minimumValueNode.getInfo();
        return info;
    }

    public Item deleteMaximum() {
        EfficientDSNode<Item> maximumValueNode = maxPriorityQueue.delMax();
        doublyLinkList.delete(maximumValueNode);
        int minHeapIndex = maximumValueNode.getMinHeapIndex();
        minPriorityQueue.deleteIndex(minHeapIndex);
        Item info = maximumValueNode.getInfo();
        return info;
    }

    public void delete(Item element) {
        EfficientDSNode<Item> nodeToBeDeleted = null;
        for (EfficientDSNode<Item> node : doublyLinkList) {
            if(node.getInfo() == element) {
                nodeToBeDeleted = node;
                break;
            }
        }

        doublyLinkList.delete(nodeToBeDeleted);
        maxPriorityQueue.deleteIndex(nodeToBeDeleted.getMaxHeapIndex());
        minPriorityQueue.deleteIndex(nodeToBeDeleted.getMinHeapIndex());

    }

    public static void main(String[] args) {

        EfficientDS ds = new EfficientDS();
        ds.insert(45);
        ds.insert(23);
        ds.insert(30);

        System.out.println(ds.getMaxElement());
        System.out.println(ds.getMinElement());

    }

}

/*

The idea is to use two binary heaps (one max and one min heap). The main challenge is, while deleting an item,
we need to delete from both min-heap and max-heap. So, we need some kind of mutual data structure.
In the following design, we have used doubly linked list as a mutual data structure.
The doubly linked list contains all input items and indexes of corresponding min and max heap nodes.
The nodes of min and max heaps store addresses of nodes of doubly linked list.
The root node of min heap stores the address of minimum item in doubly linked list.
Similarly, root of max heap stores address of maximum item in doubly linked list. Following are the details of operations.

1) findMax(): We get the address of maximum value node from root of Max Heap. So this is a O(1) operation.

1) findMin(): We get the address of minimum value node from root of Min Heap. So this is a O(1) operation.

3) deleteMin(): We get the address of minimum value node from root of Min Heap. We use this address to find the node
in doubly linked list. From the doubly linked list, we get node of Max Heap. We delete node from all three.
We can delete a node from doubly linked list in O(1) time. delete() operations for max and min heaps take O(Logn) time.

4) deleteMax(): is similar to deleteMin()

5) Insert() We always insert at the beginning of linked list in O(1) time. Inserting the address in Max and
Min Heaps take O(Logn) time. So overall complexity is O(Logn)

6) Delete() We first search the item in Linked List. Once the item is found in O(n) time, we delete it
from linked list. Then using the indexes stored in linked list, we delete it from Min Heap and Max Heaps in O(Logn) time.
So overall complexity of this operation is O(n). The Delete operation can be optimized to O(Logn)
by using a balanced binary search tree instead of doubly linked list as a mutual data structure.
Use of balanced binary search will not effect time complexity of other operations as it will act as a
mutual data structure like doubly Linked List.


You an simply use a tree map (stl map or multimap if duplicates).
Since the data is sorted you get the min and max in O(1) and can delete them in O(1). Insert/Delete takes log n.

 */