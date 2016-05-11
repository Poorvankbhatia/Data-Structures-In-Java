package utility;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by poorvank on 03/05/16.
 */
public class Stack<Item> implements Iterable<Item> {

    private Node first;
    private int size;


    private class Node {

        private Item item;
        private Node next;

    }

    public Stack() {
        first = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first==null;
    }

    public int size() {
        return size;
    }


    //Adding elements to the front of the linked list
    public void push(Item item) {
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
        size++;
    }

    public Item pop() {

        if(isEmpty()) {
            throw new NoSuchElementException("Stack Underflow");
        }
        Item item = first.item;
        first = first.next;
        size--;
        return item;
    }

    public Item peek() {

        if(isEmpty()) {
            throw new NoSuchElementException("Stack Underlow");
        }
        return first.item;

    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current!=null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

    }


}
