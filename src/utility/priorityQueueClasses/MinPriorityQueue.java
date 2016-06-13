package utility.priorityQueueClasses;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by poorvank on 16/05/16.
 */
@SuppressWarnings("unchecked")

/**
 * @see <a href="http://stackoverflow.com/questions/2827585/what-is-super-t-syntax">Super Syantax</a>
 */
public class MinPriorityQueue<Item extends Comparable<? super Item>> implements Iterable<Item> {

    private Item[] pq;
    private int size;


    public MinPriorityQueue(Item[] items) {
        pq = (Item[]) new Comparable[items.length+1];
        size = items.length;
        for(int i=0;i<size;i++) {
            pq[i+1] = items[i];
        }


        for(int k=size/2;k>=1;k--) {
            sink(k);
        }


        if(!isMinHeap()) {
            System.out.println("Heap property not satisfied in constructor!!!");
            System.exit(1);
        }
    }

    public MinPriorityQueue(int capacity) {
        pq = (Item[]) new Comparable[capacity+1];
        size = 0;
    }

    public MinPriorityQueue() {
        this(1);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size==0;
    }

    private void exchange(int i,int j) {
        Item k = pq[i];
        pq[i] = pq[j];
        pq[j] = k;
    }


    private boolean isSmall(int i, int j) {
        return (pq[i]).compareTo(pq[j]) < 0;
    }

    /*
     * Return current position of the element in priority queue
     */
    private int swim(int k) {

        while (k>1 && isSmall(k,k/2)) {
            exchange(k/2,k);
            k=k/2;
        }
        return k;
    }

    private void resize(int capacity) {
        if(capacity>size) {
            Item[] temp = (Item[]) new Comparable[capacity];
            for(int i=1;i<=size;i++) {
                temp[i] = pq[i];
            }
            pq = temp;
        }
    }

    private void sink(int k) {

        while ((2*k)<=size) {
            int j = 2*k;
            if(j<size && isSmall(j+1,j)) {
                j++;
            }
            if(isSmall(k,j)) {
                break;
            }
            exchange(k,j);
            k = j;
        }
    }

    public Item getMinimumElement() {
        if(isEmpty()) {
            throw new NoSuchElementException("Priority Queue Underflow");
        }
        return pq[1];
    }

    // Replace root with a given Item type
    public void replaceRoot(Item k) {
        if(isEmpty()) {
            throw new NoSuchElementException("Priority Queue Underflow");
        }
        pq[1] = k;
        sink(1);
    }

    /*
     * Return final inserted position of item in pq
     */
    public int insert(Item item) {

        if(getSize()>=pq.length-1) {
            resize(2*pq.length);
        }
        pq[++size] = item;
        int pos = swim(size);
        if(!isMinHeap())  {
            System.out.println("Heap property not satisfied while inserting!!!");
            System.exit(1);
        }

        return pos;
    }

    public Iterator<Item> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Item> {

        private MinPriorityQueue<Item> copy;

        public HeapIterator() {
            copy = new MinPriorityQueue<>(size);
            for(int i=1;i<=size;i++) {
                copy.insert(pq[i]);
            }
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMin();
        }

    }

    public void deleteIndex(Integer index) {
        if(index>getSize()) {
            throw new NoSuchElementException("Index is greater than size, can't be deleted");
        }
        exchange(index,size);
        size--;
        sink(index);
        pq[size+1] = null;
        if(size>0 && size==(pq.length-1)/4) {
            resize(pq.length/2);
        }
        if(!isMinHeap())  {
            System.out.println("Heap property not satisfied!!!");
        }
    }

    public Item delMin() {
        if(isEmpty()) {
            throw new NoSuchElementException("Proiority Queue Underflow");
        }
        exchange(1, size);
        Item min = pq[size--];
        sink(1);
        pq[size+1] = null;
        if(size>0 && size==(pq.length-1)/4) {
            resize(pq.length/2);
        }
        if(!isMinHeap())  {
            System.out.println("Heap property not satisfied while deleting!!!");
            System.exit(1);
        }
        return min;

    }

    public boolean isMinHeap() {
        return isMinHeap(1);
    }

    private boolean isMinHeap(int k) {
        if (k > size) return true;
        int left = 2*k, right = 2*k + 1;
        if (left  <= size && isSmall(left, k))  return false;
        if (right <= size && isSmall(right, k)) return false;
        return isMinHeap(left) && isMinHeap(right);
    }

    public static void main(String[] args) {

        Integer arr[] = new Integer[]{25, 35, 18, 9, 46, 70, 48, 23, 78, 12};
        MinPriorityQueue<Integer> minPQ = new MinPriorityQueue(arr);
        System.out.println("Minimum element = " + minPQ.getMinimumElement() + " size of PQ = " + minPQ.size);
        for (Integer i : minPQ) {
            System.out.print(i + " ");
        }

    }

}
