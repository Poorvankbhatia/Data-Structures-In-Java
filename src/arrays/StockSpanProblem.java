/*

The Stock Span Problem
The stock span problem is a financial problem where we have a series of n daily price 
quotes for a stock and we need to calculate span of stock’s price for all n days. 
The span Si of the stock’s price on a given day i is defined as the maximum number of 
consecutive days just before the given day, for which the price of the stock on the
 current day is less than or equal to its price on the given day.
For example, if an array of 7 days prices is given as {100, 80, 60, 70, 60, 75, 85}, 
then the span values for corresponding 7 days are {1, 1, 1, 2, 1, 4, 6}

 */

package arrays;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by poorvank on 6/18/15.
 */
public class StockSpanProblem {

    public static void main(String[] args) {

        int[] price = new int[]{10, 4, 5, 90, 120, 80};

        int[] s = new int[price.length];
        s[0] = 1;

        Stack<Integer> stack = new Stack<>();

        stack.push(0);

        for (int i = 1; i < price.length; i++) {

            while (!stack.isEmpty() && price[i] > price[stack.peek()]) {
                stack.pop();
            }

            s[i] = (stack.isEmpty()) ? (i + 1) : (i - stack.peek());

            stack.push(i);

        }

        System.out.println(Arrays.toString(s));

    }

}


/*

A Linear Time Complexity Method
We see that S[i] on day i can be easily computed if we 
know the closest day preceding i, such that the price is greater
than on that day than the price on day i. If such a day exists, let’s call it h(i), otherwise, we define h(i) = -1.
The span is now computed as S[i] = i – h(i).

To implement this logic, we use a stack as an abstract data type to store the days i, h(i), h(h(i)) 
and so on. When we go from day i-1 to i, we pop the days when the price of the stock was 
less than or equal to price[i] and then push the value of day i back into the stack.

Time Complexity: O(n). It seems more than O(n) at first look. If we take a closer look, 
we can observe that every element of array is added and removed from stack at most once. 
So there are total 2n operations at most. Assuming that a stack operation takes O(1) time, 
we can say that the time complexity is O(n).

Auxiliary Space: O(n) in worst case when all elements are sorted in decreasing order.

 */