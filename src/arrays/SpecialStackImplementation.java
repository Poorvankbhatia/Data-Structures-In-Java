/*

Design and Implement Special Stack Data Structure 
Question: Design a Data Structure SpecialStack that supports all the stack operations like push(), pop(), isEmpty(), isFull() and an additional operation getMin() which should return minimum 
element from the SpecialStack. All these operations of SpecialStack must be O(1). 
To implement SpecialStack, you should only use standard Stack data structure and no other data structure 
like arrays, list, .. etc.

 */

package arrays;

/**
 * Created by poorvank on 4/20/15.
 */

class SpecialStack {

    private static final int MAX = 10;
    public int topStack = -1;
    public int topAStack = -1;
    int[] stack = new int[MAX];
    //Auxiliary Stack
    int[] auxiliaryStack = new int[MAX];

    public void push(int a) {

        if (topStack == MAX - 1) {
            System.out.println("Stack overflow");
            return;
        }
        topStack++;
        stack[topStack] = a;
        if (topAStack == -1 || (auxiliaryStack[topAStack] > a)) {
            topAStack++;
            auxiliaryStack[topAStack] = a;
        }

    }

    public int pop() {

        if (topStack == -1) {
            System.out.println("Stack underflow");
            return -1;
        }
        int a = topStack--;
        if (a == auxiliaryStack[topAStack]) {
            topAStack--;
        }
        return a;

    }

    public int peek() {

        if (topStack == -1) {
            System.out.println("Stack underflow");
            return -1;
        }
        return stack[topStack];
    }

    public int getMin() {

        if (topAStack == -1) {
            System.out.println("Stack underflow");
            return -1;
        }
        return auxiliaryStack[topAStack];

    }


}

public class SpecialStackImplementation {

    public static void main(String[] args) {

        SpecialStack specialStack = new SpecialStack();
        specialStack.push(18);
        specialStack.push(19);
        System.out.println("minimum is - " + specialStack.getMin());
        specialStack.push(29);
        specialStack.push(15);
        specialStack.push(16);
        System.out.println("minimum is - " + specialStack.getMin());

    }

}


/*

Use two stacks: one to store actual stack elements and other as an auxiliary stack to store minimum values. 
The idea is to do push() and pop() operations in such a way that the top of auxiliary stack is always the minimum. 
Let us see how push() and pop() operations work.

Push(int x) // inserts an element x to Special Stack 
1) push x to the first stack (the stack with actual elements)
2) compare x with the top element of the second stack (the auxiliary stack). Let the top element be y.
…..a) If x is smaller than y then push x to the auxiliary stack.
…..b) If x is greater than y then push y to the auxiliary stack.

int Pop() // removes an element from Special Stack and return the removed element 
1) pop the top element from the auxiliary stack.
2) pop the top element from the actual stack and return it.

The step 1 is necessary to make sure that the auxiliary stack is also updated for future operations.

int getMin() // returns the minimum element from Special Stack 
1) Return the top element of auxiliary stack.

We can limit the number of elements in auxiliary stack. We can push only when the incoming element of main stack is 
smaller than or equal to top of auxiliary stack. Similarly during pop, if the pop off element equal to top of 
auxiliary stack, remove the top element of auxiliary stack. Following is modified implementation of push() and pop().

 */