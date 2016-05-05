/*

Next Greater Element
Given an array, print the Next Greater Element (NGE) for every element. 
The Next greater Element for an element x is the first greater element on the right side of x in array. 
Elements for which no greater element exist, consider next greater element as -1.

Examples:
a) For any array, rightmost element always has next greater element as -1.
b) For an array which is sorted in decreasing order, all elements have next greater element as -1.
c) For the input array [4, 5, 2, 25}, the next greater elements for each element are as follows.

Element       NGE
   4      -->   5
   5      -->   25
   2      -->   25
   25     -->   -1
d) For the input array [13, 7, 6, 12}, the next greater elements for each element are as follows.

  Element        NGE
   13      -->    -1
   7       -->     12
   6       -->     12
   12     -->     -1

 */

package stack;
import utility.Stack;

/**
 * Created by poorvank on 7/27/15.
 */
public class NextGreaterElement {

    public static void main(String[] args) {

        int[] array = new int[]{11, 13, 21, 3};

        printNGE(array);

    }

    private static void printNGE(int[] array) {

        Stack<Integer> stack = new Stack<>();
        int element, next;

        stack.push(array[0]);

        for (int i = 1; i < array.length; i++) {

            next = array[i];
            element = stack.pop();

            while (element < next) {
                System.out.println(element + " " + next);
                if (stack.isEmpty()) {
                    break;
                }
                element = stack.pop();
            }

            if (element > next) {
                stack.push(element);
            }

            stack.push(next);
        }

        while (!stack.isEmpty()) {
            element = stack.pop();
            System.out.println(element + " " + -1);
        }

    }

}


/*

1) Push the first element to stack.
2) Pick rest of the elements one by one and follow following steps in loop.
….a) Mark the current element as next.
….b) If stack is not empty, then pop an element from stack and compare it with next.
….c) If next is greater than the popped element, then next is the next greater element fot the popped element.
….d) Keep poppoing from the stack while the popped element is smaller than next. next becomes the next greater element for all such popped elements
….g) If next is smaller than the popped element, then push the popped element back.
3) After the loop in step 2 is over, pop all the elements from stack and print -1 as next element for them.

 */