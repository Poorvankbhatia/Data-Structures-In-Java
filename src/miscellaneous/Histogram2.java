/*

/*

Find the largest rectangular area possible in a given histogram where the largest
rectangle can be made of a number of contiguous bars. For simplicity,
assume that all bars have same width and the width is 1 unit.

For example, consider the following histogram with 7 bars of heights {6, 2, 5, 4, 5, 2, 6}.
The largest possible rectangle possible is 12
 */

package miscellaneous;

import stack.ArrayStack;
import stack.Stack;

import java.util.Arrays;

/**
 * Created by poorvank on 8/22/15.
 */
public class Histogram2 {

    public static void main(String[] args) {

        int[] array = new int[]{6, 2, 5, 4, 5, 1, 6};

        System.out.println(findMaxArea(array));

    }

    private static int findMaxArea(int[] array) {

        int n = array.length;
        Stack<Integer> leftStack = new ArrayStack<>();
        Stack<Integer> rightStack = new ArrayStack<>();

        int[] width = new int[n];

        Arrays.fill(width, 1);

        for (int i = 0; i < n; i++) {

            while (!leftStack.isEmpty() && array[i] <= array[leftStack.getTop()]) {
                leftStack.pop();
            }

            //All the bars on left are greater than current bar
            if (leftStack.isEmpty()) {
                width[i] += i;
            } else {
                width[i] += i - leftStack.getTop() - 1;
            }

            leftStack.push(i);

        }

        for (int i = n - 1; i >= 0; i--) {

            while (!rightStack.isEmpty() && array[i] <= array[rightStack.getTop()]) {
                rightStack.pop();
            }

            //All bars on the right are greater
            if (rightStack.isEmpty()) {
                width[i] += n - 1 - i;
            } else {
                width[i] += rightStack.getTop() - i - 1;
            }

            rightStack.push(i);
        }

        int max = 0;

        for (int i = 0; i < n; i++) {

            max = Math.max(max, (width[i] * array[i]));

        }

        return max;
    }

}

/*

1. For each bar, we must be able to find the biggest rectangle containing this bar. 
So the biggest one of these n rectangles is what we want.

To get the biggest rectangle for a certain bar (let's say bar[i], the (i+1)th bar), 
we just need to find out the biggest interval that contains this bar. What we know is
 that all the bars in this interval must be at least the same height with bar[i]. 
 So if we figure out how many consecutive same-height-or-higher bars are there on the 
 immediate left of bar[i], and how many consecutive same-height-or-higher bars are there on 
 the immediate right of the bar[i], we will know the length of the interval, which is the width 
 of the biggest rectangle for bar[i].

To count the number of consecutive same-height-or-higher bars on the immediate left of bar[i], we only need to find
 the closest bar on the left that is shorter than the bar[i], because all the bars between this bar and bar[i] will
  be consecutive same-height-or-higher bars.

We use a stack to dynamicly keep track of all the left bars that are shorter than a certain bar. 
In other words, if we iterate from the first bar to bar[i], when we just arrive at the bar[i] 
and haven't updated the stack, the stack should store all the bars that are no higher than bar[i-1],
 including bar[i-1] itself. We compare bar[i]'s height with every bar in the stack until we find one that
  is shorter than bar[i], which is the cloest shorter bar. If the bar[i] is higher than all the bars in the 
  stack, it means all bars on the left of bar[i] are higher than bar[i].

We can do the same thing on the right side of the i-th bar. Then we know for bar[i] how many bars are 
there in the interval.

 */