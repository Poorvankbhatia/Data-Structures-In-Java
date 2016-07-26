/*

There are n stairs, a person standing at the bottom wants to reach the top. 
The person can climb either 1 stair or 2 stairs at a time. Count the number of ways, the person can reach the top.

Input: n = 1
Output: 1
There is only one way to climb 1 stair

Input: n = 2
Output: 2
There are two ways: (1, 1) and (2)

Input: n = 4
Output: 5
(1, 1, 1, 1), (1, 1, 2), (2, 1, 1), (1, 2, 1), (2, 2)

 */


package dyanamicprogramming;

import java.util.Arrays;

/**
 * Created by poorvank on 7/22/15.
 */
public class CountWaysToReachStairs {

    public static void main(String[] args) {

        int n = 4, m = 2;

        //because ways(n) is equal to fibonacci(n+1)
        System.out.println(countWaysUtil(n, m));

    }

    /**
     * *
     *
     * @param n
     * @param m
     * @return
     * @link http://www.maths.surrey.ac.uk/hosted-sites/R.Knott/Fibonacci/fibpuzzles.html
     */

    private static int countWaysUtil(int n, int m) {

        int[] result = new int[n + 1];
        result[0] = 1;
        result[1] = 1;

        for (int i = 2; i < result.length; i++) {

            for (int j = 1; j <= m && j <= i; j++) {

                result[i] = result[i] + result[i - j];

            }

        }

        System.out.println(Arrays.toString(result));
        return result[n];

    }

}


/*

We can easily find recursive nature in above problem. 
The person can reach n’th stair from either (n-1)’th stair or from (n-2)’th stair. 
Let the total number of ways to reach n’t stair be ‘ways(n)’. The value of ‘ways(n)’ can be written as following.

    ways(n) = ways(n-1) + ways(n-2)
The above expression is actually the expression for Fibonacci numbers, 
but there is one thing to notice, the value of ways(n) is equal to fibonacci(n+1).

ways(1) = fib(2) = 1
ways(2) = fib(3) = 2
ways(3) = fib(4) = 3
ways(4) = fib(3) = 5

Generalization of the above problem
How to count number of ways if the person can climb up to m stairs for a 
given value m? For example if m is 4, the person can climb 1 stair or 2 stairs or 3 stairs or 4 stairs at a time.

We can write the recurrence as following.

   ways(n, m) = ways(n-1, m) + ways(n-2, m) + ... ways(n-m, m) 




 */