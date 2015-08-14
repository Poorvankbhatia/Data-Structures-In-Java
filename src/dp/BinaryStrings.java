/*

Count number of binary strings without consecutive 1’s
Given a positive integer N, count all possible distinct binary strings of length N such that 
there are no consecutive 1’s.

Examples:

Input:  N = 2
Output: 3
// The 3 strings are 00, 01, 10

Input: N = 3
Output: 5
// The 5 strings are 000, 001, 010, 100, 101

 */

package dp;

/**
 * Created by poorvank on 5/28/15.
 */
public class BinaryStrings {

    public static void main(String[] args) {

        int n = 3;
        count(n);

    }

    private static void count(int n) {

        int[] a = new int[n];
        int[] b = new int[n];

        a[0] = 1;
        b[0] = 1;

        for (int i = 1; i < n; i++) {
            a[i] = a[i - 1] + b[i - 1];
            b[i] = a[i - 1];
        }

        System.out.println(a[n - 1] + b[n - 1]);

    }

}


/*

Let a[i] be the number of binary strings of length i which do not contain any two consecutive 1’s and which end in 0.
Similarly, let b[i] be the number of such strings which end in 1. We can append either 0 or 1 to a string ending in 0,
but we can only append 0 to a string ending in 1. This yields the recurrence relation:

a[i] = a[i - 1] + b[i - 1]
b[i] = a[i - 1] 
The base cases of above recurrence are a[1] = b[1] = 1. The total number of strings of length i is just a[i] + b[i].

 a               b
 0               1
 00,10           01
 a[3]  000,100         010
 b[3]  001,101
 */