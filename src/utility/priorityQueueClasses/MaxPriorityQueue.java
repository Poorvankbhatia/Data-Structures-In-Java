package utility.priorityQueueClasses;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by poorvank on 4/21/15.
 */
public class MaxPriorityQueue<Item> implements Iterable<Item> {

    private Item[] pq;
    private int size;

    @SuppressWarnings("unchecked")
    public MaxPriorityQueue(int capacity) {
        pq = (Item[]) new Object[capacity+1];
        size = 0;
    }

    public MaxPriorityQueue() {
        this(1);
    }

    public MaxPriorityQueue(Item[] items) {
        pq = (Item[]) new Object[items.length+1];
        size = items.length;
        for(int i=0;i<size;i++) {
            pq[i+1] = items[i];
        }
        for(int k=size/2;k>=1;k--) {
            sink(k);
        }
        if(!isMaxHeap()) {
            System.out.println("Heap property not satisfied!!!");
        }
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


    /*
       Cast to comparable in case we have predifined objects in heap
     */
    private boolean isSmall(int i,int j) {
        return ((Comparable<Item>) pq[i]).compareTo(pq[j]) < 0;
    }

    private void swim(int k) {

        while (k>1 && isSmall(k/2,k)) {
            exchange(k/2,k);
            k=k/2;
        }
    }

    private void resize(int capacity) {
        if(capacity>size) {
            Item[] temp = (Item[]) new Object[capacity];
            for(int i=1;i<=size;i++) {
                temp[i] = pq[i];
            }
            pq = temp;
        }
    }

    /**
     * A Brilliant and concise method for sink. (Copied from Sedgewick Book)
     * Guaranteed orgasm after a dry run!
     */
    private void sink(int k) {

        while ((2*k)<=size) {
            int j = 2*k;
            if(j<size && isSmall(j,j+1)) {
                j++;
            }
            if(!isSmall(k,j)) {
                break;
            }
            exchange(k,j);
            k = j;
        }
    }


    public Item max() {
        if(isEmpty()) {
            throw new NoSuchElementException("Priority Queue Underflow");
        }
        return pq[1];
    }

    public void insert(Item item) {

        if(getSize()>=pq.length-1) {
            resize(2*pq.length);
        }
        pq[++size] = item;
        swim(size);
        if(!isMaxHeap())  {
            System.out.println("Heap property not satisfied!!!");
        }
    }

    public Iterator<Item> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Item> {

        private MaxPriorityQueue<Item> copy;

        public HeapIterator() {
            copy = new MaxPriorityQueue<>(size);
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
            return copy.delMax();
        }

    }

    public Item delMax() {
        if(isEmpty()) {
            throw new NoSuchElementException("Proiority Queue Underflow");
        }
        Item max = pq[1];
        exchange(1,size);
        size--;
        sink(1);
        pq[size+1] = null;
        if(size>0 && size==(pq.length-1)/4) {
            resize(pq.length/2);
        }
        if(!isMaxHeap())  {
            System.out.println("Heap property not satisfied!!!");
        }
        return max;

    }

    public boolean isMaxHeap() {
        return isMaxHeap(1);
    }

    private boolean isMaxHeap(int k) {
        if (k > size) return true;
        int left = 2*k, right = 2*k + 1;
        if (left  <= size && isSmall(k, left))  return false;
        if (right <= size && isSmall(k, right)) return false;
        return isMaxHeap(left) && isMaxHeap(right);
    }

    public static void main(String[] args) {

        Integer arr[] = new Integer[]{25, 35, 18, 9, 46, 70, 48, 23, 78, 12};
        MaxPriorityQueue<Integer> maxPQ = new MaxPriorityQueue(arr);
        System.out.println(maxPQ.max() + " " + maxPQ.size);
        for (Integer i : maxPQ) {
            System.out.print(i + " ");
        }

    }

}


/*
Proposition Q. In an N-key priority queue, the heap algorithms
require no more than 1 + lg N compares for insert
and no more than 2 lg N compares for remove the
maximum.
Proof: By Proposition P, both operations involve moving
along a path between the root and the bottom of the
heap whose number of links is no more than lg N. The
remove the maximum operation requires two compares
for each node on the path (except at the bottom): one
to find the child with the larger key, the other to decide
whether that child needs to be promoted.


Proposition Q (continued). In an index priority queue of size N,
the number of compares required is proportional to at most log N
for insert, change priority, delete, and remove the minimum.
Proof: Immediate from inspection of the code and the fact that all
paths in a heap are of length at most ~lg N.


 */
