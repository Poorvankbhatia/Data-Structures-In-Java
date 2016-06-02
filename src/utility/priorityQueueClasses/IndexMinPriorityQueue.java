/*
Index priority queue. In many applications, it makes sense to allow clients to refer
to items that are already on the priority queue. One easy way to do so is to associate
a unique integer index with each item. Moreover, it is often the case that clients have
a universe of items of a known size N and perhaps are using (parallel) arrays to store
information about the items, so other unrelated client code might already be using an
integer index to refer to items.
 */


package utility.priorityQueueClasses;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by poorvank on 19/05/16.
 */
public class IndexMinPriorityQueue<Key extends Comparable<Key>> implements Iterable<Integer> {

    private int maxSize; //Maximum number of elements in priority queue
    private int N;       //Cuurent number of elements
    private int[] pq;    //Heap (1-base index) (Index represents size and value position priority)
    private int[] qp;  //Inverse of pq -->  pq[qp[i]]=qp[pq[i]=i (Integer index same as client) (Index represents position)
    private Key[] keys;  //Keys[i]= priority of i


    public IndexMinPriorityQueue(int maxSize) {
        if(maxSize<0) {
            throw new IllegalArgumentException("MaxSize cannopt be less than 0");
        }
        this.maxSize = maxSize;
        keys = (Key[]) new Comparable[maxSize]; //Because it is a heap 1-based index
        pq = new int[maxSize+1];
        qp = new int[maxSize+1];
        for(int i=0;i<=maxSize;i++) {
            qp[i]=-1;
        }
    }

    public boolean isEmpty() {
        return N==0;
    }

    public int getCurrentSize() {
        return N;
    }

    public boolean containsIndex(int i) {
        if(i<0 || i>=maxSize) {
            throw new IndexOutOfBoundsException(i + " not present.");
        }
        return qp[i]!=-1;
    }


    private boolean isGreater(int i,int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exchange(int i,int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j]=temp;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k>1 && isGreater(k/2,k)) {
            exchange(k/2,k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = (2*k);
            if(j< N && isGreater(j,j+1)) {
                j++;
            }
            if(isGreater(j,k)) {
                break;
            }
            exchange(k,j);
            k=j;
        }
    }

    public void insert(int i,Key key) {
        if(i<0 && i>=maxSize) {
            throw new IndexOutOfBoundsException();
        }
        if(containsIndex(i)) {
            throw new IllegalArgumentException("Already present !!");
        }
        N++;
        qp[i] = N;
        pq[N] = i;
        keys[i] = key;
        swim(N);
    }

    public Key getMinimumKey() {
        if(isEmpty()) {
            throw new NoSuchElementException("Underflow!");
        }
        return keys[pq[1]];
    }

    public int getMinIndex() {
        if(isEmpty()) {
            throw new NoSuchElementException("Underflow!");
        }
        return pq[1];
    }


    //Dry Run To Understand
    public int deleteMinimum() {
        if(isEmpty()) {
            throw new NoSuchElementException("Undeflow!");
        }
        int min = pq[1];
        exchange(1,N--);
        sink(1);
        keys[min] = null;
        pq[N+1] = -1;
        qp[min] = -1;
        return min;
    }

    public Key keyOf(int i) {
        if (i < 0 || i >= maxSize) {
            throw new IndexOutOfBoundsException();
        }
        if (!containsIndex(i))
            throw new NoSuchElementException("index is not in the priority queue");
        else return keys[i];
    }

    public void changeKey(int i,Key key) {
        if(i<0 || i>=maxSize) {
            throw  new IndexOutOfBoundsException();
        }
        if(!containsIndex(i)) {
            throw new NoSuchElementException("Index not present in queue");
        }
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexMinPriorityQueue<Key> copy;

        public HeapIterator() {
            copy = new IndexMinPriorityQueue<>(pq.length - 1);
            for (int i = 1; i <= N; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.deleteMinimum();
        }
    }

    public void delete(int i) {
        if (i < 0 || i >= maxSize) throw new IndexOutOfBoundsException();
        if (!containsIndex(i)) throw new NoSuchElementException("index is not in the priority queue");
        int index = qp[i];
        exchange(index, N--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }

    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexMinPriorityQueue<String> pq = new IndexMinPriorityQueue<>(strings.length);
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        System.out.println(pq.getCurrentSize());

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.deleteMinimum();
            System.out.println(i + " " + strings[i]);
        }
        System.out.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (int i : pq) {
            System.out.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty()) {
            pq.deleteMinimum();
        }

    }

}
