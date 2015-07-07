package arrays;
import Stack.*;

/**
 * Created by poorvank on 7/6/15.
 */

class ModQueue {

    private static Stack<Integer> stack1 = new ArrayStack<Integer>();
    private static Stack<Integer> stack2 = new ArrayStack<Integer>();

    public static void enqueue(int x) {
        stack1.push(x);
    }
    
    public static int deQueue() {
        
        if(stack1.isEmpty() && stack2.isEmpty()) {
            System.out.println("Empty queue");
            return -1;
        }
        
        if(stack2.isEmpty()) {
           
            while (!stack1.isEmpty()) {
                int x = stack1.pop();
                stack2.push(x);
            }

        }
        
        return stack2.pop();
    }
    
}

public class QueueUsing2Stacks {
    
    public static void main(String[] args) {
        
        ModQueue.enqueue(1);
        ModQueue.enqueue(2);
        ModQueue.enqueue(3);
        
        System.out.println(ModQueue.deQueue());
        
    }
    
}


/*

A queue can be implemented using two stacks. Let queue to be implemented be q and stacks used to implement 
q be stack1 and stack2. q can be implemented in two ways:

Method 1 (By making enQueue operation costly)
This method makes sure that newly entered element is always at the top of stack 1, so that deQueue
 operation just pops from stack1. To put the element at top of stack1, stack2 is used.

enQueue(q, x)
  1) While stack1 is not empty, push everything from satck1 to stack2.
  2) Push x to stack1 (assuming size of stacks is unlimited).
  3) Push everything back to stack1.

dnQueue(q)
  1) If stack1 is empty then error
  2) Pop an item from stack1 and return it
Method 2 (By making deQueue operation costly)
In this method, in en-queue operation, the new element is entered at the top of stack1. 
In de-queue operation, if stack2 is empty then all the elements are moved to stack2 and finally top of stack2 is returned.

enQueue(q,  x)
  1) Push x to stack1 (assuming size of stacks is unlimited).

deQueue(q)
  1) If both stacks are empty then error.
  2) If stack2 is empty
       While stack1 is not empty, push everything from satck1 to stack2.
  3) Pop the element from stack2 and return it.
Method 2 is definitely better than method 1. Method 1 moves all the elements twice in enQueue operation, 
while method 2 (in deQueue operation) moves the elements once and moves elements only if stack2 empty.


 Method 2 where recursion (or Function Call Stack) is used to implement
 queue using only one user defined stack.

enQueue(x)
  1) Push x to stack1.

deQueue:
  1) If stack1 is empty then error.
  2) If stack1 has only one element then return it.
  3) Recursively pop everything from the stack1, store the popped item 
    in a variable res,  push the res back to stack1 and return res

 */