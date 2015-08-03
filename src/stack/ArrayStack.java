package stack;

/**
 * Created by poorvank on 7/6/15.
 */
public class ArrayStack<Item> implements Stack<Item> {

    private final static int DEFAULT_SIZE = 10;
    private Item container[];
    private int top;

    public ArrayStack() {
        this(DEFAULT_SIZE);
    }

    public ArrayStack(int initSize) {
        container = (Item[]) new Object[initSize];
        top = -1;
    }

    public Item getTop() {
        if (top == -1)
            return null;
        return container[top];
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public Item pop() {
        if (top == -1)
            return null;
        return container[top--];
    }

    public void push(Item itm) {
        container[++top] = itm;
    }

    public int size() {
        return (top + 1);
    }

}
