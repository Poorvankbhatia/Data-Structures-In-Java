package stackImplementation;

/**
 * Created by poorvank on 7/6/15.
 */

/*

 The following Stack interface can be assigned any object that implements this interface no
 matter the underlying implementation
 
 Item is the object passed that the object will store at the run time

 */
public interface Stack<Item> {

    // return the top item without removing it from stack
    Item getTop();

    // return the top item and removes it from stack
    Item pop();

    // adds an item to the stack
    void push(Item item);

    // returns true if stack is empty, false otherwise
    boolean isEmpty();

    // returns the number of items in stack right now
    int size();

}
