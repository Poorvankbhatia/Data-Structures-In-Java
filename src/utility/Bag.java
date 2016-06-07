/*

A bag is a collection where removing items is not supported—its purpose is to
provide clients with the ability to collect items and then to iterate through the collected
items (the client can also test if a bag is empty and find its number of items). The order
of iteration is unspecified and should be immaterial to the client. To appreciate the concept,
consider the idea of an avid marble collector, who might put marbles in a bag, one
at a time, and periodically process all the marbles to look
for one having some particular characteristic. With our
Bag API, a client can add items to a bag and process them
all with a foreach statement whenever needed. Such a client
could use a stack or a queue, but one way to emphasize
that the order in which items are processed is immaterial
is to use a Bag.

 */


package utility;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by poorvank on 06/05/16.
 */
public class Bag<Item> implements Iterable<Item> {

    private Node first;
    private int size;

    public Bag() {
        first = null;
        size = 0;
    }

    private class Node {
        private Item item;
        private Node next;
    }

    public void addToBag(Item item) {
        Node old = first;
        first = new Node();
        first.item = item;
        first.next = old;
        size++;
    }

    public int getSize() {
        return size;
    }


    public Iterator<Item> iterator() {
        /*
        What is an iterator? An object from a class that implements the methods hasNext()
        and next(),
         */
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

    }

    public String toString() {

        Node current = first;
        if (current == null) {
            return "No items in the bag";
        }
        StringBuilder sb = new StringBuilder();
        while (current != null) {
            sb.append(current.item).append(" ");
            current = current.next;
        }

        return sb.toString();

    }

}
