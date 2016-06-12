package utility;

/**
 * Created by poorvank on 11/06/16.
 */
public class DLLNode<T extends Comparable<T>> implements Comparable<DLLNode<T>> {

    private T info;
    private DLLNode next;
    private DLLNode previous;

    public DLLNode(T info) {
       this(info,null,null);
    }

    public DLLNode(T info, DLLNode next, DLLNode previous) {
        this.info = info;
        this.next = next;
        this.previous = previous;
    }



    public T getInfo() {
        return info;
    }

    public DLLNode getNext() {
        return next;
    }

    public DLLNode getPrevious() {
        return previous;
    }

    public void setPrevious(DLLNode previous) {
        this.previous = previous;
    }

    /**
     * Refer to this link : http://stackoverflow.com/questions/21544716/implementing-comparable-with-a-generic-class
     */
    @Override
    public int compareTo(DLLNode<T> o) {
        return this.info.compareTo(o.getInfo());
    }

}
