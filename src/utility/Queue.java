package utility;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by poorvank.b on 10/05/16.
 */
public class Queue<Item> implements Iterable<Item> {

    private Node first;
    private Node last;
    private int size;

    private class Node {
        private Node next;
        private Item item;
    }

    public Queue() {
        first = null;
        last = null;
        size = 0;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize()==0;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()) {
           first = last;
        }
        else {
            oldLast.next = last;
        }
        size++;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    public Item dequeue() {
        if(isEmpty()) {
            throw new NoSuchElementException("Queue Underflow");
        }
        Item item = first.item;
        first = first.next;
        size--;
        if(isEmpty()) {
            last = null;
        }

        return item;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        Node current = first;

        public boolean hasNext() {
            return current!=null;
        }

        public Item next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public static void main(String[] args) {

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);

        for (Integer integer : queue) {
            System.out.print(integer + " ");
        }


    }

}


