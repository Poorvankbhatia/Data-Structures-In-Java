package utility;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by poorvank on 11/06/16.
 */
@SuppressWarnings("unchecked")
public class DoublyLinkList<Item extends Comparable<? super Item>>  implements Iterable<Item> {

    private int size;
    private DLLNode head;
    private DLLNode tail;

    public DoublyLinkList() {
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

    public DLLNode getHeadNode() {
        if(isEmpty()) {
            throw new NoSuchElementException("Link list is empty!");
        }
        return head;
    }

    public DLLNode getTailNode() {
        if(isEmpty()) {
            throw new NoSuchElementException("Link list is empty!");
        }
        return tail;
    }

    public void add(Item item) {

        DLLNode node = new DLLNode(item);
        if(isEmpty()) {
            head = tail = node;
        } else {
            DLLNode temp = head;
            head = node;
            head.setNext(temp);
            temp.setPrevious(head);
        }
        size++;

    }

    public void add(DLLNode node) {
        if(isEmpty()) {
            head = tail = node;
        } else {
            DLLNode temp = head;
            head = node;
            head.setNext(temp);
            temp.setPrevious(head);
        }
        size++;
    }

    public void delete(DLLNode node) {
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

    public DLLNode removeFirst() {

        if(isEmpty()) {
            throw new NoSuchElementException("Empty List");
        }
        DLLNode current = head;
        head = head.getNext();
        if(head==null) {
            tail=null;
        }
        size--;
        return current;

    }



    public DLLNode removeLast() {

        if(isEmpty()) {
            throw new NoSuchElementException("Empty List");
        }
        DLLNode prev = tail;
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
        return new DoublyLinkListIterator();
    }

    private class DoublyLinkListIterator implements Iterator<Item>  {

        DLLNode current = head;

        public boolean hasNext() {
            return current!=null;
        }

        public Item next() {
            Item item = (Item) current.getInfo();
            current = current.getNext();
            return item;
        }

    }

    public static void main(String[] args) {

        DoublyLinkList<String> dll = new DoublyLinkList();
        dll.add("a");
        dll.add("b");
        dll.add("c");
        for (String element : dll) {
            System.out.print(" " + element);
        }

        dll.removeFirst();
        dll.removeLast();

        System.out.println("\n After removing elements : ");

        for (String element : dll) {
            System.out.print(" " + element);
        }
    }

}
