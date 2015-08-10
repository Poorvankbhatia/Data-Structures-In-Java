/*

Implement two stacks in an array
Create a data structure twoStacks that represents two stacks. 
Implementation of twoStacks should use only one array, i.e., both stacks should use the same array for storing elements.
Following functions must be supported by twoStacks.

push1(int x) –> pushes x to first stack
push2(int x) –> pushes x to second stack

pop1() –> pops an element from first stack and return the popped element
pop2() –> pops an element from second stack and return the popped element

Implementation of twoStack should be space efficient.

 */

package miscellaneous;

/**
 * Created by poorvank on 8/10/15.
 */

class TwoStackArray {

    private int top1;
    private int top2;
    private int size;
    private int[] arr;

    public TwoStackArray(int n) {
        arr = new int[n];
        top1 = -1;
        top2 = n;
        size = n;
    }

    public void push(int stackNo, int item) {

        if (top1 < top2 - 1) {

            if (stackNo == 1) {
                top1++;
                arr[top1] = item;
            } else if (stackNo == 2) {
                top2--;
                arr[top2] = item;
            }

        } else {
            System.out.println("Stack overflow");
        }


    }

    public int pop(int stackNo) {

        if (stackNo == 1 && (top1 >= 0)) {

            int x = arr[top1];
            top1--;
            return x;

        } else if (stackNo == 2 && (top2 < size)) {
            int x = arr[top2];
            top2++;
            return x;
        } else {
            System.out.println("Stack Underflow");
            return -1;
        }

    }

}

public class ImplementTwoStacksArray {

    public static void main(String[] args) {

        TwoStackArray ts = new TwoStackArray(7);
        ts.push(1, 5);
        ts.push(2, 10);
        ts.push(2, 15);
        ts.push(1, 11);
        ts.push(2, 7);

        System.out.println("Pop from stack1  = " + ts.pop(1));
        ts.push(2, 40);
        System.out.println("Pop from stack2  = " + ts.pop(2));

    }

}

/*

This method efficiently utilizes the available space. It doesn’t cause an overflow if there is space available in arr[]. 
The idea is to start two stacks from two extreme corners of arr[]. stack1 starts from the leftmost element, 
the first element in stack1 is pushed at index 0. The stack2 starts from the rightmost corner, 
the first element in stack2 is pushed at index (n-1). Both stacks grow (or shrink) in opposite direction. 
To check for overflow, all we need to check is for space between top elements of both stacks. 

 */